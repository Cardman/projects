package code.formathtml.sample;

import code.bean.Bean;


public class BeanOne extends Bean {

    public BeanOne() {
        setClassName("code.formathtml.classes.BeanOne");
        setLanguage("");
    }

    @Override
    public void beforeDisplaying() {
        setClassName("code.formathtml.classes.BeanOne");
    }
}
