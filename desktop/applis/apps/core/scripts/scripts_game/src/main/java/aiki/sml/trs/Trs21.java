package aiki.sml.trs;
public final class Trs21{
private static final String TR_GRASS="GRASS";
private static final String TR_ROCK="ROCK";
private static final String TR_ROAD="ROAD";
private static final String TR_WATER="WATER";
private static final String TR_NOTHING="NOTHING";
private static final String TR_SNOW="SNOW";
private static final String TR_ICE="ICE";
private static final String TR_DESERT="DESERT";
private static final String TR_BUILDING="BUILDING";
private Trs21(){}
static String tr(){
String f=TR_GRASS+"\tHERBE\n";
f+=TR_ROCK+"\tROCHE\n";
f+=TR_ROAD+"\tROUTE\n";
f+=TR_WATER+"\tEAU\n";
f+=TR_NOTHING+"\tRIEN\n";
f+=TR_SNOW+"\tNEIGE\n";
f+=TR_ICE+"\tGLACE\n";
f+=TR_DESERT+"\tDESERT\n";
f+=TR_BUILDING+"\tBATIMENT\n";
return f;
}
}
