package code.scripts.pages.aiki;
import code.util.*;
public final class CssInit{
private CssInit(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_pk/rom/web/css/abilities.css",CssDataAbilities.in());
m.addEntry("resources_pk/rom/web/css/attaques.css",CssDataAttaques.in());
m.addEntry("resources_pk/rom/web/css/capacites.css",CssDataCapacites.in());
m.addEntry("resources_pk/rom/web/css/items.css",CssDataItems.in());
m.addEntry("resources_pk/rom/web/css/moves.css",CssDataMoves.in());
m.addEntry("resources_pk/rom/web/css/objets.css",CssDataObjets.in());
m.addEntry("resources_pk/rom/web/css/pokedex.css",CssDataPokedex.in());
m.addEntry("resources_pk/rom/web/css/simulation.css",CssDataSimulation.in());
m.addEntry("resources_pk/rom/web/css/statuts.css",CssDataStatuts.in());
m.addEntry("resources_pk/rom/web_fight/css/fight.css",CssFightFight.in());
m.addEntry("resources_pk/rom/web_game/css/difficulty.css",CssGameDifficulty.in());
m.addEntry("resources_pk/rom/web_pk/css/pokemon.css",CssPkPokemon.in());
m.addEntry("resources_pk/rom/web_prog/css/difficulty.css",CssProgDifficulty.in());
return m;
}
}
