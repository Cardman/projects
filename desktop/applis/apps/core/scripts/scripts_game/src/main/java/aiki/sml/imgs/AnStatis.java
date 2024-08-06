package aiki.sml.imgs;
import aiki.db.*;
import code.util.*;
public final class AnStatis{
private AnStatis(){}
public static StringMap<String> im(){
StringMap<String> i = new StringMap<String>(new CollCapacity(8));
i.addEntry(DataBase.DEF_STAT_ACCURACY,AnStatis0.i0());
i.addEntry(DataBase.DEF_STAT_ATTACK,AnStatis0.i1());
i.addEntry(DataBase.DEF_STAT_CRITICAL_HIT,AnStatis0.i2());
i.addEntry(DataBase.DEF_STAT_DEFENSE,AnStatis0.i3());
i.addEntry(DataBase.DEF_STAT_EVASINESS,AnStatis0.i4());
i.addEntry(DataBase.DEF_STAT_SPECIAL_ATTACK,AnStatis0.i5());
i.addEntry(DataBase.DEF_STAT_SPECIAL_DEFENSE,AnStatis0.i6());
i.addEntry(DataBase.DEF_STAT_SPEED,AnStatis0.i7());
return i;
}
}
