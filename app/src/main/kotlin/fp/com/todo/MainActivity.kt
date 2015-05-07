package fp.com.todo

import android.app.ListActivity
import android.os.Bundle
import kotlinx.android.synthetic.activity_main.btn_add

public class MainActivity : ListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_add.setAlpha(0f)
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
