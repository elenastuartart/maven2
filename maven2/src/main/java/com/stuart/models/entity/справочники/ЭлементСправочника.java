package com.stuart.models.entity.справочники;

import com.stuart.dao.записьБД.ЗаписьБД_DAO;
import com.stuart.models.entity.ЗаписьБД;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class ЭлементСправочника extends ЗаписьБД {

    @Override
    public boolean ПередЗаписью() {
        return super.ПередЗаписью();
    }

    public boolean ЗаписатьЭлементСправочника(SessionFactory factory) {
        boolean result = true;
        if(this.ПередЗаписью() == false || this.ДобавитьЗапись_в_БД(factory)==false) {
            result = false;
        }
        return  result;
    }

    public void ИзменитьЗаписьСправочника(SessionFactory factory) {
        ЗаписьБД_DAO ЭлементDAO = new ЗаписьБД_DAO(factory);
        try (final Session session = factory.openSession()) {
            session.beginTransaction();
            session.evict(this);
            session.update(this);
            session.getTransaction().commit();
        }
    }


}
