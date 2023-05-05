package com.example.kata312.dao;

import com.example.kata312.model.User;
import org.springframework.stereotype.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;



@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (getUserById(id) != null) {
            entityManager.remove(getUserById(id));
        }
    }

    @Override
    public void updateUser(User user) {
        if (getUserById(user.getId()) != null) {
            entityManager.merge(user);
        }
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }
}