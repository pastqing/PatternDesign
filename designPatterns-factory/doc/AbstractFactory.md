# java设计模式——抽象工厂模式

标签（空格分隔）： java 设计模式

---
[TOC]
##前言
通过上篇的[工厂方法模式][1]我们知道， 在工厂方法模式中具体工厂负责具体的产品， 它们是一 一对应的。一个具体工厂中往往只有一个具体产品的**create**方法或者多个**create**的重载方法。这样就带来一个问题， 如果想一个工厂能生产**多种不同的产品**， 那么工厂方法模式就不适合， 这时就需要使用抽象工厂模式

###产品等级结构与产品族
引用[Abstract Factory][2]的定义：

- **产品等级结构**：产品等级结构即产品的继承结构，如一个抽象类是电视机，其子类有海尔电视机、海信电视机、TCL电视机，则抽象电视机与具体品牌的电视机之间构成了一个产品等级结构，抽象电视机是父类，而具体品牌的电视机是其子类。
- **产品族** ：在抽象工厂模式中，产品族是指由同一个工厂生产的，位于不同产品等级结构中的一组产品，如海尔电器工厂生产的海尔电视机、海尔电冰箱，海尔电视机位于电视机产品等级结构中，海尔电冰箱位于电冰箱产品等级结构中。

有了这两个概念， 可以更好地理解抽象工厂模式：

- 当我们定义的工厂不只是生产`同一个`**产品等级结构**中的产品时，这时就应该考虑使用抽象工厂模式。
- 抽象工厂模式与工厂方法模式最大的区别在于，工厂方法模式针对的是一个产品等级结构，而抽象工厂模式则需要面对多个产品等级结构，一个工厂等级结构可以负责多个不同产品等级结构中的产品对象的创建。当一个工厂等级结构可以创建出分属于不同产品等级结构的一个产品族中的所有对象时，抽象工厂模式比工厂方法模式更为简单、有效率。

##抽象工厂模式的定义
**抽象工厂模式（Abstract Factory）**： 为创建一组相关或相互依赖的对象提供一个接口，而且无需指定他们的具体类。
它包含以下角色：

- **抽象工厂**
- **具体工厂**
- **抽象产品**
- **具体产品**
![此处输入图片的描述][3]

##抽象工厂模式的应用
###场景代码示例
**抽象产品类**
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
**产品类1 和产品类2同属于一个产品族**
```java
/**产品类3 产品类4 **/
public class AnswerBody_Electric extends AnswerHead_2 {
    public String toXML() {
        System.out.println("AnswerBody_Electric");
    }
}
public class AnswerBody_Electric extends AnswerHead_2 {
    public String toXML() {
        System.out.println("AnswerBody_Electirc product2");
    }
}
```
**抽象工厂接口**
```java
interface AbstractFactory {
   public AnswerHead createProductA();
   public AnswerHead_2 createproductB();
}
```

**具体工厂类**
```java
public class ProductAFactory implements AbstractFactory{
    
}
```

###Spring源码分析



  [1]: https://www.zybuluo.com/pastqing/note/129365
  [2]: http://design-patterns.readthedocs.org/zh_CN/latest/creational_patterns/abstract_factory.html
  [3]: http://design-patterns.readthedocs.org/zh_CN/latest/_images/AbatractFactory.jpg