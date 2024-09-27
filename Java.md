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

## MyBatisPlus

> MyBatisPlus是一款MyBatis增强工具,用于简化开发，提高效率.它在MyBatis的基础上只做增强不做改变,为简化而开发,并提高效率

### Demo

#### User表

```sql
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    id BIGINT NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);
```

```sql
DELETE FROM `user`;

INSERT INTO `user` (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```

#### Maven依赖

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-spring-boot3-starter</artifactId>
    <version>3.5.7</version>
</dependency>
```

#### Mapper配置

```Java
package com.example.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplusdemo.domian.User;

public interface UserMapper extends BaseMapper<User> {
}
```

#### User实体类

```java
package com.example.mybatisplusdemo.domian;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                 // Get Set 方法构造
@NoArgsConstructor    // 无参数构造
@AllArgsConstructor   // 全参数构造
@TableName("tb_user") // 指定表明
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String email;
}

```

#### application.yml

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/testdb?characterEncoding=utf-8&serverTimezone=UTC
    username: root
    password: root
    driver-class-name: com.mysql.jdbc.Driver
```

#### Test

```java
package com.example.mybatisplusdemo;

import com.example.mybatisplusdemo.domian.User;
import com.example.mybatisplusdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class MyBatisPlusDemoApplicationTests {


    @Autowired(required = false)
    private UserMapper userMapper;

    @Test
    public void testQueryList(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testSelect(){
        System.out.println("------ Select Method Test -----");
        List<User> users = userMapper.selectList(null);
        Assert.isTrue(5 == users.size(),"");
        users.forEach(System.out::println);
    }

}

```

### 全局设置表名前缀

```yml
mybatis-plus:
	global-config:
		db-config:
			table-prefix: tb_
```

主键生成策略

```java
 @Test
    public void testInsert(){
        User user = new User();
        user.setName("PiD");
        user.setAge(19);
        user.setEmail("test9@baomidou.com");
        int userId = userMapper.insert(user); // 数据库内userId : 雪花算法生成
        System.out.println(userId);
    }
```

#### @TableId注解

> @TableId(type = IdType.AUTO)
>
> - AUTO 数据库自增长,依赖于数据库.该类型需要数据ID设置自增长，否则error
> - NONE 雪花算法
> - INPUT 手动设置ID,否则为Null
> - ASSIGN_ID 当没有手动设置主键,自动填充(雪花算法)
> - ASSIGN_UUID 当实体类主键为null，才会自动填充,使用UUID

:roll_eyes:全局设置

```yml
mybatis-plus:
	global-config:
		db-config:
			id-type: auto
```

### 驼峰映射

```yml
mybatis-plus:
	configuration:
		map-underscore-to-camel-case: false 
		# 是否开启驼峰映射
```

### 打印MP日志

```yml
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

### 基本操作

```java
package com.example.mybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.example.mybatisplusdemo.domian.User;
import com.example.mybatisplusdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@SpringBootTest
class MyBatisPlusDemoApplicationTests {


    @Autowired(required = false)
    private UserMapper userMapper;

    @Test
    public void testQueryList(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testSelect(){
        System.out.println("------ Select Method Test -----");
        List<User> users = userMapper.selectList(null);
        Assert.isTrue(5 == users.size(),"");
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("PiD");
        user.setAge(19);
        user.setEmail("test9@baomidou.com");
        int userId = userMapper.insert(user); // userId : 雪花算法生成
        System.out.println(userId);
    }


    @Test
    public void testDelete(){
        List<Integer> ids = new ArrayList<>();
        ids.add(3);
        ids.add(4);
        int result = userMapper.deleteByIds(ids);
        System.out.println(result);
    }

    @Test
    public void testDeleteMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("name","PiD");
        int i = userMapper.deleteByMap(map);
        System.out.println(i);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(2L);
        user.setAge(29);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    public void testWrapper(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("age",18);
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void testQuery(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("id","name");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testClassMapper(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(new User());
        userQueryWrapper.select(new Predicate<TableFieldInfo>() {
            @Override
            public boolean test(TableFieldInfo tableFieldInfo) {
                return !"email".equals(tableFieldInfo.getColumn());
            }
        });
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testUpdateWrapper(){
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.gt("id",1);
        userUpdateWrapper.set("age",80);
        int update = userMapper.update(userUpdateWrapper);
        System.out.println(update);

    }

}

```

### LambdaWrapper

```java
    @Test
    public void testLambdaWrapper(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.gt(User::getAge,18);
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
//        users.forEach(System.out::println);
        System.out.println(users);
    }
```

### 自定义方法

#### Order表

```sql
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` int(11) DEFAULT NULL COMMENT '价格',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT '1' COMMENT '版本',
  `del_flag` int(1) DEFAULT '0' COMMENT '逻辑删除标识,0-未删除,1-已删除',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`id`,`price`,`remark`,`user_id`,`update_time`,`create_time`,`version`,`del_flag`,`create_by`,`update_by`) values (1,2000,'无',2,'2021-08-24 21:02:43','2021-08-24 21:02:46',1,0,NULL,NULL),(2,3000,'无',3,'2021-08-24 21:03:32','2021-08-24 21:03:35',1,0,NULL,NULL),(3,4000,'无',2,'2021-08-24 21:03:39','2021-08-24 21:03:41',1,0,NULL,NULL);

```



