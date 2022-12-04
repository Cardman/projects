package aiki.beans.facade.simulation.dto;

import aiki.beans.MvLineStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class SelectLineMoveSelectedGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(((SelectLineMove) ((MvLineStruct) _instance).getWildPk()).isSelected());
    }
}
