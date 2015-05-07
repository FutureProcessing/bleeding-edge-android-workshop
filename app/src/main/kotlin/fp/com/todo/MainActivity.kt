package fp.com.todo

import android.app.ListActivity
import android.os.Bundle
import fp.com.todo.backend.Backend
import fp.com.todo.backend.MockedBackendService
import kotlinx.android.synthetic.activity_main.btn_add
import retrofit.MockRestAdapter
import retrofit.RestAdapter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import kotlin.properties.Delegates

public class MainActivity : ListActivity() {

    var backend: Backend by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_add.setAlpha(0f)
        val restAdapter = RestAdapter.Builder().setEndpoint("1.1.1.1").build()
        backend = MockRestAdapter.from(restAdapter).create(javaClass<Backend>(), MockedBackendService())

        backend.getTasks().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe() {
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
}
