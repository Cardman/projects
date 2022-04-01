package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;

public class SampleBeanOneMap implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return(CustBeanLgNames.getTree(((SampleBeanStruct)_instance).getMap()));
    }
}
