package code.scripts.pages.aiki;
final class MesDataEndroundEndround{
private static final String C_P_3_0="End of round\n";
private static final String C_P_3_38="Fin de tour\n";
private static final String M_P_3_TITLE="title";
private static final char SEP='=';
private MesDataEndroundEndround(){}
static String en(){
String f="";
f+=M_P_3_TITLE+SEP+C_P_3_0;
return f;
}
static String fr(){
String f="";
f+=M_P_3_TITLE+SEP+C_P_3_38;
return f;
}
}
