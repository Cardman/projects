package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.sml.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class RendLink extends RendElement {
    private String content;
    private StringMap<ExecTextPart> execOpExpTitle;

    public RendLink(int _offsetTrim, Element read, StringMap<ExecTextPart> execAttributes, StringMap<ExecTextPart> execAttributesText, String content, StringMap<ExecTextPart> execOpExpTitle) {
        super(_offsetTrim, read, execAttributes, execAttributesText);
        this.content = content;
        this.execOpExpTitle = execOpExpTitle;
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx) {
        String fileContent_ = content;
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
        if (!execOpExpTitle.isEmpty()) {
            StringList objects_ = new StringList();
            for (EntryCust<String, ExecTextPart> e:execOpExpTitle.entryList()) {
                ExecTextPart r_ = e.getValue();
                objects_.add(RenderingText.render(r_,_cont, _stds, _ctx));
                if (_ctx.callsOrException()) {
                    return;
                }
                curWr_.removeAttribute(e.getKey());
            }
            fileContent_ = StringList.simpleStringsFormat(fileContent_, objects_);
        }
        ElementList heads_ = ownerDocument_.getElementsByTagName(_cont.getRendKeyWords().getKeyWordHead());
        if (fileContent_ != null && heads_.getLength() == CustList.ONE_ELEMENT) {
            Element head_ = heads_.item(CustList.FIRST_INDEX);
            CustList<Element> children_ = new CustList<Element>();
            for (Element c: head_.getChildElements()) {
                if (!StringList.quickEq(c.getTagName(), _cont.getRendKeyWords().getKeyWordStyle())) {
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
