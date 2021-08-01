package code.scripts.pages.cards;
import code.util.*;
public final class MessTarotPage{
private MessTarotPage(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_cards/css/tarot.css",css());
m.addEntry("resources_cards/messages/en/messages_tarot.properties",en());
m.addEntry("resources_cards/messages/fr/messages_tarot.properties",fr());
return m;
}
static String css(){
String f="h1 {\n";
f+="\tcolor: #0000FF;\n";
f+="}\n";
f+="td,caption{\n";
f+="\tborder:1px solid black;\n";
f+="}\n";
return f;
}
static String en(){
String f="win=You win.\n";
f+="equality=Equality.\n";
f+="loose=You loose.\n";
f+="successful=The bid {0} is passed of {1} points.\n";
f+="failed=The bid {0} is failed of {1} points.\n";
f+="mid=The bid {0} is neither passed nor failed.\n";
f+="slam=The attack''s team has achieved the grand slam.\n";
f+="noSlam=The attack''s team has not won all tricks.\n";
f+="successfulDeclaredSlam=The attack''s team has achieved the grand slam by declaring it.\n";
f+="successfulNoDeclaredSlam=The attack''s team has achieved the grand slam without declaring it.\n";
f+="successfulSlam=The defense''s team has achieved the grand slam.\n";
f+="failedDeclaredSlam=The attack''s team has failed the grand slam by declaring it.\n";
f+="noSlamAttack=The attack''s team has not won all tricks.\n";
f+="noSlamDefense=The defense''s team has not won all tricks.\n";
f+="detailsSuccessfulDeclaredSlam=Successful declared slam (attack): {0}.\n";
f+="detailsSuccessfulNoDeclaredSlam=Successful no declared slam (attack): {0}.\n";
f+="detailsSuccessfulSlam=Successful slam (defense): {0}.\n";
f+="detailsFailedDeclaredSlam=Failed slam (attack): {0}.\n";
f+="results=Results\n";
f+="classic_bid=Calculation of bidding points\n";
f+="classic_base=Base points for bidding:\n";
f+="classic_small=Player who has led the trump ace to the last trick:\n";
f+="classic_diff=Difference between taker''s points and necessary points in order to win this deal:\n";
f+="classic_rate=Rate in relationship with bidding:\n";
f+="classic_score_taker=Taker''s score without declaring: ( {0} + {1} + {2} ) * {3} = {4} points\n";
f+="classic_decl=Calculation of players''s declaring\n";
f+="classic_decl_player={0}''s declaring ({1}):\n";
f+="sum=Sum :\n";
f+="classic_sum_player=Sum of players'' declaring:\n";
f+="classic_addon=Additional bonuses\n";
f+="classic_addon_att=Bonuses for attack team:\n";
f+="classic_addon_def=Bonuses for defense team:\n";
f+="classic_addon_sum=Sum of additional bonuses:\n";
f+="classic_rate_pl=Rates and scores of this deal for each player\n";
f+="player=Player\n";
f+="rate=Rate\n";
f+="score=Score\n";
f+="exp_rate=This rate is applied on taker''s score\n";
f+="exp_score=This score belongs to a linear relationship with taker''s score and the rate\n";
f+="variant_table_1=Steps of calculation of players'' ranking by criteria\n";
f+="variant_table_1_1=Player\n";
f+="variant_table_1_2=Ranking in relationship with the difference of points\n";
f+="variant_table_1_3=Ranking in relationship with the number of oudlers\n";
f+="variant_table_1_4=Ranking in relationship with the number of characters\n";
f+="variant_table_1_5=Ranking in relationship with the strength of characters\n";
f+="variant_table_1_6=Final rank\n";
f+="variant_table_2=Calculation of players'' points\n";
f+="variant_table_2_1=Nickname\n";
f+="variant_table_2_2=Won points in the tricks\n";
f+="variant_table_2_3=Minimum score for winning\n";
f+="variant_table_2_4=Differences of points\n";
f+="variant_table_2_5=Rate\n";
f+="variant_table_2_6=Score\n";
f+="variant_decl=Calculation of players''s declaring\n";
f+="variant_decl_pl={0}''s declaring:\n";
f+="variant_add=Bonuses\n";
f+="variant_add_pl=Players'' additional bonuses\n";
f+="variant_add_pl_1=Nickname\n";
f+="variant_add_pl_2=Bonus\n";
f+="classic_points=1 Calculation of attack team''s points\n";
f+="classic_oulders=Number of oudlers won in the attack team''s tricks:\n";
f+="classic_need=Number of necessary points in order that the taker wins:\n";
f+="classic_won=Number of points won in the attack team''s tricks:\n";
f+="classic_att=2 Attack team\n";
f+="classic_taker=Taker:\n";
f+="classic_parts=Taker''s partners:\n";
f+="classic_none_part=Noone\n";
f+="classic_called=Called cards:\n";
f+="classic_none_called=Noone\n";
f+="classic_bid_end=Bid:\n";
f+="classic_res=3 Results\n";
f+="variant_res=Results\n";
f+="variant_res_1=The greatest difference of points:\n";
f+="variant_res_2=Your position before deciding:\n";
f+="variant_res_3=Your final position:\n";
f+="variant_scores=Scores\n";
f+="beat_cards=Mix Cards\n";
f+="dealing_pl=Players'' repartition\n";
f+="mode=Mode\n";
f+="discard=Discarding after call\n";
f+="bids=Allowed Bids at the beginning of the deal\n";
f+="decls=Allowed declaring\n";
f+="hands=Handfuls\n";
f+="hand=Handful\n";
f+="nb=Number\n";
f+="mis=Allowed miseres:\n";
f+="nothing=Nothing\n";
f+="ending=End of game\n";
f+="yes=yes\n";
f+="no=no\n";
return f;
}
static String fr(){
String f="win=Vous gagnez.\n";
f+="equality=Match nul.\n";
f+="loose=Vous perdez.\n";
f+="successful=L''ench&egrave;re {0} est r&eacute;ussie de {1} points.\n";
f+="failed=L''ench&egrave;re {0} est chut&eacute;e de {1} points.\n";
f+="mid=L''ench&egrave;re {0} n''est ni r&eacute;ussie ni chut&eacute;e.\n";
f+="slam=L''attaque a r&eacute;ussi un capot.\n";
f+="noSlam=L''attaque n''a pas r&eacute;ussi de capot.\n";
f+="successfulDeclaredSlam=L''attaque a r&eacute;ussi le grand chelem en l''annon&ccedil;ant.\n";
f+="successfulNoDeclaredSlam=L''attaque a r&eacute;ussi le grand chelem sans l''annoncer.\n";
f+="successfulSlam=La d&eacute;fense a r&eacute;ussi le grand chelem.\n";
f+="failedDeclaredSlam=L''attaque n''a pas r&eacute;ussi le grand chelem en l''annon&ccedil;ant.\n";
f+="noSlamAttack=L''attaque n''a pas gagn&eacute; tous les plis.\n";
f+="noSlamDefense=La d&eacute;fense n''a pas gagn&eacute; tous les plis.\n";
f+="detailsSuccessfulDeclaredSlam=Chelem demand&eacute; et r&eacute;ussi (attaque): {0}.\n";
f+="detailsSuccessfulNoDeclaredSlam=Chelem non demand&eacute; et r&eacute;ussi (attaque): {0}.\n";
f+="detailsSuccessfulSlam=Chelem r&eacute;ussi (d&eacute;fense): {0}.\n";
f+="detailsFailedDeclaredSlam=Chelem chut&eacute; (attack): {0}.\n";
f+="results=R&eacute;sultats\n";
f+="classic_bid=Calcul des points li&eacute;s &agrave; l''ench&egrave;re\n";
f+="classic_base=Base de points pour l''ench&egrave;re:\n";
f+="classic_small=Joueur ayant men&eacute; le Petit au bout:\n";
f+="classic_diff=Diff&eacute;rence entre les points du preneur et les points n&eacute;cessaires pour gagner la partie :\n";
f+="classic_rate=Coefficient en relation avec l''ench&egrave;re:\n";
f+="classic_score_taker=Score du preneur sans les annonces: ( {0} + {1} + {2} ) * {3} = {4} points\n";
f+="classic_decl=Calcul des annonces des joueurs\n";
f+="classic_decl_player=annonces de {0} ({1}): \n";
f+="sum=Somme :\n";
f+="classic_sum_player=Somme des annonces des joueurs:\n";
f+="classic_addon=Primes suppl&eacute;mentaires\n";
f+="classic_addon_att=Prime pour l''attaque:\n";
f+="classic_addon_def=Prime pour la d&eacute;fense:\n";
f+="classic_addon_sum=Somme des primes:\n";
f+="classic_rate_pl=Coefficient et scores de cette partie pour chaque joueur\n";
f+="player=Joueur\n";
f+="rate=Coefficient\n";
f+="score=Score\n";
f+="exp_rate=Ce coefficient est appliqu&eacute; au score du preneur\n";
f+="exp_score=Ce score est en relation lin&eacute;aire avec le score du preneur et le coefficient\n";
f+="variant_table_1=Etapes de calculs de la position des joueurs par crit&egrave;res\n";
f+="variant_table_1_1=Pseudo\n";
f+="variant_table_1_2=Classement li&eacute;e &agrave; la diff&eacute;rence de points\n";
f+="variant_table_1_3=Classement li&eacute;e au nombre de bouts\n";
f+="variant_table_1_4=Classement li&eacute;e au nombre de figures\n";
f+="variant_table_1_5=Classement li&eacute;e &agrave; la hauteur des figures\n";
f+="variant_table_1_6=Classement final\n";
f+="variant_table_2=Calcul des points des joueur\n";
f+="variant_table_2_1=Pseudo\n";
f+="variant_table_2_2=Points gagn&eacute;es dans les plis\n";
f+="variant_table_2_3=Score minimal pour gagner\n";
f+="variant_table_2_4=Diff&eacute;rences de points\n";
f+="variant_table_2_5=Coefficient\n";
f+="variant_table_2_6=Score\n";
f+="variant_decl=Calcul des annonces des joueurs\n";
f+="variant_decl_pl=annonce de {0}:\n";
f+="variant_add=Primes\n";
f+="variant_add_pl=Primes suppl&eacute;mentaires des joueurs\n";
f+="variant_add_pl_1=Joueur\n";
f+="variant_add_pl_2=Prime\n";
f+="classic_points=1 Calcul des points de l''attaque\n";
f+="classic_oulders=Nombre de bouts gagn&eacute;s dans les plis de l''attaque:\n";
f+="classic_need=Nombre de points n&eacute;cessaires pour que le preneur gagne:\n";
f+="classic_won=Nombre de points gagn&eacute;s dans les plis de l''attaque:\n";
f+="classic_att=2 Attaque\n";
f+="classic_taker=Preneur:\n";
f+="classic_parts=Partenaires du preneur:\n";
f+="classic_none_part=Aucun\n";
f+="classic_called=Cartes appel&eacute;es:\n";
f+="classic_none_called=Aucune\n";
f+="classic_bid_end=Ench&egrave;re:\n";
f+="classic_res=3 R&eacute;sultats:\n";
f+="variant_res=R&eacute;sultats\n";
f+="variant_res_1=La plus grande diff&eacute;rence de points:\n";
f+="variant_res_2=Votre position avant d&eacute;partage:\n";
f+="variant_res_3=Votre position finale:\n";
f+="variant_scores=Scores\n";
f+="beat_cards=Battre les cartes\n";
f+="dealing_pl=R&eacute;partition des joueurs\n";
f+="mode=Mode\n";
f+="discard=Ecart apr&egrave;s appel\n";
f+="bids=Ench&egrave;res au d&eacute;but de la partie\n";
f+="decls=Annonces autoris&eacute;es\n";
f+="hands=Poign&eacute;es\n";
f+="hand=Poign&eacute;e\n";
f+="nb=Nombre\n";
f+="mis=Mis&egrave;res autoris&eacute;es:\n";
f+="nothing=Rien\n";
f+="ending=Fin de partie\n";
f+="yes=oui\n";
f+="no=non\n";
return f;
}
}
