package code.scripts.pages.cards;
import code.bean.nat.analyze.blocks.*;
import code.sml.util.*;
import code.util.*;
public final class MessBelotePage{
public static final String APP_BEAN = "belote_bean";
private static final String C_0_0_0="You win.\n";
private static final String C_0_0_10="{0}''s declaring ({1}):\n";
private static final String C_0_0_11="No thing\n";
private static final String C_0_0_12="Sum :\n";
private static final String C_0_0_13="1 Calculation of attack team''s points\n";
private static final String C_0_0_14="Number of necessary points in order that the taker wins:\n";
private static final String C_0_0_15="Number of points won in the attack team''s tricks:\n";
private static final String C_0_0_16="2 Attack team\n";
private static final String C_0_0_17="Taker:\n";
private static final String C_0_0_18="Taker''s partners:\n";
private static final String C_0_0_19="Noone\n";
private static final String C_0_0_1="Equality.\n";
private static final String C_0_0_20="Bid:\n";
private static final String C_0_0_21="3 Results\n";
private static final String C_0_0_22="Scored points by attack''s team without bonuses:\n";
private static final String C_0_0_23="Scored points by defense''s team without bonuses:\n";
private static final String C_0_0_24="Scored points by attack''s team with bonuses:\n";
private static final String C_0_0_25="Scored points by defense''s team with bonuses:\n";
private static final String C_0_0_26="Final scored points by attack''s team:\n";
private static final String C_0_0_27="Final scored points by defense''s team:\n";
private static final String C_0_0_28="Scores\n";
private static final String C_0_0_29="Mix Cards\n";
private static final String C_0_0_2="You loose.\n";
private static final String C_0_0_30="Deal all cards\n";
private static final String C_0_0_31="Allowed declaring\n";
private static final String C_0_0_32="Undertrumping a foe\n";
private static final String C_0_0_33="Allowed Bids at the beginning of the deal\n";
private static final String C_0_0_34="Rules of playing trumps when a partner is leading the current trick\n";
private static final String C_0_0_35="Players'' repartition\n";
private static final String C_0_0_36="End of game\n";
private static final String C_0_0_37="162 - 0, if the attack''s team looses.\n";
private static final String C_0_0_38="If the attack''s team looses, then this team keeps its own scored points.\n";
private static final String C_0_0_39="yes\n";
private static final String C_0_0_3="The bid {0} is passed of {1} points.\n";
private static final String C_0_0_40="no\n";
private static final String C_0_0_41="Vous gagnez.\n";
private static final String C_0_0_42="Match nul.\n";
private static final String C_0_0_43="Vous perdez.\n";
private static final String C_0_0_44="L''ench&egrave;re {0} est r&eacute;ussie de {1} points.\n";
private static final String C_0_0_45="L''ench&egrave;re {0} est chut&eacute;e de {1} points.\n";
private static final String C_0_0_46="L''ench&egrave;re {0} n''est ni r&eacute;ussie ni chut&eacute;e.\n";
private static final String C_0_0_47="L''attaque a r&eacute;ussi un capot.\n";
private static final String C_0_0_48="L''attaque n''a pas r&eacute;ussi de capot.\n";
private static final String C_0_0_49="R&eacute;sultats\n";
private static final String C_0_0_4="The bid {0} is failed of {1} points.\n";
private static final String C_0_0_50="D&eacute;tails des annonces\n";
private static final String C_0_0_51="Annonces de {0} ({1}):\n";
private static final String C_0_0_52="Rien\n";
private static final String C_0_0_53="Somme :\n";
private static final String C_0_0_54="1 Calcul des points en attaque\n";
private static final String C_0_0_55="Nombre de points n&eacute;cessaires pour que le preneur gagne:\n";
private static final String C_0_0_56="Nombre de points gagn&eacute;s dans les plis de l''attaque:\n";
private static final String C_0_0_57="2 Attaque\n";
private static final String C_0_0_58="Preneur:\n";
private static final String C_0_0_59="Partenaires du preneur:\n";
private static final String C_0_0_5="The bid {0} is neither passed nor failed.\n";
private static final String C_0_0_60="Aucun\n";
private static final String C_0_0_61="Ench&egrave;re:\n";
private static final String C_0_0_62="3 R&eacute;sultats\n";
private static final String C_0_0_63="Points marqu&eacute;s par l''attaque sans les primes:\n";
private static final String C_0_0_64="Points marqu&eacute;s par la d&eacute;fense sans les primes:\n";
private static final String C_0_0_65="Points marqu&eacute;s par l''attaque avec les primes:\n";
private static final String C_0_0_66="Points marqu&eacute;s par la d&eacute;fense avec les primes:\n";
private static final String C_0_0_67="Score d&eacute;finitif de l''attaque:\n";
private static final String C_0_0_68="Score d&eacute;finitif de la d&eacute;fense:\n";
private static final String C_0_0_69="Scores\n";
private static final String C_0_0_6="The attack''s team has achieved the grand slam.\n";
private static final String C_0_0_70="Battre les cartes\n";
private static final String C_0_0_71="Distribuer toutes les cartes\n";
private static final String C_0_0_72="Annonces autoris&eacute;es\n";
private static final String C_0_0_73="Sous-couper un adversaire\n";
private static final String C_0_0_74="Ench&egrave;res autoris&eacute;es au d&eacute;but de la partie\n";
private static final String C_0_0_75="R&egrave;gles du jeu des atouts lorsqu''un partenaire est ma&icirc;tre du pli courant\n";
private static final String C_0_0_76="R&eacute;partition des joueurs\n";
private static final String C_0_0_77="Fin de partie\n";
private static final String C_0_0_78="162 - 0, si l''attaque perd.\n";
private static final String C_0_0_79="Si l''attaque gagne, alors cette &eacute;quipe garde son propre score.\n";
private static final String C_0_0_7="The attack''s team has not won all tricks.\n";
private static final String C_0_0_80="oui\n";
private static final String C_0_0_81="non\n";
private static final String C_0_0_8="Results\n";
private static final String C_0_0_9="Details of declaring\n";
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
public static TranslationsAppli enBelote(){
TranslationsAppli b = new TranslationsAppli();
b.getMapping().addEntry("messages_belote",AnaRendBlockHelp.file(en()));
return b;
}
public static TranslationsAppli frBelote(){
TranslationsAppli b = new TranslationsAppli();
b.getMapping().addEntry("messages_belote",AnaRendBlockHelp.file(fr()));
return b;
}
public static StringMap<String> ms(){
StringMap<String> b = new StringMap<String>();
b.addEntry("resources_cards/css/belote.css",css());
//b.addEntry("resources_cards/messages/en/messages_belote.properties",en());
//b.addEntry("resources_cards/messages/fr/messages_belote.properties",fr());
return b;
}
static String css(){
String b="h1 {\n";
b+="\tcolor:blue;\n";
b+="}\n";
return b;
}
static String en(){
String f=M_WIN+SEP+C_0_0_0;
f+=M_EQUALITY+SEP+C_0_0_1;
f+=M_LOOSE+SEP+C_0_0_2;
f+=M_SUCCESSFUL+SEP+C_0_0_3;
f+=M_FAILED+SEP+C_0_0_4;
f+=M_MID+SEP+C_0_0_5;
f+=M_SLAM+SEP+C_0_0_6;
f+=M_NOSLAM+SEP+C_0_0_7;
f+=M_RESULTS+SEP+C_0_0_8;
f+=M_DETAIL+SEP+C_0_0_9;
f+=M_DECL_PLAYER+SEP+C_0_0_10;
f+=M_NOTHING+SEP+C_0_0_11;
f+=M_SUM+SEP+C_0_0_12;
f+=M_CALC_TITLE+SEP+C_0_0_13;
f+=M_NEED+SEP+C_0_0_14;
f+=M_WON_TRICK+SEP+C_0_0_15;
f+=M_TAKER_TITLE+SEP+C_0_0_16;
f+=M_TAKER+SEP+C_0_0_17;
f+=M_TAKER_TEAM+SEP+C_0_0_18;
f+=M_NONE+SEP+C_0_0_19;
f+=M_BID+SEP+C_0_0_20;
f+=M_RESULTS_TITLE+SEP+C_0_0_21;
f+=M_WITHOUT_DECL_ATT+SEP+C_0_0_22;
f+=M_WITHOUT_DECL_DEF+SEP+C_0_0_23;
f+=M_WITH_DECL_ATT+SEP+C_0_0_24;
f+=M_WITH_DECL_DEF+SEP+C_0_0_25;
f+=M_FIN_ATT+SEP+C_0_0_26;
f+=M_FIN_DEF+SEP+C_0_0_27;
f+=M_SCORES+SEP+C_0_0_28;
f+=M_BEAT_CARDS+SEP+C_0_0_29;
f+=M_DEAL+SEP+C_0_0_30;
f+=M_DECL+SEP+C_0_0_31;
f+=M_UNDER+SEP+C_0_0_32;
f+=M_BIDS+SEP+C_0_0_33;
f+=M_PARTNER+SEP+C_0_0_34;
f+=M_DEALING+SEP+C_0_0_35;
f+=M_END+SEP+C_0_0_36;
f+=M_END_DEF+SEP+C_0_0_37;
f+=M_END_ELSE+SEP+C_0_0_38;
f+=M_YES+SEP+C_0_0_39;
f+=M_NO+SEP+C_0_0_40;
return f;
}
static String fr(){
String f=M_WIN+SEP+C_0_0_41;
f+=M_EQUALITY+SEP+C_0_0_42;
f+=M_LOOSE+SEP+C_0_0_43;
f+=M_SUCCESSFUL+SEP+C_0_0_44;
f+=M_FAILED+SEP+C_0_0_45;
f+=M_MID+SEP+C_0_0_46;
f+=M_SLAM+SEP+C_0_0_47;
f+=M_NOSLAM+SEP+C_0_0_48;
f+=M_RESULTS+SEP+C_0_0_49;
f+=M_DETAIL+SEP+C_0_0_50;
f+=M_DECL_PLAYER+SEP+C_0_0_51;
f+=M_NOTHING+SEP+C_0_0_52;
f+=M_SUM+SEP+C_0_0_53;
f+=M_CALC_TITLE+SEP+C_0_0_54;
f+=M_NEED+SEP+C_0_0_55;
f+=M_WON_TRICK+SEP+C_0_0_56;
f+=M_TAKER_TITLE+SEP+C_0_0_57;
f+=M_TAKER+SEP+C_0_0_58;
f+=M_TAKER_TEAM+SEP+C_0_0_59;
f+=M_NONE+SEP+C_0_0_60;
f+=M_BID+SEP+C_0_0_61;
f+=M_RESULTS_TITLE+SEP+C_0_0_62;
f+=M_WITHOUT_DECL_ATT+SEP+C_0_0_63;
f+=M_WITHOUT_DECL_DEF+SEP+C_0_0_64;
f+=M_WITH_DECL_ATT+SEP+C_0_0_65;
f+=M_WITH_DECL_DEF+SEP+C_0_0_66;
f+=M_FIN_ATT+SEP+C_0_0_67;
f+=M_FIN_DEF+SEP+C_0_0_68;
f+=M_SCORES+SEP+C_0_0_69;
f+=M_BEAT_CARDS+SEP+C_0_0_70;
f+=M_DEAL+SEP+C_0_0_71;
f+=M_DECL+SEP+C_0_0_72;
f+=M_UNDER+SEP+C_0_0_73;
f+=M_BIDS+SEP+C_0_0_74;
f+=M_PARTNER+SEP+C_0_0_75;
f+=M_DEALING+SEP+C_0_0_76;
f+=M_END+SEP+C_0_0_77;
f+=M_END_DEF+SEP+C_0_0_78;
f+=M_END_ELSE+SEP+C_0_0_79;
f+=M_YES+SEP+C_0_0_80;
f+=M_NO+SEP+C_0_0_81;
return f;
}
}
