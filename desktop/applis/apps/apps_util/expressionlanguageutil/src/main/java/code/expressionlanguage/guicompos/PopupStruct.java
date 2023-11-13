package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;

public final class PopupStruct extends CustComponentStruct {
    private final AbsPopupMenu popupMenu;
    private final CustList<CustComponentStruct> compo = new CustList<CustComponentStruct>();
    public PopupStruct(String _className, AbsCompoFactory _compoFactory) {
        super(_className);
        popupMenu = _compoFactory.newAbsPopupMenu();
    }

    public void show(Struct _panel, Struct _i, Struct _height) {
        int i_ = ((IntStruct)_i).intStruct();
        int h_ = ((IntStruct)_height).intStruct();
        if (_panel instanceof CustComponentStruct) {
            popupMenu.show(((CustComponentStruct)_panel).getComponent(),i_,h_);
        } else {
            popupMenu.show(i_,h_);
        }
    }

    public void add(Struct _global) {
        if (_global instanceof CustComponentStruct) {
            if (((CustComponentStruct) _global).getParentComponent() != NullStruct.NULL_VALUE) {
                return;
            }
//            for (CustComponentStruct a: compo) {
//                if (a.sameReference(_global)) {
//                    return;
//                }
//            }
            ((CustComponentStruct) _global).setParentComponent(this);
            compo.add((CustComponentStruct) _global);
            popupMenu.add(((CustComponentStruct)_global).getComponent());
        }
    }

    public void remove(Struct _global) {
        if (_global instanceof CustComponentStruct) {
            int i_ = 0;
            int index_ = -1;
            for (CustComponentStruct a: compo) {
                if (a.sameReference(_global)) {
                    index_ = i_;
                    break;
                }
                i_++;
            }
            if (index_ < 0) {
                return;
            }
            compo.remove(index_);
            ((CustComponentStruct) _global).setNullParentComponent();
            popupMenu.remove(((CustComponentStruct)_global).getComponent());
        }
    }
    public Struct getCompo(Struct _index) {
        int index_ = ((NumberStruct)_index).intStruct();
        if (!compo.isValidIndex(index_)) {
            return NullStruct.NULL_VALUE;
        }
        return compo.get(index_);
    }
    public Struct getCompoCount() {
        return new IntStruct(compo.size());
    }
    @Override
    protected AbsCustComponent getComponent() {
        return popupMenu;
    }
}
