package fp.com.todo.services;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import fp.com.todo.backend.Backend;
import rx.Observable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class ByExternalServiceNameValidatorTest {

    private ByExternalServiceNameValidator nameValidator;
    @Mock
    private Backend mockedBackend;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        nameValidator = new ByExternalServiceNameValidator(mockedBackend);
    }

    @Test
    public void emptyName_isInvalid() {
        // given
        String name = "";
        setBackendToValidateNameAs(name, true);

        // when
        Observable<Boolean> isValid = nameValidator.isValid(name);

        // then
        assertThat(isValid.toBlocking().first()).isFalse();
    }

    @Test
    public void nameStartingWithLowerCase_isInvalid() {
        // given
        String name = "name";
        setBackendToValidateNameAs(name, true);

        // when
        Observable<Boolean> isValid = nameValidator.isValid(name);

        // then
        assertThat(isValid.toBlocking().first()).isFalse();
    }

    @Test
    public void nameStartsWithUpperCase_and_ExternalServiceSaysItIsInvalid_isInvalid() {
        // given
        String name = "Name";
        setBackendToValidateNameAs(name, false);

        // when
        Observable<Boolean> isValid = nameValidator.isValid(name);

        // then
        assertThat(isValid.toBlocking().first()).isFalse();
    }

    @Test
    public void nameStartsWithUpperCase_and_ExternalServiceSaysItIsValid_isValid() {
        // given
        String name = "Name";
        setBackendToValidateNameAs(name, true);

        // when
        Observable<Boolean> isValid = nameValidator.isValid(name);

        // then
        assertThat(isValid.toBlocking().first()).isTrue();
    }

    private void setBackendToValidateNameAs(String name, boolean validationResult) {
        given(mockedBackend.validateName(name)).willReturn(Observable.just(validationResult));
    }
}