package aiki.sml.imgs;
import code.util.*;
import aiki.db.*;
import aiki.sml.init.*;
public final class TypeImg extends CstIgame{
private TypeImg(){}
public static StringMap<ImageArrayBaseSixtyFour> im(String _base){
StringMap<ImageArrayBaseSixtyFour> i = new StringMap<ImageArrayBaseSixtyFour>(18);
i.addEntry(I_ACIER,ImageArrayBaseSixtyFour.instance(TypeImg0.i0(),_base));
i.addEntry(I_COMBAT,ImageArrayBaseSixtyFour.instance(TypeImg0.i1(),_base));
i.addEntry(I_DRAGON,ImageArrayBaseSixtyFour.instance(TypeImg0.i2(),_base));
i.addEntry(I_EAU,ImageArrayBaseSixtyFour.instance(TypeImg0.i3(),_base));
i.addEntry(I_ELECTRIQUE,ImageArrayBaseSixtyFour.instance(TypeImg0.i4(),_base));
i.addEntry(I_FEE,ImageArrayBaseSixtyFour.instance(TypeImg0.i5(),_base));
i.addEntry(I_FEU,ImageArrayBaseSixtyFour.instance(TypeImg0.i6(),_base));
i.addEntry(I_GLACE,ImageArrayBaseSixtyFour.instance(TypeImg0.i7(),_base));
i.addEntry(I_INSECTE,ImageArrayBaseSixtyFour.instance(TypeImg0.i8(),_base));
i.addEntry(I_NORMAL,ImageArrayBaseSixtyFour.instance(TypeImg0.i9(),_base));
i.addEntry(I_PLANTE,ImageArrayBaseSixtyFour.instance(TypeImg0.i10(),_base));
i.addEntry(I_POISON,ImageArrayBaseSixtyFour.instance(TypeImg0.i11(),_base));
i.addEntry(I_PSY,ImageArrayBaseSixtyFour.instance(TypeImg0.i12(),_base));
i.addEntry(I_ROCHE,ImageArrayBaseSixtyFour.instance(TypeImg0.i13(),_base));
i.addEntry(I_SOL,ImageArrayBaseSixtyFour.instance(TypeImg0.i14(),_base));
i.addEntry(I_SPECTRE,ImageArrayBaseSixtyFour.instance(TypeImg0.i15(),_base));
i.addEntry(I_TENEBRE,ImageArrayBaseSixtyFour.instance(TypeImg0.i16(),_base));
i.addEntry(I_VOL,ImageArrayBaseSixtyFour.instance(TypeImg0.i17(),_base));
return i;
}
}
