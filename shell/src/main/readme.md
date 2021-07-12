# shell  

## Java 模拟 Linux 命令处理和管道  

### 项目背景  
linux 下有很多对文本进行操作的命令，比如 cat filename 可以将文件的所有内容输出到
控制台上。grep keyword filename 可以将文件内容中包含 keyword 的行内容输出到控
制台上。wc -l filename 可以统计 filename 文件中的行数。
|是代表管道的意思，管道左边命令的结果作为管道右边命令的输入，比如 cat filename | 
grep exception | wc -l，可以用来统计一个文件中 exception 出现的行数。
请实现一个功能，可以解析一个 linux 命令（只包含上面三个命令和上面提到的参数以及和
管道一起完成的组合，其它情况不考虑），并将结果输出到控制台上，比如下面这几个例子：
1. cat xx.txt
2. cat xx.txt | grep xml
3. wc -l xx.txt
4. cat xx.txt | grep xml | wc -l