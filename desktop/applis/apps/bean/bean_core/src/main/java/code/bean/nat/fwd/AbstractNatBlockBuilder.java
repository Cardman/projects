package code.bean.nat.fwd;

import code.bean.nat.analyze.*;
import code.bean.nat.analyze.blocks.*;
import code.bean.nat.exec.*;
import code.bean.nat.exec.blocks.*;
import code.sml.*;

public interface AbstractNatBlockBuilder {
    NatAnaRendBlock defBlock(String _prefix, RendKeyWordsGroup _rendKeyWords, Element _elt);
    NatBlock toExec(NatAnaRendBlock _from);
    NatImportingPageAbs fwd();

    NatResultText newNatResultText();

}
