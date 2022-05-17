package ua.goit.controller.developersServlets;

import ua.goit.config.DatabaseManager;
import ua.goit.config.HibernateProvider;
import ua.goit.model.convert.DevelopersConverter;
import ua.goit.model.convert.SkillsConverter;
import ua.goit.model.dto.DevelopersDto;
import ua.goit.model.dto.SkillsDto;
import ua.goit.repository.DevelopersRepository;
import ua.goit.repository.SkillsRepository;
import ua.goit.service.DevelopersService;
import ua.goit.service.SkillsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/developer-updated")
public class UpdateDeveloperServlet extends HttpServlet {
    private DevelopersService developersService;
    private SkillsService skillsService;

    @Override
    public void init() throws ServletException {
        DatabaseManager dbConnector = new HibernateProvider();
        developersService = new DevelopersService(new DevelopersRepository(dbConnector), new DevelopersConverter(new SkillsConverter()));
        skillsService = new SkillsService(new SkillsConverter(), new SkillsRepository(dbConnector));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String developerId = req.getParameter("developerId");
        String developerName = req.getParameter("developerName");
        int developerAge = Integer.parseInt(req.getParameter("developerAge"));
        int developerSalary = Integer.parseInt(req.getParameter("developerSalary"));
        DevelopersDto developersDto = new DevelopersDto();
        developersDto.setId(Integer.parseInt(developerId));
        try {
            developersService.findById(Integer.parseInt(developerId));
        } catch (Exception e) {
            req.setAttribute("exception", "Developer with this id is absent. Please try again.");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        developersDto.setName(developerName);
        developersDto.setAge(developerAge);
        developersDto.setSalary(developerSalary);

        if (req.getParameterValues("skillId") != null) {
            Set<Integer> skillIds = Arrays.stream(req.getParameterValues("skillId"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());
            Set<SkillsDto> skills = skillsService.findByIds(skillIds);
            developersDto.setSkills(skills);
        }
        developersService.update(developersDto);
        req.getRequestDispatcher("/WEB-INF/html/developersJSP/developerUpdated.jsp").forward(req, resp);
    }
}
