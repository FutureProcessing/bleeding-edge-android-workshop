package fp.com.todo.backend

import retrofit.client.Response
import rx.Observable

public class BackendService(val service: Backend) : Backend {
    override fun postTask(task: Task): Observable<Response> {
        return service.postTask(task)
    }

    override fun updateTask(task: Task): Observable<Response> {
        return service.updateTask(task)
    }

    override fun getImagesUrls(): Observable<List<String>> {
        return service.getImagesUrls()
    }

    override fun getTasks(): Observable<List<Task>> {
        return service.getTasks()
    }
}