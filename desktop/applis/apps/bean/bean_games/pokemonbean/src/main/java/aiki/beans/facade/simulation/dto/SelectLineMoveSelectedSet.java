package aiki.beans.facade.simulation.dto;

import aiki.beans.MvLineStruct;
import code.bean.nat.*;
public class SelectLineMoveSelectedSet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SelectLineMove) ((MvLineStruct) _instance).getWildPk()).getSelected().setSelected(NaBoSt.isTrue(_args[0]));
        return NaNu.NULL_VALUE;
    }
}
