package fp.com.todo

import android.app.ListActivity
import android.os.Bundle
import fp.com.todo.backend.MockedBackendService
import fp.com.todo.backend.Task
import kotlinx.android.synthetic.activity_main.btn_add
import rx.Observable
import rx.lang.kotlin.observable

public class MainActivity : ListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_add.setAlpha(0f)
        tasks().subscribe() {
            setListAdapter(TasksAdapter(this, it))
        }
    }

    override fun onPause() {
        super.onPause()
        btn_add.setAlpha(0f)
    }

    override fun onResume() {
        super.onResume()
        btn_add.animate()
                .setStartDelay(1000)
                .alpha(1f)
                .withLayer().start()
    }

    private fun tasks(): Observable<List<Task>> {
        return observable { subscriber ->
            subscriber.onNext(MockedBackendService().tasks.map { it -> it.getValue() })
            subscriber.onCompleted()
        }
    }
}
