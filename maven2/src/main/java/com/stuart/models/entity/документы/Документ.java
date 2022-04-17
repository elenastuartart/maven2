package com.stuart.models.entity.документы;

import com.stuart.models.entity.ЗаписьБД;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.SessionFactory;

@Getter
@Setter
public class Документ extends ЗаписьБД {

    public boolean ПометкаПроведения;

    @Override
    public boolean ПередЗаписью() {
        return super.ПередЗаписью();
    }

    public boolean ЗаписатьРегистры(SessionFactory factory) {
        return true;
    }

    public boolean ЗаписатьТабЧасти(SessionFactory factory) {
        return true;
    }

    public boolean Проведение(SessionFactory factory) {

        boolean result = true;
        ПометкаПроведения = true;
        if (ЗаписатьДокумент(factory)==false) {
            result = false;
        }
        return result;
    }

    public boolean ЗаписатьДокумент(SessionFactory factory) {

        boolean result = true;
        if(this.ПередЗаписью() == false || this.ДобавитьЗапись_в_БД(factory)==false) {
            result = false;
        }
        if(result!=false) {
            if(this.ЗаписатьТабЧасти(factory)==false) {
                result = false;
            }
        }
        if (result!=false && ПометкаПроведения==true) {
            if(this.ЗаписатьРегистры(factory)==false) {
                result = false;
            }
        }
        return  result;
    }
}
