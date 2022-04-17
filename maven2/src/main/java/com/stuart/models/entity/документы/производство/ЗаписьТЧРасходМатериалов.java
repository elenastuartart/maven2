package com.stuart.models.entity.документы.производство;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.models.entity.справочники.ЗаписьЭтапыПроизводства;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ЗаписьТЧРасходМатериалов extends ЗаписьБД {

    public UUID Код;
    public Integer НомерСтроки;
    public ЗаписьЭтапыПроизводства Этап;
    public ЗаписьНоменклатура Номенклатура;
    public Double Количество;



    public ЗаписьТЧРасходМатериалов(ЗаписьЭтапыПроизводства этап,
                          ЗаписьНоменклатура номенклатура, Double количество) {
        this.Этап = этап;
        this.Номенклатура = номенклатура;
        this.Количество = количество;
    }

}
