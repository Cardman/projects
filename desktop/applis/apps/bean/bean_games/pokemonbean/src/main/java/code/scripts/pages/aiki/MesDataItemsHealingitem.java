package code.scripts.pages.aiki;
final class MesDataItemsHealingitem{
private static final String C_P_24_0="Here is the table of happiness points depending on the BALL having caught the pokemon to be healed:\n";
private static final String C_P_24_1="BALL having caught\n";
private static final String C_P_24_2="Other used BALL or none\n";
private static final String C_P_24_3="Won happiness points\n";
private static final String C_P_24_4="This object can restore all team while a fight or not.\n";
private static final String C_P_24_5="Voici le tableau des points de bonheur gagnés en fonction de la BALL de capture du pokémon à booster:\n";
private static final String C_P_24_6="BALL de capture\n";
private static final String C_P_24_7="Autre BALL de capture ou aucune\n";
private static final String C_P_24_8="Points de bonheur gagnés\n";
private static final String C_P_24_9="Cet objet permet de soigner toute l''équipe pendant un combat ou non.\n";
private static final String M_P_24_HAPPINESS="happiness";
private static final String M_P_24_HAPPINESS_BALL="happiness_ball";
private static final String M_P_24_HAPPINESS_BOOST="happiness_boost";
private static final String M_P_24_HAPPINESS_OTHER_BALL="happiness_other_ball";
private static final String M_P_24_HEAL_TEAM="heal_team";
private static final char SEP='=';
private MesDataItemsHealingitem(){}
static String en(){
String f=M_P_24_HAPPINESS+SEP+C_P_24_0;
f+=M_P_24_HAPPINESS_BALL+SEP+C_P_24_1;
f+=M_P_24_HAPPINESS_OTHER_BALL+SEP+C_P_24_2;
f+=M_P_24_HAPPINESS_BOOST+SEP+C_P_24_3;
f+=M_P_24_HEAL_TEAM+SEP+C_P_24_4;
return f;
}
static String fr(){
String f=M_P_24_HAPPINESS+SEP+C_P_24_5;
f+=M_P_24_HAPPINESS_BALL+SEP+C_P_24_6;
f+=M_P_24_HAPPINESS_OTHER_BALL+SEP+C_P_24_7;
f+=M_P_24_HAPPINESS_BOOST+SEP+C_P_24_8;
f+=M_P_24_HEAL_TEAM+SEP+C_P_24_9;
return f;
}
}
