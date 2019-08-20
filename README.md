# RxApplication（模块名称）
## Description（模块简单的描述）
## OverView（使用效果展示）
## Introduction(详细介绍)
http module use to send message

## Feature (使用特点)
* easy to use
* 2
* 3

## Download
加载方法
```
implementation 'me.jessyan:progressmanager:1.5.0'

```

## Usage (使用方法)
### Step 1
``` java
 // When building OkHttpClient, the OkHttpClient.Builder() is passed to the with() method to initialize the configuration
 OkHttpClient = ProgressManager.getInstance().with(new OkHttpClient.Builder())
                .build();
```

### Step 2

## ProGuard （代码混淆）
```
 -keep class me.jessyan.progressmanager.** { *; }
 -keep interface me.jessyan.progressmanager.** { *; }
```

## About Me
* **Email**: <jess.yan.effort@gmail.com>
* **Home**: <http://jessyan.me>
* **掘金**: <https://juejin.im/user/57a9dbd9165abd0061714613>
* **简书**: <https://www.jianshu.com/u/1d0c0bc634db>


## License
```
 Copyright 2017, jessyan

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
