package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
public class AddPokemonBeanPokedexGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return PokemonStandards.getPkLine(( (AddPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getPokedex());
    }
}
