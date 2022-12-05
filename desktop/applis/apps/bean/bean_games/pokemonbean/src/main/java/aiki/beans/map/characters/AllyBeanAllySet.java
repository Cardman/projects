package aiki.beans.map.characters;

import aiki.beans.AllyStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class AllyBeanAllySet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (AllyBean) ((PokemonBeanStruct)_instance).getInstance()).setAlly(((AllyStruct)_args[0]).getAlly());
        return NaNu.NULL_VALUE;
    }
}
