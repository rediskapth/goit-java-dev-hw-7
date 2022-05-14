package ua.goit.service;

import ua.goit.dao.CompanyDao;
import ua.goit.dao.CustomerDao;
import ua.goit.dao.DeveloperDao;
import ua.goit.dao.ProjectDao;
import ua.goit.model.Project;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjectService implements CrudService<Project> {
    private static ProjectService projectService;
    private final ProjectDao projectDao;
    private final DeveloperDao developerDao;
    private final CustomerDao customerDao;
    private final CompanyDao companyDao;

    private ProjectService() {
        projectDao = ProjectDao.getInstance();
        developerDao = DeveloperDao.getInstance();
        customerDao = CustomerDao.getInstance();
        companyDao = CompanyDao.getInstance();
    }

    @Override
    public void save(Project entity) {
        projectDao.save(entity);
    }

    @Override
    public Project findById(Integer id) {
        return projectDao.findById(id).orElseThrow(() -> new IllegalArgumentException
                (String.format("Project with id %d not found", id)));
    }

    @Override
    public Set<Project> findByIds(Set<Integer> ids) {
        return new HashSet<>(projectDao.findByIds(ids));
    }

    @Override
    public List<Project> findAll() {
        return projectDao.findAll();
    }

    @Override
    public void update(Project entity) {
        projectDao.update(entity);
    }

    @Override
    public void remove(Project entity) {
        projectDao.remove(entity);
    }

    public static ProjectService getInstance() {
        if (projectService == null) {
            projectService = new ProjectService();
        }
        return projectService;
    }
}
