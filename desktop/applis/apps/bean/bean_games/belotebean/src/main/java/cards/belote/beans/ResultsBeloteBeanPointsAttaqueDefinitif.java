package cards.belote.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public final class ResultsBeloteBeanPointsAttaqueDefinitif implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaNbSt(((ResultsBeloteBean) ((BeloteBeanStruct)_instance).getInstance()).getPointsAttaqueDefinitif());
    }
}
