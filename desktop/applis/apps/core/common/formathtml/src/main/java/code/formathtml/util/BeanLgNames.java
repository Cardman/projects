package code.formathtml.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.HtmlPage;
import code.formathtml.RendRequestUtil;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.structs.Message;
import code.maths.montecarlo.AbstractGenerator;
import code.sml.Element;
import code.util.CustList;
import code.util.IdMap;
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

    @Override
    public void logIssue(String _info) {
    }

    public static int parseInt(String _string, int _def) {
        String value_ = _string.trim();
        if (value_.isEmpty()) {
            return _def;
        }
        return NumberUtil.parseInt(value_);
    }

    public abstract void preInitBeans(Configuration _conf);

    public abstract AbstractWrapper newWrapper(LocalVariable _local);

    public abstract ResultErrorStd convert(NodeContainer _container, ContextEl _context, RendStackCall _rendStackCall);

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

    public abstract String initializeRendSessionDoc(ContextEl _ctx, String _language, Configuration _configuration, Struct _db, RendStackCall _rendStackCall);
    public abstract String processRendAnchorRequest(Element _ancElt, String _language, Configuration _configuration, HtmlPage _htmlPage, ContextEl _ctx, RendStackCall _rendStack);
    public Struct redirect(HtmlPage _htmlPage, Struct _bean, ContextEl _ctx, RendStackCall _rendStack){
        Struct ret_;
        if (_htmlPage.isForm()) {
            ret_ = RendRequestUtil.redirectForm(new Argument(_bean),(int)_htmlPage.getUrl(), this, _ctx, _rendStack, _htmlPage);
        } else {
            ret_=RendRequestUtil.redirect(new Argument(_bean),(int)_htmlPage.getUrl(), this, _ctx, _rendStack, _htmlPage);
        }
        return ret_;
    }

    public abstract Message validate(Configuration _conf, NodeContainer _cont, String _validatorId, ContextEl _ctx, RendStackCall _rendStack);

    public abstract IdMap<RendDynOperationNode, ArgumentsPair> getAllArgs(CustList<RendDynOperationNode> _nodes, ContextEl _ctx, RendStackCall _rendStackCall);
    public abstract void setGlobalArgumentStruct(Struct _obj, ContextEl _ctx, RendStackCall _rendStackCall);

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
