package com.example.springbootdemo.DAO;

import com.example.springbootdemo.model.Role;
import com.example.springbootdemo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RolesDAOImpl implements RolesDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getByName(String name) {
        Query query = entityManager.createQuery("select u from Role u where u.name = :name");
        query.setParameter("name", name);
        return (Role) query.getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select u from Role u", Role.class).getResultList();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
