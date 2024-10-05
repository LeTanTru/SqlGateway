package com.dopamine.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SQLUtil {
	public static String getHtmlTable(ResultSet results) throws SQLException {
		StringBuilder htmlTable = new StringBuilder();
		ResultSetMetaData metaData = results.getMetaData();
		int columnCount = metaData.getColumnCount();

		htmlTable.append("<table class=\"table table-bordered table-hover\">");

		htmlTable.append("<tr>");
		for (int i = 1; i <= columnCount; i++) {
			htmlTable.append("<th class=\"text-center\" scope=\"col\">");
			htmlTable.append(metaData.getColumnName(i));
			htmlTable.append("</th>");
		}
		htmlTable.append("</tr>");

		while (results.next()) {
			htmlTable.append("<tr>");
			for (int i = 1; i <= columnCount; i++) {
				htmlTable.append("<td class=\"text-center\">");
				htmlTable.append(results.getString(i));
				htmlTable.append("</td>");
			}
			htmlTable.append("</tr>");
		}

		htmlTable.append("</table>");
		return htmlTable.toString();
	}
	/*
	 * <table class="table table-bordered table-hover"> <thead> <tr> <th
	 * class="text-center" scope="col">UserID</th> <th class="text-center"
	 * scope="col">FirstName</th> <th class="text-center" scope="col">LastName</th>
	 * <th class="text-center" scope="col">EmailAddress</th> </tr> </thead> <tbody>
	 * <c:forEach items="${users}" var="user"> <tr> <th class="text-center"
	 * scope="row">${user.userId}</th> <td
	 * class="text-center">${user.firstName}</td> <td
	 * class="text-center">${user.lastName}</td> <td
	 * class="text-center">${user.emailAddress}</td> </tr> </c:forEach> </table>
	 */
}
