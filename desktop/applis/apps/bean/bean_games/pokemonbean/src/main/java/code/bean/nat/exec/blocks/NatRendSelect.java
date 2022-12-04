package code.bean.nat.exec.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatCaller;
import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.NatFieldUpdates;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.sml.Document;
import code.sml.Element;
import code.sml.NavigationCore;
import code.sml.RendReadWrite;
import code.util.CustList;
import code.util.IdList;

public final class NatRendSelect extends NatParentBlock {
    private final CustList<NatExecOperationNode> opsValue;
    private final CustList<NatExecOperationNode> opsMap;
    private final Element elt;
    private final NatFieldUpdates fieldUpdates;

    public NatRendSelect(CustList<NatExecOperationNode> _opsRead, CustList<NatExecOperationNode> _opsValue, NatCaller _opsWrite,
                         CustList<NatExecOperationNode> _opsMap,
                         Element _elt,
                         NatFieldUpdates _init) {
        fieldUpdates = _init;
        fieldUpdates.setOpsRead(_opsRead);
        fieldUpdates.setOpsWrite(_opsWrite);
        this.opsValue = _opsValue;
        this.opsMap = _opsMap;
        this.elt = _elt;
    }

    @Override
    public void processEl(NatConfigurationCore _cont, NatRendStackCall _rendStack) {
        Struct value_ = BeanNatCommonLgNames.getAllArgs(opsValue, _rendStack).lastValue().getArgument();
        Struct map_ = BeanNatCommonLgNames.getAllArgs(opsMap, _rendStack).lastValue().getArgument();
        RendReadWrite rw_ = _rendStack.getLastPage().getRendReadWrite();
        Document doc_ = rw_.getDocument();
        Element docElementSelect_ = appendChild(doc_,rw_,_cont.getRendKeyWords().getKeyWordsTags().getKeyWordSelect());
        String name_ = elt.getAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrName());
        processOptionsMapEnumName(_cont, map_,
                doc_, docElementSelect_,
                value_);
        docElementSelect_.setAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrName(), name_);
        processIndexes(_cont,elt,docElementSelect_, _rendStack);
        RendBlockHelp.processBlock(_rendStack, this);
    }

    public static Element appendChild(Document _doc, RendReadWrite _rw, String _read) {
        Element currentNode_ = _doc.createElement(_read);
        NavigationCore.simpleAppendChild(_doc, _rw.getWrite(), currentNode_);
        return currentNode_;
    }
    private static void processOptionsMapEnumName(NatConfigurationCore _conf, Struct _extractedMap,
                                                  Document _docSelect, Element _docElementSelect, Struct _returnedVarValue) {
        CustList<Struct> obj_ = values(_returnedVarValue);
        Struct l_ = RendBlockHelp.iterator(_extractedMap);
        processOptions(_conf, _docSelect, _docElementSelect, obj_, l_);
    }

    private static void processOptions(NatConfigurationCore _conf, Document _docSelect, Element _docElementSelect, CustList<Struct> _obj, Struct _l) {
        while (true) {
            Struct hasNext_ = RendBlockHelp.nasNextCom(_l);
            if (BooleanStruct.isFalse(hasNext_)) {
                break;
            }
            Struct entry_ = RendBlockHelp.nextCom(_l);
            Struct o_ = RendBlockHelp.first(entry_);
            Element option_ = _docSelect.createElement(_conf.getRendKeyWords().getKeyWordsTags().getKeyWordOption());
            String value_ = NatRendElementForm.getStringKey(o_);
            option_.setAttribute(_conf.getRendKeyWords().getKeyWordsAttrs().getAttrValue(),value_);
            for (Struct n: _obj) {
                if (n.sameReference(o_)) {
                    option_.setAttribute(_conf.getRendKeyWords().getKeyWordsAttrs().getAttrSelected(), _conf.getRendKeyWords().getKeyWordsAttrs().getAttrSelected());
                    break;
                }
            }
            Struct second_ = RendBlockHelp.second(entry_);
            String txt_ = BeanNatCommonLgNames.processString(second_);
            option_.appendChild(_docSelect.createTextNode(txt_));
            _docElementSelect.appendChild(option_);
        }
    }

    private static CustList<Struct> values(Struct _returnedVarValue) {
        IdList<Struct> obj_ = new IdList<Struct>();
        obj_.add(_returnedVarValue);
        return obj_;
    }
    private void processIndexes(NatConfigurationCore _cont, Element _read, Element _write, NatRendStackCall _rendStackCall) {
        NatRendElementForm.fetchName(_cont, _read, _write, fieldUpdates, _rendStackCall);
        NatRendElementForm.fetchValue(_cont,_read,_write,opsValue, _rendStackCall);
    }
}
