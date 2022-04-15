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
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RendRadio extends RendInput {
    private CustList<RendDynOperationNode> opsConverterFieldValue = new CustList<RendDynOperationNode>();
    private String varNameConverterFieldValue = EMPTY_STRING;

    public RendRadio(Element _read, StringMap<DefExecTextPart> _execAttributes, StringMap<DefExecTextPart> _execAttributesText,
                     CustList<RendDynOperationNode> _opsRead, CustList<RendDynOperationNode> _opsValue,
                     CustList<RendDynOperationNode> _opsConverter, CustList<RendDynOperationNode> _opsConverterField,
                     String _varNameConverter, String _varNameConverterField, String _idClass, String _idName, String _className,
                     CustList<RendDynOperationNode> _opsConverterFieldValue, String _varNameConverterFieldValue) {
        super(_read, _execAttributes, _execAttributesText, _opsRead, _opsValue, _opsConverter, _opsConverterField, _varNameConverter, _varNameConverterField, _idClass, _idName, _className);
        this.opsConverterFieldValue = _opsConverterFieldValue;
        this.varNameConverterFieldValue = _varNameConverterFieldValue;
    }


    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        Element elt_ = (Element) _nextWrite;
        Argument arg_ = processIndexes(_cont, _read, elt_, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        Struct res_ = arg_.getStruct();
        if (!opsConverterFieldValue.isEmpty()) {
            LocalVariable locVar_ = LocalVariable.newLocalVariable(arg_.getStruct(), _ctx.getStandards().getContent().getCoreNames().getAliasObject());
            _rendStack.getLastPage().putValueVar(varNameConverterFieldValue, new VariableWrapper(locVar_));
            Argument argConv_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(opsConverterFieldValue, _ctx, _rendStack).lastValue().getArgument());
            _rendStack.getLastPage().removeRefVar(varNameConverterFieldValue);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            res_ = argConv_.getStruct();
        }
        if (res_ == NullStruct.NULL_VALUE) {
            elt_.removeAttribute(_cont.getRendKeyWords().getAttrChecked());
        } else {
            String strObj_ = getStringKey(res_, _ctx, _rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            procDefValue(_cont, elt_, strObj_);
        }
    }

    public static void procDefValue(Configuration _cont, Element _elt, String _strObj) {
        if (StringUtil.quickEq(_elt.getAttribute(_cont.getRendKeyWords().getAttrValue()), _strObj)) {
            _elt.setAttribute(_cont.getRendKeyWords().getAttrChecked(), _cont.getRendKeyWords().getAttrChecked());
        } else {
            _elt.removeAttribute(_cont.getRendKeyWords().getAttrChecked());
        }
    }
}
