package cards.belote.beans;

import code.bean.nat.*;
import code.bean.nat.*;
public class BeloteSumDeclaringPlayerDeclaring implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return BeloteStandards.getDeclaringPlayerValueArray((((BeloteSumDeclaringPlayerStruct)_instance).getSumDeclaringPlayer()).getDeclaring());
    }
}
