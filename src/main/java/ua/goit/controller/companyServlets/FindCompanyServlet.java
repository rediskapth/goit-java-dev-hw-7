package ua.goit.controller.companyServlets;

import ua.goit.model.Company;
import ua.goit.service.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/find-company")
public class FindCompanyServlet extends HttpServlet {
    private CompanyService companyService;

    @Override
    public void init() throws ServletException {
        companyService = CompanyService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyId = req.getParameter("companyId");
        Company company;
        try {
            company = companyService.findById(Integer.parseInt(companyId));
        } catch (Exception e) {
            req.setAttribute("exception", "Company with this id is absent. Please try again.");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("company", company);
        req.getRequestDispatcher("/WEB-INF/html/companiesJSP/findCompany.jsp").forward(req, resp);
    }
}
