package fp.com.todo.di;

import dagger.Module;

@Module
public class BackendModule {
    private static final String BACKEND_URL = "http://10.57.149.89:10080";

    //            return MockRestAdapter.from(restAdapter).create(Backend.class, new MockedBackendService());
}
