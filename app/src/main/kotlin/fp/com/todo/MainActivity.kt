package fp.com.todo

import android.app.ListActivity
import android.os.Bundle
import fp.com.todo.backend.Backend
import fp.com.todo.backend.Task
import kotlinx.android.synthetic.activity_main.btn_add
import kotlinx.android.synthetic.activity_main.srl_tasks
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates

public class MainActivity : ListActivity() {

    var backend: Backend by Delegates.notNull()
        [Inject] set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TodoApplication.graph.inject(this)
        btn_add.setAlpha(0f)
        srl_tasks.setOnRefreshListener({ downloadData() })
        btn_add.setOnClickListener { startActivity<AddTaskActivity>() }
        downloadData()
    }

    private fun downloadData() {
        backend.getTasks()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .finallyDo({ srl_tasks.setRefreshing(false) })
                .subscribe(
                        {
                            setListAdapter(TasksAdapter(this, it, { updateTask(it) }))
                        },
                        {
                            throwable ->
                            toast("Loading tasks failed")
                            Timber.e(throwable, "Loading tasks failed")
                        })
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

    private fun updateTask(task: Task) {
        backend.updateTask(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            response ->
                            toast("Update task ${task.name} finished")
                        },
                        {
                            throwable ->
                            toast("Update task ${task.name} failed")
                            Timber.e(throwable, "Update task $task failed")
                        })
    }

}
