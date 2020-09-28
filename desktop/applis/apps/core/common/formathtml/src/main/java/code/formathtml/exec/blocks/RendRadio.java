package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.Configuration;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class RendRadio extends RendInput {
    private CustList<RendDynOperationNode> opsConverterFieldValue = new CustList<RendDynOperationNode>();
    private String varNameConverterFieldValue = EMPTY_STRING;

    public RendRadio(int _offsetTrim, Element read, StringMap<ExecTextPart> execAttributes, StringMap<ExecTextPart> execAttributesText, CustList<RendDynOperationNode> opsRead, CustList<RendDynOperationNode> opsValue, CustList<RendDynOperationNode> opsWrite, CustList<RendDynOperationNode> opsConverter, CustList<RendDynOperationNode> opsConverterField, String varName, String varNameConverter, String varNameConverterField, String id, String idClass, String idName, String className, CustList<RendDynOperationNode> opsConverterFieldValue, String varNameConverterFieldValue) {
        super(_offsetTrim, read, execAttributes, execAttributesText, opsRead, opsValue, opsWrite, opsConverter, opsConverterField, varName, varNameConverter, varNameConverterField, id, idClass, idName, className);
        this.opsConverterFieldValue = opsConverterFieldValue;
        this.varNameConverterFieldValue = varNameConverterFieldValue;
    }


    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        Element elt_ = (Element) _nextWrite;
        Argument arg_ = processIndexes(_cont, _read, elt_);
        ContextEl context_ = _cont.getContext();
        if (context_.hasException()) {
            return;
        }
        Struct res_ = arg_.getStruct();
        if (!opsConverterFieldValue.isEmpty()) {
            LocalVariable locVar_ = LocalVariable.newLocalVariable(arg_.getStruct(), context_.getStandards().getAliasObject());
            _cont.getLastPage().putLocalVar(varNameConverterFieldValue, locVar_);
            Argument argConv_ = RenderExpUtil.calculateReuse(opsConverterFieldValue, _cont);
            _cont.getLastPage().removeLocalVar(varNameConverterFieldValue);
            if (context_.hasException()) {
                return;
            }
            res_ = argConv_.getStruct();
        }
        if (res_ == NullStruct.NULL_VALUE) {
            elt_.removeAttribute(_cont.getRendKeyWords().getAttrChecked());
        } else {
            String strObj_ = getStringKey(_cont, res_);
            if (context_.hasException()) {
                return;
            }
            if (StringList.quickEq(elt_.getAttribute(_cont.getRendKeyWords().getAttrValue()),strObj_)) {
                elt_.setAttribute(_cont.getRendKeyWords().getAttrChecked(), _cont.getRendKeyWords().getAttrChecked());
            } else {
                elt_.removeAttribute(_cont.getRendKeyWords().getAttrChecked());
            }
        }
    }
}
