package code.bean.nat.fwd;

import code.bean.nat.analyze.blocks.NatAnaRendParentBlock;
import code.formathtml.errors.RendKeyWords;
import code.sml.Element;

public interface AbstractNatBlockBuilder {
    NatAnaRendParentBlock defBlock(String _prefix, RendKeyWords _rendKeyWords, Element _elt);
}
