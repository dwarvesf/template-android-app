package {{packageName}}.viewmodel

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import {{packageName}}.data.local.user.UserLocalDataSource
import {{packageName}}.repository.UserRepository
import {{packageName}}.service.UserService
import {{packageName}}.utils.Constant
import {{packageName}}.view.list.ListViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.locks.ReentrantLock


@RunWith(AndroidJUnit4::class)
class ListViewModelTest {

    private lateinit var listViewModel: ListViewModel
    @Before
    fun setUp() {
        listViewModel = ListViewModel(InstrumentationRegistry.getTargetContext(),
                UserRepository.getInstance(UserService.getInstance().api, UserLocalDataSource.getInstance(InstrumentationRegistry.getTargetContext())!!))
    }

    @Test
    fun doLogin() {
        val lock = ReentrantLock()
        val condition = lock.newCondition()

        lock.lock()
        try {

            listViewModel.getListData(Constant.LIMIT, 0, false)


            listViewModel.outputs.onResultsReceived.subscribe {
                condition.signal()
            }
            condition.await()

        } finally {
            lock.unlock()
        }


    }


}
