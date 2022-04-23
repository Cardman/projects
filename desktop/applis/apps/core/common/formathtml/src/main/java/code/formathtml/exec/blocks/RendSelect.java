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
import code.sml.Document;
import code.sml.Element;
import code.util.*;
import code.util.core.StringUtil;

public final class RendSelect extends RendParentBlock implements RendWithEl {
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsValue = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsMap = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsDefault = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverter = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverterField = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverterFieldValue = new CustList<RendDynOperationNode>();
    private StringMap<CustList<RendDynOperationNode>> execAttributesText;
    private StringMap<CustList<RendDynOperationNode>> execAttributes;
    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private final Element elt;
    private final boolean multiple;
    private String className = EMPTY_STRING;
    private final boolean arrayConverter;

    public RendSelect(CustList<RendDynOperationNode> _opsRead, CustList<RendDynOperationNode> _opsValue,
                      CustList<RendDynOperationNode> _opsMap, CustList<RendDynOperationNode> _opsDefault, CustList<RendDynOperationNode> _opsConverter,
                      CustList<RendDynOperationNode> _opsConverterField, CustList<RendDynOperationNode> _opsConverterFieldValue,
                      StringMap<CustList<RendDynOperationNode>> _execAttributesText, StringMap<CustList<RendDynOperationNode>> _execAttributes,
                      String _idClass, String _idName, Element _elt, boolean _multiple,
                      String _className, boolean _arrayConverter) {
        this.opsRead = _opsRead;
        this.opsValue = _opsValue;
        this.opsMap = _opsMap;
        this.opsDefault = _opsDefault;
        this.opsConverter = _opsConverter;
        this.opsConverterField = _opsConverterField;
        this.opsConverterFieldValue = _opsConverterFieldValue;
        this.execAttributesText = _execAttributesText;
        this.execAttributes = _execAttributes;
        this.idClass = _idClass;
        this.idName = _idName;
        this.elt = _elt;
        this.multiple = _multiple;
        this.className = _className;
        this.arrayConverter = _arrayConverter;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        Argument value_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(opsValue, _ctx, _rendStack).lastValue().getArgument());
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        Argument map_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(opsMap, _ctx, _rendStack).lastValue().getArgument());
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
                    doc_, docElementSelect_,
                    value_.getStruct(), _stds, _ctx, _rendStack);
        } else {
            processOptionsMapEnum(_cont, map_.getStruct(),
                    doc_, docElementSelect_, _stds, _ctx, _rendStack);
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
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        for (EntryCust<String, CustList<RendDynOperationNode>> e: execAttributes.entryList()) {
            IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(e.getValue(), _ctx, _rendStack);
            String txt_ = RendInput.idRad(args_,_ctx,_rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            docElementSelect_.setAttribute(e.getKey(),txt_);
        }
        DefFieldUpdates f_ = new DefFieldUpdates();
        f_.setIdClass(idClass);
        f_.setIdName(idName);
        f_.setOpsRead(opsRead);
        f_.setOpsConverter(opsConverter);
        f_.setArrayConverter(arrayConverter);
        f_.setClassName(className);
        prStack(_cont,docElementSelect_,f_,def_,_rendStack.getLastPage().getGlobalArgument(),_rendStack);
        simpleAppendChild(doc_, rw_, docElementSelect_);
        processBlock(_cont, _stds, _ctx, _rendStack);
    }

    private void processOptionsMapEnum(Configuration _conf, Struct _extractedMap,
                                       Document _docSelect, Element _docElementSelect, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        Argument argDef_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(opsDefault, _ctx, _rendStackCall).lastValue().getArgument());
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        processOptionsMapEnumName(_conf,_extractedMap,_docSelect,_docElementSelect,argDef_.getStruct(), _advStandards, _ctx, _rendStackCall);
    }

    private void processOptionsMapEnumName(Configuration _conf, Struct _extractedMap,
                                           Document _docSelect, Element _docElementSelect, Struct _returnedVarValue, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        CustList<Struct> obj_ = values(_returnedVarValue, _advStandards, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        Argument arg_ = iteratorMultTable(_extractedMap, _advStandards, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        Struct l_;
        l_ = arg_.getStruct();
        processOptions(_conf, _docSelect, _docElementSelect, obj_, l_, _advStandards, _ctx, _rendStackCall);
    }

    private void processOptions(Configuration _conf, Document _docSelect, Element _docElementSelect, CustList<Struct> _obj, Struct _l, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        while (true) {
            Argument hasNext_ = hasNextPair(_l, _advStandards, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                return;
            }
            if (BooleanStruct.isFalse(hasNext_.getStruct())) {
                break;
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
            Element option_ = _docSelect.createElement(_conf.getRendKeyWords().getKeyWordOption());
            String value_ = processOptionValue(o_, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                return;
            }
            option_.setAttribute(_conf.getRendKeyWords().getAttrValue(),value_);
            for (Struct n: _obj) {
                if (n.sameReference(o_)) {
                    option_.setAttribute(_conf.getRendKeyWords().getAttrSelected(), _conf.getRendKeyWords().getAttrSelected());
                    break;
                }
            }
            Argument second_ = second(entry_, _advStandards, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                return;
            }
            String txt_ = processOptionText(second_, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                return;
            }
            option_.appendChild(_docSelect.createTextNode(txt_));
            _docElementSelect.appendChild(option_);
        }
    }
    private String processOptionValue(Struct _arg, ContextEl _ctx, RendStackCall _rendStackCall) {
        if (opsConverterField.isEmpty()) {
            return getStringKey(_arg, _ctx, _rendStackCall);
        }
        LocalVariable locVar_ = LocalVariable.newLocalVariable(_arg, _ctx.getStandards().getContent().getCoreNames().getAliasObject());
        _rendStackCall.getLastPage().putValueVar("0", new VariableWrapper(locVar_));
        Argument arg_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(opsConverterField, _ctx, _rendStackCall).lastValue().getArgument());
        _rendStackCall.getLastPage().removeRefVar("0");
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return EMPTY_STRING;
        }
        return BeanCustLgNames.processStr(arg_, _ctx, _rendStackCall);
    }
    private String processOptionText(Argument _arg, ContextEl _ctx, RendStackCall _rendStackCall) {
        if (opsConverterFieldValue.isEmpty()) {
            return BeanCustLgNames.processStr(_arg, _ctx, _rendStackCall);
        }
        LocalVariable locVar_ = LocalVariable.newLocalVariable(_arg.getStruct(), _ctx.getStandards().getContent().getCoreNames().getAliasObject());
        _rendStackCall.getLastPage().putValueVar("0", new VariableWrapper(locVar_));
        Argument arg_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(opsConverterFieldValue, _ctx, _rendStackCall).lastValue().getArgument());
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
        DefFetchedObjs def_ = fetchName(_cont, _read, _ctx, _rendStackCall, "", opsRead);
        look(_cont,_write,def_,_rendStackCall);
        fetchValue(_cont,_read,_write,opsValue, opsConverterField, _ctx, _rendStackCall);
        return def_;
    }
}
