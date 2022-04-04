package code.bean.nat.fwd;

import code.bean.nat.AbstractNatImpLgNames;
import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.bean.nat.analyze.blocks.NatAnaRendBlock;
import code.formathtml.errors.RendKeyWords;
import code.sml.Element;

public final class AdvNatBlockBuilder implements AbstractNatBlockBuilder {
    private final AbstractNatImpLgNames natImpLgNames;

    public AdvNatBlockBuilder(AbstractNatImpLgNames _natImpLgNames) {
        this.natImpLgNames = _natImpLgNames;
    }

    @Override
    public NatAnaRendBlock defBlock(String _prefix, RendKeyWords _rendKeyWords, Element _elt) {
        return AnaRendBlockHelp.defBlock(_prefix, _rendKeyWords, _elt, natImpLgNames);
    }
}
