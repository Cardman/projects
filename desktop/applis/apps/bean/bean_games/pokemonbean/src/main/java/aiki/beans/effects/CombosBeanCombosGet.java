package aiki.beans.effects;

import aiki.beans.ComboDtoStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
public class CombosBeanCombosGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new ComboDtoStruct(( (CombosBean) ((PokemonBeanStruct)_instance).getInstance()).getCombos());
    }
}
