package code.formathtml.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.DoubleInfo;
import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.exec.RendStdFctOperation;
import code.formathtml.structs.Message;
import code.expressionlanguage.Argument;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.*;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.exec.RendSettableFieldOperation;
import code.maths.montecarlo.AbstractGenerator;
import code.sml.Document;
import code.sml.Element;
import code.util.*;

public abstract class BeanLgNames extends LgNames {

    public static final String OFF = "off";
    public static final String ON = "on";

    public BeanLgNames(AbstractGenerator _gene) {
        super(_gene);
    }

    public static int parseInt(String _string, int _def) {
        String value_ = _string.trim();
        if (value_.isEmpty()) {
            return _def;
        }
        return Numbers.parseInt(value_);
    }


    public abstract void beforeDisplaying(Struct _arg,Configuration _cont);
    public abstract String getScope(Struct _bean, Configuration _cont);
    public abstract void setScope(Struct _bean, String _scope,Configuration _cont);
    public abstract void setLanguage(Struct _bean, String _scope,Configuration _cont);
    public abstract String processString(Argument _arg,Configuration _cont);

    public abstract Argument iteratorMultTable(Struct _arg, Configuration _cont);
    public abstract Argument hasNextPair(Struct _arg,Configuration _conf);
    public abstract Argument nextPair(Struct _arg,Configuration _conf);
    public abstract Argument first(Struct _arg,Configuration _conf);
    public abstract Argument second(Struct _arg,Configuration _conf);
    public abstract Argument iterator(Struct _arg,Configuration _cont);
    public abstract Argument hasNext(Struct _arg,Configuration _cont);
    public abstract Argument next(Struct _arg,Configuration _cont);

    public abstract String getStringKey(Configuration _conf, Struct _instance);

    public abstract void preInitBeans(Configuration _conf);
    public abstract void initBeans(Configuration _conf,String _language,Struct _db);

