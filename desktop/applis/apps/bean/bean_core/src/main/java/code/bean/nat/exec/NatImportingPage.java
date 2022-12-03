package code.bean.nat.exec;

import code.bean.nat.exec.blocks.NatRendElem;
import code.formathtml.exec.blocks.*;

public final class NatImportingPage extends NatImportingPageAbs {

    public void removeRendLastBlockSt() {
        NatAbstractStask last_ = getRendBlockStacks().last();
        if (last_ instanceof NatIfStack && ((NatIfStack) last_).getBlock() instanceof NatRendElem) {
            getRendReadWrite().setWrite(RendBlock.getParentNode(getRendReadWrite()));
        }
        getRendBlockStacks().removeQuicklyLast();
    }

    @Override
    public NatImportingPageAbs fwd() {
        return new NatImportingPage();
    }

    @Override
    public NatRendReadWrite newNatRendReadWrite(NatRendStackCall _rendStackCall) {
        return new NatRendReadWrite();
    }
}
