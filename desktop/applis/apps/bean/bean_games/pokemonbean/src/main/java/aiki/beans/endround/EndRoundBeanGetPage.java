package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class EndRoundBeanGetPage implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (EndRoundBean) ((PokemonBeanStruct)_instance).getInstance()).getPage(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
