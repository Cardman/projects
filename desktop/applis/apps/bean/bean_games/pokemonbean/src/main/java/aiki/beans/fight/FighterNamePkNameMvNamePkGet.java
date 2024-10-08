package aiki.beans.fight;

import aiki.beans.*;
import code.bean.nat.*;

public class FighterNamePkNameMvNamePkGet implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaImgSt(((FighterNamePkNameMvStruct)_instance).getKey().getImagePk());
    }
}
