package aiki.sml.trs;
import aiki.db.*;
import aiki.sml.init.*;
import code.sml.util.*;
public final class MessagesTrsEnvironments extends CstIgame{
private MessagesTrsEnvironments(){}
static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(9);
e.add(DataBase.DEF_ENV_GRASS,"GRASS");
e.add(DataBase.DEF_ENV_ROCK,"ROCK");
e.add(DataBase.DEF_ENV_ROAD,"ROAD");
e.add(DataBase.DEF_ENV_WATER,"WATER");
e.add(DataBase.DEF_ENV_NOTHING,"NOTHING");
e.add(DataBase.DEF_ENV_SNOW,"SNOW");
e.add(DataBase.DEF_ENV_ICE,"ICE");
e.add(DataBase.DEF_ENV_DESERT,"DESERT");
e.add(DataBase.DEF_ENV_BUILDING,"BUILDING");
return e;
}
static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(9);
f.add(DataBase.DEF_ENV_GRASS,"HERBE");
f.add(DataBase.DEF_ENV_ROCK,"ROCHE");
f.add(DataBase.DEF_ENV_ROAD,"ROUTE");
f.add(DataBase.DEF_ENV_WATER,"EAU");
f.add(DataBase.DEF_ENV_NOTHING,"RIEN");
f.add(DataBase.DEF_ENV_SNOW,"NEIGE");
f.add(DataBase.DEF_ENV_ICE,"GLACE");
f.add(DataBase.DEF_ENV_DESERT,"DESERT");
f.add(DataBase.DEF_ENV_BUILDING,"BATIMENT");
return f;
}
}
