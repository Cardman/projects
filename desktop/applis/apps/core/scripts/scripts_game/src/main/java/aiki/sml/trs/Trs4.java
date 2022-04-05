package aiki.sml.trs;
public final class Trs4{
private static final String TR_GRASS="GRASS";
private static final String TR_ROCK="ROCK";
private static final String TR_ROAD="ROAD";
private static final String TR_WATER="WATER";
private static final String TR_NOTHING="NOTHING";
private static final String TR_SNOW="SNOW";
private static final String TR_ICE="ICE";
private static final String TR_DESERT="DESERT";
private static final String TR_BUILDING="BUILDING";
private Trs4(){}
static String tr(){
String e=TR_GRASS+"\tGRASS\n";
e+=TR_ROCK+"\tROCK\n";
e+=TR_ROAD+"\tROAD\n";
e+=TR_WATER+"\tWATER\n";
e+=TR_NOTHING+"\tNOTHING\n";
e+=TR_SNOW+"\tSNOW\n";
e+=TR_ICE+"\tICE\n";
e+=TR_DESERT+"\tDESERT\n";
e+=TR_BUILDING+"\tBUILDING\n";
return e;
}
}
