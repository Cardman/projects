package code.scripts.pages.aiki;
final class MesDataMovesEffectsEfforder{
private static final String C_P_53_0="This effect changes the order of the fighters.\n";
private static final String C_P_53_1="If the target is not KO, the target uses a move after the user.\n";
private static final String C_P_53_2="If the user is not knocked out after the use of its move, the target lastly uses a move.If it exists some pokemon targetted by this move, they will use a move in the same order than the other one.If the user of this move is knocked out before the target using a move and if the target is not KO, then the effect is stopped.\n";
private static final String C_P_53_3="Cet effet change l''ordre d''attaque des combattants.\n";
private static final String C_P_53_4="Si la cible n''est pas KO, la cible attaque après le lanceur.\n";
private static final String C_P_53_5="Si le lanceur ne tombe pas KO après son attaque, la cible attaque en dernier.S''il existe plusieurs pokémons cibles de cette attaque, il attaqueront dans le même ordre que les autres.Si le lanceur de cette attaque tombe KO avant l''attaque de la cible et si la cible n''est pas KO, alors l''effet est arrêté.\n";
private static final String M_P_53_AFTER_USER="after_user";
private static final String M_P_53_EFFECT="effect";
private static final String M_P_53_LAST="last";
private static final char SEP='=';
private MesDataMovesEffectsEfforder(){}
static String en(){
String f=M_P_53_EFFECT+SEP+C_P_53_0;
f+=M_P_53_AFTER_USER+SEP+C_P_53_1;
f+=M_P_53_LAST+SEP+C_P_53_2;
return f;
}
static String fr(){
String f=M_P_53_EFFECT+SEP+C_P_53_3;
f+=M_P_53_AFTER_USER+SEP+C_P_53_4;
f+=M_P_53_LAST+SEP+C_P_53_5;
return f;
}
}
