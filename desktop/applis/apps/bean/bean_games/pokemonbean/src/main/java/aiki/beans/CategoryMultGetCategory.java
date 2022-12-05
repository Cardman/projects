package aiki.beans;

import code.bean.nat.*;
import code.bean.nat.*;
import code.bean.nat.*;
public class CategoryMultGetCategory implements NatCaller{
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        return new NaStSt(( ((CategoryMultStruct) _instance).getCategoryMult()).getCategory());
    }
}
