package code.scripts.pages.aiki;
final class MesDataItemsBerry{
private MesDataItemsBerry(){}
static String en(){
String f="win_super_eff_time=The berry is enabled while the owner suffers damage from a move directly used by a fighter.<br/>\n";
f+="win_super_eff=If the owner of the berry is targetted by a super effective move and can use a berry, then the rate of full life restoring the owner is {0}, the berry is not used if the owner had all its hp.<br/>\n";
f+="sorting_users_time=The berry is enabled at the moment of sorting the users by order of using.<br/>\n";
f+="sorting_users=If the owner of the berry has to use a move after a pokemon not having a berry, then the owner of the berry uses first a move.<br/>\n";
f+="mult_damage_foe_time=The berry is enabled while the owner suffers damage from a move directly used by a fighter.<br/>\n";
f+="mult_damage_foe=While the owner is targetted by a super effective move or not effective by default, the damage rate inflicted varies in function by the type of the suffered move:<br/>\n";
f+="mult_damage_foe_type=Type of the move\n";
f+="mult_damage_foe_eff=Efficiency of the move\n";
f+="mult_damage_foe_rate=Rate\n";
f+="mult_stat_time=The berry is enabled at the end of the round of the owner.<br/>\n";
f+="mult_stat=In function of the current rate of full rate of the owner, les levels of statistics of the owner vary by the following kind:<br/>\n";
f+="mult_stat_key=Statistic\n";
f+="mult_stat_hp=Rate of full life\n";
f+="mult_stat_boost=Variation of level\n";
f+="without_fail_time=The berry is enabled while the owner uses a move.<br/>\n";
f+="without_fail=If the owner of the berry is going to fail using a move, then the owner successes the move.<br/>\n";
f+="heal_pp_time=The berry is enabled at the end of the round of the owner.<br/>\n";
f+="heal_pp=If the owner of the berry has one of its moves without PP, then {0} PP are restored fot this move.<br/>\n";
f+="heal_hp_time=The berry is enabled at the end of the round of the owner.<br/>\n";
f+="heal_hp=If the rate of full life of the owner of the berry is at most {0}, then {1} hp are restored.<br/>The player can use a such berry never mind the remaining health points strictly lower at the maximum, in this case the pokemon is healed by {1} health points.<br/>\n";
f+="heal_hp_only_round_heal=The player can use a such berry never mind the remaining health points strictly lower at the maximum, in this case the pokemon is healed by {1} health points.<br/>\n";
f+="heal_status_time=The berry is enabled while the owner is targetted by an adding of status.<br/>\n";
f+="heal_status=The following status of the owner are deleted:<br/>\n";
f+="heal_hp_rate_time=The berry is enabled at the end of the round of the owner.<br/>\n";
f+="heal_hp_rate=If the current rate of full life of the owner of the berry is lower or equals to {0}, then the rate of full restored life is {1}.<br/>The player can use a such berry never mind the remaining health points strictly lower at the maximum, in this case the pokemon is healed by {1} of its full life.<br/>\n";
f+="heal_hp_rate_only_round_heal=The player can use a such berry never mind the remaining health points strictly lower at the maximum, in this case the pokemon is healed by {1} of its full life.<br/>\n";
f+="category_time=The berry is enabled while the owner suffers damage from a move directly used by a fighter.<br/>\n";
f+="category=If the owner suffers from a move with the category {0}, then its statistics vary by the following kind:<br/>\n";
f+="category_stat=Statistic\n";
f+="category_boost=Boost\n";
f+="recoil_time=The berry is enabled while the owner suffers damage from a move directly used by a fighter.<br/>\n";
f+="recoil=While the owner suffers damage from a move, the recoil damage inflicted against the user vary by the category of the move:<br/>\n";
f+="recoil_cat=Category of the suffered move\n";
f+="recoil_hp=Rate of lost life by the user\n";
return f;
}
static String fr(){
String f="win_super_eff_time=La baie est activ&eacute;e lorsque le possesseur subit des d&eacute;g&acirc;ts d''une attaque utilis&eacute;e directement par un combattant.<br/>\n";
f+="win_super_eff=Si le porteur de la baie est touch&eacute; par une attaque super efficace et peut utiliser une baie, alors le taux de vie totale r&eacute;cup&eacute;r&eacute;e du porteur vaut {0}, la baie n''est pas utilis&eacute;e si le porteur avait tous ses pv.<br/>\n";
f+="sorting_users_time=La baie est activ&eacute;e au moment de classer les lanceurs par ordre de lancer.<br/>\n";
f+="sorting_users=Si le porteur de la baie est cens&eacute; attaquer apr&egrave;s un pok&eacute;mon ne portant la baie, alors le porteur de la baie attaque d''abord.<br/>\n";
f+="mult_damage_foe_time=La baie est activ&eacute;e lorsque le possesseur subit des d&eacute;g&acirc;ts d''une attaque utilis&eacute;e directement par un combattant.<br/>\n";
f+="mult_damage_foe=Lorsque le porteur est touch&eacute; par une attaque de type super efficace sur lui ou de type non efficace de base, le coefficient des d&eacute;g&acirc;ts inflig&eacute;s varie en fonction du type de l''attaque subie:<br/>\n";
f+="mult_damage_foe_type=Type de l''attaque\n";
f+="mult_damage_foe_eff=Efficacit&eacute; de l''attaque\n";
f+="mult_damage_foe_rate=Coefficient\n";
f+="mult_stat_time=La baie est activ&eacute;e en fin de tour du possesseur.<br/>\n";
f+="mult_stat=En fonction du taux de vie totale actuelle du porteur, les crans des statistiques du porteur varient de la fa&ccedil;on suivante:<br/>\n";
f+="mult_stat_key=Statistique\n";
f+="mult_stat_hp=Taux de vie maximal\n";
f+="mult_stat_boost=Variation du cran\n";
f+="without_fail_time=La baie est activ&eacute;e lorsque le possesseur attaque.<br/>\n";
f+="without_fail=Si le porteur de la baie vient &agrave; &eacute;chouer une attaque, alors il la r&eacute;ussit.<br/>\n";
f+="heal_pp_time=La baie est activ&eacute;e en fin de tour du possesseur.<br/>\n";
f+="heal_pp=Si le porteur de la baie a une de ses attaques sans PP, alors {0} PP sont restaur&eacute;s pour cette attaque.<br/>\n";
f+="heal_hp_time=La baie est activ&eacute;e en fin de tour du possesseur.<br/>\n";
f+="heal_hp=Si le taux de vie totale du porteur de la baie est de au plus {0}, alors {1} pv sont restaur&eacute;s.<br/>Le joueur peut utiliser une telle baie peu importe les points de vie restants strictement inf&eacute;rieurs au maximum, dans ce cas le pokemon est soign&eacute; de {1} points de vie.<br/>\n";
f+="heal_hp_only_round_heal=Le joueur peut utiliser une telle baie peu importe les points de vie restants strictement inf&eacute;rieurs au maximum, dans ce cas le pokemon est soign&eacute; de {1} points de vie.<br/>\n";
f+="heal_status_time=La baie est activ&eacute;e lorsque le possesseur est vis&eacute; par une alt&eacute;ration de statut.<br/>\n";
f+="heal_status=Les statuts suivants du porteur sont gu&eacute;ris:<br/>\n";
f+="heal_hp_rate_time=La baie est activ&eacute;e en fin de tour du possesseur.<br/>\n";
f+="heal_hp_rate=Si le taux de vie totale actuelle du porteur de la baie est de au plus {0}, alors le taux de vie totale r&eacute;cup&eacute;r&eacute;e vaut {1}.<br/>Le joueur peut utiliser une telle baie peu importe les points de vie restants strictement inf&eacute;rieurs au maximum, dans ce cas le pokemon est soign&eacute; de {1} de sa vie totale.<br/>\n";
f+="heal_hp_rate_only_round_heal=Le joueur peut utiliser une telle baie peu importe les points de vie restants strictement inf&eacute;rieurs au maximum, dans ce cas le pokemon est soign&eacute; de {1} de sa vie totale.<br/>\n";
f+="category_time=La baie est activ&eacute;e lorsque le possesseur subit des d&eacute;g&acirc;ts d''une attaque utilis&eacute;e directement par un combattant.<br/>\n";
f+="category=Si le possesseur subit une attaque de cat&eacute;gorie {0}, alors ses statistiques suivantes varient de la fa&ccedil;on suivante:<br/>\n";
f+="category_stat=Statistique\n";
f+="category_boost=Boost\n";
f+="recoil_time=La baie est activ&eacute;e lorsque le possesseur subit des d&eacute;g&acirc;ts d''une attaque utilis&eacute;e directement par un combattant.<br/>\n";
f+="recoil=Lorsque le porteur subit les d&eacute;g&acirc;ts d''une attaque, les d&eacute;g&acirc;ts de recul inflig&eacute;s au lanceur varient en fonction de la cat&eacute;gorie de l''attaque:<br/>\n";
f+="recoil_cat=Cat&eacute;gorie de l''attaque subie\n";
f+="recoil_hp=Taux de vie perdu par le lanceur\n";
return f;
}
}
