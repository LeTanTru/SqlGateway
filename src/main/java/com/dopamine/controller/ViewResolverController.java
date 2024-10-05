package com.dopamine.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/views/*", "/css/*", "/js/*"})
public class ViewResolverController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        String resourcePath = path.substring(request.getContextPath().length());

        // Map the resource path to the WEB-INF directory
        if (resourcePath.startsWith("/views/") || resourcePath.startsWith("/css/") || resourcePath.startsWith("/js/")) {
            // Forward the request to the resource in WEB-INF
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF" + resourcePath);
            dispatcher.forward(request, response);
        } else {
            // Resource not found
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
