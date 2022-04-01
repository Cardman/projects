package aiki.beans.facade.simulation.dto;

import aiki.beans.MvLineStruct;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
public class SelectLineMoveSelectedSet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        ( (SelectLineMove) ((MvLineStruct) _instance).getWildPk()).setSelected(BooleanStruct.isTrue(_args[0]));
        return NullStruct.NULL_VALUE;
    }
}
