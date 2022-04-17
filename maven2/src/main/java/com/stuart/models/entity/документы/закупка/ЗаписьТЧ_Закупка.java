package com.stuart.models.entity.документы.закупка;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ЗаписьТЧ_Закупка extends ЗаписьБД {

    public UUID Код;
    public Integer НомерСтроки;
    public ЗаписьНоменклатура Номенклатура;
    public Double Количество;
    public Double Цена;
    public Double Сумма;


    public ЗаписьТЧ_Закупка(ЗаписьНоменклатура номенклатура,
                            Double количество, Double цена) {
        Номенклатура = номенклатура;
        Количество = количество;
        Цена = цена;
        Сумма = цена * количество;
    }

    @Override
    public boolean ПередЗаписью() {
        if (this.getНоменклатура() == null
                || this.getКоличество() == null || this.getЦена() == null
                || this.getСумма() == null )
            return false;
        else
            return true;
    }

}
