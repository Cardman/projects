package aiki.beans.map.characters;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
public class DualFightBeanPageAllyGet implements NatCaller{
    @Override
    public Struct re(Struct _instance, Struct[] _args){
        return new StringStruct(DualFightBean.PAGE_ALLY);
    }
}
