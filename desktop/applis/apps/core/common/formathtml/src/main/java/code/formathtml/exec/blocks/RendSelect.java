package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
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
import code.util.*;
import code.util.core.StringUtil;

public final class RendSelect extends RendParentBlock implements RendWithEl {
    private final RendSelectOperators opers;
    private final StringMap<CustList<RendDynOperationNode>> execAttributesText;
    private final StringMap<CustList<RendDynOperationNode>> execAttributes;
    private final Element elt;
    private final boolean multiple;
    private final DefFieldUpdates defFieldUpdates;

    public RendSelect(StringMap<CustList<RendDynOperationNode>> _execAttributesText, StringMap<CustList<RendDynOperationNode>> _execAttributes,
                      Element _elt, boolean _multiple,
                      DefFieldUpdates _def, RendSelectOperators _ops) {
        opers = _ops;
        this.execAttributesText = _execAttributesText;
        this.execAttributes = _execAttributes;
        this.elt = _elt;
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
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        Argument value_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(opers.getOpsValue(), _ctx, _rendStack).lastValue().getArgument());
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        Argument map_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(opers.getOpsMap(), _ctx, _rendStack).lastValue().getArgument());
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        RendReadWrite rw_ = _rendStack.getLastPage().getRendReadWrite();
        Document doc_ = rw_.getDocument();
        Element docElementSelect_ = doc_.createElement(_cont.getRendKeyWords().getKeyWordSelect());
        if (multiple) {
            docElementSelect_.setAttribute(_cont.getRendKeyWords().getAttrMultiple(), _cont.getRendKeyWords().getAttrMultiple());
        }
        String name_ = elt.getAttribute(_cont.getRendKeyWords().getAttrName());
        String default_ = elt.getAttribute(_cont.getRendKeyWords().getAttrDefault());
        if (default_.isEmpty()) {
            processOptionsMapEnumName(_cont, map_.getStruct(),
                    docElementSelect_,
                    value_.getStruct(), _stds, _ctx, _rendStack);
        } else {
            processOptionsMapEnum(_cont, map_.getStruct(),
                    docElementSelect_, _stds, _ctx, _rendStack);
        }
        boolean id_ = false;
        for (EntryCust<String, CustList<RendDynOperationNode>> e: execAttributesText.entryList()) {
            IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(e.getValue(), _ctx, _rendStack);
            String txt_ = RendInput.idRad(args_,_ctx,_rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            id_ = true;
            docElementSelect_.setAttribute(e.getKey(),txt_);
        }
        if (id_ && !elt.getAttribute(_cont.getRendKeyWords().getAttrValidator()).trim().isEmpty()) {
            docElementSelect_.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator()),
                    elt.getAttribute(_cont.getRendKeyWords().getAttrValidator()));
        }
        docElementSelect_.setAttribute(_cont.getRendKeyWords().getAttrName(), name_);
        DefFetchedObjs def_ = processIndexes(_cont, elt, docElementSelect_, _ctx, _rendStack);
        end(_cont, _stds, _ctx, _rendStack, docElementSelect_, def_);
    }

    private void end(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack, Element _docSelect, DefFetchedObjs _def) {
        RendReadWrite rw_ = _rendStack.getLastPage().getRendReadWrite();
        Document doc_ = rw_.getDocument();
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        for (EntryCust<String, CustList<RendDynOperationNode>> e: execAttributes.entryList()) {
            IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(e.getValue(), _ctx, _rendStack);
            String txt_ = RendInput.idRad(args_,_ctx,_rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            _docSelect.setAttribute(e.getKey(),txt_);
        }

        prStack(_cont,_docSelect,defFieldUpdates,_def,_rendStack.getLastPage().getGlobalArgument(),_rendStack);
        simpleAppendChild(doc_, rw_, _docSelect);
        processBlock(_cont, _stds, _ctx, _rendStack);
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
