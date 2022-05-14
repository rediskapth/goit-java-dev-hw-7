package ua.goit.controller.skillServlets;

import ua.goit.model.Skill;
import ua.goit.service.SkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/skill-deleted")
public class DeleteSkillServlet extends HttpServlet {
    private SkillService skillService;

    @Override
    public void init() throws ServletException {
        skillService = SkillService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String skillId = req.getParameter("skillId");
        Skill skill = new Skill();
        skill.setId(Integer.parseInt(skillId));
        try {
            skillService.findById(Integer.parseInt(skillId));
        } catch (Exception e) {
            req.setAttribute("exception", "Skill with this id is absent. Please try again.");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        skillService.remove(skill);
        req.getRequestDispatcher("/WEB-INF/html/skillsJSP/skillDeleted.jsp").forward(req, resp);
    }
}
