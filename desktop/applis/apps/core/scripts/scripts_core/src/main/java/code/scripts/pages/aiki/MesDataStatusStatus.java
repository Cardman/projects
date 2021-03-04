package code.scripts.pages.aiki;
final class MesDataStatusStatus{
private MesDataStatusStatus(){}
static String en(){
String f="title=Data of the status {0}\n";
f+="auto_damage=The user inflicts itself damage of a move with a base power being {0} based for the attack on the value of the statistic {1} and for the defense on the value of the statistic {2}.<br/>\n";
f+="rate_use_move=The probability that the owner can act its round is {0}.<br/>\n";
f+="not_attack=The owner cannot act its round.<br/>\n";
f+="rate_use_move_foe=The probability that the owner can use a move against a target in relation with the owner is {0}.<br/>\n";
f+="not_attack_foe=The owner cannot use a move against a target in relation with the owner.<br/>\n";
f+="rate_heal_move=The probability that the owner is healed from the status is {0} knowing that the owner uses a move.<br/>\n";
f+="rate_use_move_round=Here is the law of averages in fonction by the number of rounds:<br/>\n";
f+="rate_use_move_round_key=Number of rounds\n";
f+="rate_use_move_round_rate=Probability\n";
f+="single=The status depends on the target.<br/>\n";
f+="relation=The status depends on the user and the target.<br/>\n";
f+="catching_rate=The catching rate is multiplied by {0} for each owned status by the user.<br/>\n";
f+="disabled_eff_if_switch=The effect is stopped while the owner exit from the front of battle.<br/>\n";
f+="increment_end_round=The rank of incrementing is {0}.<br/>\n";
f+="incrementing_end_round_true=The incrementing of the number of rounds of the effect happens at the end of round.<br/>\n";
f+="incrementing_end_round_false=The incrementing of the number of rounds of the effect happens at the beginning of round.<br/>\n";
f+="mult_stat=The statistics of the owner are multiplied in the following way:<br/>\n";
f+="mult_stat_key=Statistic\n";
f+="mult_stat_value=Rate\n";
f+="reasons=The status is not affected to the fighter at the end of round if and only if one of the conditions is checked:<br/>\n";
f+="wedding=If the status is triggered by a partner of the target and if the target has an opposite gender at its partner, then the target falls in love with the partner even if the target is protected.<br/>\n";
f+="heal_hp=In the case when a move achieves a loved partner, the rate of restored life of this partner is {0}.<br/>\n";
f+="damaged_foes=If the owner loves a partner, then damage are multiplied by {0} for each partner that it loves.<br/>\n";
f+="status=Status\n";
f+="endRound=See the effect of end of round\n";
f+="formula={0}\n";
f+="damage_incremented_true=Damage is multiplied by the number of elapsed rounds.\n";
f+="damage_incremented_false=Damage is not multiplied by the number of elapsed rounds.\n";
return f;
}
static String fr(){
String f="title=Donn&eacute;es sur le statut {0}\n";
f+="auto_damage=Le lanceur s''autoinflige les d&eacute;g&acirc;ts d''une attaque de puissance de base valant {0} se basant pour l''attaque sur la valeur de la statistique {1} et pour la d&eacute;fense sur la valeur de la statistique {2}.<br/>\n";
f+="rate_use_move=La probabilit&eacute; que le porteur puisse entamer son tour est de {0}.<br/>\n";
f+="not_attack=Le porteur ne peut pas entamer son tour.<br/>\n";
f+="rate_use_move_foe=La probabilit&eacute; que le porteur puisse utiliser une attaque sur une cible en relation avec le porteur est de {0}.<br/>\n";
f+="not_attack_foe=Le porteur ne peut pas utiliser une attaque sur une cible en relation avec le porteur.<br/>\n";
f+="rate_heal_move=La probabilit&eacute; que le porteur soit soign&eacute; du statut est de {0} sachant que le porteur attaque.<br/>\n";
f+="rate_use_move_round=Voici le tableau des probabilit&eacute;s en fonction du nombre de tour:<br/>\n";
f+="rate_use_move_round_key=Nombre de tours\n";
f+="rate_use_move_round_rate=Probabilit&eacute;\n";
f+="single=Le statut d&eacute;pend de la cible.<br/>\n";
f+="relation=Le statut d&eacute;pend du lanceur et de la cible.<br/>\n";
f+="catching_rate=Le coefficient de capture est multipli&eacute; par {0} pour chaque statut poss&eacute;d&eacute; par le porteur.<br/>\n";
f+="disabled_eff_if_switch=L''effet s''arr&ecirc;te lorsque le porteur sort du terrain.<br/>\n";
f+="increment_end_round=Le rang d''incr&eacute;mentation vaut {0}.<br/>\n";
f+="incrementing_end_round_true=L''incr&eacute;mentation du nombre de tours de l''effet a lieu en fin de tour.<br/>\n";
f+="incrementing_end_round_false=L''incr&eacute;mentation du nombre de tours de l''effet a lieu en d&eacute;but de tour.<br/>\n";
f+="mult_stat=Les statistiques du porteur sont multipli&eacute;es de la fa&ccedil;on suivante:<br/>\n";
f+="mult_stat_key=Statistique\n";
f+="mult_stat_value=Coefficient\n";
f+="reasons=Le statut n''est pas affect&eacute; en fin de tour si et seulement une des conditions est v&eacute;rifi&eacute;e:<br/>\n";
f+="wedding=Si le statut est provoqu&eacute; par un partenaire de la cible et si la cible est du genre oppos&eacute; &agrave; son partenaire, alors la cible tombe amoureuse du partenaire m&ecirc;me si la cible est immunis&eacute;e.<br/>\n";
f+="heal_hp=En cas d''attaque touchant un partenaire aim&eacute;, le taux de vie restaur&eacute;s de ce partenaire vaut {0}.<br/>\n";
f+="damaged_foes=Si le porteur aime un partenaire, alors les d&eacute;g&acirc;ts sont multipli&eacute;s par {0} pour chaque partenaire qu''il aime.<br/>\n";
f+="status=Statuts\n";
f+="endRound=Voir l''effet de fin de tour\n";
f+="formula={0}\n";
f+="damage_incremented_true=Les d&eacute;g&acirc;ts sont multipli&eacute;s par le nombre de tours &eacute;coul&eacute;.\n";
f+="damage_incremented_false=Les d&eacute;g&acirc;ts ne sont pas multipli&eacute;s par le nombre de tours &eacute;coul&eacute;.\n";
return f;
}
}
