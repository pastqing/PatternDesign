# java设计模式——观察者模式

标签（空格分隔）： java 设计模式

---
[TOC]

##一、观察者模式（Observer）的定义：
观察者模式又称为**订阅—发布模式**，在此模式中，一个目标对象管理所有相依于它的观察者对象，并且在它本身的状态改变时主动发出通知。这通常透过呼叫各观察者所提供的方法来实现。此种模式通常被用来事件处理系统。

###1、观察者模式的一般结构
首先看下观察者模式的类图描述：
![此处输入图片的描述][1]
观察者模式的角色如下：

- **Subject（抽象主题接口）**：定义了主题类中对**观察者列表**的一系列操作， 包括增加，删除， 通知等。
- **Concrete Subject**（具体主题类）：
- **Observer（抽象观察者接口）**：定义了**观察者**对主题类更新状态接受操作。
- **ConcreteObserver（具体观察者类）**：实现观察者接口更新主题类通知等逻辑。

从这个类图可以看出， 主题类中维护了一个实现观察者接口的类列表， 主题类通过这个列表来对观察者进行一系列的增删改操作。观察者类也可以主动调用**update**方法来了解获取主题类的状态更新信息。

以上的类图所描述的只是基本的**观察者模式**的思想， 有很多不足。比如作为观察者也可以主动订阅某类主题等。下面的例子将进行一些改动， 以便适用具体的业务逻辑。

###2、观察者模式示例
我们构建一个观察者和主题类， 观察者可以主动订阅主题或者取消主题。主题类统一被一个**主题管理者**所管理。下面给出类图：
![Observer.png-221.4kB][2]

- **Subject**:
```java
public interface Subject {
	//注册一个observer
	public void register(Observer observer);
	//移除一个observer
	public void remove(Observer observer);
	//通知所有观察者
	public void notifyObservers();
	//获取主题类要发布的消息
	public String getMessage();
}
```

- **ConcerteSubject**:
```java
public class MySubject implements Subject {

	private List<Observer> observers;
	
	private boolean changed;
	private String message;
	//对象锁， 用于同步更新观察者列表
	private final Object mutex = new Object();

	public MySubject() {
		observers = new ArrayList<Observer>();
		changed = false;
	}

	@Override
	public void register(Observer observer) {
		if (observer == null)
			throw new NullPointerException();
			//保证不重复
		if (!observers.contains(observer))
			observers.add(observer);
	}

	@Override
	public void remove(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		// temp list
		List<Observer> tempObservers = null;
		synchronized (mutex) {
			if (!changed)
				return;
			tempObservers = new ArrayList<>(this.observers);
			this.changed = false;
		}
		for(Observer obj : tempObservers) {
			obj.update();
		}
	}

	//主题类发布新消息
	public void makeChanged(String message) {
		System.out.println("The Subject make a change: " + message);
		this.message = message;
		this.changed = true;
		notifyObservers();
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
```
**ConcerteSubject**做出更新时， 就通知列表中的所有观察者， 并且调用观察者**update**方法以实现接受通知后的逻辑。这里注意`notifyObservers`中的**同步块**。在多线程的情况下， 为了避免主题类发布通知时， 其他线程对观察者列表的增删操作， **同步块**中用一个**临时List**来获取当前的观察者列表。

- **SubjectManagement**：主题类管理器
```java
public class SubjectManagement {
	//一个记录 名字——主题类 的Map
	private Map<String, Subject> subjectList = new HashMap<String, Subject>();
	
	public void addSubject(String name, Subject subject) {
		subjectList.put(name, subject);
	}
	public void addSubject(Subject subject) {
		subjectList.put(subject.getClass().getName(), subject);
	}
	public Subject getSubject(String subjectName) {
		return subjectList.get(subjectName);
	}
	public void removeSubject(String name, Subject subject) {
		
	}
	public void removeSubject(Subject subject) {
		
	}
	//singleton
	private SubjectManagement() {}
	public static SubjectManagement getInstance() {
		return SubjectManagementInstance.instance;
	}
	private static class SubjectManagementInstance {
		static final SubjectManagement instance = new SubjectManagement();
	}
}

```
**主题类管理器**的作用就是在**观察者**订阅某个主题时， 获取此主题的实例对象。

