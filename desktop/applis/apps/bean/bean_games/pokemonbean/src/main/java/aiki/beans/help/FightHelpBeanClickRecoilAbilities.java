package aiki.beans.help;

import aiki.beans.PokemonBeanStruct;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class FightHelpBeanClickRecoilAbilities implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_instance).getInstance()).clickRecoilAbilities(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
