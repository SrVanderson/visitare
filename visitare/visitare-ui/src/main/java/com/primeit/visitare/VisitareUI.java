package com.primeit.visitare;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
 *
 */
@SuppressWarnings("serial")
@Theme("visitare-theme")
@Widgetset("com.primeit.visitare.VisitareAppWidgetset")
public class VisitareUI extends UI {

	@Override
	protected void init(VaadinRequest vaadinRequest) {

	}

	@WebServlet(urlPatterns = "/*", name = "VisitareUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = VisitareUI.class, productionMode = false)
	public static class VisitareUIServlet extends VaadinServlet {
	}
}
