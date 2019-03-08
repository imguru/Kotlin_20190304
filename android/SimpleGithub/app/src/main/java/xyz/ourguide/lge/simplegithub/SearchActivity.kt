package xyz.ourguide.lge.simplegithub

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.item_search.view.*
import kotlinx.android.synthetic.main.search_activity.*
import xyz.ourguide.lge.simplegithub.model.GithubRepo
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

// EditText의 내용이 변경될 때마다, 검색 API를 호출하는 기능을
// Rx + RxDataBinding 이용해서 만들어봅시다.

class SearchActivity : AppCompatActivity() {
    val disposeBag = CompositeDisposable()
    val adapter = SearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        searchRecyclerView.layoutManager = LinearLayoutManager(this)
        searchRecyclerView.adapter = adapter

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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            Log.e(TAG, "onNext - $it")
                            adapter.items = it.items
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

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.Holder>() {
    var items: List<GithubRepo> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(parent)

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val model = items[position]

        with (holder.itemView) {
            nameTextView.text = model.fullName
            langTextView.text = model.language
        }
    }

    class Holder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                    R.layout.item_search, parent, false
            )
    )
}













