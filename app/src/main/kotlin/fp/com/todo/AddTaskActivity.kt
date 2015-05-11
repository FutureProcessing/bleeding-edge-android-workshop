package fp.com.todo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.view.Menu
import android.view.View
import com.squareup.picasso.Picasso
import fp.com.todo.backend.Backend
import fp.com.todo.backend.Task
import fp.com.todo.services.InvalidTaskNameException
import fp.com.todo.services.NameValidator
import kotlinx.android.synthetic.activity_add_item.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivityForResult
import retrofit.client.Response
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates

public class AddTaskActivity : ActionBarActivity() {

    private var imageUrl = ""
    private var task: Task? = null

    // TODO: Use Companion Object to define constants
    companion object {
        val TASK = "EXTRA_TASK"
        val IMAGE = "EXTRA_IMAGE"
    }

    var backend: Backend by Delegates.notNull()
        [Inject] set

    var nameValidator: NameValidator by Delegates.notNull()
        [Inject] set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        TodoApplication.graph.inject(this)

        // TODO: Handle listeners
        btn_add_image.setOnClickListener { startActivityForResult<ImagesActivity>(0) }
        btn_cancel.setOnClickListener { onBackPressed() }
        btn_ok.setOnClickListener { buildAndSendTask() }

        // TODO: Load tasks details from passed Task in Bundle
        task = getIntent().getSerializableExtra(TASK) as Task?
        if (task != null) {
            loadTaskToView(task!!)
            btn_add_image.setVisibility(View.GONE)
            btn_ok.setVisibility(View.GONE)
            btn_cancel.setVisibility(View.GONE)
            et_item_name.setEnabled(false)
        }
    }

    private fun buildAndSendTask() {
        nameValidator.
                isValid(itemName()).
                flatMap(
                        {
                            if (it) backend.postTask(buildTask())
                            else Observable.error<Response>(InvalidTaskNameException())
                        }).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(
                        {
                            response ->
                            longToast("Task sent")
                            finish()
                        },
                        {
                            throwable ->
                            when (throwable) {
                                is InvalidTaskNameException -> longToast("Invalid task name")
                                else -> longToast("Task not sent")
                            }
                            Timber.e(throwable, "Posting task failed")
                        }
                )
    }

    private fun loadTaskToView(task: Task) {
        et_item_name.setText(task.name)
        Picasso.with(this).load(task.imageUrl).into(iv_item_image)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_add_item, menu)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data == null || data.getExtras() == null || resultCode != Activity.RESULT_OK) {
            return
        }

        // TODO: Store selected image URL
        imageUrl = data.getExtras().getString(IMAGE)

        // TODO: Use Picasso to display image from passed URL
        Picasso.with(this).load(imageUrl).into(iv_item_image)
    }

    private fun itemName() = et_item_name.getText().toString()


    private fun buildTask(): Task {
        if (task != null) {
            return task!!
        }

        return Task(0, itemName(), false, imageUrl)
    }
}
