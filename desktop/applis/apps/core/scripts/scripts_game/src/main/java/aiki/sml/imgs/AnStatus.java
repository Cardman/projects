package aiki.sml.imgs;
import code.util.*;
public final class AnStatus{
private AnStatus(){}
public static StringMap<String> im(){
StringMap<String> i = new StringMap<String>(new CollCapacity(13));
i.addEntry("AMOUR",AnStatus0.i0());
i.addEntry("BRULURE",AnStatus0.i1());
i.addEntry("CAUCHEMAR_ST",AnStatus0.i2());
i.addEntry("CONFUSION",AnStatus0.i3());
i.addEntry("GEL",AnStatus0.i4());
i.addEntry("MAUDIT",AnStatus0.i5());
i.addEntry("PARALYSIE",AnStatus0.i6());
i.addEntry("PEUR",AnStatus0.i7());
i.addEntry("POISON_GRAVE",AnStatus0.i8());
i.addEntry("SIMPLE_POISON",AnStatus0.i9());
i.addEntry("SOMMEIL",AnStatus0.i10());
i.addEntry("SOMMEIL_REPOS",AnStatus0.i11());
i.addEntry("VAMPIGRAINE_ST",AnStatus0.i12());
return i;
}
}
