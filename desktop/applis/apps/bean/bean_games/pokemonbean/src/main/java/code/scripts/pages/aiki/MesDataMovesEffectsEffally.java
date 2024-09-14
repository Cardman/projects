package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffally{
private static final String C_P_38_0="The effect lets the partner to get bonuses.\n";
private static final String C_P_38_1="The move lets the partner to multiply damage that the partner by {0}.\n";
private static final String C_P_38_2="L''effet permet au partenaire d''obtenir des bonus.\n";
private static final String C_P_38_3="L''attaque permet au partenaire de multiplier les dégâts qu''il inflige par {0}.\n";
private static final String M_P_38_EFFECT="effect";
private static final String M_P_38_MUL_ALLY_DAMAGE="mul_ally_damage";
private static final char SEP='=';
private MesDataMovesEffectsEffally(){}
static String en(){
String f=M_P_38_EFFECT+SEP+C_P_38_0;
f+=M_P_38_MUL_ALLY_DAMAGE+SEP+C_P_38_1;
return f;
}
static String fr(){
String f=M_P_38_EFFECT+SEP+C_P_38_2;
f+=M_P_38_MUL_ALLY_DAMAGE+SEP+C_P_38_3;
return f;
}
}
