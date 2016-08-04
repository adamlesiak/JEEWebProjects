package controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adamlesiak.visma.Client;
import com.adamlesiak.visma.ClientAuthentication;
import com.adamlesiak.visma.ClientDataFetcher;

@Controller
public class Welcome {

//	@RequestMapping(value = "/")
//	public ModelAndView welcome() {
//		ModelAndView model = new ModelAndView("welcome").addObject("var", 123);
//		return model;
//	}
//	
//	@RequestMapping(value = "/page-1")
//	public ModelAndView page1() {
//		ModelAndView model = new ModelAndView("page1").addObject("var", "page 1");
//		return model;
//	}
//	
//	@RequestMapping(value = "/page-2")
//	public ModelAndView page2() {
//		ModelAndView model = new ModelAndView("page2").addObject("var", "page 2");
//		return model;
//	}
	
	
	String AUTHORIZATION_ENDPOINT = "https://auth-sandbox.test.vismaonline.com/eaccountingapi/oauth/authorize";
	String TOKEN_ENDPOINT = "https://auth-sandbox.test.vismaonline.com/eaccountingapi/oauth/token";
	String CLIENT_ID = "svenskaugnslackering";
	String CLIENT_SECRET = "gFpXegrsa4es4bs7Wg2nnFnWEw7UWxpsD7ZSX6ZA";
	String REDIRECT_URI = "https://localhost:44300/callback";
	
	
	@RequestMapping(value = "/")
	public String welcome(HttpServletResponse response) throws IOException {
		
		Client client = new Client(CLIENT_ID, CLIENT_SECRET, REDIRECT_URI);
		ClientAuthentication clientAuthentication = new ClientAuthentication(client);
		String url = clientAuthentication.getAuthenticationURL(AUTHORIZATION_ENDPOINT);
				
		response.sendRedirect(url);
						
		return null;
	}
	
	@RequestMapping(value = "/callback")
	public ModelAndView callback(HttpServletRequest request) throws IOException {
		
		String codeRequest = request.getParameter("code");
		
		Client client = new Client(CLIENT_ID, CLIENT_SECRET, REDIRECT_URI);
		ClientDataFetcher clientDataFetcher = new ClientDataFetcher(client, codeRequest);
		clientDataFetcher.requestAccessToken(TOKEN_ENDPOINT);
		String accesToken = clientDataFetcher.getAccessToken();
		
		ModelAndView model = new ModelAndView("results").addObject("access_token", accesToken);
		return model;
	}
	
}
