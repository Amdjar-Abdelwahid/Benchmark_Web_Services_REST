package com.example.serviceA.dao;

import com.example.serviceA.entities.Category;
import com.example.serviceA.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDAO {

    public List<Category> findAll(int page, int size) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Category> q = session.createQuery("from Category", Category.class);
            if (page >= 0 && size > 0) {
                q.setFirstResult(page * size);
                q.setMaxResults(size);
            }
            return q.list();
        }
    }

    public Category findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Category.class, id);
        }
    }

    public Category save(Category category) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(category);
            tx.commit();
            return category;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Category update(Long id, Category updated) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Category existing = session.get(Category.class, id);
            if (existing == null) return null;
            existing.setCode(updated.getCode());
            existing.setName(updated.getName());
            existing.setUpdatedAt(updated.getUpdatedAt());
            session.merge(existing);
            tx.commit();
            return existing;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public boolean delete(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Category c = session.get(Category.class, id);
            if (c == null) return false;
            session.remove(c);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
