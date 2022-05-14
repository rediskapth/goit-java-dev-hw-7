package ua.goit.controller.companyServlets;

import ua.goit.model.Company;
import ua.goit.service.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/company-deleted")
public class DeleteCompanyServlet extends HttpServlet {
    private CompanyService companyService;

    @Override
    public void init() throws ServletException {
        companyService = CompanyService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyId = req.getParameter("companyId");
        Company company = new Company();
        company.setId(Integer.parseInt(companyId));
        try {
            companyService.findById(Integer.parseInt(companyId));
        } catch (Exception e) {
            req.setAttribute("exception", "Company with this id is absent. Please try again.");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        companyService.remove(company);
        req.getRequestDispatcher("/WEB-INF/html/companiesJSP/companyDeleted.jsp").forward(req, resp);
    }
}
