# Week3
## Overview
- 피그마 적용
- Fragment, Viewpager 활용
- Glide 적용
- 이미지 uri 적용

## 구현방법

### selector 적용
Edittext에 focus가 되면 drawable이 변경되도록 구현했습니다.
```xml
android:background="@drawable/selector_register"
```
selector_register.xml
```xml
<selector xmlns:android="http://schemas.android.com/apk/res/android">  
   <item android:drawable="@drawable/shape_round_edit_text" android:state_focused="true" />  
   <item android:drawable="@drawable/shape_unselected_register_round_edit_text" android:state_focused="false" />  
</selector>
```

### 리스트에 여러 이미지 넣기
아래와 같이 세 종류의 이미지를 만들어줍니다.
```kotlin
val imageSrc = when (num % 3) {  
    0 -> "https://github.com/WonJoongLee.png"  
    1 -> "https://github.com/torvalds.png"  
    else -> "https://github.com/mdb1217.png"  
}  
githubProfileList.add(  
    GithubData(  
        "문다빈$num",  
		"안드로이드의 어머니$num",  
		imageSrc,  
		true  
	)  
)
```
이미지를 바인딩 해주는 부분에서 해당 이미지 url을 넣어줍니다.
```kotlin
Glide.with(binding.ivGithubProfileImage)  
    .load(githubData.imageSrc)  
    .circleCrop()  
    .into(binding.ivGithubProfileImage)
```

### DataBinding 활용한 리사이클러뷰 아이템
아래와 같이 xml에 data variable을 생성합니다.
```xml
<variable  
  name="data"  
  type="kr.wonjoong.data.model.GithubData" />
```
그 후 각각 view에 바인딩 해주면 어댑터에서 코드를 줄일 수 있습니다.
```xml
<TextView  
  android:id="@+id/tv_name"  
  android:layout_width="0dp"  
  android:layout_height="wrap_content"  
  android:layout_marginHorizontal="8dp"  
  android:ellipsize="end"  
  android:text="@{data.name}"  
  android:textColor="@color/black"  
  android:textSize="20sp"  
  android:textStyle="bold"  
  app:layout_constraintBottom_toTopOf="@id/tv_description"  
  app:layout_constraintEnd_toEndOf="parent"  
  app:layout_constraintStart_toEndOf="@id/iv_github_profile_image"  
  app:layout_constraintTop_toTopOf="@id/iv_github_profile_image"  
  app:layout_constraintVertical_chainStyle="packed"  
  tools:text="문다빈" />  
  
<TextView  
  android:id="@+id/tv_description"  
  android:layout_width="0dp"  
  android:layout_height="wrap_content"  
  android:ellipsize="end"  
  android:maxLines="2"  
  android:text="@{data.description}"  
  app:layout_constraintBottom_toBottomOf="@id/iv_github_profile_image"  
  app:layout_constraintEnd_toEndOf="parent"  
  app:layout_constraintStart_toStartOf="@id/tv_name"  
  app:layout_constraintTop_toBottomOf="@id/tv_name"  
  tools:text="안드로이드 파트장" />
```

### 파일 접근 권한 요청
`registerForActivityResult`를 통해 intent 반환 값을 적용했습니다.
```kotlin
getImageResult = registerForActivityResult(  
    ActivityResultContracts.StartActivityForResult()  
) { result ->  
  if (result.resultCode == RESULT_OK) {  
		    val uri = result.data?.data  
			Glide  
            .with(binding.ivGalleryImage)  
            .load(uri)  
            .into(binding.ivGalleryImage)  
    }  
}
```
파일 접근 권한이 없다면 요청했습니다.
```kotlin
if (ContextCompat.checkSelfPermission(  
        requireContext(),  
  android.Manifest.permission.READ_EXTERNAL_STORAGE  
  ) != PackageManager.PERMISSION_GRANTED  
) {  
    ActivityCompat.requestPermissions(  
        requireActivity(),  
		arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),  
		1  
  )  
}
```