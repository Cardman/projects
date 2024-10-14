package aiki.sml.trs;
import aiki.db.*;
import aiki.sml.init.*;
import code.sml.util.*;
public final class MessagesTrsBooleans extends CstIgame{
private MessagesTrsBooleans(){}
static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(3);
e.add(DataBase.DEF_SEL_BOOL_YES,"YES");
e.add(DataBase.DEF_SEL_BOOL_NO,"NO");
e.add(DataBase.DEF_SEL_BOOL_YES_AND_NO," ");
return e;
}
static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(3);
f.add(DataBase.DEF_SEL_BOOL_YES,"OUI");
f.add(DataBase.DEF_SEL_BOOL_NO,"NON");
f.add(DataBase.DEF_SEL_BOOL_YES_AND_NO," ");
return f;
}
}
