package code.scripts.pages.aiki;
import code.util.*;
public final class CssInit{
private CssInit(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("web/css/abilities.css",CssDataAbilities.in());
m.addEntry("web/css/attaques.css",CssDataAttaques.in());
m.addEntry("web/css/capacites.css",CssDataCapacites.in());
m.addEntry("web/css/items.css",CssDataItems.in());
m.addEntry("web/css/moves.css",CssDataMoves.in());
m.addEntry("web/css/objets.css",CssDataObjets.in());
m.addEntry("web/css/pokedex.css",CssDataPokedex.in());
m.addEntry("web/css/simulation.css",CssDataSimulation.in());
m.addEntry("web/css/statuts.css",CssDataStatuts.in());
m.addEntry("web_fight/css/fight.css",CssFightFight.in());
m.addEntry("web_game/css/difficulty.css",CssGameDifficulty.in());
m.addEntry("web_pk/css/pokemon.css",CssPkPokemon.in());
m.addEntry("web_prog/css/difficulty.css",CssProgDifficulty.in());
return m;
}
}
