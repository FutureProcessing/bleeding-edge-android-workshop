package fp.com.todo.backend

import retrofit.client.Response
import retrofit.http.Body
import retrofit.http.GET
import retrofit.http.PUT
import rx.Observable

public trait Backend {
    GET("/task")
    fun getTasks(): Observable<List<Task>>

    GET("/image/urls")
    fun getImagesUrls(): Observable<List<String>>

    PUT("/task/{id}")
    fun updateTask(Body task: Task): Observable<Response>
}