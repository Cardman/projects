package code.bean.nat.fwd;

import code.bean.nat.analyze.blocks.NatAnaRendStdElement;
import code.formathtml.analyze.blocks.AnaRendParentBlock;
import code.formathtml.errors.RendKeyWords;
import code.sml.AttributePart;
import code.sml.Element;
import code.util.StringMap;

public final class DefNatBlockBuilder implements AbstractNatBlockBuilder {

    @Override
    public AnaRendParentBlock defBlock(int _begin, String _prefix, RendKeyWords _rendKeyWords, Element _elt, StringMap<AttributePart> _attributes) {
        return new NatAnaRendStdElement(_elt, _begin);
    }
}
