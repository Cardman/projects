package aiki.beans.help;

import aiki.beans.*;
import code.bean.nat.*;
import code.bean.nat.RtSt;
import code.bean.nat.*;
public class FightHelpBeanMinHpNotKoGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new RtSt(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getMinHpNotKo());
    }
}
