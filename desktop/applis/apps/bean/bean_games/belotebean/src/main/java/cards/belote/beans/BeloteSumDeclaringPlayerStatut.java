package cards.belote.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class BeloteSumDeclaringPlayerStatut implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt((((BeloteSumDeclaringPlayerStruct)_instance).getSumDeclaringPlayer()).getStatut());
    }
}
