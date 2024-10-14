package aiki.sml.imgs;
import code.util.*;
import aiki.db.*;
import aiki.sml.init.*;
public final class PeopleImg extends CstIgame{
private PeopleImg(){}
public static StringMap<ImageArrayBaseSixtyFour> im(String _base){
StringMap<ImageArrayBaseSixtyFour> i = new StringMap<ImageArrayBaseSixtyFour>(13);
i.addEntry(F_ADRIANA_TXT,ImageArrayBaseSixtyFour.instance(PeopleImg0.i0(),_base));
i.addEntry(F_ALISE_TXT,ImageArrayBaseSixtyFour.instance(PeopleImg0.i1(),_base));
i.addEntry(F_BIANCA_TXT,ImageArrayBaseSixtyFour.instance(PeopleImg0.i2(),_base));
i.addEntry(F_BREEDER_F_TXT,ImageArrayBaseSixtyFour.instance(PeopleImg0.i3(),_base));
i.addEntry(F_BREEDER_M_TXT,ImageArrayBaseSixtyFour.instance(PeopleImg0.i4(),_base));
i.addEntry(F_CONSTANT_TXT,ImageArrayBaseSixtyFour.instance(PeopleImg0.i5(),_base));
i.addEntry(F_KARINA_TXT,ImageArrayBaseSixtyFour.instance(PeopleImg0.i7(),_base));
i.addEntry(F_SELLER_F_TXT,ImageArrayBaseSixtyFour.instance(PeopleImg0.i9(),_base));
i.addEntry(F_SELLER_M_TXT,ImageArrayBaseSixtyFour.instance(PeopleImg0.i10(),_base));
i.addEntry(F_STEVE_TXT,ImageArrayBaseSixtyFour.instance(PeopleImg0.i11(),_base));
i.addEntry(F_TCHEREN_TXT,ImageArrayBaseSixtyFour.instance(PeopleImg0.i12(),_base));
i.addEntry(F_TRAINER_F_TXT,ImageArrayBaseSixtyFour.instance(PeopleImg0.i13(),_base));
i.addEntry(F_TRAINER_M_TXT,ImageArrayBaseSixtyFour.instance(PeopleImg0.i14(),_base));
return i;
}
}
