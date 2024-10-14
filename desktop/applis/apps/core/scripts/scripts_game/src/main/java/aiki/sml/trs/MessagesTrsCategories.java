package aiki.sml.trs;
import aiki.sml.init.*;
import code.sml.util.*;
public final class MessagesTrsCategories extends CstIgame{
private MessagesTrsCategories(){}
static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(3);
e.add(I_AUTRE,"OTHER");
e.add(I_SPECIALE,"SPECIAL");
e.add(I_PHYSIQUE,"PHYSICAL");
return e;
}
static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(3);
f.add(I_AUTRE,"AUTRE");
f.add(I_SPECIALE,"SPECIALE");
f.add(I_PHYSIQUE,"PHYSIQUE");
return f;
}
}
