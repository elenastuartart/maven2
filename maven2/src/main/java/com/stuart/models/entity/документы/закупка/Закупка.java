package com.stuart.models.entity.документы.закупка;

import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.регистры.ЗаписьРегистраВзаиморасчеты;
import com.stuart.models.entity.справочники.ЗаписьКонтрагент;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Закупка extends Документ {

    public UUID Код;
    public Date Дата;
    public Integer Номер;
    public boolean ПометкаПроведения;
    public ЗаписьКонтрагент Контрагент;
    public Double ИтоговаяСумма;

    public ArrayList<ЗаписьТЧ_Закупка> ТабличнаяЧасть = new ArrayList<>();

    public Закупка(Date дата, Integer номер, ЗаписьКонтрагент контрагент) {
        Дата = дата;
        Номер = номер;
        this.ПометкаПроведения = false;
        Контрагент = контрагент;
    }

    public void ЗаполнитьТЧ(ЗаписьТЧ_Закупка запись) {
//        if(ТабличнаяЧасть.isEmpty()) {
//            запись.НомерСтроки = 1;
//        }
//        else {
//            запись.НомерСтроки = ТабличнаяЧасть.size() + 1;
//        }
        this.ТабличнаяЧасть.add(запись); //по мере создания записей добавляем их в табличную часть документа
    }

    public void ПосчитатьИтоговуюСумму() {
        double sum1 = 0;
        for (int i = 0; i < ТабличнаяЧасть.size(); i++) {
            ЗаписьТЧ_Закупка запись = ТабличнаяЧасть.get(i);
            sum1 = sum1 + запись.Сумма;
        }
        this.ИтоговаяСумма = sum1;
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
        return "Закупка " + Номер + " от "
                + Дата.toString() + " на сумму " + ИтоговаяСумма
                + " контрагент " + Контрагент.getНаименование() + " проведение " + ПометкаПроведения ;
    }

    public void toStringTable () {
        for (int i = 0; i < this.ТабличнаяЧасть.size(); i++) {
            System.out.println(
                    ТабличнаяЧасть.get(i).Номенклатура.getНаименование() +" " +
                    ТабличнаяЧасть.get(i).Цена + " руб. " +
                    ТабличнаяЧасть.get(i).Количество + " " +
                    ТабличнаяЧасть.get(i).Сумма + " ");
            System.out.println( );
        }
    }

    @Override
    public boolean ЗаписатьТабЧасти(SessionFactory factory) {
        boolean result = true;
        for (int i = 0; i < ТабличнаяЧасть.size(); i ++) {
            var СтрТЧ = ТабличнаяЧасть.get(i);
            СтрТЧ.setНомерСтроки(i+1);
            if(СтрТЧ.ДобавитьЗапись_в_БД(factory) == false) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean ЗаписатьРегистры(SessionFactory factory) {
        boolean result = true;
        var СтрРегистра = new ЗаписьРегистраВзаиморасчеты();
        СтрРегистра.setРегистратор(this);
        СтрРегистра.setДата(this.getДата());
        СтрРегистра.setКонтрагент(this.getКонтрагент());
        СтрРегистра.setСумма(this.getИтоговаяСумма());

        if(СтрРегистра.ДобавитьЗапись_в_БД(factory) == false) {
            result = false;
        }
        return result;
    }



}
