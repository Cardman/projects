package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustComponent;
import code.gui.AbsScrollPane;
import code.gui.initialize.AbsCompoFactory;

public final class ScrollPaneStruct extends CustComponentStruct {
    private final AbsScrollPane scrollPane;
    private Struct view = NullStruct.NULL_VALUE;
    private ScrollPaneStruct(String _className, AbsCompoFactory _compo) {
        super(_className);
        scrollPane = _compo.newAbsScrollPane();
    }
    private ScrollPaneStruct(CustComponentStruct _cust,String _className, AbsCompoFactory _compo) {
        super(_className);
        if (_cust.getParentComponent() != NullStruct.NULL_VALUE) {
            scrollPane = _compo.newAbsScrollPane();
            return;
        }
        view = _cust;
        scrollPane = _compo.newAbsScrollPane(_cust.getComponent());
    }

    public static ScrollPaneStruct newScroll(String _className, AbsCompoFactory _compo) {
        return new ScrollPaneStruct(_className,_compo);
    }

    public static ScrollPaneStruct newScroll(Struct _str, String _className, AbsCompoFactory _compo) {
        if (!(_str instanceof CustComponentStruct)) {
            return new ScrollPaneStruct(_className,_compo);
        }
        CustComponentStruct c_ = (CustComponentStruct) _str;
        return new ScrollPaneStruct(c_,_className,_compo);
    }
    public void setViewportView(Struct _graphic) {
        if (!(_graphic instanceof CustComponentStruct)) {
            view = NullStruct.NULL_VALUE;
            scrollPane.setNullViewportView();
            return;
        }
        CustComponentStruct c_ = (CustComponentStruct) _graphic;
        if (c_.getParentComponent() != NullStruct.NULL_VALUE) {
            return;
        }
        if (view instanceof CustComponentStruct) {
            ((CustComponentStruct) view).setNullParentComponent();
        }
        view = c_;
        scrollPane.setViewportView(c_.getComponent());
    }

    public IntStruct getHorizontalValue() {
        return new IntStruct(scrollPane.getHorizontalValue());
    }
    public void setHorizontalValue(Struct _value) {
        scrollPane.setHorizontalValue(((NumberStruct)_value).intStruct());
    }
    public IntStruct getVerticalValue() {
        return new IntStruct(scrollPane.getVerticalValue());
    }
    public void setVerticalValue(Struct _value) {
        scrollPane.setVerticalValue(((NumberStruct)_value).intStruct());
    }
    public void revalidate() {
        scrollPane.revalidate();
    }
    public Struct getView() {
        return view;
    }

    @Override
    protected AbsCustComponent getComponent() {
        return scrollPane;
    }
}
