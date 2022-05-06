package ua.goit.controller.skillsServlets;

import ua.goit.config.DatabaseManager;
import ua.goit.config.HibernateProvider;
import ua.goit.model.convert.SkillsConverter;
import ua.goit.model.dto.SkillsDto;
import ua.goit.repository.SkillsRepository;
import ua.goit.service.SkillsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/find-skill")
public class FindSkillServlet extends HttpServlet {
    private SkillsService skillsService;

    @Override
    public void init() throws ServletException {
        DatabaseManager dbConnector = new HibernateProvider();
        skillsService = new SkillsService(new SkillsConverter(), new SkillsRepository(dbConnector));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String skillId = req.getParameter("skillId");
        SkillsDto skillsDto;
        try {
            skillsDto = skillsService.findById(Integer.parseInt(skillId));
        } catch (Exception e) {
            req.setAttribute("exception", "Skill with this id is absent. Please try again.");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("skill", skillsDto);
        req.getRequestDispatcher("/WEB-INF/html/skillsJSP/findSkill.jsp").forward(req, resp);
    }
}
