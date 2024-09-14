package code.scripts.pages.aiki;
final class MesDataStatusStatusset{
private static final String C_P_89_0="Searching status\n";
private static final String C_P_89_1="Status\n";
private static final String C_P_89_2="Content of the name of status:\n";
private static final String C_P_89_3="Return to the index\n";
private static final String C_P_89_4="Recherche de statuts\n";
private static final String C_P_89_5="Statuts\n";
private static final String C_P_89_6="Contenu du nom de statut:\n";
private static final String C_P_89_7="Revenir à l''indexe\n";
private static final String C_P_89_8="OK\n";
private static final String M_P_89_CONTENT="content";
private static final String M_P_89_INDEX="index";
private static final String M_P_89_STATUS="status";
private static final String M_P_89_TITLE="title";
private static final String M_P_89_OK="ok";
private static final char SEP='=';
private MesDataStatusStatusset(){}
static String en(){
String f=M_P_89_TITLE+SEP+C_P_89_0;
f+=M_P_89_STATUS+SEP+C_P_89_1;
f+=M_P_89_CONTENT+SEP+C_P_89_2;
f+=M_P_89_INDEX+SEP+C_P_89_3;
f+=M_P_89_OK+SEP+C_P_89_8;
return f;
}
static String fr(){
String f=M_P_89_TITLE+SEP+C_P_89_4;
f+=M_P_89_STATUS+SEP+C_P_89_5;
f+=M_P_89_CONTENT+SEP+C_P_89_6;
f+=M_P_89_INDEX+SEP+C_P_89_7;
f+=M_P_89_OK+SEP+C_P_89_8;
return f;
}
}
