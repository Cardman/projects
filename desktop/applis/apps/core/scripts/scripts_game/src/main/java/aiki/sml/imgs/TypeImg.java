package aiki.sml.imgs;
import code.util.*;
public final class TypeImg{
private TypeImg(){}
public static StringMap<String> im(){
StringMap<String> i = new StringMap<String>(new CollCapacity(18));
i.addEntry("ACIER",TypeImg0.i0());
i.addEntry("COMBAT",TypeImg0.i1());
i.addEntry("DRAGON",TypeImg0.i2());
i.addEntry("EAU",TypeImg0.i3());
i.addEntry("ELECTRIQUE",TypeImg0.i4());
i.addEntry("FEE",TypeImg0.i5());
i.addEntry("FEU",TypeImg0.i6());
i.addEntry("GLACE",TypeImg0.i7());
i.addEntry("INSECTE",TypeImg0.i8());
i.addEntry("NORMAL",TypeImg0.i9());
i.addEntry("PLANTE",TypeImg0.i10());
i.addEntry("POISON",TypeImg0.i11());
i.addEntry("PSY",TypeImg0.i12());
i.addEntry("ROCHE",TypeImg0.i13());
i.addEntry("SOL",TypeImg0.i14());
i.addEntry("SPECTRE",TypeImg0.i15());
i.addEntry("TENEBRE",TypeImg0.i16());
i.addEntry("VOL",TypeImg0.i17());
return i;
}
}
