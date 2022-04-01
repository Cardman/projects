package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class EndRoundBeanGetEvts implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getEnd(( (EndRoundBean) ((PokemonBeanStruct)_instance).getInstance()).getEvts());
    }
}
