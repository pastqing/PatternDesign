# java设计模式——简单工厂模式

标签（空格分隔）： java 设计模式

---
[TOC]
##前言
简单来说， 工厂模式就是按照需求来返回一个类型的对象。 使用工厂模式的意义就是， 如果对象的实例化与代码依赖太大的话， 不方便进行扩展和维护。 使用工厂的目的就是使对象的实例化与主程序代码就行解耦。
工厂模式的设计原则是： **依赖抽象原则** ：

- **变量不要持有具体类的引用**
- **不要让类继承自具体类， 要继承抽象类或接口**
- **不要覆盖基类中已实现的方法**

##简单工厂模式
准确的说， 简单工厂模式并不是设计模式， 他更像是一种编程习惯。简单工厂模式不属于23种GOF设计模式之一。简单工厂模式的思想是利用一个工厂类来选择创建不同的产品类。下面将实例讲解。

> 应用场景：我们与银行有业务来往， 需要与银行方进行报文通信。 不同的业务的报文不同， 为此我们需要有一个工厂来简单创建不同的报文。

为了符合上述场景， 首先定义两个产品类和一个产品接口， 具体如下：
```java
    //产品接口
    public interface IProduct{
        
    }
```
```
/** 定义一个抽象类， 它是所有产品的父类 **/
public abstract class AnswerHead {
    protected String request_type; 						
	protected String agent_code;						
	protected String trn_code;							
	protected String front_traceno;	
	public abstarct String toXML();
}
```
```
    /** 产品类1， 只有应答报文头 **/
public class AnswerHeadOnly extends AnswerHead {
	public String toXML() {
		System.out.println("AnswerHeadOnly to xml");
		return null;
	}
}
```
```
/**产品类2， 业务1应答报文体 **/
public class AnswerBody_Mobile extends AnswerHead {
    public String toXML() {
        System.out.println(" AnswerBody_Mobile to xml");
        return null;
    }
}
```
接下来定义一个简单工厂类， 根据简单工厂的思想， 这个类用于根据参数的不同而生产不同的产品实例：
```
public class ProductFactory {

    public static AnswerHead createProduct(String type) {
        AnswerHead answer = null;
		if(answer == null){
			if(type.equals("AnswerHead"))
				return new AnswerHeadOnly();
			if(type.equals("AnswerBody_Mobile"))
				return new AnswerBody_Mobile();
		}
		return answer;
    }
}
```
以上就是一个简单工厂模式的场景应用， 下面介绍一个简单工厂模式在请求分发上的应用
###简单工厂模式的实际应用
我们在写一个Servlet处理客户端请求的时候， 往往会一个Servlet处理多个业务逻辑， 比如：
```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    String flag = request.getParameter("flag");
    if(flag.equals("service1")) {
        service1();
    }else if(flag.equals("service2")) {
        service2();
    }
    ...
}
```
以上是我们的Servlet处理多业务逻辑的常规方法， 写一坨`if else`语句。一种比较好的办法就是， 将请求的分发与Servlet分离， 让Servlet只处理业务逻辑。我们把各种请求的Servlet看做产品类， `javax.servlet.HttpServlet`是产品父类， `javax.servlet.Servlet`是产品接口， 这样我们定义一个`ServletFactory`, 在过滤器里解析url请求并交给`ServletFactory`来处理就可以了。 这就是一个典型的简单工厂应用。
```
@WebFilter("/TransRequest")
public class TransRequest implements Filter{
	private String servletName;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest myRequest = (HttpServletRequest)request;
	    //拿到请求的servlet名字， 这里我们约定请求都是/servletName形式
		String names[] = myRequest.getRequestURI().trim().split("/");
		servletName = names[2];
		if( servletName != null) {
		//以下是最典型的两句简单工厂的例子
			Servlet servlet = ServletFactory.createServlet(servletName);
			servlet.service(request, response);
		}else
			chain.doFilter(request, response);
	}

```
每次来一个请求我们用工厂生产一个servlet， 这样可以免去在xml配置大量的servlet路径信息比较方便。而且这样也会使逻辑更加清晰， servlet仅仅就是在业务层处理业务。
工厂类如下：
```
public class ServletFactory {
	public static Servlet createServlet(String servletName) throws ServletException {
		if(servletName.equals("servletName1")) {
			return new Service1();
		}else if(servletName.equals("servletName2")){
			return new Service2();
		}else{
			throw new ServletException("No such servlet");
		}
	}
}
```
上面的工厂类虽然没有抛开繁琐的`if else`,  但是利用简单工厂的思想仍然解决了一些问题。简单工厂是非常简单的一种算不上设计模式的设计模式， 解决的问题也很有限。 以上请求分发各大javaEE 框架都已实现， 例如`Struts2`, 当然框架不是用的简单工厂。

##总结
简单工厂模式， 总结起来就是一个工厂类，一个产品接口（其实也可以是一个抽象类，甚至一个普通的父类）和一群实现了产品接口的具体产品，而这个工厂类，根据传入的参数去创造一个具体的实现类，并向上转型为接口作为结果返回。

下一篇是**工厂方法模式**


参考文献：[简单工厂模式][1]


  [1]: http://blog.csdn.net/zuoxiaolong8810/article/details/9044033