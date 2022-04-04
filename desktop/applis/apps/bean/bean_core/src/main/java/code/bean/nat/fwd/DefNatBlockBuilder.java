package code.bean.nat.fwd;

import code.bean.nat.analyze.blocks.NatAnaRendBlock;
import code.bean.nat.analyze.blocks.NatAnaRendStdElement;
import code.formathtml.errors.RendKeyWords;
import code.sml.Element;

public final class DefNatBlockBuilder implements AbstractNatBlockBuilder {

    @Override
    public NatAnaRendBlock defBlock(String _prefix, RendKeyWords _rendKeyWords, Element _elt) {
        return new NatAnaRendStdElement(_elt);
    }
}
