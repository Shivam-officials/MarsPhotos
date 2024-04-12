package com.example.marsphotos.rules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
/**
 * Test rule to manage the coroutine dispatcher for unit tests.
 */
class TestDispatcherRule(

    /**
     * The UnconfinedTestDispatcher class inherits from the
     * TestDispatcher class and it specifies that tasks must not be executed in any particular order.
     * use the StandardTestDispatcher class enables full control over
     * coroutine execution for complicated tests that require a manual approach,
     */
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() { /** TestWatcher is JUnit class that enables you to take actions on different execution phases of a test.(like starting and finishing). */

    /**
     * Before a test starts, sets the Dispatchers.Main dispatcher to the testDispatcher property.
     * This ensures coroutines launched with Dispatchers.Main run on the test dispatcher.
     */
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    /**
     * After a test finishes, resets the Dispatchers.Main dispatcher to its default behavior.
     */
    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
