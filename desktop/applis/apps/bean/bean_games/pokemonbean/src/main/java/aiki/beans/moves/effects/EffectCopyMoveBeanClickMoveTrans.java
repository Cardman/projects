package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EffectCopyMoveBeanClickMoveTrans implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (EffectCopyMoveBean) ((PokemonBeanStruct)_instance).getInstance()).clickMoveTrans(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
