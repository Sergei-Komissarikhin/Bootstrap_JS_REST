package com.sergei.spring.boot.dao;


import com.sergei.spring.boot.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers(){
        return entityManager
                .createQuery("SELECT distinct u FROM User u left join fetch u.roles",User.class).getResultList();
    }

    @Override
    public User getUserById(Long id){
        User user = entityManager.find(User.class,id);
        return user;
    }

    @Override
    public void updateUser(User user){
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User getUserByName(String email){
        return entityManager.createQuery("SELECT u FROM User u " +
                        "INNER JOIN FETCH u.roles " +
                        "WHERE u.email = :email",
                        User.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
