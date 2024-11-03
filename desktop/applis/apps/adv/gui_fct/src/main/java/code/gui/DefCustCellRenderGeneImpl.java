package code.gui;

import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.util.AbsMap;
import code.util.core.StringUtil;

public final class DefCustCellRenderGeneImpl<T> extends AbsCustCellRenderGeneImpl<T> {
    private final AbsMap<T,String> messages;
    public DefCustCellRenderGeneImpl(AbsCompoFactory _compo, AbstractImageFactory _img, AbsMap<T,String> _m) {
        super(_compo, _img);
        messages = _m;
    }

    public AbsMap<T, String> getMessages() {
        return messages;
    }

    @Override
    protected String convert(T _info) {
        return StringUtil.nullToEmpty(messages.getVal(_info));
    }
}
