package com.stuart.application;

import com.stuart.dao.записьБД.ЗаписьБД_DAO;
import com.stuart.models.entity.документы.продажа.ЗаписьТЧСписокТоваров;
import com.stuart.models.entity.документы.продажа.Реализация;
import com.stuart.models.entity.справочники.NodeTest;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.LockModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class HelloApplication {

    public static void main(String[] args) {
        SessionFactory factory = null;

        try {
            factory = new Configuration().configure().buildSessionFactory();
            final Session session = factory.openSession();

            ЗаписьБД_DAO записьБД_dao = new ЗаписьБД_DAO(factory);


//            final ЗаписьКонтрагент КА1 = new ЗаписьКонтрагент();
//            КА1.setКод(10103);
//            КА1.setАдресКА("Москва");
//            КА1.setКонтактноеЛицоКА("aaa2@mail.ru");
//            КА1.setНаименование("Fashion house");
//            КА1.setТипКонтрагента("покупатель");
//            КА1.ЗаписатьЭлементСправочника(factory);

//
//            final ЗаписьНоменклатура Ном1 = new ЗаписьНоменклатура();
//            Ном1.setНаименование("Молд Агнесс");
//            Ном1.setКод(10201);
//            Ном1.setАртикул(500102);
//            Ном1.setКатегория("Товары на реализацию");
//            Ном1.setПодкатегория("Кукла бланк");
//            Ном1.setПроизводитель(КА1);


//            session.getTransaction().rollback();
//

            Query<ЗаписьКонтрагент> query = session.createQuery("from ЗаписьКонтрагент node1 where node1.Код = :text");
//            query.setLockMode(LockModeType.PESSIMISTIC_READ);
            query.setParameter("text", 10101);
            ЗаписьКонтрагент записьКонтрагент = query.uniqueResult();
//            записьКонтрагент.setНаименование("измененно");
//            session.update(записьКонтрагент);
//            session.lock(записьКонтрагент, LockModeType.PESSIMISTIC_WRITE);
            session.beginTransaction();
            записьКонтрагент.setНаименование("Поле изменено");
            session.merge(записьКонтрагент);
            session.getTransaction().commit();
            session.close();

//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaUpdate<ЗаписьКонтрагент> criteriaUpdate = cb.createCriteriaUpdate(ЗаписьКонтрагент.class);
//            Root<ЗаписьКонтрагент> root = criteriaUpdate.from(ЗаписьКонтрагент.class);
//
//            criteriaUpdate.set("Наименование", "Поле изменено!");
//
//            criteriaUpdate.where(cb.equal(root.get("ID"), записьКонтрагент.getID()));
//
//            Transaction transaction = session.beginTransaction();
//            session.createQuery(criteriaUpdate).executeUpdate();
//            transaction.commit();
//            session.close();








            //ЗаписьНоменклатура Ном2 = session.get(ЗаписьНоменклатура.class,);
//            Ном1.ЗаписатьЭлементСправочника(factory);

            //-+++Ном1.ИзменитьЗаписьСправочникаНоменклатура(factory, "Карина");

//            Ном1.ЗаписатьСправочник(factory);
//            int res = Ном1.УдалитьЗапись_Из_БД(factory, Ном1.getКод());
//            System.out.println(res);

//            Реализация док1 = new Реализация(new Date(), 12231234, КА1);
//            ЗаписьТЧСписокТоваров запись1 = new ЗаписьТЧСписокТоваров(Ном1, (double)2, (double)200);
//            ЗаписьТЧСписокТоваров запись2 = new ЗаписьТЧСписокТоваров( Ном1, (double)4, (double)50);
//            ЗаписьТЧСписокТоваров запись3 = new ЗаписьТЧСписокТоваров( Ном1, (double)3, (double)30);
////
//            док1.ЗаполнитьТЧ(запись1);
//            док1.ЗаполнитьТЧ(запись2);
//            док1.ЗаполнитьТЧ(запись3);
//            док1.ПосчитатьИтоговуюСумму();

//            System.out.println(док1.toString());
//            док1.toStringTable();
////
//            ЗаписьРегистраВзаиморасчеты взаиморасчеты1 = new ЗаписьРегистраВзаиморасчеты();
//            System.out.println(взаиморасчеты1.toString());
//
//            ЭлементDAO.create(док1);
//            boolean проверка = взаиморасчеты1.ПередЗаписью();
//            System.out.println(проверка);
       }
        finally {
            if (factory != null) {
                factory.close();
            }
        }
    }
}
