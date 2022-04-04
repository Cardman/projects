package code.bean.nat.fwd;

import code.bean.nat.analyze.blocks.NatAnaRendBlock;
import code.formathtml.errors.RendKeyWords;
import code.sml.Element;

public interface AbstractNatBlockBuilder {
    NatAnaRendBlock defBlock(String _prefix, RendKeyWords _rendKeyWords, Element _elt);
}
