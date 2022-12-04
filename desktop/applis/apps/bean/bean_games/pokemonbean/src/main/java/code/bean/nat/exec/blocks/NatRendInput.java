package code.bean.nat.exec.blocks;

import code.bean.nat.*;
import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.NatFieldUpdates;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.bean.nat.*;
import code.sml.Element;
import code.sml.NavigationCore;
import code.sml.Node;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class NatRendInput extends NatRendElementForm {
    private final CustList<NatExecOperationNode> opsValue;
    private final NatFieldUpdates fieldUpdates;

    public NatRendInput(Element _read, StringMap<NatExecTextPart> _execAttributes, StringMap<NatExecTextPart> _execAttributesText,
                        CustList<NatExecOperationNode> _opsRead, CustList<NatExecOperationNode> _opsValue, NatCaller _opsWrite,
                        NatFieldUpdates _init) {
        super(_read, _execAttributes, _execAttributesText);
        fieldUpdates = _init;
        fieldUpdates.setOpsRead(_opsRead);
        fieldUpdates.setOpsWrite(_opsWrite);
        this.opsValue = _opsValue;
    }

    void input(NatConfigurationCore _cont, Node _nextWrite, Element _read, NatRendStackCall _rendStack) {
        Element elt_ = (Element) _nextWrite;
        NaSt arg_ = processIndexes(_cont, _read, elt_, _rendStack);
        if (StringUtil.quickEq(_read.getAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrType()), _cont.getRendKeyWords().getKeyWordsValues().getValueRadio())) {
            String strObj_ = NatRendElementForm.getStringKey(arg_);
            NavigationCore.procDefValue(elt_,strObj_, _cont.getRendKeyWords());
        }
    }

    NaSt processIndexes(NatConfigurationCore _cont, Element _read, Element _write, NatRendStackCall _rendStackCall) {
        NaSt arg_ = NatRendElementForm.fetchName(_cont, _read, _write, fieldUpdates, _rendStackCall);
        NatRendElementForm.fetchValue(_cont,_read,_write,opsValue, _rendStackCall);
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getKeyWordsAttrs().getAttrConvertValue()));
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getKeyWordsAttrs().getAttrConvertField()));
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getKeyWordsAttrs().getAttrConvertFieldValue()));
        return arg_;
    }

}
