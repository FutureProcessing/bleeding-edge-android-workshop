package fp.com.todo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.view.Menu
import android.view.View
import com.squareup.picasso.Picasso
import fp.com.todo.backend.Task
import kotlinx.android.synthetic.activity_add_item.*

public class AddTaskActivity : ActionBarActivity() {

    private var imageUrl = ""
    private var task: Task? = null

    // TODO: Use Companion Object to define constants
    companion object {
        val TASK = "EXTRA_TASK"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        // TODO: Handle listeners
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

        // TODO: Use Picasso to display image from passed URL
    }

    private fun itemName() = et_item_name.getText().toString()
}
