package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.*;
import code.gui.CustComponent;
import code.gui.TabbedPane;
import code.util.CustList;

public final class TabbedPaneStruct extends CustComponentStruct {
    private TabbedPane tabbedPane = new TabbedPane();
    protected TabbedPaneStruct(String _className) {
        super(_className);
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
        tabbedPane.setSelectedIndex(((NumberStruct)_index).intStruct());
    }
    public void add(Struct _title, CustComponentStruct _comp) {
        if (_comp.getParentComponent() != NullStruct.NULL_VALUE) {
            return;
        }
        _comp.setParentComponent(this);
        getChildren().add(_comp);
        if (_title instanceof StringStruct) {
            tabbedPane.add(((StringStruct)_title).getInstance(), _comp.getComponent());
        } else {
            tabbedPane.add("", _comp.getComponent());
        }
    }

    public void setTab(Struct _index,CustComponentStruct _comp) {
        int index_ = ((NumberStruct)_index).intStruct();
        if (!getChildren().isValidIndex(index_)) {
            return;
        }
        if (_comp.getParentComponent() != NullStruct.NULL_VALUE) {
            return;
        }
        getChildren().get(index_).setNullParentComponent();
        _comp.setParentComponent(this);
        getChildren().set(index_,_comp);
        tabbedPane.setTab(index_,_comp.getComponent());
    }
    public Struct getTitle(Struct _index) {
        int index_ = ((NumberStruct)_index).intStruct();
        String title_ = tabbedPane.getTitle(index_);
        if (title_ == null) {
            return new StringStruct("");
        }
        return new StringStruct(title_);
    }
    public void setTitle(Struct _index,Struct _title) {
        if (_title instanceof StringStruct) {
            int index_ = ((NumberStruct)_index).intStruct();
            tabbedPane.setTitle(index_, ((StringStruct)_title).getInstance());
        }
    }

    public Struct remove(Struct _index) {
        CustList<CustComponentStruct> ch_ = getChildren();
        int index_ = ((NumberStruct)_index).intStruct();
        if (!ch_.isValidIndex(index_)) {
            return NullStruct.NULL_VALUE;
        }
        CustComponentStruct c_ = ch_.get(index_);
        c_.setNullParentComponent();
        ch_.remove(index_);
        tabbedPane.remove(index_);
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
    protected CustComponent getComponent() {
        return tabbedPane;
    }
}
