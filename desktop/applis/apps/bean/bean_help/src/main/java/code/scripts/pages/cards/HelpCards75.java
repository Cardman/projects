package code.scripts.pages.cards;
final class HelpCards75 extends HelpCardsCommon{
private HelpCards75(){}
static String en(){
String e="";
e+=M_4_0+"=Value of cards\n";
e+=M_4_1+"=trump:\n";
e+=M_4_2+"=Jack: 20 points.\n";
e+=M_4_3+"=9: 14 points.\n";
e+=M_4_4+"=Ace: 11 points.\n";
e+=M_4_5+"=10: 10 points.\n";
e+=M_4_6+"=King: 4 points.\n";
e+=M_4_7+"=Queen: 3 points.\n";
e+=M_4_8+"=8 and 7: 0 point.\n";
e+=M_4_9+"=suit:\n";
e+=M_4_10+"=Ace: 11 points.\n";
e+=M_4_12+"=King: 4 points.\n";
e+=M_4_13+"=Queen: 3 points.\n";
e+=M_4_14+"=Jack: 2 points.\n";
e+=M_4_15+"=9, 8 et 7: 0 point.\n";
e+=M_4_16+"=Calculation of scores\n";
e+=M_4_17+"=&#160;Nb means \"Nombre\".\n";
e+=M_4_18+"=&#160;pts means \"points\".\n";
e+=M_4_19+"=Ten of last trick, (Win the last trick): 10 points.\n";
e+=M_4_20+"=Belote rebelote: 20 points.\n";
e+=M_4_21+"=chelem (For the team of the taker, if the team wins all tricks): 100 points.\n";
e+=M_4_22+"=Success of bid:\n";
e+=M_4_23+"=&#160;&#160;Full points for a deal: 162 points.\n";
e+=M_4_24+"=&#160;&#160;Score of the taker without declaring points = Nb pts scored by the taker and the taker''s partner + Ten of last trick if the taker''s team wins the last trick.\n";
e+=M_4_25+"=&#160;&#160;Declaring = Belote rebelote\n";
e+=M_4_26+"=&#160;&#160;Temporary full score of the taker = Declaring of the taker''s team + Score of the taker without declaring point\n";
e+=M_4_27+"=&#160;&#160;If the belote rebelote has been declared during the deal:\n";
e+=M_4_28+"=&#160;&#160;&#160;If the full score of the taker is lower than 91 points,\n";
e+=M_4_29+"=&#160;&#160;&#160;&#160;then the full score is set to zero (The taker looses.).\n";
e+=M_4_30+"=&#160;&#160;&#160;Else the temporary full score of the taker becomes definitive.\n";
e+=M_4_31+"=&#160;&#160;Else\n";
e+=M_4_32+"=&#160;&#160;&#160;If the full score of the taker is lower than 81 points,\n";
e+=M_4_33+"=&#160;&#160;&#160;&#160;then the full score is set to zero (The taker looses.).\n";
e+=M_4_34+"=&#160;&#160;&#160;Else the temporary full score of the taker becomes definitive.\n";
e+=M_4_35+"=&#160;&#160;Full score of defender = 162 - definitive full score of the taker\n";
e+=M_4_36+"=&#160;&#160;If the taker looses and if the belote rebelote has been declared during the deal:\n";
e+=M_4_37+"=&#160;&#160;&#160;Then the full score of a defender is 182.\n";
e+=M_4_38+"=&#160;&#160;The taker''s partner and the taker win the same points.\n";
e+=M_4_39+"=&#160;&#160;If all tricks are won by the taker''s team,\n";
e+=M_4_40+"=&#160;&#160;&#160;Then, the chelem points are added to full score of each player of the taker''s team.\n";
e+=M_4_41+"=Examples:\n";
e+=M_4_42+"=If the taker''s team scores 75 points in one''s tricks,\n";
e+=M_4_43+"=&#160;&#160;if the taker''s team win the last trick,\n";
e+=M_4_44+"=&#160;&#160;if the defense owns the declaring belote rebelote,\n";
e+=M_4_45+"=&#160;&#160;then the taker has a temporary score of 85 points, that is under 91 points.\n";
e+=M_4_46+"=&#160;&#160;So the taker looses the deal and score zero points and one''s partner also, and each defender scores 182 points.\n";
e+=M_4_47+"=If the taker''s team scores 75 points in one''s tricks,\n";
e+=M_4_48+"=&#160;&#160;if the taker''s team win the last trick,\n";
e+=M_4_49+"=&#160;&#160;then the taker has a temporary score of 85 points, that is over 81 points.\n";
e+=M_4_50+"=&#160;&#160;So the taker wins the deal and scores 85 points and one''s partner also, and each defender scores 77 points.\n";
e+=M_4_51+"=If the taker''s team wins all tricks,\n";
e+=M_4_52+"=&#160;&#160;if the taker''s team owns the declaring belote rebelote,\n";
e+=M_4_53+"=&#160;&#160;then the taker has a temporary score of 182 points, that is over 81 points.\n";
e+=M_4_54+"=&#160;&#160;So the taker wins the deal and scores 282 points and one''s partner also, and each defender scores zero points.\n";
return e;
}
static String fr(){
String f="";
f+=M_4_0+"=Valeur des cartes\n";
f+=M_4_1+"=atout:\n";
f+=M_4_2+"=Valet: 20 points.\n";
f+=M_4_3+"=9: 14 points.\n";
f+=M_4_4+"=As: 11 points.\n";
f+=M_4_5+"=10: 10 points.\n";
f+=M_4_6+"=Roi: 4 points.\n";
f+=M_4_7+"=Dame: 3 points.\n";
f+=M_4_8+"=8 et 7: 0 point.\n";
f+=M_4_9+"=couleur:\n";
f+=M_4_10+"=As: 11 points.\n";
f+=M_4_12+"=Roi: 4 points.\n";
f+=M_4_13+"=Dame: 3 points.\n";
f+=M_4_14+"=Valet: 2 points.\n";
f+=M_4_15+"=9, 8 et 7: 0 point.\n";
f+=M_4_16+"=Calcul de scores\n";
f+=M_4_17+"=&#160;Nb signifie \"Nombre\".\n";
f+=M_4_18+"=&#160;pts signifie \"points\".\n";
f+=M_4_19+"=Dix de der, (Faire le dernier pli): 10 points.\n";
f+=M_4_20+"=Belote rebelote: 20 points.\n";
f+=M_4_21+"=capot (Pour l''&#233;quipe du preneur, si elle fait tous les plis): 100 points.\n";
f+=M_4_22+"=R&#233;ussite d''un contrat:\n";
f+=M_4_23+"=&#160;&#160;Total des points pour une partie: 162 points.\n";
f+=M_4_24+"=&#160;&#160;Score du preneur sans points d''annonces = Nb pts r&#233;alis&#233;s par le preneur et son partenaire + Dix de der si le preneur ou son partenaire fait le dernier pli.\n";
f+=M_4_25+"=&#160;&#160;Annonces = Belote rebelote\n";
f+=M_4_26+"=&#160;&#160;Score total temporaire du preneur = Annonces de l''&#233;quipe du preneur + Score du preneur sans points d''annonces\n";
f+=M_4_27+"=&#160;&#160;Si la belote rebelote a &#233;t&#233; annonc&#233;e au cours de la partie:\n";
f+=M_4_28+"=&#160;&#160;&#160;Si le score total du preneur ne d&#233;passe pas 91 points,\n";
f+=M_4_29+"=&#160;&#160;&#160;&#160;alors le score total du preneur est remis &#224; z&#233;ro (On dit que le preneur est dedans.).\n";
f+=M_4_30+"=&#160;&#160;&#160;Sinon le score total temporaire du preneur devient d&#233;finitif.\n";
f+=M_4_31+"=&#160;&#160;Sinon\n";
f+=M_4_32+"=&#160;&#160;&#160;Si le score total du preneur ne d&#233;passe pas 81 points,\n";
f+=M_4_33+"=&#160;&#160;&#160;&#160;alors le score total du preneur est remis &#224; z&#233;ro (On dit que le preneur est dedans.).\n";
f+=M_4_34+"=&#160;&#160;&#160;Sinon le score total temporaire du preneur devient d&#233;finitif.\n";
f+=M_4_35+"=&#160;&#160;Score total d''un d&#233;fenseur = 162 - score total d&#233;finitif du preneur\n";
f+=M_4_36+"=&#160;&#160;Si le preneur perd et si la belote rebelote a &#233;t&#233; annonc&#233;e au cours de la partie:\n";
f+=M_4_37+"=&#160;&#160;&#160;Alors le score total d''un d&#233;fenseur vaut 182.\n";
f+=M_4_38+"=&#160;&#160;Score total du partenaire du preneur = score total d&#233;finitif du preneur\n";
f+=M_4_39+"=&#160;&#160;Si tous les plis sont fait par l''&#233;quipe du preneur,\n";
f+=M_4_40+"=&#160;&#160;&#160;Alors, on ajoute le capot au score total du partenaire du preneur et au score total d&#233;finitif du preneur.\n";
f+=M_4_41+"=Exemples:\n";
f+=M_4_42+"=Si l''&#233;quipe du preneur marque 75 points dans ses plis,\n";
f+=M_4_43+"=&#160;&#160;si l''&#233;quipe du preneur fait le dernier pli,\n";
f+=M_4_44+"=&#160;&#160;si la d&#233;fense poss&#232;de l''annonce belote-rebelote,\n";
f+=M_4_45+"=&#160;&#160;alors le preneur a un score temporaire de 85 points, ce qui est en-dessous de 91 points.\n";
f+=M_4_46+"=&#160;&#160;Donc le preneur perd la partie et marque z&#233;ro points de score ainsi que son partenaite, et chaque d&#233;fenseur marque 182 points de score.\n";
f+=M_4_47+"=Si l''&#233;quipe du preneur marque 75 points dans ses plis,\n";
f+=M_4_48+"=&#160;&#160;si l''&#233;quipe du preneur fait le dernier pli,\n";
f+=M_4_49+"=&#160;&#160;alors le preneur a un score temporaire de 85 points, ce qui est au-dessus de 81 points.\n";
f+=M_4_50+"=&#160;&#160;Donc le preneur gagne la partie et marque 85 points de score ainsi que son partenaite, et chaque d&#233;fenseur marque 77 points de score.\n";
f+=M_4_51+"=Si l''&#233;quipe du preneur fait tous les plis,\n";
f+=M_4_52+"=&#160;&#160;si l''&#233;quipe du preneur poss&#232;de l''annonce belote-rebelote,\n";
f+=M_4_53+"=&#160;&#160;alors le preneur a un score temporaire de 182 points, ce qui est au-dessus de 81 points.\n";
f+=M_4_54+"=&#160;&#160;Donc le preneur gagne la partie et marque 282 points de score ainsi que son partenaite, et chaque d&#233;fenseur marque z&#233;ro points de score.\n";
return f;
}
}
