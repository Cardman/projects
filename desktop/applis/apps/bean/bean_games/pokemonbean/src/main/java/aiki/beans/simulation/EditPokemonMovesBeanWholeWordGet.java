package aiki.beans.simulation;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class EditPokemonMovesBeanWholeWordGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (EditPokemonMovesBean) ((PokemonBeanStruct)_instance).getInstance()).getWholeWord());
    }
}
