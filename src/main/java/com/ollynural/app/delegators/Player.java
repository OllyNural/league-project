package com.ollynural.app.delegators;

import com.ollynural.app.main.dao.BasicDAO;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class Player
 */
@WebServlet("/player")
public class Player extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Player() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Working step 1");
        handleServerResponse(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleServerResponse(request, response);
    }
	
	private void handleServerResponse(HttpServletRequest request, HttpServletResponse response) {

        try {
            BasicDAO dao = new BasicDAO();
            String summonerName = request.getParameter("summonerName");
            System.out.println(summonerName);
            dao.getOrRetrieveBasicAndRankedSummonerInformation(summonerName);

            request.getRequestDispatcher("WEB-INF/JSP/pages/player.jsp").forward(request, response);
            System.out.println("working step 2" + summonerName);
        } catch (Exception e){
            e.printStackTrace();
        }
	}

}
