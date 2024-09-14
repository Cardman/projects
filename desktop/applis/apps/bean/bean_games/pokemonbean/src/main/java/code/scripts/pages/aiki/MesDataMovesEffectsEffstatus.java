package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffstatus{
private static final String C_P_59_0="This effect adds/deletes a status at least.\n";
private static final String C_P_59_10="Cet effet ajoute/supprime au moins un statut.\n";
private static final String C_P_59_11="Voici la loi de tirage des statuts à ajouter à la cible:\n";
private static final String C_P_59_12="Statut\n";
private static final String C_P_59_13="Conditions suffisantes d''échec\n";
private static final String C_P_59_14="Probabilité\n";
private static final String C_P_59_15="Aucun statut supplémentaire\n";
private static final String C_P_59_16="Les statuts de la cible supprimés sont les suivants:\n";
private static final String C_P_59_17="Le lanceur tombe KO. Le remplaçant du lanceur est totalement soigné (pp, pv, statuts).\n";
private static final String C_P_59_18="Les statuts du lanceur sont transférés vers la cible qu''elle n''a pas.\n";
private static final String C_P_59_19="{0}\n";
private static final String C_P_59_1="Here is the law of editing status to add to the target:\n";
private static final String C_P_59_2="Status\n";
private static final String C_P_59_3="Sufficient conditions of fail\n";
private static final String C_P_59_4="Rate\n";
private static final String C_P_59_5="No added status\n";
private static final String C_P_59_6="The deleted status of the target are the following one:\n";
private static final String C_P_59_7="The user is knocked out. The substitute of the user is full healed (pp, hp, status).\n";
private static final String C_P_59_8="The status of the user are forwarded to the target that does not have.\n";
private static final String C_P_59_9="{0}\n";
private static final String M_P_59_DELETED_STATUS="deleted_status";
private static final String M_P_59_EFFECT="effect";
private static final String M_P_59_FAIL="fail";
private static final String M_P_59_FORMULA="formula";
private static final String M_P_59_FORWARD="forward";
private static final String M_P_59_KO_USER="ko_user";
private static final String M_P_59_LAW_STATUS="law_status";
private static final String M_P_59_OTHER_STATUS="other_status";
private static final String M_P_59_RATE_EVENT="rate_event";
private static final String M_P_59_STATUS="status";
private static final char SEP='=';
private MesDataMovesEffectsEffstatus(){}
static String en(){
String f=M_P_59_EFFECT+SEP+C_P_59_0;
f+=M_P_59_LAW_STATUS+SEP+C_P_59_1;
f+=M_P_59_STATUS+SEP+C_P_59_2;
f+=M_P_59_FAIL+SEP+C_P_59_3;
f+=M_P_59_RATE_EVENT+SEP+C_P_59_4;
f+=M_P_59_OTHER_STATUS+SEP+C_P_59_5;
f+=M_P_59_DELETED_STATUS+SEP+C_P_59_6;
f+=M_P_59_KO_USER+SEP+C_P_59_7;
f+=M_P_59_FORWARD+SEP+C_P_59_8;
f+=M_P_59_FORMULA+SEP+C_P_59_9;
return f;
}
static String fr(){
String f=M_P_59_EFFECT+SEP+C_P_59_10;
f+=M_P_59_LAW_STATUS+SEP+C_P_59_11;
f+=M_P_59_STATUS+SEP+C_P_59_12;
f+=M_P_59_FAIL+SEP+C_P_59_13;
f+=M_P_59_RATE_EVENT+SEP+C_P_59_14;
f+=M_P_59_OTHER_STATUS+SEP+C_P_59_15;
f+=M_P_59_DELETED_STATUS+SEP+C_P_59_16;
f+=M_P_59_KO_USER+SEP+C_P_59_17;
f+=M_P_59_FORWARD+SEP+C_P_59_18;
f+=M_P_59_FORMULA+SEP+C_P_59_19;
return f;
}
}
