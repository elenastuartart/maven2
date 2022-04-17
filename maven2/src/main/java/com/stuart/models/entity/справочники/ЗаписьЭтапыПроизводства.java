package com.stuart.models.entity.справочники;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@ToString
public class ЗаписьЭтапыПроизводства extends ЭлементСправочника {
    @Id
    @GeneratedValue
    private UUID ID;
    private Integer Код;
    private String Наименование;
    private String ОписаниеЭтапа;

    @Override
    public boolean ПередЗаписью() {
        if ( this.getКод() == null
                    || this.getНаименование() == null
                    || this.getОписаниеЭтапа() == null)
            return false;
        else
            return true;
    }
}

