package aiki.beans.moves;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class MovesBeanCategoryGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (MovesBean) ((PokemonBeanStruct)_instance).getInstance()).getCategory());
    }
}
