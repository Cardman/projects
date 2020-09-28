package code.formathtml.exec.blocks;

import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.stacks.RendIfStack;
import code.formathtml.stacks.RendReadWrite;
import code.sml.*;
import code.util.EntryCust;
import code.util.StringMap;

public abstract class RendElement extends RendParentBlock implements RendWithEl, RendReducableOperations {
    private Element read;
    private StringMap<ExecTextPart> execAttributes = new StringMap<ExecTextPart>();
    private StringMap<ExecTextPart> execAttributesText = new StringMap<ExecTextPart>();

    public RendElement(int _offsetTrim, Element read, StringMap<ExecTextPart> execAttributes, StringMap<ExecTextPart> execAttributesText) {
        super(_offsetTrim);
        this.read = read;
        this.execAttributes = execAttributes;
        this.execAttributesText = execAttributesText;
    }

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
        for (EntryCust<String, ExecTextPart> e: execAttributesText.entryList()) {
            ExecTextPart res_ = e.getValue();
            String txt_ = RenderingText.render(res_, _cont);
            if (_cont.getContext().hasException()) {
                return;
            }
            ((Element)nextWrite_).setAttribute(e.getKey(),txt_);
        }
        processExecAttr(_cont,nextWrite_,read);
        if (_cont.getContext().hasException()) {
            return;
        }
        for (EntryCust<String, ExecTextPart> e: execAttributes.entryList()) {
            ExecTextPart res_ = e.getValue();
            String txt_ = RenderingText.render(res_, _cont);
            if (_cont.getContext().hasException()) {
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
