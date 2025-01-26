package code.gui.document;

import aiki.game.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;

public final class BeanDisplayCopiedMove implements BeanDisplay<CopiedMove> {
    @Override
    public int display(AbsBeanRender _rend, AbstractProgramInfos _api, AbsPanel _form, CopiedMove _info, int _index, int _count) {
        _rend.formatMessageDir(_api,_form,AbsBeanRender.remainder(_api,_index,_count),_info.getMove());
        _rend.formatMessageDir(_api,_form,AbsBeanRender.remainder(_api,_index+1,_count),Long.toString(_info.getPp()));
        return 2;
    }

}
