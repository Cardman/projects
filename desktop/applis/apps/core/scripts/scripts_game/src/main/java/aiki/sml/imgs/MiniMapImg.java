package aiki.sml.imgs;
import code.util.*;
import aiki.sml.init.*;
public final class MiniMapImg extends CstIgame{
private MiniMapImg(){}
public static StringMap<String> im(){
StringMap<String> i = new StringMap<String>(6);
i.addEntry(F_CITY_LOCKED_TXT,MiniMapImg0.i0());
i.addEntry(F_CITY_UNLOCKED_TXT,MiniMapImg0.i1());
i.addEntry(F_GROUND_TXT,MiniMapImg0.i2());
i.addEntry(F_LEAGUE_TXT,MiniMapImg0.i3());
i.addEntry(F_ROAD_TXT,MiniMapImg0.i4());
i.addEntry(F_ROCK_TXT,MiniMapImg0.i5());
return i;
}
}
