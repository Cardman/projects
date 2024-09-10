package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;

public class DifficultyBeanChange implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (DifficultyBean) ((PokemonBeanStruct)_instance).getInstance()).change();
        return new NaStSt(PkScriptPages.WEB_GAME_HTML_DIFFICULTY_HTML);
    }
}
