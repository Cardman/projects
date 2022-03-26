package code.scripts.pages.aiki;
final class MesDataItemsFossil{
private static final String C_P_21_0="This fossil can rescucitate at level {0}:\n";
private static final String C_P_21_1="Ce fossile permet de faire revivre au niveau {0}:\n";
private static final String M_P_21_FOSSIL="fossil";
private static final char SEP='=';
private MesDataItemsFossil(){}
static String en(){
return M_P_21_FOSSIL+SEP+C_P_21_0;
}
static String fr(){
return M_P_21_FOSSIL+SEP+C_P_21_1;
}
}
