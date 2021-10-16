package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffprotection{
private static final String C_P_55_0="This effect protects a part of the team of the user.<br/>\n";
private static final String C_P_55_10="Le lanceur est prot&eacute;g&eacute;e des attaques o&ugrave; il est pris pour cible.<br/>\n";
private static final String C_P_55_11="Le lanceur ne peut pas tomber KO par des attaques o&ugrave; il est pris pour cible.<br/>Il garde au moins {0} pv.<br/>\n";
private static final String C_P_55_12="Le lanceur est prot&eacute;g&eacute;e des attaques offensives o&ugrave; il est pris pour cible.<br/>\n";
private static final String C_P_55_13="Le lanceur est prot&eacute;g&eacute;e des attaques non offensives o&ugrave; il est pris pour cible.<br/>\n";
private static final String C_P_55_1="The team of the user is protected against multiple targets moves.<br/>\n";
private static final String C_P_55_2="The team of the user is protected against moves with priority greater than 0.<br/>\n";
private static final String C_P_55_3="The user is protected against moves that target the user.<br/>\n";
private static final String C_P_55_4="The user cannot be knocked out by moves that target the user.<br/>The user keeps {0} hp at leeast.<br/>\n";
private static final String C_P_55_5="The user is protected against damaging moves that target the user.<br/>\n";
private static final String C_P_55_6="The user is protected against not damaging moves that target the user.<br/>\n";
private static final String C_P_55_7="Cet effet prot&egrave;ge une partie de l''&eacute;quipe du lanceur.<br/>\n";
private static final String C_P_55_8="L''&eacute;quipe du lanceur est prot&eacute;g&eacute;e des attaques multi cibles.<br/>\n";
private static final String C_P_55_9="L''&eacute;quipe du lanceur est prot&eacute;g&eacute;e des attaques de priorit&eacute; sup&eacute;rieure &agrave; 0.<br/>\n";
private static final String M_P_55_EFFECT="effect";
private static final String M_P_55_PROT_MULTI_TARGETS="prot_multi_targets";
private static final String M_P_55_PROT_PRIO="prot_prio";
private static final String M_P_55_PROT_SINGLE="prot_single";
private static final String M_P_55_PROT_SINGLE_DAMAGE="prot_single_damage";
private static final String M_P_55_PROT_SINGLE_KO="prot_single_ko";
private static final String M_P_55_PROT_SINGLE_STATUS="prot_single_status";
private static final char SEP='=';
private MesDataMovesEffectsEffprotection(){}
static String en(){
String f=M_P_55_EFFECT+SEP+C_P_55_0;
f+=M_P_55_PROT_MULTI_TARGETS+SEP+C_P_55_1;
f+=M_P_55_PROT_PRIO+SEP+C_P_55_2;
f+=M_P_55_PROT_SINGLE+SEP+C_P_55_3;
f+=M_P_55_PROT_SINGLE_KO+SEP+C_P_55_4;
f+=M_P_55_PROT_SINGLE_DAMAGE+SEP+C_P_55_5;
f+=M_P_55_PROT_SINGLE_STATUS+SEP+C_P_55_6;
return f;
}
static String fr(){
String f=M_P_55_EFFECT+SEP+C_P_55_7;
f+=M_P_55_PROT_MULTI_TARGETS+SEP+C_P_55_8;
f+=M_P_55_PROT_PRIO+SEP+C_P_55_9;
f+=M_P_55_PROT_SINGLE+SEP+C_P_55_10;
f+=M_P_55_PROT_SINGLE_KO+SEP+C_P_55_11;
f+=M_P_55_PROT_SINGLE_DAMAGE+SEP+C_P_55_12;
f+=M_P_55_PROT_SINGLE_STATUS+SEP+C_P_55_13;
return f;
}
}
