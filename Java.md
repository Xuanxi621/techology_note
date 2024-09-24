# Java

> Java SE : Java平台标准版,是其他平台版本的基础
>
> Java ME (淘汰) : 嵌入式设备(手机平板) 开发
>
> Java EE : 后台服务器程序开发;程序(对数据进行处理)

:rocket:跨平台

> Java源文件 -> Java字节码文件 -> Jvm虚拟机 -> 操作系统

:newspaper_roll:DOS命令

> 清屏 cls

## Java开发环境安装

## 基本概念

- JVM: Jvm(Java Virtual Machine),Java虚拟机,Java程序需要在Jvm上执行
- JRE : JRE(Java Runtime Environment),Java运行环境,包含Jvm和Java的核心类库(可供我们直接使用)
- JDK : JDK(Java Development Kit)称为Java开发工具,包含JRE和开发工具

>JDK包含JRE|JRE包含JVM

## HelloWorld

```java
public class HelloWorld{
    public static void main(String[] args){
        System.out.println("Hello World");
    }
}
```

```shell
javac HelloWorld.java 编译文件生成class文件
java HelloWorld 执行Java程序
```

:robot: 小驼峰命名 : 变量名、方法名  setAge

:robot:大驼峰命名 : 类名 FileUploadController

## 栈与堆

> 栈：方法调用的时候会进入栈
>
> 栈：方法中的局部变量存储在栈中(用来存储堆中的地址值)
>
> 堆：new出来的东西都存储在堆中(开辟内存空间)

## IDEA技巧

> arr.fori 遍历数组
>
> Shift双击查找类
>
> Alt+7 查看该类的方法

## 面向对象

- 封装

- 继承

  > Java中类只能单继承,只能有一个父类,但是可以多层继承
  >
  > 需要符合逻辑

- 多态

  >同一个数据类型的不同对象对同一种行为会有多种不同的实现

>overload:同一个类中，方法名相同，形参不同
>
>override:对继承的方法进行重写
>
>this:代表本类
>
>super:代表父类

