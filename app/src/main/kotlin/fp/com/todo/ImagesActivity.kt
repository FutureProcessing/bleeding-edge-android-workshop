package fp.com.todo

import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import fp.com.todo.backend.Backend
import kotlinx.android.synthetic.activity_images.rv_images
import me.tatarka.rxloader.RxLoader
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject
import kotlin.properties.Delegates

public class ImagesActivity : ActionBarActivity() {
    var imagesAdapter: ImagesAdapter by Delegates.notNull()

    var backend: Backend by Delegates.notNull()
        [Inject] set

    override fun onCreate(savedInstanceState: Bundle?) {
        super<ActionBarActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)
        TodoApplication.graph.inject(this)
        prepareRecyclerView()
        //        prepareRxLoaderWithSwipeToRefresh()

        backend.getImagesUrls().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe() {
            rv_images.setAdapter(ImagesAdapter(it))
        }
    }

    private fun prepareRecyclerView() {
        // TODO: uncomment and change number of columns in different orientation
        rv_images.setLayoutManager(GridLayoutManager(this, getResources().getInteger(R.integer.number_of_image_grid_columns)))
        rv_images.setItemAnimator(DefaultItemAnimator())
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
