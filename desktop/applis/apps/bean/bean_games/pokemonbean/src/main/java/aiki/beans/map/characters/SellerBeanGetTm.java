package aiki.beans.map.characters;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class SellerBeanGetTm implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( (SellerBean) ((PokemonBeanStruct)_instance).getInstance()).getTm(NumParsers.convertToNumber(_args[0]).intStruct()));
    }
}
