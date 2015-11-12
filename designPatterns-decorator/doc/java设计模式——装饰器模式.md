# java设计模式——装饰器模式

标签（空格分隔）： java 设计模式

---
[TOC]

## 一、装饰器模式(Decorator)的定义
[wiki][1]是这样定义**装饰器模式的**：在面向对象编程的领域，一种动态的往一个类中添加新的行为的设计模式。就功能而言，装饰器模式相比生成子类更加灵活，这样可以给某个对象而不是整个类添加一些功能。

通过**装饰器模式**可以在运行时扩充一个类的功能。增加一个修饰类包裹原来的类，包裹的方式一般是通过在将原来的对象作为修饰类的构造函数的参数。装饰类实现新的功能，但是，在不需要用到新功能的地方，它可以直接调用原来的类中的方法。修饰类必须和原来的类有相同的接口。

##二、装饰器模式的实现
下面是装饰器的基本类图：
![Decorator.png-189.4kB][2]
**装饰器模式的基本角色**：

- **被装饰类接口**： Component
- **具体被装饰类**： ConcreteComponent
- **装饰器抽象类**： Decorator, 这个类可有可无具体看情况。
- **具体的装饰器类**： ConcreteDecorator， 具体装饰器类实现真正的动态添加类的功能的作用。

```java
public interface Component {
    public void method();
}
```
```java
public class ConcreteComponent implements Component{
	public void method() {
		System.out.println("The basic method");
	}
}
```
```java
public abstract class Decorator implements Component{
	protected Component component;
	public Decorator(Component component) {
		this.component = component;
	}
	public void method() {
		component.method();
	}
}
```
```java
public class ConcreteDecoratorA extends Decorator{
	public ConcreteDecoratorA(Component component) {
		super(component);
	}
	public void method() {
	    //先调用原始方法， 在执行扩展方法
		super.method();
		System.out.println("DecoratorA add sth");
	}
	//扩展的其他方法...
	public void methodA() {
		System.out.println("method A");
	}
	...
	
}
```
一个装饰类同样可以被另一个装饰器类所装饰。利用**组合**而不是**继承**的形式来扩展某个类， 使其更加灵活。

##二、装饰器模式的具体应用
我们构造这样一个情景：某个应用的数据访问层上，实现持久化的技术有很多种**mybaits，jdbc**等等。如此我们设计一个接口：
```java
public interface GenerateInterface<T> {
	//创建一条记录
	public void save(T data);
	//更新一条记录
	public void update(T data);
}
```
不同的ORM技术，我们分别设计两个类：
```java
public class MybatisORM<T> implements GenerateInterface<T> {

	@Override
	public void save(T data) {
		//mybatis save
		System.out.println("mybaits save");
	}
	@Override
	public void update(T data) {
		//mybatis update
		System.out.println("mybatis update");
	}
}
```
```java
public class JdbcORM<T> implements GenerateInterface<T> {
	@Override
	public void save(T data) {
		//insert data into table with jdbc
		System.out.println("jdbc save");
	}
	@Override
	public void update(T data) {
		//update data to table with jdbc
		System.out.println("jdbc update");
	}

}
```
现在新增一个需求，是我们在持久化数据的时候， **save or update**时同时更新日志，这时候为了不更改原有代码， 也不使用继承，我们采用**装饰器模式**，定义一个装饰者。
```java
public class LogDecoratorORM <T> implements GenerateInterface<T>{
	
	private GenerateInterface<T> generator;
	
	public LogDecoratorORM(GenerateInterface<T> generator) {
		this.generator = generator;
	}
	private void logSave(T data) {
		//to insert log table
		System.out.println("log save");
	}
	private void logUpdate(T data) {
		//to update log table
		System.out.println("log update");
	}
	@Override
	public void save(T data) {
		logSave(data);
		generator.save(data);
	}
	@Override
	public void update(T data) {
		logUpdate(data);
		generator.save(data);
	}
}
```
如此就可以实现需求的变动。

##三、JDK中的IO与装饰器模式
JDK中IO体系的设计就是基于**装饰器模式**的。我们先看个简单的IO体系类图：


**InputSteam**相当于上面模型的**Component**
**FileInputStream**就是InputSteam的实现类了。
**BufferedInputSteam, DataInputSteam**等就是具体的装饰类了。



##四、总结
- **装饰器模式的优点**：
 - 相比于静态继承更加灵活。使用静态继承可能会创建更多的子类。
 - 保持了接口的一致， 使用户操作没有难度。
 - 可以产生叠加效果，可以重复修饰同一个对象。也可以使用不同的装饰器修饰同一个对象，产生叠加。
 - 可以作为**AOP**的一种简单实现方式。即可以在这类方法的前后加入控制方法。
 
- **装饰器模式的缺点：**
 - 看上去产生了很多小类，学起来累。。
 - 一个被修饰过的类和之前的并不一样。需要额外注意。



  [1]: https://zh.wikipedia.org/wiki/%E4%BF%AE%E9%A5%B0%E6%A8%A1%E5%BC%8F
  [2]: http://static.zybuluo.com/pastqing/1zbo7vf2yvbxm4tmhlwbi8h0/Decorator.png