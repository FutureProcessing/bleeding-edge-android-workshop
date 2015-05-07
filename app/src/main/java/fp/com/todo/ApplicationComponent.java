package fp.com.todo;

import javax.inject.Singleton;

import dagger.Component;
import fp.com.todo.di.BackendModule;

@Singleton
@Component(modules = BackendModule.class)
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);

    void inject(ImagesActivity imagesActivity);

    void inject(AddTaskActivity addTaskActivity);
}
