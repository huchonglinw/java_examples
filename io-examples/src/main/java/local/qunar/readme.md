# qunar入职题目  

题目考察点：
1、注意编码规范，参考阿里巴巴 Java 手册
2、熟练使用 JDK 和 guava 的 collection、IO、network 等模块
3、了解使用 SpringMVC 或 SpringBoot 框架，并尝试结合 mybatis 使用
4、熟悉 linux 命令和 http 请求原理
5、考虑代码可读性和扩展性
6、思考面向接口编程
7、尝试 guava 和 java8 语法
8、了解 Java 网络编程

## 一、日志分析
从本题对应的附件中找到 access.log 文件，并编程根据文件内容统计以下数据：
1. 请求总量；
2. 请求最频繁的 10 个 HTTP 接口，及其相应的请求数量；
3. POST 和 GET 请求量分别为多少；
4. URI 格式均为 /AAA/BBB 或者 /AAA/BBB/CCC 格式，按 AAA 分类，输出各个类别
   下 URI 都有哪些。
   本题推荐使用 Java 语言和 Guava 库。
   
## 二、有效代码行数统计
   从本题对应的附件中找到 StringUtils.java 文件，将其复制到工程的 classpath 下，编程
   统计附件中 的 StringUtils.java 文件的有效代码行数（一个数字）到一个新文 件
   validLineCount.txt 中。请注意，
1) 有效不包括空行、注释；
2) 考虑代码里有多行注释的情况；
3) 不用考虑代码和注释混合在一行的情况。
   
## 三、文本解密
   从本题对应的附件中找到 sdxl_prop.txt 和 sdxl_template.txt。根据 sdxl_prop.txt 中内
   容替换掉 sdxl_template.txt 里$function(index)形式文字，将其还原成一本完整小说，写
   到文件 sdxl.txt 中，输出在 classpath 下。
   其中 function 有 4 种类型，替换规则如下：
1) natureOrder 自然排序，即文本中排列顺序
2) indexOrder 索引排序，文本中每行第一个数字为索引
3) charOrder 文本排序，java 的字符排序
4) charOrderDESC 文本倒序，java 的字符倒序
   
## 四、Java 模拟 Linux 命令处理和管道
   请使用 Java 语言实现一个基本的 shell 模拟器。
   linux 下有很多对文本进行操作的命令，比如 cat filename 可以将文件的所有内容输出到
   控制台上。grep keyword filename 可以将文件内容中包含 keyword 的行内容输出到控
   制台上。wc -l filename 可以统计 filename 文件中的行数。
   |是代表管道的意思，管道左边命令的结果作为管道右边命令的输入，比如 cat filename |
   grep exception | wc -l，可以用来统计一个文件中 exception 出现的行数。
   请实现一个功能，可以解析一个 linux 命令（只包含上面三个命令和上面提到的参数以及和
   管道一起完成的组合，其它情况不考虑），并将结果输出到控制台上，比如下面这几个例子：
   cat xx.txt
   cat xx.txt | grep xml
   wc -l xx.txt
   cat xx.txt | grep xml | wc -l
   请注意程序对于未来加入其他命令的可扩展性和对于大规模输入的内存开销。
   
## 五、Java 网络编程
   使用 java socket 编程实现一 server 和 client，client 通过命令行读取用户输入的网址，将
   其传输给 server 端，server 通过 http 获取数据，统计总字符数(包括标点符号)、汉字数、
   英文字符数、标点符号数，返回给 client，并输出。使用 network.nio 加分。