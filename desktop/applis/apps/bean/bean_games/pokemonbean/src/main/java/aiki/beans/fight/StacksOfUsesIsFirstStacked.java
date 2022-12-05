package aiki.beans.fight;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class StacksOfUsesIsFirstStacked implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( ((StacksOfUsesStruct) _instance).getStacksOfUses()).isFirstStacked());
    }
}
