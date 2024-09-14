package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffswitchabilities{
private static final String C_P_60_0="This effect changes an ability at least.\n";
private static final String C_P_60_10="La cible prend la capacité du lanceur.Si la capacité du lanceur était sans effet, alors la capacité de la cible sera sans effet.\n";
private static final String C_P_60_11="Le lanceur et la cible s''échangent les capacités.Si la capacité du lanceur était sans effet, alors la capacité de la cible sera sans effet.Si la capacité de la cible était sans effet, alors la capacité du lanceur sera sans effet.\n";
private static final String C_P_60_1="The target takes the ability:\n";
private static final String C_P_60_2="The ability of the target has not effect anymore.\n";
private static final String C_P_60_3="The user takes the ability of the target.If the ability of the target was without effect, then the ability of the user will be without effect.\n";
private static final String C_P_60_4="The target takes the ability of the user.If the ability of the user was without effect, then the ability of the target will be without effect.\n";
private static final String C_P_60_5="The user and the target switch one each other the abilities.If the ability of the user was without effect, then the ability of the target will be without effect.If the ability of the target was without effect, then the ability of the user will be without effect.\n";
private static final String C_P_60_6="Cet effet change au moins une capacité.\n";
private static final String C_P_60_7="La cible prend la capacité:\n";
private static final String C_P_60_8="La capacité de la cible n''a plus d''effet.\n";
private static final String C_P_60_9="Le lanceur prend la capacité de la cible.Si la capacité de la cible était sans effet, alors la capacité du lanceur sera sans effet.\n";
private static final String M_P_60_EFFECT="effect";
private static final String M_P_60_GIVE_CONST="give_const";
private static final String M_P_60_GIVE_CONST_EMPTY="give_const_empty";
private static final String M_P_60_GIVE_TO_TARGET="give_to_target";
private static final String M_P_60_GIVE_TO_USER="give_to_user";
private static final String M_P_60_SWICTH_ABILITIES="swicth_abilities";
private static final char SEP='=';
private MesDataMovesEffectsEffswitchabilities(){}
static String en(){
String f=M_P_60_EFFECT+SEP+C_P_60_0;
f+=M_P_60_GIVE_CONST+SEP+C_P_60_1;
f+=M_P_60_GIVE_CONST_EMPTY+SEP+C_P_60_2;
f+=M_P_60_GIVE_TO_USER+SEP+C_P_60_3;
f+=M_P_60_GIVE_TO_TARGET+SEP+C_P_60_4;
f+=M_P_60_SWICTH_ABILITIES+SEP+C_P_60_5;
return f;
}
static String fr(){
String f=M_P_60_EFFECT+SEP+C_P_60_6;
f+=M_P_60_GIVE_CONST+SEP+C_P_60_7;
f+=M_P_60_GIVE_CONST_EMPTY+SEP+C_P_60_8;
f+=M_P_60_GIVE_TO_USER+SEP+C_P_60_9;
f+=M_P_60_GIVE_TO_TARGET+SEP+C_P_60_10;
f+=M_P_60_SWICTH_ABILITIES+SEP+C_P_60_11;
return f;
}
}
