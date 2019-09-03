package com.sopen.studentmanagement.repositories.Impl;

import com.sopen.studentmanagement.model.User;
import com.sopen.studentmanagement.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Transactional
@Repository
public class UserRepositoryImpl implements UserRepository {
  @PersistenceContext
  EntityManager em;
  @Override
  public User findById(Long id) {
    TypedQuery<User> query = em.createQuery("select u from User u where u.id=:id", User.class);
    query.setParameter("id", id);
    try {
      return query.getSingleResult();
    }catch (NoResultException e){
      return null;
    }
  }
}
