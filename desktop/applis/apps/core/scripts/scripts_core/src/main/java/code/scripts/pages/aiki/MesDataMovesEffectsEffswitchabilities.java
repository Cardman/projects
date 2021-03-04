package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffswitchabilities{
private MesDataMovesEffectsEffswitchabilities(){}
static String en(){
String f="effect=This effect changes an ability at least.<br/>\n";
f+="give_const=The target takes the ability <a c:command=\"$clickAbility({1})\">{0}</a>.<br/>\n";
f+="give_const_empty=The ability of the target has not effect anymore.<br/>\n";
f+="give_to_user=The user takes the ability of the target.<br/>If the ability of the target was without effect, then the ability of the user will be without effect.<br/>\n";
f+="give_to_target=The target takes the ability of the user.<br/>If the ability of the user was without effect, then the ability of the target will be without effect.<br/>\n";
f+="swicth_abilities=The user and the target switch one each other the abilities.<br/>If the ability of the user was without effect, then the ability of the target will be without effect.<br/>If the ability of the target was without effect, then the ability of the user will be without effect.<br/>\n";
return f;
}
static String fr(){
String f="effect=Cet effet change au moins une capacit&eacute;.<br/>\n";
f+="give_const=La cible prend la capacit&eacute; <a c:command=\"$clickAbility({1})\">{0}</a>.<br/>\n";
f+="give_const_empty=La capacit&eacute; de la cible n''a plus d''effet.<br/>\n";
f+="give_to_user=Le lanceur prend la capacit&eacute; de la cible.<br/>Si la capacit&eacute; de la cible &eacute;tait sans effet, alors la capacit&eacute; du lanceur sera sans effet.<br/>\n";
f+="give_to_target=La cible prend la capacit&eacute; du lanceur.<br/>Si la capacit&eacute; du lanceur &eacute;tait sans effet, alors la capacit&eacute; de la cible sera sans effet.<br/>\n";
f+="swicth_abilities=Le lanceur et la cible s''&eacute;changent les capacit&eacute;s.<br/>Si la capacit&eacute; du lanceur &eacute;tait sans effet, alors la capacit&eacute; de la cible sera sans effet.<br/>Si la capacit&eacute; de la cible &eacute;tait sans effet, alors la capacit&eacute; du lanceur sera sans effet.<br/>\n";
return f;
}
}
