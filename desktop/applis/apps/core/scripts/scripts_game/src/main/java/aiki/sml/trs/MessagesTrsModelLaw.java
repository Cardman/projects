package aiki.sml.trs;
import aiki.sml.init.*;
import code.sml.util.*;
public final class MessagesTrsModelLaw extends CstIgame{
private MessagesTrsModelLaw(){}
static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(5);
e.add(Cst4.CS_DECROISSANT,"lowing");
e.add(Cst4.CS_CROISSANT,"growing");
e.add(Cst4.CS_CONSTANT_MAX,"max value");
e.add(Cst4.CS_UNIFORME,"uniform");
e.add(Cst4.CS_CONSTANT_MIN,"min value");
return e;
}
static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(5);
f.add(Cst4.CS_DECROISSANT,"décroissant");
f.add(Cst4.CS_CROISSANT,"croissant");
f.add(Cst4.CS_CONSTANT_MAX,"valeur max");
f.add(Cst4.CS_UNIFORME,"uniforme");
f.add(Cst4.CS_CONSTANT_MIN,"valeur min");
return f;
}
}
