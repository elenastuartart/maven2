package com.stuart.dao.записьБД;


import com.stuart.dao.DAO;
import com.stuart.models.entity.ЗаписьБД;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public class ЗаписьБД_DAO implements DAO{
    private final SessionFactory factory;

    public ЗаписьБД_DAO(final SessionFactory factory) {
        this.factory = factory;
    }


    public void save(ЗаписьБД записьБД) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(записьБД);
            session.getTransaction().commit();
        }
    }

    @Override
    public boolean create(ЗаписьБД записьБД) { // запись в БД
        try (final Session session = factory.openSession()) {
            boolean ПроверкаЗаписи = записьБД.ПередЗаписью();
            if (ПроверкаЗаписи==true) {
                session.beginTransaction();
                session.save(записьБД);
                session.getTransaction().commit();
                session.close();
            }
            return true;
        }
        catch(Exception e){
                System.out.println("Заполните все поля!"); //обработать исключение БД и не дать записать в базу
                return false;
        }
    }


    public List findByCode(Integer code) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("select Код from ЗаписьНоменклатура where Код = :paramCode");
            query.setParameter("paramCode", code);
            List list = query.getResultList();
            session.getTransaction().commit();
            return list;
        }
    }


    @Override
    public void update(ЗаписьБД записьБД) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(записьБД);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(ЗаписьБД записьБД) {

    }


    public int deleted(Integer code) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();
            String sqlDeleteString = "delete ЗаписьКонтрагент where Код = :param";
            int result = session.createQuery(sqlDeleteString).setParameter("param", code).executeUpdate();
            session.getTransaction().commit();
            return result;
        }
    }

    //написать универсальный механизм редактирования полей таблицы


}
