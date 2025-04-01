package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.*;
import code.gui.AbsCustComponent;
import code.gui.AbsSplitPane;
import code.gui.GuiConstants;
import code.gui.initialize.AbsCompoFactory;

public final class SplitPaneStruct extends CustComponentStruct implements ContainerStruct{
    private final AbsSplitPane splitPane;
    private Struct left;
    private Struct right;
    public SplitPaneStruct(String _className, Struct _orient, Struct _left, Struct _right, AbsCompoFactory _compoFactory) {
        super(_className);
        left = _left;
        right = _right;
        if (_left instanceof CustComponentStruct) {
            ((CustComponentStruct)_left).setParentComponent(this);
            getChildren().add((CustComponentStruct)_left);
            if (_right instanceof CustComponentStruct) {
                ((CustComponentStruct)_right).setParentComponent(this);
                getChildren().add((CustComponentStruct)_right);
                splitPane = split(_orient, _left, _right, _compoFactory);
            } else {
                splitPane = splitLeft(_orient, _left, _compoFactory);
            }
        } else if (_right instanceof CustComponentStruct) {
            ((CustComponentStruct)_right).setParentComponent(this);
            getChildren().add((CustComponentStruct)_right);
            splitPane = splitRight(_orient, _right, _compoFactory);
        } else {
            splitPane = split(_orient,_compoFactory);
        }
    }
    private static AbsSplitPane split(Struct _orient, Struct _left, Struct _right, AbsCompoFactory _compoFactory) {
        if (GuiConstants.toSplitOrientation(((NumberStruct)_orient).intStruct()) == GuiConstants.HORIZONTAL_SPLIT) {
            return _compoFactory.newHorizontalSplitPane(((CustComponentStruct)_left).getComponent(),((CustComponentStruct)_right).getComponent());
        }
        return _compoFactory.newVerticalSplitPane(((CustComponentStruct)_left).getComponent(),((CustComponentStruct)_right).getComponent());
    }
    private static AbsSplitPane splitLeft(Struct _orient, Struct _left, AbsCompoFactory _compoFactory) {
        if (GuiConstants.toSplitOrientation(((NumberStruct)_orient).intStruct()) == GuiConstants.HORIZONTAL_SPLIT) {
            return _compoFactory.newHorizontalSplitPaneLeft(((CustComponentStruct)_left).getComponent());
        }
        return _compoFactory.newVerticalSplitPaneLeft(((CustComponentStruct)_left).getComponent());
    }
    private static AbsSplitPane splitRight(Struct _orient, Struct _right, AbsCompoFactory _compoFactory) {
        if (GuiConstants.toSplitOrientation(((NumberStruct)_orient).intStruct()) == GuiConstants.HORIZONTAL_SPLIT) {
            return _compoFactory.newHorizontalSplitPaneRight(((CustComponentStruct)_right).getComponent());
        }
        return _compoFactory.newVerticalSplitPaneRight(((CustComponentStruct)_right).getComponent());
    }
    private static AbsSplitPane split(Struct _orient, AbsCompoFactory _compoFactory) {
        if (GuiConstants.toSplitOrientation(((NumberStruct)_orient).intStruct()) == GuiConstants.HORIZONTAL_SPLIT) {
            return _compoFactory.newHorizontalSplitPane();
        }
        return _compoFactory.newVerticalSplitPane();
    }

    @Override
    public void move(CustComponentStruct _compo) {
        if (_compo == left) {
            removeLeft();
            left = NullStruct.NULL_VALUE;
        }
        if (_compo == right) {
            removeRight();
            right = NullStruct.NULL_VALUE;
        }
    }

    public Struct getLeftComponent() {
        return left;
    }

    public void setLeftComponent(Struct _scroll) {
        if (!(_scroll instanceof CustComponentStruct)) {
            removeLeft();
            left = NullStruct.NULL_VALUE;
            splitPane.setLeftComponentNull();
            return;
        }
        CustComponentStruct c_ = (CustComponentStruct) _scroll;
        if (kept(c_)) {
            return;
        }
        removeLeft();
        left = _scroll;
        c_.setParentComponent(this);
        splitPane.setLeftComponent(c_.getComponent());
        getChildren().add(0, c_);
    }

    private void removeLeft() {
        if (left instanceof CustComponentStruct) {
            getChildren().removeObj((CustComponentStruct) left);
            ((CustComponentStruct) left).setNullParentComponent();
        }
    }

    public Struct getRightComponent() {
        return right;
    }
    public void setRightComponent(Struct _scroll) {
        if (!(_scroll instanceof CustComponentStruct)) {
            removeRight();
            right = NullStruct.NULL_VALUE;
            splitPane.setRightComponentNull();
            return;
        }
        CustComponentStruct c_ = (CustComponentStruct) _scroll;
        if (kept(c_)) {
            return;
        }
        removeRight();
        right = _scroll;
        c_.setParentComponent(this);
        splitPane.setRightComponent(c_.getComponent());
        getChildren().add(c_);
    }

    private void removeRight() {
        if (right instanceof CustComponentStruct) {
            getChildren().removeObj((CustComponentStruct) right);
            ((CustComponentStruct) right).setNullParentComponent();
        }
    }


    public BooleanStruct isContinuousLayout() {
        return BooleanStruct.of(splitPane.isContinuousLayout());
    }

    public void setContinuousLayout(Struct _b) {
        splitPane.setContinuousLayout(BooleanStruct.isTrue(_b));
    }

    public BooleanStruct isOneTouchExpandable() {
        return BooleanStruct.of(splitPane.isOneTouchExpandable());
    }

    public void setOneTouchExpandable(Struct _b) {
        splitPane.setOneTouchExpandable(BooleanStruct.isTrue(_b));
    }

    public IntStruct getDividerLocation() {
        return new IntStruct(splitPane.getDividerLocation());
    }
    public void setDividerLocation(Struct _i) {
        splitPane.setDividerLocation(((NumberStruct)_i).intStruct());
    }

    public IntStruct getDividerSize() {
        return new IntStruct(splitPane.getDividerSize());
    }
    public void setDividerSize(Struct _i) {
        splitPane.setDividerSize(((NumberStruct)_i).intStruct());
    }

    public void revalidate() {
        splitPane.revalidate();
    }

    @Override
    protected AbsCustComponent getComponent() {
        return splitPane;
    }
}
