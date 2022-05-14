package ua.goit.service;

import ua.goit.dao.DeveloperDao;
import ua.goit.dao.SkillDao;
import ua.goit.model.Skill;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SkillService implements CrudService<Skill> {
    private static SkillService skillService;
    private final SkillDao skillDao;
    private final DeveloperDao developerDao;

    private SkillService() {
        skillDao = SkillDao.getInstance();
        developerDao = DeveloperDao.getInstance();
    }

    @Override
    public void save(Skill entity) {
        skillDao.save(entity);
    }

    @Override
    public Skill findById(Integer id) {
        return skillDao.findById(id).orElseThrow(() -> new IllegalArgumentException
                (String.format("Skill with id %d not found", id)));
    }

    @Override
    public Set<Skill> findByIds(Set<Integer> ids) {
        return new HashSet<>(skillDao.findByIds(ids));
    }

    @Override
    public List<Skill> findAll() {
        return skillDao.findAll();
    }

    @Override
    public void update(Skill entity) {
        skillDao.update(entity);
    }

    @Override
    public void remove(Skill entity) {
        skillDao.remove(entity);
    }

    public static SkillService getInstance() {
        if (skillService == null) {
            skillService = new SkillService();
        }
        return skillService;
    }
}
