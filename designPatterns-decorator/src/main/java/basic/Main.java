package basic;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class Main {
	public static void main(String[] args) throws SOAPException, IOException {

		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				// TODO Auto-generated method stub
				return true;
			}
		});
		// URL url = new URL("http://135.251.145.87:8443/plxwebapi/api");

		// URL url = new URL("https://135.251.236.168:8443/plxwebapi/api");

		URL url = new URL("https://ebanks.gdb.com.cn/sperbank/perbankLogin.jsp");
		// URL("https://123.129.254.17/sperbank/perbankLogin.jsp");
		// URL url = new URL("https://www.baidu.com");
		HttpsURLConnection urlConnection = (HttpsURLConnection) url
				.openConnection();

		if (url.getProtocol().equalsIgnoreCase("https")) {

			System.out.println("Enable SSL");
			System.out.println(urlConnection.getURL());

		}

		SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();

		SOAPConnection connection = scf.createConnection();
		MessageFactory mf = MessageFactory.newInstance();

		SOAPMessage msg = mf.createMessage();

		SOAPMessage reply = connection.call(msg, url);

//		System.out.println(msg.toString());

//		System.out.println(reply.toString());

		connection.close();

	}
}
