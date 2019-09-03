package com.sopen.studentmanagement.repositories.Impl;

import com.sopen.studentmanagement.model.User;
import com.sopen.studentmanagement.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class UserRepositoryImpl implements UserRepository {
  @PersistenceContext
  EntityManager em;

  @Override
  public List<User> findAll() {
    TypedQuery<User> query = em.createQuery("select u from User u", User.class);
    return query.getResultList();
  }

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

  @Override
  public void save(User model) {
    if(model.getId() != null){
      em.merge(model);
    } else {
      em.persist(model);
    }
  }

  @Override
  public void remove(Long id) {
    User user = findById(id);
    if (user!=null){
      em.remove(user);
    }
  }

  @Override
  public User findByUserName(String username) throws UsernameNotFoundException {
    TypedQuery<User> query = em.createQuery("select u from User u where u.username=:username", User.class);
    query.setParameter("username", username);
    try{
      return query.getSingleResult();
    } catch (NoResultException e){
      throw new UsernameNotFoundException("User Not Found with -> username: " + username);
    }
  }
}
