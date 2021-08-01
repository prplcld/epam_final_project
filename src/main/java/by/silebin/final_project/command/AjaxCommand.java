package by.silebin.final_project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AjaxCommand {
    void execute(HttpServletRequest request, HttpServletResponse response);
}
