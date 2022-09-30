package code.formathtml.sample;

import code.bean.Bean;
import code.bean.nat.StringMapObjectBase;


public class BeanTwo extends Bean {

    public BeanTwo() {
        setClassName("code.formathtml.classes.BeanTwo");
        setBaseForms(new StringMapObjectBase());
    }
    @Override
    public void beforeDisplaying() {
        setClassName("code.formathtml.classes.BeanTwo");
        SampleBeanStruct.spec(this);
    }
}
