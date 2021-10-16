package code.scripts.pages.cards;
import code.util.*;
public final class MessBelotePage{
private static final String C_0="You win.\n";
private static final String C_10="{0}''s declaring ({1}):\n";
private static final String C_11="No thing\n";
private static final String C_12="Sum :\n";
private static final String C_13="1 Calculation of attack team''s points\n";
private static final String C_14="Number of necessary points in order that the taker wins:\n";
private static final String C_15="Number of points won in the attack team''s tricks:\n";
private static final String C_16="2 Attack team\n";
private static final String C_17="Taker:\n";
private static final String C_18="Taker''s partners:\n";
private static final String C_19="Noone\n";
private static final String C_1="Equality.\n";
private static final String C_20="Bid:\n";
private static final String C_21="3 Results\n";
private static final String C_22="Scored points by attack''s team without bonuses:\n";
private static final String C_23="Scored points by defense''s team without bonuses:\n";
private static final String C_24="Scored points by attack''s team with bonuses:\n";
private static final String C_25="Scored points by defense''s team with bonuses:\n";
private static final String C_26="Final scored points by attack''s team:\n";
private static final String C_27="Final scored points by defense''s team:\n";
private static final String C_28="Scores\n";
private static final String C_29="Mix Cards\n";
private static final String C_2="You loose.\n";
private static final String C_30="Deal all cards\n";
private static final String C_31="Allowed declaring\n";
private static final String C_32="Undertrumping a foe\n";
private static final String C_33="Allowed Bids at the beginning of the deal\n";
private static final String C_34="Rules of playing trumps when a partner is leading the current trick\n";
private static final String C_35="Players'' repartition\n";
private static final String C_36="End of game\n";
private static final String C_37="162 - 0, if the attack''s team looses.\n";
private static final String C_38="If the attack''s team looses, then this team keeps its own scored points.\n";
private static final String C_39="yes\n";
private static final String C_3="The bid {0} is passed of {1} points.\n";
private static final String C_40="no\n";
private static final String C_41="Vous gagnez.\n";
private static final String C_42="Match nul.\n";
private static final String C_43="Vous perdez.\n";
private static final String C_44="L''ench&egrave;re {0} est r&eacute;ussie de {1} points.\n";
private static final String C_45="L''ench&egrave;re {0} est chut&eacute;e de {1} points.\n";
private static final String C_46="L''ench&egrave;re {0} n''est ni r&eacute;ussie ni chut&eacute;e.\n";
private static final String C_47="L''attaque a r&eacute;ussi un capot.\n";
private static final String C_48="L''attaque n''a pas r&eacute;ussi de capot.\n";
private static final String C_49="R&eacute;sultats\n";
private static final String C_4="The bid {0} is failed of {1} points.\n";
private static final String C_50="D&eacute;tails des annonces\n";
private static final String C_51="Annonces de {0} ({1}):\n";
private static final String C_52="Rien\n";
private static final String C_53="Somme :\n";
private static final String C_54="1 Calcul des points en attaque\n";
private static final String C_55="Nombre de points n&eacute;cessaires pour que le preneur gagne:\n";
private static final String C_56="Nombre de points gagn&eacute;s dans les plis de l''attaque:\n";
private static final String C_57="2 Attaque\n";
private static final String C_58="Preneur:\n";
private static final String C_59="Partenaires du preneur:\n";
private static final String C_5="The bid {0} is neither passed nor failed.\n";
private static final String C_60="Aucun\n";
private static final String C_61="Ench&egrave;re:\n";
private static final String C_62="3 R&eacute;sultats\n";
private static final String C_63="Points marqu&eacute;s par l''attaque sans les primes:\n";
private static final String C_64="Points marqu&eacute;s par la d&eacute;fense sans les primes:\n";
private static final String C_65="Points marqu&eacute;s par l''attaque avec les primes:\n";
private static final String C_66="Points marqu&eacute;s par la d&eacute;fense avec les primes:\n";
private static final String C_67="Score d&eacute;finitif de l''attaque:\n";
private static final String C_68="Score d&eacute;finitif de la d&eacute;fense:\n";
private static final String C_69="Scores\n";
private static final String C_6="The attack''s team has achieved the grand slam.\n";
private static final String C_70="Battre les cartes\n";
private static final String C_71="Distribuer toutes les cartes\n";
private static final String C_72="Annonces autoris&eacute;es\n";
private static final String C_73="Sous-couper un adversaire\n";
private static final String C_74="Ench&egrave;res autoris&eacute;es au d&eacute;but de la partie\n";
private static final String C_75="R&egrave;gles du jeu des atouts lorsqu''un partenaire est ma&icirc;tre du pli courant\n";
private static final String C_76="R&eacute;partition des joueurs\n";
private static final String C_77="Fin de partie\n";
private static final String C_78="162 - 0, si l''attaque perd.\n";
private static final String C_79="Si l''attaque gagne, alors cette &eacute;quipe garde son propre score.\n";
private static final String C_7="The attack''s team has not won all tricks.\n";
private static final String C_80="oui\n";
private static final String C_81="non\n";
private static final String C_8="Results\n";
private static final String C_9="Details of declaring\n";
private static final String M_BEAT_CARDS="beat_cards";
private static final String M_BID="bid";
private static final String M_BIDS="bids";
private static final String M_CALC_TITLE="calc_title";
private static final String M_DEAL="deal";
private static final String M_DEALING="dealing";
private static final String M_DECL="decl";
private static final String M_DECL_PLAYER="decl_player";
private static final String M_DETAIL="detail";
private static final String M_END="end";
private static final String M_END_DEF="end_def";
private static final String M_END_ELSE="end_else";
private static final String M_EQUALITY="equality";
private static final String M_FAILED="failed";
private static final String M_FIN_ATT="fin_att";
private static final String M_FIN_DEF="fin_def";
private static final String M_LOOSE="loose";
private static final String M_MID="mid";
private static final String M_NEED="need";
private static final String M_NO="no";
private static final String M_NONE="none";
private static final String M_NOSLAM="noSlam";
private static final String M_NOTHING="nothing";
private static final String M_PARTNER="partner";
private static final String M_RESULTS="results";
private static final String M_RESULTS_TITLE="results_title";
private static final String M_SCORES="scores";
private static final String M_SLAM="slam";
private static final String M_SUCCESSFUL="successful";
private static final String M_SUM="sum";
private static final String M_TAKER="taker";
private static final String M_TAKER_TEAM="taker_team";
private static final String M_TAKER_TITLE="taker_title";
private static final String M_UNDER="under";
private static final String M_WIN="win";
private static final String M_WITHOUT_DECL_ATT="without_decl_att";
private static final String M_WITHOUT_DECL_DEF="without_decl_def";
private static final String M_WITH_DECL_ATT="with_decl_att";
private static final String M_WITH_DECL_DEF="with_decl_def";
private static final String M_WON_TRICK="won_trick";
private static final String M_YES="yes";
private static final char SEP='=';
private MessBelotePage(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_cards/css/belote.css",css());
m.addEntry("resources_cards/messages/en/messages_belote.properties",en());
m.addEntry("resources_cards/messages/fr/messages_belote.properties",fr());
return m;
}
static String css(){
String f="h1 {\n";
f+="\tcolor: #0000FF;\n";
f+="}\n";
return f;
}
static String en(){
String f=M_WIN+SEP+C_0;
f+=M_EQUALITY+SEP+C_1;
f+=M_LOOSE+SEP+C_2;
f+=M_SUCCESSFUL+SEP+C_3;
f+=M_FAILED+SEP+C_4;
f+=M_MID+SEP+C_5;
f+=M_SLAM+SEP+C_6;
f+=M_NOSLAM+SEP+C_7;
f+=M_RESULTS+SEP+C_8;
f+=M_DETAIL+SEP+C_9;
f+=M_DECL_PLAYER+SEP+C_10;
f+=M_NOTHING+SEP+C_11;
f+=M_SUM+SEP+C_12;
f+=M_CALC_TITLE+SEP+C_13;
f+=M_NEED+SEP+C_14;
f+=M_WON_TRICK+SEP+C_15;
f+=M_TAKER_TITLE+SEP+C_16;
f+=M_TAKER+SEP+C_17;
f+=M_TAKER_TEAM+SEP+C_18;
f+=M_NONE+SEP+C_19;
f+=M_BID+SEP+C_20;
f+=M_RESULTS_TITLE+SEP+C_21;
f+=M_WITHOUT_DECL_ATT+SEP+C_22;
f+=M_WITHOUT_DECL_DEF+SEP+C_23;
f+=M_WITH_DECL_ATT+SEP+C_24;
f+=M_WITH_DECL_DEF+SEP+C_25;
f+=M_FIN_ATT+SEP+C_26;
f+=M_FIN_DEF+SEP+C_27;
f+=M_SCORES+SEP+C_28;
f+=M_BEAT_CARDS+SEP+C_29;
f+=M_DEAL+SEP+C_30;
f+=M_DECL+SEP+C_31;
f+=M_UNDER+SEP+C_32;
f+=M_BIDS+SEP+C_33;
f+=M_PARTNER+SEP+C_34;
f+=M_DEALING+SEP+C_35;
f+=M_END+SEP+C_36;
f+=M_END_DEF+SEP+C_37;
f+=M_END_ELSE+SEP+C_38;
f+=M_YES+SEP+C_39;
f+=M_NO+SEP+C_40;
return f;
}
static String fr(){
String f=M_WIN+SEP+C_41;
f+=M_EQUALITY+SEP+C_42;
f+=M_LOOSE+SEP+C_43;
f+=M_SUCCESSFUL+SEP+C_44;
f+=M_FAILED+SEP+C_45;
f+=M_MID+SEP+C_46;
f+=M_SLAM+SEP+C_47;
f+=M_NOSLAM+SEP+C_48;
f+=M_RESULTS+SEP+C_49;
f+=M_DETAIL+SEP+C_50;
f+=M_DECL_PLAYER+SEP+C_51;
f+=M_NOTHING+SEP+C_52;
f+=M_SUM+SEP+C_53;
f+=M_CALC_TITLE+SEP+C_54;
f+=M_NEED+SEP+C_55;
f+=M_WON_TRICK+SEP+C_56;
f+=M_TAKER_TITLE+SEP+C_57;
f+=M_TAKER+SEP+C_58;
f+=M_TAKER_TEAM+SEP+C_59;
f+=M_NONE+SEP+C_60;
f+=M_BID+SEP+C_61;
f+=M_RESULTS_TITLE+SEP+C_62;
f+=M_WITHOUT_DECL_ATT+SEP+C_63;
f+=M_WITHOUT_DECL_DEF+SEP+C_64;
f+=M_WITH_DECL_ATT+SEP+C_65;
f+=M_WITH_DECL_DEF+SEP+C_66;
f+=M_FIN_ATT+SEP+C_67;
f+=M_FIN_DEF+SEP+C_68;
f+=M_SCORES+SEP+C_69;
f+=M_BEAT_CARDS+SEP+C_70;
f+=M_DEAL+SEP+C_71;
f+=M_DECL+SEP+C_72;
f+=M_UNDER+SEP+C_73;
f+=M_BIDS+SEP+C_74;
f+=M_PARTNER+SEP+C_75;
f+=M_DEALING+SEP+C_76;
f+=M_END+SEP+C_77;
f+=M_END_DEF+SEP+C_78;
f+=M_END_ELSE+SEP+C_79;
f+=M_YES+SEP+C_80;
f+=M_NO+SEP+C_81;
return f;
}
}
