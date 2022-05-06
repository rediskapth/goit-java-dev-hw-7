package ua.goit.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "companies_projects")
public class CompaniesProjectsDao {
    private Integer companyId;
    private Integer projectId;

    public CompaniesProjectsDao(Integer companyId, Integer projectId) {
        this.companyId = companyId;
        this.projectId = projectId;
    }

    public CompaniesProjectsDao() {
    }

    @Column(name = "company_id")
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Column(name = "project_id")
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "CompaniesProjectsDao{" +
                "companyId=" + companyId +
                ", projectId=" + projectId +
                '}';
    }
}
