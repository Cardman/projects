package code.bean.nat.fwd;

import code.formathtml.analyze.blocks.AnaRendParentBlock;
import code.formathtml.errors.RendKeyWords;
import code.sml.AttributePart;
import code.sml.Element;
import code.util.StringMap;

public interface AbstractNatBlockBuilder {
    AnaRendParentBlock defBlock(int _begin, String _prefix, RendKeyWords _rendKeyWords, Element _elt, StringMap<AttributePart> _attributes);
}
