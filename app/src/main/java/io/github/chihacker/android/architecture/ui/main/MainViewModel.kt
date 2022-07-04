package io.github.chihacker.android.architecture.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.chihacker.android.architecture.common.exception.UserNotExistException
import io.github.chihacker.android.architecture.common.livedata.Event
import io.github.chihacker.android.architecture.domain.GetUserUseCase
import io.github.chihacker.android.architecture.ui.main.model.User

//viewModel 은 domain(usecase or interactor)을 활용하여 데이터를 가져오고 이를 Ui 모델에 맵핑합니다.
//ui 모델은 라이브데이터에 저장되며 구독하고 있는 view 에 notify 됩니다.
class MainViewModel(
    private val getUserUseCase: GetUserUseCase,
): ViewModel() {

    //ui 는 state 로
    private val _mainViewState = MutableLiveData<MainViewState>()
    val mainViewState: LiveData<MainViewState> = _mainViewState

    //일회성인 동작을 livedata 를 이용해서 동작시킬때는 Event 를 활용한다.
    private val _showToastEvent = MutableLiveData<Event<String>>()
    val showToastEvent: LiveData<Event<String>> = _showToastEvent

    //저는 주로 ViewModel 로 보내는 커멘드를 interaction 기준으로 정의합니다.
    //interaction 은 사용자가 앱과 상호작용하는 반응이 있고 (onClick, onScrollChanged 등)
    //프레임워크나 외부 모듈, 라이브러리 등에서 호출되는 경우가 있습니다. ( onCreate, initialize 등 )
    //이 경우 장점은 view 에서는 어떤 비지니스 로직이 구현되는지 몰라도 된다라는 점이 있습니다.
    //단점은 ui 가 추가 삭제되는 경우 비지니스 로직도 함께 추가, 삭제 시켜줘야하며 viewModel 이 길어질 수 있습니다.
    //다른 방법으로는 필요한 커멘드를 viewModel 에 정의하고 view 에서 로직을 수행시켜주는 방법입니다.
    //이 방법의 장점은 자주 사용되는 로직을 재활용할수 있습니다.
    //단점은 view 에서 본인이 사용할 로직을 알고 있어야 합니다.

    // ViewModel 로 보내는 커멘드를 interaction 기준으로 정의한 예
    fun onClickBtnUser() {

        val user = try {
            _mainViewState.value!!.getUser()
        } catch (e: UserNotExistException) {
            User("error", "error", 0, 0)
        }

        val dto = getUserUseCase.invoke(
            GetUserUseCase.Param(
                id = user.id
            )
        )

        _mainViewState.value = MainViewState.Content(
            User(
                dto.id,
                dto.name,
                dto.age,
                dto.kage
            )
        )
    }

    fun onClickBtnAge() {
        _showToastEvent.value = Event("click btn age")
    }

    // ViewModel 로 보내는 커멘드를 로직 기준으로 정의한 예
    fun showToast(msg: String) {
        _showToastEvent.value = Event(msg)
    }
}