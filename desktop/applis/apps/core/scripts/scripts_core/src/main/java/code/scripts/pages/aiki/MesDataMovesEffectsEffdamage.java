package code.scripts.pages.aiki;
final class MesDataMovesEffectsEffdamage{
private MesDataMovesEffectsEffdamage(){}
static String en(){
String f="effect=This effect inflicts damage against targets with a constant value of damage points or not.<br/>\n";
f+="const_damage=Except for failing, the target loosed {0} pv.<br/>\n";
f+="const_power=The base power of the move is {0}.<br/>\n";
f+="var_power=The base power of the move has for litteral expression:<br/> {0}.<br/>\n";
f+="damage_mult_counter=Here is the damage rates suffered by the user during the round in fonction by the category:<br/>\n";
f+="category=Category\n";
f+="rate=Rate\n";
f+="damag_law_const=The inflicted damage are always: {0}.<br/>\n";
f+="damag_law=The inflicted damage is calculated from the following law:<br/>\n";
f+="event=Event\n";
f+="rate_event=Rate\n";
f+="hit_law=The number of repeated hits for the move is calculated from the following law:<br/>\n";
f+="event_nb_hits=Number of hits\n";
f+="hit_law_const=The number of repeated hits for the move is {0}.<br/>\n";
f+="ch_rate=The base level of critical hit is {0}.<br/>\n";
f+="ch_law=The law of critical hits is the following one:<br/>\n";
f+="event_rate=Rate\n";
f+="attack_user=By being value for the attack, the statistic {0} of the user is used for calculate the damage of the move.<br/>\n";
f+="attack_target=By being value for the attack, the statistic {0} of the target is used for calculate the damage of the move.<br/>\n";
f+="defense_user=By being value for the defense, the statistic {0} of the user is used for calculate the damage of the move.<br/>\n";
f+="defense_target=By being value for the defense, the statistic {0} of the target is used for calculate the damage of the move.<br/>\n";
f+="ign_neg_stat=The move ignores the following negative variations of the statistics of the user:<br/>\n";
f+="ign_pos_stat=The move ignores the following positive variations of the statistics of the target:<br/>\n";
f+="boost_statis_once_ko_foe=If the target is knocked out by this move, the increased statistics of the user are the following one:<br/>\n";
f+="statistic=Statistic\n";
f+="boost=Increasing\n";
f+="summing_team=Each not KO partner and without status uses a move against the target with the same base power.<br/>\n";
f+="rand_max=The value for the random rate edited for the calculation of damage is always maximum.<br/>\n";
f+="formula={0}\n";
return f;
}
static String fr(){
String f="effect=Cet effet inflige des d&eacute;g&acirc;ts aux cibles avec une valeur fixe de points de d&eacute;g&acirc;ts ou non.<br/>\n";
f+="const_damage=Sauf en cas d''&eacute;chec, la cible perd {0} pv.<br/>\n";
f+="const_power=La puissance de base de l''attaque est {0}.<br/>\n";
f+="var_power=La puissance de base de l''attaque a pour expression litt&eacute;rale:<br/> {0}.<br/>\n";
f+="damage_mult_counter=Voici les coefficients des d&eacute;g&acirc;ts subits par le lanceur pendant le tour en fonction de la cat&eacute;gorie:<br/>\n";
f+="category=Cat&eacute;gorie\n";
f+="rate=Coefficient\n";
f+="damag_law_const=Les d&eacute;g&acirc;ts inflig&eacute;s valent toujours: {0}.<br/>\n";
f+="damag_law=Les d&eacute;g&acirc;ts inflig&eacute;s suivent la loi suivante.<br/>\n";
f+="event=Ev&eacute;nement\n";
f+="rate_event=Probabilit&eacute;\n";
f+="hit_law=Le nombre de coups r&eacute;p&eacute;t&eacute;s pour l''attaque suit la loi suivante:<br/>\n";
f+="event_nb_hits=Nombre de coups\n";
f+="hit_law_const=Le nombre de coups r&eacute;p&eacute;t&eacute;s pour l''attaque est de {0}.<br/>\n";
f+="ch_rate=Le niveau de coup critique de base est {0}.<br/>\n";
f+="ch_law=La loi de probabilit&eacute; des coups critiques est la suivante:<br/>\n";
f+="event_rate=Coefficient\n";
f+="attack_user=En tant que valeur pour l''attaque, la statistique {0} du lanceur est utilis&eacute;e pour calculer les d&eacute;g&acirc;ts de l''attaque.<br/>\n";
f+="attack_target=En tant que valeur pour l''attaque, la statistique {0} de la cible est utilis&eacute;e pour calculer les d&eacute;g&acirc;ts de l''attaque.<br/>\n";
f+="defense_user=En tant que valeur pour la d&eacute;fense, la statistique {0} du lanceur est utilis&eacute;e pour calculer les d&eacute;g&acirc;ts de l''attaque.<br/>\n";
f+="defense_target=En tant que valeur pour la d&eacute;fense, la statistique {0} de la cible est utilis&eacute;e pour calculer les d&eacute;g&acirc;ts de l''attaque.<br/>\n";
f+="ign_neg_stat=L''attaque ignore les variations n&eacute;gatives des statistiques du lanceur suivantes:<br/>\n";
f+="ign_pos_stat=L''attaque ignore les variations positives des statistiques de la cible suivantes:<br/>\n";
f+="boost_statis_once_ko_foe=Si la cible tombe Ko par cette attaque, les statistiques du lanceur augment&eacute;es sont les suivantes:<br/>\n";
f+="statistic=Statistique\n";
f+="boost=Augmentation\n";
f+="summing_team=Chaque partenaire non KO et sans statut attaque la cible avec la m&ecirc;me puissance de base.<br/>\n";
f+="rand_max=La valeur pour le coefficient al&eacute;atoire tir&eacute; pour le calcul des d&eacute;g&acirc;ts est toujours maximal.<br/>\n";
f+="formula={0}\n";
return f;
}
}
