package aiki.sml.imgs;
import code.util.*;
import aiki.db.*;
import aiki.sml.init.*;
public final class AnStatus extends CstIgame{
private AnStatus(){}
public static StringMap<ImageArrayBaseSixtyFour> im(String _base){
StringMap<ImageArrayBaseSixtyFour> i = new StringMap<ImageArrayBaseSixtyFour>(13);
i.addEntry(I_AMOUR,ImageArrayBaseSixtyFour.instance(AnStatus0.i0(),_base));
i.addEntry(I_BRULURE,ImageArrayBaseSixtyFour.instance(AnStatus0.i1(),_base));
i.addEntry(I_CAUCHEMAR_ST,ImageArrayBaseSixtyFour.instance(AnStatus0.i2(),_base));
i.addEntry(I_CONFUSION,ImageArrayBaseSixtyFour.instance(AnStatus0.i3(),_base));
i.addEntry(I_GEL,ImageArrayBaseSixtyFour.instance(AnStatus0.i4(),_base));
i.addEntry(I_MAUDIT,ImageArrayBaseSixtyFour.instance(AnStatus0.i5(),_base));
i.addEntry(I_PARALYSIE,ImageArrayBaseSixtyFour.instance(AnStatus0.i6(),_base));
i.addEntry(I_PEUR,ImageArrayBaseSixtyFour.instance(AnStatus0.i7(),_base));
i.addEntry(I_POISON_GRAVE,ImageArrayBaseSixtyFour.instance(AnStatus0.i8(),_base));
i.addEntry(I_SIMPLE_POISON,ImageArrayBaseSixtyFour.instance(AnStatus0.i9(),_base));
i.addEntry(I_SOMMEIL,ImageArrayBaseSixtyFour.instance(AnStatus0.i10(),_base));
i.addEntry(I_SOMMEIL_REPOS,ImageArrayBaseSixtyFour.instance(AnStatus0.i11(),_base));
i.addEntry(I_VAMPIGRAINE_ST,ImageArrayBaseSixtyFour.instance(AnStatus0.i12(),_base));
return i;
}
}
