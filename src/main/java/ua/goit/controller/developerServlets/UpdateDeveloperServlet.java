package ua.goit.controller.developerServlets;

import ua.goit.model.Developer;
import ua.goit.service.DeveloperService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/developer-updated")
public class UpdateDeveloperServlet extends HttpServlet {
    private DeveloperService developerService;

    @Override
    public void init() throws ServletException {
        developerService = DeveloperService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String developerId = req.getParameter("developerId");
        String developerName = req.getParameter("developerName");
        Integer developerAge = Integer.parseInt(req.getParameter("developerAge"));
        Integer developerSalary = Integer.parseInt(req.getParameter("developerSalary"));
        Developer developer = new Developer();
        developer.setId(Integer.parseInt(developerId));
        try {
            developerService.findById(Integer.parseInt(developerId));
        } catch (Exception e) {
            req.setAttribute("exception", "Developer with this id is absent. Please try again.");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        developer.setName(developerName);
        developer.setAge(developerAge);
        developer.setSalary(developerSalary);
        developerService.update(developer);
        req.getRequestDispatcher("/WEB-INF/html/developersJSP/developerUpdated.jsp").forward(req, resp);
    }
}
