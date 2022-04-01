package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EffectEndRoundPositionTargetBeanGetTrTargetRelationMove implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (EffectEndRoundPositionTargetBean) ((PokemonBeanStruct)_instance).getInstance()).getTrTargetRelationMove(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
