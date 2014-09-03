package Main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;
import model.Tennant;
import model.User;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Model model;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}
	
	@Override
	public void init() {
		model = (Model) getServletContext().getAttribute("model");
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("username");
		getServletContext().getRequestDispatcher("/login.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (isValidEntry(request)) {
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(Cookie c : cookies) {
					if(c.getName().equals("session")) {
						int session = Integer.parseInt(c.getValue());
						session++;
						c.setValue(session+"");
						response.addCookie(c);
						System.out.println(session+"");
					}
				}
			} else {
				Cookie cookie = new Cookie("session", "1");
				response.addCookie(cookie);
			}
			getServletContext().getRequestDispatcher("/OverviewServlet").forward(
					request, response);
		} else {
			getServletContext().getRequestDispatcher("/wronglogin.html").forward(
					request, response);
		}
	}

	private boolean isValidEntry(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username == null || password == null) {
			System.out.println("No entry given");
		} 	
		
		for (User u : model.getUsers()) {
			if (username.equals(u.getUsername())) {
				if (u.isValidPassword(password)) {
					return true;
				}
				System.out.println("No valid password entered");
				return false;
			}
		}
		System.out.println("No valid user entered");
		return false;
	}

}
