package ua.goit.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "skills")
public class Skill {
    private Integer id;
    private String language;
    private String skill;
    private Set<Developer> developers;

    public Skill() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "developers_skills",
            joinColumns = {@JoinColumn(name = "skill_id")},
            inverseJoinColumns = {@JoinColumn(name = "developer_id")}
    )
    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }
}
