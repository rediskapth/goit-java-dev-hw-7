package ua.goit.controller.developerServlets;

import ua.goit.model.Developer;
import ua.goit.service.DeveloperService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/developer-deleted")
public class DeleteDeveloperServlet extends HttpServlet {
    private DeveloperService developerService;

    @Override
    public void init() throws ServletException {
        developerService = DeveloperService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String developerId = req.getParameter("developerId");
        Developer developer = new Developer();
        developer.setId(Integer.parseInt(developerId));
        try {
            developerService.findById(Integer.parseInt(developerId));
        } catch (Exception e) {
            req.setAttribute("exception", "Developer with this id is absent. Please try again.");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        developerService.remove(developer);
        req.getRequestDispatcher("/WEB-INF/html/developersJSP/developerDeleted.jsp").forward(req, resp);
    }
}
