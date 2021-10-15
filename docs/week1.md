# week1
## Overview
- 프로젝트 초기화
- 회원가입, 로그인, 홈 구현

## 구동영상
https://user-images.githubusercontent.com/57510192/136332929-ced47930-4049-4708-aea8-22f1b6396a18.mp4

## 문서 작성 과제
### Level2 도전과제 2-2
1. **명시적 인텐트**
   명시적 인텐트 : 인텐트에 클래스 객체나 컴포넌트 이름을 지정하여 호출할 대상을 확실히 알 수 있는 경우에 사용한다. 주로 애플리케이션 내부에서 사용한다. 명시적 인텐트는 특정 컴포넌트나 액티비티가 명확하게 실행되어야 하는 경우 사용된다.
2. **암시적 인텐트**
   암시적 인텐트 : 인텐트의 액션과 데이터를 지정하긴 했지만, 호출할 대상이 달라질 수 있는 경우에 암시적 인텐트를 사용한다. `SoptHub` 프로젝트에서도 마찬가지로 Github 아이콘을 클릭했을 때 웹페이지로 연결되는데, 이 때 크롬을 통해서 접속할 수도 있고 다른 브라우저로 접속할 수도 있다. 이렇게 암시적 인텐트에서는 안드로이드 시스템이 인텐트를 이용해 요청한 정보를 처리할 수 있는 적절한 컴포넌트를 찾아본 다음 사용자에게 그 대상과 처리 결과를 보여주는 과정을 거친다. 이미 기존에 어떤 기능들을 지원하는 앱들이 있는 경우 암시적 인텐트를 사용해서 그 앱들을 사용하면 되는 것이다.
### Level3 심화과제 3-1: ViewBinding, DataBinding
**ViewBinding**은 view와 상호작용하는 코드를 더욱 쉽게 작성할 수 있도록 도와준다. 특히 매번 `findViewById`를 통해 xml의 layout을 고치는 것은 쉽지 않다. 그래서 **viewBinding**을 통해 바인딩 클래스 인스턴스로  layout에 ID가 있는 모든 뷰에 대해 보다 편하게 직접적으로 참조하여 사용한다.
**DataBinding**은 ViewBinding과 비슷하지만 몇가지의 차이점이 존재한다.
- 데이터 바인딩은 `<layout>` 태글르 사용하여 만든 레이아웃만 처리한다.
- 내부적으로 데이터 바인딩 클래슬르 생성할 때 루트뷰에 tag를 상비하는데 뷰바인딩은 그런 작업이 없다.
- 뷰바인딩은 데이터바인딩보다 어노테이션 프로세싱의 일부를 사용하기 때문에 더 빠르게 바인딩 클래스를 생성한다.
  ([이 블로그](https://charlezz.medium.com/view-binding-%EC%82%B4%ED%8E%B4%EB%B3%B4%EA%B8%B0-df3526d909a7)를 참고했습니다.)

그래서 때에 따라서 databinding과 viewbinding을 잘 나눠서 쓰면 된다.
### Level3 심화과제 3-2: setOnClickListener를 람다식으로 사용할 수 있는 이유
`setOnClickListener`와 같은 람다를 밖으로 빼내서 사용할 수 있는 이유는 **코틀린에서는 함수 호출 시 맨 뒤에 있는 인자가 람다식이라면 그 람다를 괄호 밖으로 빼낼 수 있기 때문**이다.
간단한 예시로 `maxBy`라는 함수를 확인해보면,
```kotlin
people.maxBy({ p: Person -> p.age })
```
람다가 유일하며 제일 마지막에 있기 때문에 밖으로 빼낸다.
```kotlin
people.maxBy(){ p: Person -> p.age }
```
괄호는 생략 가능하므로 생략해준다.
```kotlin
people.maxBy { p: Person -> p.age }
```
`people`로 파라미터의 타입추론도 가능하므로 파라미터도 뺄 수 있다.
```kotlin
people.maxBy { p.age }
```

## 기타
### buildSrc 모듈 분리
- 추후 네트워크, 데이터 모듈을 다루기 위해 `buildSrc`모듈을 따로 뺐습니다.

### build.gradle.kts로 변경
- `build.gradle`을 `build.gradle.kts`로 변경했습니다.

### customview 처리
- 반복되는 뷰(`EditTextWithTitle`)는 커스텀뷰로 바꿨습니다.

### BaseViewUtil로 binding 연결
- BaseView를 만들어 `AppCompatActivity()`대신 BaseView를 상속받아 구현하도록 했습니다.