package fp.com.todo.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fp.com.todo.BuildConfig;
import fp.com.todo.backend.Backend;
import fp.com.todo.backend.BackendService;
import fp.com.todo.backend.MockedBackendService;
import retrofit.MockRestAdapter;
import retrofit.RestAdapter;

@Module
public class BackendModule {
    private static final String BACKEND_URL = "http://10.57.149.40:10080";

    @Provides
    @Singleton
    public Backend provideBackend(RestAdapter restAdapter) {
        if(BuildConfig.DEBUG) {
            return MockRestAdapter.from(restAdapter).create(Backend.class, new MockedBackendService());
        }

        return new BackendService(restAdapter.create(Backend.class));
    }

    @Provides
    @Singleton
    public RestAdapter provideRestAdapter() {
        return new RestAdapter.Builder().setEndpoint(BACKEND_URL).build();
    }
}
