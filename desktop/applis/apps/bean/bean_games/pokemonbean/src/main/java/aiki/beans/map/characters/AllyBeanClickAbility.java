package aiki.beans.map.characters;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class AllyBeanClickAbility implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AllyBean) ((PokemonBeanStruct)_instance).getInstance()).clickAbility(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
