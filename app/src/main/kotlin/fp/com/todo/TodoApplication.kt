package fp.com.todo

import timber.log.Timber
import kotlin.properties.Delegates

public class TodoApplication : BaseApplication() {

    companion object {
        var graph: ApplicationComponent by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        graph = provideApplicationComponent()
        Timber.plant(Timber.DebugTree())
    }
}