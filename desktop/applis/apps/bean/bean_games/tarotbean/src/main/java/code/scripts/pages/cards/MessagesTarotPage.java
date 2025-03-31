package code.scripts.pages.cards;

import code.sml.util.*;

public final class MessagesTarotPage {
    public static final String APP_BEAN = "tarot_bean";
    public static final String M_BEAT_CARDS="beat_cards";
    public static final String M_BIDS="bids";
    public static final String M_CLASSIC_ADDON="classic_addon";
    public static final String M_CLASSIC_ADDON_ATT="classic_addon_att";
    public static final String M_CLASSIC_ADDON_DEF="classic_addon_def";
    public static final String M_CLASSIC_ADDON_SUM="classic_addon_sum";
    public static final String M_CLASSIC_ATT="classic_att";
    public static final String M_CLASSIC_BASE="classic_base";
    public static final String M_CLASSIC_BID="classic_bid";
    public static final String M_CLASSIC_BID_END="classic_bid_end";
    public static final String M_CLASSIC_CALLED="classic_called";
    public static final String M_CLASSIC_DECL="classic_decl";
    public static final String M_CLASSIC_DECL_PLAYER="classic_decl_player";
    public static final String M_CLASSIC_DIFF="classic_diff";
    public static final String M_CLASSIC_NEED="classic_need";
    public static final String M_CLASSIC_NONE_CALLED="classic_none_called";
    public static final String M_CLASSIC_NONE_PART="classic_none_part";
    public static final String M_CLASSIC_OULDERS="classic_oulders";
    public static final String M_CLASSIC_PARTS="classic_parts";
    public static final String M_CLASSIC_POINTS="classic_points";
    public static final String M_CLASSIC_RATE="classic_rate";
    public static final String M_CLASSIC_RATE_PL="classic_rate_pl";
    public static final String M_CLASSIC_RES="classic_res";
    public static final String M_CLASSIC_SCORE_TAKER="classic_score_taker";
    public static final String M_CLASSIC_SMALL="classic_small";
    public static final String M_CLASSIC_SUM_PLAYER="classic_sum_player";
    public static final String M_CLASSIC_TAKER="classic_taker";
    public static final String M_CLASSIC_WON="classic_won";
    public static final String M_DEALING_PL="dealing_pl";
    public static final String M_DECLS="decls";
    public static final String M_DETAILSFAILEDDECLAREDSLAM="detailsFailedDeclaredSlam";
    public static final String M_DETAILSSUCCESSFULDECLAREDSLAM="detailsSuccessfulDeclaredSlam";
    public static final String M_DETAILSSUCCESSFULNODECLAREDSLAM="detailsSuccessfulNoDeclaredSlam";
    public static final String M_DETAILSSUCCESSFULSLAM="detailsSuccessfulSlam";
    public static final String M_DISCARD="discard";
    public static final String M_PLAY_CALLED="played_called";
    public static final String M_ENDING="ending";
    public static final String M_EQUALITY="equality";
    public static final String M_EXP_RATE="exp_rate";
    public static final String M_EXP_SCORE="exp_score";
    public static final String M_FAILED="failed";
    public static final String M_FAILEDDECLAREDSLAM="failedDeclaredSlam";
    public static final String M_HAND="hand";
    public static final String M_HANDS="hands";
    public static final String M_LOOSE="loose";
    public static final String M_MID="mid";
    public static final String M_MIS="mis";
    public static final String M_MODE="mode";
    public static final String M_NB="nb";
    public static final String M_NO="no";
    public static final String M_NOSLAM="noSlam";
    public static final String M_NOSLAMATTACK="noSlamAttack";
    public static final String M_SLAMDEFENSE="slamDefense";
    public static final String M_NOSLAMDEFENSE="noSlamDefense";
    public static final String M_NOTHING="nothing";
    public static final String M_PLAYER="player";
    public static final String M_RATE="rate";
    public static final String M_RESULTS="results";
    public static final String M_SCORE="score";
    public static final String M_SLAM="slam";
    public static final String M_SUCCESSFUL="successful";
    public static final String M_SUCCESSFULDECLAREDSLAM="successfulDeclaredSlam";
    public static final String M_SUCCESSFULNODECLAREDSLAM="successfulNoDeclaredSlam";
    public static final String M_SUCCESSFULSLAM="successfulSlam";
    public static final String M_SUM="sum";
    public static final String M_VARIANT_ADD="variant_add";
    public static final String M_VARIANT_ADD_PL="variant_add_pl";
    public static final String M_VARIANT_ADD_PL_1="variant_add_pl_1";
    public static final String M_VARIANT_ADD_PL_2="variant_add_pl_2";
    public static final String M_VARIANT_DECL="variant_decl";
    public static final String M_VARIANT_DECL_PL="variant_decl_pl";
    public static final String M_VARIANT_RES="variant_res";
    public static final String M_VARIANT_RES_1="variant_res_1";
    public static final String M_VARIANT_RES_2="variant_res_2";
    public static final String M_VARIANT_RES_3="variant_res_3";
    public static final String M_VARIANT_SCORES="variant_scores";
    public static final String M_VARIANT_TABLE_1="variant_table_1";
    public static final String M_VARIANT_TABLE_1_1="variant_table_1_1";
    public static final String M_VARIANT_TABLE_1_2="variant_table_1_2";
    public static final String M_VARIANT_TABLE_1_3="variant_table_1_3";
    public static final String M_VARIANT_TABLE_1_4="variant_table_1_4";
    public static final String M_VARIANT_TABLE_1_5="variant_table_1_5";
    public static final String M_VARIANT_TABLE_1_6="variant_table_1_6";
    public static final String M_VARIANT_TABLE_2="variant_table_2";
    public static final String M_VARIANT_TABLE_2_1="variant_table_2_1";
    public static final String M_VARIANT_TABLE_2_2="variant_table_2_2";
    public static final String M_VARIANT_TABLE_2_3="variant_table_2_3";
    public static final String M_VARIANT_TABLE_2_4="variant_table_2_4";
    public static final String M_VARIANT_TABLE_2_5="variant_table_2_5";
    public static final String M_VARIANT_TABLE_2_6="variant_table_2_6";
    public static final String M_WIN="win";
    public static final String M_YES="yes";
    public static final String SCORE = "Score";

