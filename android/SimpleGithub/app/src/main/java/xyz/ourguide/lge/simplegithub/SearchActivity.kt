package xyz.ourguide.lge.simplegithub

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.search_activity.*
import java.util.concurrent.TimeUnit

// EditText의 내용이 변경될 때마다, 검색 API를 호출하는 기능을
// Rx + RxDataBinding 이용해서 만들어봅시다.

class SearchActivity : AppCompatActivity() {
    val disposeBag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        // Rx Databinding
        disposeBag += searchEditText.textChanges()
                .throttleLast(1000, TimeUnit.MILLISECONDS)
                .filter {
                    it.isNotBlank()
                }
                .map {
                    it.toString()
                }
                .flatMap {
                    rxGithubApi.rxSearchRepo(it)
                }
                .subscribeBy(
                        onNext = {
                            Log.e(TAG, "onNext - $it")
                        },
                        onError = {
                            Log.e(TAG, "onError - ${it.localizedMessage}")
                        },
                        onComplete = {
                            Log.e(TAG, "onComplete")
                        }
                )
    }

    override fun onDestroy() {
        disposeBag.dispose()

        super.onDestroy()
    }
}