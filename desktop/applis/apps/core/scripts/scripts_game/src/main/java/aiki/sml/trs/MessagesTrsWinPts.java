package aiki.sml.trs;
import aiki.sml.init.*;
import code.sml.util.*;
public final class MessagesTrsWinPts extends CstIgame{
private MessagesTrsWinPts(){}
static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(4);
e.add(Cst5.CS_TRES_DIFFICILE,"Very hard");
e.add(Cst5.CS_DIFFICILE,"Hard");
e.add(Cst5.CS_TRES_FACILE,"Very easy");
e.add(Cst5.CS_FACILE,"Easy");
return e;
}
static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(4);
f.add(Cst5.CS_TRES_DIFFICILE,"Très difficile");
f.add(Cst5.CS_DIFFICILE,"Difficile");
f.add(Cst5.CS_TRES_FACILE,"Très facile");
f.add(Cst5.CS_FACILE,"Facile");
return f;
}
}
