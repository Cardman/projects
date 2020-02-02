package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.stacks.RendIfStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendRemovableVars;
import code.sml.*;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public abstract class RendElement extends RendParentBlock implements RendWithEl, RendReducableOperations, RendBuildableElMethod {
    private Element read;
    private StringMap<ResultText> attributes = new StringMap<ResultText>();
    private StringMap<ResultText> attributesText = new StringMap<ResultText>();
    RendElement(Element _elt, OffsetsBlock _offset) {
        super(_offset);
        read = _elt;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        String prefixWrite_ = _cont.getPrefix();
//        String beanName_ = _ip.getBeanName();
        StringList attributesNames_ = new StringList();
        NamedNodeMap mapAttr_ = read.getAttributes();
        int nbAttrs_ = mapAttr_.getLength();
        for (int i = 0; i < nbAttrs_; i++) {
            attributesNames_.add(mapAttr_.item(i).getName());
        }
        attributesNames_.removeAllString(ATTRIBUTE_ID);
        String id_ = read.getAttribute(ATTRIBUTE_ID);
        if (!id_.isEmpty()) {
            ResultText r_ = new ResultText();
            r_.buildId(id_,_cont,_doc);
            attributesText.put(ATTRIBUTE_ID,r_);
        }
        String prefGr_ = StringList.concat(prefixWrite_, ATTRIBUTE_GROUP_ID);
        attributesNames_.removeAllString(prefGr_);
        String groupId_ = read.getAttribute(prefGr_);
        if (!groupId_.isEmpty()) {
            ResultText r_ = new ResultText();
            r_.buildId(groupId_,_cont,_doc);
            attributesText.put(prefGr_,r_);
        }
        processAttributes(_cont,_doc,read,attributesNames_);
        for (String a: attributesNames_) {
            String attr_ = read.getAttribute(a);
            if (attr_.trim().isEmpty()) {
                continue;
            }
            ResultText r_ = new ResultText();
            r_.build(attr_,_cont,_doc);
            attributes.addEntry(a,r_);
        }
    }

    @Override
    public void reduce(Configuration _context) {
        for (EntryCust<String,ResultText> e: attributesText.entryList()) {
            ResultText.reduce(e.getValue().getOpExp());
        }
    }

    protected abstract void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list);

    public final Element getRead() {
        return read;
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        Node write_ = rw_.getWrite();
        if (ip_.matchStatement(this)) {
            processBlockAndRemove(_cont);
            return;
        }
        Document ownerDocument_ = rw_.getDocument();
        appendChild(ownerDocument_, write_,read);
        MutableNode nextWrite_ = write_.getLastChild();
        for (EntryCust<String,ResultText> e: attributesText.entryList()) {
            ResultText res_ = e.getValue();
            String txt_ = ResultText.render(res_.getOpExp(), res_.getTexts(), _cont);
            if (_cont.getContext().hasExceptionOrFailInit()) {
                return;
            }
            ((Element)nextWrite_).setAttribute(e.getKey(),txt_);
        }
        processExecAttr(_cont,nextWrite_,read);
        if (_cont.getContext().hasExceptionOrFailInit()) {
            return;
        }
        for (EntryCust<String,ResultText> e: attributes.entryList()) {
            ResultText res_ = e.getValue();
            String txt_ = ResultText.render(res_.getOpExp(), res_.getTexts(), _cont);
            if (_cont.getContext().hasExceptionOrFailInit()) {
                return;
            }
            ((Element)nextWrite_).setAttribute(e.getKey(),txt_);
        }
        RendIfStack if_ = new RendIfStack();
        if_.setLastBlock(this);
        if_.setBlock(this);
        if_.setCurrentVisitedBlock(this);
        ip_.addBlock(if_);
        if_.setEntered(true);
        rw_.setRead(getFirstChild());
        rw_.setWrite(nextWrite_);
    }

    protected abstract void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read);

}
