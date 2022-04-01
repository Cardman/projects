package aiki.beans.game;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class PokemonPlayerBeanEvolutionsGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getStrStr(( (PokemonPlayerBean) ((PokemonBeanStruct)_instance).getInstance()).getEvolutions());
    }
}
