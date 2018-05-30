
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
Logs.init(BuildConfig.DEBUG, "log-demo")
```
## 配置

gradle

```
implementation "com.sqq.xiaqu:logs:1.0.0"
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
