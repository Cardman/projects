package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightHelpBeanIsDisappearingUser implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).isDisappearingUser(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
