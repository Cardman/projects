package code.scripts.pages.cards;
import code.util.*;
public final class MessTarotPage{
private static final String C_0_2_0="You win.\n";
private static final String C_0_2_100="La d&eacute;fense a r&eacute;ussi le grand chelem.\n";
private static final String C_0_2_101="L''attaque n''a pas r&eacute;ussi le grand chelem en l''annon&ccedil;ant.\n";
private static final String C_0_2_102="L''attaque n''a pas gagn&eacute; tous les plis.\n";
private static final String C_0_2_103="La d&eacute;fense n''a pas gagn&eacute; tous les plis.\n";
private static final String C_0_2_104="Chelem demand&eacute; et r&eacute;ussi (attaque): {0}.\n";
private static final String C_0_2_105="Chelem non demand&eacute; et r&eacute;ussi (attaque): {0}.\n";
private static final String C_0_2_106="Chelem r&eacute;ussi (d&eacute;fense): {0}.\n";
private static final String C_0_2_107="Chelem chut&eacute; (attack): {0}.\n";
private static final String C_0_2_108="R&eacute;sultats\n";
private static final String C_0_2_109="Calcul des points li&eacute;s &agrave; l''ench&egrave;re\n";
private static final String C_0_2_10="The defense''s team has achieved the grand slam.\n";
private static final String C_0_2_110="Base de points pour l''ench&egrave;re:\n";
private static final String C_0_2_111="Joueur ayant men&eacute; le Petit au bout:\n";
private static final String C_0_2_112="Diff&eacute;rence entre les points du preneur et les points n&eacute;cessaires pour gagner la partie :\n";
private static final String C_0_2_113="Coefficient en relation avec l''ench&egrave;re:\n";
private static final String C_0_2_114="Score du preneur sans les annonces: ( {0} + {1} + {2} ) * {3} = {4} points\n";
private static final String C_0_2_115="Calcul des annonces des joueurs\n";
private static final String C_0_2_116="annonces de {0} ({1}): \n";
private static final String C_0_2_117="Somme :\n";
private static final String C_0_2_118="Somme des annonces des joueurs:\n";
private static final String C_0_2_119="Primes suppl&eacute;mentaires\n";
private static final String C_0_2_11="The attack''s team has failed the grand slam by declaring it.\n";
private static final String C_0_2_120="Prime pour l''attaque:\n";
private static final String C_0_2_121="Prime pour la d&eacute;fense:\n";
private static final String C_0_2_122="Somme des primes:\n";
private static final String C_0_2_123="Coefficient et scores de cette partie pour chaque joueur\n";
private static final String C_0_2_124="Joueur\n";
private static final String C_0_2_125="Coefficient\n";
private static final String C_0_2_126="Score\n";
private static final String C_0_2_127="Ce coefficient est appliqu&eacute; au score du preneur\n";
private static final String C_0_2_128="Ce score est en relation lin&eacute;aire avec le score du preneur et le coefficient\n";
private static final String C_0_2_129="Etapes de calculs de la position des joueurs par crit&egrave;res\n";
private static final String C_0_2_12="The attack''s team has not won all tricks.\n";
private static final String C_0_2_130="Pseudo\n";
private static final String C_0_2_131="Classement li&eacute;e &agrave; la diff&eacute;rence de points\n";
private static final String C_0_2_132="Classement li&eacute;e au nombre de bouts\n";
private static final String C_0_2_133="Classement li&eacute;e au nombre de figures\n";
private static final String C_0_2_134="Classement li&eacute;e &agrave; la hauteur des figures\n";
private static final String C_0_2_135="Classement final\n";
private static final String C_0_2_136="Calcul des points des joueur\n";
private static final String C_0_2_137="Pseudo\n";
private static final String C_0_2_138="Points gagn&eacute;es dans les plis\n";
private static final String C_0_2_139="Score minimal pour gagner\n";
private static final String C_0_2_13="The defense''s team has not won all tricks.\n";
private static final String C_0_2_140="Diff&eacute;rences de points\n";
private static final String C_0_2_141="Coefficient\n";
private static final String C_0_2_142="Score\n";
private static final String C_0_2_143="Calcul des annonces des joueurs\n";
private static final String C_0_2_144="annonce de {0}:\n";
private static final String C_0_2_145="Primes\n";
private static final String C_0_2_146="Primes suppl&eacute;mentaires des joueurs\n";
private static final String C_0_2_147="Joueur\n";
private static final String C_0_2_148="Prime\n";
private static final String C_0_2_149="1 Calcul des points de l''attaque\n";
private static final String C_0_2_14="Successful declared slam (attack): {0}.\n";
private static final String C_0_2_150="Nombre de bouts gagn&eacute;s dans les plis de l''attaque:\n";
private static final String C_0_2_151="Nombre de points n&eacute;cessaires pour que le preneur gagne:\n";
private static final String C_0_2_152="Nombre de points gagn&eacute;s dans les plis de l''attaque:\n";
private static final String C_0_2_153="2 Attaque\n";
private static final String C_0_2_154="Preneur:\n";
private static final String C_0_2_155="Partenaires du preneur:\n";
private static final String C_0_2_156="Aucun\n";
private static final String C_0_2_157="Cartes appel&eacute;es:\n";
private static final String C_0_2_158="Aucune\n";
private static final String C_0_2_159="Ench&egrave;re:\n";
private static final String C_0_2_15="Successful no declared slam (attack): {0}.\n";
private static final String C_0_2_160="3 R&eacute;sultats:\n";
private static final String C_0_2_161="R&eacute;sultats\n";
private static final String C_0_2_162="La plus grande diff&eacute;rence de points:\n";
private static final String C_0_2_163="Votre position avant d&eacute;partage:\n";
private static final String C_0_2_164="Votre position finale:\n";
private static final String C_0_2_165="Scores\n";
private static final String C_0_2_166="Battre les cartes\n";
private static final String C_0_2_167="R&eacute;partition des joueurs\n";
private static final String C_0_2_168="Mode\n";
private static final String C_0_2_169="Ecart apr&egrave;s appel\n";
private static final String C_0_2_16="Successful slam (defense): {0}.\n";
private static final String C_0_2_170="Ench&egrave;res au d&eacute;but de la partie\n";
private static final String C_0_2_171="Annonces autoris&eacute;es\n";
private static final String C_0_2_172="Poign&eacute;es\n";
private static final String C_0_2_173="Poign&eacute;e\n";
private static final String C_0_2_174="Nombre\n";
private static final String C_0_2_175="Mis&egrave;res autoris&eacute;es:\n";
private static final String C_0_2_176="Rien\n";
private static final String C_0_2_177="Fin de partie\n";
private static final String C_0_2_178="oui\n";
private static final String C_0_2_179="non\n";
private static final String C_0_2_17="Failed slam (attack): {0}.\n";
private static final String C_0_2_18="Results\n";
private static final String C_0_2_19="Calculation of bidding points\n";
private static final String C_0_2_1="Equality.\n";
private static final String C_0_2_20="Base points for bidding:\n";
private static final String C_0_2_21="Player who has led the trump ace to the last trick:\n";
private static final String C_0_2_22="Difference between taker''s points and necessary points in order to win this deal:\n";
private static final String C_0_2_23="Rate in relationship with bidding:\n";
private static final String C_0_2_24="Taker''s score without declaring: ( {0} + {1} + {2} ) * {3} = {4} points\n";
private static final String C_0_2_25="Calculation of players''s declaring\n";
private static final String C_0_2_26="{0}''s declaring ({1}):\n";
private static final String C_0_2_27="Sum :\n";
private static final String C_0_2_28="Sum of players'' declaring:\n";
private static final String C_0_2_29="Additional bonuses\n";
private static final String C_0_2_2="You loose.\n";
private static final String C_0_2_30="Bonuses for attack team:\n";
private static final String C_0_2_31="Bonuses for defense team:\n";
private static final String C_0_2_32="Sum of additional bonuses:\n";
private static final String C_0_2_33="Rates and scores of this deal for each player\n";
private static final String C_0_2_34="Player\n";
private static final String C_0_2_35="Rate\n";
private static final String C_0_2_36="Score\n";
private static final String C_0_2_37="This rate is applied on taker''s score\n";
private static final String C_0_2_38="This score belongs to a linear relationship with taker''s score and the rate\n";
private static final String C_0_2_39="Steps of calculation of players'' ranking by criteria\n";
private static final String C_0_2_3="The bid {0} is passed of {1} points.\n";
private static final String C_0_2_40="Player\n";
private static final String C_0_2_41="Ranking in relationship with the difference of points\n";
private static final String C_0_2_42="Ranking in relationship with the number of oudlers\n";
private static final String C_0_2_43="Ranking in relationship with the number of characters\n";
private static final String C_0_2_44="Ranking in relationship with the strength of characters\n";
private static final String C_0_2_45="Final rank\n";
private static final String C_0_2_46="Calculation of players'' points\n";
private static final String C_0_2_47="Nickname\n";
private static final String C_0_2_48="Won points in the tricks\n";
private static final String C_0_2_49="Minimum score for winning\n";
private static final String C_0_2_4="The bid {0} is failed of {1} points.\n";
private static final String C_0_2_50="Differences of points\n";
private static final String C_0_2_51="Rate\n";
private static final String C_0_2_52="Score\n";
private static final String C_0_2_53="Calculation of players''s declaring\n";
private static final String C_0_2_54="{0}''s declaring:\n";
private static final String C_0_2_55="Bonuses\n";
private static final String C_0_2_56="Players'' additional bonuses\n";
private static final String C_0_2_57="Nickname\n";
private static final String C_0_2_58="Bonus\n";
private static final String C_0_2_59="1 Calculation of attack team''s points\n";
private static final String C_0_2_5="The bid {0} is neither passed nor failed.\n";
private static final String C_0_2_60="Number of oudlers won in the attack team''s tricks:\n";
private static final String C_0_2_61="Number of necessary points in order that the taker wins:\n";
private static final String C_0_2_62="Number of points won in the attack team''s tricks:\n";
private static final String C_0_2_63="2 Attack team\n";
private static final String C_0_2_64="Taker:\n";
private static final String C_0_2_65="Taker''s partners:\n";
private static final String C_0_2_66="Noone\n";
private static final String C_0_2_67="Called cards:\n";
private static final String C_0_2_68="Noone\n";
private static final String C_0_2_69="Bid:\n";
private static final String C_0_2_6="The attack''s team has achieved the grand slam.\n";
private static final String C_0_2_70="3 Results\n";
private static final String C_0_2_71="Results\n";
private static final String C_0_2_72="The greatest difference of points:\n";
private static final String C_0_2_73="Your position before deciding:\n";
private static final String C_0_2_74="Your final position:\n";
private static final String C_0_2_75="Scores\n";
private static final String C_0_2_76="Mix Cards\n";
private static final String C_0_2_77="Players'' repartition\n";
private static final String C_0_2_78="Mode\n";
private static final String C_0_2_79="Discarding after call\n";
private static final String C_0_2_7="The attack''s team has not won all tricks.\n";
private static final String C_0_2_80="Allowed Bids at the beginning of the deal\n";
private static final String C_0_2_81="Allowed declaring\n";
private static final String C_0_2_82="Handfuls\n";
private static final String C_0_2_83="Handful\n";
private static final String C_0_2_84="Number\n";
private static final String C_0_2_85="Allowed miseres:\n";
private static final String C_0_2_86="Nothing\n";
private static final String C_0_2_87="End of game\n";
private static final String C_0_2_88="yes\n";
private static final String C_0_2_89="no\n";
private static final String C_0_2_8="The attack''s team has achieved the grand slam by declaring it.\n";
private static final String C_0_2_90="Vous gagnez.\n";
private static final String C_0_2_91="Match nul.\n";
private static final String C_0_2_92="Vous perdez.\n";
private static final String C_0_2_93="L''ench&egrave;re {0} est r&eacute;ussie de {1} points.\n";
private static final String C_0_2_94="L''ench&egrave;re {0} est chut&eacute;e de {1} points.\n";
private static final String C_0_2_95="L''ench&egrave;re {0} n''est ni r&eacute;ussie ni chut&eacute;e.\n";
private static final String C_0_2_96="L''attaque a r&eacute;ussi un capot.\n";
private static final String C_0_2_97="L''attaque n''a pas r&eacute;ussi de capot.\n";
private static final String C_0_2_98="L''attaque a r&eacute;ussi le grand chelem en l''annon&ccedil;ant.\n";
private static final String C_0_2_99="L''attaque a r&eacute;ussi le grand chelem sans l''annoncer.\n";
private static final String C_0_2_9="The attack''s team has achieved the grand slam without declaring it.\n";
private static final String M_BEAT_CARDS="beat_cards";
private static final String M_BIDS="bids";
private static final String M_CLASSIC_ADDON="classic_addon";
private static final String M_CLASSIC_ADDON_ATT="classic_addon_att";
private static final String M_CLASSIC_ADDON_DEF="classic_addon_def";
private static final String M_CLASSIC_ADDON_SUM="classic_addon_sum";
private static final String M_CLASSIC_ATT="classic_att";
private static final String M_CLASSIC_BASE="classic_base";
private static final String M_CLASSIC_BID="classic_bid";
private static final String M_CLASSIC_BID_END="classic_bid_end";
private static final String M_CLASSIC_CALLED="classic_called";
private static final String M_CLASSIC_DECL="classic_decl";
private static final String M_CLASSIC_DECL_PLAYER="classic_decl_player";
private static final String M_CLASSIC_DIFF="classic_diff";
private static final String M_CLASSIC_NEED="classic_need";
private static final String M_CLASSIC_NONE_CALLED="classic_none_called";
private static final String M_CLASSIC_NONE_PART="classic_none_part";
private static final String M_CLASSIC_OULDERS="classic_oulders";
private static final String M_CLASSIC_PARTS="classic_parts";
private static final String M_CLASSIC_POINTS="classic_points";
private static final String M_CLASSIC_RATE="classic_rate";
private static final String M_CLASSIC_RATE_PL="classic_rate_pl";
private static final String M_CLASSIC_RES="classic_res";
private static final String M_CLASSIC_SCORE_TAKER="classic_score_taker";
private static final String M_CLASSIC_SMALL="classic_small";
private static final String M_CLASSIC_SUM_PLAYER="classic_sum_player";
private static final String M_CLASSIC_TAKER="classic_taker";
private static final String M_CLASSIC_WON="classic_won";
private static final String M_DEALING_PL="dealing_pl";
private static final String M_DECLS="decls";
private static final String M_DETAILSFAILEDDECLAREDSLAM="detailsFailedDeclaredSlam";
private static final String M_DETAILSSUCCESSFULDECLAREDSLAM="detailsSuccessfulDeclaredSlam";
private static final String M_DETAILSSUCCESSFULNODECLAREDSLAM="detailsSuccessfulNoDeclaredSlam";
private static final String M_DETAILSSUCCESSFULSLAM="detailsSuccessfulSlam";
private static final String M_DISCARD="discard";
private static final String M_ENDING="ending";
private static final String M_EQUALITY="equality";
private static final String M_EXP_RATE="exp_rate";
private static final String M_EXP_SCORE="exp_score";
private static final String M_FAILED="failed";
private static final String M_FAILEDDECLAREDSLAM="failedDeclaredSlam";
private static final String M_HAND="hand";
private static final String M_HANDS="hands";
private static final String M_LOOSE="loose";
private static final String M_MID="mid";
private static final String M_MIS="mis";
private static final String M_MODE="mode";
private static final String M_NB="nb";
private static final String M_NO="no";
private static final String M_NOSLAM="noSlam";
private static final String M_NOSLAMATTACK="noSlamAttack";
private static final String M_NOSLAMDEFENSE="noSlamDefense";
private static final String M_NOTHING="nothing";
private static final String M_PLAYER="player";
private static final String M_RATE="rate";
private static final String M_RESULTS="results";
private static final String M_SCORE="score";
private static final String M_SLAM="slam";
private static final String M_SUCCESSFUL="successful";
private static final String M_SUCCESSFULDECLAREDSLAM="successfulDeclaredSlam";
private static final String M_SUCCESSFULNODECLAREDSLAM="successfulNoDeclaredSlam";
private static final String M_SUCCESSFULSLAM="successfulSlam";
private static final String M_SUM="sum";
private static final String M_VARIANT_ADD="variant_add";
private static final String M_VARIANT_ADD_PL="variant_add_pl";
private static final String M_VARIANT_ADD_PL_1="variant_add_pl_1";
private static final String M_VARIANT_ADD_PL_2="variant_add_pl_2";
private static final String M_VARIANT_DECL="variant_decl";
private static final String M_VARIANT_DECL_PL="variant_decl_pl";
private static final String M_VARIANT_RES="variant_res";
private static final String M_VARIANT_RES_1="variant_res_1";
private static final String M_VARIANT_RES_2="variant_res_2";
private static final String M_VARIANT_RES_3="variant_res_3";
private static final String M_VARIANT_SCORES="variant_scores";
private static final String M_VARIANT_TABLE_1="variant_table_1";
private static final String M_VARIANT_TABLE_1_1="variant_table_1_1";
private static final String M_VARIANT_TABLE_1_2="variant_table_1_2";
private static final String M_VARIANT_TABLE_1_3="variant_table_1_3";
private static final String M_VARIANT_TABLE_1_4="variant_table_1_4";
private static final String M_VARIANT_TABLE_1_5="variant_table_1_5";
private static final String M_VARIANT_TABLE_1_6="variant_table_1_6";
private static final String M_VARIANT_TABLE_2="variant_table_2";
private static final String M_VARIANT_TABLE_2_1="variant_table_2_1";
private static final String M_VARIANT_TABLE_2_2="variant_table_2_2";
private static final String M_VARIANT_TABLE_2_3="variant_table_2_3";
private static final String M_VARIANT_TABLE_2_4="variant_table_2_4";
private static final String M_VARIANT_TABLE_2_5="variant_table_2_5";
private static final String M_VARIANT_TABLE_2_6="variant_table_2_6";
private static final String M_WIN="win";
private static final String M_YES="yes";
private static final char SEP='=';
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
String f=M_WIN+SEP+C_0_2_0;
f+=M_EQUALITY+SEP+C_0_2_1;
f+=M_LOOSE+SEP+C_0_2_2;
f+=M_SUCCESSFUL+SEP+C_0_2_3;
f+=M_FAILED+SEP+C_0_2_4;
f+=M_MID+SEP+C_0_2_5;
f+=M_SLAM+SEP+C_0_2_6;
f+=M_NOSLAM+SEP+C_0_2_7;
f+=M_SUCCESSFULDECLAREDSLAM+SEP+C_0_2_8;
f+=M_SUCCESSFULNODECLAREDSLAM+SEP+C_0_2_9;
f+=M_SUCCESSFULSLAM+SEP+C_0_2_10;
f+=M_FAILEDDECLAREDSLAM+SEP+C_0_2_11;
f+=M_NOSLAMATTACK+SEP+C_0_2_12;
f+=M_NOSLAMDEFENSE+SEP+C_0_2_13;
f+=M_DETAILSSUCCESSFULDECLAREDSLAM+SEP+C_0_2_14;
f+=M_DETAILSSUCCESSFULNODECLAREDSLAM+SEP+C_0_2_15;
f+=M_DETAILSSUCCESSFULSLAM+SEP+C_0_2_16;
f+=M_DETAILSFAILEDDECLAREDSLAM+SEP+C_0_2_17;
f+=M_RESULTS+SEP+C_0_2_18;
f+=M_CLASSIC_BID+SEP+C_0_2_19;
f+=M_CLASSIC_BASE+SEP+C_0_2_20;
f+=M_CLASSIC_SMALL+SEP+C_0_2_21;
f+=M_CLASSIC_DIFF+SEP+C_0_2_22;
f+=M_CLASSIC_RATE+SEP+C_0_2_23;
f+=M_CLASSIC_SCORE_TAKER+SEP+C_0_2_24;
f+=M_CLASSIC_DECL+SEP+C_0_2_25;
f+=M_CLASSIC_DECL_PLAYER+SEP+C_0_2_26;
f+=M_SUM+SEP+C_0_2_27;
f+=M_CLASSIC_SUM_PLAYER+SEP+C_0_2_28;
f+=M_CLASSIC_ADDON+SEP+C_0_2_29;
f+=M_CLASSIC_ADDON_ATT+SEP+C_0_2_30;
f+=M_CLASSIC_ADDON_DEF+SEP+C_0_2_31;
f+=M_CLASSIC_ADDON_SUM+SEP+C_0_2_32;
f+=M_CLASSIC_RATE_PL+SEP+C_0_2_33;
f+=M_PLAYER+SEP+C_0_2_34;
f+=M_RATE+SEP+C_0_2_35;
f+=M_SCORE+SEP+C_0_2_36;
f+=M_EXP_RATE+SEP+C_0_2_37;
f+=M_EXP_SCORE+SEP+C_0_2_38;
f+=M_VARIANT_TABLE_1+SEP+C_0_2_39;
f+=M_VARIANT_TABLE_1_1+SEP+C_0_2_40;
f+=M_VARIANT_TABLE_1_2+SEP+C_0_2_41;
f+=M_VARIANT_TABLE_1_3+SEP+C_0_2_42;
f+=M_VARIANT_TABLE_1_4+SEP+C_0_2_43;
f+=M_VARIANT_TABLE_1_5+SEP+C_0_2_44;
f+=M_VARIANT_TABLE_1_6+SEP+C_0_2_45;
f+=M_VARIANT_TABLE_2+SEP+C_0_2_46;
f+=M_VARIANT_TABLE_2_1+SEP+C_0_2_47;
f+=M_VARIANT_TABLE_2_2+SEP+C_0_2_48;
f+=M_VARIANT_TABLE_2_3+SEP+C_0_2_49;
f+=M_VARIANT_TABLE_2_4+SEP+C_0_2_50;
f+=M_VARIANT_TABLE_2_5+SEP+C_0_2_51;
f+=M_VARIANT_TABLE_2_6+SEP+C_0_2_52;
f+=M_VARIANT_DECL+SEP+C_0_2_53;
f+=M_VARIANT_DECL_PL+SEP+C_0_2_54;
f+=M_VARIANT_ADD+SEP+C_0_2_55;
f+=M_VARIANT_ADD_PL+SEP+C_0_2_56;
f+=M_VARIANT_ADD_PL_1+SEP+C_0_2_57;
f+=M_VARIANT_ADD_PL_2+SEP+C_0_2_58;
f+=M_CLASSIC_POINTS+SEP+C_0_2_59;
f+=M_CLASSIC_OULDERS+SEP+C_0_2_60;
f+=M_CLASSIC_NEED+SEP+C_0_2_61;
f+=M_CLASSIC_WON+SEP+C_0_2_62;
f+=M_CLASSIC_ATT+SEP+C_0_2_63;
f+=M_CLASSIC_TAKER+SEP+C_0_2_64;
f+=M_CLASSIC_PARTS+SEP+C_0_2_65;
f+=M_CLASSIC_NONE_PART+SEP+C_0_2_66;
f+=M_CLASSIC_CALLED+SEP+C_0_2_67;
f+=M_CLASSIC_NONE_CALLED+SEP+C_0_2_68;
f+=M_CLASSIC_BID_END+SEP+C_0_2_69;
f+=M_CLASSIC_RES+SEP+C_0_2_70;
f+=M_VARIANT_RES+SEP+C_0_2_71;
f+=M_VARIANT_RES_1+SEP+C_0_2_72;
f+=M_VARIANT_RES_2+SEP+C_0_2_73;
f+=M_VARIANT_RES_3+SEP+C_0_2_74;
f+=M_VARIANT_SCORES+SEP+C_0_2_75;
f+=M_BEAT_CARDS+SEP+C_0_2_76;
f+=M_DEALING_PL+SEP+C_0_2_77;
f+=M_MODE+SEP+C_0_2_78;
f+=M_DISCARD+SEP+C_0_2_79;
f+=M_BIDS+SEP+C_0_2_80;
f+=M_DECLS+SEP+C_0_2_81;
f+=M_HANDS+SEP+C_0_2_82;
f+=M_HAND+SEP+C_0_2_83;
f+=M_NB+SEP+C_0_2_84;
f+=M_MIS+SEP+C_0_2_85;
f+=M_NOTHING+SEP+C_0_2_86;
f+=M_ENDING+SEP+C_0_2_87;
f+=M_YES+SEP+C_0_2_88;
f+=M_NO+SEP+C_0_2_89;
return f;
}
static String fr(){
String f=M_WIN+SEP+C_0_2_90;
f+=M_EQUALITY+SEP+C_0_2_91;
f+=M_LOOSE+SEP+C_0_2_92;
f+=M_SUCCESSFUL+SEP+C_0_2_93;
f+=M_FAILED+SEP+C_0_2_94;
f+=M_MID+SEP+C_0_2_95;
f+=M_SLAM+SEP+C_0_2_96;
f+=M_NOSLAM+SEP+C_0_2_97;
f+=M_SUCCESSFULDECLAREDSLAM+SEP+C_0_2_98;
f+=M_SUCCESSFULNODECLAREDSLAM+SEP+C_0_2_99;
f+=M_SUCCESSFULSLAM+SEP+C_0_2_100;
f+=M_FAILEDDECLAREDSLAM+SEP+C_0_2_101;
f+=M_NOSLAMATTACK+SEP+C_0_2_102;
f+=M_NOSLAMDEFENSE+SEP+C_0_2_103;
f+=M_DETAILSSUCCESSFULDECLAREDSLAM+SEP+C_0_2_104;
f+=M_DETAILSSUCCESSFULNODECLAREDSLAM+SEP+C_0_2_105;
f+=M_DETAILSSUCCESSFULSLAM+SEP+C_0_2_106;
f+=M_DETAILSFAILEDDECLAREDSLAM+SEP+C_0_2_107;
f+=M_RESULTS+SEP+C_0_2_108;
f+=M_CLASSIC_BID+SEP+C_0_2_109;
f+=M_CLASSIC_BASE+SEP+C_0_2_110;
f+=M_CLASSIC_SMALL+SEP+C_0_2_111;
f+=M_CLASSIC_DIFF+SEP+C_0_2_112;
f+=M_CLASSIC_RATE+SEP+C_0_2_113;
f+=M_CLASSIC_SCORE_TAKER+SEP+C_0_2_114;
f+=M_CLASSIC_DECL+SEP+C_0_2_115;
f+=M_CLASSIC_DECL_PLAYER+SEP+C_0_2_116;
f+=M_SUM+SEP+C_0_2_117;
f+=M_CLASSIC_SUM_PLAYER+SEP+C_0_2_118;
f+=M_CLASSIC_ADDON+SEP+C_0_2_119;
f+=M_CLASSIC_ADDON_ATT+SEP+C_0_2_120;
f+=M_CLASSIC_ADDON_DEF+SEP+C_0_2_121;
f+=M_CLASSIC_ADDON_SUM+SEP+C_0_2_122;
f+=M_CLASSIC_RATE_PL+SEP+C_0_2_123;
f+=M_PLAYER+SEP+C_0_2_124;
f+=M_RATE+SEP+C_0_2_125;
f+=M_SCORE+SEP+C_0_2_126;
f+=M_EXP_RATE+SEP+C_0_2_127;
f+=M_EXP_SCORE+SEP+C_0_2_128;
f+=M_VARIANT_TABLE_1+SEP+C_0_2_129;
f+=M_VARIANT_TABLE_1_1+SEP+C_0_2_130;
f+=M_VARIANT_TABLE_1_2+SEP+C_0_2_131;
f+=M_VARIANT_TABLE_1_3+SEP+C_0_2_132;
f+=M_VARIANT_TABLE_1_4+SEP+C_0_2_133;
f+=M_VARIANT_TABLE_1_5+SEP+C_0_2_134;
f+=M_VARIANT_TABLE_1_6+SEP+C_0_2_135;
f+=M_VARIANT_TABLE_2+SEP+C_0_2_136;
f+=M_VARIANT_TABLE_2_1+SEP+C_0_2_137;
f+=M_VARIANT_TABLE_2_2+SEP+C_0_2_138;
f+=M_VARIANT_TABLE_2_3+SEP+C_0_2_139;
f+=M_VARIANT_TABLE_2_4+SEP+C_0_2_140;
f+=M_VARIANT_TABLE_2_5+SEP+C_0_2_141;
f+=M_VARIANT_TABLE_2_6+SEP+C_0_2_142;
f+=M_VARIANT_DECL+SEP+C_0_2_143;
f+=M_VARIANT_DECL_PL+SEP+C_0_2_144;
f+=M_VARIANT_ADD+SEP+C_0_2_145;
f+=M_VARIANT_ADD_PL+SEP+C_0_2_146;
f+=M_VARIANT_ADD_PL_1+SEP+C_0_2_147;
f+=M_VARIANT_ADD_PL_2+SEP+C_0_2_148;
f+=M_CLASSIC_POINTS+SEP+C_0_2_149;
f+=M_CLASSIC_OULDERS+SEP+C_0_2_150;
f+=M_CLASSIC_NEED+SEP+C_0_2_151;
f+=M_CLASSIC_WON+SEP+C_0_2_152;
f+=M_CLASSIC_ATT+SEP+C_0_2_153;
f+=M_CLASSIC_TAKER+SEP+C_0_2_154;
f+=M_CLASSIC_PARTS+SEP+C_0_2_155;
f+=M_CLASSIC_NONE_PART+SEP+C_0_2_156;
f+=M_CLASSIC_CALLED+SEP+C_0_2_157;
f+=M_CLASSIC_NONE_CALLED+SEP+C_0_2_158;
f+=M_CLASSIC_BID_END+SEP+C_0_2_159;
f+=M_CLASSIC_RES+SEP+C_0_2_160;
f+=M_VARIANT_RES+SEP+C_0_2_161;
f+=M_VARIANT_RES_1+SEP+C_0_2_162;
f+=M_VARIANT_RES_2+SEP+C_0_2_163;
f+=M_VARIANT_RES_3+SEP+C_0_2_164;
f+=M_VARIANT_SCORES+SEP+C_0_2_165;
f+=M_BEAT_CARDS+SEP+C_0_2_166;
f+=M_DEALING_PL+SEP+C_0_2_167;
f+=M_MODE+SEP+C_0_2_168;
f+=M_DISCARD+SEP+C_0_2_169;
f+=M_BIDS+SEP+C_0_2_170;
f+=M_DECLS+SEP+C_0_2_171;
f+=M_HANDS+SEP+C_0_2_172;
f+=M_HAND+SEP+C_0_2_173;
f+=M_NB+SEP+C_0_2_174;
f+=M_MIS+SEP+C_0_2_175;
f+=M_NOTHING+SEP+C_0_2_176;
f+=M_ENDING+SEP+C_0_2_177;
f+=M_YES+SEP+C_0_2_178;
f+=M_NO+SEP+C_0_2_179;
return f;
}
}
