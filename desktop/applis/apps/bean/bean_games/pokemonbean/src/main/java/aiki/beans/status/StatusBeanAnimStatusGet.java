package aiki.beans.status;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class StatusBeanAnimStatusGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (StatusBean) ((PokemonBeanStruct)_instance).getInstance()).getAnimStatus());
    }
}
