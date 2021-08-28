package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustComponent;
import code.gui.AbsPopupMenu;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;

public final class PopupStruct extends CustComponentStruct {
    private final AbsPopupMenu popupMenu;
    private final CustList<CustComponentStruct> compo = new CustList<CustComponentStruct>();
    private final CustList<AbsMenuStruct> menus = new CustList<AbsMenuStruct>();
    protected PopupStruct(String _className, AbsCompoFactory _compoFactory) {
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
            for (CustComponentStruct a: compo) {
                if (a.sameReference(_global)) {
                    return;
                }
            }
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
            popupMenu.remove(((CustComponentStruct)_global).getComponent());
        }
    }
    Struct getCompo(Struct _index) {
        int index_ = ((NumberStruct)_index).intStruct();
        if (!compo.isValidIndex(index_)) {
            return NullStruct.NULL_VALUE;
        }
        return compo.get(index_);
    }
    Struct getCompoCount() {
        return new IntStruct(compo.size());
    }
    public void addMenu(Struct _global) {
        if (_global instanceof AbsMenuStruct) {
            if (((AbsMenuStruct) _global).getParentMenu() != NullStruct.NULL_VALUE) {
                return;
            }
            for (AbsMenuStruct a: menus) {
                if (a.sameReference(_global)) {
                    return;
                }
            }
            menus.add((AbsMenuStruct) _global);
            if (_global instanceof MenuStruct) {
                popupMenu.add(((MenuStruct)_global).getComponent());
            } else if (_global instanceof MenuItemStruct) {
                popupMenu.add(((MenuItemStruct)_global).getComponent());
            } else {
                popupMenu.add(((MenuItemCheckStruct)_global).getComponent());
            }
        }
    }
    public void removeMenu(Struct _global) {
        if (_global instanceof AbsMenuStruct) {
            int i_ = 0;
            int index_ = -1;
            for (AbsMenuStruct a: menus) {
                if (a.sameReference(_global)) {
                    index_ = i_;
                    break;
                }
                i_++;
            }
            if (index_ < 0) {
                return;
            }
            menus.remove(index_);
            if (_global instanceof MenuStruct) {
                popupMenu.remove(((MenuStruct)_global).getComponent());
            } else if (_global instanceof MenuItemStruct) {
                popupMenu.remove(((MenuItemStruct)_global).getComponent());
            } else {
                popupMenu.remove(((MenuItemCheckStruct)_global).getComponent());
            }
        }
    }
    Struct getMenu(Struct _index) {
        int index_ = ((NumberStruct)_index).intStruct();
        if (!menus.isValidIndex(index_)) {
            return NullStruct.NULL_VALUE;
        }
        return menus.get(index_);
    }
    Struct getMenuCount() {
        return new IntStruct(menus.size());
    }
    @Override
    protected AbsCustComponent getComponent() {
        return popupMenu;
    }
}
