package aiki.sml.imgs;
import code.util.*;
public final class LinkImg{
private LinkImg(){}
public static StringMap<String> im(){
StringMap<String> i = new StringMap<String>(new CollCapacity(7));
i.addEntry("building_inside_link.txt",LinkImg0.i0());
i.addEntry("down_cave.txt",LinkImg0.i1());
i.addEntry("link_inside.txt",LinkImg0.i2());
i.addEntry("link_league_levels.txt",LinkImg0.i3());
i.addEntry("link_outside.txt",LinkImg0.i4());
i.addEntry("link_outside_2.txt",LinkImg0.i5());
i.addEntry("up_cave.txt",LinkImg0.i6());
return i;
}
}
