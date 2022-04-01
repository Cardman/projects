package aiki.beans.facade.simulation.dto;

import aiki.beans.MvLineStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
public class SelectLineMoveSelectedGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return BooleanStruct.of(( (SelectLineMove) ((MvLineStruct) _instance).getWildPk()).getSelected());
    }
}
