package aiki.beans;

import code.bean.nat.BeanNatCommonLgNamesInt;
import code.formathtml.Navigation;
import code.formathtml.util.WithPageInfos;

public interface BeanNatCommonLgNamesForm extends BeanNatCommonLgNamesInt, WithPageInfos {
    void execute(boolean _form, Navigation _navigation);
}
