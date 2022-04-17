package com.stuart.models.entity.документы.продажа;

import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.регистры.ЗаписьРегистраВзаиморасчеты;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import lombok.*;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Реализация extends Документ {

    public UUID Код;
    public Date Дата;
    public Integer Номер;
    public boolean ПометкаПроведения;
    public ЗаписьКонтрагент Контрагент;
    public Double ИтоговаяСумма;


    @Getter
    @Setter
    public ArrayList<ЗаписьТЧСписокТоваров> ТабличнаяЧасть = new ArrayList<>();

    public Реализация(Date дата, Integer номер, ЗаписьКонтрагент контрагент) {
        Дата = дата;
        Номер = номер;
        this.ПометкаПроведения = false;
        Контрагент = контрагент;
    }

    public void ЗаполнитьТЧ(ЗаписьТЧСписокТоваров Запись) {
        if(ТабличнаяЧасть.isEmpty()) {
            Запись.НомерСтроки = 1;
        }
        else {
            Запись.НомерСтроки = ТабличнаяЧасть.size() + 1;
        }
        this.ТабличнаяЧасть.add(Запись); //по мере создания записей добавляем их в табличную часть документа
    }

    public void ПосчитатьИтоговуюСумму() {
        double sum = 0;
        for (int i = 0; i < ТабличнаяЧасть.size(); i++) {
            ЗаписьТЧСписокТоваров запись = ТабличнаяЧасть.get(i);
            sum = sum + запись.Сумма;
        }
        this.ИтоговаяСумма = sum;
    }

    @Override
    public boolean ПередЗаписью() {

        if ((this.getДата() == null || this.getНомер() == null
                || this.getКонтрагент() == null || this.getИтоговаяСумма() == null
                || this.getТабличнаяЧасть() == null))
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "Реализация " + Номер + " от "
                + Дата.toString() + " на сумму " + ИтоговаяСумма
                + " контрагент " + Контрагент.getНаименование() + " проведение " + ПометкаПроведения ;
    }

    public void toStringTable () {
        for (int i = 0; i < this.ТабличнаяЧасть.size(); i++) {
            System.out.println( ТабличнаяЧасть.get(i).НомерСтроки + " " +
                    ТабличнаяЧасть.get(i).Номенклатура.getНаименование() +" " +
                    ТабличнаяЧасть.get(i).Цена + " руб. " +
                    ТабличнаяЧасть.get(i).Количество + " " +
                    ТабличнаяЧасть.get(i).Сумма )  ;
            System.out.println( );
        }
    }

    @Override
    public boolean ЗаписатьТабЧасти(SessionFactory factory) {
        boolean result = true;
        for (int i = 0; i < ТабличнаяЧасть.size(); i ++) {
            var СтрТЧ = ТабличнаяЧасть.get(i);
            if(СтрТЧ.ДобавитьЗапись_в_БД(factory) == false) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean ЗаписатьРегистры(SessionFactory factory) {
        boolean result = true;
        var СтрРегистра = new ЗаписьРегистраВзаиморасчеты() ;
        СтрРегистра.setРегистратор(this);
        СтрРегистра.setДата(this.getДата());
        СтрРегистра.setКонтрагент(this.getКонтрагент());
        СтрРегистра.setСумма((double)0);
        if(СтрРегистра.ДобавитьЗапись_в_БД(factory) == false) {
            result = false;
        }
        return result;
    }
}
