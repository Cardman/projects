package aiki.beans.status;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class StatusBeanMapVarsFailEndRoundGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getStrStr(( (StatusBean) ((PokemonBeanStruct)_instance).getInstance()).getMapVarsFailEndRound());
    }
}
