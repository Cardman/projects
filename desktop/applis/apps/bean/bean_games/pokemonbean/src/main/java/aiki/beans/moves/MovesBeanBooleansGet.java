package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import aiki.beans.WithFilterBean;
import code.bean.nat.*;
import code.bean.nat.*;

public class MovesBeanBooleansGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return PokemonStandards.getStrStr(( (WithFilterBean) ((PokemonBeanStruct)_instance).getInstance()).getBooleans());
    }
}
