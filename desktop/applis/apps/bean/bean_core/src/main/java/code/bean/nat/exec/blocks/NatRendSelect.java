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
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsValue = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsWrite = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsMap = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsConverter = new CustList<RendDynOperationNode>();
    private String varName = RendBlockHelp.EMPTY_STRING;
    private InputInfo varNames = new InputInfo();
    private String id = RendBlockHelp.EMPTY_STRING;
    private String idClass = RendBlockHelp.EMPTY_STRING;
    private String idName = RendBlockHelp.EMPTY_STRING;
    private final Element elt;
    private String varNameConverter = RendBlockHelp.EMPTY_STRING;
    private String className = RendBlockHelp.EMPTY_STRING;
    private final boolean arrayConverter;

    public NatRendSelect(CustList<RendDynOperationNode> _opsRead, CustList<RendDynOperationNode> _opsValue, CustList<RendDynOperationNode> _opsWrite,
                         CustList<RendDynOperationNode> _opsMap,
                         String _varName, String _id, String _idClass, String _idName, Element _elt,
                         String _varNameConverter,
                         String _className, boolean _arrayConverter, InputInfo _list) {
        this.opsRead = _opsRead;
        this.opsValue = _opsValue;
        this.opsWrite = _opsWrite;
        this.opsMap = _opsMap;
        this.opsConverter = new CustList<RendDynOperationNode>();
        this.varName = _varName;
        this.id = _id;
        this.idClass = _idClass;
        this.idName = _idName;
        this.elt = _elt;
        this.varNameConverter = _varNameConverter;
        this.className = _className;
        this.arrayConverter = _arrayConverter;
        varNames = _list;
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
                value_.getStruct(), _stds, _ctx);
        docElementSelect_.setAttribute(_cont.getRendKeyWords().getAttrName(), name_);
        processIndexes(_cont,elt,docElementSelect_, _stds, _ctx, _rendStack);
        RendSelect.possibleSelect(_cont,_rendStack,docElementSelect_);
        RendBlockHelp.processBlock(_ctx, _rendStack, this);
    }

    private static void processOptionsMapEnumName(Configuration _conf, Struct _extractedMap,
                                           Document _docSelect, Element _docElementSelect, Struct _returnedVarValue, BeanLgNames _advStandards, ContextEl _ctx) {
        CustList<Struct> obj_ = values(_returnedVarValue);
        Argument arg_ = RendBlockHelp.iteratorMultTable(_extractedMap, _ctx);
        Struct l_;
        l_ = arg_.getStruct();
        processOptions(_conf, _docSelect, _docElementSelect, obj_, l_, _advStandards, _ctx);
    }

    private static void processOptions(Configuration _conf, Document _docSelect, Element _docElementSelect, CustList<Struct> _obj, Struct _l, BeanLgNames _advStandards, ContextEl _ctx) {
        while (true) {
            Argument hasNext_ = RendBlockHelp.hasNextPair(_l, _ctx);
            if (BooleanStruct.isFalse(hasNext_.getStruct())) {
                break;
            }
            Argument nextPair_ = RendBlockHelp.nextPair(_l, _ctx);
            Struct entry_ = nextPair_.getStruct();
            Argument first_ = RendBlockHelp.first(entry_, _ctx);
            Struct o_ = first_.getStruct();
            Element option_ = _docSelect.createElement(_conf.getRendKeyWords().getKeyWordOption());
            String value_ = RendBlockHelp.getStringKey(o_, _advStandards, _ctx);
            option_.setAttribute(_conf.getRendKeyWords().getAttrValue(),value_);
            for (Struct n: _obj) {
                if (n.sameReference(o_)) {
                    option_.setAttribute(_conf.getRendKeyWords().getAttrSelected(), _conf.getRendKeyWords().getAttrSelected());
                    break;
                }
            }
            Argument second_ = RendBlockHelp.second(entry_, _ctx);
            String txt_ = BeanNatLgNames.processString(second_, _ctx);
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
        FieldUpdates f_ = new FieldUpdates();
        f_.setId(id);
        f_.setIdClass(idClass);
        f_.setIdName(idName);
        f_.setOpsRead(opsRead);
        f_.setOpsWrite(opsWrite);
        f_.setVarName(varName);
        f_.setVarNames(varNames);
        f_.setVarNameConverter(varNameConverter);
        f_.setOpsConverter(opsConverter);
        f_.setArrayConverter(arrayConverter);
        f_.setClassName(className);
        RendBlockHelp.fetchName(_cont, _read, _write, f_, _advStandards, _ctx, _rendStackCall);
        RendBlockHelp.fetchValue(_cont,_read,_write,opsValue, _advStandards, _ctx, _rendStackCall);
    }
}
