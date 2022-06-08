package com.lawencon.auth.security;

import java.io.IOException;
import java.time.Duration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.auth.dao.UserDao;
import com.lawencon.auth.dto.login.LoginDtoDataRes;
import com.lawencon.auth.dto.login.LoginDtoReq;
import com.lawencon.auth.dto.login.LoginDtoRes;
import com.lawencon.auth.model.User;

public class AuthenticationFilter extends OncePerRequestFilter {
	private UserDao userDao;
	private AuthenticationManager authenticationManager;
	private JwtComponent jwtComponent;
	private ObjectMapper objectMapper;

	public AuthenticationFilter(AuthenticationManager authenticationManager, JwtComponent jwtComponent,
			ObjectMapper objectMapper, UserDao userDao) {
		this.authenticationManager = authenticationManager;
		this.jwtComponent = jwtComponent;
		this.objectMapper = objectMapper;
		this.userDao = userDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request.getRequestURI().equals("/auth/login")) {
			try {
				if (!request.getMethod().equals(HttpMethod.POST.name())) {
					response.setStatus(401);
					return;
				}

				LoginDtoReq data = objectMapper.readValue(request.getInputStream(), LoginDtoReq.class);

				boolean isAuthenticate = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()))
						.isAuthenticated();

				if (isAuthenticate) {
					User user = userDao.getByUser(data.getUsername());
					String token = jwtComponent.GenerateToken(Duration.ofHours(1), user.getId().toString());
					LoginDtoRes loginDtoRes = new LoginDtoRes();

					LoginDtoDataRes loginData = new LoginDtoDataRes();
					
					loginData.setToken(token);
					loginData.setId(user.getId());
					loginData.setUsername(user.getUsername());
					loginData.setRoleCode(user.getRoleId().getRoleCode());

					loginDtoRes.setData(loginData);

					response.setContentType(MediaType.APPLICATION_JSON_VALUE);
					response.getWriter().append(objectMapper.writeValueAsString(loginDtoRes));
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(401);
				response.getWriter().append(e.getMessage());
			}

			return;
		}

		filterChain.doFilter(request, response);
	}

}
