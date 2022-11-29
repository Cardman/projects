package aiki.beans;

import code.bean.nat.BeanNatCommonLgNamesInt;
import code.formathtml.Navigation;
import code.formathtml.util.WithPageInfos;
import code.sml.Element;

public interface BeanNatCommonLgNamesForm extends BeanNatCommonLgNamesInt, WithPageInfos {
    void execute(boolean _form, Element _elt, Navigation _navigation);
}
