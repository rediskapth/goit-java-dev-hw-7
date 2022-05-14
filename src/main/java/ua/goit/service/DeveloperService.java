package ua.goit.service;

import ua.goit.dao.DeveloperDao;
import ua.goit.dao.ProjectDao;
import ua.goit.dao.SkillDao;
import ua.goit.model.Developer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeveloperService implements CrudService<Developer> {
    private static DeveloperService developerService;
    private final DeveloperDao developerDao;
    private final ProjectDao projectDao;
    private final SkillDao skillDao;

    private DeveloperService() {
        developerDao = DeveloperDao.getInstance();
        projectDao = ProjectDao.getInstance();
        skillDao = SkillDao.getInstance();
    }

    @Override
    public void save(Developer entity) {
        developerDao.save(entity);
    }

    @Override
    public Developer findById(Integer id) {
        return developerDao.findById(id).orElseThrow(() -> new IllegalArgumentException
                (String.format("Developer with id %d not found", id)));
    }

    @Override
    public Set<Developer> findByIds(Set<Integer> ids) {
        return new HashSet<>(developerDao.findByIds(ids));
    }

    @Override
    public List<Developer> findAll() {
        return developerDao.findAll();
    }

    @Override
    public void update(Developer entity) {
        developerDao.update(entity);
    }

    @Override
    public void remove(Developer entity) {
        developerDao.remove(entity);
    }

    public static DeveloperService getInstance() {
        if (developerService == null) {
            developerService = new DeveloperService();
        }
        return developerService;
    }
}
