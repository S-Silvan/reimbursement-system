package com.ers.service;

import javax.servlet.http.HttpServletRequest;

import com.ers.model.Employee;

public interface OAuthService {
	Employee login(String userid, String password);
	boolean logout(HttpServletRequest request);
}
