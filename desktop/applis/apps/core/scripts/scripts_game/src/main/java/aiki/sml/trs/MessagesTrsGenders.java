package aiki.sml.trs;
import aiki.db.*;
import aiki.sml.init.*;
import code.sml.util.*;
public final class MessagesTrsGenders extends CstIgame{
private MessagesTrsGenders(){}
static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(3);
e.add(DataBase.DEF_GENDER_NO_GENDER,"NO_GENDER");
e.add(DataBase.DEF_GENDER_FEMALE,"FEMALE");
e.add(DataBase.DEF_GENDER_MALE,"MALE");
return e;
}
static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(3);
f.add(DataBase.DEF_GENDER_NO_GENDER,"ASSEXUE");
f.add(DataBase.DEF_GENDER_FEMALE,"FEMELLE");
f.add(DataBase.DEF_GENDER_MALE,"MALE");
return f;
}
}
