package aiki.sml.trs;
import aiki.fight.items.*;
import aiki.sml.init.*;
import code.sml.util.*;
public final class MessagesTrsClasses extends CstIgame{
private MessagesTrsClasses(){}
static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(14);
e.add(Item.EVOLVING_ITEM ,"Item that a pokemon holds in order to evolve");
e.add(Item.REPEL ,"Item that repels wild pokemon during a limited number steps");
e.add(Item.BERRY ,"Healing item with single use while held by a pokemon");
e.add(Item.HEALING_HP ,"Item used for healing health points");
e.add(Item.HEALING_HP_STATUS ,"Item used for healing health points and status");
e.add(Item.SELLING_ITEM ,"Item, while sold, give to the player much money");
e.add(Item.ITEM_FOR_BATTLE ,"Item that a pokemon holds for a battle");
e.add(Item.HEALING_STATUS ,"Item used for healing from status conditions");
e.add(Item.BALL ,"Useful item for catching a wild pokemon");
e.add(Item.HEALING_ITEM ,"Item used for healing a pokemon or a team. It possibly give happiness to a pokemon.");
e.add(Item.FOSSIL ,"Item able to revive a pokemon");
e.add(Item.EVOLVING_STONE ,"Item that is used on a pokemon in order to evolve");
e.add(Item.BOOST ,"Item that increases statistics of a pokemon");
e.add(Item.HEALING_PP ,"Item used for healing power points (moves)");
return e;
}
static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(14);
f.add(Item.EVOLVING_ITEM ,"Objet qu'un pokemon tient pour évoluer");
f.add(Item.REPEL ,"Objet repoussant les pokemon sauvages pendant un nombre de pas limité");
f.add(Item.BERRY ,"Objet de soin souvent à utilisation unique lorsque porté par un pokemon");
f.add(Item.HEALING_HP ,"Objet utilisé pour soigner des points de vie");
f.add(Item.HEALING_HP_STATUS ,"Objet utilisé pour soigner des points de vies et des statuts");
f.add(Item.SELLING_ITEM ,"Objet, lorsque vendu, donne au joueur beaucoup d'argent");
f.add(Item.ITEM_FOR_BATTLE ,"Objet qu'un pokemon tient pour un combat");
f.add(Item.HEALING_STATUS ,"Objet utilisé pour soigner contre les altérations de statuts");
f.add(Item.BALL ,"Objet utile pour capturer un pokemon sauvage");
f.add(Item.HEALING_ITEM ,"Objet utilisé pour soigner un pokemon ou une équipe. Il peut donner du bonheur à un pokemon.");
f.add(Item.FOSSIL ,"Objet pouvant revivre un pokemon");
f.add(Item.EVOLVING_STONE ,"Objet utilisé sur un pokemon pour évoluer");
f.add(Item.BOOST ,"Objet augmentant les statistiques d'un pokemon");
f.add(Item.HEALING_PP ,"Objet utilisé pour soigner des points de pouvoir (attaques)");
return f;
}
}
