package code.scripts.pages.aiki;
final class MesDataItemsRepel{
private static final String C_P_30_0="The wild pokemon are repelled during {0} steps.<br/>\n";
private static final String C_P_30_1="Les pok&eacute;mons sauvages sont repouss&eacute;s pendant {0} pas.<br/>\n";
private static final String M_P_STEPS="steps";
private static final char SEP='=';
private MesDataItemsRepel(){}
static String en(){
String f=M_P_STEPS+SEP+C_P_30_0;
return f;
}
static String fr(){
String f=M_P_STEPS+SEP+C_P_30_1;
return f;
}
}
