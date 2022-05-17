package ua.goit.model.dao;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "skills")
public class SkillsDao {
    private Integer id;
    private String language;
    private String skill;
    private Set<DevelopersDao> developers;

    public SkillsDao(Integer id, String language, String skill) {
        this.id = id;
        this.language = language;
        this.skill = skill;
    }

    public SkillsDao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Column(name = "skill")
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @ManyToMany(mappedBy = "skills")
    public void setDevelopers(Set<DevelopersDao> developers) {
        this.developers = developers;
    }

    @Override
    public String toString() {
        return "SkillsDao{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }
}
