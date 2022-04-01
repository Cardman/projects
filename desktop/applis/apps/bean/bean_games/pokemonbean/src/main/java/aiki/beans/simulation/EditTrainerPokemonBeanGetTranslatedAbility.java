package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class EditTrainerPokemonBeanGetTranslatedAbility implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (EditTrainerPokemonBean) ((PokemonBeanStruct)_instance).getInstance()).getTranslatedAbility());
    }
}
