package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.Struct;
public class EffectEndRoundTeamBeanDeleteAllStatusGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new RateStruct(( (EffectEndRoundTeamBean) ((PokemonBeanStruct)_instance).getInstance()).getDeleteAllStatus());
    }
}
