package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffclone{
private static final String C_P_40_0="The user uses {0} of its hp maximum.\n";
private static final String C_P_40_10="Le clone ne peut pas être soigné.\n";
private static final String C_P_40_11="Le clone subit les dégâts au lieu de son possesseur sauf pour les dégâts de fin de tour autres que par les attaques suivantes:.\n";
private static final String C_P_40_12="Si le possesseur utilise une des attaques suivantes:\n";
private static final String C_P_40_13="alors le clone est transmis vers le pokémon entrant et ce dernier subit les dégâts des attaques suivantes:\n";
private static final String C_P_40_14="Les dégâts d''un pokémon cloné confus vont vers le pokémon cloné.\n";
private static final String C_P_40_15="Les dégâts de recul d''une attaque d''un pokémon cloné vont vers le pokémon cloné.\n";
private static final String C_P_40_16="Les attaques se basant sur des pv restants se basent sur les pv restants du pokémon cloné.\n";
private static final String C_P_40_17="Si le possesseur du clone tombe KO, alors le clone est détruit.\n";
private static final String C_P_40_1="The clone cannot be healed.\n";
private static final String C_P_40_2="The clone suffers damage instead of its owner except for damage of end of round other by the following moves:\n";
private static final String C_P_40_3="If the owner uses one of the following moves:\n";
private static final String C_P_40_4="then the clone is forwarded to the sent pokemon and this last one suffers damage of the following moves:\n";
private static final String C_P_40_5="Damage against a cloned confused pokemon go to the cloned pokemon.\n";
private static final String C_P_40_6="Recoil damage of a move of a cloned pokemon go to the cloned pokemon.\n";
private static final String C_P_40_7="The moves based on remaining hp based themselves on remaining hp of the cloned pokemon.\n";
private static final String C_P_40_8="If the owner of the clone is knocked out, then the clone is destroyed.\n";
private static final String C_P_40_9="Le lanceur utilise {0} de ses pv max.\n";
private static final String M_P_40_EFFECT="effect";
private static final String M_P_40_EFFECT_2="effect_2";
private static final String M_P_40_EFFECT_3="effect_3";
private static final String M_P_40_EFFECT_4="effect_4";
private static final String M_P_40_EFFECT_5="effect_5";
private static final String M_P_40_EFFECT_6="effect_6";
private static final String M_P_40_EFFECT_7="effect_7";
private static final String M_P_40_EFFECT_8="effect_8";
private static final String M_P_40_EFFECT_9="effect_9";
private static final char SEP='=';
private MesDataMovesEffectsEffclone(){}
static String en(){
String f=M_P_40_EFFECT+SEP+C_P_40_0;
f+=M_P_40_EFFECT_2+SEP+C_P_40_1;
f+=M_P_40_EFFECT_3+SEP+C_P_40_2;
f+=M_P_40_EFFECT_4+SEP+C_P_40_3;
f+=M_P_40_EFFECT_5+SEP+C_P_40_4;
f+=M_P_40_EFFECT_6+SEP+C_P_40_5;
f+=M_P_40_EFFECT_7+SEP+C_P_40_6;
f+=M_P_40_EFFECT_8+SEP+C_P_40_7;
f+=M_P_40_EFFECT_9+SEP+C_P_40_8;
return f;
}
static String fr(){
String f=M_P_40_EFFECT+SEP+C_P_40_9;
f+=M_P_40_EFFECT_2+SEP+C_P_40_10;
f+=M_P_40_EFFECT_3+SEP+C_P_40_11;
f+=M_P_40_EFFECT_4+SEP+C_P_40_12;
f+=M_P_40_EFFECT_5+SEP+C_P_40_13;
f+=M_P_40_EFFECT_6+SEP+C_P_40_14;
f+=M_P_40_EFFECT_7+SEP+C_P_40_15;
f+=M_P_40_EFFECT_8+SEP+C_P_40_16;
f+=M_P_40_EFFECT_9+SEP+C_P_40_17;
return f;
}
}
