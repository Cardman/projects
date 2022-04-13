package code.formathtml.util;

import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.HtmlPage;
import code.maths.montecarlo.AbstractGenerator;
import code.util.StringList;
import code.util.core.NumberUtil;

public abstract class BeanLgNames extends LgNames {

    public static final String OFF = "off";
    public static final String ON = "on";

    protected static final String REF_TAG = "#";

    protected static final String DOT = ".";

    protected static final String CALL_METHOD = "$";

    protected static final String EMPTY_STRING = "";
    private String currentBeanName = "";

    private String currentUrl = "";

    protected BeanLgNames(AbstractGenerator _gene) {
        super(_gene);
    }


    public static int parseInt(String _string, int _def) {
        String value_ = _string.trim();
        if (value_.isEmpty()) {
            return _def;
        }
        return NumberUtil.parseInt(value_);
    }

    public abstract HtmlPage getPage();
    public String getAliasPrimBoolean() {
        return getContent().getPrimTypes().getAliasPrimBoolean();
    }

    public String getAliasPrimLong() {
        return getContent().getPrimTypes().getAliasPrimLong();
    }

    public String getAliasObject() {
        return getContent().getCoreNames().getAliasObject();
    }
    public String getAliasString() {
        return getContent().getCharSeq().getAliasString();
    }

    public static Struct wrapStd(StringList _element) {
        String v_ = oneElt(_element);
        return wrapStd(v_);
    }

    public static String oneElt(StringList _element) {
        String v_;
        if (_element.isEmpty()) {
            v_ = null;
        } else {
            v_ = _element.first();
        }
        return v_;
    }

    public static Struct wrapStd(String _element) {
        if (_element == null) {
            return NullStruct.NULL_VALUE;
        }
        return new StringStruct(_element);
    }

    public String getCurrentBeanName() {
        return currentBeanName;
    }

    public void setCurrentBeanName(String _v) {
        this.currentBeanName = _v;
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String _v) {
        this.currentUrl = _v;
    }

}
