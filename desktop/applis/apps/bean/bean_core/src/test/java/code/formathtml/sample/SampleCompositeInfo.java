package code.formathtml.sample;

import code.bean.nat.*;

public class SampleCompositeInfo implements NatCaller {
    @Override
    public NaSt re(NaSt _instance, NaSt[] _args) {
//        if (((SampleBeanStruct)_instance).getOthers().isEmpty()) {
//            ((SampleBeanStruct)_instance).getOthers().addEntry("",new BeanThree());
//        }
        return _instance;
//        return new SampleBeanStruct(((SampleBeanStruct)_instance).getComposite());
    }
}
