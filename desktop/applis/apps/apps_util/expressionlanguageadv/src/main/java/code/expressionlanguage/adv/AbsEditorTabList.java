package code.expressionlanguage.adv;

import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.gui.AbstractMutableTreeNodeCore;
import code.gui.GroupFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class AbsEditorTabList extends GroupFrame {
    private AbsResultContextNext resultContextNext;
    protected AbsEditorTabList(AbsResultContextNext _a, String _lg, AbstractProgramInfos _list) {
        super(_lg, _list);
        setResultContextNext(_a);
    }

    static String buildPath(AbstractMutableTreeNodeCore<String> _treePath) {
        StringList pathFull_ = new StringList();
        AbstractMutableTreeNodeCore<String> current_ = _treePath;
        while (current_ != null) {
            pathFull_.add(0,current_.info());
            current_ = current_.getParent();
        }
        StringUtil.removeObj(pathFull_, "");
        return StringUtil.join(pathFull_,"");
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
