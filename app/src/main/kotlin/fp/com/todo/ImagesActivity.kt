package fp.com.todo

import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import fp.com.todo.backend.Backend
import kotlinx.android.synthetic.activity_images.activity_images_swipe_refresh_layout
import kotlinx.android.synthetic.activity_images.rv_images
import me.tatarka.rxloader.RxLoader
import me.tatarka.rxloader.RxLoaderManager
import me.tatarka.rxloader.RxLoaderObserver
import org.jetbrains.anko.toast
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
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
        prepareRxLoaderWithSwipeToRefresh()

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
        activity_images_swipe_refresh_layout.setOnRefreshListener({ rxLoader.restart() })
        rxLoader.start()
    }

    private fun createRxLoader(): RxLoader<List<String>> {
        return RxLoaderManager.get(this).create(
                backend.getImagesUrls()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .finallyDo({
                            activity_images_swipe_refresh_layout
                                    .setRefreshing(false)
                        }),
                createRxLoaderObserver())
    }

    private fun createRxLoaderObserver(): RxLoaderObserver<List<String>> {
        return object : RxLoaderObserver<List<String>>() {
            override fun onStarted() {
                // post is due to some bug or strange change in API 21 (regarding underlying layout) - without progress
                // won't show at initial load (but is this really important?)
                activity_images_swipe_refresh_layout.post { activity_images_swipe_refresh_layout.setRefreshing(true) }
            }

            override fun onNext(imageUrls: List<String>) {
                imagesAdapter = ImagesAdapter(imageUrls)
                rv_images.setAdapter(imagesAdapter)
            }

            override fun onError(error: Throwable) {
                toast("Unable to download image URLs! ${error.getMessage()}")
                Timber.e(error, "Unable to download image URLs")
            }
        }
    }
}
