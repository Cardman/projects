package code.formathtml.util;

import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.maths.montecarlo.AbstractGenerator;
import code.sml.HtmlPageInt;
import code.util.StringList;

public abstract class BeanLgNames extends LgNames {

    public static final String DOT = ".";

    public static final String CALL_METHOD = "$";

    public static final String EMPTY_STRING = "";

    protected BeanLgNames(AbstractGenerator _gene) {
        super(_gene);
    }


    public abstract HtmlPageInt getPage();

    public static Struct wrapStd(StringList _element) {
        String v_ = oneEltNull(_element);
        return wrapStd(v_);
    }

    public static String oneEltNull(StringList _element) {
        String w_;
        if (_element.isEmpty()) {
            w_ = null;
        } else {
            w_ = _element.first();
        }
        return w_;
    }

    public static Struct wrapStd(String _element) {
        if (_element == null) {
            return NullStruct.NULL_VALUE;
        }
        return new StringStruct(_element);
    }


}
