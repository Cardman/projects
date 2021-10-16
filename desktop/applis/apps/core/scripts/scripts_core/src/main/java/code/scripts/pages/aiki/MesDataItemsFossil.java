package code.scripts.pages.aiki;
final class MesDataItemsFossil{
private static final String C_P_21_0="This fossil can rescucitate <a c:command=\"$clickPokemon\">{0}</a>, at level {1}.<br/>\n";
private static final String C_P_21_1="Ce fossile permet de faire revivre <a c:command=\"$clickPokemon\">{0}</a>, au niveau {1}.<br/>\n";
private static final String M_P_FOSSIL="fossil";
private static final char SEP='=';
private MesDataItemsFossil(){}
static String en(){
String f=M_P_FOSSIL+SEP+C_P_21_0;
return f;
}
static String fr(){
String f=M_P_FOSSIL+SEP+C_P_21_1;
return f;
}
}
