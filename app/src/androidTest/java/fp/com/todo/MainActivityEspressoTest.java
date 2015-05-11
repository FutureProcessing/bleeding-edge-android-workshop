package fp.com.todo;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickingOnAddNewTaskButton_FlowsToProperActivity() {
        onView(withId(R.id.btn_add)).perform(click());

        onView(withId(R.id.btn_add_image)).check(matches(isDisplayed()));
    }
}
