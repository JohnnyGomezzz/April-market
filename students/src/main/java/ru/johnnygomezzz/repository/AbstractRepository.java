package ru.johnnygomezzz.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.johnnygomezzz.util.SessionFactoryUtil;

import java.io.Serializable;
import java.util.List;

public class AbstractRepository<T, ID extends Serializable> {
    protected Class<T> typeClass;
    protected Class<ID> idClass;

    public AbstractRepository(Class<T> typeClass, Class<ID> idClass) {
        this.typeClass = typeClass;
        this.idClass = idClass;
    }

    public T findById(ID id) {
        return SessionFactoryUtil.getSessionFactory().openSession().get(typeClass, id);
    }

    public List<T> findAll() {
        return (List<T>) SessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("From " + typeClass.getSimpleName()).list();
    }

    public void saveOrUpdate(T obj) {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(obj);
        transaction.commit();
        session.close();
    }
}
