package ua.goit.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers_projects")
public class CustomersProjectsDao {
    private Integer customerId;
    private Integer projectId;

    public CustomersProjectsDao(Integer customerId, Integer projectId) {
        this.customerId = customerId;
        this.projectId = projectId;
    }

    public CustomersProjectsDao() {
    }

    @Column(name = "customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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
        return "CustomersProjectsDao{" +
                "customerId=" + customerId +
                ", projectId=" + projectId +
                '}';
    }
}
