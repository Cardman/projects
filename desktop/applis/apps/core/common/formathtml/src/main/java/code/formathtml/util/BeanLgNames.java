package code.formathtml.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.DoubleInfo;
import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.blocks.RendImport;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.opers.RendSettableFieldOperation;
import code.formathtml.exec.opers.RendStdFctOperation;
import code.formathtml.structs.Message;
import code.expressionlanguage.Argument;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.*;
import code.maths.montecarlo.AbstractGenerator;
import code.sml.Element;
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


    public abstract void beforeDisplaying(Struct _arg, Configuration _cont, ContextEl _ctx, RendStackCall _rendStack);
    public abstract String processString(Argument _arg, ContextEl _ctx, RendStackCall _stack);

    public abstract void preInitBeans(Configuration _conf);
    public abstract void initBeans(Configuration _conf, String _language, Struct _db, ContextEl _ctx, RendStackCall _rendStack);

    public abstract AbstractWrapper newWrapper(LocalVariable _local);
    public String getInputClass(Element _write, Configuration _conf) {
        return _write.getAttribute(StringUtil.concat(_conf.getPrefix(),_conf.getRendKeyWords().getAttrClassName()));
    }
    public ResultErrorStd convert(NodeContainer _container, Configuration _conf, ContextEl _context, RendStackCall _rendStackCall) {
        CustList<RendDynOperationNode> ops_ = _container.getOpsConvert();
        if (!ops_.isEmpty()) {
            String varNameConvert_ = _container.getVarNameConvert();
            LocalVariable lv_ = newLocVar(_container);
            _rendStackCall.getLastPage().putValueVar(varNameConvert_, newWrapper(lv_));
            _rendStackCall.getLastPage().setGlobalArgumentStruct(_container.getBean());
            Argument res_ = RenderExpUtil.calculateReuse(ops_, this, _context, _rendStackCall);
            _rendStackCall.getLastPage().removeRefVar(varNameConvert_);
            ResultErrorStd out_ = new ResultErrorStd();
            if (_context.callsOrException(_rendStackCall.getStackCall())) {
                return out_;
            }
            out_.setResult(res_.getStruct());
            return out_;
        }
        String className_ = _container.getNodeInformation().getInputClass();
        StringList values_ = _container.getValue();
        return getStructToBeValidated(values_, className_, _conf, _context, _rendStackCall);
    }
    protected LocalVariable newLocVar(NodeContainer _container) {
        StringList values_ = _container.getValue();
        if (_container.isArrayConverter()) {
            int len_ = values_.size();
            ArrayStruct arr_ = new ArrayStruct(len_,StringExpUtil.getPrettyArrayType(getAliasString()));
            for (int i = 0; i < len_; i++) {
                arr_.set(i, new StringStruct(values_.get(i)));
            }
            return LocalVariable.newLocalVariable(arr_,StringExpUtil.getPrettyArrayType(getAliasString()));
        }
        if (!values_.isEmpty()) {
            return LocalVariable.newLocalVariable(new StringStruct(values_.first()), getAliasString());
        }
        return LocalVariable.newLocalVariable(NullStruct.NULL_VALUE, getAliasString());
    }
    public ResultErrorStd getStructToBeValidated(StringList _values, String _className, Configuration _context, ContextEl _ctx, RendStackCall _stack) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(_className, getAliasString())) {
            String v_;
            if (_values.isEmpty()) {
                v_ = null;
            } else {
                v_ = _values.first();
            }
            res_.setResult(wrapStd(v_));
            return res_;
        }
        if (_values.isEmpty() || _values.first().trim().isEmpty()) {
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return getStructToBeValidatedPrim(_values, _className, _ctx, _stack, res_);
    }

    public abstract ResultErrorStd getStructToBeValidatedPrim(StringList _values, String _className, ContextEl _ctx, RendStackCall _stack, ResultErrorStd _res);

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

    public static Struct wrapStd(String _element) {
        if (_element == null) {
            return NullStruct.NULL_VALUE;
        }
        return new StringStruct(_element);
    }


    public abstract String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _currentUrl, String _language, ContextEl _ctx, RendStackCall _rendStack);

    public abstract Message validate(Configuration _conf, NodeContainer _cont, String _validatorId, ContextEl _ctx, RendStackCall _rendStack);

}
