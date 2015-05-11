package fp.com.todo.backend

import retrofit.client.Response
import retrofit.http.*
import rx.Observable

public trait Backend {
    GET("/task")
    fun getTasks(): Observable<List<Task>>

    GET("/image/urls")
    fun getImagesUrls(): Observable<List<String>>

    PUT("/task/{id}")
    fun updateTask(Body task: Task): Observable<Response>

    POST("/task")
    fun postTask(Body task: Task): Observable<Response>

    GET("/validator")
    fun validateName(Query("name") name: String): Observable<Boolean>
}