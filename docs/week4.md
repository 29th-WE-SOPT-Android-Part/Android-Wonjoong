# Week4
동영상 길이가 길어서 Github으로 안올라가지는 것 같습니다!
그래서 [유튜브 링크](https://youtu.be/jzVyWdOIH50) 로 대체합니다!

## Overview
- 서버 통신 구현
- OkHttp 활용
- suspend 함수 활용
- 힐트 주입

## 과제 Level1
### PostMan 로그인 캡처
![로그인](https://user-images.githubusercontent.com/57510192/141303434-4b450ed4-c347-4fcb-830b-688c2d8b5405.PNG)
### PostMan 회원가입 캡처
![회원가입](https://user-images.githubusercontent.com/57510192/141303440-8ed161a5-5220-4802-8c18-98a08a742582.PNG)
### Sopt API 구현 코드
```kotlin
data class SoptApiResponse(  
    val responseData: ResponseData,  
 val data: SoptApiResponseData  
) {  
    data class SoptApiResponseData(  
        val id: Int,  
		val name: String,  
		val email: String  
    )  
}  
  
data class SignUpRequestData(  
    val email: String,  
	val name: String,  
	val password: String  
)  
  
data class SignInRequestData(  
    val email: String,  
	val password: String  
)  
  
interface SoptApi {  
    @POST("user/signup")  
    suspend fun signUp(  
        @Body body: SignUpRequestData  
    ): SoptApiResponse  
  
    @POST("user/login")  
    suspend fun signIn(  
        @Body body: SignInRequestData  
    ): SoptApiResponse  
}
```
## 과제 Level 2
### Github API 구현 코드
아래의 Github API 코드를 통해 Github에서 사용자 정보와 팔로워 수를 받아 화면에 띄워주었습니다.
```kotlin
data class GithubFollowerResponse(  
    @SerializedName("login")  
    val id: String,  
	@SerializedName("avatar_url")  
    val profileImageUrl: String  
)  
  
data class GithubUserFollowerResponse(  
    val followers: Int,  
	val following: Int  
)  
  
interface GithubApi {  
    @GET("users/WonJoongLee/followers")  
    suspend fun getFollowers(): List<GithubFollowerResponse>  
  
    @GET("users/{userId}")  
    suspend fun getUserFollowers(  
        @Path("userId") userId: String,  
	): GithubUserFollowerResponse  
  
    @GET("users/{userId}/repos")  
    suspend fun getRepositories(  
        @Path("userId") userId: String,  
	): GithubUserFollowerResponse  
}
```
### OkHttp로 Header 넣어주기
`headerInterceptor`로 헤더를 생성하고, `provideOkHttpClient()`에서 헤더를 넣어주었습니다.
그 후 `Retorift` 빌더의 `client()`에서 http client를 넣었습니다.
```kotlin
private val headerInterceptor = Interceptor {  
  val request = it.request()  
        .newBuilder()  
        .addHeader("Content-Type", "application/json")  
        .build()  
    return@Interceptor it.proceed(request)  
}  
  
private fun provideOkHttpClient() = OkHttpClient.Builder()  
    .addInterceptor(headerInterceptor)  
    .build()  
  
private val gson = GsonBuilder()  
    .setLenient()  
    .create()  
  
private fun provideRetrofit(baseUrl: String) =  
    Retrofit.Builder()  
        .baseUrl(baseUrl)  
        .client(provideOkHttpClient())  
        .addConverterFactory(GsonConverterFactory.create(gson))  
        .build()
```
### Wrapper 클래스 작성
응답의 경우 같은 형식으로 오기 때문에 `ResponseData`라는 Wrapper 클래스를 생성하였습니다.
```kotlin
data class ResponseData(  
    val status: Int,  
	val success: Boolean,  
	val message: String,  
)
```
그 후 아래와 같이 `response`라는 property로 활용했습니다.
```kotlin
data class SoptApiResponse(  
    val responseData: ResponseData,  
	val data: SoptApiResponseData  
)
```
## Level3 심화과제
### suspend 비동기 처리
Callback을 대신하여 `suspend`를 이용했습니다. 아래와 같이 suspend function으로 구현하고 Call 클래스를 빼줬습니다.
```kotlin
@POST("user/signup")  
suspend fun signUp(  
    @Body body: SignUpRequestData  
): SoptApiResponse
```
위와 같이 함수를 선언한 후 IO 쓰레드에서 해당 함수를 호출했습니다.
```kotlin
viewModelScope.launch(Dispatchers.IO) {  
  kotlin.runCatching {  
  soptApi.signUp(SignUpRequestData(email, name, password))  
    }.onSuccess {  
  _isRegisterSuccess.postValue(true)  
    }.onFailure {  
  _isRegisterSuccess.postValue(false)  
    }  
}
```
## 기타
### Hilt 추가
`NetworkModule`에서 통신하는 부분을 주입했습니다.
```kotlin
@Provides  
@Singleton  
fun provideSoptApi(): SoptApi =  
    provideRetrofit(SOPT_BASE_URL).create(SoptApi::class.java)  
  
@Provides  
@Singleton  
fun provideGithubApi(): GithubApi =  
    provideRetrofit(GITHUB_BASE_URL).create(GithubApi::class.java)
```
## 4주차 이원중 패치 내역 v4.0.0
- OkHttp를 이용해 헤더를 주입하는 법을 배웠습니다.
- suspend 내용을 익힐 수 있었습니다.
- Hilt로 주입하는 법을 학습했습니다.
