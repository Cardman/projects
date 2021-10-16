package code.scripts.pages.aiki;
final class MesDataPokemonEvolutionsEvoteam{
private static final String C_P_80_0="<a c:command=\"$clickTeam({2})\">{1}</a> must be present in the team while {0} grows a level.\n";
private static final String C_P_80_1="<a c:command=\"$clickTeam({2})\">{1}</a> doit &ecirc;tre pr&eacute;sent dans l''&eacute;quipe pendant que {0} monte d''un niveau.\n";
private static final String M_P_80_TEAM="team";
private static final char SEP='=';
private MesDataPokemonEvolutionsEvoteam(){}
static String en(){
String f=M_P_80_TEAM+SEP+C_P_80_0;
return f;
}
static String fr(){
String f=M_P_80_TEAM+SEP+C_P_80_1;
return f;
}
}
