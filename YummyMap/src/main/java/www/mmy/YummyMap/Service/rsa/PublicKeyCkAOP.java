package www.mmy.YummyMap.Service.rsa;

import javax.servlet.http.HttpServletRequest;

//import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint; 
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Aspect
public class PublicKeyCkAOP {
	@Autowired
	RsaServiceImpl rsaImpl;
	
	@Before("execution(* www.mmy.YummyMap.Service.admin.AdminService.PublicKeySrvc(..))")
	public void rsaCk(JoinPoint join ) {
		Object[] obj = join.getArgs();
		
		HttpServletRequest req = (HttpServletRequest) obj[0];
		
		String RSAModulus = req.getParameter("RSAModulus");
		String RSAExponent = req.getParameter("RSAExponent");
		
		System.out.println("RSAModulus : " + RSAModulus);
		System.out.println("RSAExponent : " + RSAExponent);
		
		if(RSAModulus == null || RSAModulus.length() == 0) { 
			rsaImpl.initRsa(req);
		}else {
	        req.setAttribute("RSAModulus", RSAModulus); // rsa modulus 를 request 에 추가
	        req.setAttribute("RSAExponent", RSAExponent); // rsa exponent 를 request 에 추가
		}
	}
	
}
