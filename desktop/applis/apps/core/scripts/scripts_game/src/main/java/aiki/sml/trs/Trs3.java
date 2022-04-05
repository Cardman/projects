package aiki.sml.trs;
public final class Trs3{
private static final String E_TR_AIKI_FIGHT_ITEMS_EVOLVINGITEM="aiki.fight.items.EvolvingItem";
private static final String E_TR_AIKI_FIGHT_ITEMS_REPEL="aiki.fight.items.Repel";
private static final String E_TR_AIKI_FIGHT_ITEMS_BERRY="aiki.fight.items.Berry";
private static final String E_TR_AIKI_FIGHT_ITEMS_HEALINGHP="aiki.fight.items.HealingHp";
private static final String E_TR_AIKI_FIGHT_ITEMS_HEALINGHPSTATUS="aiki.fight.items.HealingHpStatus";
private static final String E_TR_AIKI_FIGHT_ITEMS_SELLINGITEM="aiki.fight.items.SellingItem";
private static final String E_TR_AIKI_FIGHT_ITEMS_ITEMFORBATTLE="aiki.fight.items.ItemForBattle";
private static final String E_TR_AIKI_FIGHT_ITEMS_HEALINGSTATUS="aiki.fight.items.HealingStatus";
private static final String E_TR_AIKI_FIGHT_ITEMS_BALL="aiki.fight.items.Ball";
private static final String E_TR_AIKI_FIGHT_ITEMS_HEALINGITEM="aiki.fight.items.HealingItem";
private static final String E_TR_AIKI_FIGHT_ITEMS_FOSSIL="aiki.fight.items.Fossil";
private static final String E_TR_AIKI_FIGHT_ITEMS_EVOLVINGSTONE="aiki.fight.items.EvolvingStone";
private static final String E_TR_AIKI_FIGHT_ITEMS_BOOST="aiki.fight.items.Boost";
private static final String E_TR_AIKI_FIGHT_ITEMS_HEALINGPP="aiki.fight.items.HealingPp";
private Trs3(){}
static String tr(){
String e=E_TR_AIKI_FIGHT_ITEMS_EVOLVINGITEM+"\tItem that a pokemon holds in order to evolve\n";
e+=E_TR_AIKI_FIGHT_ITEMS_REPEL+"\tItem that repels wild pokemon during a limited number steps\n";
e+=E_TR_AIKI_FIGHT_ITEMS_BERRY+"\tHealing item with single use while held by a pokemon\n";
e+=E_TR_AIKI_FIGHT_ITEMS_HEALINGHP+"\tItem used for healing health points\n";
e+=E_TR_AIKI_FIGHT_ITEMS_HEALINGHPSTATUS+"\tItem used for healing health points and status\n";
e+=E_TR_AIKI_FIGHT_ITEMS_SELLINGITEM+"\tItem, while sold, give to the player much money\n";
e+=E_TR_AIKI_FIGHT_ITEMS_ITEMFORBATTLE+"\tItem that a pokemon holds for a battle\n";
e+=E_TR_AIKI_FIGHT_ITEMS_HEALINGSTATUS+"\tItem used for healing from status conditions\n";
e+=E_TR_AIKI_FIGHT_ITEMS_BALL+"\tUseful item for catching a wild pokemon\n";
e+=E_TR_AIKI_FIGHT_ITEMS_HEALINGITEM+"\tItem used for healing a pokemon or a team. It possibly give happiness to a pokemon.\n";
e+=E_TR_AIKI_FIGHT_ITEMS_FOSSIL+"\tItem able to revive a pokemon\n";
e+=E_TR_AIKI_FIGHT_ITEMS_EVOLVINGSTONE+"\tItem that is used on a pokemon in order to evolve\n";
e+=E_TR_AIKI_FIGHT_ITEMS_BOOST+"\tItem that increases statistics of a pokemon\n";
e+=E_TR_AIKI_FIGHT_ITEMS_HEALINGPP+"\tItem used for healing power points (moves)\n";
return e;
}
}
