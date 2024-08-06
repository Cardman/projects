package aiki.sml.trs;
import aiki.fight.items.*;
public final class Trs3{
private Trs3(){}
static String tr(){
String e= Item.EVOLVING_ITEM +"\tItem that a pokemon holds in order to evolve\n";
e+= Item.REPEL +"\tItem that repels wild pokemon during a limited number steps\n";
e+= Item.BERRY +"\tHealing item with single use while held by a pokemon\n";
e+= Item.HEALING_HP +"\tItem used for healing health points\n";
e+= Item.HEALING_HP_STATUS +"\tItem used for healing health points and status\n";
e+= Item.SELLING_ITEM +"\tItem, while sold, give to the player much money\n";
e+= Item.ITEM_FOR_BATTLE +"\tItem that a pokemon holds for a battle\n";
e+= Item.HEALING_STATUS +"\tItem used for healing from status conditions\n";
e+= Item.BALL +"\tUseful item for catching a wild pokemon\n";
e+= Item.HEALING_ITEM +"\tItem used for healing a pokemon or a team. It possibly give happiness to a pokemon.\n";
e+= Item.FOSSIL +"\tItem able to revive a pokemon\n";
e+= Item.EVOLVING_STONE +"\tItem that is used on a pokemon in order to evolve\n";
e+= Item.BOOST +"\tItem that increases statistics of a pokemon\n";
e+= Item.HEALING_PP +"\tItem used for healing power points (moves)\n";
return e;
}
}
