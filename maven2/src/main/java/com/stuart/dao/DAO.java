package com.stuart.dao;

import com.stuart.models.entity.ЗаписьБД;
import com.stuart.models.entity.документы.Документ;
import com.stuart.models.entity.регистры.ЗаписьРегистра;

import java.util.UUID;

//public interface DAO {
//    public void create(final ЭлементСправочника ЭлементСправочника);
//    public void save(final ЭлементСправочника ЭлементСправочника);
//    public void update (final ЭлементСправочника ЭлементСправочника);
//    public void delete(final ЭлементСправочника ЭлементСправочника);
//    //public ЭлементСправочника read(final int code);
//}

public interface DAO {
    public boolean create(final ЗаписьБД записьБД);

    public void update(ЗаписьБД записьБД);

    public void delete(ЗаписьБД записьБД) ;

    //public ЭлементСправочника read(final int code);
}
