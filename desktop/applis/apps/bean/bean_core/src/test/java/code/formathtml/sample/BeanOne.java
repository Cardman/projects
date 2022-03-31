package code.formathtml.sample;

import code.bean.Bean;
import code.bean.nat.StringMapObjectBase;


public class BeanOne extends Bean {

    public BeanOne() {
        setClassName("code.formathtml.classes.BeanOne");
        setLanguage("");
        setBaseForms(new StringMapObjectBase());
    }

    @Override
    public void beforeDisplaying() {
        setClassName("code.formathtml.classes.BeanOne");
    }
}
