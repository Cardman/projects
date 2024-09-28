package aiki.sml.imgs;
import aiki.db.*;
import code.util.*;
public final class HeBack{
private HeBack(){}
public static StringMap<String> im(){
StringMap<String> i = new StringMap<String>(2);
i.addEntry(DataBase.DEF_ENV_ROAD+";"+DataBase.DEF_SEX_GIRL,HeBack0.i0());
i.addEntry(DataBase.DEF_ENV_ROAD+";"+DataBase.DEF_SEX_BOY,HeBack0.i1());
return i;
}
}
