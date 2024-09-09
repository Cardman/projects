package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
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
    protected Struct processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        Element elt_ = (Element) _nextWrite;
        DefFetchedObjs arg_ = processIndexes(_cont, _read, elt_, _ctx, _rendStack, idRadio);
        Struct res_ = arg_.getArg();
        if (res_ == null) {
            return null;
        }
        if (!opsConverterFieldValue.isEmpty()) {
            LocalVariable locVar_ = LocalVariable.newLocalVariable(res_, _ctx.getStandards().getContent().getCoreNames().getAliasObject());
            _rendStack.getLastPage().putValueVar("0", new VariableWrapper(locVar_));
            Struct argConv_ = RenderExpUtil.getFinalArg(opsConverterFieldValue, _ctx, _rendStack);
            _rendStack.getLastPage().removeRefVar("0");
            if (argConv_ == null) {
                return null;
            }
            res_ = argConv_;
        }
        if (res_ == NullStruct.NULL_VALUE) {
            elt_.removeAttribute(_cont.getRendKeyWords().getAttrChecked());
        } else {
            Struct strObj_ = RendDynOperationNode.processString(res_, _ctx, _rendStack);
            if (strObj_ == null) {
                return null;
            }
            NavigationCore.procDefValue(elt_, NumParsers.getString(strObj_).getInstance(), _cont.getRendKeyWords().group());
        }
        prStack(_cont,elt_,arg_,_rendStack.getLastPage().getGlobalStruct(),_rendStack);
        return NullStruct.NULL_VALUE;
    }

}
