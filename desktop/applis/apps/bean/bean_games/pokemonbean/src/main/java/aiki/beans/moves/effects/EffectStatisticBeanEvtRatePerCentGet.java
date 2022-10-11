package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EffectStatisticBeanEvtRatePerCentGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(((EffectStatisticBean) ((PokemonBeanStruct) _instance).getInstance()).getEffectStatisticCommon().getEvtRatePerCent());
    }
}
