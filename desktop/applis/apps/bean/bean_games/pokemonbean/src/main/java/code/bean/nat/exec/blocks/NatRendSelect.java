package code.bean.nat.exec.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.*;
import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.NatFieldUpdates;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.bean.nat.*;
import code.bean.nat.*;
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
        NaSt value_ = BeanNatCommonLgNames.getAllArgs(opsValue, _rendStack).lastValue().getArgument();
        NaSt map_ = BeanNatCommonLgNames.getAllArgs(opsMap, _rendStack).lastValue().getArgument();
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
    private static void processOptionsMapEnumName(NatConfigurationCore _conf, NaSt _extractedMap,
                                                  Document _docSelect, Element _docElementSelect, NaSt _returnedVarValue) {
        CustList<NaSt> obj_ = values(_returnedVarValue);
        NaSt l_ = RendBlockHelp.iterator(_extractedMap);
        processOptions(_conf, _docSelect, _docElementSelect, obj_, l_);
    }

    private static void processOptions(NatConfigurationCore _conf, Document _docSelect, Element _docElementSelect, CustList<NaSt> _obj, NaSt _l) {
        while (true) {
            NaSt hasNext_ = RendBlockHelp.nasNextCom(_l);
            if (NaBoSt.isFalse(hasNext_)) {
                break;
            }
            NaSt entry_ = RendBlockHelp.nextCom(_l);
            NaSt o_ = RendBlockHelp.first(entry_);
            Element option_ = _docSelect.createElement(_conf.getRendKeyWords().getKeyWordsTags().getKeyWordOption());
            String value_ = NatRendElementForm.getStringKey(o_);
            option_.setAttribute(_conf.getRendKeyWords().getKeyWordsAttrs().getAttrValue(),value_);
            for (NaSt n: _obj) {
                if (n.sameReference(o_)) {
                    option_.setAttribute(_conf.getRendKeyWords().getKeyWordsAttrs().getAttrSelected(), _conf.getRendKeyWords().getKeyWordsAttrs().getAttrSelected());
                    break;
                }
            }
            NaSt second_ = RendBlockHelp.second(entry_);
            String txt_ = BeanNatCommonLgNames.processString(second_);
            option_.appendChild(_docSelect.createTextNode(txt_));
            _docElementSelect.appendChild(option_);
        }
    }

    private static CustList<NaSt> values(NaSt _returnedVarValue) {
        IdList<NaSt> obj_ = new IdList<NaSt>();
        obj_.add(_returnedVarValue);
        return obj_;
    }
    private void processIndexes(NatConfigurationCore _cont, Element _read, Element _write, NatRendStackCall _rendStackCall) {
        NatRendElementForm.fetchName(_cont, _read, _write, fieldUpdates, _rendStackCall);
        NatRendElementForm.fetchValue(_cont,_read,_write,opsValue, _rendStackCall);
    }
}