    public String getInputClass(Element _write, Configuration _conf) {
        return _write.getAttribute(StringList.concat(_conf.getPrefix(),_conf.getRendKeyWords().getAttrClassName()));
    }
    public ResultErrorStd convert(NodeContainer _container, Configuration _conf) {
        CustList<RendDynOperationNode> ops_ = _container.getOpsConvert();
        if (!ops_.isEmpty()) {
            String varNameConvert_ = _container.getVarNameConvert();
            LocalVariable lv_ = newLocVar(_container,_conf);
            _conf.getLastPage().putLocalVar(varNameConvert_, lv_);
            _conf.getLastPage().setGlobalArgumentStruct(_container.getBean(), _conf);
            Argument res_ = RenderExpUtil.calculateReuse(ops_, _conf);
            _conf.getLastPage().removeLocalVar(varNameConvert_);
            ResultErrorStd out_ = new ResultErrorStd();
            if (_conf.getContext().hasException()) {
                return out_;
            }
            out_.setResult(res_.getStruct());
            return out_;
        }
        String className_ = _container.getNodeInformation().getInputClass();
        StringList values_ = _container.getValue();
        return getStructToBeValidated(values_, className_, _conf);
    }
    protected LocalVariable newLocVar(NodeContainer _container, Configuration _conf) {
        StringList values_ = _container.getValue();
        if (_container.isArrayConverter()) {
            int len_ = values_.size();
            ArrayStruct arr_ = new ArrayStruct(new Struct[len_],StringExpUtil.getPrettyArrayType(getAliasString()));
            for (int i = 0; i < len_; i++) {
                arr_.getInstance()[i] = new StringStruct(values_.get(i));
            }
            return LocalVariable.newLocalVariable(arr_,StringExpUtil.getPrettyArrayType(getAliasString()));
        }
        if (!values_.isEmpty()) {
            return LocalVariable.newLocalVariable(new StringStruct(values_.first()),getAliasString());
        }
        return LocalVariable.newLocalVariable(NullStruct.NULL_VALUE,getAliasString());
    }
    public ResultErrorStd getStructToBeValidated(StringList _values, String _className, Configuration _context) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(_className, getAliasString())) {
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
        byte cast_ = ExecClassArgumentMatching.getPrimitiveWrapCast(_className, this);
        if (cast_ == PrimitiveTypes.BOOL_WRAP) {
            res_.setResult(BooleanStruct.of(StringList.quickEq(_values.first(),ON)));
            return res_;
        }
        if (cast_ == PrimitiveTypes.CHAR_WRAP) {
            res_.setResult(new CharStruct(_values.first().trim().charAt(0)));
            return res_;
        }
        if (cast_ > PrimitiveTypes.LONG_WRAP) {
            DoubleInfo doubleInfo_ = NumParsers.splitDouble(_values.first());
            if (!doubleInfo_.isValid()) {
                _context.getContext().setException(new ErrorStruct(_context.getContext(),getAliasCastType()));
                return res_;
            }
            res_.setResult(NumParsers.convertToFloat(cast_,new DoubleStruct(doubleInfo_.getValue())));
            return res_;
        }
        LongInfo val_ = NumParsers.parseLong(_values.first(), 10);
        if (!val_.isValid()) {
            _context.getContext().setException(new ErrorStruct(_context.getContext(),getAliasCastType()));
            return res_;
        }
        res_.setResult(NumParsers.convertToInt(cast_,new LongStruct(val_.getValue())));
        return res_;
    }

    public boolean isConveritble(String _className) {
        if (StringList.quickEq(_className, getAliasString())) {
            return true;
        }

        return AnaTypeUtil.isPrimitiveOrWrapper(_className, this);
    }
    public abstract ReportedMessages setupAll(Navigation _nav, Configuration _conf, StringMap<String> _files, AnalyzedPageEl _page, RendAnalysisMessages _rend);
    protected static Struct wrapStd(String _element) {
        if (_element == null) {
            return NullStruct.NULL_VALUE;
        }
        return new StringStruct(_element);
    }
    public void setBeanForms(Configuration _conf, Struct _mainBean,
                             RendImport _node, boolean _keepField, String _beanName) {
        if (_mainBean == null) {
            return;
        }
        Struct bean_ = _conf.getBuiltBeans().getVal(_beanName);
        if (bean_ == null) {
            return;
        }
        gearFw(_conf, _mainBean, _node, _keepField, bean_);
    }

    public AnalyzedPageEl load(Configuration _configuration, String _lgCode, Document _document, RendAnalysisMessages _rend) {
        for (Element c: _document.getDocumentElement().getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringList.quickEq(fieldName_, "firstUrl")) {
                _configuration.setFirstUrl(c.getAttribute("value"));
                continue;
            }
            if (StringList.quickEq(fieldName_, "prefix")) {
                _configuration.setPrefix(c.getAttribute("value"));
                continue;
            }
            if (StringList.quickEq(fieldName_, "messagesFolder")) {
                _configuration.setMessagesFolder(c.getAttribute("value"));
                continue;
            }
            if (StringList.quickEq(fieldName_, "beans")) {
                _configuration.setBeansInfos(ReadConfiguration.loadBeans(c));
                continue;
            }
            if (StringList.quickEq(fieldName_, "properties")) {
                _configuration.setProperties(ReadConfiguration.loadStringMapString(c));
                continue;
            }
            if (StringList.quickEq(fieldName_, "navigation")) {
                _configuration.setNavigation(ReadConfiguration.loadStringMapStrings(c));
                continue;
            }

            if (StringList.quickEq(fieldName_, "addedFiles")) {
                _configuration.setAddedFiles(ReadConfiguration.getStringList(c));
                continue;
            }
            if (StringList.quickEq(fieldName_, "renderFiles")) {
                _configuration.setRenderFiles(ReadConfiguration.getStringList(c));
            }
        }
        return specificLoad(_configuration,_lgCode,_document, _rend);
    }
    protected abstract AnalyzedPageEl specificLoad(Configuration _configuration, String _lgCode, Document _document, RendAnalysisMessages _rend);


    public abstract Argument getCommonArgument(RendSettableFieldOperation _rend, Argument _previous, Configuration _conf);
    public abstract Argument getCommonSetting(RendSettableFieldOperation _rend, Argument _previous, Configuration _conf, Argument _right);
    public abstract Argument getCommonFctArgument(RendStdFctOperation _rend, Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf);


    protected abstract void gearFw(Configuration _conf, Struct _mainBean, RendImport _node, boolean _keepField, Struct _bean);

    public abstract void forwardDataBase(Struct _bean, Struct _to, Configuration _conf);
    public abstract void storeForms(Struct _bean, Configuration _conf);

    public abstract void setStoredForms(Struct _bean, Configuration _conf);

    public abstract Message validate(Configuration _conf,NodeContainer _cont, String _validatorId);

}
