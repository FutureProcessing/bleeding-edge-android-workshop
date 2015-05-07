package fp.com.todo.backend

import rx.Observable

public class BackendService(val service: Backend) : Backend {
    override fun getImagesUrls(): Observable<List<String>> {
        return service.getImagesUrls()
    }

    override fun getTasks(): Observable<List<Task>> {
        return service.getTasks()
    }
}