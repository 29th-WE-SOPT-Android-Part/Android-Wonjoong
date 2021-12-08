# Week7
## 실행영상
온보딩 | 자동 로그인
:--: | :--:
<video src="https://user-images.githubusercontent.com/57510192/145130890-6643b64f-c116-4a5b-90a4-4298f62c59e8.mp4" width="300" /> | <video src="https://user-images.githubusercontent.com/57510192/145130914-e7634665-6d44-4810-85dc-c5ba0f97c462.mp4" width="300" />

## Overview
- 온보딩 화면 구현
- sharedPreferences 활용해서 자동로그인 구현
- NavigationComponent Backstack 관리
- Toolbar 연동
- RoomDB 활용한 자동로그인 구현

## 과제 Level1
### 온보딩 화면
<img src="https://user-images.githubusercontent.com/57510192/145131433-be7e9780-83b5-42ce-a215-a7195a33383f.PNG" width="600"><br>
각 버튼을 클릭했을 때, 다음 화면으로 넘어가도록 구현했습니다. 버튼 클릭 시 다음 화면으로 넘어가는 코드는 Directions를 활용했습니다.
```kotlin
findNavController().navigate(Onboarding1FragmentDirections.actionOnboarding1FragmentToOnboarding2Fragment())
```

### SharedPreferences 활용해서 자동로그인 구현
`autoLogin`은 자동 로그인이 설정되어있는지 여부에 관련된 코드이고, `OnBoardingState`는 온보딩 화면을 보여줘야 하는지 여부에 대한 코드입니다.
```kotlin
@Singleton
class SoptHubSharedPreference @Inject constructor(@ApplicationContext context: Context) {
    private val preference = PreferenceManager.getDefaultSharedPreferences(context)

    fun setAutoLogin(isOn: Boolean) {
        preference.edit().putBoolean(IS_AUTO_LOGIN, isOn).apply()
    }

    fun getAutoLogin(): Boolean {
        return preference.getBoolean(IS_AUTO_LOGIN, false)
    }

    fun setOnBoardingState(state: Boolean) {
        preference.edit().putBoolean(IS_ONBOARDING_DONE, state).apply()
    }

    fun getOnBoardingState(): Boolean {
        return preference.getBoolean(IS_ONBOARDING_DONE, false)
    }

    companion object {
        private const val IS_AUTO_LOGIN = "IS_AUTO_LOGIN"
        private const val IS_ONBOARDING_DONE = "IS_ONBOARDING_DONE"
    }
}
```

### Util 클래스 코드 및 패키징 방식
<img src="https://user-images.githubusercontent.com/57510192/145131886-e7eefc35-f762-4571-aa3d-1ca6c3fce27c.PNG" width="300"><br>
확장함수는 접미사로 Extensions를 붙이고, 상속받아 추가 구현하는 코드는 접미사로 Util을 붙였습니다. 이런것보다 클래스명이나 파일명을 보고 어떤 역할을 하는지 파악할 수 있도록 하기만 하면 되는 것 같습니다.
<img src="https://user-images.githubusercontent.com/57510192/145132181-832ea062-1f74-4cbc-8b5d-430ef543f2c7.PNG" width="300"><br>
데이터는 외부 모듈로 뺴주었습니다.

## 과제 Level2
### NavigationComponent Backstack 관리
```xml
<action
    android:id="@+id/action_onboarding3Fragment_to_onboarding1Fragment"
    app:destination="@id/onboarding1Fragment"
    app:popUpTo="@+id/onboarding1Fragment"
    app:popUpToInclusive="true" />
```
온보딩 세 번째 프래그먼트에서 첫 번째 프래그먼트로 이동하는 action을 추가한 후 해당 프래그먼트까지 쌓여있던 back stack을 제거하는 `popUpToInclusive` attribute를 추가했습니다.

### NavigationComponent Toolbar 연동
```xml
<fragment
    android:id="@+id/onboarding1Fragment"
    android:name="com.wonjoong.android.sopthub.ui.onboarding.Onboarding1Fragment"
    android:label="첫 번째 화면"
    tools:layout="@layout/fragment_onboarding_1">
    ...
</fragment>
```
```kotlin
val toolBarConfiguration = AppBarConfiguration(navController.graph)
binding.tbTitle.setupWithNavController(navController, toolBarConfiguration)
```
xml에서 라벨에 프래그먼트 이름을 추가하고, 위 코드로 toolbar와 연결하여 toolbar의 이름이 변경되도록 구현했습니다.

## 과제 Level3
### Room 활용한 자동 로그인
```kotlin
//DAO
@Dao
interface SoptDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAutoLoginData(autoLoginData: AutoLoginData)

    @Query("UPDATE AutoLoginData SET isAutoLoginState = :autoLoginData WHERE id = 1")
    suspend fun updateAutoLoginData(autoLoginData: Boolean)

    @Query("SELECT * FROM AutoLoginData WHERE id = 1")
    suspend fun getAutoLoginData(): AutoLoginData
}
```
```kotlin
//Database
@Database(entities = [AutoLoginData::class], version = 1)
abstract class SoptDatabase: RoomDatabase() {
    abstract fun soptDAO(): SoptDAO
}
```
```kotlin
//Repository
@Singleton
class SoptRepository @Inject constructor(
    private val soptDAO: SoptDAO
) : SoptDataSource {

    override suspend fun initAutoLoginData() = withContext(Dispatchers.IO) {
        soptDAO.insertAutoLoginData(AutoLoginData(false))
    }

    override suspend fun setAutoLogin(isOn: Boolean) = withContext(Dispatchers.IO) {
        soptDAO.updateAutoLoginData(isOn)
    }

    override suspend fun getAutoLogin(): Boolean = withContext(Dispatchers.IO) {
        return@withContext soptDAO.getAutoLoginData().isAutoLoginState
    }
}
```
테이블에 자동로그인 Boolean 값 하나를 두어 자동로그인 상태를 구현했습니다. DAO에서는 실질적으로 Table에 접근하는 쿼리문이 들어가고, Database에서는 엔티티를 활용해 테이블을 생성합니다. 마지막으로 Repository에서는 DAO를 통해 테이블에 접근하는 로직을 작성했습니다.
```kotlin
//SettingsViewModel.kt
private val _isSwitchChecked = MutableLiveData<Boolean>()
val isSwitchChecked: LiveData<Boolean> get() = _isSwitchChecked
fun setSwitchChecked() {
    ...
    _isSwitchChecked.value = !(_isSwitchChecked.value ?: return)
    viewModelScope.launch {
        repository.setAutoLogin(_isSwitchChecked.value ?: return@launch)
    }
}
```
xml에서 자동로그인 on/off가 switch로 구현되어 있는데, 사용자가 끄고 켤 때마다 `setSwitchChecked`가 호출되어 해당 값을 db에 저장해주고 있습니다.