package code.scripts.messages.cards;
import code.util.*;
public final class MessPresidentGr{
private static final String CANNOT_PASS="cannotPass";
private static final String CANNOT_USE_LOWER="cannotUseLower";
private static final String CANNOT_USE_LOWER_OR_EQ="cannotUseLowerOrEq";
private static final String C_P_1_0="You cannot play any cards at this moment because two players have consecutively played card groups of same strength.\n";
private static final String C_P_1_10="Si vous voulez jouer, vous devez utiliser des cartes dont la valeur est sup&eacute;rieure &agrave; {0}.\n";
private static final String C_P_1_11="Si vous voulez jouer, vous devez utiliser des cartes dont la valeur est sup&eacute;rieure ou &eacute;gale &agrave; {0}.\n";
private static final String C_P_1_12="Si vous voulez jouer, vous devez utiliser exactement {0} cartes.\n";
private static final String C_P_1_13="Vous devez jouer sur {0}.\n";
private static final String C_P_1_14="Global results\n";
private static final String C_P_1_15="Detail results\n";
private static final String C_P_1_16="R&eacute;sultats globaux\n";
private static final String C_P_1_17="D&eacute;tail des r&eacute;sultats\n";
private static final String C_P_1_1="You cannot play any cards while this trick because you had passed before.\n";
private static final String C_P_1_2="You have to play cards while same value as {0} or pass.\n";
private static final String C_P_1_3="If you want to play, you have to use cards whose value is greater than {0}.\n";
private static final String C_P_1_4="If you want to play, you have to use cards whose value is greater than or equals to {0}.\n";
private static final String C_P_1_5="If you want to play, you have to use exactly {0} cards.\n";
private static final String C_P_1_6="You have to play over {0}.\n";
private static final String C_P_1_7="Vous ne pouvez pas jouer de cartes sur ce coup car deux joueurs ont jou&eacute; cons&eacute;cutivement des groupes de cartes de m&ecirc;me force.\n";
private static final String C_P_1_8="Vous ne pouvez pas jouer de cartes pendant ce pli car vous avez pass&eacute; avant.\n";
private static final String C_P_1_9="Vous devez jouez des cartes de m&ecirc;me valeur que {0} ou passer.\n";
private static final String DETAIL_RESULTS_PAGE="detailResultsPage";
private static final String HAS_PLAY_GIVEN_NUMBER_CARDS="hasPlayGivenNumberCards";
private static final String HAS_TO_EQUAL_OR_SKIP="hasToEqualOrSkip";
private static final String HAVE_PASSED="havePassed";
private static final String RESULTS_PAGE="resultsPage";
private static final String SKIPPED="skipped";
private static final char EQ_SEP='=';
private static final char SEP=':';
private MessPresidentGr(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_cards/classes/en/cards/president/gamepresident.properties",enGame());
m.addEntry("resources_cards/classes/fr/cards/president/gamepresident.properties",frGame());
m.addEntry("resources_cards/classes/en/cards/president/resultspresident.properties",enRes());
m.addEntry("resources_cards/classes/fr/cards/president/resultspresident.properties",frRes());
return m;
}
static String enGame(){
String f=SKIPPED+SEP+C_P_1_0;
f+=HAVE_PASSED+SEP+C_P_1_1;
f+=HAS_TO_EQUAL_OR_SKIP+SEP+C_P_1_2;
f+=CANNOT_USE_LOWER_OR_EQ+SEP+C_P_1_3;
f+=CANNOT_USE_LOWER+SEP+C_P_1_4;
f+=HAS_PLAY_GIVEN_NUMBER_CARDS+SEP+C_P_1_5;
f+=CANNOT_PASS+SEP+C_P_1_6;
return f;
}
static String frGame(){
String f=SKIPPED+SEP+C_P_1_7;
f+=HAVE_PASSED+SEP+C_P_1_8;
f+=HAS_TO_EQUAL_OR_SKIP+SEP+C_P_1_9;
f+=CANNOT_USE_LOWER_OR_EQ+SEP+C_P_1_10;
f+=CANNOT_USE_LOWER+SEP+C_P_1_11;
f+=HAS_PLAY_GIVEN_NUMBER_CARDS+SEP+C_P_1_12;
f+=CANNOT_PASS+SEP+C_P_1_13;
return f;
}
static String enRes(){
String f=RESULTS_PAGE+EQ_SEP+C_P_1_14;
f+=DETAIL_RESULTS_PAGE+EQ_SEP+C_P_1_15;
return f;
}
static String frRes(){
String f=RESULTS_PAGE+EQ_SEP+C_P_1_16;
f+=DETAIL_RESULTS_PAGE+EQ_SEP+C_P_1_17;
return f;
}
}
