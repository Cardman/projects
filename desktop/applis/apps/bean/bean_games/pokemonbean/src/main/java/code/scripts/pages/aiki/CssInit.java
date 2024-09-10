package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.util.*;
public final class CssInit{
private CssInit(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry(PkScriptPages.REN_ADD_WEB_CSS_ABILITIES_CSS,CssDataAbilities.in());
m.addEntry(PkScriptPages.REN_ADD_WEB_CSS_ITEMS_CSS,CssDataItems.in());
m.addEntry(PkScriptPages.REN_ADD_WEB_CSS_MOVES_CSS,CssDataMoves.in());
m.addEntry(PkScriptPages.REN_ADD_WEB_CSS_POKEDEX_CSS,CssDataPokedex.in());
m.addEntry(PkScriptPages.REN_ADD_WEB_CSS_SIMULATION_CSS,CssDataSimulation.in());
m.addEntry(PkScriptPages.REN_ADD_WEB_FIGHT_CSS_FIGHT_CSS,CssFightFight.in());
m.addEntry(PkScriptPages.REN_ADD_WEB_GAME_CSS_DIFFICULTY_CSS,CssGameDifficulty.in());
m.addEntry(PkScriptPages.REN_ADD_WEB_PK_CSS_POKEMON_CSS,CssPkPokemon.in());
m.addEntry(PkScriptPages.REN_ADD_WEB_PROG_CSS_DIFFICULTY_CSS,CssProgDifficulty.in());
return m;
}
}
