package by.silebin.final_project.command.impl.ajax;

import by.silebin.final_project.command.AjaxCommand;
import by.silebin.final_project.command.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultAjaxCommand implements AjaxCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(PagePath.NOT_FOUND_PAGE);
    }
}
