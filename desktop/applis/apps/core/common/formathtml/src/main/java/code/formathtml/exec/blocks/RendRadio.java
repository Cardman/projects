package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DefFieldUpdates;
import code.sml.Element;
import code.sml.NavigationCore;
import code.sml.Node;
import code.util.CustList;
import code.util.StringMap;

public final class RendRadio extends RendInput {
    private final CustList<RendDynOperationNode> idRadio;
    private final CustList<RendDynOperationNode> opsConverterFieldValue;

    public RendRadio(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText,
                     CustList<RendDynOperationNode> _opsValue,
                     CustList<RendDynOperationNode> _opsConverterField,
                     CustList<RendDynOperationNode> _opsConverterFieldValue, DefFieldUpdates _f) {
        super(_read, _execAttributes, _execAttributesText, _opsValue, _opsConverterField, _f);
        this.opsConverterFieldValue = _opsConverterFieldValue;
        idRadio = _f.getIdRadio();
    }


    @Override
    protected boolean processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        Element elt_ = (Element) _nextWrite;
        DefFetchedObjs arg_ = processIndexes(_cont, _read, elt_, _ctx, _rendStack, idRadio);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return true;
        }
        Struct res_ = arg_.getArg();
        if (!opsConverterFieldValue.isEmpty()) {
            LocalVariable locVar_ = LocalVariable.newLocalVariable(arg_.getArg(), _ctx.getStandards().getContent().getCoreNames().getAliasObject());
            _rendStack.getLastPage().putValueVar("0", new VariableWrapper(locVar_));
            Argument argConv_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(opsConverterFieldValue, _ctx, _rendStack).lastValue().getArgument());
            _rendStack.getLastPage().removeRefVar("0");
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return true;
            }
            res_ = argConv_.getStruct();
        }
        if (res_ == NullStruct.NULL_VALUE) {
            elt_.removeAttribute(_cont.getRendKeyWords().getAttrChecked());
        } else {
            String strObj_ = getStringKey(res_, _ctx, _rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return true;
            }
            NavigationCore.procDefValue(elt_, strObj_, _cont.getRendKeyWords().group());
        }
        prStack(_cont,elt_,arg_,_rendStack.getLastPage().getGlobalArgument(),_rendStack);
        return _ctx.callsOrException(_rendStack.getStackCall());
    }

}
