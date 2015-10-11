# java设计模式——单例模式

标签（空格分隔）： java 设计模式

---
[TOC]
    
##单例模式的使用动机
某些系统中的某些类的对象**只需要一个**， 或者只能是一个， 如果多于一个甚至会出现错误，这时候我们就要用到单例模式。日志对象， 打印池对象， 序列ID生成器等应用。单例模式可以让系统在减少内存空间的情况下仍然能正常工作。


## 单例模式的几种方式
###懒汉式，线程不安全
先说一下何谓**懒汉**，何谓**饿汉**。所谓**懒汉**就是在你要用到这个单例对象的时候才会去建立这个对象， 而**饿汉**就是无论用不用，都在一开始就建立了这个单例对象。下面是一个典型的懒汉式单例的写法：
```java
public class LazyNotSafe {
    private static LazyNotSafe singleton;
    private LazyNotSafe() {}
    public static LazyNotSafe getInstance() {
        if( singleton == null ) {  
            singleton = new LazyNotSafe();
        }
        return singleton;
    }
}
```
上面代码很常规， 运用了懒汉式加载， 但是存在的问题是， 在多线程的环境下调用`getInstance`时就会出现问题，会创建多个实例。原因是线程A在进入`if`语句块执行创建动作还未结束时， 线程B也进入`if`语句， 这样就会导致错误的发生。

###懒汉式， 线程安全
要解决以上问题， 让线程安全起来， 最简单的方法就是使用`synchronized`, 将`getInstance` 声明成一个同步方法即可。
```java
public class LazySafeBad {
    private static LazySafeBad singleton = null;
    private LazySafeBad() {}
    public static synchronized LazySafeBad getInstance() {
        if(singleton == null){
            singleton = new LazySafeBad();
        }
        return singleton;
    }
}
```
这样的做法使所有的线程在调用`getInstance`方法时都会等待其他线程正在调用的线程结束。然而，这种等待的代价显然是太大了。

###双重检验锁
上面无脑的同步方法无疑是大大降低了效率， 为此我们使用**同步块**加锁的方法， 只是需要用到单例对象，且对象还未建立时同步。
```java
public class DoubleLock {
    private static DoubleLock singleton = null;
    private DoubleLock() {}
    public static DoubleLock getInstance() {
        if(singleton == null) {
            synchronized(DoubleLock.class) {
                if(singleton == null) {
                    singleton = new DoubleLock();
                }
            }
        }
        return singleton;
    }
}
```
为什么要判断两次`singleton`对象是否为空， 原因还是为了避免多个对象实例的生成。假如线程A拿到锁进入同步块生成了一个对象并返回。此时线程B拿到锁进入同步块，如果此时不判断`singleton`的话，就会多次创建对象， 造成单例失败。

以上的DCL（双重锁）的做法仍然是有问题的。问题出在`singleton = new DoubleLock()`并不是一个原子操作。在执行`new`时JVM会发生以下几个动作：

- 1.给singleton分配内存
- 2.调用构造函数， 初始化成员变量
- 3.将singleton对象指向所分配的内存空间
当第三步完成后，对象就不再是`null`了。但是JVM会进行指令的优化重排序， 将原本的1-2-3的顺序可能变为1-3-2。这样就有可能在线程A实例化一个对象之后，线程B在3执行完后就抢占了，此时实例已经非`Null`此时线程二就会返回该实例， 这样就会出错了。
在**JDK1.5**之后，可以使用`volatile`解决以上问题。
```java
public class DoubleLock {
    private volatile static DoubleLock singleton = null;
    private DoubleLock() {}
    public static DoubleLock getInstance() {
        if(singleton == null) {
            synchronized(DoubleLock.class) {
                if(singleton == null) {
                    singleton = new DoubleLock();
                }
            }
        }
        return singleton;
    }
}
```
关键字`volatile`可以说是java虚拟机提供的最轻量级的同步机制，它包含了两方面的语义：

- 一、当一个变量定义为`volatile`时，它保证了此变量对所有线程的**可见性**。这里的可见性是指当一条线程修改了这个变量的值，新值对于其他线程来说是可以立即知道的。而普通变量无法做到这一点。普通变量的值在线程间传递需要通过主内存来完成。例如，线程A修改了一个普通变量的值，然后向主内存中回写，线程B等线程A回写完成后再次主内存进行读取时，新值才会可见。
- 二、禁止指令重排优化。普通变量仅仅会保证在该方法的执行过程中所有依赖赋值结果的地方都能保证取到正确的结果，而不能保证变量赋值的操作顺序与代码一致。
上面使用`volatile`来解决DLC实现带来的问题， 正是运用了`volatile`的第二个语义。

###饿汉式

```java
public class Hunger {
    private static final Hunger singleton = new Hunger();
    private Hunger() {}
    public static Hunger getInstance() {
        return singleton;
    }
}
```

###静态内部类（Initialization On Demand Holder idiom）

```java
public class Singleton {
	private Singleton(){}
	public static Singleton getInstance(){
		return SingletonInstance.instance;
	}
	private static class SingletonInstance{
		static final Singleton instance = new Singleton();
	}
}
```
采取静态内部类的方法实现单例模式的好处是，类第一次加载时，类的静态属性进行初始化， 并且JVM虚拟会保证并发访问， 不会出现初始化过程中遭遇别的线程使用的情况。静态变量只初始化一次， 因此可以保证单例。
> 对于为什静态内部类采取static final 的写法，其细节说明可以参见一下文章。
[Initialization On Demand Holder idiom的实现探讨][1]

###枚举
```java
public enum EnumSingleton {
    INSTANCE;
    //定义枚举的方法
    public void someMethods() {
        ...
    }
}
```
一个枚举Enum常量代表了一个实例，enum类型只能有这些常量实例。这样的标准保证enum常量不能被克隆，也不会因为反序列化产生不同的实例，想通过反射机制得到一个enum类型的实例也是不行的。因此可以用枚举Enum来实现单例模式。利用枚举实现单例的好处是简单方便， 并且可以传递一些参数，实现一些文件或者流的读取等。同时，枚举的创建在JVM中也是线程安全的，所以不存在同步问题。

####参考文献
[10 Singleton Pattern Interview questions in Java][2]
[How to create thread safe Singleton in Java][3]


  [1]: http://ifeve.com/initialization-on-demand-holder-idiom/
  [2]: http://javarevisited.blogspot.com/2011/03/10-interview-questions-on-singleton.html
  [3]: http://javarevisited.blogspot.com/2012/12/how-to-create-thread-safe-singleton-in-java-example.html