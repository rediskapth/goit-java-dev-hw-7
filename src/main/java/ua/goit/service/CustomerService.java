package ua.goit.service;

import ua.goit.dao.CustomerDao;
import ua.goit.dao.ProjectDao;
import ua.goit.model.Customer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerService implements CrudService<Customer> {
    private static CustomerService customerService;
    private final CustomerDao customerDao;
    private final ProjectDao projectDao;

    private CustomerService() {
        customerDao = CustomerDao.getInstance();
        projectDao = ProjectDao.getInstance();
    }

    @Override
    public void save(Customer entity) {
        customerDao.save(entity);
    }

    @Override
    public Customer findById(Integer id) {
        return customerDao.findById(id).orElseThrow(() -> new IllegalArgumentException
                (String.format("Customer with id %d not found", id)));
    }

    @Override
    public Set<Customer> findByIds(Set<Integer> ids) {
        return new HashSet<>(customerDao.findByIds(ids));
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public void update(Customer entity) {
        customerDao.update(entity);
    }

    @Override
    public void remove(Customer entity) {
        customerDao.remove(entity);
    }

    public static CustomerService getInstance() {
        if (customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }
}
