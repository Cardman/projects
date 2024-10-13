package aiki.sml.imgs;
import code.util.*;
import aiki.sml.init.*;
public final class AnStatus extends CstIgame{
private AnStatus(){}
public static StringMap<String> im(){
StringMap<String> i = new StringMap<String>(13);
i.addEntry(I_AMOUR,AnStatus0.i0());
i.addEntry(I_BRULURE,AnStatus0.i1());
i.addEntry(I_CAUCHEMAR_ST,AnStatus0.i2());
i.addEntry(I_CONFUSION,AnStatus0.i3());
i.addEntry(I_GEL,AnStatus0.i4());
i.addEntry(I_MAUDIT,AnStatus0.i5());
i.addEntry(I_PARALYSIE,AnStatus0.i6());
i.addEntry(I_PEUR,AnStatus0.i7());
i.addEntry(I_POISON_GRAVE,AnStatus0.i8());
i.addEntry(I_SIMPLE_POISON,AnStatus0.i9());
i.addEntry(I_SOMMEIL,AnStatus0.i10());
i.addEntry(I_SOMMEIL_REPOS,AnStatus0.i11());
i.addEntry(I_VAMPIGRAINE_ST,AnStatus0.i12());
return i;
}
}
