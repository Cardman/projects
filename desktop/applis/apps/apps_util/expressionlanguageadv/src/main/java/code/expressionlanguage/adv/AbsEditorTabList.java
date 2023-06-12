package code.expressionlanguage.adv;

import code.util.core.StringUtil;

public abstract class AbsEditorTabList {

    int indexOpened(String _str) {
        int opened_ = -1;
        int s_ = tabCount();
        for (int i = 0; i < s_; i++) {
            if (StringUtil.quickEq(tab(i).getFullPath(), _str)) {
                opened_ = i;
                break;
            }
        }
        return opened_;
    }
    public abstract int tabCount();
    public abstract AbsTabEditor tab(int _i);
}
