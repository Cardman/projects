package aiki.sml.imgs;
import code.util.*;
import aiki.db.*;
import aiki.sml.init.*;
public final class HeMini extends CstIgame{
private HeMini(){}
public static StringMap<ImageArrayBaseSixtyFour> im(String _base){
StringMap<ImageArrayBaseSixtyFour> i = new StringMap<ImageArrayBaseSixtyFour>(8);
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_DIR_LEFT+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_BOY,ImageArrayBaseSixtyFour.instance(HeMini0.i0(),_base));
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_DIR_RIGHT+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_GIRL,ImageArrayBaseSixtyFour.instance(HeMini0.i1(),_base));
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_DIR_DOWN+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_GIRL,ImageArrayBaseSixtyFour.instance(HeMini0.i2(),_base));
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_DIR_UP+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_GIRL,ImageArrayBaseSixtyFour.instance(HeMini0.i3(),_base));
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_DIR_UP+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_BOY,ImageArrayBaseSixtyFour.instance(HeMini0.i4(),_base));
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_DIR_RIGHT+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_BOY,ImageArrayBaseSixtyFour.instance(HeMini0.i5(),_base));
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_DIR_LEFT+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_GIRL,ImageArrayBaseSixtyFour.instance(HeMini0.i6(),_base));
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_DIR_DOWN+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_BOY,ImageArrayBaseSixtyFour.instance(HeMini0.i7(),_base));
return i;
}
}
