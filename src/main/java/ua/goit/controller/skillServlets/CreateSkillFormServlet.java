package ua.goit.controller.skillServlets;

import ua.goit.model.Developer;
import ua.goit.service.DeveloperService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/create-skill-form")
public class CreateSkillFormServlet extends HttpServlet {
    private DeveloperService developerService;

    @Override
    public void init() throws ServletException {
        developerService = DeveloperService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Developer> developers = developerService.findAll();
        req.setAttribute("developers", developers);
        req.getRequestDispatcher("/WEB-INF/html/skillsJSP/createSkillForm.jsp").forward(req, resp);
    }
}
