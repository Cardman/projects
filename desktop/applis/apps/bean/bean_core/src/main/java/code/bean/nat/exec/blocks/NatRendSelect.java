package code.bean.nat.exec.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.BeanNatLgNames;
import code.bean.nat.exec.NatRendStackCall;
import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.stacks.RendReadWrite;
import code.formathtml.util.FieldUpdates;
import code.sml.Document;
import code.sml.Element;
import code.util.CustList;
import code.util.IdList;

public final class NatRendSelect extends RendParentBlock implements NatRendWithEl {
    private final CustList<RendDynOperationNode> opsValue;
    private final CustList<RendDynOperationNode> opsMap;
    private final Element elt;
    private final FieldUpdates fieldUpdates;

    public NatRendSelect(CustList<RendDynOperationNode> _opsRead, CustList<RendDynOperationNode> _opsValue, CustList<RendDynOperationNode> _opsWrite,
                         CustList<RendDynOperationNode> _opsMap,
                         Element _elt,
                         FieldUpdates _init) {
        fieldUpdates = _init;
        fieldUpdates.setOpsRead(_opsRead);
        fieldUpdates.setOpsWrite(_opsWrite);
        this.opsValue = _opsValue;
        this.opsMap = _opsMap;
        this.elt = _elt;
    }

    @Override
    public void processEl(Configuration _cont, NatRendStackCall _rendStack) {
        Argument value_ = Argument.getNullableValue(BeanNatCommonLgNames.getAllArgs(opsValue, _rendStack).lastValue().getArgument());
        Argument map_ = Argument.getNullableValue(BeanNatCommonLgNames.getAllArgs(opsMap, _rendStack).lastValue().getArgument());
        RendReadWrite rw_ = _rendStack.getLastPage().getRendReadWrite();
        Document doc_ = rw_.getDocument();
        Element docElementSelect_ = appendChild(doc_,rw_,_cont.getRendKeyWords().getKeyWordSelect());
        String name_ = elt.getAttribute(_cont.getRendKeyWords().getAttrName());
        processOptionsMapEnumName(_cont, map_.getStruct(),
                doc_, docElementSelect_,
                value_.getStruct());
        docElementSelect_.setAttribute(_cont.getRendKeyWords().getAttrName(), name_);
        processIndexes(_cont,elt,docElementSelect_, _rendStack);
        RendBlockHelp.processBlock(_rendStack, this);
    }

    private static void processOptionsMapEnumName(Configuration _conf, Struct _extractedMap,
                                                  Document _docSelect, Element _docElementSelect, Struct _returnedVarValue) {
        CustList<Struct> obj_ = values(_returnedVarValue);
        Argument arg_ = RendBlockHelp.iteratorMultTable(_extractedMap);
        Struct l_;
        l_ = arg_.getStruct();
        processOptions(_conf, _docSelect, _docElementSelect, obj_, l_);
    }

    private static void processOptions(Configuration _conf, Document _docSelect, Element _docElementSelect, CustList<Struct> _obj, Struct _l) {
        while (true) {
            Argument hasNext_ = RendBlockHelp.nasNextCom(_l);
            if (BooleanStruct.isFalse(hasNext_.getStruct())) {
                break;
            }
            Argument nextPair_ = RendBlockHelp.nextCom(_l);
            Struct entry_ = nextPair_.getStruct();
            Argument first_ = RendBlockHelp.first(entry_);
            Struct o_ = first_.getStruct();
            Element option_ = _docSelect.createElement(_conf.getRendKeyWords().getKeyWordOption());
            String value_ = RendBlockHelp.getStringKey(o_);
            option_.setAttribute(_conf.getRendKeyWords().getAttrValue(),value_);
            for (Struct n: _obj) {
                if (n.sameReference(o_)) {
                    option_.setAttribute(_conf.getRendKeyWords().getAttrSelected(), _conf.getRendKeyWords().getAttrSelected());
                    break;
                }
            }
            Argument second_ = RendBlockHelp.second(entry_);
            String txt_ = BeanNatLgNames.processString(second_);
            option_.appendChild(_docSelect.createTextNode(txt_));
            _docElementSelect.appendChild(option_);
        }
    }

    private static CustList<Struct> values(Struct _returnedVarValue) {
        IdList<Struct> obj_ = new IdList<Struct>();
        obj_.add(_returnedVarValue);
        return obj_;
    }
    private void processIndexes(Configuration _cont, Element _read, Element _write, NatRendStackCall _rendStackCall) {
        RendBlockHelp.fetchName(_cont, _read, _write, fieldUpdates, _rendStackCall);
        RendBlockHelp.fetchValue(_cont,_read,_write,opsValue, _rendStackCall);
    }
}
