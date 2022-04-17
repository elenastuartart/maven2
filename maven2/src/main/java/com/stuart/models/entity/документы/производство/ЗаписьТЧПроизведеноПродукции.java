package com.stuart.models.entity.документы.производство;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.справочники.ЗаписьНоменклатура;
import com.stuart.models.entity.справочники.ЗаписьЭтапыПроизводства;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ЗаписьТЧПроизведеноПродукции extends ЗаписьБД {

    public UUID Код;
    public Integer НомерСтроки;
    public ЗаписьНоменклатура Номенклатура;
    public Double Количество;

    public ЗаписьТЧПроизведеноПродукции(ЗаписьНоменклатура номенклатура, Double количество) {
        Номенклатура = номенклатура;
        Количество = количество;
    }
}
