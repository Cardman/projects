package cards.belote.beans;

import code.bean.nat.*;
import code.bean.nat.*;
public class DetailsResultsBeloteBeanDeclaring implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeloteStandards.getSumDeclaringPlayerArray(((DetailsResultsBeloteBean)((BeloteBeanStruct)_instance).getInstance()).getDeclaring());
    }
}
