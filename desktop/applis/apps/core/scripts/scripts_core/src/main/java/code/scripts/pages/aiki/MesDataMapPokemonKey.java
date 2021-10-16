package code.scripts.pages.aiki;
final class MesDataMapPokemonKey{
private static final String C_P_34_0="Name:\n";
private static final String C_P_34_10="Pokemon of the ally of the player:<br/>\n";
private static final String C_P_34_11="Pokemon of the foe trainer:<br/>\n";
private static final String C_P_34_12="Base reward.\n";
private static final String C_P_34_13="Nom:\n";
private static final String C_P_34_14="Niveau:\n";
private static final String C_P_34_15="Genre:\n";
private static final String C_P_34_16="Capacit&eacute;:\n";
private static final String C_P_34_17="Objet:\n";
private static final String C_P_34_18="Aucun objet\n";
private static final String C_P_34_19="Attaques\n";
private static final String C_P_34_1="Level:\n";
private static final String C_P_34_20="Revenir &agrave; l''indexe\n";
private static final String C_P_34_21="Revenir &agrave; l''ensemble des lieux\n";
private static final String C_P_34_22="Multiplicit&eacute;:\n";
private static final String C_P_34_23="Les pokemon de l''alli&eacute; du joueur:<br/>\n";
private static final String C_P_34_24="Les pokemon du dresseur ennemi:<br/>\n";
private static final String C_P_34_25="R&eacute;compense de base\n";
private static final String C_P_34_2="Gende:\n";
private static final String C_P_34_3="Ability:\n";
private static final String C_P_34_4="Item:\n";
private static final String C_P_34_5="No item\n";
private static final String C_P_34_6="Moves\n";
private static final String C_P_34_7="Return to the index\n";
private static final String C_P_34_8="Return to the set of places\n";
private static final String C_P_34_9="Multiplicity:\n";
private static final String M_P_ABILITY="ability";
private static final String M_P_ALLY_TEAM="ally_team";
private static final String M_P_FOE_TEAM="foe_team";
private static final String M_P_GENDER="gender";
private static final String M_P_INDEX="index";
private static final String M_P_ITEM="item";
private static final String M_P_ITEM_NO="item_no";
private static final String M_P_LEVEL="level";
private static final String M_P_MAP="map";
private static final String M_P_MOVES="moves";
private static final String M_P_MULTIPLICITY="multiplicity";
private static final String M_P_NAME="name";
private static final String M_P_REWARD="reward";
private static final char SEP='=';
private MesDataMapPokemonKey(){}
static String en(){
String f=M_P_NAME+SEP+C_P_34_0;
f+=M_P_LEVEL+SEP+C_P_34_1;
f+=M_P_GENDER+SEP+C_P_34_2;
f+=M_P_ABILITY+SEP+C_P_34_3;
f+=M_P_ITEM+SEP+C_P_34_4;
f+=M_P_ITEM_NO+SEP+C_P_34_5;
f+=M_P_MOVES+SEP+C_P_34_6;
f+=M_P_INDEX+SEP+C_P_34_7;
f+=M_P_MAP+SEP+C_P_34_8;
f+=M_P_MULTIPLICITY+SEP+C_P_34_9;
f+=M_P_ALLY_TEAM+SEP+C_P_34_10;
f+=M_P_FOE_TEAM+SEP+C_P_34_11;
f+=M_P_REWARD+SEP+C_P_34_12;
return f;
}
static String fr(){
String f=M_P_NAME+SEP+C_P_34_13;
f+=M_P_LEVEL+SEP+C_P_34_14;
f+=M_P_GENDER+SEP+C_P_34_15;
f+=M_P_ABILITY+SEP+C_P_34_16;
f+=M_P_ITEM+SEP+C_P_34_17;
f+=M_P_ITEM_NO+SEP+C_P_34_18;
f+=M_P_MOVES+SEP+C_P_34_19;
f+=M_P_INDEX+SEP+C_P_34_20;
f+=M_P_MAP+SEP+C_P_34_21;
f+=M_P_MULTIPLICITY+SEP+C_P_34_22;
f+=M_P_ALLY_TEAM+SEP+C_P_34_23;
f+=M_P_FOE_TEAM+SEP+C_P_34_24;
f+=M_P_REWARD+SEP+C_P_34_25;
return f;
}
}
