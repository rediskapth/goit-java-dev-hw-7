package ua.goit.controller.developerServlets;

import ua.goit.model.Developer;
import ua.goit.model.Project;
import ua.goit.model.Skill;
import ua.goit.service.DeveloperService;
import ua.goit.service.ProjectService;
import ua.goit.service.SkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/developer-created")
public class CreateDeveloperServlet extends HttpServlet {
    private DeveloperService developerService;
    private ProjectService projectService;
    private SkillService skillService;

    @Override
    public void init() throws ServletException {
        developerService = DeveloperService.getInstance();
        projectService = ProjectService.getInstance();
        skillService = SkillService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String developerName = req.getParameter("developerName");
        String developerAge = req.getParameter("developerAge");
        String developerSalary = req.getParameter("developerSalary");
        Developer developer = new Developer();
        developer.setName(developerName);
        developer.setAge(Integer.valueOf(developerAge));
        developer.setSalary(Integer.valueOf(developerSalary));

        if (req.getParameterValues("projectId") != null) {
            Set<Integer> projectIds = Arrays.stream(req.getParameterValues("projectId"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());
            Set<Project> projects = projectService.findByIds(projectIds);
            developer.setProjects(projects);
        }
        if (req.getParameterValues("skillId") != null) {
            Set<Integer> skillIds = Arrays.stream(req.getParameterValues("skillId"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());
            Set<Skill> skills = skillService.findByIds(skillIds);
            developer.setSkills(skills);
        }
        developerService.save(developer);
        req.getRequestDispatcher("/WEB-INF/html/developersJSP/developerCreated.jsp").forward(req, resp);
    }
}
