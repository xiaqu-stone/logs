
# Logs


## 简介
基于`android.util.Log`做了一层封装，使得如下：

1. 针对`json`格式化输出
2. 设置APP的全局Log标签
3. 输出Logs代码调用处：所在线程、所在文件、所在方法、所在行数

## json效果输出

```
╔═══════════════════════════════════════════════════════════════════════════════════════
║ [ main: (MainActivity.kt:25) testLog ]
║ {
║     "employees": [
║         {
║             "firstName": "Bill",
║             "lastName": "Gates"
║         },
║         {
║             "firstName": "George",
║             "lastName": "Bush"
║         },
║         {
║             "firstName": "Thomas",
║             "lastName": "Carter"
║         }
║     ]
║ }
╚═══════════════════════════════════════════════════════════════════════════════════════
```
## 使用

程序开始处调用初始化开关与全局TAG即可
```
Logs.init(Logs.DEBUG, "log-demo")
```

## 配置

gradle

```
implementation "com.sqq.xiaqu:logs:1.0.3"
```
maven 
```
<dependency>
  <groupId>com.sqq.xiaqu</groupId>
  <artifactId>logs</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

## 更新日志

- `v1.0.1` log开关的控制方式，更改为等级控制，使得release包也可以输出一些关键信息。
- `v1.0.2` 添加默认全局tag，避免未调用init时，因tag为空字符串，导致log未被输出
- `v1.0.3` 修改json中的url输出等级