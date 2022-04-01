package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class EffectTeamBeanDefaultBoostGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( (EffectTeamBean) ((PokemonBeanStruct)_instance).getInstance()).getDefaultBoost());
    }
}
