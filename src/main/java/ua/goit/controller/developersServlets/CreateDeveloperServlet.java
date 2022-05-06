package ua.goit.controller.developersServlets;

import ua.goit.config.DatabaseManager;
import ua.goit.config.HibernateProvider;
import ua.goit.model.convert.DevelopersConverter;
import ua.goit.model.dto.DevelopersDto;
import ua.goit.repository.DevelopersRepository;
import ua.goit.service.DevelopersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/developer-created")
public class CreateDeveloperServlet extends HttpServlet {
    private DevelopersService developersService;

    @Override
    public void init() throws ServletException {
        DatabaseManager dbConnector = new HibernateProvider();
        developersService = new DevelopersService(new DevelopersConverter(), new DevelopersRepository(dbConnector));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String developerName = req.getParameter("developerName");
        int developerAge = Integer.parseInt(req.getParameter("developerAge"));
        int developerSalary = Integer.parseInt(req.getParameter("developerSalary"));
        DevelopersDto developersDto = new DevelopersDto();
        developersDto.setName(developerName);
        developersDto.setAge(developerAge);
        developersDto.setSalary(developerSalary);
        developersService.save(developersDto);
        req.getRequestDispatcher("/WEB-INF/html/developersJSP/developerCreated.jsp").forward(req, resp);
    }
}
