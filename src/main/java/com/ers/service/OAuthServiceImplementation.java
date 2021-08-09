package com.ers.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ers.dao.OAuthDao;
import com.ers.dao.OAuthDaoImplementation;
import com.ers.model.Employee;

public class OAuthServiceImplementation implements OAuthService{
	private OAuthDao oAuthDao=new OAuthDaoImplementation();
	
	@Override
	public boolean login(String userid, String password, HttpServletRequest request) {
		Employee employee=oAuthDao.login(userid, password);
		if(employee!=null) {
			HttpSession httpSession=request.getSession();
			httpSession.setAttribute("employee", employee);
		}
		return employee!=null;
	}
	
	@Override
	public boolean authorize(HttpServletRequest request) {
		boolean isAuthorized=false;
		HttpSession httpSession=request.getSession();
		Employee employee=(Employee) httpSession.getAttribute("employee");
		if(employee!=null)
			isAuthorized=oAuthDao.authorize(employee);
		return isAuthorized;
	}
	
	@Override
	public boolean logout(HttpServletRequest request) {
		HttpSession httpSession=request.getSession();
		httpSession.setAttribute("employee", null);
		return true;
	}
}