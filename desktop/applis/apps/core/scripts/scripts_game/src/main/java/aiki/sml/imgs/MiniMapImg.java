package aiki.sml.imgs;
import code.util.*;
import aiki.db.*;
import aiki.sml.init.*;
public final class MiniMapImg extends CstIgame{
private MiniMapImg(){}
public static StringMap<ImageArrayBaseSixtyFour> im(String _base){
StringMap<ImageArrayBaseSixtyFour> i = new StringMap<ImageArrayBaseSixtyFour>(6);
i.addEntry(F_CITY_LOCKED_TXT,ImageArrayBaseSixtyFour.instance(MiniMapImg0.i0(),_base));
i.addEntry(F_CITY_UNLOCKED_TXT,ImageArrayBaseSixtyFour.instance(MiniMapImg0.i1(),_base));
i.addEntry(F_GROUND_TXT,ImageArrayBaseSixtyFour.instance(MiniMapImg0.i2(),_base));
i.addEntry(F_LEAGUE_TXT,ImageArrayBaseSixtyFour.instance(MiniMapImg0.i3(),_base));
i.addEntry(F_ROAD_TXT,ImageArrayBaseSixtyFour.instance(MiniMapImg0.i4(),_base));
i.addEntry(F_ROCK_TXT,ImageArrayBaseSixtyFour.instance(MiniMapImg0.i5(),_base));
return i;
}
}
