package xyz.ourguide.lge.simplegithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

// SSL - proxy
// mLGE: 4f3d2s1a@@

// OkHttpClient

const val TAG = "MainActivity"

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
            // Background Thread 생성 코드
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
                }
            }.start()
        }
    }
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