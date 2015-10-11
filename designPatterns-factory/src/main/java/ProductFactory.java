import com.su.product.AnswerBody_Mobile;
import com.su.product.AnswerHead;
import com.su.product.AnswerHeadOnly;


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
	public static void main(String[] args) {
		AnswerHead an = ProductFactory.createProduct("AnswerBody_Mobile");
		an.toXML();
	}
}
