package ua.goit.service;

import ua.goit.dao.CompanyDao;
import ua.goit.dao.ProjectDao;
import ua.goit.model.Company;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompanyService implements CrudService<Company> {
    private static CompanyService companyService;
    private final CompanyDao companyDao;
    private final ProjectDao projectDao;

    private CompanyService() {
        companyDao = CompanyDao.getInstance();
        projectDao = ProjectDao.getInstance();
    }

    @Override
    public void save(Company entity) {
        companyDao.save(entity);
    }

    @Override
    public Company findById(Integer id) {
        return companyDao.findById(id).orElseThrow(() -> new IllegalArgumentException
                (String.format("Company with id %d not found", id)));
    }

    @Override
    public Set<Company> findByIds(Set<Integer> ids) {
        return new HashSet<>(companyDao.findByIds(ids));
    }

    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    @Override
    public void update(Company entity) {
        companyDao.update(entity);
    }

    @Override
    public void remove(Company entity) {
        companyDao.remove(entity);
    }

    public static CompanyService getInstance() {
        if (companyService == null) {
            companyService = new CompanyService();
        }
        return companyService;
    }
}
