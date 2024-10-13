package aiki.sml.imgs;
import code.util.*;
import aiki.sml.init.*;
public final class LinkImg extends CstIgame{
private LinkImg(){}
public static StringMap<String> im(){
StringMap<String> i = new StringMap<String>(7);
i.addEntry(F_BUILDING_INSIDE_LINK_TXT,LinkImg0.i0());
i.addEntry(F_DOWN_CAVE_TXT,LinkImg0.i1());
i.addEntry(F_LINK_INSIDE_TXT,LinkImg0.i2());
i.addEntry(F_LINK_LEAGUE_LEVELS_TXT,LinkImg0.i3());
i.addEntry(F_LINK_OUTSIDE_TXT,LinkImg0.i4());
i.addEntry(F_LINK_OUTSIDE_2_TXT,LinkImg0.i5());
i.addEntry(F_UP_CAVE_TXT,LinkImg0.i6());
return i;
}
}
