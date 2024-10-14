package aiki.sml.imgs;
import code.util.*;
import aiki.db.*;
import aiki.sml.init.*;
public final class LinkImg extends CstIgame{
private LinkImg(){}
public static StringMap<ImageArrayBaseSixtyFour> im(String _base){
StringMap<ImageArrayBaseSixtyFour> i = new StringMap<ImageArrayBaseSixtyFour>(7);
i.addEntry(F_BUILDING_INSIDE_LINK_TXT,ImageArrayBaseSixtyFour.instance(LinkImg0.i0(),_base));
i.addEntry(F_DOWN_CAVE_TXT,ImageArrayBaseSixtyFour.instance(LinkImg0.i1(),_base));
i.addEntry(F_LINK_INSIDE_TXT,ImageArrayBaseSixtyFour.instance(LinkImg0.i2(),_base));
i.addEntry(F_LINK_LEAGUE_LEVELS_TXT,ImageArrayBaseSixtyFour.instance(LinkImg0.i3(),_base));
i.addEntry(F_LINK_OUTSIDE_TXT,ImageArrayBaseSixtyFour.instance(LinkImg0.i4(),_base));
i.addEntry(F_LINK_OUTSIDE_2_TXT,ImageArrayBaseSixtyFour.instance(LinkImg0.i5(),_base));
i.addEntry(F_UP_CAVE_TXT,ImageArrayBaseSixtyFour.instance(LinkImg0.i6(),_base));
return i;
}
}
