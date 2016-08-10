package controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.auth.AuthenticationException;
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
	String CLIENT_ID = "";
	String CLIENT_SECRET = "";
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
	public ModelAndView callback(HttpServletRequest request) throws IOException, AuthenticationException {
		
		String codeRequest = request.getParameter("code");
		
		Client client = new Client(CLIENT_ID, CLIENT_SECRET, REDIRECT_URI);
		ClientDataFetcher clientDataFetcher = new ClientDataFetcher(client, codeRequest);
		
		Object accesToken_Object = request.getSession().getAttribute("access_token");
		String accesToken = "";
		if (accesToken_Object == null) {
			clientDataFetcher.requestAccessToken(TOKEN_ENDPOINT);
			accesToken = clientDataFetcher.getAccessToken();
			request.getSession().setAttribute("access_token", accesToken);
		} else {
			accesToken = accesToken_Object.toString();
		}

		ModelAndView model = new ModelAndView("results").addObject("access_token", accesToken);
		return model;
	}
	
	@RequestMapping(value = "/send-article")
	public String sendArticle(HttpServletRequest request) throws IOException {
		
		Client client = new Client(CLIENT_ID, CLIENT_SECRET, REDIRECT_URI);
		ClientDataFetcher clientDataFetcher = new ClientDataFetcher(client, null);		
		String JSON = "{\"IsActive\": \"true\", \"Number\": \"No20160810\", \"Name\": \"20160805_03_Name\", \"NameEnglish\": \"Order fee\", \"NetPrice\": \"100\", \"GrossPrice\": \"125\", \"CodingId\": \"9661752e-4a27-4fd4-abb2-0ff63a88d8ae\", \"UnitId\": \"b6344095-57c2-4488-8d6b-44c16cc700b5\", \"UnitName\": \"Styck\", \"StockBalance\": \"0\", \"StockBalanceReserved\": \"0\", \"StockBalanceAvailable\": \"0\",\"HouseWorkType\": null}";
		
		Object accesToken_Object = request.getSession().getAttribute("access_token");
		String accesToken = accesToken_Object.toString();
		clientDataFetcher.sendDataJSON("https://eaccountingapi-sandbox.test.vismaonline.com/v1/articles", accesToken, JSON);
		return null;
	}
	
}
