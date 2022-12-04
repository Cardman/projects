package aiki.beans.map.characters;

import aiki.beans.AllyStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class DualFightBeanAllyGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new AllyStruct(( (DualFightBean) ((PokemonBeanStruct)_instance).getInstance()).getAlly());
    }
}
