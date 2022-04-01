package aiki.beans.facade.dto;

import aiki.beans.ItLineStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class ItemLineDescriptionClassGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(( ((ItLineStruct) _instance).getWildPk()).getDescriptionClass());
    }
}
