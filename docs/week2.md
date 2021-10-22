# Week2
## Overview
- 팔로워 목록, 레포지토리 목록 프래그먼트 이용해서 구현
- 버튼 클릭시 프래그먼트 전환
- Grid Layout으로 리사이클러뷰 구현
- 리사이클러뷰 온클릭 구현
- 리사이클러뷰 ItemDecoration 추가
- 리사이클러뷰 아이템 삭제 및 되돌리기 구현
- 리사이클러뷰 Diffutil로 개선

## 구동영상
|프래그먼트 전환|아이템 클릭|아이템 삭제|
|:---:|:---:|:---:|
| https://user-images.githubusercontent.com/57510192/138455351-2feb8d17-bae0-4c53-ac9f-c8136c01dd91.mp4 | https://user-images.githubusercontent.com/57510192/138455291-d6bc6e5e-8901-47aa-94db-ece120dbef83.mp4 | https://user-images.githubusercontent.com/57510192/138455295-b2160fe0-5c83-4d66-b616-2e1d5fd590d1.mp4 |

## 구현방법

### 프래그먼트 전환
```kotlin
private fun observeSelectedFragmentValue() {
    viewModel.selectedFragment.observe(this) { newSelectedFragmentType ->
        when (newSelectedFragmentType) {
            GithubFragmentType.Follower -> setFragmentWith(followerFragment)
            GithubFragmentType.Repository -> setFragmentWith(repositoryFragment)
        }
    }
}

private fun setFragmentWith(fragment: GithubFragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.fragment_container_github, fragment)
        .commit()
}
```
팔로워 목록, 레포지토리 목록 클릭 시 화면 전환을 위해 beginTransaction을 사용했습니다. 또한 화면이 교체되어야 하므로 `replace()`를 이용해 프래그먼트를 교체했습니다.

### 리사이클러뷰
**1. DiffUtil 사용**
```kotlin
val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GithubData>() {
    override fun areItemsTheSame(oldItem: GithubData, newItem: GithubData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GithubData, newItem: GithubData): Boolean {
        return oldItem == newItem
    }
}
```
DiffUtil을 이용해 아이템 변화 시 리사이클러뷰의 리스트가 업데이트 되었을 때 효율적으로 리스트를 업데이트해준다. DiffUtil을 이용한다면 아이템이 바뀌기 전의 리스트와 아이템이 바뀐 후의 리스트 비교를 Background Thread에서 처리하기 때문에 용이하다.
**2. 삭제 되돌리기**
제일 마지막에 저장한 아이템을 메모리에 들고 있어 사용자의 실수를 막을 수 있다.
```kotlin
private lateinit var cachedItem: Pair<Int, GithubData>
```
Pair에는 각각 Item의 Position과 데이터가 들어있다.
**3. 하나의 어댑터로 처리**
두 아이템이 비슷한 뷰를 갖고 있었기 때문에 하나의 어댑터와 아이템으로 처리했다. 모델에서 `isImageVisible`로 두 뷰를 나누었다.
```kotlin
data class GithubData(
    val name: String,
    val description: String,
    val isImageVisible: Boolean = false,
)
```
`false`라면 xml에서 `android:visibility="@{data.imageVisible ? View.VISIBLE : View.GONE }"`로 사진이 나오지 않도록 해준다.
코드의 양은 줄었지만 유지보수하기 어렵다는 점에서 좋지만은 않은 것 같다.

### 프래그먼트 보일러 플레이트 코드 제거
Fragment가 생성될 때마다 필요한 코드를 제거했다. 아래의 BaseFragment를 상속받아 반복되는 보일러 플레이트 코드를 줄일 수 있었다.
```kotlin
abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes val layoutRes: Int) : Fragment() {

    private var _binding: T? = null
    protected val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
```