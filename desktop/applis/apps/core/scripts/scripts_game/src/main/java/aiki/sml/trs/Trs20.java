package aiki.sml.trs;
import aiki.fight.items.*;
public final class Trs20{
private Trs20(){}
static String tr(){
String f= Item.EVOLVING_ITEM +"\tObjet qu'un pokemon tient pour &eacute;voluer\n";
f+= Item.REPEL +"\tObjet repoussant les pokemon sauvages pendant un nombre de pas limit&eacute;\n";
f+= Item.BERRY +"\tObjet de soin souvent &agrave; utilisation unique lorsque port&eacute; par un pokemon\n";
f+= Item.HEALING_HP +"\tObjet utilis&eacute; pour soigner des points de vie\n";
f+= Item.HEALING_HP_STATUS +"\tObjet utilis&eacute; pour soigner des points de vies et des statuts\n";
f+= Item.SELLING_ITEM +"\tObjet, lorsque vendu, donne au joueur beaucoup d'argent\n";
f+= Item.ITEM_FOR_BATTLE +"\tObjet qu'un pokemon tient pour un combat\n";
f+= Item.HEALING_STATUS +"\tObjet utilis&eacute; pour soigner contre les alt&eacute;rations de statuts\n";
f+= Item.BALL +"\tObjet utile pour capturer un pokemon sauvage\n";
f+= Item.HEALING_ITEM +"\tObjet utilis&eacute; pour soigner un pokemon ou une &eacute;quipe. Il peut donner du bonheur &agrave; un pokemon.\n";
f+= Item.FOSSIL +"\tObjet pouvant revivre un pokemon\n";
f+= Item.EVOLVING_STONE +"\tObjet utilis&eacute; sur un pokemon pour &eacute;voluer\n";
f+= Item.BOOST +"\tObjet augmentant les statistiques d'un pokemon\n";
f+= Item.HEALING_PP +"\tObjet utilis&eacute; pour soigner des points de pouvoir (attaques)\n";
return f;
}
}
