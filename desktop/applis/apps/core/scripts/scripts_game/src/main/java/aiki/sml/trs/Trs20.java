package aiki.sml.trs;
public final class Trs20{
private static final String F_TR_AIKI_FIGHT_ITEMS_EVOLVINGITEM="aiki.fight.items.EvolvingItem";
private static final String F_TR_AIKI_FIGHT_ITEMS_REPEL="aiki.fight.items.Repel";
private static final String F_TR_AIKI_FIGHT_ITEMS_BERRY="aiki.fight.items.Berry";
private static final String F_TR_AIKI_FIGHT_ITEMS_HEALINGHP="aiki.fight.items.HealingHp";
private static final String F_TR_AIKI_FIGHT_ITEMS_HEALINGHPSTATUS="aiki.fight.items.HealingHpStatus";
private static final String F_TR_AIKI_FIGHT_ITEMS_SELLINGITEM="aiki.fight.items.SellingItem";
private static final String F_TR_AIKI_FIGHT_ITEMS_ITEMFORBATTLE="aiki.fight.items.ItemForBattle";
private static final String F_TR_AIKI_FIGHT_ITEMS_HEALINGSTATUS="aiki.fight.items.HealingStatus";
private static final String F_TR_AIKI_FIGHT_ITEMS_BALL="aiki.fight.items.Ball";
private static final String F_TR_AIKI_FIGHT_ITEMS_HEALINGITEM="aiki.fight.items.HealingItem";
private static final String F_TR_AIKI_FIGHT_ITEMS_FOSSIL="aiki.fight.items.Fossil";
private static final String F_TR_AIKI_FIGHT_ITEMS_EVOLVINGSTONE="aiki.fight.items.EvolvingStone";
private static final String F_TR_AIKI_FIGHT_ITEMS_BOOST="aiki.fight.items.Boost";
private static final String F_TR_AIKI_FIGHT_ITEMS_HEALINGPP="aiki.fight.items.HealingPp";
private Trs20(){}
static String tr(){
String f=F_TR_AIKI_FIGHT_ITEMS_EVOLVINGITEM+"\tObjet qu'un pokemon tient pour &eacute;voluer\n";
f+=F_TR_AIKI_FIGHT_ITEMS_REPEL+"\tObjet repoussant les pokemon sauvages pendant un nombre de pas limit&eacute;\n";
f+=F_TR_AIKI_FIGHT_ITEMS_BERRY+"\tObjet de soin souvent &agrave; utilisation unique lorsque port&eacute; par un pokemon\n";
f+=F_TR_AIKI_FIGHT_ITEMS_HEALINGHP+"\tObjet utilis&eacute; pour soigner des points de vie\n";
f+=F_TR_AIKI_FIGHT_ITEMS_HEALINGHPSTATUS+"\tObjet utilis&eacute; pour soigner des points de vies et des statuts\n";
f+=F_TR_AIKI_FIGHT_ITEMS_SELLINGITEM+"\tObjet, lorsque vendu, donne au joueur beaucoup d'argent\n";
f+=F_TR_AIKI_FIGHT_ITEMS_ITEMFORBATTLE+"\tObjet qu'un pokemon tient pour un combat\n";
f+=F_TR_AIKI_FIGHT_ITEMS_HEALINGSTATUS+"\tObjet utilis&eacute; pour soigner contre les alt&eacute;rations de statuts\n";
f+=F_TR_AIKI_FIGHT_ITEMS_BALL+"\tObjet utile pour capturer un pokemon sauvage\n";
f+=F_TR_AIKI_FIGHT_ITEMS_HEALINGITEM+"\tObjet utilis&eacute; pour soigner un pokemon ou une &eacute;quipe. Il peut donner du bonheur &agrave; un pokemon.\n";
f+=F_TR_AIKI_FIGHT_ITEMS_FOSSIL+"\tObjet pouvant revivre un pokemon\n";
f+=F_TR_AIKI_FIGHT_ITEMS_EVOLVINGSTONE+"\tObjet utilis&eacute; sur un pokemon pour &eacute;voluer\n";
f+=F_TR_AIKI_FIGHT_ITEMS_BOOST+"\tObjet augmentant les statistiques d'un pokemon\n";
f+=F_TR_AIKI_FIGHT_ITEMS_HEALINGPP+"\tObjet utilis&eacute; pour soigner des points de pouvoir (attaques)\n";
return f;
}
}
