package ua.goit.controller.companyServlets;

import ua.goit.model.Company;
import ua.goit.service.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/company-updated")
public class UpdateCompanyServlet extends HttpServlet {
    private CompanyService companyService;

    @Override
    public void init() throws ServletException {
        companyService = CompanyService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyId = req.getParameter("companyId");
        String companyName = req.getParameter("companyName");
        String companyLocation = req.getParameter("companyLocation");
        Company company = new Company();
        company.setId(Integer.parseInt(companyId));
        try {
            companyService.findById(Integer.parseInt(companyId));
        } catch (Exception e) {
            req.setAttribute("exception", "Company with this id is absent. Please try again.");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        company.setName(companyName);
        company.setLocation(companyLocation);
        companyService.update(company);
        req.getRequestDispatcher("/WEB-INF/html/companiesJSP/companyUpdated.jsp").forward(req, resp);
    }
}
