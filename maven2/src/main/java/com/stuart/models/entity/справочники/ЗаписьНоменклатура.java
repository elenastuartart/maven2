package com.stuart.models.entity.справочники;

import com.stuart.dao.записьБД.ЗаписьБД_DAO;
import lombok.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@ToString
public class ЗаписьНоменклатура extends ЭлементСправочника {
    @Id
    @GeneratedValue
    private UUID ID;
    private Integer Код;
    private String Наименование;
    private Integer Артикул;
    private String Категория;
    private String Подкатегория;
    //(optional = false, cascade = CascadeType.)
    @ManyToOne (optional = false, cascade = CascadeType.ALL)
    private ЗаписьКонтрагент Производитель;


    public ЗаписьНоменклатура(List list) {

    }

    public void setПроизводитель(ЗаписьКонтрагент производитель) {
        Производитель = производитель;
    }

    @Override
    public boolean ПередЗаписью() {
        if ( this.getКод() == null
                || this.getНаименование() == null || this.getАртикул() == 0
                || this.getКатегория() == null || this.getПодкатегория()==null)
//                || this.getПроизводитель() == null)
            return false;
        else
            return true;
    }

    public void ИзменитьЗаписьСправочникаНоменклатура(SessionFactory factory, String param) {
        ЗаписьБД_DAO ЭлементDAO = new ЗаписьБД_DAO(factory);
        try (final Session session = factory.openSession()) {
            session.beginTransaction();
            this.setНаименование(param);
            session.update(this);
            session.getTransaction().commit();
        }
    }
}
