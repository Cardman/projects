package code.scripts.pages.aiki;
final class MesDataAbilityAbilities{
private static final String C_P_0_0="Search abilities\n";
private static final String C_P_0_1="Abilities\n";
private static final String C_P_0_2="Content of the name of ability:\n";
private static final String C_P_0_3="Return to the index\n";
private static final String C_P_0_4="Recherche de capacit&eacute;s\n";
private static final String C_P_0_5="Capacit&eacute;s\n";
private static final String C_P_0_6="Contenu du nom de capacit&eacute;:\n";
private static final String C_P_0_7="Revenir &agrave; l''indexe\n";
private static final String M_P_0_ABILITIES="abilities";
private static final String M_P_0_CONTENT="content";
private static final String M_P_0_INDEX="index";
private static final String M_P_0_TITLE="title";
private static final char SEP='=';
private MesDataAbilityAbilities(){}
static String en(){
String f=M_P_0_TITLE+SEP+C_P_0_0;
f+=M_P_0_ABILITIES+SEP+C_P_0_1;
f+=M_P_0_CONTENT+SEP+C_P_0_2;
f+=M_P_0_INDEX+SEP+C_P_0_3;
return f;
}
static String fr(){
String f=M_P_0_TITLE+SEP+C_P_0_4;
f+=M_P_0_ABILITIES+SEP+C_P_0_5;
f+=M_P_0_CONTENT+SEP+C_P_0_6;
f+=M_P_0_INDEX+SEP+C_P_0_7;
return f;
}
}
