package code.bean.nat.exec.blocks;

import code.bean.nat.BeanNatLgNames;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.exec.blocks.RendSelect;
import code.formathtml.exec.blocks.RendWithEl;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.*;
import code.sml.*;
import code.util.*;

public final class NatRendSelect extends RendParentBlock implements RendWithEl {
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
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        Argument value_ = RenderExpUtil.calculateReuse(opsValue, _stds, _ctx, _rendStack);
        Argument map_ = RenderExpUtil.calculateReuse(opsMap, _stds, _ctx, _rendStack);
        RendReadWrite rw_ = _rendStack.getLastPage().getRendReadWrite();
        Document doc_ = rw_.getDocument();
        Element docElementSelect_ = appendChild(doc_,rw_,_cont.getRendKeyWords().getKeyWordSelect());
        String name_ = elt.getAttribute(_cont.getRendKeyWords().getAttrName());
        processOptionsMapEnumName(_cont, map_.getStruct(),
                doc_, docElementSelect_,
                value_.getStruct(), _stds);
        docElementSelect_.setAttribute(_cont.getRendKeyWords().getAttrName(), name_);
        processIndexes(_cont,elt,docElementSelect_, _stds, _ctx, _rendStack);
        RendSelect.possibleSelect(_cont,_rendStack,docElementSelect_);
        RendBlockHelp.processBlock(_ctx, _rendStack, this);
    }

    private static void processOptionsMapEnumName(Configuration _conf, Struct _extractedMap,
                                                  Document _docSelect, Element _docElementSelect, Struct _returnedVarValue, BeanLgNames _advStandards) {
        CustList<Struct> obj_ = values(_returnedVarValue);
        Argument arg_ = RendBlockHelp.iteratorMultTable(_extractedMap);
        Struct l_;
        l_ = arg_.getStruct();
        processOptions(_conf, _docSelect, _docElementSelect, obj_, l_, _advStandards);
    }

    private static void processOptions(Configuration _conf, Document _docSelect, Element _docElementSelect, CustList<Struct> _obj, Struct _l, BeanLgNames _advStandards) {
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
            String value_ = RendBlockHelp.getStringKey(o_, _advStandards);
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
    private void processIndexes(Configuration _cont, Element _read, Element _write, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        RendBlockHelp.fetchName(_cont, _read, _write, fieldUpdates, _advStandards, _ctx, _rendStackCall);
        RendBlockHelp.fetchValue(_cont,_read,_write,opsValue, _advStandards, _ctx, _rendStackCall);
    }
}
