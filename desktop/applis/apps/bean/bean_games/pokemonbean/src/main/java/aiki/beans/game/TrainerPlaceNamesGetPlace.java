package aiki.beans.game;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class TrainerPlaceNamesGetPlace implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( ((TrainerPlaceNamesStruct) _instance).getInstance()).getPlace());
    }
}
