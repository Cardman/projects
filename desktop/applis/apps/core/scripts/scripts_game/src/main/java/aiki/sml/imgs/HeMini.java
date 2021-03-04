package aiki.sml.imgs;
import code.util.*;
public final class HeMini{
private HeMini(){}
public static StringMap<String> im(){
StringMap<String> i = new StringMap<String>(new CollCapacity(8));
i.addEntry("ROAD;LEFT;BOY",HeMini0.i0());
i.addEntry("ROAD;RIGHT;GIRL",HeMini0.i1());
i.addEntry("ROAD;DOWN;GIRL",HeMini0.i2());
i.addEntry("ROAD;UP;GIRL",HeMini0.i3());
i.addEntry("ROAD;UP;BOY",HeMini0.i4());
i.addEntry("ROAD;RIGHT;BOY",HeMini0.i5());
i.addEntry("ROAD;LEFT;GIRL",HeMini0.i6());
i.addEntry("ROAD;DOWN;BOY",HeMini0.i7());
return i;
}
}
