package com.sopen.studentmanagement.repositories.Impl;

import com.sopen.studentmanagement.model.Grade;
import com.sopen.studentmanagement.model.User;
import com.sopen.studentmanagement.repositories.GradeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public class GradeRepositoryImpl implements GradeRepository {
  @PersistenceContext
  EntityManager em;

  @Override
  public Grade findByStudentIdAndSubjectId(Long studentId, Long subjectId) {
    TypedQuery<Grade> query = em.createQuery("select g from Grade g where g.student.id = :studentId and g.subject.id = :subjectId",Grade.class);
    query.setParameter("studentId", studentId);
    query.setParameter("subjectId", subjectId);
    try {
      return query.getSingleResult();
    }catch (NoResultException e){
      return null;
    }
  }

  @Override
  public List<Grade> findAllByStudent(User student) {
    TypedQuery<Grade> query = em.createQuery("select g from Grade g where g.student = :student",Grade.class);
    query.setParameter("student", student);
    return query.getResultList();
  }

  @Override
  public void save(Grade model) {
    if(model.getId() != null){
      em.merge(model);
    } else {
      em.persist(model);
    }
  }
}
