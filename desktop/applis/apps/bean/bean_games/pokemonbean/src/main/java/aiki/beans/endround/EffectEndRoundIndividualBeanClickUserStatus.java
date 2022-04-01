package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EffectEndRoundIndividualBeanClickUserStatus implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (EffectEndRoundIndividualBean) ((PokemonBeanStruct)_instance).getInstance()).clickUserStatus(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
