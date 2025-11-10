package com.example.serviceA.dao;

import com.example.serviceA.entities.Item;
import com.example.serviceA.entities.Category;
import com.example.serviceA.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ItemDAO {

    public List<Item> findAll(int page, int size) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Item> q = session.createQuery("from Item", Item.class);
            if (page >= 0 && size > 0) {
                q.setFirstResult(page * size);
                q.setMaxResults(size);
            }
            return q.list();
        }
    }

    public Item findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Item.class, id);
        }
    }

    public List<Item> findByCategoryId(Long categoryId, int page, int size) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Item> q = session.createQuery("from Item i where i.category.id = :cid", Item.class);
            q.setParameter("cid", categoryId);
            if (page >= 0 && size > 0) {
                q.setFirstResult(page * size);
                q.setMaxResults(size);
            }
            return q.list();
        }
    }

    public Item save(Item item) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            // If item has category with id only, better to attach managed category
            if (item.getCategory() != null && item.getCategory().getId() != null) {
                Category cat = session.get(Category.class, item.getCategory().getId());
                item.setCategory(cat);
            }
            session.persist(item);
            tx.commit();
            return item;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Item update(Long id, Item updated) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Item existing = session.get(Item.class, id);
            if (existing == null) return null;
            existing.setSku(updated.getSku());
            existing.setName(updated.getName());
            existing.setPrice(updated.getPrice());
            existing.setStock(updated.getStock());
            existing.setUpdatedAt(updated.getUpdatedAt());
            if (updated.getCategory() != null && updated.getCategory().getId() != null) {
                Category cat = session.get(Category.class, updated.getCategory().getId());
                existing.setCategory(cat);
            }
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
            Item it = session.get(Item.class, id);
            if (it == null) return false;
            session.remove(it);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
