package aiki.beans;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class MoveTargetGetTarget implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new TargetCoordsStruct(( ((MoveTargetStruct) _instance).getInstance()).getTarget(),PokemonStandards.TYPE_TARGET_COORDS);
    }
}
