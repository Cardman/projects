package code.expressionlanguage.adv;

import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.gui.GroupFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.util.core.StringUtil;

public abstract class AbsEditorTabList extends GroupFrame {
    private AbsResultContextNext resultContextNext;
    protected AbsEditorTabList(AbsResultContextNext _a, AbstractProgramInfos _list) {
        super(_list);
        setResultContextNext(_a);
    }

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

    public AbsResultContextNext getResultContextNext() {
        return resultContextNext;
    }

    public void setResultContextNext(AbsResultContextNext _r) {
        this.resultContextNext = _r;
    }
}
