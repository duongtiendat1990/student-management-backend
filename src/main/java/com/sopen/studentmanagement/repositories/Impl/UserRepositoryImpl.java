package com.sopen.studentmanagement.repositories.Impl;

import com.sopen.studentmanagement.model.User;
import com.sopen.studentmanagement.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
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

  @Override
  public User findByEmail(String email) {
    TypedQuery<User> query = em.createQuery("select u from User u where u.email=:email", User.class);
    query.setParameter("email", email);
    try{
      return query.getSingleResult();
    } catch (NoResultException e){
     return null;
    }
  }

  @Override
  public User findByPhoneNumber(String phoneNumber) {
    TypedQuery<User> query = em.createQuery("select u from User u where u.phoneNumber=:phoneNumber", User.class);
    query.setParameter("phoneNumber", phoneNumber);
    try{
      return query.getSingleResult();
    } catch (NoResultException e){
      return null;
    }
  }

  @Override
  public User findByEmailIgnoreCase(String email) {
    TypedQuery<User> query = em.createQuery("select u from User u where lower(u.email) like lower(:email)", User.class);
    query.setParameter("email", email);
    try{
      return query.getSingleResult();
    } catch (NoResultException e){
      return null;
    }
  }

  @Override
  public List<User> findAllStudent() {
    Query query = em.createNativeQuery("select u.* from User u inner join user_roles ur on ur.user_id = u.id inner join role r on r.id = ur.role_id where r.name = 'ROLE_STUDENT'", User.class);
    return query.getResultList();
  }

  @Override
  public boolean existsByUsername(String username) {

      try {
        findByUserName(username);
        return true;
      } catch (UsernameNotFoundException e){
        return false;
      }

  }

  @Override
  public boolean existsByEmail(String email) {
    if (findByEmail(email)!=null){
      return true;
    }
    return false;
  }

  @Override
  public boolean existsByPhoneNumber(String phoneNumber) {
    if (findByPhoneNumber(phoneNumber)!=null){
      return true;
    }
    return false;
  }
}
