
# CsvSource Query


## 背景

程序从CSV格式的文件中读取数据,每行包含英文逗号分隔的两个整数,数据有上万行,数据可以看做foo(a int,b int);

csv 数据如下:
```

123,456
222,789
100,8989
...
10000,12121

```

实现以下sql

```

SELECT AVG(a),b from foo group by b;

```


结果输出到标准输出


## 环境

1. git		    2.x
2. jdk		    1.8.x
3. maven 	    3.x
4. spring-boot  1.5.x

## build 命令

mvn install 

## jar包使用

```
java -jar jar包名 param1 param2 

eg:
java -jar foo-1.0-SNAPSHOT.jar G:\fmatrix\foo\src\main\resources\foo.csv G:\fmatrix\result_a.txt
```


```
tip
param1:读取csv文件的路径
param1:查询结果写入的文件目录
```


## 类结构

```$xslt
com.feiyi.foo.entity.Column    | Column 实体类主要存储CSVTable的column
com.feiyi.foo.entity.Type      | 数据类型实体类, 该项目中只有 INTEGER 类型
com.feiyi.foo.entity.Foo       | 数据实体类,有int 类型的A,B两列
com.feiyi.foo.entity.CSVTable  | csv实体类 主要存储colum,csv文件路径,以及加载到内存中的数据
com.feiyi.foo.utils.FooUtils   | 工具类,有加载数据到内存的实现，查询的实现，将查询结果写入文件的实现
com.feiyi.foo.FooMain          | 程序入口,调用工具类中的一系列方法实现项目目标;传入两个参数,第一个是读取csv文件的路径,第二个是查询结果写入的文件目录
```

## 具体实现

```
1、接收传过来的输入和输出路径并赋值
2、构建表名为foo的CSVTable对象
3、给CSVTable对象赋值csv文件路径
4、读取CSV文件将数据加载到内存(构造List<Foo>,将每条数据读取出来封装成Foo存入List中)
5、执行查询
  5.1 构造 Map<Integer, List<Integer>>
  5.2 遍历 List<Foo> ,以columnB 为key,把相同key的column 放入 value(List<Integer>)中
  5.3 遍历 Map<Integer, List<Integer>> 为 value求平均值,并将结果存入Map<Integer, Integer>(columnB为key 平均值为value)
6、 遍历存着columnB和平均值的map 将数据写入文件中(首列为avgA,次列为B)
```

## 性能分析

```
(单位: ms, 测试数据 读取csv文件10000条,写入输出文件100条, 输出文件不存在)

构建表信息耗时	        4	5	3	5	3	4	4	3	4	4
加载数据到内存中耗时	26	26	36	28	29	28	29	32	28	33
查询耗时	                31	7	8	12	11	11	7	11	13	14
创建文件并写入耗时	35	28	32	42	31	35	31	29	35	52
总耗时	                96	66	79	87	74	78	71	75	80	103

```

```
(单位: ms, 测试数据 读取csv文件10000条,写入输出文件100条, 输出文件不存在)

        构建表信息耗时 加载数据到内存中耗时  查询耗时  创建文件并写入耗时    总耗时
最高值     5                  36             31           52             103
最低值     3                  26             7            28              66
平均值    3.9                29.5           12.5          35             80.9
```

由上述数据可见当资源足够的的时候主要限制是在IO读写上,读数据平均在 338条/ms,写数据平均在 3条/ms


``` 
上述数据来源

G:\fmatrix\foo\target>java -jar foo-1.0-SNAPSHOT.jar G:\fmatrix\foo\src\main\resources\foo.csv G:\fmatrix\result_a.txt
build table consume: 4ms
load data into memory consume : 26ms
query consume : 31ms
create file and write consume : 35ms
total time : 96ms

G:\fmatrix\foo\target>java -jar foo-1.0-SNAPSHOT.jar G:\fmatrix\foo\src\main\resources\foo.csv G:\fmatrix\result_a.txt
build table consume: 5ms
load data into memory consume : 26ms
query consume : 7ms
create file and write consume : 28ms
total time : 66ms

G:\fmatrix\foo\target>java -jar foo-1.0-SNAPSHOT.jar G:\fmatrix\foo\src\main\resources\foo.csv G:\fmatrix\result_a.txt
build table consume: 3ms
load data into memory consume : 36ms
query consume : 8ms
create file and write consume : 32ms
total time : 79ms

G:\fmatrix\foo\target>java -jar foo-1.0-SNAPSHOT.jar G:\fmatrix\foo\src\main\resources\foo.csv G:\fmatrix\result_a.txt
build table consume: 5ms
load data into memory consume : 28ms
query consume : 12ms
create file and write consume : 42ms
total time : 87ms

G:\fmatrix\foo\target>java -jar foo-1.0-SNAPSHOT.jar G:\fmatrix\foo\src\main\resources\foo.csv G:\fmatrix\result_a.txt
build table consume: 3ms
load data into memory consume : 29ms
query consume : 11ms
create file and write consume : 31ms
total time : 74ms

G:\fmatrix\foo\target>java -jar foo-1.0-SNAPSHOT.jar G:\fmatrix\foo\src\main\resources\foo.csv G:\fmatrix\result_a.txt
build table consume: 4ms
load data into memory consume : 28ms
query consume : 11ms
create file and write consume : 35ms
total time : 78ms

G:\fmatrix\foo\target>java -jar foo-1.0-SNAPSHOT.jar G:\fmatrix\foo\src\main\resources\foo.csv G:\fmatrix\result_a.txt
build table consume: 4ms
load data into memory consume : 29ms
query consume : 7ms
create file and write consume : 31ms
total time : 71ms

G:\fmatrix\foo\target>java -jar foo-1.0-SNAPSHOT.jar G:\fmatrix\foo\src\main\resources\foo.csv G:\fmatrix\result_a.txt
build table consume: 3ms
load data into memory consume : 32ms
query consume : 11ms
create file and write consume : 29ms
total time : 75ms

G:\fmatrix\foo\target>java -jar foo-1.0-SNAPSHOT.jar G:\fmatrix\foo\src\main\resources\foo.csv G:\fmatrix\result_a.txt
build table consume: 4ms
load data into memory consume : 28ms
query consume : 13ms
create file and write consume : 35ms
total time : 80ms

G:\fmatrix\foo\target>java -jar foo-1.0-SNAPSHOT.jar G:\fmatrix\foo\src\main\resources\foo.csv G:\fmatrix\result_a.txt
build table consume: 4ms
load data into memory consume : 33ms
query consume : 14ms
create file and write consume : 52ms
total time : 103ms
```

## 后续优化
```
1、该实现借鉴了数据库的思想，引入了Table,Column...等实体类,如果只要查询速度的话,可以将这些封装舍弃,简化结构直接从csv读取数据执行查询
2、可以增加并发,同步读取数据, 本地测试只用了10000条数据,如果目标数据量很大的话,单一读写取会很慢,资源得不到最有效的利用,可以增加并发, 提高效率
```