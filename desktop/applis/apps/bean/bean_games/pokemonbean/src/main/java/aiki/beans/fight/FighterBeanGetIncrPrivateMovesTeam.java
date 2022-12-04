package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class FighterBeanGetIncrPrivateMovesTeam implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (FighterBean) ((PokemonBeanStruct)_instance).getInstance()).getIncrPrivateMovesTeam(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
