package code.formathtml.sample;

import code.bean.Bean;


public class BeanTwo extends Bean {

    public BeanTwo() {
        setClassName("code.formathtml.classes.BeanTwo");
    }
    @Override
    public void beforeDisplaying() {
        setClassName("code.formathtml.classes.BeanTwo");
    }
}
