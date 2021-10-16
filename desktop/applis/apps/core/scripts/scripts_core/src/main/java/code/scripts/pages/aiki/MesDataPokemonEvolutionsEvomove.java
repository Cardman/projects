package code.scripts.pages.aiki;
final class MesDataPokemonEvolutionsEvomove{
private static final String C_P_77_0="{0} knows the move <a c:command=\"$clickMove({2})\">{1}</a> and grows a level.\n";
private static final String C_P_77_1="{0} conna&icirc;t l''attaque <a c:command=\"$clickMove({2})\">{1}</a> et monte d''un niveau.\n";
private static final String M_P_MOVE="move";
private static final char SEP='=';
private MesDataPokemonEvolutionsEvomove(){}
static String en(){
String f=M_P_MOVE+SEP+C_P_77_0;
return f;
}
static String fr(){
String f=M_P_MOVE+SEP+C_P_77_1;
return f;
}
}
