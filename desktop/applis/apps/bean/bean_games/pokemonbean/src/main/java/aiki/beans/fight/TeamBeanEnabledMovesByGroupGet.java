package aiki.beans.fight;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class TeamBeanEnabledMovesByGroupGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return AikiBeansFightStd.getStrAct(( (TeamBean) ((PokemonBeanStruct)_instance).getInstance()).getEnabledMovesByGroup());
    }
}
