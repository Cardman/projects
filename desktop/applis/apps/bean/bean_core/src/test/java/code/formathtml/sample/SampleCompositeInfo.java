package code.formathtml.sample;

import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;

public class SampleCompositeInfo implements NatCaller {
    @Override
    public Struct re(Struct _instance, Struct[] _args) {
        if (((SampleBeanStruct)_instance).getOthers().isEmpty()) {
            ((SampleBeanStruct)_instance).getOthers().addEntry("",new BeanThree());
        }
        return new SampleBeanStruct(((SampleBeanStruct)_instance).getComposite());
    }
}
