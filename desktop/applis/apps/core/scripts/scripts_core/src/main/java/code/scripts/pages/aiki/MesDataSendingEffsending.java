package code.scripts.pages.aiki;
final class MesDataSendingEffsending{
private MesDataSendingEffsending(){}
static String en(){
String f="effect=This effect happens after the effects of the moves acting while the sending of a foe on the front battle.<br/>\n";
f+="disable_weather=The weathers are disabled while the presence of the sent fighter on the front battle.<br/>\n";
f+="disable_weather_2=If the owner is knocked out, looses its ability or exit from the front battle, then all disabled weathers at sending of the owner become enabled on condition that the number of rounds is positive.<br/>\n";
f+="disable_weather_3=If a fighter with an item or an ability invoking one of the weathers is sent on the front battle and if it exists an item or an ability disabling the weathers, then the weather is not enabled.<br/>\n";
f+="disable_weather_4=In contrary, if a move invoking a weather is directly used, then the move does not fail by the disability of the weathers but the weather only will become enabled if no other incompatible weather is invoked.<br/>\n";
f+="weather=While the owner is sent on the front battle, the weather <a c:command=\"$clickWeather\">{0}</a> is enabled.\n";
f+="copy_ab=The owner copies the ability of the closest foe to the owner (in front of is possible).<br/>\n";
f+="plate=Le type of the owner becomes the type of the owned plate, else the type of the owner is the base type (on the associated data).<br/>\n";
f+="weight=The weight of the owner is multiplied by {0} while its sending.<br/>\n";
return f;
}
static String fr(){
String f="effect=Cet effet a lieu apr&egrave;s les effets des attaques agissant lors de l''entr&eacute;e d''un adversaire sur le terrain.<br/>\n";
f+="disable_weather=Les climats sont d&eacute;sactiv&eacute;s pendant la pr&eacute;sence du combattant entr&eacute; sur le terrain.<br/>\n";
f+="disable_weather_2=Si le porteur tombe KO, perd sa capacit&eacute; ou sort du terrain, alors tous les climats d&eacute;sactiv&eacute;s &agrave; l''entr&eacute;e du porteur deviennent actifs &agrave; condition que le nombre de tours soit positif.<br/>\n";
f+="disable_weather_3=Si un combattant avec un objet ou une capacit&eacute; invoquant un des climats entre sur le terrain et s''il existe une capacit&eacute; ou un objet d&eacute;sactivant les climats, alors le climat n''est pas activ&eacute;.<br/>\n";
f+="disable_weather_4=En revanche, si une attaque invoquant un climat est directement utilis&eacute;e, alors l''attaque n''&eacute;choue pas &agrave; cause de l''inactivit&eacute; des climats mais le climat ne deviendra actif que si aucun autre climat incompatible n''&eacute;tait invoqu&eacute;.<br/>\n";
f+="weather=Lorsque le possesseur entre sur le terrain, le climat <a c:command=\"$clickWeather\">{0}</a> est actif.\n";
f+="copy_ab=Le porteur copie la capacit&eacute; de l''adversaire le plus proche de lui (en face si possible).<br/>\n";
f+="plate=Le type du porteur devient le type de la plaque poss&eacute;d&eacute;e, sinon le type du porteur est celui de base (sur la fiche associ&eacute;e dans les donn&eacute;es).<br/>\n";
f+="weight=La masse du porteur est multipli&eacute; par {0} lors de son entr&eacute;e.<br/>\n";
return f;
}
}
