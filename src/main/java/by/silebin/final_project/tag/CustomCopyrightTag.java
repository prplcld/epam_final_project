package by.silebin.final_project.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CustomCopyrightTag extends TagSupport {

    private static final Logger logger = LogManager.getLogger(CustomCopyrightTag.class);

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<footer> &copy Anton Silebin </footer>");
        } catch (IOException e) {
            logger.error("Error while writing to out stream for tag", e);
        }
        return SKIP_BODY;
    }
}
