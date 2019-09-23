package com.sopen.studentmanagement.repositories.Impl;

import com.sopen.studentmanagement.model.Class;
import com.sopen.studentmanagement.repositories.ClassRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ClassRepositoryImpl implements ClassRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Class> findAll() {
        TypedQuery<Class> query = em.createQuery("select c from Class c", Class.class);
        return query.getResultList();
    }

    @Override
    public Class findById(Long id) {
        TypedQuery<Class> query = em.createQuery("select c from Class c where c.id=:id", Class.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void save(Class model) {
        if(model.getId() != null){
            em.merge(model);
        } else {
            em.persist(model);
        }
    }

    @Override
    public void remove(Long id) {
        Class aClass = findById(id);
        if (aClass!=null){
            em.remove(aClass);
        }
    }

    @Override
    public Class findByCode(String code) {
        TypedQuery<Class> query = em.createQuery("select c from Class c where c.code=:code", Class.class);
        query.setParameter("code", code);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Class> findAllBySubjectId(Long subjectId) {
        TypedQuery<Class> query = em.createQuery("select c from Class c where c.subject.id=:subjectId", Class.class);
        query.setParameter("subjectId", subjectId);
        return query.getResultList();
    }
}
