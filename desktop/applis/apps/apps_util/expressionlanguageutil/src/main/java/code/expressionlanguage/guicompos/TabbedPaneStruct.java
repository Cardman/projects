package code.expressionlanguage.guicompos;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.*;
import code.gui.AbsCustComponent;
import code.gui.AbsTabbedPane;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;

public final class TabbedPaneStruct extends CustComponentStruct implements ContainerStruct {
    private final AbsTabbedPane tabbedPane;
    public TabbedPaneStruct(String _className, AbsCompoFactory _compoFactory) {
        super(_className);
        tabbedPane = _compoFactory.newAbsTabbedPane();
    }

    public IntStruct getComponentCount() {
        return new IntStruct(tabbedPane.getComponentCount());
    }

    public Struct getComponent(Struct _n) {
        CustList<CustComponentStruct> ch_ = getChildren();
        int index_ = ((NumberStruct)_n).intStruct();
        if (!ch_.isValidIndex(index_)) {
            return NullStruct.NULL_VALUE;
        }
        return ch_.get(index_);
    }

    public IntStruct getSelectedIndex() {
        return new IntStruct(tabbedPane.getSelectedIndex());
    }

    public void setSelectedIndex(Struct _index) {
        int is_ = ((NumberStruct) _index).intStruct();
        if (is_ < 0|| is_ >= tabbedPane.getComponentCount()) {
            return;
        }
        tabbedPane.selectIndex(is_);
    }
    public void add(Struct _title, CustComponentStruct _comp) {
        if (kept(_comp)) {
            return;
        }
        _comp.setParentComponent(this);
        getChildren().add(_comp);
        tabbedPane.add(NumParsers.getString(_title).getInstance(), _comp.getComponent());
    }

    public void setTab(Struct _index,CustComponentStruct _comp) {
        int index_ = ((NumberStruct)_index).intStruct();
        if (!getChildren().isValidIndex(index_)) {
            return;
        }
        if (kept(_comp)) {
            return;
        }
//        getChildren().get(index_).setNullParentComponent();
        _comp.setParentComponent(this);
        getChildren().add(index_,_comp);
        tabbedPane.setTab(index_,_comp.getComponent());
    }
    public Struct getTitle(Struct _index) {
        int index_ = ((NumberStruct)_index).intStruct();
        String title_ = tabbedPane.getTitle(index_);
//        if (title_ == null) {
//            return new StringStruct("");
//        }
        return new StringStruct(title_);
    }
    public void setTitle(Struct _index,Struct _title) {
        if (_title instanceof StringStruct) {
            int index_ = ((NumberStruct)_index).intStruct();
            tabbedPane.setTitle(index_, ((StringStruct)_title).getInstance());
        }
    }

    @Override
    public void move(CustComponentStruct _compo) {
        remove(getChildren().indexOfObj(_compo));
    }

    public Struct remove(Struct _index) {
        int index_ = ((NumberStruct)_index).intStruct();
        return remove(index_);
    }

    private WithoutParentIdStruct remove(int _index) {
        CustList<CustComponentStruct> ch_ = getChildren();
        if (!ch_.isValidIndex(_index)) {
            return NullStruct.NULL_VALUE;
        }
        CustComponentStruct c_ = ch_.get(_index);
        c_.setNullParentComponent();
        ch_.remove(_index);
        tabbedPane.remove(_index);
        return c_;
    }

    public IntStruct index(CustComponentStruct _comp) {
        int i_ = 0;
        int index_ = -1;
        for (CustComponentStruct c: getChildren()) {
            if (c.getComponent() == _comp.getComponent()) {
                index_ = i_;
                break;
            }
            i_++;
        }
        return new IntStruct(index_);
    }
    public IntStruct remove(CustComponentStruct _comp) {
        int i_ = 0;
        int index_ = -1;
        CustList<CustComponentStruct> rem_ = new CustList<CustComponentStruct>();
        CustList<String> remTitles_ = new CustList<String>();
        for (CustComponentStruct c: getChildren()) {
            if (c.getComponent() == _comp.getComponent()) {
                c.setNullParentComponent();
                _comp.setNullParentComponent();
                index_ = i_;
            } else {
                remTitles_.add(tabbedPane.getTitle(i_));
                rem_.add(c);
            }
            i_++;
        }
        getChildren().clear();
        tabbedPane.removeAll();
        i_ = 0;
        for (CustComponentStruct c: rem_) {
            getChildren().add(c);
            tabbedPane.add(remTitles_.get(i_),c.getComponent());
            i_++;
        }
        return new IntStruct(index_);
    }
    public void removeAll() {
        CustList<CustComponentStruct> ch_ = getChildren();
        for (CustComponentStruct c: ch_) {
            c.setNullParentComponent();
        }
        ch_.clear();
        tabbedPane.removeAll();
    }
    @Override
    protected AbsCustComponent getComponent() {
        return tabbedPane;
    }
}
