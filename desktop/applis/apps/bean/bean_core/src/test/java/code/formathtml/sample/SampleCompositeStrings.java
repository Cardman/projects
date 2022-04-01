package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;

public class SampleCompositeStrings implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        BeanThree i_ = (BeanThree) ((SampleBeanStruct)_instance).getBean();
        StringList ls_ = i_.getStrings();
        return(CustBeanLgNames.getStringArray(ls_));
    }
}