- **Observer**：
```java
public interface Observer {
	public void update();
	public void setSubject(Subject subject);
}
```
- **ConcerteObserver**:
```java
public class MyObserver implements Observer {
	private Subject subject;
	
	// get the notify message from Concentrate Subject
	@Override
	public void update() {
		String message = subject.getMessage();
		System.out.println("From Subject " + subject.getClass().getName()
				+ " message: " + message);
	}

	@Override
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	// subcirbe some Subject
	public void subscribe(String subjectName) {
		SubjectManagement.getInstance().getSubject(subjectName).register(this);
	}

	// cancel subcribe
	public void cancelSubcribe(String subjectName) {
		SubjectManagement.getInstance().getSubject(subjectName).remove(this);
	}

}
```
- **测试**：我们将主题类和观察者抽象成**写者和读者**
```java
public class ObserverTest {
	private static MySubject writer;
    @BeforeClass
	public static void setUpBeforeClass() throws Exception {
		writer = new MySubject();
		//添加一个名为Linus的作家
		SubjectManagement.getInstance().addSubject("Linus",writer);
	}
	@Test
	public void test() {
	    //定义几个读者
		MyObserver reader1 = new MyObserver();
		MyObserver reader2 = new MyObserver();
		MyObserver reader3 = new MyObserver();
		reader1.setSubject(writer);
		reader2.setSubject(writer);
		reader3.setSubject(writer);
		reader1.subscribe("Linus");
		reader2.subscribe("Linus");
		reader3.subscribe("Linus");
		writer.makeChanged("I have a new Changed");
		reader1.update();
	}
}
```
以上就是观察者模式的小示例。可以看出每个**主题类**都要维护一个相应的**观察者列表**， 这里可以根据具体主题的抽象层次进一步抽象， **将这种聚集放到一个抽象类中去实现， 来共同维护一个列表**， 当然具体操作要看实际的业务逻辑。

##二、Servlet中的Listener
再说**Servlet中的Listener**之前， 先说说观察者模式的另一种形态——**事件驱动模型**。与上面提到的观察者模式的主题角色一样， **事件驱动模型**包括**事件源， 具体事件， 监听器， 具体监听器**。
**Servlet**中的**Listener**就是典型的**事件驱动模型**。
JDK中有一套**事件驱动**的类， 包括一个**统一的监听器接口**和一个**统一的事件源**, 源码如下：

```java
/**
 * A tagging interface that all event listener interfaces must extend.
 * @since JDK1.1
 */
public interface EventListener {
}
```
这是一个**标志接口**， JDK规定所有监听器必须**继承这个接口**。

```java
public class EventObject implements java.io.Serializable {

    private static final long serialVersionUID = 5516075349620653480L;

    /**
     * The object on which the Event initially occurred.
     */
    protected transient Object  source;

    /**
     * Constructs a prototypical Event.
     *
     * @param    source    The object on which the Event initially occurred.
     * @exception  IllegalArgumentException  if source is null.
     */
    public EventObject(Object source) {
        if (source == null)
            throw new IllegalArgumentException("null source");

        this.source = source;
    }

    /**
     * The object on which the Event initially occurred.
     *
     * @return   The object on which the Event initially occurred.
     */
    public Object getSource() {
        return source;
    }

    /**
     * Returns a String representation of this EventObject.
     *
     * @return  A a String representation of this EventObject.
     */
    public String toString() {
        return getClass().getName() + "[source=" + source + "]";
    }
}
```
**EvenObject**是JDK给我们规定的一个统一的**事件源**。**EvenObject**类中定义了一个事件源以及获取事件源的`get`方法。

