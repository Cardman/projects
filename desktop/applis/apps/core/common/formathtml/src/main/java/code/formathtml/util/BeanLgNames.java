package code.formathtml.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.structs.Message;
import code.expressionlanguage.Argument;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.*;
import code.maths.montecarlo.AbstractGenerator;
import code.util.*;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public abstract class BeanLgNames extends LgNames {

    public static final String OFF = "off";
    public static final String ON = "on";

    public BeanLgNames(AbstractGenerator _gene) {
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


    public abstract String processString(Argument _arg, ContextEl _ctx, RendStackCall _stack);

    public abstract void preInitBeans(Configuration _conf);
    public abstract void initBeans(Configuration _conf, String _language, Struct _db, ContextEl _ctx, RendStackCall _rendStack);

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


    public abstract String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _currentUrl, String _language, ContextEl _ctx, RendStackCall _rendStack);

    public abstract Message validate(Configuration _conf, NodeContainer _cont, String _validatorId, ContextEl _ctx, RendStackCall _rendStack);

    public abstract IdMap<RendDynOperationNode, ArgumentsPair> getAllArgs(CustList<RendDynOperationNode> _nodes, ContextEl _ctx, RendStackCall _rendStackCall);
    public abstract void setGlobalArgumentStruct(Struct _obj, ContextEl _ctx, RendStackCall _rendStackCall);
}
