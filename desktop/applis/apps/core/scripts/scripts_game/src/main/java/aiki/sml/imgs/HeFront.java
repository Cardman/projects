package aiki.sml.imgs;
import aiki.db.*;
import aiki.sml.init.*;
import code.util.*;
public final class HeFront extends CstIgame{
private HeFront(){}
public static StringMap<String> im(){
StringMap<String> i = new StringMap<String>(2);
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_GIRL,HeFront0.i0());
i.addEntry(DataBase.DEF_ENV_ROAD+SEPARATOR_KEY_HEROS+DataBase.DEF_SEX_BOY,HeFront0.i1());
return i;
}
}
