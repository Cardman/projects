package code.formathtml.classes;

import code.expressionlanguage.ContextEl;
import code.formathtml.util.BeanLgNames;
import code.util.StringList;

public class CustBeanLgNames extends BeanLgNames {

    @Override
    public StringList getDefaultValues(ContextEl _cont, String _className,
            String _value) {
        if (StringList.quickEq(_className, "code.formathtml.classes.EnumNumber")) {
            return StringList.splitChars(_value, ',');
        }
        if (StringList.quickEq(_className, "code.formathtml.classes.EnumNumbers")) {
            return new StringList(_value);
        }
        return super.getDefaultValues(_cont, _className, _value);
    }
}
