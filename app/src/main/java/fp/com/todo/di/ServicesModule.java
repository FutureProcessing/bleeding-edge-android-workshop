package fp.com.todo.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fp.com.todo.services.ByExternalServiceNameValidator;
import fp.com.todo.services.NameValidator;

@Module
public class ServicesModule {

    @Provides
    @Singleton
    public NameValidator provideNameValidator(ByExternalServiceNameValidator nameValidator) {
        return nameValidator;
    }
}
