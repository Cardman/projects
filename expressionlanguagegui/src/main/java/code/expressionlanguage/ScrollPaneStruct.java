package code.expressionlanguage;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.gui.ScrollPane;

public final class ScrollPaneStruct extends CustComponentStruct {
    private ScrollPane scrollPane;
    private Struct view = NullStruct.NULL_VALUE;
    private ScrollPaneStruct(String _className) {
        super(_className);
        scrollPane = new ScrollPane();
    }
    private ScrollPaneStruct(CustComponentStruct _cust,String _className) {
        super(_className);
        view = _cust;
        scrollPane = new ScrollPane(_cust.getComponent());
    }

    static ScrollPaneStruct newScroll(String _className) {
        return new ScrollPaneStruct(_className);
    }

    static ScrollPaneStruct newScroll(Struct _str, String _className) {
        if (!(_str instanceof CustComponentStruct)) {
            return new ScrollPaneStruct(_className);
        }
        CustComponentStruct c_ = (CustComponentStruct) _str;
        return new ScrollPaneStruct(c_,_className);
    }
    public void setViewportView(Struct _graphic) {
        if (!(_graphic instanceof CustComponentStruct)) {
            view = NullStruct.NULL_VALUE;
            scrollPane.setNullViewportView();
            return;
        }
        CustComponentStruct c_ = (CustComponentStruct) _graphic;
        view = c_;
        scrollPane.setViewportView(c_.getComponent());
    }

    public Struct getView() {
        return view;
    }

    @Override
    protected CustComponent getComponent() {
        return scrollPane;
    }
}
