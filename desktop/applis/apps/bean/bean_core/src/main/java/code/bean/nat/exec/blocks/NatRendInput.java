package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatFieldUpdates;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendRadio;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class NatRendInput extends NatRendElement {
    private final CustList<NatExecOperationNode> opsValue;
    private final NatFieldUpdates fieldUpdates;

    public NatRendInput(Element _read, StringMap<NatExecTextPart> _execAttributes, StringMap<NatExecTextPart> _execAttributesText,
                        CustList<NatExecOperationNode> _opsRead, CustList<NatExecOperationNode> _opsValue, CustList<NatExecOperationNode> _opsWrite,
                        NatFieldUpdates _init) {
        super(_read, _execAttributes, _execAttributesText);
        fieldUpdates = _init;
        fieldUpdates.setOpsRead(_opsRead);
        fieldUpdates.setOpsWrite(_opsWrite);
        this.opsValue = _opsValue;
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, NatRendStackCall _rendStack) {
        Element elt_ = (Element) _nextWrite;
        Argument arg_ = processIndexes(_cont, _read, elt_, _rendStack);
        if (StringUtil.quickEq(_read.getAttribute(_cont.getRendKeyWords().getAttrType()), _cont.getRendKeyWords().getValueRadio())) {
            Struct res_ = arg_.getStruct();
            String strObj_ = RendBlockHelp.getStringKey(res_);
            RendRadio.procDefValue(_cont,elt_,strObj_);
        }
    }

    Argument processIndexes(Configuration _cont, Element _read, Element _write, NatRendStackCall _rendStackCall) {
        Argument arg_ = RendBlockHelp.fetchName(_cont, _read, _write, fieldUpdates, _rendStackCall);
        RendBlockHelp.fetchValue(_cont,_read,_write,opsValue, _rendStackCall);
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertField()));
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertFieldValue()));
        return arg_;
    }

}
