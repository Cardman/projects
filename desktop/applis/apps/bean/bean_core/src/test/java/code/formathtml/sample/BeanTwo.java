package code.formathtml.sample;

import code.bean.Bean;
import code.bean.nat.StringMapObjectBase;


public class BeanTwo extends Bean {

    public BeanTwo() {
        setBaseForms(new StringMapObjectBase());
    }
    @Override
    public void beforeDisplaying() {
        SampleBeanStruct.spec(this);
    }
}
