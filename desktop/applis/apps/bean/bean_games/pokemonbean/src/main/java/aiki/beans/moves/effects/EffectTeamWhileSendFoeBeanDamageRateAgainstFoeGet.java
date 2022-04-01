package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EffectTeamWhileSendFoeBeanDamageRateAgainstFoeGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (EffectTeamWhileSendFoeBean) ((PokemonBeanStruct)_instance).getInstance()).getDamageRateAgainstFoe());
    }
}
