package xyz.ourguide.lge.simplegithub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import xyz.ourguide.lge.simplegithub.model.RepoSearchResponse
import xyz.ourguide.lge.simplegithub.model.User
import java.util.concurrent.TimeUnit
import xyz.ourguide.lge.simplegithub.another.User as AnotherUser

// Kotlin 에서는 import 하는 클래스의 이름을 변경하는 기능을 제공합니다.

// Retrofit + Rx
//  => Reactive Extension

// Rx: '비동기 이벤트'를 컬렉션처럼 일반화된 연산으로 다룰 수 있습니다.
//  - 비동기 코드는 흐름을 제어하는 것이 어렵다.

//  a() -> b(result_a) -> c(result_b)
// 동기
// {
//    val result_a = a()
//    val result_b = b(result_a)
//    c(result_b)
// }

// 비동기 - Callback
/*
    // Callback 지옥
    a({ result_a, err ->
        if (err) {
            return
        }

        b(result_a, { result_b, err ->
            if (err) {
                return
            }

            c(result_b, { result_c, err ->
                if (err) {
                   return
                }
            })
        })
    })
*/

// Collection
//    Iterable  -  Iterator
// Iterator Pattern
//   : 컬렉션의 내부 구조에 상관없이 요소를 열거하는 객체

class SListIterator(var current: SList.Node?) : Iterator<Int> {
    override fun hasNext(): Boolean = current != null

    override fun next(): Int {
        val ret = current?.value
        current = current?.next

        return ret!!
    }
}

class SList : Iterable<Int> {
    override fun iterator(): Iterator<Int> = SListIterator(head)

    class Node(val value: Int, val next: Node?)

    var head: Node? = null

    fun addFront(value: Int) {
        head = Node(value, head)
    }

    fun getFront(): Int? = head?.value
}

// 비동기의 이벤트 - Collection
//   : 이벤트(데이터)가 발생하는 시점을 알 수 없기 때문에
//     push: 데이터가 발생하였을 때, Observable이 알려주는 형태

//  Observable(Iterable) <- Observer(Iterator)

// Rx 요소
//  1. Observable
//   : 이벤트를 만들어 내는 주체로, 이벤트 스트림을 통해 이벤트를 내보냅니다.
//     한개부터 여러개의 이벤트를 생성할 수 있으며, 이벤트를 만들지 않을 수도 있습니다.

//  2. Observer
//   : Observable에서 만들어진 이벤트에 반응하는 객체
//     이벤트가 발생하였을 때, 수행할 작업을 정의합니다.
//     "Observer가 Observable을 구독(subscribe)한다" 라고 합니다.
//     - onNext      : 이벤트(데이터)가 발생하였을 때
//     - onComplete  : 이벤트 스트림이 종료되었을 때
//     - onError     : 에러가 발생하였을 때

//  3. Operator(연산자)
//   : 연산자는 이벤트 스트림을 통해 전달되는 이벤트를 처리합니다.(변환, 필터 ...)

//  4. Scheduler
//   : 작업을 수행할 스레드를 지정합니다.
//     UI(main) 스레드 / IO 스레드 / 작업 스레드 / 새로운 스레드 ...
//     observerOn 메소드를 사용해서, 작업을 지정하는 것이 가능합니다.

//  5. Disposable - 비메모리 자원
//   : Observer가 Observable을 구독할 때 생성되는 객체로 Observable에서 만드는 이벤트 스트림과
//     필요한 리소스를 관리하는 기능이 있습니다.
//     Disposable 객체를 반드시 해지해주어야 한다.
//       dispose() 함수를 통해 해지할 수 있습니다.
//     CompositeDisposable을 사용하면, 여러 개의 Disposable 객체의 수명을 관리할 수 있습니다.

// Rx
interface GithubApi2 {
    @GET("users/{login}")
    fun rxGetUser(@Path("login") login: String): Observable<User>

    @GET("search/repositories") // ?q={query}
    fun rxSearchRepo(@Query("q") query: String): Observable<RepoSearchResponse>
}

// 2. Retrofit 라이브러리가 위의 인터페이스를 직접 분석해서, 코드를 생성해준다.
val rxRetrofit: Retrofit = Retrofit.Builder().apply {
    baseUrl("https://api.github.com/")
    client(httpClient)

    addConverterFactory(GsonConverterFactory.create())

    // Call -> Rx Observable
    //  - RxJava2CallAdapterFactory.create(): 동기(Test 용)
    //    : 메인 스레드에서는 절대 네트워크 비동기 요청을 사용할 수 없습니다.

    //  - RxJava2CallAdapterFactory.createAsync(): 비동기
    addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())

}.build()

val rxGithubApi: GithubApi2 = rxRetrofit.create(GithubApi2::class.java)

