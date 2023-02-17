package code.gui;

public interface AbsTabbedPane extends AbsCustComponent {
    int getComponentCount();
    int getSelectedIndex();
    void setSelectedIndex(int _index);
    void selectIndex(int _index);
    void add(String _title, AbsCustComponent _component);
    void addIntTab(String _title, AbsCustComponent _component);
    void addIntTab(String _title, AbsCustComponent _component, String _tooltip);
    boolean setTab(int _index,AbsCustComponent _component);
    void setTabComponentAt(int _index, AbsCustComponent _component);
    String getTitle(int _index);
    String getTitleAt(int _index);
    String getToolTipAt(int _index);
    boolean setTitle(int _index,String _title);
    void setTitleAt(int _index, String _title);
    void setToolTipAt(int _index, String _title);
    void remove(int _index);
    void removeAll();
    void innerRemoveAll();
}
