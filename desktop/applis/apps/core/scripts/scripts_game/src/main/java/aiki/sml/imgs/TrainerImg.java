package aiki.sml.imgs;
import code.util.*;
import aiki.db.*;
import aiki.sml.init.*;
public final class TrainerImg extends CstIgame{
private TrainerImg(){}
public static StringMap<ImageArrayBaseSixtyFour> im(String _base){
StringMap<ImageArrayBaseSixtyFour> i = new StringMap<ImageArrayBaseSixtyFour>(8);
i.addEntry(F_ADRIANA_TXT,ImageArrayBaseSixtyFour.instance(TrainerImg0.i0(),_base));
i.addEntry(F_ALISE_TXT,ImageArrayBaseSixtyFour.instance(TrainerImg0.i1(),_base));
i.addEntry(F_CONSTANT_TXT,ImageArrayBaseSixtyFour.instance(TrainerImg0.i2(),_base));
i.addEntry(F_COUPLE_1_TXT,ImageArrayBaseSixtyFour.instance(TrainerImg0.i3(),_base));
i.addEntry(F_KARINA_TXT,ImageArrayBaseSixtyFour.instance(TrainerImg0.i4(),_base));
i.addEntry(F_STEVE_TXT,ImageArrayBaseSixtyFour.instance(TrainerImg0.i5(),_base));
i.addEntry(F_TRAINER_F_TXT,ImageArrayBaseSixtyFour.instance(TrainerImg0.i6(),_base));
i.addEntry(F_TRAINER_M_TXT,ImageArrayBaseSixtyFour.instance(TrainerImg0.i7(),_base));
return i;
}
}
