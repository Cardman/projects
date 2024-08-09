package code.scripts.pages.cards;
import code.bean.nat.analyze.blocks.*;
import code.sml.util.*;
import code.util.*;
public final class MessPresidentPage{
public static final String APP_BEAN = "president_bean";
private static final String C_0_1_0="You win.\n";
private static final String C_0_1_10="Mix Cards\n";
private static final String C_0_1_11="Number of players\n";
private static final String C_0_1_12="Number of stacks\n";
private static final String C_0_1_13="Number of cards per player: {0}\n";
private static final String C_0_1_14="Number of cards per player: between {0} and {1}\n";
private static final String C_0_1_15="Equality\n";
private static final String C_0_1_16="Possible reversing strength\n";
private static final String C_0_1_17="Every player has to play if possible\n";
private static final String C_0_1_18="A player looses if this player finish by the best cards\n";
private static final String C_0_1_19="For next deals, players can switch their cards at the beginning\n";
private static final String C_0_1_1="Equality.\n";
private static final String C_0_1_20="For next deals, the looser player starts first the first trick\n";
private static final String C_0_1_21="yes\n";
private static final String C_0_1_22="no\n";
private static final String C_0_1_23="Vous gagnez.\n";
private static final String C_0_1_24="Match nul.\n";
private static final String C_0_1_25="Vous perdez.\n";
private static final String C_0_1_26="L''ench&egrave;re {0} est r&eacute;ussie de {1} points.\n";
private static final String C_0_1_27="L''ench&egrave;re {0} est chut&eacute;e de {1} points.\n";
private static final String C_0_1_28="L''ench&egrave;re {0} n''est ni r&eacute;ussie ni chut&eacute;e.\n";
private static final String C_0_1_29="L''attaque a r&eacute;ussi un capot.\n";
private static final String C_0_1_2="You loose.\n";
private static final String C_0_1_30="L''attaque n''a pas r&eacute;ussi de capot.\n";
private static final String C_0_1_31="Rangs\n";
private static final String C_0_1_32="R&eacute;sultats\n";
private static final String C_0_1_33="Battre les cartes\n";
private static final String C_0_1_34="Nombre de joueurs\n";
private static final String C_0_1_35="Nombre de tas\n";
private static final String C_0_1_36="Nombre de cartes par joueur: {0}\n";
private static final String C_0_1_37="Nombre de cartes par joueur: entre {0} et {1}\n";
private static final String C_0_1_38="&Eacute;galit&eacute;\n";
private static final String C_0_1_39="R&eacute;volution possible\n";
private static final String C_0_1_3="The bid {0} is passed of {1} points.\n";
private static final String C_0_1_40="Chaque joueur doit jouer si possible\n";
private static final String C_0_1_41="Un joueur perd si ce joueur finit par les meilleures cartes\n";
private static final String C_0_1_42="Pour les donnes suivantes, les joueurs peuvent &eacute;changer leurs cartes au d&eacute;but\n";
private static final String C_0_1_43="Pour les donnes suivantes, le perdant commence &agrave; jouer le premier pli\n";
private static final String C_0_1_44="oui\n";
private static final String C_0_1_45="non\n";
private static final String C_0_1_4="The bid {0} is failed of {1} points.\n";
private static final String C_0_1_5="The bid {0} is neither passed nor failed.\n";
private static final String C_0_1_6="The attack''s team has achieved the grand slam.\n";
private static final String C_0_1_7="The attack''s team has not won all tricks.\n";
private static final String C_0_1_8="Ranks\n";
private static final String C_0_1_9="Results\n";
private static final String M_BEAT_CARDS="beat_cards";
private static final String M_EQUALITY="equality";
private static final String M_EQUALTY="equalty";
private static final String M_FAILED="failed";
private static final String M_HAS_TO_PLAY="has_to_play";
private static final String M_INVERT="invert";
private static final String M_LOOSE="loose";
private static final String M_LOOSE_COND="loose_cond";
private static final String M_LOSSE_FIRST="losse_first";
private static final String M_MID="mid";
private static final String M_NB_CARDS="nb_cards";
private static final String M_NB_CARDS_BET="nb_cards_bet";
private static final String M_NB_PLAYERS="nb_players";
private static final String M_NB_STACKS="nb_stacks";
private static final String M_NO="no";
private static final String M_NOSLAM="noSlam";
private static final String M_RANKS="ranks";
private static final String M_RESULTS="results";
private static final String M_SLAM="slam";
private static final String M_SUCCESSFUL="successful";
private static final String M_SWITCH="switch";
private static final String M_WIN="win";
private static final String M_YES="yes";
private static final char SEP='=';
private MessPresidentPage(){}
public static TranslationsAppli enPresident(){
TranslationsAppli b = new TranslationsAppli();
b.getMapping().addEntry("resources_cards/messages/messages_president.properties",AnaRendBlockHelp.file(en()));
return b;
}
public static TranslationsAppli frPresident(){
TranslationsAppli b = new TranslationsAppli();
b.getMapping().addEntry("resources_cards/messages/messages_president.properties",AnaRendBlockHelp.file(fr()));
return b;
}
public static StringMap<String> ms(){
StringMap<String> p = new StringMap<String>();
p.addEntry("resources_cards/css/president.css",css());
//p.addEntry("resources_cards/messages/en/messages_president.properties",en());
//p.addEntry("resources_cards/messages/fr/messages_president.properties",fr());
return p;
}
static String css(){
String p="h1 {\n";
p+="\tcolor:blue;\n";
p+="}\n";
return p;
}
static String en(){
String f=M_WIN+SEP+C_0_1_0;
f+=M_EQUALITY+SEP+C_0_1_1;
f+=M_LOOSE+SEP+C_0_1_2;
f+=M_SUCCESSFUL+SEP+C_0_1_3;
f+=M_FAILED+SEP+C_0_1_4;
f+=M_MID+SEP+C_0_1_5;
f+=M_SLAM+SEP+C_0_1_6;
f+=M_NOSLAM+SEP+C_0_1_7;
f+=M_RANKS+SEP+C_0_1_8;
f+=M_RESULTS+SEP+C_0_1_9;
f+=M_BEAT_CARDS+SEP+C_0_1_10;
f+=M_NB_PLAYERS+SEP+C_0_1_11;
f+=M_NB_STACKS+SEP+C_0_1_12;
f+=M_NB_CARDS+SEP+C_0_1_13;
f+=M_NB_CARDS_BET+SEP+C_0_1_14;
f+=M_EQUALTY+SEP+C_0_1_15;
f+=M_INVERT+SEP+C_0_1_16;
f+=M_HAS_TO_PLAY+SEP+C_0_1_17;
f+=M_LOOSE_COND+SEP+C_0_1_18;
f+=M_SWITCH+SEP+C_0_1_19;
f+=M_LOSSE_FIRST+SEP+C_0_1_20;
f+=M_YES+SEP+C_0_1_21;
f+=M_NO+SEP+C_0_1_22;
return f;
}
static String fr(){
String f=M_WIN+SEP+C_0_1_23;
f+=M_EQUALITY+SEP+C_0_1_24;
f+=M_LOOSE+SEP+C_0_1_25;
f+=M_SUCCESSFUL+SEP+C_0_1_26;
f+=M_FAILED+SEP+C_0_1_27;
f+=M_MID+SEP+C_0_1_28;
f+=M_SLAM+SEP+C_0_1_29;
f+=M_NOSLAM+SEP+C_0_1_30;
f+=M_RANKS+SEP+C_0_1_31;
f+=M_RESULTS+SEP+C_0_1_32;
f+=M_BEAT_CARDS+SEP+C_0_1_33;
f+=M_NB_PLAYERS+SEP+C_0_1_34;
f+=M_NB_STACKS+SEP+C_0_1_35;
f+=M_NB_CARDS+SEP+C_0_1_36;
f+=M_NB_CARDS_BET+SEP+C_0_1_37;
f+=M_EQUALTY+SEP+C_0_1_38;
f+=M_INVERT+SEP+C_0_1_39;
f+=M_HAS_TO_PLAY+SEP+C_0_1_40;
f+=M_LOOSE_COND+SEP+C_0_1_41;
f+=M_SWITCH+SEP+C_0_1_42;
f+=M_LOSSE_FIRST+SEP+C_0_1_43;
f+=M_YES+SEP+C_0_1_44;
f+=M_NO+SEP+C_0_1_45;
return f;
}
}
