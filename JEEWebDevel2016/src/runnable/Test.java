package runnable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.adamlesiak.visma.Client;
import com.adamlesiak.visma.ClientAuthentication;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
		
		/*
		 * test class change
		 */

		List<String> list = Arrays.asList("warsaw", "london", "paris", "madrid");
		
		/*
		list.sort(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
			
		});
		*/
		
		Collections.sort(list, (String o1, String o2) -> o1.compareTo(o2));
		
		
		//list.forEach(System.out::println);
	
		String AUTHORIZATION_ENDPOINT = "https://auth-sandbox.test.vismaonline.com/eaccountingapi/oauth/authorize";
		String TOKEN_ENDPOINT = "https://auth-sandbox.test.vismaonline.com/eaccountingapi/oauth/token";
		String CLIENT_ID = "[client_id]";
		String CLIENT_SECRET = "[client_secret]";
		String REDIRECT_URI = "https://localhost:44300/callback";
		
		Client client = new Client(CLIENT_ID, CLIENT_SECRET);
		ClientAuthentication clientAuthentication = new ClientAuthentication(client);
		String url = clientAuthentication.getAuthenticationURL(AUTHORIZATION_ENDPOINT, REDIRECT_URI);
		System.out.println(url);
		
	}

}
