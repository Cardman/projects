package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class DifficultyBeanChange implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (DifficultyBean) ((PokemonBeanStruct)_instance).getInstance()).change();
        return new NaStSt(AikiBeansGameStd.WEB_GAME_HTML_DIFFICULTY_HTML);
    }
}
