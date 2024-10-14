package aiki.sml.trs;
import aiki.sml.init.*;
import code.sml.util.*;
public final class MessagesTrsTypes extends CstIgame{
private MessagesTrsTypes(){}
static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(18);
e.add(I_INSECTE,"BUG");
e.add(I_PSY,"PSY");
e.add(I_TENEBRE,"DARK");
e.add(I_ACIER,"STEEL");
e.add(I_PLANTE,"GRASS");
e.add(I_EAU,"WATER");
e.add(I_DRAGON,"DRAGON");
e.add(I_FEU,"FIRE");
e.add(I_ROCHE,"ROCK");
e.add(I_SPECTRE,"GHOST");
e.add(I_SOL,"GROUND");
e.add(I_VOL,"FLY");
e.add(I_ELECTRIQUE,"ELECTRIC");
e.add(I_GLACE,"ICE");
e.add(I_NORMAL,"NORMAL");
e.add(I_POISON,"POISON");
e.add(I_FEE,"FAIRY");
e.add(I_COMBAT,"FIGHT");
return e;
}
static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(18);
f.add(I_INSECTE,"INSECTE");
f.add(I_PSY,"PSY");
f.add(I_TENEBRE,"TENEBRE");
f.add(I_ACIER,"ACIER");
f.add(I_PLANTE,"PLANTE");
f.add(I_EAU,"EAU");
f.add(I_DRAGON,"DRAGON");
f.add(I_FEU,"FEU");
f.add(I_ROCHE,"ROCHE");
f.add(I_SPECTRE,"SPECTRE");
f.add(I_SOL,"SOL");
f.add(I_VOL,"VOL");
f.add(I_ELECTRIQUE,"ELECTRIQUE");
f.add(I_GLACE,"GLACE");
f.add(I_NORMAL,"NORMAL");
f.add(I_POISON,"POISON");
f.add(I_FEE,"FEE");
f.add(I_COMBAT,"COMBAT");
return f;
}
}
