package code.bean.nat.fwd;

import code.bean.nat.AbstractNatImpLgNames;
import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.formathtml.analyze.blocks.AnaRendParentBlock;
import code.formathtml.errors.RendKeyWords;
import code.sml.AttributePart;
import code.sml.Element;
import code.util.StringMap;

public final class AdvNatBlockBuilder implements AbstractNatBlockBuilder {
    private final AbstractNatImpLgNames natImpLgNames;

    public AdvNatBlockBuilder(AbstractNatImpLgNames _natImpLgNames) {
        this.natImpLgNames = _natImpLgNames;
    }

    @Override
    public AnaRendParentBlock defBlock(int _begin, String _prefix, RendKeyWords _rendKeyWords, Element _elt, StringMap<AttributePart> _attributes) {
        return AnaRendBlockHelp.defBlock(_begin, _prefix, _rendKeyWords, _elt, _attributes, natImpLgNames);
    }
}
