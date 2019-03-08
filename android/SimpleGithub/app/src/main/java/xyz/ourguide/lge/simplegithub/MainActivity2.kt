package xyz.ourguide.lge.simplegithub

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import xyz.ourguide.lge.simplegithub.model.RepoSearchResponse
import xyz.ourguide.lge.simplegithub.model.User

// letsencrypt
//   : https 인증서를 무료로 발급 받을 수 있습니다.
//     단점: 3개월마다 갱신해줘야 합니다.
//    => 아마존에서 인증서 발급 받아서 사용합니다.

// ngrok
//   : 테스트 목적으로 https 할당 받아서 사용할 수 있는 개발자 도구


// baseUrl - https://api.github.com/
// GET - users/{login}
// GET - search/repositories?q={text}

// GithubApi.kt
// 1. REST API에 대한 inteface를 정의합니다.
interface GithubApi {
    @GET("users/{login}")
    // @Headers("Content-Type", "application/json")
    fun getUser(@Path("login") login: String): Call<User>

    @GET("search/repositories") // ?q={query}
    fun searchRepo(@Query("q") query: String): Call<RepoSearchResponse>
}

// 2. Retrofit 라이브러리가 위의 인터페이스를 직접 분석해서, 코드를 생성해준다.
val httpClient: OkHttpClient = OkHttpClient.Builder().apply {

}.build()

val retrofit: Retrofit = Retrofit.Builder().apply {
    baseUrl("https://api.github.com/")
    client(httpClient)

    addConverterFactory(GsonConverterFactory.create())
}.build()

val githubApi: GithubApi = retrofit.create(GithubApi::class.java)


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchButton.setOnClickListener {
            githubApi.searchRepo("gson").enqueue(
                    success = { response ->
                        if (response.isSuccessful) {
                            response.body()?.let {
                                Log.e(TAG, "$it")
                            }
                        }
                    },
                    failure = {

                    }
            )

        }


        sampleButton.setOnClickListener {
            val call = githubApi.getUser("ourguide")

            // Retrofit Callback의 호출은 UI 스레드에서 호출됨을 보장합니다.
            //  => runOnUiThread / Handler 를 이용할 필요가 없습니다.
            call.enqueue(
                    success = { response: Response<User> ->
                        response.body()?.let { user ->
                            Toast.makeText(
                                    this@MainActivity2,
                                    "Ok - $user",
                                    Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    failure = { t ->
                        Toast.makeText(
                                this@MainActivity2,
                                "Failed - ${t.localizedMessage}",
                                Toast.LENGTH_SHORT
                        ).show()
                    }
            )


            /*
            call.enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity2,
                        "Fail!", Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Toast.makeText(
                        this@MainActivity2,
                        "Ok!", Toast.LENGTH_SHORT
                    ).show()
                }
            })
            */
        }

    }
}

// Retrofit Call을 람다를 통해 사용할 수 있도록 해준다.
inline fun <T> Call<T>.enqueue(
        crossinline success: (Response<T>) -> Unit,
        crossinline failure: (Throwable) -> Unit
) {
    enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, e: Throwable) {
            failure(e)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            success(response)
        }
    })
}








