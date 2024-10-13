package aiki.sml.imgs;
import code.util.*;
import aiki.sml.init.*;
public final class TypeImg extends CstIgame{
private TypeImg(){}
public static StringMap<String> im(){
StringMap<String> i = new StringMap<String>(18);
i.addEntry(I_ACIER,TypeImg0.i0());
i.addEntry(I_COMBAT,TypeImg0.i1());
i.addEntry(I_DRAGON,TypeImg0.i2());
i.addEntry(I_EAU,TypeImg0.i3());
i.addEntry(I_ELECTRIQUE,TypeImg0.i4());
i.addEntry(I_FEE,TypeImg0.i5());
i.addEntry(I_FEU,TypeImg0.i6());
i.addEntry(I_GLACE,TypeImg0.i7());
i.addEntry(I_INSECTE,TypeImg0.i8());
i.addEntry(I_NORMAL,TypeImg0.i9());
i.addEntry(I_PLANTE,TypeImg0.i10());
i.addEntry(I_POISON,TypeImg0.i11());
i.addEntry(I_PSY,TypeImg0.i12());
i.addEntry(I_ROCHE,TypeImg0.i13());
i.addEntry(I_SOL,TypeImg0.i14());
i.addEntry(I_SPECTRE,TypeImg0.i15());
i.addEntry(I_TENEBRE,TypeImg0.i16());
i.addEntry(I_VOL,TypeImg0.i17());
return i;
}
}
