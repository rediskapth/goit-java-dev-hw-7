package ua.goit.controller.skillServlets;

import ua.goit.model.Developer;
import ua.goit.model.Skill;
import ua.goit.service.DeveloperService;
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

@WebServlet("/skill-created")
public class CreateSkillServlet extends HttpServlet {
    private SkillService skillService;
    private DeveloperService developerService;

    @Override
    public void init() throws ServletException {
        skillService = SkillService.getInstance();
        developerService = DeveloperService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String skillLanguage = req.getParameter("skillLanguage");
        String skillSkill = req.getParameter("skillSkill");
        Skill skill = new Skill();
        skill.setLanguage(skillLanguage);
        skill.setSkill(skillSkill);

        if (req.getParameterValues("developerId") != null) {
            Set<Integer> developerIds = Arrays.stream(req.getParameterValues("developerId"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());
            Set<Developer> developers = developerService.findByIds(developerIds);
            skill.setDevelopers(developers);
        }
        skillService.save(skill);
        req.getRequestDispatcher("/WEB-INF/html/skillsJSP/skillCreated.jsp").forward(req, resp);
    }
}
