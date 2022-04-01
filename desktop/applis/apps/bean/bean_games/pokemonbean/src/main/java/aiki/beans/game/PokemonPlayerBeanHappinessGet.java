package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
public class PokemonPlayerBeanHappinessGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new IntStruct(( (PokemonPlayerBean) ((PokemonBeanStruct)_instance).getInstance()).getHappiness());
    }
}
