package fp.com.todo;

import android.app.Application;

public abstract class BaseApplication extends Application{
    protected ApplicationComponent provideApplicationComponent(){
        return DaggerApplicationComponent.builder().build();
    }
}
