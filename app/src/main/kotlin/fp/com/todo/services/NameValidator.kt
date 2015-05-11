package fp.com.todo.services

import rx.Observable

public trait NameValidator {
    fun isValid(name: String): Observable<Boolean>
}