下面就分析一下**Servlet Listener**的运行流程。

###1、Servlet Listener的组成
目前， Servlet中存在**6种两类事件**的监听器接口， 具体如下图：
![ServletListener.png-127kB][3]
具体触发情境如下表：
![ServletListener _2.png-312.2kB][4]

###2、一个具体的Listener触发过程
我们以**ServletRequestAttributeListener**为例， 来分析一下此处**事件驱动**的流程。

首先一个**Servlet**中， **HttpServletRequest**调用`setAttrilbute`方法时， 实际上是调用的`org.apache.catalina.connector.request#setAttrilbute`方法。 我们看下它的源码：
```java
public void setAttribute(String name, Object value) {
        ...
        //上面的逻辑代码已省略
        
        // 此处即通知监听者
        notifyAttributeAssigned(name, value, oldValue);
    }
```
下面是`notifyAttributeAssigned(String name, Object value, Object oldValue)`的源码
```java
private void notifyAttributeAssigned(String name, Object value,
            Object oldValue) {
            
        //从容器中获取webAPP中定义的Listener的实例对象
        Object listeners[] = context.getApplicationEventListeners();
        if ((listeners == null) || (listeners.length == 0)) {
            return;
        }
        boolean replaced = (oldValue != null);
        //创建相关事件对象
        ServletRequestAttributeEvent event = null;
        if (replaced) {
            event = new ServletRequestAttributeEvent(
                    context.getServletContext(), getRequest(), name, oldValue);
        } else {
            event = new ServletRequestAttributeEvent(
                    context.getServletContext(), getRequest(), name, value);
        }
        //遍历所有监听器列表， 找到对应事件的监听器
        for (int i = 0; i < listeners.length; i++) {
            if (!(listeners[i] instanceof ServletRequestAttributeListener)) {
                continue;
            }
            //调用监听器的方法， 实现监听操作
            ServletRequestAttributeListener listener =
                (ServletRequestAttributeListener) listeners[i];
            try {
                if (replaced) {
                    listener.attributeReplaced(event);
                } else {
                    listener.attributeAdded(event);
                }
            } catch (Throwable t) {
                ExceptionUtils.handleThrowable(t);
                context.getLogger().error(sm.getString("coyoteRequest.attributeEvent"), t);
                // Error valve will pick this exception up and display it to user
                attributes.put(RequestDispatcher.ERROR_EXCEPTION, t);
            }
        }
    }
```

上面的例子很清楚的看出`ServletRequestAttributeListener`是如何调用的。用户只需要实现**监听器接口**就行。Servlet中的**Listener**几乎涵盖了Servlet整个**生命周期**中你感兴趣的事件， 灵活运用这些**Listenser**可以使程序更加灵活。


##三、总结
- 观察者模式定义了对象之间**一对多的关系**， 当一个对象（被观察者）的状态改变时， 依赖它的对象都会收到通知。可以应用到**发布——订阅， 变化——更新**这种业务场景中。

- 观察者和被观察者之间用**松耦合的方式**， 被观察者不知道观察者的细节， 只知道观察者实现了接口。

- 事件驱动模型更加灵活，但也是付出了系统的复杂性作为代价的，因为我们要为每一个事件源定制一个监听器以及事件，这会增加系统的负担。















  [1]: http://dl2.iteye.com/upload/attachment/0088/1629/dedf4ef1-8b8a-3237-a944-5a14d44f950d.png
  [2]: http://static.zybuluo.com/pastqing/ganajqc87702g6zf3z5nfwx5/Observer.png
  [3]: http://static.zybuluo.com/pastqing/y6ow96k2nd19uv2jbm1ebqar/ServletListener.png
  [4]: http://static.zybuluo.com/pastqing/i44w82y3kfdk22ascfhmfdue/ServletListener%20_2.png