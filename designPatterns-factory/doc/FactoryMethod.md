# java设计模式——工厂方法模式

标签（空格分隔）： java 设计模式

---
[TOC]
##前言
应用场景： 在上篇的[简单工厂模式][1]中， 我们使用了一个工厂类来创建产品。我们对其做一下修改， 不再设计一个报文工厂类来负责所有产品的创建了， 而是将具体产品的创建过程交给专门的工厂子类来完成。我们预先定义一个抽象工厂类， 然后利用具体的工厂类来分别生成产品（报文种类1， 报文种类2， ...)。 这样做的好处显而易见， 如果出现新类型的报文， 只需要为这种新类型创建一个具体的工厂类即可， 符合了开闭原则。

##工厂方法模式的定义
**工厂方法(Factory Method)模式**的意义是定义一个创建产品对象的工厂接口，将实际创建工作推迟到子类当中。核心工厂类不再负责产品的创建，这样核心类成为一个抽象工厂角色，仅负责具体工厂子类必须实现的接口，这样进一步抽象化的好处是使得工厂方法模式可以使系统在不修改具体工厂角色的情况下引进新的产品。
它包含了如下角色：

- **抽象产品（Product）**
- **具体产品（ConcreteProduct）**
- **抽象工厂（Factory）**
- **具体工厂（ConcreteFactory）**
![此处输入图片的描述][2]
##工厂方法模式的应用

### 场景代码示例
**抽象产品类**：
```java
public abstract class AnswerHead {
    protected String request_type;                      
    protected String agent_code;                        
    protected String trn_code;                          
    protected String front_traceno; 
    public abstarct String toXML();
}
```
**具体产品类**
```java
    /** 产品类1， 只有应答报文头 **/
public class AnswerHeadOnly extends AnswerHead {
    public String toXML() {
        System.out.println("AnswerHeadOnly to xml");
        return null;
    }
}
```
```java
/**产品类2， 业务1应答报文体 **/
public class AnswerBody_Mobile extends AnswerHead {
    public String toXML() {
        System.out.println(" AnswerBody_Mobile to xml");
        return null;
    }
}
```
**抽象工厂接口**
```java
public interface ProductFactory {
	public AnswerHead createProduct();
}
```
**具体工厂类**
```java 
public class AnswerBody_Mobile_Factory implements ProductFactory {
	public AnswerHead createProduct() {
		return new AnswerBody_Mobile();
	}
}
```
**测试类**
```java
public static void main(String[] args) {
		ProductFactory factory = new AnswerBody_Mobile_Factory();
		AnswerHead am =  factory.createProduct();
		am.toXML();
	}
```
运用了工厂方法模式， 我们之前的`if else`被不同类型的具体工厂类所取代， 这样在增加新的产品的时候不用修改原来的代码， 增加了可维护性。 当然这样做的后果就是项目中多了很多的类和接口, 增加了系统复杂性。

###工厂方法模式在JDBC API中的应用
JDBC是JDK提供的标准化数据接口， 各大数据库厂商都实现了JDBC API， JDBC是使用工厂方法模式的呢， 我们关注1个类和3个接口，

- **`java.sql.Driver`**: 每个数据库厂商都要实现这个接口, 用来注册驱动，打开数据库连接

- **`java.sql.Connection`**: 每个数据库厂商都要实现这个接口， 用来执行数据的各种操作
- **`java.sql.DriverMangement`**: jdk 提供的管理Driver的类
我们引用网上的一个类图：
![此处输入图片的描述][3]
    - 工厂方法模式就是提供一个抽象的工厂，一个抽象的产品，在上述当中相当于Driver（数据库连接工厂）和Connection（抽象产品），实现的一方需要提供一个具体的工厂类（比如mysql驱动）和一个具体的产品（比如mysql数据库连接）。

    - 客户端调用时不依赖于具体工厂和产品（即到底是mysql驱动，mysql数据库连接还是oracle驱动，oracle连接我们只管使用抽象的driver和connection），而是依赖于抽象工厂和抽象产品完成工作。
    
    - 当然这里对driver的使用变成了对DriverManager的使用， 具体JDBC是如何利用DriverManagement加载驱动的，请看我的另一篇文章。[JDBC Driver 加载过程分析][4]

###工厂方法模式日志记录中的应用

一般系统中的日志记录器可以使用工厂方法模式来设计， 如下图：
![此处输入图片的描述][5]

##总结
- 工厂方法模式属于类创建型模式。在工厂方法模式中，工厂父类负责定义创建产品对象的公共接口，而工厂子类则负责生成具体的产品对象，这样做的目的是将产品类的实例化操作延迟到工厂子类中完成，即通过工厂子类来确定究竟应该实例化哪一个具体产品类。