    private MessagesTarotPage(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_BEAT_CARDS,"Mix Cards");
        e_.add(M_BIDS,"Allowed Bids at the beginning of the deal");
        e_.add(M_CLASSIC_ADDON,"Additional bonuses");
        e_.add(M_CLASSIC_ADDON_ATT,"Bonuses for attack team:");
        e_.add(M_CLASSIC_ADDON_DEF,"Bonuses for defense team:");
        e_.add(M_CLASSIC_ADDON_SUM,"Sum of additional bonuses:");
        e_.add(M_CLASSIC_ATT,"2 Attack team");
        e_.add(M_CLASSIC_BASE,"Base points for bidding:");
        e_.add(M_CLASSIC_BID,"Calculation of bidding points");
        e_.add(M_CLASSIC_BID_END,"Bid:");
        e_.add(M_CLASSIC_CALLED,"Called cards:");
        e_.add(M_CLASSIC_DECL,"Calculation of players's declaring");
        e_.add(M_CLASSIC_DECL_PLAYER,"{0}''s declaring ({1}):");
        e_.add(M_CLASSIC_DIFF,"Difference between taker's points and necessary points in order to win this deal:");
        e_.add(M_CLASSIC_NEED,"Number of necessary points in order that the taker wins:");
        e_.add(M_CLASSIC_NONE_CALLED,"Noone");
        e_.add(M_CLASSIC_NONE_PART,"Noone");
        e_.add(M_CLASSIC_OULDERS,"Number of oudlers won in the attack team's tricks:");
        e_.add(M_CLASSIC_PARTS,"Taker's partners:");
        e_.add(M_CLASSIC_POINTS,"1 Calculation of attack team's points");
        e_.add(M_CLASSIC_RATE,"Rate in relationship with bidding:");
        e_.add(M_CLASSIC_RATE_PL,"Rates and scores of this deal for each player");
        e_.add(M_CLASSIC_RES,"3 Results");
        e_.add(M_CLASSIC_SCORE_TAKER,"Taker''s score without declaring: ( {0} + {1} + {2} ) * {3} = {4} points");
        e_.add(M_CLASSIC_SMALL,"Player who has led the trump ace to the last trick:");
        e_.add(M_CLASSIC_SUM_PLAYER,"Sum of players' declaring:");
        e_.add(M_CLASSIC_TAKER,"Taker:");
        e_.add(M_CLASSIC_WON,"Number of points won in the attack team's tricks:");
        e_.add(M_DEALING_PL,"Players' repartition");
        e_.add(M_DECLS,"Allowed declaring");
        e_.add(M_DETAILSFAILEDDECLAREDSLAM,"Failed slam (attack): {0}.");
        e_.add(M_DETAILSSUCCESSFULDECLAREDSLAM,"Successful declared slam (attack): {0}.");
        e_.add(M_DETAILSSUCCESSFULNODECLAREDSLAM,"Successful no declared slam (attack): {0}.");
        e_.add(M_DETAILSSUCCESSFULSLAM,"Successful slam (defense): {0}.");
        e_.add(M_DISCARD,"Discarding after call");
        e_.add(M_PLAY_CALLED,"Allow playing the called suit at the first round");
        e_.add(M_ENDING,"End of game");
        e_.add(M_EQUALITY,"Equality.");
        e_.add(M_EXP_RATE,"This rate is applied on taker's score");
        e_.add(M_EXP_SCORE,"This score belongs to a linear relationship with taker's score and the rate");
        e_.add(M_FAILED,"The bid {0} is failed of {1} points.");
        e_.add(M_FAILEDDECLAREDSLAM,"The attack's team has failed the grand slam by declaring it.");
        e_.add(M_HAND,"Handful");
        e_.add(M_HANDS,"Handfuls");
        e_.add(M_LOOSE,"You loose.");
        e_.add(M_MID,"The bid {0} is neither passed nor failed.");
        e_.add(M_MIS,"Allowed miseres:");
        e_.add(M_MODE,"Mode");
        e_.add(M_NB,"Number");
        e_.add(M_NO,"no");
        e_.add(M_NOSLAM,"The attack's team has not won all tricks.");
        e_.add(M_NOSLAMATTACK,"The attack's team has not won all tricks.");
        e_.add(M_SLAMDEFENSE,"The defense's team has won all tricks.");
        e_.add(M_NOSLAMDEFENSE,"The defense's team has not won all tricks.");
        e_.add(M_NOTHING,"Nothing");
        e_.add(M_PLAYER,"Player");
        e_.add(M_RATE,"Rate");
        e_.add(M_RESULTS,"Results");
        e_.add(M_SCORE, SCORE);
        e_.add(M_SLAM,"The attack's team has achieved the grand slam.");
        e_.add(M_SUCCESSFUL,"The bid {0} is passed of {1} points.");
        e_.add(M_SUCCESSFULDECLAREDSLAM,"The attack's team has achieved the grand slam by declaring it.");
        e_.add(M_SUCCESSFULNODECLAREDSLAM,"The attack's team has achieved the grand slam without declaring it.");
        e_.add(M_SUCCESSFULSLAM,"The defense's team has achieved the grand slam.");
        e_.add(M_SUM,"Sum :");
        e_.add(M_VARIANT_ADD,"Bonuses");
        e_.add(M_VARIANT_ADD_PL,"Players' additional bonuses");
        e_.add(M_VARIANT_ADD_PL_1,"Nickname");
        e_.add(M_VARIANT_ADD_PL_2,"Bonus");
        e_.add(M_VARIANT_DECL,"Calculation of players's declaring");
        e_.add(M_VARIANT_DECL_PL,"{0}''s declaring:");
        e_.add(M_VARIANT_RES,"Results");
        e_.add(M_VARIANT_RES_1,"The greatest difference of points:");
        e_.add(M_VARIANT_RES_2,"Your position before deciding:");
        e_.add(M_VARIANT_RES_3,"Your final position:");
        e_.add(M_VARIANT_SCORES,"Scores");
        e_.add(M_VARIANT_TABLE_1,"Steps of calculation of players' ranking by criteria");
        e_.add(M_VARIANT_TABLE_1_1,"Player");
        e_.add(M_VARIANT_TABLE_1_2,"Ranking in relationship with the difference of points");
        e_.add(M_VARIANT_TABLE_1_3,"Ranking in relationship with the number of oudlers");
        e_.add(M_VARIANT_TABLE_1_4,"Ranking in relationship with the number of characters");
        e_.add(M_VARIANT_TABLE_1_5,"Ranking in relationship with the strength of characters");
        e_.add(M_VARIANT_TABLE_1_6,"Final rank");
        e_.add(M_VARIANT_TABLE_2,"Calculation of players' points");
        e_.add(M_VARIANT_TABLE_2_1,"Nickname");
        e_.add(M_VARIANT_TABLE_2_2,"Won points in the tricks");
        e_.add(M_VARIANT_TABLE_2_3,"Minimum score for winning");
        e_.add(M_VARIANT_TABLE_2_4,"Differences of points");
        e_.add(M_VARIANT_TABLE_2_5,"Rate");
        e_.add(M_VARIANT_TABLE_2_6, SCORE);
        e_.add(M_WIN,"You win.");
        e_.add(M_YES,"yes");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_BEAT_CARDS,"Battre les cartes");
        f_.add(M_BIDS,"Enchères au début de la partie");
        f_.add(M_CLASSIC_ADDON,"Primes supplémentaires");
        f_.add(M_CLASSIC_ADDON_ATT,"Prime pour l'attaque:");
        f_.add(M_CLASSIC_ADDON_DEF,"Prime pour la défense:");
        f_.add(M_CLASSIC_ADDON_SUM,"Somme des primes:");
        f_.add(M_CLASSIC_ATT,"2 Attaque");
        f_.add(M_CLASSIC_BASE,"Base de points pour l'enchère:");
        f_.add(M_CLASSIC_BID,"Calcul des points liés à l'enchère");
        f_.add(M_CLASSIC_BID_END,"Enchère:");
        f_.add(M_CLASSIC_CALLED,"Cartes appelées:");
        f_.add(M_CLASSIC_DECL,"Calcul des annonces des joueurs");
        f_.add(M_CLASSIC_DECL_PLAYER,"annonces de {0} ({1}): ");
        f_.add(M_CLASSIC_DIFF,"Différence entre les points du preneur et les points nécessaires pour gagner la partie :");
        f_.add(M_CLASSIC_NEED,"Nombre de points nécessaires pour que le preneur gagne:");
        f_.add(M_CLASSIC_NONE_CALLED,"Aucune");
        f_.add(M_CLASSIC_NONE_PART,"Aucun");
        f_.add(M_CLASSIC_OULDERS,"Nombre de bouts gagnés dans les plis de l'attaque:");
        f_.add(M_CLASSIC_PARTS,"Partenaires du preneur:");
        f_.add(M_CLASSIC_POINTS,"1 Calcul des points de l'attaque");
        f_.add(M_CLASSIC_RATE,"Coefficient en relation avec l'enchère:");
        f_.add(M_CLASSIC_RATE_PL,"Coefficient et scores de cette partie pour chaque joueur");
        f_.add(M_CLASSIC_RES,"3 Résultats:");
        f_.add(M_CLASSIC_SCORE_TAKER,"Score du preneur sans les annonces: ( {0} + {1} + {2} ) * {3} = {4} points");
        f_.add(M_CLASSIC_SMALL,"Joueur ayant mené le Petit au bout:");
        f_.add(M_CLASSIC_SUM_PLAYER,"Somme des annonces des joueurs:");
        f_.add(M_CLASSIC_TAKER,"Preneur:");
        f_.add(M_CLASSIC_WON,"Nombre de points gagnés dans les plis de l'attaque:");
        f_.add(M_DEALING_PL,"Répartition des joueurs");
        f_.add(M_DECLS,"Annonces autorisées");
        f_.add(M_DETAILSFAILEDDECLAREDSLAM,"Chelem chuté (attack): {0}.");
        f_.add(M_DETAILSSUCCESSFULDECLAREDSLAM,"Chelem demandé et réussi (attaque): {0}.");
        f_.add(M_DETAILSSUCCESSFULNODECLAREDSLAM,"Chelem non demandé et réussi (attaque): {0}.");
        f_.add(M_DETAILSSUCCESSFULSLAM,"Chelem réussi (défense): {0}.");
        f_.add(M_DISCARD,"Ecart après appel");
        f_.add(M_PLAY_CALLED,"Autoriser le jeu de la couleur appelée au premier tour");
        f_.add(M_ENDING,"Fin de partie");
        f_.add(M_EQUALITY,"Match nul.");
        f_.add(M_EXP_RATE,"Ce coefficient est appliqué au score du preneur");
        f_.add(M_EXP_SCORE,"Ce score est en relation linéaire avec le score du preneur et le coefficient");
        f_.add(M_FAILED,"L''enchère {0} est chutée de {1} points.");
        f_.add(M_FAILEDDECLAREDSLAM,"L'attaque n'a pas réussi le grand chelem en l'annonçant.");
        f_.add(M_HAND,"Poignée");
        f_.add(M_HANDS,"Poignées");
        f_.add(M_LOOSE,"Vous perdez.");
        f_.add(M_MID,"L''enchère {0} n''est ni réussie ni chutée.");
        f_.add(M_MIS,"Misères autorisées:");
        f_.add(M_MODE,"Mode");
        f_.add(M_NB,"Nombre");
        f_.add(M_NO,"non");
        f_.add(M_NOSLAM,"L'attaque n'a pas réussi de capot.");
        f_.add(M_NOSLAMATTACK,"L'attaque n'a pas gagné tous les plis.");
        f_.add(M_SLAMDEFENSE,"La défense a gagné tous les plis.");
        f_.add(M_NOSLAMDEFENSE,"La défense n'a pas gagné tous les plis.");
        f_.add(M_NOTHING,"Rien");
        f_.add(M_PLAYER,"Joueur");
        f_.add(M_RATE,"Coefficient");
        f_.add(M_RESULTS,"Résultats");
        f_.add(M_SCORE, SCORE);
        f_.add(M_SLAM,"L'attaque a réussi un capot.");
        f_.add(M_SUCCESSFUL,"L''enchère {0} est réussie de {1} points.");
        f_.add(M_SUCCESSFULDECLAREDSLAM,"L'attaque a réussi le grand chelem en l'annonçant.");
        f_.add(M_SUCCESSFULNODECLAREDSLAM,"L'attaque a réussi le grand chelem sans l'annoncer.");
        f_.add(M_SUCCESSFULSLAM,"La défense a réussi le grand chelem.");
        f_.add(M_SUM,"Somme :");
        f_.add(M_VARIANT_ADD,"Primes");
        f_.add(M_VARIANT_ADD_PL,"Primes supplémentaires des joueurs");
        f_.add(M_VARIANT_ADD_PL_1,"Joueur");
        f_.add(M_VARIANT_ADD_PL_2,"Prime");
        f_.add(M_VARIANT_DECL,"Calcul des annonces des joueurs");
        f_.add(M_VARIANT_DECL_PL,"annonce de {0}:");
        f_.add(M_VARIANT_RES,"Résultats");
        f_.add(M_VARIANT_RES_1,"La plus grande différence de points:");
        f_.add(M_VARIANT_RES_2,"Votre position avant départage:");
        f_.add(M_VARIANT_RES_3,"Votre position finale:");
        f_.add(M_VARIANT_SCORES,"Scores");
        f_.add(M_VARIANT_TABLE_1,"Etapes de calculs de la position des joueurs par critères");
        f_.add(M_VARIANT_TABLE_1_1,"Pseudo");
        f_.add(M_VARIANT_TABLE_1_2,"Classement liée à la différence de points");
        f_.add(M_VARIANT_TABLE_1_3,"Classement liée au nombre de bouts");
        f_.add(M_VARIANT_TABLE_1_4,"Classement liée au nombre de figures");
        f_.add(M_VARIANT_TABLE_1_5,"Classement liée à la hauteur des figures");
        f_.add(M_VARIANT_TABLE_1_6,"Classement final");
        f_.add(M_VARIANT_TABLE_2,"Calcul des points des joueur");
        f_.add(M_VARIANT_TABLE_2_1,"Pseudo");
        f_.add(M_VARIANT_TABLE_2_2,"Points gagnées dans les plis");
        f_.add(M_VARIANT_TABLE_2_3,"Score minimal pour gagner");
        f_.add(M_VARIANT_TABLE_2_4,"Différences de points");
        f_.add(M_VARIANT_TABLE_2_5,"Coefficient");
        f_.add(M_VARIANT_TABLE_2_6, SCORE);
        f_.add(M_WIN,"Vous gagnez.");
        f_.add(M_YES,"oui");
        return f_;
    }
}