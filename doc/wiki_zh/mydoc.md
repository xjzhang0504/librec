### random
为了保证每次生成的结果是可以复现的, 随机数字的初始值通过配置项`rec.ramdom.seed`来设置
示例配置如下:

```
rec.random.seed=1
```
java示例代码如下

```java
conf.set("rec.random.seed","1");
```