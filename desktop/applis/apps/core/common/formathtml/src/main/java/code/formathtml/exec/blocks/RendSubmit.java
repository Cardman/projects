package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.util.*;
import code.util.core.StringUtil;

public final class RendSubmit extends RendElement {

    private final StringMap<CustList<RendDynOperationNode>> opExp;

    private final StringMap<String> preformatted;

    public RendSubmit(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText, StringMap<CustList<RendDynOperationNode>> _opExp, StringMap<String> _preformatted) {
        super(_read, _execAttributes, _execAttributesText);
        this.opExp = _opExp;
        this.preformatted = _preformatted;
    }

    @Override
    protected Struct processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
//        ImportingPage ip_ = _cont.getLastPage();
//        ip_.setProcessingAttribute(ATTRIBUTE_VALUE_SUBMIT);
//        ip_.setOffset(var_.length()+1);
//        ip_.setLookForAttrValue(true);
        curWr_.removeAttribute(_cont.getRendKeyWords().getAttrMessage());
        curWr_.removeAttribute(_cont.getRendKeyWords().getAttrEscapedAmp());
        StringList objects_ = new StringList();
        for (EntryCust<String,CustList<RendDynOperationNode>> e:opExp.entryList()) {
            String txt_ = RendInput.idRad(e.getValue(),_ctx,_rendStack);
            if (txt_ == null) {
                return null;
            }
            objects_.add(txt_);
            curWr_.removeAttribute(e.getKey());
        }
        curWr_.setAttribute(_cont.getRendKeyWords().getAttrValue(), StringUtil.simpleStringsFormat(preformatted.getVal(_cont.getCurrentLanguage()), objects_));
        curWr_.setAttribute(_cont.getRendKeyWords().getAttrType(), _cont.getRendKeyWords().getValueSubmit());
        ownerDocument_.renameNode(curWr_, _cont.getRendKeyWords().getKeyWordInput());
        return NullStruct.NULL_VALUE;
    }
}
