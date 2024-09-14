package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffvarpp{
private static final String C_P_69_0="This effect affects the pp of moves of a fighter.\n";
private static final String C_P_69_1="The target looses {0} pp of full pp of its last successful move.If the last successful move by the target owns less than {0} pp of the full pp, then the number of pp becomes zero.\n";
private static final String C_P_69_2="Cet effet touche aux pp d''un combattant.\n";
private static final String C_P_69_3="La cible perd {0} pp des pp max de sa dernière attaque réussie.Si la dernière attaque réussie de la cible possède moins de {0} pp des pp max, alors le nombre de pp devient nul.\n";
private static final String M_P_69_DELETE_PP="delete_pp";
private static final String M_P_69_EFFECT="effect";
private static final char SEP='=';
private MesDataMovesEffectsEffvarpp(){}
static String en(){
String f=M_P_69_EFFECT+SEP+C_P_69_0;
f+=M_P_69_DELETE_PP+SEP+C_P_69_1;
return f;
}
static String fr(){
String f=M_P_69_EFFECT+SEP+C_P_69_2;
f+=M_P_69_DELETE_PP+SEP+C_P_69_3;
return f;
}
}
