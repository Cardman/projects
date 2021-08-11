package code.bean.nat.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class NatRendSubmit extends NatRendElement {

    private final StringMap<String> preformatted;

    public NatRendSubmit(Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText, StringMap<String> _preformatted) {
        super(_read, _execAttributes, _execAttributesText);
        this.preformatted = _preformatted;
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
//        ImportingPage ip_ = _cont.getLastPage();
//        ip_.setProcessingAttribute(ATTRIBUTE_VALUE_SUBMIT);
//        ip_.setOffset(var_.length()+1);
//        ip_.setLookForAttrValue(true);
        curWr_.removeAttribute(_cont.getRendKeyWords().getAttrMessage());
        curWr_.removeAttribute(_cont.getRendKeyWords().getAttrEscapedAmp());
        StringList objects_ = new StringList();
        curWr_.setAttribute(_cont.getRendKeyWords().getAttrValue(), StringUtil.simpleStringsFormat(preformatted.getVal(_cont.getCurrentLanguage()), objects_));
        curWr_.setAttribute(_cont.getRendKeyWords().getAttrType(), _cont.getRendKeyWords().getValueSubmit());
        ownerDocument_.renameNode(curWr_, _cont.getRendKeyWords().getKeyWordInput());
    }
}
