package com.sergei.spring.boot.dao;

import com.sergei.spring.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("DELETE FROM User WHERE id =:id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
