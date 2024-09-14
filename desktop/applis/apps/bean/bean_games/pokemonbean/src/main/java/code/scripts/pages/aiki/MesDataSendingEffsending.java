package code.scripts.pages.aiki;
final class MesDataSendingEffsending{
private static final String C_P_84_0="This effect happens after the effects of the moves acting while the sending of a foe on the front battle.\n";
private static final String C_P_84_10="Les climats sont désactivés pendant la présence du combattant entré sur le terrain.\n";
private static final String C_P_84_11="Si le porteur tombe KO, perd sa capacité ou sort du terrain, alors tous les climats désactivés à l''entrée du porteur deviennent actifs à condition que le nombre de tours soit positif.\n";
private static final String C_P_84_12="Si un combattant avec un objet ou une capacité invoquant un des climats entre sur le terrain et s''il existe une capacité ou un objet désactivant les climats, alors le climat n''est pas activé.\n";
private static final String C_P_84_13="En revanche, si une attaque invoquant un climat est directement utilisée, alors l''attaque n''échoue pas à cause de l''inactivité des climats mais le climat ne deviendra actif que si aucun autre climat incompatible n''était invoqué.\n";
private static final String C_P_84_14="Lorsque le possesseur entre sur le terrain, le climat suivant est actif:\n";
private static final String C_P_84_15="Le porteur copie la capacité de l''adversaire le plus proche de lui (en face si possible).\n";
private static final String C_P_84_16="Le type du porteur devient le type de la plaque possédée, sinon le type du porteur est celui de base (sur la fiche associée dans les données).\n";
private static final String C_P_84_17="La masse du porteur est multiplié par {0} lors de son entrée.\n";
private static final String C_P_84_1="The weathers are disabled while the presence of the sent fighter on the front battle.\n";
private static final String C_P_84_2="If the owner is knocked out, looses its ability or exit from the front battle, then all disabled weathers at sending of the owner become enabled on condition that the number of rounds is positive.\n";
private static final String C_P_84_3="If a fighter with an item or an ability invoking one of the weathers is sent on the front battle and if it exists an item or an ability disabling the weathers, then the weather is not enabled.\n";
private static final String C_P_84_4="In contrary, if a move invoking a weather is directly used, then the move does not fail by the disability of the weathers but the weather only will become enabled if no other incompatible weather is invoked.\n";
private static final String C_P_84_5="While the owner is sent on the front battle, the following weather is enabled:\n";
private static final String C_P_84_6="The owner copies the ability of the closest foe to the owner (in front of is possible).\n";
private static final String C_P_84_7="Le type of the owner becomes the type of the owned plate, else the type of the owner is the base type (on the associated data).\n";
private static final String C_P_84_8="The weight of the owner is multiplied by {0} while its sending.\n";
private static final String C_P_84_9="Cet effet a lieu après les effets des attaques agissant lors de l''entrée d''un adversaire sur le terrain.\n";
private static final String M_P_84_COPY_AB="copy_ab";
private static final String M_P_84_DISABLE_WEATHER="disable_weather";
private static final String M_P_84_DISABLE_WEATHER_2="disable_weather_2";
private static final String M_P_84_DISABLE_WEATHER_3="disable_weather_3";
private static final String M_P_84_DISABLE_WEATHER_4="disable_weather_4";
private static final String M_P_84_EFFECT="effect";
private static final String M_P_84_PLATE="plate";
private static final String M_P_84_WEATHER="weather";
private static final String M_P_84_WEIGHT="weight";
private static final char SEP='=';
private MesDataSendingEffsending(){}
static String en(){
String f=M_P_84_EFFECT+SEP+C_P_84_0;
f+=M_P_84_DISABLE_WEATHER+SEP+C_P_84_1;
f+=M_P_84_DISABLE_WEATHER_2+SEP+C_P_84_2;
f+=M_P_84_DISABLE_WEATHER_3+SEP+C_P_84_3;
f+=M_P_84_DISABLE_WEATHER_4+SEP+C_P_84_4;
f+=M_P_84_WEATHER+SEP+C_P_84_5;
f+=M_P_84_COPY_AB+SEP+C_P_84_6;
f+=M_P_84_PLATE+SEP+C_P_84_7;
f+=M_P_84_WEIGHT+SEP+C_P_84_8;
return f;
}
static String fr(){
String f=M_P_84_EFFECT+SEP+C_P_84_9;
f+=M_P_84_DISABLE_WEATHER+SEP+C_P_84_10;
f+=M_P_84_DISABLE_WEATHER_2+SEP+C_P_84_11;
f+=M_P_84_DISABLE_WEATHER_3+SEP+C_P_84_12;
f+=M_P_84_DISABLE_WEATHER_4+SEP+C_P_84_13;
f+=M_P_84_WEATHER+SEP+C_P_84_14;
f+=M_P_84_COPY_AB+SEP+C_P_84_15;
f+=M_P_84_PLATE+SEP+C_P_84_16;
f+=M_P_84_WEIGHT+SEP+C_P_84_17;
return f;
}
}
