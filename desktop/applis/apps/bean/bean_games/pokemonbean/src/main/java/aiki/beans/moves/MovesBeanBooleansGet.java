package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import aiki.beans.WithFilterBean;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;

public class MovesBeanBooleansGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getStrStr(( (WithFilterBean) ((PokemonBeanStruct)_instance).getInstance()).getBooleans());
    }
}
