package fp.com.todo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import fp.com.todo.backend.Task

public class TasksAdapter : ArrayAdapter<Task> {

    constructor(context: Context, tasks: List<Task>) : super(context, 0, tasks) {
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        // Get the data item for this position
        val task = getItem(position)
        var view = convertView;
        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.task_list_item, parent, false);
        }

        // TODO: Lookup view for data population

        // TODO: Populate the data into the template view using the data object

        // TODO: Fill image view by Picasso or reset image

        // TODO: Handle CheckBox click

        // TODO: Handle row (TextView) click

        // Return the completed view to render on screen
        return view;
    }

    /*
    private fun launchDetailsActivity(task: Task, imageView: ImageView) {
        val options = ActivityOptions
                .makeSceneTransitionAnimation(
                        getContext() as Activity,
                        imageView,
                        getContext().getResources().getText(R.string.task_image).toString())
        // start the new activity
        val intent = Intent(getContext(), javaClass<AddTaskActivity>())
        intent.putExtra(AddTaskActivity.TASK, task)
        getContext().startActivity(intent, options.toBundle())
    }
    */
}