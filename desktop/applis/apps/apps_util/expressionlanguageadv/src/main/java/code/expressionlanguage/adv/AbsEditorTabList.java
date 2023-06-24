package code.expressionlanguage.adv;

import code.expressionlanguage.utilcompo.AbsResultContextNext;
import code.gui.AbstractMutableTreeNode;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class AbsEditorTabList {
    private AbsResultContextNext resultContextNext;
    protected AbsEditorTabList(AbsResultContextNext _a) {
        setResultContextNext(_a);
    }

    static String buildPath(AbstractMutableTreeNode _treePath) {
        StringList pathFull_ = new StringList();
        AbstractMutableTreeNode current_ = _treePath;
        while (current_ != null) {
            pathFull_.add(0,current_.getUserObject());
            current_ = (AbstractMutableTreeNode) current_.getParent();
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
