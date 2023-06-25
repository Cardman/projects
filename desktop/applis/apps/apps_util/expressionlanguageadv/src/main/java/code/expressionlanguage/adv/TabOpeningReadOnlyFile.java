package code.expressionlanguage.adv;

import code.stream.BytesInfo;
import code.util.core.StringUtil;

public final class TabOpeningReadOnlyFile implements AbsOpeningReadOnlyFile {
    @Override
    public void openFile(AbsDebuggerGui _curr, String _str, String _content) {
        int opened_ = _curr.indexOpened(_str);
        if (opened_ > -1) {
            _curr.getTabbedPane().selectIndex(opened_);
            return;
        }
        String fullPath_ = _curr.pathToSrc(_curr.getManageOptions())+ _str;
        BytesInfo content_ = new BytesInfo(StringUtil.encode(_content),false);
        _curr.addTab(_curr.getManageOptions(),fullPath_,content_, _curr.getManageOptions().getOptions());
        _curr.getTabbedPane().selectIndex(_curr.getTabs().getLastIndex());
    }
}
