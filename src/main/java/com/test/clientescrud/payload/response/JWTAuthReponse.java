package com.test.clientescrud.payload.response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTAuthReponse {
    
	private String tokenAccess;
	private String typeToken = "Bearer";

	public JWTAuthReponse(String tokenAccess) {
		super();
		this.tokenAccess = tokenAccess;
	}

	public JWTAuthReponse(String tokenAccess, String typeToken) {
		super();
		this.tokenAccess = tokenAccess;
		this.typeToken = typeToken;
	}
}
