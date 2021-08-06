package by.silebin.final_project.controller.filter;

import by.silebin.final_project.command.RequestAttribute;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@WebFilter(urlPatterns = {"/controller"})
public class LocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(true);
        String queryString = httpRequest.getQueryString();
        String prevRequest = RequestAttribute.CONTROLLER_URL + queryString;
        if (session.getAttribute(RequestAttribute.LOCALE) == null) {
            session.setAttribute(RequestAttribute.LOCALE, Locale.getDefault());
        }
        if (queryString != null) {
            session.setAttribute(RequestAttribute.PREV_REQUEST, prevRequest);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
