package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;

public class SampleBeanOneTree implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        return(CustBeanLgNames.getTree(((SampleBeanStruct)_instance).getTree()));
    }
}
