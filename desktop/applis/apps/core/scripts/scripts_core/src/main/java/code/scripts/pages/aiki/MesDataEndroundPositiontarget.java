package code.scripts.pages.aiki;
final class MesDataEndroundPositiontarget{
private static final String C_P_9_0="This effect regards the position of the user and the position of the target.<br/>\n";
private static final String C_P_9_1="The effect of the move happens two rounds after.<br/>The move is based on the caracteristics of the target at the moment of using.<br/>While the two rounds after using are not finished, none of the following moves can be used.<br/>\n";
private static final String C_P_9_2="Cet effet tient compte de la position du lanceur et celle de la cible.<br/>\n";
private static final String C_P_9_3="L''effet de l''attaque a lieu deux tours apr&egrave;s.<br/>L''attaque se base sur les caract&eacute;ristiques de la cible au moment du lancer.<br/>Tant que les deux tours apr&egrave;s lancer ne sont pas pass&eacute;s, aucune des attaques suivantes ne peut &ecirc;tre utilis&eacute;e;<br/>\n";
private static final String M_P_9_ANTICIPE="anticipe";
private static final String M_P_9_EFFECT="effect";
private static final char SEP='=';
private MesDataEndroundPositiontarget(){}
static String en(){
String f=M_P_9_EFFECT+SEP+C_P_9_0;
f+=M_P_9_ANTICIPE+SEP+C_P_9_1;
return f;
}
static String fr(){
String f=M_P_9_EFFECT+SEP+C_P_9_2;
f+=M_P_9_ANTICIPE+SEP+C_P_9_3;
return f;
}
}