- 工厂方法模式是简单工厂模式的进一步抽象和推广。由于使用了面向对象的多态性，工厂方法模式保持了简单工厂模式的优点，而且克服了它的缺点。在工厂方法模式中，核心的工厂类不再负责所有产品的创建，而是将具体创建工作交给子类去做。这个核心类仅仅负责给出具体工厂必须实现的接口，而不负责产品类被实例化这种细节，这使得工厂方法模式可以允许系统在不修改工厂角色的情况下引进新产品

- 工厂方法模式的主要优点是增加新的产品类时无须修改现有系统，并封装了产品对象的创建细节，系统具有良好的灵活性和可扩展性；其缺点在于增加新产品的同时需要增加新的工厂，导致系统类的个数成对增加，在一定程度上增加了系统的复杂性

---
##附：静态工厂方法
静态工厂方法不是设计模式， 它是我们常用的一些编码技巧， **Effective java**第一条就介绍了它的用法， 这里记录一下。

来看一个JDK中的例子：
```java
public final class Boolean implements java.io.Serializable,
        Comparable<Boolean> {
    //这里缓存了两个对象
    public static final Boolean TRUE = new Boolean(true);
    public static final Boolean FALSE = new Boolean(false);
        
    public static Boolean valueOf(boolean b) {
        return (b ? TRUE : FALSE);
    }
}
```
这里`public static Boolean valueOf` 就是一个静态工厂方法， 它的作用是用来产生一个**Boolean**对象

### 静态工厂方法的优势
- **具有更加可读的方法名**：在使用构造函数去构造对象的时候，我们传递不同的参数构造不同类型的对象。如果不看文档的话，我们很难记住传递什么参数能够构造什么样子的对象。用静态的工厂方法就不一样啦，我们可以不同的工厂方法不同名字，我们就可以很容易的记住什么方法名可以构造什么样的对象。

- **不必在每次调用它们的时候都创建一个新对象**：我们调用静态工厂方法返回的可能是缓存的一个对象，而不是一个新的对象。这可以减少创建新的对象，从来提高性能，前面提到的 Boolean 就是一个例子。
```java
public static Integer valueOf(int i) {
    assert IntegerCache.high >= 127;
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}
```
上面的代码是Integer的valueOf方法， 可以看出它做了一个cache缓存。这个方法将总是缓存 -128 到 127的值， 每次从缓存中获取对象， 而不是重新**new**一个

- **返回原返回类型的任何子类型的对象**
这个特性让静态工厂的可扩展性大大的优于构造函数。

- **静态方法还可以简化参数化类型的对象创建**: 在调用参数化类的构造器的时候， 即使类型很明显也必须指明。
```java
Map<String, List<String>> m = new HashMap<String, List<String>>();
```
以上代码非常复杂丑陋， 静态工厂方法帮我们解决：
```java
public static <K, V> HashMap<K, V> newInstance() {
    return new HashMap<K, V>();
}
```

###静态工厂方法的缺陷
- 如果我们在一个类中将构造函数设为private，只提供静态工厂方法来创建对象，那么我们就不能通过继承的方式来扩展该类。不过还好的是，在需要进行扩展的时候，我们现在一般提倡用组合而不是继承。

- 静态工厂方法很难与其他静态方法区分开。为了和普通的静态方法区分， 静态构造方法在命名上需要遵守一些规则：
 - **valueOf**返回和参数一样的对象， 通常用于**类型转换**
 - **getInstance**根据参数返回对应的对象， 该对象可能是缓存在对象池中的对象。 对于单例（**Singleton**）， 我们使用无参的**getInstance**, 并且总返回一个对象。
 - **newInstance**与**getInstance**基本一样， 只不过每次生成一个新对象。
 - **getType**: 与**getInstance**一样， 区别是这个方法返回的对象是另外一个不同的类
 - **newType**: 与**getType**类似， 不过每次返回一个新对象。

**参考文献：**
[工厂方法模式][6]
[工厂方法模式(Factory Method Pattern)][7]
[Effective 中的静态工厂方法][8]


  [1]: https://www.zybuluo.com/pastqing/note/128050
  [2]: http://design-patterns.readthedocs.org/zh_CN/latest/_images/FactoryMethod.jpg
  [3]: http://img.blog.csdn.net/20130626181440203?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvenVveGlhb2xvbmc4ODEw/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center
  [4]: https://www.zybuluo.com/pastqing/note/107544
  [5]: http://design-patterns.readthedocs.org/zh_CN/latest/_images/loger.jpg
  [6]: http://blog.csdn.net/zuoxiaolong8810/article/details/9067775
  [7]: http://design-patterns.readthedocs.org/zh_CN/latest/creational_patterns/factory_method.html
  [8]: http://hellojinjie.com/2014/04/03/effective-java%EF%BC%9A%E4%BD%BF%E7%94%A8%E9%9D%99%E6%80%81%E5%B7%A5%E5%8E%82%E6%96%B9%E6%B3%95/