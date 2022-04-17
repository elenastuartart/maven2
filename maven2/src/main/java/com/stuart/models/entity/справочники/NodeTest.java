package com.stuart.models.entity.справочники;

import com.stuart.models.entity.ЗаписьБД;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@ToString
public class NodeTest extends ЗаписьБД {
    @Id
    @Column(name = "id")
    private Long Key;
    @Column(name = "description")
    private String Desc;

}
