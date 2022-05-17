package ua.goit.controller.developersServlets;

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
import java.util.List;

@WebServlet("/update-developer-form")
public class UpdateDeveloperFormServlet extends HttpServlet {
    private SkillsService skillsService;

    @Override
    public void init() throws ServletException {
        DatabaseManager dbConnector = new HibernateProvider();
        skillsService = new SkillsService(new SkillsConverter(), new SkillsRepository(dbConnector));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SkillsDto> skills = skillsService.findAll();
        req.setAttribute("skills", skills);
        req.getRequestDispatcher("/WEB-INF/html/developersJSP/updateDeveloperForm.jsp").forward(req, resp);
    }
}
