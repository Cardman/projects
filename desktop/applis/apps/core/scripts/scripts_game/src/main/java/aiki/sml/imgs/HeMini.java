package aiki.sml.imgs;
import aiki.db.*;
import aiki.sml.init.*;
import code.util.*;
public final class HeMini extends CstIgame{
private HeMini(){}
public static StringMap<String> im(){
StringMap<String> i = new StringMap<String>(8);
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_DIR_LEFT+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_BOY,HeMini0.i0());
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_DIR_RIGHT+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_GIRL,HeMini0.i1());
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_DIR_DOWN+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_GIRL,HeMini0.i2());
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_DIR_UP+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_GIRL,HeMini0.i3());
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_DIR_UP+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_BOY,HeMini0.i4());
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_DIR_RIGHT+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_BOY,HeMini0.i5());
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_DIR_LEFT+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_GIRL,HeMini0.i6());
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_DIR_DOWN+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_BOY,HeMini0.i7());
return i;
}
}
