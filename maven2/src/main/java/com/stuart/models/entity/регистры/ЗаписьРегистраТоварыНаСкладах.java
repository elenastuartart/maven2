package com.stuart.models.entity.регистры;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ЗаписьРегистраТоварыНаСкладах extends ЗаписьРегистра {

    private UUID ID;
    private Документ Регистратор;
    public ЗаписьНоменклатура Номенклатура;
    public Double Количество;
    public Double Сумма;

    @Override
    public boolean ПередЗаписью() {
        if (this.getРегистратор() == null || this.getНоменклатура() == null
                || this.getКоличество() == null
                || this.getСумма() == null)
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "ЗаписьРегистраТоварыНаСкладах{" +
                "Дата: " + Номенклатура.getНаименование().toString() +
                "; Контрагент: " + Количество +
                "; Сумма: " + Сумма +
                '}';
    }

}
