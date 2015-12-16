package com.ollynural.app.delegators;

import com.ollynural.app.main.dao.BasicDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Player
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getRequestDispatcher("src/WEB-INF/JSP/pages/register.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String summonerName = request.getParameter("summonerName");
			String summonerUniversity = request.getParameter("summonerUniversity");

			System.out.println(summonerName + summonerUniversity);
			
			BasicDAO dao = new BasicDAO();
			int outcome = dao.checkAndUpdateSummonerNameAndInformation(summonerName, summonerUniversity);
			System.out.println(outcome);
			//request.setAttribute("outcome", outcome);
			//request.getRequestDispatcher("/WEB-INF/JSP/pages/register.jsp").forward(request, response);
			response.setContentType("text/html");;  // Set content type of the response so that jQuery knows what it can expect.
			response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
			response.getWriter().println(outcome);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
