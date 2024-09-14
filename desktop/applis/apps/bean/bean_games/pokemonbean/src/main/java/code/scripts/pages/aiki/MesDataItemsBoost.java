package code.scripts.pages.aiki;
final class MesDataItemsBoost{
private static final String C_P_18_0="Used on a move, this move wins {0} pp.\n";
private static final String C_P_18_10="BALL de capture\n";
private static final String C_P_18_11="Autre BALL de capture ou aucune\n";
private static final String C_P_18_12="Points de bonheur gagnés\n";
private static final String C_P_18_13="Voici le tableau des evs gagnés par statistique si les evs de la statistique à augmenter sont inférieurs ou égaux à {0}:\n";
private static final String C_P_18_14="Statistique\n";
private static final String C_P_18_15="Evs gagnés\n";
private static final String C_P_18_1="Here is the table of happiness points depending on the BALL having caught the pokemon to be boosted:\n";
private static final String C_P_18_2="BALL having caught\n";
private static final String C_P_18_3="Other used BALL or none\n";
private static final String C_P_18_4="Won happiness points\n";
private static final String C_P_18_5="Here is the table of won evs by statistic if evs of the statistic to be increased are lower or equal to {0}:\n";
private static final String C_P_18_6="Statistic\n";
private static final String C_P_18_7="Won evs\n";
private static final String C_P_18_8="Utilisé sur une attaque, celle-ci gagne {0} pp.\n";
private static final String C_P_18_9="Voici le tableau des points de bonheur gagnés en fonction de la BALL de capture du pokémon à booster:\n";
private static final String M_P_18_EVS="evs";
private static final String M_P_18_EVS_BOOST="evs_boost";
private static final String M_P_18_EVS_STAT="evs_stat";
private static final String M_P_18_HAPPINESS="happiness";
private static final String M_P_18_HAPPINESS_BALL="happiness_ball";
private static final String M_P_18_HAPPINESS_BOOST="happiness_boost";
private static final String M_P_18_HAPPINESS_OTHER_BALL="happiness_other_ball";
private static final String M_P_18_WIN_PP="win_pp";
private static final char SEP='=';
private MesDataItemsBoost(){}
static String en(){
String f=M_P_18_WIN_PP+SEP+C_P_18_0;
f+=M_P_18_HAPPINESS+SEP+C_P_18_1;
f+=M_P_18_HAPPINESS_BALL+SEP+C_P_18_2;
f+=M_P_18_HAPPINESS_OTHER_BALL+SEP+C_P_18_3;
f+=M_P_18_HAPPINESS_BOOST+SEP+C_P_18_4;
f+=M_P_18_EVS+SEP+C_P_18_5;
f+=M_P_18_EVS_STAT+SEP+C_P_18_6;
f+=M_P_18_EVS_BOOST+SEP+C_P_18_7;
return f;
}
static String fr(){
String f=M_P_18_WIN_PP+SEP+C_P_18_8;
f+=M_P_18_HAPPINESS+SEP+C_P_18_9;
f+=M_P_18_HAPPINESS_BALL+SEP+C_P_18_10;
f+=M_P_18_HAPPINESS_OTHER_BALL+SEP+C_P_18_11;
f+=M_P_18_HAPPINESS_BOOST+SEP+C_P_18_12;
f+=M_P_18_EVS+SEP+C_P_18_13;
f+=M_P_18_EVS_STAT+SEP+C_P_18_14;
f+=M_P_18_EVS_BOOST+SEP+C_P_18_15;
return f;
}
}
