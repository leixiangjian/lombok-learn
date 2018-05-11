# lombok-learn<br>
git:https://github.com/rzwitserloot/lombok<br>
此工具主要为加速开发效率提供很多的注解，编译时会生产编译后的代码<br>

以下为引用内容:<br>
此组件针对不同的IDE需要进行jar包的引用<br>
例如Eclipse:<br>
1. 下载lombok.jar包<br>
https://projectlombok.org/download.html<br>
2. 运行lombok.jar<br>
java -jar D:\work\evn\lombok.jar 数秒后将弹出一框，以确认eclipse的安装路径<br>
3. 确认eclipse安装路径<br>
确认完eclipse的安装路径后，点击install/update按钮，即可安装完成<br>
4. 安装结束检查<br>
安装完成之后，确认eclipse安装路径下是否多了一个lombok.jar包，并且其配置文件eclipse.ini中是否添加了如下内容:<br>
-javaagent:lombok.jar <br>
-Xbootclasspath/a:lombok.jar <br>
5. 重启eclipse<br>
如果工程中使用了maven并且maven中引用的lombok版本号与eclipse根目录下的jar包不完全一致，则有可能eclipse中的setXx()代码会报编译不通过的错误，实际上eclipse代码自动提示是有的并且maven编译也可以通过。<br>
6. 解决问题<br>
在eclipse.ini中修改配置如下：<br>
-javaagent:lombok-1.16.20.jar<br>
-Xbootclasspath/a:lombok-1.16.20.jar<br>

注解介绍

  下面只是介绍了几个常用的注解，更多的请参见https://projectlombok.org/features/index.html。

@Getter / @Setter

  可以作用在类上和属性上，放在类上，会对所有的非静态(non-static)属性生成Getter/Setter方法，放在属性上，会对该属性生成Getter/Setter方法。并可以指定Getter/Setter方法的访问级别。

@EqualsAndHashCode

  默认情况下，会使用所有非瞬态(non-transient)和非静态(non-static)字段来生成equals和hascode方法，也可以指定具体使用哪些属性。

@ToString

  生成toString方法，默认情况下，会输出类名、所有属性，属性会按照顺序输出，以逗号分割。

@NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor

  无参构造器、部分参数构造器、全参构造器，当我们需要重载多个构造器的时候，Lombok就无能为力了。

@Data

  @ToString, @EqualsAndHashCode, 所有属性的@Getter, 所有non-final属性的@Setter和@RequiredArgsConstructor的组合，通常情况下，我们使用这个注解就足够了。

Lombok原理

  了解了简单的使用之后，现在应该比较好奇它是如何实现的。整个使用的过程中，只需要使用注解而已，不需要做其它额外的工作，那玄妙之处应该是在注解的解析上。JDK5引入了注解的同时，也提供了两种解析方式。

运行时解析

  运行时能够解析的注解，必须将@Retention设置为RUNTIME，这样可以通过反射拿到该注解。java.lang.reflect反射包中提供了一个接口AnnotatedElement，该接口定义了获取注解信息的几个方法，Class、Constructor、Field、Method、Package等都实现了该接口，大部分开发者应该都很熟悉这种解析方式。

boolean isAnnotationPresent(Class<? extends Annotation> annotationClass);
<T extends Annotation> T getAnnotation(Class<T> annotationClass);
Annotation[] getAnnotations();
Annotation[] getDeclaredAnnotations();
1
2
3
4
编译时解析

编译时解析有两种机制，网上很多文章都把它俩搞混了，分别简单描述一下。

Annotation Processing Tool

  apt自JDK5产生，JDK7已标记为过期，不推荐使用，JDK8中已彻底删除，自JDK6开始，可以使用Pluggable Annotation Processing API来替换它，apt被替换主要有2点原因：

api都在com.sun.mirror非标准包下
没有集成到javac中，需要额外运行
  apt的更多介绍可以参见这里。

Pluggable Annotation Processing API

  JSR 269，自JDK6加入，作为apt的替代方案，它解决了apt的两个问题，javac在执行的时候会调用实现了该API的程序，这样我们就可以对编译器做一些增强，这时javac执行的过程如下： 
这里写图片描述 
  Lombok就是使用这种方式实现的，有兴趣的话可以去看看其Lombok源码，对应注解的实现都在HandleXXX中，比如@Getter注解的实现是HandleGetter.handle()。还有一些其它类库使用这种方式实现，比如Google Auto、Dagger等等。

Lombok问题

无法支持多种参数构造器的重载
奇淫巧技，使用会有争议
