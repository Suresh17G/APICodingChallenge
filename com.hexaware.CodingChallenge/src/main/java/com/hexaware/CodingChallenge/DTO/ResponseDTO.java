package com.hexaware.CodingChallenge.DTO;


public class ResponseDTO {

    private String jwt;
    private long userid;
    private String role;

    
	public ResponseDTO() {
		super();
	}



	public ResponseDTO(String jwt, long userid, String role) {
		super();
		this.jwt = jwt;
		this.userid = userid;
		this.role = role;
	}



	public long getUserid() {
		return userid;
	}



	public void setUserid(long userid) {
		this.userid = userid;
	}


	public String getJwt() {
		return jwt;
	}


	public void setJwt(String jwt) {
		this.jwt = jwt;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "ResponseDTO [jwt=" + jwt + ", role=" + role + "]";
	}

	
}