class MainActivity3 : AppCompatActivity() {
    // lateinit var disposable: Disposable
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sampleButton.setOnClickListener {
            // RxJava
            /*
            rxGithubApi.rxGetUser("ourguide")
                .subscribe({
                    // onNext
                    Log.e(TAG, "onNext - $it")
                }, {
                    // onError
                    Log.e(TAG, "onError - ${it.localizedMessage}")
                }, {
                    // onComplete
                    Log.e(TAG, "onComplete")
                })
            */

            // 1. 스레드 지정 기능
            //   => observeOn: Observer가 동작하는 스레드를 지정하고 싶다.
            //      subscribeOn: Observable이 동작하는 스레드를 지정하고 싶다.
            /*
            rxGithubApi.rxGetUser("ourguide")
                .subscribeOn(Schedulers.io())                // io Thread
                .observeOn(AndroidSchedulers.mainThread())   // RxAndroid
                .doOnSubscribe {
                    Log.e(TAG, "doOnSubscribe")
                }
                .doOnComplete {
                    Log.e(TAG, "doOnComplete")
                }
                .doOnTerminate {
                    Log.e(TAG, "doOnTerminate")
                }
                .doFinally {
                    Log.e(TAG, "doFinally")
                }
                .subscribeBy(
                    onNext = {
                        Toast.makeText(this, "onNext", Toast.LENGTH_SHORT).show()
                        Log.e(TAG, "onNext - $it")
                    },
                    onError = {
                        Log.e(TAG, "onError - ${it.localizedMessage}")
                    },
                    onComplete = {
                        Log.e(TAG, "onComplete")
                    }
                )
            */

            // 2. 연산자
            //   map, filter
            //   throttle: 배압 조절 기능
            /*
            rxGithubApi.rxGetUser("ourguide")
                .throttleFirst(5, TimeUnit.SECONDS)  // 0.3
                .map {
                    it.avatarUrl
                }
                .filter {
                    it != "null"
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        Log.e(TAG, "onNext - $it")
                        val intent = I ntent(this, MainActivity::class.java)
                        startActivity(intent)
                    },
                    onError = {

                    }
                )
             */

            /*
            rxGithubApi.rxGetUser("ourguide").subscribeBy {
                rxGithubApi.rxSearchRepo("sample").subscribeBy {

                }
            }
            */


            // CompositeDisposable 을 만들어서, 내부적으로 발생하는 Disposable 객체에 대해서
            // 명시적인 해지를 해주어야 한다.

            // flatMap
            //  : 비동기 연산의 흐름을 제어하는 것이 가능하다.
            rxGithubApi.rxGetUser("ourguide").flatMap { _ ->
                rxGithubApi.rxSearchRepo("sample")
            }.subscribeBy {
                Log.e(TAG, "${it.totalCount}")
            }.addTo(compositeDisposable)


            // zip
            /*
            compositeDisposable.plusAssign(
                Observables.zip(
                    rxGithubApi.rxGetUser("ourguide"),
                    rxGithubApi.rxSearchRepo("sample")
                ).doOnDispose {
                    Log.e(TAG, "doOnDispose")
                }.subscribeBy { (user, res) ->
                    // val user = result.first
                    // val res = result.second

                    Log.e(TAG, "${user.id} / ${res.totalCount}")
                }
            )
            */

            // https://stackoverflow.com/questions/49154069/why-is-doondispose-not-called
            compositeDisposable += Observables.zip(
                rxGithubApi.rxGetUser("ourguide"),
                rxGithubApi.rxSearchRepo("sample")
            ).doOnDispose {
                Log.e(TAG, "doOnDispose")
            }.subscribeBy { (user, res) ->
                // val user = result.first
                // val res = result.second

                Log.e(TAG, "${user.id} / ${res.totalCount}")
            }


        }

        searchButton.setOnClickListener {
            // disposable.dispose()
            // Log.e(TAG, "${disposable.isDisposed}")

            val list = listOf(1, 2, 3, 4, 5)
            // list[0] - operator 함수
            val a = list.get(0)
            val b = list[0]

            // List<Integer> list = Arrays.asList(1, 2, 3, 4, 5)
            // list.get(0)
        }

    }

    override fun onDestroy() {
        // Rx를 통해서 생성된 disposable 객체는 명시적인 종료 메소드를 통해 파괴되어야 한다.
        // disposable.dispose()

        compositeDisposable.dispose()

        super.onDestroy()
    }
}

// += 연산자를 통해 아래의 함수를 호출할 수 있습니다.
//  : 코틀린은 연산자 오버로딩의 기법이 함수의 이름으로 약속되어 있습니다.
operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    this.add(disposable)
}


/*
class MainActivity3: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sampleButton.setOnClickListener {
            val list = SList()
            list.addFront(10)
            list.addFront(20)
            list.addFront(30)

            // list.getFront()?.let {
            //    Log.e(TAG, "value: $it")
            // }

            /*
            val iter = list.iterator()
            while (iter.hasNext()) {
                Log.e(TAG, "${iter.next()}")
            }
            */
            for (e in list) {
                Log.e(TAG, "$e")
            }
        }


    }
}
*/