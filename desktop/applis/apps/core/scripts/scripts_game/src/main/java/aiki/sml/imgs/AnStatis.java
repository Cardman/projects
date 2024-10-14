package aiki.sml.imgs;
import code.util.*;
import aiki.db.*;
public final class AnStatis{
private AnStatis(){}
public static StringMap<ImageArrayBaseSixtyFour> im(String _base){
StringMap<ImageArrayBaseSixtyFour> i = new StringMap<ImageArrayBaseSixtyFour>(8);
i.addEntry(DataBase.DEF_STAT_ACCURACY,ImageArrayBaseSixtyFour.instance(AnStatis0.i0(),_base));
i.addEntry(DataBase.DEF_STAT_ATTACK,ImageArrayBaseSixtyFour.instance(AnStatis0.i1(),_base));
i.addEntry(DataBase.DEF_STAT_CRITICAL_HIT,ImageArrayBaseSixtyFour.instance(AnStatis0.i2(),_base));
i.addEntry(DataBase.DEF_STAT_DEFENSE,ImageArrayBaseSixtyFour.instance(AnStatis0.i3(),_base));
i.addEntry(DataBase.DEF_STAT_EVASINESS,ImageArrayBaseSixtyFour.instance(AnStatis0.i4(),_base));
i.addEntry(DataBase.DEF_STAT_SPECIAL_ATTACK,ImageArrayBaseSixtyFour.instance(AnStatis0.i5(),_base));
i.addEntry(DataBase.DEF_STAT_SPECIAL_DEFENSE,ImageArrayBaseSixtyFour.instance(AnStatis0.i6(),_base));
i.addEntry(DataBase.DEF_STAT_SPEED,ImageArrayBaseSixtyFour.instance(AnStatis0.i7(),_base));
return i;
}
}
