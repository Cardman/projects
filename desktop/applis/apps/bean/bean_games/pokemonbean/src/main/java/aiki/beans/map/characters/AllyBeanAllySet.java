package aiki.beans.map.characters;

import aiki.beans.AllyStruct;
import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class AllyBeanAllySet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (AllyBean) ((PokemonBeanStruct)_instance).getInstance()).setAlly(((AllyStruct)_args[0]).getInstance());
        return NullStruct.NULL_VALUE;
    }
}
