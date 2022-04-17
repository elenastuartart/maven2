package com.stuart.models.entity;

import com.stuart.dao.записьБД.ЗаписьБД_DAO;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;

public class ЗаписьБД {

    public boolean ПередЗаписью() {
        return false;
    }

    public boolean ДобавитьЗапись_в_БД(SessionFactory factory) {
        ЗаписьБД_DAO ЭлементDAO = new ЗаписьБД_DAO(factory);
        return ЭлементDAO.create(this);
    }

    public int УдалитьЗапись_Из_БД(SessionFactory factory, int code) {
        ЗаписьБД_DAO ЭлементDAO = new ЗаписьБД_DAO(factory);
        try (final Session session = factory.openSession()) {
            session.beginTransaction();
            String sqlDeleteString = "delete ЗаписьНоменклатура where Код = :param";
            int result = session.createQuery(sqlDeleteString).setParameter("param", code).executeUpdate();
            session.getTransaction().commit();
            return result;
        }
    }


    public void ИзменитьЗапись(SessionFactory factory) {

        ЗаписьБД_DAO ЭлементDAO = new ЗаписьБД_DAO(factory);
        try (final Session session = factory.openSession()) {
            session.beginTransaction();
            session.evict(this);

        }
    }

}
