package aiki.beans.map.characters;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;

public class AllyBeanGetGender implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (AllyBean) ((PokemonBeanStruct)_instance).getInstance()).getGender(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
