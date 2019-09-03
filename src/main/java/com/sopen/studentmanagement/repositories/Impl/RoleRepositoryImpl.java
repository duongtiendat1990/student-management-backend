package com.sopen.studentmanagement.repositories.Impl;

import com.sopen.studentmanagement.model.Role;
import com.sopen.studentmanagement.model.RoleName;
import com.sopen.studentmanagement.repositories.RoleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
@Transactional
public class RoleRepositoryImpl implements RoleRepository {

  @PersistenceContext
  EntityManager em;
  @Override
  public Role findByName(RoleName name) {
    TypedQuery<Role> query = em.createQuery("select u from Role u where u.name=:name", Role.class);
    query.setParameter("name", name);
    try {
      return query.getSingleResult();
    } catch (NoResultException e){
      return null;
    }
  }

  @Override
  public void save(Role role) {
    if(role.getId() != null){
      em.merge(role);
    } else {
      em.persist(role);
    }
  }
}
