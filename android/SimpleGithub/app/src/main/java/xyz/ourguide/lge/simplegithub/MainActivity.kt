package xyz.ourguide.lge.simplegithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

// SSL - proxy
// mLGE: 4f3d2s1a@@

// OkHttpClient

// REST(HTTP) API
//   HTTP METHOD
//      GET
//      POST
//      PUT
//      DELETE
//      HEAD
//      ...

//  HTTP Header
//   Content-Type    application/json
//   Accept          application/json

// Retrofit
//   => HTTP API 사용하는데 발생하는 일련의 반복되는 코드를 구조화할 수 있다.

// Gson(json) -> Model
const val TAG = "MainActivity"

// Model
// "login": "ourguide",
//  "id": 591413,
//  "avatar_url": "https://avatars0.githubusercontent.com/u/591413?v=4",

// model을 사용할 때 반드시 주의할 점
//  => Proguard
//     1) 난독화 - 코드를 읽을 수 없도록, 이름을 변경하는 작업
//     2) 사용하지 않는 메소드를 제거해서, apk 크기를 작게 만들어 줍니다.

//  => model 클래스들은 반드시 난독화의 예외 규칙에 주어야 합니다.
//    Proguard 내일 보여줄 것.
data class User(val login: String,
                val id: Int,
                @field:SerializedName("avatar_url") val avatarUrl: String,
                val location: String)

class MainActivity : AppCompatActivity() {
    // private static final String TAG = "MainActivity"
    // private static final String TAG = MainActivity::class.getSimpleName()
    companion object {
        // const val: 컴파일 타임 상수     // const, constexpr
        //       val: 런타임 상수         // const

        // const val TAG = "MainActivity"
        // val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sampleButton.setOnClickListener {
            val client = OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }.build()

            val request = Request.Builder().apply {
                url("https://api.github.com/users/ourguide")
                header("Content-Type", "application/json")
            }.build()

            val call = client.newCall(request)

            // OkHttp call 객체
            //   => 동기(execute) / 비동기 호출할지 선택할 수 있다.
            //  비동기 호출
            //    : 결과를 반환값으로 받을 수 없다. - Callback Pattern
            /*
            call.enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity,
                            "Fail!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity,
                            "Ok!", Toast.LENGTH_SHORT).show()
                    }
                }
            })
            */

            val gson = GsonBuilder().apply {
            }.create()

            call.enqueue(
                success = { response ->
                    response.body()?.let { body ->
                        val json = body.string()
                        val user = gson.fromJson(json, User::class.java)

                        runOnUiThread {
                            Toast.makeText(
                                this@MainActivity,
                                "$user", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }


//                    runOnUiThread {
//                        Toast.makeText(this@MainActivity,
//                            "Ok!", Toast.LENGTH_SHORT).show()
//                    }
                },
                failure = {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity,
                            "Fail!", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }


        /*
        sampleButton.setOnClickListener {
            // Background Thread 생성 코드
            //  => AsyncTask
            Thread {
                val client = OkHttpClient.Builder().apply {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }.build()
                // 동기 / 비동기

                // 1. Request
                val request = Request.Builder().apply {
                    url("https://api.github.com/users/ourguide")
                }.build()

                // 2. 동기 호출
                //    : 절대 UI(메인) 스레드에서 호출하면 안됩니다.
                //    => NetworkOnMainThreadException
                val call = client.newCall(request)

                // 3. 결과를 받는다.
                val response: Response = call.execute()
                response.body()?.let { responseBody ->
                    Log.e(TAG, responseBody.string())

                    // 다른 스레드에서 UI를 업데이트 하면 안됩니다.
                    // 반드시 UI의 업데이트는 메인 스레드에서 수행되어야 한다.
                    runOnUiThread {
                        Toast.makeText(this, "ok!!", Toast.LENGTH_SHORT).show()
                    }

                }
            }.start()
        }
        */
    }
}


// OkHttpClient Call객체의 enqueue는 무명객체 밖에 사용할 수 없다.
//  => 람다 블록을 통해 사용할 수 있도록 래퍼 함수를 제공하자.
inline fun Call.enqueue(crossinline success: (Response) -> Unit,
                        crossinline failure: (IOException) -> Unit) {
    enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {
            failure(e)
        }

        override fun onResponse(call: Call, response: Response) {
            success(response)
        }
    })
}




//   => Retrofit
//         implementation "com.squareup.retrofit2:retrofit:$retrofit_version"
//         implementation "com.squareup.retrofit2:converter-gson:$retrofit_version"

//      OkHttp
//         implementation "com.squareup.okhttp3:okhttp:$okhttp_version"
//         implementation "com.squareup.okhttp3:logging-interceptor:$okhttp_version"

//      Glide - Image Processing Library
//        apply plugin: 'kotlin-kapt'

//        implementation "com.github.bumptech.glide:glide:4.9.0"
//        annotationProcessor "com.github.bumptech.glide:compiler:4.9.0" // Java
//        kapt "com.github.bumptech.glide:compiler:4.9.0"                // Kotlin

//      RxJava / RxAndroid
//        implementation "io.reactivex.rxjava2:rxjava:2.2.7"
//        implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'

//      RxKotlin
//        implementation 'io.reactivex.rxjava2:rxkotlin:2.3.0'

//      RxBinding
//        implementation 'com.jakewharton.rxbinding3:rxbinding:3.0.0-alpha2'
//        implementation 'com.jakewharton.rxbinding3:rxbinding-core:3.0.0-alpha2'
//        implementation 'com.jakewharton.rxbinding3:rxbinding-recyclerview:3.0.0-alpha2'
//        implementation 'com.jakewharton.rxbinding3:rxbinding-appcompat:3.0.0-alpha2'

//      Gson
//        implementation 'com.google.code.gson:gson:2.8.5'