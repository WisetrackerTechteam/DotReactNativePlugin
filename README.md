### 1. 리액트 플러그인 설치 (AOS/IOS 공통 설정)

#### 1.1 플러그인 다운로드
-> 터미널에서 아래의 명령어를 통해 리액트 플러그인을 프로젝트에 다운로드 합니다.

```shell
$ npm install --save git+https://github.com/WisetrackerTechteam/RW-react-package.git
```

#### 1.2 플러그인 설치 확인

-> 프로젝트에 플러그인이 정상 다운로드되면 다음과 같이 플러그인 파일이 추가된 것을 확인할 수 있습니다.

![]()

### 2. Android 리액트 플러그인 설정

#### 2.1 Http 통신 허용 설정 
-> 프로젝트의 **Target API 28 이상**일 경우 Http 통신 허용을 설정해 주세요.

```xml
<!-- AndroidManifest.xml -->
<application
	android:icon="@mipmap/ic_launcher"
	android:label="@string/app_name"
	android:networkSecurityConfig="@xml/network_security_config"
	android:theme="@style/AppTheme">
```

```xml
<!-- app/res/xml/network_security_config.xml -->
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">collector.naver.wisetracker.co.kr</domain>
        <domain includeSubdomains="true">report.wisetracker.co.kr</domain>
    </domain-config>
</network-security-config>
```

#### 2.2 string.xml 설정

#### a) dotAuthorizationKey 설정
-> 발급받은 App Analytics Key 정보 추가

```xml
<!-- 예시는 샘플 코드이며, 관리자 페이지에서 직접 발급 받은 값을 적용해 주세요  -->
<string-array name="dotAuthorizationKey">
    <item name="usdMode">1</item> // 1. DOT.DOX 2. DOT
    <item name="domain">http://collector.naver.wisetracker.co.kr</item> // DOT END POINT
    <item name="domain_x">http://collector.naver.wisetracker.co.kr</item> // DOX END POINT
    <item name="serviceNumber">103</item>
    <item name="expireDate">14</item>
    <item name="isDebug">false</item>
    <item name="isInstallRetention">true</item>
    <item name="isFingerPrint">true</item>
    <item name="accessToken">access_token_string</item>
</string-array>
```
#### b) customKeyList 설정 (필요시 설정)
-> '#' 구분자 기준으로 왼쪽은 기본 사용되고 있는 키 값 오른쪽은 변경하고자 하는 키 값을 적용해주세요.

```xml
<!-- 예시는 디폴트 advtId 키 값을 advt_id 값으로 변경하는 설정입니다. -->
<string-array name="customKeyList">
  <item name="custom_key_value1">advtId#advt_id</item>
</string-array>
```

#### 2.3 AndroidManifest.xml 설정 
-> /react-plugin/android/src/main/AndroidManifest.xml)

#### a) 인스톨 레퍼러 활성화 여부 (필요시 설정)

```xml
<!-- true 변경시 Wisetracker 통한 인스톨 레퍼러 미수신 -->
<meta-data 
    android:name="disableDotReceiver"
    android:value="false" />
```

#### b) 딥링크 설정
-> 딥링크로 진입할 android:scheme="YOUR_SCHEME" 스키마와 android:host="YOUR_HOST" 호스트를 설정해 주세요.

```xml
<!-- 예시는 wisetracker://wisetracker.co.kr 링크로 진입시 딥링크 분석이 가능하며, 사용될 값을 직접 수정해 주세요. -->
<activity android:name="kr.co.wisetracker.tracker.DeepLinkActivity" 
          android:launchMode="singleTop" >
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
	<!-- 딥링크로 진입될 스키마와 호스트 정보 입력 -->
        <data android:host="wisetracker.co.kr"
              android:scheme="wisetracker" />
    </intent-filter>
</activity>
```

### 3. IOS 리액트 플러그인 설정

XCode 프로젝트의 info.plist 파일에 제공받은 App Analytics Key 정보를 추가합니다
info.plist 파일을 open할때 **list로 보기** 가 아니라 **source로 보기**를 선탁하신뒤, 제공받으신 Key를 **Ctrl+V** 하시면 됩니다
제공받은 Key값은 아래의 예시와 같이 xml 형태를 가지고 있는 데이터 입니다

```xml
<key>dotAuthorizationKey</key>
<dict>
<<<<<<< HEAD
    <key>accessToken</key>
    <string></string>
    <key>domain</key>
    <string>http://stg-app-wcs.naver.com</string>
    <key>expireDate</key>
    <string>14</string>
    <key>isDebug</key>
    <string>true</string>
    <key>isFingerPrint</key>
    <string>true</string>
    <key>isInstallRetention</key>
    <string>true</string>
    <key>serviceNumber</key>
    <string>102</string>
    <key>useMode</key>
    <integer>1</integer>
=======
	<key>domain</key>
	<string>http://collector.naver.wisetracker.co.kr</string>
	<key>serviceNumber</key>
	<string>103</string>
	<key>expireDate</key>
	<string>14</string>
	<key>isDebug</key>
	<string>true</string>
	<key>isInstallRetention</key>
	<string>true</string>
	<key>isFingerPrint</key>
	<string>true</string>
	<key>accessToken</key>
	<string></string>
	<key>useMode</key>
	<string>1</string>
>>>>>>> 799960d245e85c83f01fec538843b5e1b98db8d9
</dict>
```

위의 Key 중에 isDebug는 collector 로 전송되는 분석 데이터를 Xcode 에서 확인할 수 있게 해주는 boolean 변수로 사용되고 있습니다
SDK를 적용해야 하는 앱 개발자는 이 값을 true로 설정하고, SDK의 기본 적용 및 추가 분석 코드 적용 과정에서 사용할 수 있습니다
( 예를 들면 이 값을 true로 적용하여, 분석 대상이 되는 앱에 적용하여 build를 하면 Android 의 경우 logcat에서, iOS 의 경우 debug console에서 collector로 전송되는 plain text 형태의 로그 확인이 가능합니다. )
분석 코드 적용이 완료되고, 스토어 배포시에는 해당 필드를 false로 변경하여 배포하는 것을 권장합니다.


http통신을 허용하기 위해 NSAppTransportSecurity 를 아래와 같이 추가합니다


```xml
<key>NSAppTransportSecurity</key>
<dict>
	<key>NSAllowsArbitraryLoads</key>
	<true/>
</dict>
```

### 4. 플러그인 초기화
리액트 프로젝트에서 앱 시작시 최초 로드되는 .js 파일에 아래 코드를 추가합니다.

```javascript
import DotReactBridge from 'DotReactNativeBridge/wrapper/react/DotReactBridge.js';
import Dop from 'DotReactNativeBridge/dop-native-sdk-inf.js'
```
