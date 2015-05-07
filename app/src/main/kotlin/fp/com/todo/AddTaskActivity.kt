package fp.com.todo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.view.Menu
import fp.com.todo.backend.Task
import kotlinx.android.synthetic.activity_add_item.et_item_name

public class AddTaskActivity : ActionBarActivity() {

    private var imageUrl = ""
    private var task: Task? = null

    // TODO: Use Companion Object to define constants

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        // TODO: Handle listeners
        // TODO: Load tasks details from passed Task in Bundle
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
