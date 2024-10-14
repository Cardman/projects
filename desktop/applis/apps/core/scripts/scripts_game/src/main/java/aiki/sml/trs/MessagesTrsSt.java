package aiki.sml.trs;
import aiki.sml.init.*;
import code.sml.util.*;
public final class MessagesTrsSt extends CstIgame{
private MessagesTrsSt(){}
static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(13);
e.add(I_POISON_GRAVE,"BAD_POISON");
e.add(I_PEUR,"FLINCH");
e.add(I_BRULURE,"BURN");
e.add(I_VAMPIGRAINE_ST,"LEECH_SEED");
e.add(I_GEL,"FREEZE");
e.add(I_SIMPLE_POISON,"POISON");
e.add(I_AMOUR,"LOVE");
e.add(I_SOMMEIL,"SLEEP");
e.add(I_SOMMEIL_REPOS,"REST_SLEEP");
e.add(I_MAUDIT,"CURSE");
e.add(I_CAUCHEMAR_ST,"NIGHTMARE");
e.add(I_CONFUSION,"CONFUSING");
e.add(I_PARALYSIE,"PARALYSIS");
return e;
}
static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(13);
f.add(I_POISON_GRAVE,"POISON_GRAVE");
f.add(I_PEUR,"PEUR");
f.add(I_BRULURE,"BRULURE");
f.add(I_VAMPIGRAINE_ST,"VAMPIGRAINE");
f.add(I_GEL,"GEL");
f.add(I_SIMPLE_POISON,"POISON");
f.add(I_AMOUR,"AMOUR");
f.add(I_SOMMEIL,"SOMMEIL");
f.add(I_SOMMEIL_REPOS,"SOMMEIL_REPOS");
f.add(I_MAUDIT,"MAUDIT");
f.add(I_CAUCHEMAR_ST,"CAUCHEMAR");
f.add(I_CONFUSION,"CONFUSION");
f.add(I_PARALYSIE,"PARALYSIE");
return f;
}
}
