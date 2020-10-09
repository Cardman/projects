package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RendSubmit extends RendElement {

    private StringMap<ExecTextPart> opExp;

    private StringMap<String> preformatted;

    public RendSubmit(int _offsetTrim, Element read, StringMap<ExecTextPart> execAttributes, StringMap<ExecTextPart> execAttributesText, StringMap<ExecTextPart> opExp, StringMap<String> preformatted) {
        super(_offsetTrim, read, execAttributes, execAttributesText);
        this.opExp = opExp;
        this.preformatted = preformatted;
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx) {
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
//        ImportingPage ip_ = _cont.getLastPage();
//        ip_.setProcessingAttribute(ATTRIBUTE_VALUE_SUBMIT);
//        ip_.setOffset(var_.length()+1);
//        ip_.setLookForAttrValue(true);
        curWr_.removeAttribute(_cont.getRendKeyWords().getAttrMessage());
        curWr_.removeAttribute(_cont.getRendKeyWords().getAttrEscapedAmp());
        StringList objects_ = new StringList();
        for (EntryCust<String,ExecTextPart> e:opExp.entryList()) {
            ExecTextPart r_ = e.getValue();
            objects_.add(RenderingText.render(r_,_cont, _stds, _ctx));
            if (_ctx.callsOrException()) {
                return;
            }
            curWr_.removeAttribute(e.getKey());
        }
        curWr_.setAttribute(_cont.getRendKeyWords().getAttrValue(), StringUtil.simpleStringsFormat(preformatted.getVal(_cont.getCurrentLanguage()), objects_));
        curWr_.setAttribute(_cont.getRendKeyWords().getAttrType(), _cont.getRendKeyWords().getValueSubmit());
        ownerDocument_.renameNode(curWr_, _cont.getRendKeyWords().getKeyWordInput());
    }
}
