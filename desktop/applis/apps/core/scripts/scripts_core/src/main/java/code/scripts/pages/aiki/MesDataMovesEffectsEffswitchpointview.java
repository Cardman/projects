package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffswitchpointview{
private MesDataMovesEffectsEffswitchpointview(){}
static String en(){
String f="effect=This effect changes the view point of fighters for a move.<br/>\n";
f+="thief=The user of this move waits that its target use a \"thievable\" move.<br/>While the target uses a move, the user of the move {0} thieves the positives effects of the move that the target has chosen without being able to use it.<br/>The user of this move invokes also the move of the target withour using pp and the target cannot use its move during a round.<br/>\n";
f+="mirror=All sent fighter using a move successful against the user of this move is target of its own \"counterable\" move and the user of the move {0} becomes user of each used move against it.<br/>\n";
f+="attract=The user of this move suffers damage of moves with single choice instead of its partners.<br/>\n";
return f;
}
static String fr(){
String f="effect=Cet effet change le point de vue des combattants pour une attaque.<br/>\n";
f+="thief=Le lanceur de cette attaque attend que sa cible lance une attaque \"saisissable\".<br/>Lorsque le tour de la cible vient, le lanceur de l''attaque {0} vole les effets positifs de l''attaque que la cible a choisie sans pouvoir la lancer.<br/>Le lanceur de cette attaque invoque aussi l''attaque de la cible sans utiliser de pp et la cible ne peut pas lancer son attaque pendant son tour.<br/>\n";
f+="mirror=Tout combattant attaquant le lanceur de cette attaque r&eacute;ussie se retrouve cible de sa propre attaque \"retournable\" et le lanceur de l''attaque {0} devient lanceur de chaque attaque lanc&eacute;e contre lui.<br/>\n";
f+="attract=Le lanceur de cette attaque subit les d&eacute;g&acirc;ts des attaques &agrave; cible unique au lieu de ses partenaires.<br/>\n";
return f;
}
}
