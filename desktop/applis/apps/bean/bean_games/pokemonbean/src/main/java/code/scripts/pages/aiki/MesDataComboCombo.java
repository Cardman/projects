package code.scripts.pages.aiki;
final class MesDataComboCombo{
private static final String C_P_2_0="Enabled groups of moves\n";
private static final String C_P_2_10="See the end of round\n";
private static final String C_P_2_11="Return to the index\n";
private static final String C_P_2_12="Groupes d''attaques actives\n";
private static final String C_P_2_13="L''effet n''est actif que si les attaques suivantes sont réussies dans le même tour:\n";
private static final String C_P_2_14="La probabilité des effets secondaires lancées par un membre de l''équipe du lanceur est multipliée par {0}.\n";
private static final String C_P_2_15="Le nombre de tours déterminé par les probabilités suivantes:\n";
private static final String C_P_2_16="Nombre de tours\n";
private static final String C_P_2_17="Probabilité\n";
private static final String C_P_2_18="Les statistiques des membres de l''équipe adverse du lanceur sont multipliées selon le tableau suivant:\n";
private static final String C_P_2_19="Statistique\n";
private static final String C_P_2_1="The effect is only enabled if the following move are successful in the same round:\n";
private static final String C_P_2_20="Coefficient\n";
private static final String C_P_2_21="Le rand d''incrémentation de fin tour est de {0}.\n";
private static final String C_P_2_22="Voir l''effet de fin de tour\n";
private static final String C_P_2_23="Revenir à l''indexe\n";
private static final String C_P_2_2="The probability of second effects used by a member of the team of the user is multiplied by {0}.\n";
private static final String C_P_2_3="The number of rounds is calculated in the following law of averages:\n";
private static final String C_P_2_4="Number of rounds\n";
private static final String C_P_2_5="Probability\n";
private static final String C_P_2_6="The statistics of the members of the foe team of the user are multiplied according to the following table:\n";
private static final String C_P_2_7="Statistic\n";
private static final String C_P_2_8="Rate\n";
private static final String C_P_2_9="The rank of incrementing of end of round is {0}.\n";
private static final String M_P_2_EFFECT="effect";
private static final String M_P_2_ENDROUND="endRound";
private static final String M_P_2_INDEX="index";
private static final String M_P_2_LAW_REPEAT="law_repeat";
private static final String M_P_2_LAW_REPEAT_KEY="law_repeat_key";
private static final String M_P_2_LAW_REPEAT_VALUE="law_repeat_value";
private static final String M_P_2_MULT_STAT_FOE="mult_stat_foe";
private static final String M_P_2_RANK_INCREMENT_NB_ROUND="rank_increment_nb_round";
private static final String M_P_2_RATE="rate";
private static final String M_P_2_RATE_SEC_EFF="rate_sec_eff";
private static final String M_P_2_STATISTIC="statistic";
private static final String M_P_2_TITLE="title";
private static final char SEP='=';
private MesDataComboCombo(){}
static String en(){
String f=M_P_2_TITLE+SEP+C_P_2_0;
f+=M_P_2_EFFECT+SEP+C_P_2_1;
f+=M_P_2_RATE_SEC_EFF+SEP+C_P_2_2;
f+=M_P_2_LAW_REPEAT+SEP+C_P_2_3;
f+=M_P_2_LAW_REPEAT_KEY+SEP+C_P_2_4;
f+=M_P_2_LAW_REPEAT_VALUE+SEP+C_P_2_5;
f+=M_P_2_MULT_STAT_FOE+SEP+C_P_2_6;
f+=M_P_2_STATISTIC+SEP+C_P_2_7;
f+=M_P_2_RATE+SEP+C_P_2_8;
f+=M_P_2_RANK_INCREMENT_NB_ROUND+SEP+C_P_2_9;
f+=M_P_2_ENDROUND+SEP+C_P_2_10;
f+=M_P_2_INDEX+SEP+C_P_2_11;
return f;
}
static String fr(){
String f=M_P_2_TITLE+SEP+C_P_2_12;
f+=M_P_2_EFFECT+SEP+C_P_2_13;
f+=M_P_2_RATE_SEC_EFF+SEP+C_P_2_14;
f+=M_P_2_LAW_REPEAT+SEP+C_P_2_15;
f+=M_P_2_LAW_REPEAT_KEY+SEP+C_P_2_16;
f+=M_P_2_LAW_REPEAT_VALUE+SEP+C_P_2_17;
f+=M_P_2_MULT_STAT_FOE+SEP+C_P_2_18;
f+=M_P_2_STATISTIC+SEP+C_P_2_19;
f+=M_P_2_RATE+SEP+C_P_2_20;
f+=M_P_2_RANK_INCREMENT_NB_ROUND+SEP+C_P_2_21;
f+=M_P_2_ENDROUND+SEP+C_P_2_22;
f+=M_P_2_INDEX+SEP+C_P_2_23;
return f;
}
}
