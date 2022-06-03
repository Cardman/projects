package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
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
import code.formathtml.exec.stacks.RendReadWrite;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DefFieldUpdates;
import code.formathtml.util.RendSelectOperators;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
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
    protected boolean processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        Argument value_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(opers.getOpsValue(), _ctx, _rendStack).lastValue().getArgument());
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return true;
        }
        Argument map_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(opers.getOpsMap(), _ctx, _rendStack).lastValue().getArgument());
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return true;
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
            processOptionsMapEnumName(_cont, map_.getStruct(),
                    docElementSelect_,
                    value_.getStruct(), _stds, _ctx, _rendStack);
        } else {
            processOptionsMapEnum(_cont, map_.getStruct(),
                    docElementSelect_, _stds, _ctx, _rendStack);
        }
        if (!getRead().getAttribute(_cont.getRendKeyWords().getAttrValidator()).trim().isEmpty()) {
            docElementSelect_.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator()),
                    getRead().getAttribute(_cont.getRendKeyWords().getAttrValidator()));
        }
        DefFetchedObjs def_ = processIndexes(_cont, getRead(), docElementSelect_, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return true;
        }
        docElementSelect_.removeAttribute(_cont.getRendKeyWords().getAttrDefault());
        docElementSelect_.removeAttribute(_cont.getRendKeyWords().getAttrVarValue());
        docElementSelect_.removeAttribute(_cont.getRendKeyWords().getAttrMap());
        docElementSelect_.removeAttribute(_cont.getRendKeyWords().getAttrConvert());
        docElementSelect_.removeAttribute(_cont.getRendKeyWords().getAttrConvertField());
        docElementSelect_.removeAttribute(_cont.getRendKeyWords().getAttrConvertFieldValue());
        docElementSelect_.removeAttribute(_cont.getRendKeyWords().getAttrConvertValue());
        docElementSelect_.removeAttribute(_cont.getRendKeyWords().getAttrValidator());
        prStack(_cont,docElementSelect_,defFieldUpdates,def_,_rendStack.getLastPage().getGlobalArgument(),_rendStack);
        return _ctx.callsOrException(_rendStack.getStackCall());
    }

    private void processOptionsMapEnum(Configuration _conf, Struct _extractedMap,
                                       Element _docElementSelect, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        Argument argDef_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(opers.getOpsDefault(), _ctx, _rendStackCall).lastValue().getArgument());
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        processOptionsMapEnumName(_conf,_extractedMap, _docElementSelect,argDef_.getStruct(), _advStandards, _ctx, _rendStackCall);
    }

    private void processOptionsMapEnumName(Configuration _conf, Struct _extractedMap,
                                           Element _docElementSelect, Struct _returnedVarValue, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        CustList<Struct> obj_ = values(_returnedVarValue, _advStandards, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        Argument arg_ = iteratorMultTable(_extractedMap, _advStandards, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        Struct l_ = arg_.getStruct();
        processOptions(_conf, _docElementSelect, obj_, l_, _advStandards, _ctx, _rendStackCall);
    }

    private void processOptions(Configuration _conf, Element _docElementSelect, CustList<Struct> _obj, Struct _l, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        while (true) {
            RendReadWrite rw_ = _rendStackCall.getLastPage().getRendReadWrite();
            Document doc_ = rw_.getDocument();
            Argument hasNext_ = hasNextPair(_l, _advStandards, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall()) || BooleanStruct.isFalse(hasNext_.getStruct())) {
                return;
            }
            Argument nextPair_ = nextPair(_l, _advStandards, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                return;
            }
            Struct entry_ = nextPair_.getStruct();
            Argument first_ = first(entry_, _advStandards, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                return;
            }
            Struct o_ = first_.getStruct();
            if (o_ == NullStruct.NULL_VALUE) {
                continue;
            }
            Element option_ = doc_.createElement(_conf.getRendKeyWords().getKeyWordOption());
            String value_ = processOptionValue(o_, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                return;
            }
            setOption(_conf, _obj, o_, option_, value_);
            Argument second_ = second(entry_, _advStandards, _ctx, _rendStackCall);
            if (exitAppendOption(_docElementSelect,_ctx,_rendStackCall,second_,option_)) {
                return;
            }
        }
    }
    private boolean exitAppendOption(Element _docElementSelect, ContextEl _ctx, RendStackCall _rendStackCall,Argument _second,Element _option) {
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return true;
        }
        String txt_ = processOptionText(_second, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
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
            return getStringKey(_arg, _ctx, _rendStackCall);
        }
        LocalVariable locVar_ = LocalVariable.newLocalVariable(_arg, _ctx.getStandards().getContent().getCoreNames().getAliasObject());
        _rendStackCall.getLastPage().putValueVar("0", new VariableWrapper(locVar_));
        Argument arg_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(opers.getOpsConverterField(), _ctx, _rendStackCall).lastValue().getArgument());
        _rendStackCall.getLastPage().removeRefVar("0");
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return EMPTY_STRING;
        }
        return BeanCustLgNames.processStr(arg_, _ctx, _rendStackCall);
    }
    private String processOptionText(Argument _arg, ContextEl _ctx, RendStackCall _rendStackCall) {
        if (opers.getOpsConverterFieldValue().isEmpty()) {
            return BeanCustLgNames.processStr(_arg, _ctx, _rendStackCall);
        }
        LocalVariable locVar_ = LocalVariable.newLocalVariable(_arg.getStruct(), _ctx.getStandards().getContent().getCoreNames().getAliasObject());
        _rendStackCall.getLastPage().putValueVar("0", new VariableWrapper(locVar_));
        Argument arg_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(opers.getOpsConverterFieldValue(), _ctx, _rendStackCall).lastValue().getArgument());
        _rendStackCall.getLastPage().removeRefVar("0");
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return EMPTY_STRING;
        }
        return BeanCustLgNames.processStr(arg_, _ctx, _rendStackCall);
    }

    private CustList<Struct> values(Struct _returnedVarValue, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStackCall) {
        IdList<Struct> obj_ = new IdList<Struct>();
        if (multiple) {
            Argument arg_ = iterator(_returnedVarValue, _stds, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                return obj_;
            }
            Struct it_ = arg_.getStruct();
            while (true) {
                Argument hasNext_ = hasNext(it_, _stds, _ctx, _rendStackCall);
                if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                    return obj_;
                }
                if (BooleanStruct.isFalse(hasNext_.getStruct())) {
                    break;
                }
                Argument next_ = next(it_, _stds, _ctx, _rendStackCall);
                if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                    return obj_;
                }
                obj_.add(next_.getStruct());
            }
        } else {
            obj_.add(_returnedVarValue);
        }
        return obj_;
    }
    private DefFetchedObjs processIndexes(Configuration _cont, Element _read, Element _write, ContextEl _ctx, RendStackCall _rendStackCall) {
        DefFetchedObjs def_ = fetchName(_cont, _read, _ctx, _rendStackCall, "", defFieldUpdates.getOpsRead());
        look(_cont,_write,def_,_rendStackCall);
        fetchValue(_cont,_read,_write, opers.getOpsValue(), opers.getOpsConverterField(), _ctx, _rendStackCall);
        return def_;
    }
}
