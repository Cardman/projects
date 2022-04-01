package aiki.beans.endround;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EffectEndRoundBeanGetTrMoves implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (EffectEndRoundBean) ((PokemonBeanStruct)_instance).getInstance()).getTrMoves(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
