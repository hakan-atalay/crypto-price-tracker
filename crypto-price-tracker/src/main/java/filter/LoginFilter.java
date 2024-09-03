package filter;

import java.io.IOException;

import entity.AppUser;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String url = httpRequest.getRequestURI();
		HttpSession session = httpRequest.getSession();

		AppUser user = (AppUser) session.getAttribute("user");
		boolean loggedIn = (user != null);
		boolean isAdmin = loggedIn && user.getRole() != null && "admin".equals(user.getRole().getRoleName());

		if (url.endsWith("user/login.xhtml") || url.endsWith("user/register.xhtml")) {
			chain.doFilter(request, response);
			return;
		}

		if (url.endsWith("user/logout.xhtml")) {
			session.invalidate();
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/user/login.xhtml");
			return;
		}

	    if (loggedIn && (isAdmin || !url.contains("/admin/"))) {
	        chain.doFilter(request, response);
	        return;
	    }

		httpResponse.sendRedirect(httpRequest.getContextPath() + "/user/login.xhtml");
	}
}
