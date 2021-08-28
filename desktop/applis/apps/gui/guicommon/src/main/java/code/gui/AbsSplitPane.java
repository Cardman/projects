package code.gui;

public interface AbsSplitPane extends AbsCustComponent {
    void setLeftComponent(AbsCustComponent _scroll);
    void innerLeft(AbsCustComponent _scroll);
    void setRightComponent(AbsCustComponent _scroll);
    void innerRight(AbsCustComponent _scroll);
    boolean isContinuousLayout();
    void setContinuousLayout(boolean _b);
    boolean isOneTouchExpandable();
    void setOneTouchExpandable(boolean _b);
    int getDividerLocation();
    void setDividerLocation(int _i);
    int getDividerSize();
    void setDividerSize(int _i);
}
