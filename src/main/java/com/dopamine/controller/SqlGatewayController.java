package com.dopamine.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dopamine.connection.DBConnection;
import com.dopamine.utils.SQLUtil;

/**
 * Servlet implementation class SqlGatewayController
 */
@WebServlet("/sqlgateway")
public class SqlGatewayController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SqlGatewayController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = new DBConnection().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String sqlStatement = request.getParameter("sqlStatement");
		String sqlResult = "";

		try {

			sqlStatement = sqlStatement.trim();

			PreparedStatement ps = connection.prepareStatement(sqlStatement);

			if (sqlStatement.length() >= 6) {
				String sqlType = sqlStatement.substring(0, 6);
				if (sqlType.equalsIgnoreCase("select")) {

					ResultSet resultSet = ps.executeQuery();

					sqlResult = SQLUtil.getHtmlTable(resultSet);
					resultSet.close();
				} else {
					int i = ps.executeUpdate(sqlStatement);
					if (i == 0) {
						sqlResult = "<p>The statement executed successfully.</p>";
					} else {
						sqlResult = "<p>The statement executed successfully.<br>" + i + " row(s) affected.</p>";
					}
				}
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			sqlResult = "<p>Error executing the SQL statement: <br>" + e.getMessage() + "</p>";
		} finally {
		}

		HttpSession session = request.getSession();
		session.setAttribute("sqlResult", sqlResult);
		session.setAttribute("sqlStatement", sqlStatement);

		response.sendRedirect("home");
	}

}
