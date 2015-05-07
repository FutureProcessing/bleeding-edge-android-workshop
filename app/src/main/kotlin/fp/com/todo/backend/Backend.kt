package fp.com.todo.backend

import retrofit.http.GET
import rx.Observable

public trait Backend {
    GET("/task")
    fun getTasks(): Observable<List<Task>>

    GET("/image/urls")
    fun getImagesUrls(): Observable<List<String>>
}