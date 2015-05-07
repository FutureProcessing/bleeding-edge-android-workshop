package fp.com.todo

import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import me.tatarka.rxloader.RxLoader
import kotlin.properties.Delegates

public class ImagesActivity : ActionBarActivity() {
    var imagesAdapter: ImagesAdapter by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super<ActionBarActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)
        prepareRecyclerView()
        prepareRxLoaderWithSwipeToRefresh()
    }

    private fun prepareRecyclerView() {
        // TODO: uncomment and change number of columns in different orientation
        //        rv_images.setLayoutManager(GridLayoutManager(this, 1))
        //        rv_images.setItemAnimator(DefaultItemAnimator())
    }

    private fun prepareRxLoaderWithSwipeToRefresh() {
        val rxLoader = createRxLoader()
        // TODO: Handle SwipeToRefresh with RxLoader
        rxLoader.start()
    }

    private fun createRxLoader(): RxLoader<List<String>> {
        throw UnsupportedOperationException()
    }
}
