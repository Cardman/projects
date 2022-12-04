package aiki.beans;

import code.bean.nat.BeanNatCommonLgNamesInt;
import code.bean.nat.NatNavigation;
import code.sml.WithPageInfos;

public interface BeanNatCommonLgNamesForm extends BeanNatCommonLgNamesInt, WithPageInfos {
    void execute(boolean _form, NatNavigation _navigation);
}
