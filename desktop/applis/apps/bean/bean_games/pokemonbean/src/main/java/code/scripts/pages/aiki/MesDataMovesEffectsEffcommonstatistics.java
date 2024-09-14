package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffcommonstatistics{
private static final String C_P_41_0="This effect affects a common value foe a statistic of the user and the target.\n";
private static final String C_P_41_1="The user and the target owns the following common values:\n";
private static final String C_P_41_2="Common element fot the user and the target\n";
private static final String C_P_41_3="Formula\n";
private static final String C_P_41_4="{0}\n";
private static final String C_P_41_5="Cet effet attribue une valeur commune pour une statistique du lanceur et de la cible.\n";
private static final String C_P_41_6="Le lanceur et la cible possède les valeurs communes suivantes:\n";
private static final String C_P_41_7="Element commun pour le lanceur et la cible\n";
private static final String C_P_41_8="Formule\n";
private static final String C_P_41_9="{0}\n";
private static final String M_P_41_COMMON="common";
private static final String M_P_41_COMMON_STAT="common_stat";
private static final String M_P_41_COMMON_VALUE="common_value";
private static final String M_P_41_EFFECT="effect";
private static final String M_P_41_FORMULA="formula";
private static final char SEP='=';
private MesDataMovesEffectsEffcommonstatistics(){}
static String en(){
String f=M_P_41_EFFECT+SEP+C_P_41_0;
f+=M_P_41_COMMON+SEP+C_P_41_1;
f+=M_P_41_COMMON_STAT+SEP+C_P_41_2;
f+=M_P_41_COMMON_VALUE+SEP+C_P_41_3;
f+=M_P_41_FORMULA+SEP+C_P_41_4;
return f;
}
static String fr(){
String f=M_P_41_EFFECT+SEP+C_P_41_5;
f+=M_P_41_COMMON+SEP+C_P_41_6;
f+=M_P_41_COMMON_STAT+SEP+C_P_41_7;
f+=M_P_41_COMMON_VALUE+SEP+C_P_41_8;
f+=M_P_41_FORMULA+SEP+C_P_41_9;
return f;
}
}
