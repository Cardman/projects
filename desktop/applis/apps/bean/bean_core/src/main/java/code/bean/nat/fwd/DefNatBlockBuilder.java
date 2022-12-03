package code.bean.nat.fwd;

import code.bean.nat.analyze.NatResultText;
import code.bean.nat.analyze.blocks.NatAnaRendBlock;
import code.bean.nat.analyze.blocks.NatAnaRendStdElement;
import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatImportingPageAbs;
import code.bean.nat.exec.blocks.NatBlock;
import code.bean.nat.exec.blocks.NatExecTextPart;
import code.bean.nat.exec.blocks.NatRendStdElement;
import code.sml.Element;
import code.sml.RendKeyWordsGroup;
import code.util.StringMap;

public final class DefNatBlockBuilder implements AbstractNatBlockBuilder {

    @Override
    public NatAnaRendBlock defBlock(String _prefix, RendKeyWordsGroup _rendKeyWords, Element _elt) {
        return new NatAnaRendStdElement(_elt,this);
    }

    @Override
    public NatBlock toExec(NatAnaRendBlock _from) {
        if (_from instanceof NatAnaRendStdElement) {
            NatAnaRendStdElement f_ = (NatAnaRendStdElement) _from;
            StringMap<NatExecTextPart> part_ = NatRendForwardInfos.toExecPartExt(f_.getAttributes());
            return new NatRendStdElement(f_.getRead(), part_);
        }
        return null;
    }

    @Override
    public NatImportingPageAbs fwd() {
        return new NatImportingPage();
    }

    @Override
    public NatResultText newNatResultText() {
        return new NatResultText();
    }

}
