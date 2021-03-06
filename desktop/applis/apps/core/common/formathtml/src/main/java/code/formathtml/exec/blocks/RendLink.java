package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.sml.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class RendLink extends RendElement {
    private final String content;
    private final StringMap<ExecTextPart> execOpExpTitle;

    public RendLink(int _offsetTrim, Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText, String _content, StringMap<ExecTextPart> _execOpExpTitle) {
        super(_offsetTrim, _read, _execAttributes, _execAttributesText);
        this.content = _content;
        this.execOpExpTitle = _execOpExpTitle;
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        String fileContent_ = content;
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
        if (!execOpExpTitle.isEmpty()) {
            StringList objects_ = new StringList();
            for (EntryCust<String, ExecTextPart> e:execOpExpTitle.entryList()) {
                ExecTextPart r_ = e.getValue();
                objects_.add(RenderingText.render(r_,_cont, _stds, _ctx, _stack, _rendStack));
                if (_ctx.callsOrException(_stack)) {
                    return;
                }
                curWr_.removeAttribute(e.getKey());
            }
            fileContent_ = StringUtil.simpleStringsFormat(fileContent_, objects_);
        }
        ElementList heads_ = ownerDocument_.getElementsByTagName(_cont.getRendKeyWords().getKeyWordHead());
        if (fileContent_ != null && heads_.getLength() == IndexConstants.ONE_ELEMENT) {
            Element head_ = heads_.item(IndexConstants.FIRST_INDEX);
            CustList<Element> children_ = new CustList<Element>();
            for (Element c: head_.getChildElements()) {
                if (!StringUtil.quickEq(c.getTagName(), _cont.getRendKeyWords().getKeyWordStyle())) {
                    continue;
                }
                children_.add(c);
            }
            boolean successAdd_ = children_.isEmpty();
            if (!successAdd_) {
                Element eltStyle_ = children_.last();
                appendText(fileContent_, ownerDocument_, eltStyle_);
            } else {
                Element eltStyle_ = ownerDocument_.createElement(_cont.getRendKeyWords().getKeyWordStyle());
                Text text_ = ownerDocument_.createTextNode(fileContent_);
                eltStyle_.appendChild(text_);
                head_.appendChild(eltStyle_);
            }
        }
    }

}
