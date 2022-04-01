package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class MoveBeanMapVarsAccuracyGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getStrStr(( (MoveBean) ((PokemonBeanStruct)_instance).getInstance()).getMapVarsAccuracy());
    }
}
