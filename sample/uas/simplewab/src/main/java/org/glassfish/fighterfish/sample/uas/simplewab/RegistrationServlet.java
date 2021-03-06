/*
 * Copyright (c) 2011, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package org.glassfish.fighterfish.sample.uas.simplewab;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.glassfish.fighterfish.sample.uas.api.UserAuthService;
import org.glassfish.osgicdi.OSGiService;
import org.osgi.framework.ServiceException;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject @OSGiService(dynamic=true)
	private UserAuthService uas;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       PrintWriter out = response.getWriter();
	       out.println("<HTML> <HEAD> <TITLE> Login "
		            + "</TITLE> </HEAD> <BODY BGCOLOR=white>");

	       String name = request.getParameter("name");
	       String password = request.getParameter("password");
	        try {
	            if (uas.register(name, password)) {
	                out.println("Registered " + name);
	            } else {
	                out.println("Failed to register " + name);
	            }
	        } catch (ServiceException e) {
	            out.println("Service is not yet available");
	        }
	        out.println("</BODY> </HTML> ");
	}

}
