package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;

public final class SelOpeningReadOnlyFile implements AbsOpeningReadOnlyFile {
    private final GuiStackForm form;
    public SelOpeningReadOnlyFile(GuiStackForm _f) {
        form = _f;
    }
    @Override
    public void openFile(AbsDebuggerGui _curr, ResultContext _res, String _str, String _content) {
        String dec_ = StringUtil.nullToEmpty(StringUtil.decode(_content.getBytes()));
        ReadOnlyFormTabEditor te_ = form.getReadOnlyFormTabEditor();
        te_.setFullPath(_str);
        te_.setUseFeed(WindowWithTreeImpl.lineSeparator(dec_));
        te_.centerText(new DefaultUniformingString().apply(dec_));
    }

}
