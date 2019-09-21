package com.sopen.studentmanagement.repositories.Impl;

import com.sopen.studentmanagement.model.ConfirmationToken;
import com.sopen.studentmanagement.repositories.ConfirmationTokenRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Transactional
@Repository
public class ConfirmationTokenRepositoryImpl implements ConfirmationTokenRepository {
  @PersistenceContext
  EntityManager em;

  @Override
  public ConfirmationToken findByConfirmationToken(String confirmationToken) {
    TypedQuery<ConfirmationToken> query = em.createQuery("select c from ConfirmationToken c where c.confirmationToken=:confirmationToken", ConfirmationToken.class);
    query.setParameter("confirmationToken", confirmationToken);
    try {
      return query.getSingleResult();
    }catch (NoResultException e){
      return null;
    }
  }

  @Override
  public void save(ConfirmationToken confirmationToken) {
    if(confirmationToken.getId() != null){
      em.merge(confirmationToken);
    } else {
      em.persist(confirmationToken);
    }
  }
}
