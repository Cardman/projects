package code.expressionlanguage;

import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.gui.PopupMenu;

public final class PopupStruct extends CustComponentStruct {
    private PopupMenu popupMenu = new PopupMenu();
    protected PopupStruct(String _className) {
        super(_className);
    }

    public void show(Struct panel, Struct _i, Struct _height) {
        int i_ = ((IntStruct)_i).intStruct();
        int h_ = ((IntStruct)_height).intStruct();
        if (panel instanceof CustComponentStruct) {
            popupMenu.show(((CustComponentStruct)panel).getComponent(),i_,h_);
        } else {
            popupMenu.show(i_,h_);
        }
    }

    public void add(Struct global) {
        if (global instanceof CustComponentStruct) {
            popupMenu.add(((CustComponentStruct)global).getComponent());
        }
    }

    @Override
    protected CustComponent getComponent() {
        return popupMenu;
    }
}
