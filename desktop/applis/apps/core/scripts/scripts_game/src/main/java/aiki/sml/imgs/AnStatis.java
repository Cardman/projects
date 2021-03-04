package aiki.sml.imgs;
import code.util.*;
public final class AnStatis{
private AnStatis(){}
public static StringMap<String> im(){
StringMap<String> i = new StringMap<String>(new CollCapacity(8));
i.addEntry("ACCURACY",AnStatis0.i0());
i.addEntry("ATTACK",AnStatis0.i1());
i.addEntry("CRITICAL_HIT",AnStatis0.i2());
i.addEntry("DEFENSE",AnStatis0.i3());
i.addEntry("EVASINESS",AnStatis0.i4());
i.addEntry("SPECIAL_ATTACK",AnStatis0.i5());
i.addEntry("SPECIAL_DEFENSE",AnStatis0.i6());
i.addEntry("SPEED",AnStatis0.i7());
return i;
}
}
