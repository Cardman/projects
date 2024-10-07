package code.bean.nat.exec.blocks;

import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.*;
import code.sml.Element;
import code.util.EntryCust;
import code.util.StringMap;

public abstract class NatRendElement extends NatParentBlock implements NatRendElem {
    private final Element read;
    private final StringMap<NatExecTextPart> natAttributes;

    protected NatRendElement(Element _read, StringMap<NatExecTextPart> _execAttributes) {
        this.read = _read;
        this.natAttributes = _execAttributes;
    }

    public final Element getRead() {
        return read;
    }

    @Override
    public void processEl(NatConfigurationCore _cont, NatRendStackCall _rendStack) {
        NatImportingPageAbs ip_ = _rendStack.getLastPage();
        NatRendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(this)) {
            RendBlockHelp.processBlockAndRemove(_rendStack, this);
            return;
        }
        tag(_cont, _rendStack, ip_, rw_);
    }

    protected void tag(NatConfigurationCore _cont, NatRendStackCall _rendStack, NatImportingPageAbs _ip, NatRendReadWrite _rw) {
        //        Document ownerDocument_ = rw_.getDocument();
        Element created_ = RendBlockHelp.appendChild(_rw.getDocument(), _rw, read);
//        for (EntryCust<String, NatExecTextPart> e: natAttributesText.entryList()) {
//            NatExecTextPart res_ = e.getValue();
//            String txt_ = NatRenderingText.renderNat(res_, _rendStack);
//            created_.setAttribute(e.getKey(),txt_);
//        }
//        if (this instanceof NatRendEscImg) {
//            ((NatRendEscImg)this).escImg(_cont, created_);
////        } else if (this instanceof NatRendImg) {
////            ((NatRendImg)this).img(_cont, created_, _rendStack);
//        } else
        if (this instanceof NatRendLink) {
            ((NatRendLink)this).link(_cont, created_);
        }
        endElement(_rendStack, _ip, _rw, created_);
    }

    protected void endElement(NatRendStackCall _rendStack, NatImportingPageAbs _ip, NatRendReadWrite _rw, Element _created) {
        attributes(_rendStack, _created, natAttributes);
        addEltStack(_ip, _rw, _created,this);
    }

    public static void attributes(NatRendStackCall _rendStack, Element _created, StringMap<NatExecTextPart> _map) {
        for (EntryCust<String, NatExecTextPart> e: _map.entryList()) {
            NatExecTextPart res_ = e.getValue();
            String txt_ = NatRenderingText.renderNat(res_, _rendStack);
            _created.setAttribute(e.getKey(),txt_);
        }
    }

    public static void addEltStack(NatImportingPageAbs _nip, NatRendReadWrite _rw, Element _created, NatParentBlock _block) {
        NatIfStack nif_ = new NatIfStack();
        nif_.setLastBlock(_block);
        nif_.setBlock(_block);
        nif_.setCurrentVisitedBlock(_block);
        _nip.addBlock(nif_);
        nif_.setEntered(true);
        _rw.setRead(_block.getFirstChild());
        _rw.setWrite(_created);
    }

}
