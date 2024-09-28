package aiki.sml.imgs;
import aiki.db.*;
import code.util.*;
public final class HeMini{
private HeMini(){}
public static StringMap<String> im(){
StringMap<String> i = new StringMap<String>(8);
i.addEntry(DataBase.DEF_ENV_ROAD+";"+DataBase.DEF_DIR_LEFT+";"+DataBase.DEF_SEX_BOY,HeMini0.i0());
i.addEntry(DataBase.DEF_ENV_ROAD+";"+DataBase.DEF_DIR_RIGHT+";"+DataBase.DEF_SEX_GIRL,HeMini0.i1());
i.addEntry(DataBase.DEF_ENV_ROAD+";"+DataBase.DEF_DIR_DOWN+";"+DataBase.DEF_SEX_GIRL,HeMini0.i2());
i.addEntry(DataBase.DEF_ENV_ROAD+";"+DataBase.DEF_DIR_UP+";"+DataBase.DEF_SEX_GIRL,HeMini0.i3());
i.addEntry(DataBase.DEF_ENV_ROAD+";"+DataBase.DEF_DIR_UP+";"+DataBase.DEF_SEX_BOY,HeMini0.i4());
i.addEntry(DataBase.DEF_ENV_ROAD+";"+DataBase.DEF_DIR_RIGHT+";"+DataBase.DEF_SEX_BOY,HeMini0.i5());
i.addEntry(DataBase.DEF_ENV_ROAD+";"+DataBase.DEF_DIR_LEFT+";"+DataBase.DEF_SEX_GIRL,HeMini0.i6());
i.addEntry(DataBase.DEF_ENV_ROAD+";"+DataBase.DEF_DIR_DOWN+";"+DataBase.DEF_SEX_BOY,HeMini0.i7());
return i;
}
}
