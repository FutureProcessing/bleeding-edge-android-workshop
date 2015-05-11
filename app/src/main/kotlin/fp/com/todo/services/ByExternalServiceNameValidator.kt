package fp.com.todo.services

import fp.com.todo.backend.Backend
import rx.Observable
import javax.inject.Inject

class ByExternalServiceNameValidator [Inject](val backend: Backend) : NameValidator {

    override fun isValid(name: String): Observable<Boolean> {
        if (name.isEmpty() || name.first().isLowerCase()) {
            return Observable.just(false);
        }
        return backend.validateName(name)
    }
}

