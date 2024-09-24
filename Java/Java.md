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



