package com.stuart.models.entity.справочники;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity

public class ЗаписьКонтрагент extends ЭлементСправочника {

    @Id
    @GeneratedValue

    private UUID ID;

    private Integer Код;

    private String Наименование;
    private String КонтактноеЛицоКА;
    private String АдресКА;
    private String ТипКонтрагента; //поставщик/покупатель
    @OneToMany(mappedBy = "Производитель")
    private Collection<ЗаписьНоменклатура> Номенклатуры;

    @Override
    public boolean ПередЗаписью() {
        if (this.getКод() == null
                || this.getНаименование() == null || this.getКонтактноеЛицоКА() == null
                ||this.getТипКонтрагента()==null)
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "ЗаписьКонтрагент{" +
                "Код=" + Код +
                ", Наименование='" + Наименование + '\'' +
                ", КонтактноеЛицоКА='" + КонтактноеЛицоКА + '\'' +
                ", АдресКА='" + АдресКА + '\'' +
                ", ТипКонтрагента='" + ТипКонтрагента + '\'' +
                '}';
    }

}
