package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatIfStack;
import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendReadWrite;
import code.bean.nat.exec.NatRendStackCall;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendElem;
import code.formathtml.exec.blocks.RendParentBlock;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.util.EntryCust;
import code.util.StringMap;

public abstract class NatRendElement extends RendParentBlock implements RendElem, NatRendWithEl {
    private final Element read;
    private final StringMap<NatExecTextPart> natAttributes;
    private final StringMap<NatExecTextPart> natAttributesText;

    protected NatRendElement(Element _read, StringMap<NatExecTextPart> _execAttributes, StringMap<NatExecTextPart> _execAttributesText) {
        this.read = _read;
        this.natAttributes = _execAttributes;
        this.natAttributesText = _execAttributesText;
    }

    public final Element getRead() {
        return read;
    }

    @Override
    public void processEl(Configuration _cont, NatRendStackCall _rendStack) {
        NatImportingPage ip_ = _rendStack.getLastPage();
        NatRendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(this)) {
            RendBlockHelp.processBlockAndRemove(_rendStack, this);
            return;
        }
        Document ownerDocument_ = rw_.getDocument();
        Element created_ = appendChild(ownerDocument_, rw_, read);
        for (EntryCust<String, NatExecTextPart> e: natAttributesText.entryList()) {
            NatExecTextPart res_ = e.getValue();
            String txt_ = NatRenderingText.renderNat(res_, _rendStack);
            created_.setAttribute(e.getKey(),txt_);
        }
        processExecAttr(_cont,created_,read, _rendStack);
        for (EntryCust<String, NatExecTextPart> e: natAttributes.entryList()) {
            NatExecTextPart res_ = e.getValue();
            String txt_ = NatRenderingText.renderNat(res_, _rendStack);
            created_.setAttribute(e.getKey(),txt_);
        }
        addEltStack(ip_,rw_,created_,this);
    }

    public static void addEltStack(NatImportingPage _nip, NatRendReadWrite _rw, Element _created, RendParentBlock _block) {
        NatIfStack nif_ = new NatIfStack();
        nif_.setLastBlock(_block);
        nif_.setBlock(_block);
        nif_.setCurrentVisitedBlock(_block);
        _nip.addBlock(nif_);
        nif_.setEntered(true);
        _rw.setRead(_block.getFirstChild());
        _rw.setWrite(_created);
    }
    protected abstract void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, NatRendStackCall _rendStack);

}
