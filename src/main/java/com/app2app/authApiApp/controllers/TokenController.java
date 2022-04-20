package com.app2app.authApiApp.controllers;

import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/app2app")
public class TokenController {

	@PostMapping(path="/tokens", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void createToken(@RequestHeader Map<String, String> headers, @RequestBody String responseBody) {
		
		String[] authzHeader = headers.get("authorization").split(" ");
		String authData = authzHeader[1];
		String credentials = new String(Base64.decodeBase64(authData));
		String userid = credentials.split(":")[0];
		String password = credentials.split(":")[1];
		System.out.println(userid);
		System.out.println(password);
		if(userid.equals("testuser") && password.equals("testpwd")) {
			
		}
		else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Credentials");
		}
		
		
	}
}
