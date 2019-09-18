package com.sopen.studentmanagement.repositories.Impl;

import com.sopen.studentmanagement.model.Subject;
import com.sopen.studentmanagement.repositories.SubjectRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class SubjectRepositoryImpl implements SubjectRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Subject> findAll() {
        TypedQuery<Subject> query = em.createQuery("select s from Subject s", Subject.class);
        return query.getResultList();
    }

    @Override
    public Subject findById(Long id) {
        TypedQuery<Subject> query = em.createQuery("select s from Subject s where s.id=:id", Subject.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void save(Subject model) {
        if(model.getId() != null){
            em.merge(model);
        } else {
            em.persist(model);
        }
    }

    @Override
    public void remove(Long id) {
        Subject subject = findById(id);
        if (subject!=null){
            em.remove(subject);
        }
    }

    @Override
    public Subject findByCode(String code) {
        TypedQuery<Subject> query = em.createQuery("select s from Subject s where s.code=:code", Subject.class);
        query.setParameter("code", code);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
