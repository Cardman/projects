package code.scripts.pages.aiki;
final class MesDataSolutionSolution{
private static final String C_P_87_0="Solution for playing\n";
private static final String C_P_87_1="Name\n";
private static final String C_P_87_2="Gender\n";
private static final String C_P_87_3="Image\n";
private static final String C_P_87_4="Return to the index\n";
private static final String C_P_87_5="Solution de jeu\n";
private static final String C_P_87_6="Nom\n";
private static final String C_P_87_7="Genre\n";
private static final String C_P_87_8="Image\n";
private static final String C_P_87_9="Retour &agrave; l''index\n";
private static final String M_P_GENDER="gender";
private static final String M_P_IMAGE="image";
private static final String M_P_INDEX="index";
private static final String M_P_NAME="name";
private static final String M_P_TITLE="title";
private static final char SEP='=';
private MesDataSolutionSolution(){}
static String en(){
String f=M_P_TITLE+SEP+C_P_87_0;
f+=M_P_NAME+SEP+C_P_87_1;
f+=M_P_GENDER+SEP+C_P_87_2;
f+=M_P_IMAGE+SEP+C_P_87_3;
f+=M_P_INDEX+SEP+C_P_87_4;
return f;
}
static String fr(){
String f=M_P_TITLE+SEP+C_P_87_5;
f+=M_P_NAME+SEP+C_P_87_6;
f+=M_P_GENDER+SEP+C_P_87_7;
f+=M_P_IMAGE+SEP+C_P_87_8;
f+=M_P_INDEX+SEP+C_P_87_9;
return f;
}
}
