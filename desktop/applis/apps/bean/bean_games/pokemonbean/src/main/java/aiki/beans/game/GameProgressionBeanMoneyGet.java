package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.LgIntStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class GameProgressionBeanMoneyGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new LgIntStruct(( (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance()).getMoney());
    }
}
