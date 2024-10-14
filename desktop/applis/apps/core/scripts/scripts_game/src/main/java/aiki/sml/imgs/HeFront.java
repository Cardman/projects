package aiki.sml.imgs;
import code.util.*;
import aiki.db.*;
import aiki.sml.init.*;
public final class HeFront extends CstIgame{
private HeFront(){}
public static StringMap<ImageArrayBaseSixtyFour> im(String _base){
StringMap<ImageArrayBaseSixtyFour> i = new StringMap<ImageArrayBaseSixtyFour>(2);
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_GIRL,ImageArrayBaseSixtyFour.instance(HeFront0.i0(),_base));
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_BOY,ImageArrayBaseSixtyFour.instance(HeFront0.i1(),_base));
return i;
}
}
