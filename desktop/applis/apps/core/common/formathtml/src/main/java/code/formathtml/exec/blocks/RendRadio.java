package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.Configuration;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.InputInfo;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RendRadio extends RendInput {
    private CustList<RendDynOperationNode> opsConverterFieldValue = new CustList<RendDynOperationNode>();
    private String varNameConverterFieldValue = EMPTY_STRING;

    public RendRadio(int _offsetTrim, Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText,
                     CustList<RendDynOperationNode> _opsRead, CustList<RendDynOperationNode> _opsValue, CustList<RendDynOperationNode> _opsWrite,
                     CustList<RendDynOperationNode> _opsConverter, CustList<RendDynOperationNode> _opsConverterField,
                     String _varName, String _varNameConverter, String _varNameConverterField, String _id, String _idClass, String _idName, String _className,
                     CustList<RendDynOperationNode> _opsConverterFieldValue, String _varNameConverterFieldValue, InputInfo _list) {
        super(_offsetTrim, _read, _execAttributes, _execAttributesText, _opsRead, _opsValue, _opsWrite, _opsConverter, _opsConverterField, _varName, _varNameConverter, _varNameConverterField, _id, _idClass, _idName, _className, _list);
        this.opsConverterFieldValue = _opsConverterFieldValue;
        this.varNameConverterFieldValue = _varNameConverterFieldValue;
    }


    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx) {
        Element elt_ = (Element) _nextWrite;
        Argument arg_ = processIndexes(_cont, _read, elt_, _stds, _ctx);
        if (_ctx.callsOrException()) {
            return;
        }
        Struct res_ = arg_.getStruct();
        if (!opsConverterFieldValue.isEmpty()) {
            LocalVariable locVar_ = LocalVariable.newLocalVariable(arg_.getStruct(), _ctx.getStandards().getContent().getCoreNames().getAliasObject());
            _cont.getLastPage().putValueVar(varNameConverterFieldValue, locVar_);
            Argument argConv_ = RenderExpUtil.calculateReuse(opsConverterFieldValue, _cont, _stds, _ctx);
            _cont.getLastPage().removeRefVar(varNameConverterFieldValue);
            if (_ctx.callsOrException()) {
                return;
            }
            res_ = argConv_.getStruct();
        }
        if (res_ == NullStruct.NULL_VALUE) {
            elt_.removeAttribute(_cont.getRendKeyWords().getAttrChecked());
        } else {
            String strObj_ = getStringKey(res_, _stds, _ctx);
            if (_ctx.callsOrException()) {
                return;
            }
            if (StringUtil.quickEq(elt_.getAttribute(_cont.getRendKeyWords().getAttrValue()),strObj_)) {
                elt_.setAttribute(_cont.getRendKeyWords().getAttrChecked(), _cont.getRendKeyWords().getAttrChecked());
            } else {
                elt_.removeAttribute(_cont.getRendKeyWords().getAttrChecked());
            }
        }
    }
}
