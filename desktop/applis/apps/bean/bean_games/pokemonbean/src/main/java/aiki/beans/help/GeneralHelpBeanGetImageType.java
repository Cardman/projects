package aiki.beans.help;

import aiki.beans.*;
import code.bean.nat.*;
public class GeneralHelpBeanGetImageType implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(( (GeneralHelpBean) ((PokemonBeanStruct)_instance).getInstance()).getImageType(NaPa.convertToNumber(_args[0]).intStruct()));
    }
}
