package aiki.beans.moves.effects;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class EffectRestrictionBeanForbidUserMoves implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (EffectRestrictionBean) ((PokemonBeanStruct)_instance).getInstance()).forbidUserMoves());
    }
}
