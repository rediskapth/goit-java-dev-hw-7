package ua.goit.controller.developerServlets;

import ua.goit.model.Project;
import ua.goit.model.Skill;
import ua.goit.service.ProjectService;
import ua.goit.service.SkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/create-developer-form")
public class CreateDeveloperFormServlet extends HttpServlet {
    private ProjectService projectService;
    private SkillService skillService;

    @Override
    public void init() throws ServletException {
        projectService = ProjectService.getInstance();
        skillService = SkillService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Project> projects = projectService.findAll();
        req.setAttribute("projects", projects);
        List<Skill> skills = skillService.findAll();
        req.setAttribute("skills", skills);
        req.getRequestDispatcher("/WEB-INF/html/developersJSP/createDeveloperForm.jsp").forward(req, resp);
    }
}
