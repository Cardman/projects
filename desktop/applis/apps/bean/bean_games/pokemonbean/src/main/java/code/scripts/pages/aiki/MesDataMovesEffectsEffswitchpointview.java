package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffswitchpointview{
private static final String C_P_63_0="This effect changes the view point of fighters for a move.\n";
private static final String C_P_63_1="The user of this move waits that its target use a \"thievable\" move.While the target uses a move, the user of the move {0} thieves the positives effects of the move that the target has chosen without being able to use it.The user of this move invokes also the move of the target withour using pp and the target cannot use its move during a round.\n";
private static final String C_P_63_2="All sent fighter using a move successful against the user of this move is target of its own \"counterable\" move and the user of the move {0} becomes user of each used move against it.\n";
private static final String C_P_63_3="The user of this move suffers damage of moves with single choice instead of its partners.\n";
private static final String C_P_63_4="Cet effet change le point de vue des combattants pour une attaque.\n";
private static final String C_P_63_5="Le lanceur de cette attaque attend que sa cible lance une attaque \"saisissable\".Lorsque le tour de la cible vient, le lanceur de l''attaque {0} vole les effets positifs de l''attaque que la cible a choisie sans pouvoir la lancer.Le lanceur de cette attaque invoque aussi l''attaque de la cible sans utiliser de pp et la cible ne peut pas lancer son attaque pendant son tour.\n";
private static final String C_P_63_6="Tout combattant attaquant le lanceur de cette attaque réussie se retrouve cible de sa propre attaque \"retournable\" et le lanceur de l''attaque {0} devient lanceur de chaque attaque lancée contre lui.\n";
private static final String C_P_63_7="Le lanceur de cette attaque subit les dégâts des attaques à cible unique au lieu de ses partenaires.\n";
private static final String M_P_63_ATTRACT="attract";
private static final String M_P_63_EFFECT="effect";
private static final String M_P_63_MIRROR="mirror";
private static final String M_P_63_THIEF="thief";
private static final char SEP='=';
private MesDataMovesEffectsEffswitchpointview(){}
static String en(){
String f=M_P_63_EFFECT+SEP+C_P_63_0;
f+=M_P_63_THIEF+SEP+C_P_63_1;
f+=M_P_63_MIRROR+SEP+C_P_63_2;
f+=M_P_63_ATTRACT+SEP+C_P_63_3;
return f;
}
static String fr(){
String f=M_P_63_EFFECT+SEP+C_P_63_4;
f+=M_P_63_THIEF+SEP+C_P_63_5;
f+=M_P_63_MIRROR+SEP+C_P_63_6;
f+=M_P_63_ATTRACT+SEP+C_P_63_7;
return f;
}
}
