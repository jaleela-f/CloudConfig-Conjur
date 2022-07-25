package com.cyberark.conjur;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cyberark.conjur.configclient.domain.ConjurAuthParam;


@SpringBootApplication
@RestController
public class ClientApplication {


	
	

	@Value("${jenkins-app.dbUserName}")
	private  String userName;
	
	@Value("${jenkins-app.dbPassword}")
	private String password;
	
	@Value("${jenkins-app.dbUrl}")
	private String url;
	
	@Autowired
	private ConjurAuthParam authParam;

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);

	}
	
	@RequestMapping("/getAuthParam")
	public String getAuthParams() {
		System.out.println("Rocking the Libray usage :) !");
		String conurAccount = authParam.getConjurAccount();
		String conjurApiKey = authParam.getConjurApiKey();
		String conjurUrl = authParam.getConjurApplianceUrl();
		String conjurLogin = authParam.getConjurAuthLogin();
		
		System.out.println("Property >>>>>>>"+userName);
		System.out.println("Property >>>>>>>"+password);
		System.out.println("Property >>>>>>>>"+url);
		
		System.out.println("Conjur Account>>>"+conurAccount);
		System.out.println("Conjur Url>>>>>"+ conjurApiKey);
		System.out.println("Conjur Login>>>>>"+conjurUrl);
		System.out.println("Conjur API Key >>>>>."+conjurLogin);
		
		return  String.valueOf("REtrieved Secrets :"+"DBUSERNAME ="+userName+"Password ="+password+"Url ="+url);
	}

}
