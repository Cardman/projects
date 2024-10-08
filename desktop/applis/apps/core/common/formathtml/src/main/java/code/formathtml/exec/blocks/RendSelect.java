package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DefFieldUpdates;
import code.formathtml.util.RendSelectOperators;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.sml.RendReadWrite;
import code.util.CustList;
import code.util.IdList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RendSelect extends RendElement {
    private final RendSelectOperators opers;
    private final boolean multiple;
    private final DefFieldUpdates defFieldUpdates;

    public RendSelect(StringMap<CustList<RendDynOperationNode>> _execAttributesText, StringMap<CustList<RendDynOperationNode>> _execAttributes,
                      Element _elt, boolean _multiple,
                      DefFieldUpdates _def, RendSelectOperators _ops) {
        super(_elt,_execAttributes,_execAttributesText);
        opers = _ops;
        this.multiple = _multiple;
        defFieldUpdates = _def;
    }

    public static DefFieldUpdates initElts(CustList<RendDynOperationNode> _opsRead, CustList<RendDynOperationNode> _opsConverter, String _idClass, String _idName, String _className, boolean _arrayConverter) {
        DefFieldUpdates s_ = new DefFieldUpdates();
        s_.setIdClass(_idClass);
        s_.setIdName(_idName);
        s_.setOpsRead(_opsRead);
        s_.setOpsConverter(_opsConverter);
        s_.setArrayConverter(_arrayConverter);
        s_.setClassName(_className);
        return s_;
    }

    @Override
    protected Struct processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        Struct value_ = RenderExpUtil.getFinalArg(opers.getOpsValue(), _ctx, _rendStack);
        if (value_ == null) {
            return null;
        }
        Struct map_ = RenderExpUtil.getFinalArg(opers.getOpsMap(), _ctx, _rendStack);
        if (map_ == null) {
            return null;
        }
        RendReadWrite rw_ = _rendStack.getLastPage().getRendReadWrite();
        Document doc_ = rw_.getDocument();
        doc_.renameNode(_nextWrite,_cont.getRendKeyWords().getKeyWordSelect());
        Element docElementSelect_ = (Element) _nextWrite;
        if (multiple) {
            docElementSelect_.setAttribute(_cont.getRendKeyWords().getAttrMultiple(), _cont.getRendKeyWords().getAttrMultiple());
        }
        String default_ = getRead().getAttribute(_cont.getRendKeyWords().getAttrDefault());
        if (default_.isEmpty()) {
            processOptionsMapEnumName(_cont, map_,
                    docElementSelect_,
                    value_, _stds, _ctx, _rendStack);
        } else {
            processOptionsMapEnum(_cont, map_,
                    docElementSelect_, _stds, _ctx, _rendStack);
        }
        if (!getRead().getAttribute(_cont.getRendKeyWords().getAttrValidator()).trim().isEmpty()) {
            docElementSelect_.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator()),
                    getRead().getAttribute(_cont.getRendKeyWords().getAttrValidator()));
        }
        DefFetchedObjs def_ = processIndexes(_cont, getRead(), docElementSelect_, _ctx, _rendStack);
        if (def_.getArg() == null) {
            return null;
        }
        docElementSelect_.removeAttribute(_cont.getRendKeyWords().getAttrDefault());
        docElementSelect_.removeAttribute(_cont.getRendKeyWords().getAttrVarValue());
        docElementSelect_.removeAttribute(_cont.getRendKeyWords().getAttrMap());
        docElementSelect_.removeAttribute(_cont.getRendKeyWords().getAttrConvert());
        docElementSelect_.removeAttribute(_cont.getRendKeyWords().getAttrConvertField());
        docElementSelect_.removeAttribute(_cont.getRendKeyWords().getAttrConvertFieldValue());
        docElementSelect_.removeAttribute(_cont.getRendKeyWords().getAttrConvertValue());
        docElementSelect_.removeAttribute(_cont.getRendKeyWords().getAttrValidator());
        prStack(_cont,docElementSelect_,defFieldUpdates,def_,_rendStack.getLastPage().getGlobalStruct(),_rendStack);
        return NullStruct.NULL_VALUE;
    }

    private void processOptionsMapEnum(Configuration _conf, Struct _extractedMap,
                                       Element _docElementSelect, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        Struct argDef_ = RenderExpUtil.getFinalArg(opers.getOpsDefault(), _ctx, _rendStackCall);
        if (argDef_ == null) {
            return;
        }
        processOptionsMapEnumName(_conf,_extractedMap, _docElementSelect,argDef_, _advStandards, _ctx, _rendStackCall);
    }

    private void processOptionsMapEnumName(Configuration _conf, Struct _extractedMap,
                                           Element _docElementSelect, Struct _returnedVarValue, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        CustList<Struct> obj_ = values(_returnedVarValue, _advStandards, _ctx, _rendStackCall);
        if (obj_ == null) {
            return;
        }
        Struct arg_ = iteratorMultTable(_extractedMap, _advStandards, _ctx, _rendStackCall);
        if (arg_ == null) {
            return;
        }
        processOptions(_conf, _docElementSelect, obj_, arg_, _advStandards, _ctx, _rendStackCall);
    }

    private void processOptions(Configuration _conf, Element _docElementSelect, CustList<Struct> _obj, Struct _l, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        while (true) {
            RendReadWrite rw_ = _rendStackCall.getLastPage().getRendReadWrite();
            Document doc_ = rw_.getDocument();
            Struct hasNext_ = hasNextPair(_l, _advStandards, _ctx, _rendStackCall);
            if (hasNext_ == null || BooleanStruct.isFalse(hasNext_)) {
                return;
            }
            Struct nextPair_ = nextPair(_l, _advStandards, _ctx, _rendStackCall);
            if (nextPair_ == null) {
                return;
            }
            Struct first_ = first(nextPair_, _advStandards, _ctx, _rendStackCall);
            if (first_ == null) {
                return;
            }
            if (first_ == NullStruct.NULL_VALUE) {
                continue;
            }
            Element option_ = doc_.createElement(_conf.getRendKeyWords().getKeyWordOption());
            String value_ = processOptionValue(first_, _ctx, _rendStackCall);
            if (value_ == null) {
                return;
            }
            setOption(_conf, _obj, first_, option_, value_);
            Struct second_ = second(nextPair_, _advStandards, _ctx, _rendStackCall);
            if (exitAppendOption(_docElementSelect,_ctx,_rendStackCall,second_,option_)) {
                return;
            }
        }
    }
    private boolean exitAppendOption(Element _docElementSelect, ContextEl _ctx, RendStackCall _rendStackCall,Struct _second,Element _option) {
        if (_second == null) {
            return true;
        }
        String txt_ = processOptionText(_second, _ctx, _rendStackCall);
        if (txt_ == null) {
            return true;
        }
        RendReadWrite rw_ = _rendStackCall.getLastPage().getRendReadWrite();
        Document doc_ = rw_.getDocument();
        _option.appendChild(doc_.createTextNode(txt_));
        _docElementSelect.appendChild(_option);
        return false;
    }

    private void setOption(Configuration _conf, CustList<Struct> _obj, Struct _o, Element _option, String _value) {
        _option.setAttribute(_conf.getRendKeyWords().getAttrValue(), _value);
        for (Struct n: _obj) {
            if (n.sameReference(_o)) {
                _option.setAttribute(_conf.getRendKeyWords().getAttrSelected(), _conf.getRendKeyWords().getAttrSelected());
                break;
            }
        }
    }

    private String processOptionValue(Struct _arg, ContextEl _ctx, RendStackCall _rendStackCall) {
        if (opers.getOpsConverterField().isEmpty()) {
            return BeanCustLgNames.processString(_arg, _ctx, _rendStackCall);
        }
        LocalVariable locVar_ = LocalVariable.newLocalVariable(_arg, _ctx.getStandards().getContent().getCoreNames().getAliasObject());
        _rendStackCall.getLastPage().putValueVar("0", new VariableWrapper(locVar_));
        Struct arg_ = RenderExpUtil.getFinalArg(opers.getOpsConverterField(), _ctx, _rendStackCall);
        _rendStackCall.getLastPage().removeRefVar("0");
        if (arg_ == null) {
            return null;
        }
        return BeanCustLgNames.processString(arg_, _ctx, _rendStackCall);
    }
    private String processOptionText(Struct _arg, ContextEl _ctx, RendStackCall _rendStackCall) {
        if (opers.getOpsConverterFieldValue().isEmpty()) {
            return BeanCustLgNames.processString(_arg, _ctx, _rendStackCall);
        }
        LocalVariable locVar_ = LocalVariable.newLocalVariable(_arg, _ctx.getStandards().getContent().getCoreNames().getAliasObject());
        _rendStackCall.getLastPage().putValueVar("0", new VariableWrapper(locVar_));
        Struct arg_ = RenderExpUtil.getFinalArg(opers.getOpsConverterFieldValue(), _ctx, _rendStackCall);
        _rendStackCall.getLastPage().removeRefVar("0");
        if (arg_ == null) {
            return null;
        }
        return BeanCustLgNames.processString(arg_, _ctx, _rendStackCall);
    }

    private CustList<Struct> values(Struct _returnedVarValue, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStackCall) {
        IdList<Struct> obj_ = new IdList<Struct>();
        if (multiple) {
            Struct arg_ = iterator(_returnedVarValue, _stds, _ctx, _rendStackCall);
            if (arg_ == null) {
                return null;
            }
            while (true) {
                Struct hasNext_ = hasNext(arg_, _stds, _ctx, _rendStackCall);
                if (hasNext_ == null) {
                    return null;
                }
                if (BooleanStruct.isFalse(hasNext_)) {
                    break;
                }
                Struct next_ = next(arg_, _stds, _ctx, _rendStackCall);
                if (next_ == null) {
                    return null;
                }
                obj_.add(next_);
            }
        } else {
            obj_.add(_returnedVarValue);
        }
        return obj_;
    }
    private DefFetchedObjs processIndexes(Configuration _cont, Element _read, Element _write, ContextEl _ctx, RendStackCall _rendStackCall) {
        DefFetchedObjs def_ = fetchName(_cont, _read, _ctx, _rendStackCall, "", defFieldUpdates.getOpsRead());
        look(_cont,_write,def_,_rendStackCall);
//        fetchValue(_cont,_read,_write, opers, _ctx, _rendStackCall, def_);
        return fetchValue(_cont,_read,_write, opers, _ctx, _rendStackCall, def_);
    }
}
