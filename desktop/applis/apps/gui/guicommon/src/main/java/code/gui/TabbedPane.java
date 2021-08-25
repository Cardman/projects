package code.gui;

import code.util.CustList;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public final class TabbedPane extends CustComponent {

    private JTabbedPane component = new JTabbedPane();

    public int getComponentCount() {
        return getChildren().size();
    }

    public int getSelectedIndex() {
        return component.getSelectedIndex();
    }

    public void setSelectedIndex(int _index) {
        if (!getChildren().isValidIndex(_index)) {
            return;
        }
        component.setSelectedIndex(_index);
    }
    public void add(String _title, AbsCustComponent _component) {
        if (_component.getParent() != null) {
            return;
        }
        _component.setParent(this);
        getChildren().add(_component);
        component.add(_title, ((CustComponent)_component).getNatComponent());
    }

    public void setTab(int _index,AbsCustComponent _component) {
        if (!getChildren().isValidIndex(_index)) {
            return;
        }
        if (_component.getParent() != null) {
            return;
        }
        getChildren().get(_index).setParent(null);
        _component.setParent(this);
        getChildren().set(_index,_component);
        component.setTabComponentAt(_index,((CustComponent) _component).getNatComponent());
    }
    public String getTitle(int _index) {
        if (!getChildren().isValidIndex(_index)) {
            return "";
        }
        return component.getTitleAt(_index);
    }
    public void setTitle(int _index,String _title) {
        if (!getChildren().isValidIndex(_index)) {
            return;
        }
        component.setTitleAt(_index,_title);
    }

    public void remove(int _index) {
        getChildren().get(_index).setParent(null);
        getChildren().remove(_index);
        component.removeTabAt(_index);
    }

    public int index(AbsCustComponent _cust) {
        int i_ = 0;
        int index_ = -1;
        for (AbsCustComponent c: getChildren()) {
            if (c == _cust) {
                index_ = i_;
                break;
            }
            i_++;
        }
        return index_;
    }
    public int remove(AbsCustComponent _cust) {
        int i_ = 0;
        int index_ = -1;
        CustList<AbsCustComponent> rem_ = new CustList<AbsCustComponent>();
        CustList<String> remTitles_ = new CustList<String>();
        for (AbsCustComponent c: getChildren()) {
            if (c == _cust) {
                c.setParent(null);
                index_ = i_;
            } else {
                remTitles_.add(component.getTitleAt(i_));
                rem_.add(c);
            }
            i_++;
        }
        getChildren().clear();
        component.removeAll();
        i_ = 0;
        for (AbsCustComponent c: rem_) {
            getChildren().add(c);
            component.addTab(remTitles_.get(i_),((CustComponent)c).getNatComponent());
            i_++;
        }
        return index_;
    }
    public AbsCustComponent getComponent(int _index) {
        return getChildren().get(_index);
    }

    @Override
    protected JComponent getNatComponent() {
        return component;
    }

    public void removeAll() {
        for (AbsCustComponent c: getChildren()) {
            c.setParent(null);
        }
        getChildren().clear();
        component.removeAll();
    }

}
