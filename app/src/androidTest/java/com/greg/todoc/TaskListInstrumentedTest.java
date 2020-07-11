package com.greg.todoc;


import android.widget.DatePicker;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.rule.ActivityTestRule;

//import com.greg.todoc.di.DI;
import com.greg.todoc.model.Task;
//import com.greg.todoc.service.TaskApiService;
import com.greg.todoc.ui.task_list.TaskListActivity;
import com.greg.todoc.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.greg.todoc.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskListInstrumentedTest{

    //rivate static int ITEMS_COUNT = 5;
    //rivate TaskListActivity mTaskListActivity;
    ///private TaskApiService mApiService;
    //rivate List<Task> mTask;

    //Rule
    //ublic ActivityTestRule<TaskListActivity> mActivityTestRule = new ActivityTestRule<>(TaskListActivity.class);

    //Before
    //ublic void setUp(){
    //   mTaskListActivity = mActivityTestRule.getActivity();
    //   assertThat(mTaskListActivity, notNullValue());
    //   mApiService = DI.getTaskApiService();
    //

    //Test
    //ublic void TestATaskList_shouldNotBeEmpty(){
    //   onView(withId(R.id.task_recycler_view))
    //           .check(ViewAssertions.matches(hasMinimumChildCount(1)));
    //

    //Test
    //ublic void TestBTaskList_deleteAction_shouldRemoveOneItem(){
    //   onView(withId(R.id.task_recycler_view))
    //           .check(withItemCount(ITEMS_COUNT))
    //           .perform(actionOnItemAtPosition(0, new DeleteViewAction()))
    //           .check(withItemCount(ITEMS_COUNT-1));
    //

    //Test
    //ublic void TestCAddTask_and_checkIf_ListContainsOneMoreItem(){
    //   onView(withId(R.id.task_recycler_view))
    //          .check(withItemCount(ITEMS_COUNT-1));
    //   onView(withId(R.id.add_btn))
    //           .perform(click());
    //   onView(withId(R.id.addTaskEt))
    //           .perform(typeText("Manger du sable"));
    //   onView(withId(R.id.projectSpinner))
    //           .perform(click());
    //   onData(allOf(is(instanceOf(String.class))))
    //           .atPosition(2)
    //           .inRoot(RootMatchers.isPlatformPopup())
    //           .perform(click());
    //   onView(withId(R.id.addDialog))
    //           .perform(click());
    //   onView(withId(R.id.task_recycler_view))
    //           .check(withItemCount(ITEMS_COUNT));
    //

    //@Test
    //public void TestDCheckTask_byDate_isDisplayed(){
    //    onView(withId(R.id.main_menu))
    //            .perform(click());
    //    onView(withText("Par dates"))
    //            .perform(click());
    //    onView(withId(R.id.dialogStartDateEdit))
    //            .perform(click());
    //    onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
    //            .perform(PickerActions.setDate(2020, 6, 4));
    //    onView(withId(android.R.id.button1))
    //            .perform(click());
    //    onView(withId(R.id.dialogEndDateEdit))
    //            .perform(doubleClick());
    //    onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
    //            .perform(PickerActions.setDate(2020, 6, 14));
    //    onView(withId(android.R.id.button1))
    //            .perform(click());
    //    onView(withId(R.id.okDateDialog))
    //            .perform(click());
    //    onView(withId(R.id.task_recycler_view))
    //            .check(ViewAssertions.matches(isDisplayed()));
    //}

    //  @Test
    //  public void TestECheckTask_byProject_isDisplayed(){
    //      onView(withId(R.id.main_menu))
    //              .perform(click());
    //      onView(withText("Par projet"))
    //              .perform(click());
    //      onView(withId(R.id.projectDialogSpinner))
    //              .perform(click());
    //      onData(allOf(is(instanceOf(String.class))))
    //              .atPosition(2)
    //              .inRoot(RootMatchers.isPlatformPopup())
    //              .perform(click());
    //      onView(withId(R.id.okProjectDialog))
    //              .perform(click());
    //      onView(withId(R.id.task_recycler_view))
    //              .check(ViewAssertions.matches(isDisplayed()));
    //  }

    //  @Test
    //  public void TestFCheckFullTask_byMenu_isDisplayed(){
    //      onView(withId(R.id.main_menu))
    //              .perform(click());
    //      onView(withText("Toutes"))
    //              .perform(click());
    //      onView(withId(R.id.task_recycler_view))
    //              .check(ViewAssertions.matches(isDisplayed()));
    //  }

    //  @Test
    //  public void TestGCheckError_isDisplayed_ifEndDate_lowerThan_StartDate(){
    //      onView(withId(R.id.main_menu))
    //              .perform(click());
    //      onView(withText("Par dates"))
    //              .perform(click());
    //      onView(withId(R.id.dialogStartDateEdit))
    //              .perform(click());
    //      onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
    //              .perform(PickerActions.setDate(2020, 6, 9));
    //      onView(withId(android.R.id.button1))
    //              .perform(click());
    //      onView(withId(R.id.dialogEndDateEdit))
    //              .perform(doubleClick());
    //      onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
    //              .perform(PickerActions.setDate(2020, 6, 2));
    //      onView(withId(android.R.id.button1))
    //              .perform(click());
    //      onView(withId(R.id.okDateDialog))
    //              .perform(click());
    //      onView(withId(R.id.dialogEndDateEdit))
    //              .check(ViewAssertions.matches(hasErrorText("Date de fin incorrecte")));
    //  }

    //@Test
    //public void TestHCheckBackgroundImageIsDisplayed_ifList_isEmpty(){
    //    multiDelete();
    //    onView(allOf(withId(R.id.noTaskMsg), withText("Tu n’as aucune tâche à traiter"), isDisplayed()));
    //     onView(withText("Tu n’as aucune tâche à traiter"))
    //             .check(ViewAssertions.matches(isDisplayed()));
    //     onView(withId(R.id.noTaskLogo))
    //            .check(ViewAssertions.matches(isDisplayed()));
    // }

    //private void multiDelete(){
    //    for (int i = 0; i < 5; i++) //mTask.size() null pointer .lenght() introuvable
    //    {
    //        onView(withId(R.id.task_recycler_view))
    //                .perform(actionOnItemAtPosition(0, new DeleteViewAction()));
    //    }
    //}
}
