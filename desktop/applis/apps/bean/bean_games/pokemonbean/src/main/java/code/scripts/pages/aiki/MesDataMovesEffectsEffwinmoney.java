package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffwinmoney{
private static final String C_P_70_0="This effect lets the player to win money.\n";
private static final String C_P_70_1="The sum of won money is {0} muliplied by the sum of the level of the user and the level of target.\n";
private static final String C_P_70_2="Cet effet permet au joueur de gagner de l''argent.\n";
private static final String C_P_70_3="La somme d''argent gagnée vaut {0} fois la somme des niveaux du lanceur et de la cible.\n";
private static final String M_P_70_EFFECT="effect";
private static final String M_P_70_WIN_MONEY="win_money";
private static final char SEP='=';
private MesDataMovesEffectsEffwinmoney(){}
static String en(){
String f=M_P_70_EFFECT+SEP+C_P_70_0;
f+=M_P_70_WIN_MONEY+SEP+C_P_70_1;
return f;
}
static String fr(){
String f=M_P_70_EFFECT+SEP+C_P_70_2;
f+=M_P_70_WIN_MONEY+SEP+C_P_70_3;
return f;
}
}
