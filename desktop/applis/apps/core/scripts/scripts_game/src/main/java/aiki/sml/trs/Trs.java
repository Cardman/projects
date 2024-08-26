package aiki.sml.trs;
import code.util.*;
public final class Trs{
public static final String EN = "en";
public static final String FR = "fr";
private Trs(){}
public static StringMap<String> tr(){
StringMap<String> i = new StringMap<String>(new CollCapacity(34));
i.addEntry(EN+"/abilities.txt",Trs0.tr());
i.addEntry(EN+"/booleans.txt",Trs1.tr());
i.addEntry(EN+"/categories.txt",Trs2.tr());
i.addEntry(EN+"/classes.txt",Trs3.tr());
i.addEntry(EN+"/environments.txt",Trs4.tr());
i.addEntry(EN+"/genders.txt",Trs5.tr());
i.addEntry(EN+"/items.txt",Trs6.tr());
i.addEntry(EN+"/litteral.txt",Trs7.tr());
i.addEntry(EN+"/math.txt",Trs8.tr());
i.addEntry(EN+"/modellaw.txt",Trs9.tr());
i.addEntry(EN+"/moves.txt",Trs10.tr());
i.addEntry(EN+"/pokemon.txt",Trs11.tr());
i.addEntry(EN+"/statistics.txt",Trs12.tr());
i.addEntry(EN+"/status.txt",Trs13.tr());
i.addEntry(EN+"/targets.txt",Trs14.tr());
i.addEntry(EN+"/types.txt",Trs15.tr());
i.addEntry(EN+"/winpts.txt",Trs16.tr());
i.addEntry(FR+"/abilities.txt",Trs17.tr());
i.addEntry(FR+"/booleans.txt",Trs18.tr());
i.addEntry(FR+"/categories.txt",Trs19.tr());
i.addEntry(FR+"/classes.txt",Trs20.tr());
i.addEntry(FR+"/environments.txt",Trs21.tr());
i.addEntry(FR+"/genders.txt",Trs22.tr());
i.addEntry(FR+"/items.txt",Trs23.tr());
i.addEntry(FR+"/litteral.txt",Trs24.tr());
i.addEntry(FR+"/math.txt",Trs25.tr());
i.addEntry(FR+"/modellaw.txt",Trs26.tr());
i.addEntry(FR+"/moves.txt",Trs27.tr());
i.addEntry(FR+"/pokemon.txt",Trs28.tr());
i.addEntry(FR+"/statistics.txt",Trs29.tr());
i.addEntry(FR+"/status.txt",Trs30.tr());
i.addEntry(FR+"/targets.txt",Trs31.tr());
i.addEntry(FR+"/types.txt",Trs32.tr());
i.addEntry(FR+"/winpts.txt",Trs33.tr());
return i;
}
}
