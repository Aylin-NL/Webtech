package Main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<User> users = new ArrayList<User>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		users.add(new User("Damon", "Daal"));
		users.add(new User("Aylin", "vRos"));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("username");
		getServletContext().getRequestDispatcher("/login.html").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (isValidEntry(request)) {
			getServletContext().getRequestDispatcher("/home.html").forward(
					request, response);
		} else {
			getServletContext().getRequestDispatcher("/login.html").forward(
					request, response);
		}

		// if(name.equals("register")) {
		// getServletContext().getRequestDispatcher("/register.html").forward(request,
		// response);
		// } else if (name.equals("submit")) {
		// System.out.println("niet registreren");
		// }

	}

	private boolean isValidEntry(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username == null && password == null) {
			System.out.println("No entry given");
		} else if (username == null && password != null) {
			System.out.println("Please enter your username!");
		} else if (username != null && password == null) {
			System.out.println("Please enter your password!");
		}
		for (User u : users) {
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
