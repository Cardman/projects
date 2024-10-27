package code.gui;

import code.gui.images.*;
import code.gui.initialize.*;
import code.util.core.*;

public final class StrCustCellRenderGeneImpl extends AbsCustCellRenderGeneImpl<String> {
    public StrCustCellRenderGeneImpl(AbsCompoFactory _compo, AbstractImageFactory _img) {
        super(_compo, _img);
    }

    @Override
    protected String convert(String _info) {
        return StringUtil.nullToEmpty(_info);
    }
}
