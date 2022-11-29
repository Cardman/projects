package code.formathtml.sample;

import code.bean.Bean;


public class BeanTwo extends Bean {

//    public BeanTwo() {
//        setBaseForms(new StringMapObjectBase());
//    }
    @Override
    public void beforeDisplaying() {
        SampleBeanStruct.spec(this);
    }
}
