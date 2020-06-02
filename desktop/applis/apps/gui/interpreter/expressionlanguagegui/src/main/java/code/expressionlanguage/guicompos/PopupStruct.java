package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.gui.PopupMenu;
import code.util.CustList;

public final class PopupStruct extends CustComponentStruct {
    private PopupMenu popupMenu = new PopupMenu();
    private CustList<CustComponentStruct> compo = new CustList<CustComponentStruct>();
    private CustList<AbsMenuStruct> menus = new CustList<AbsMenuStruct>();
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
            if ((((CustComponentStruct) global)).getParentComponent() != NullStruct.NULL_VALUE) {
                return;
            }
            for (CustComponentStruct a: compo) {
                if (a.sameReference(global)) {
                    return;
                }
            }
            (((CustComponentStruct) global)).setParentComponent(this);
            compo.add((CustComponentStruct) global);
            popupMenu.add(((CustComponentStruct)global).getComponent());
        }
    }

    public void remove(Struct global) {
        if (global instanceof CustComponentStruct) {
            int i_ = 0;
            int index_ = -1;
            for (CustComponentStruct a: compo) {
                if (a.sameReference(global)) {
                    index_ = i_;
                    break;
                }
                i_++;
            }
            if (index_ < 0) {
                return;
            }
            compo.remove(index_);
            popupMenu.remove(((CustComponentStruct)global).getComponent());
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
    public void addMenu(Struct global) {
        if (global instanceof AbsMenuStruct) {
            if ((((AbsMenuStruct) global)).getParentMenu() != NullStruct.NULL_VALUE) {
                return;
            }
            for (AbsMenuStruct a: menus) {
                if (a.sameReference(global)) {
                    return;
                }
            }
            menus.add((AbsMenuStruct) global);
            if (global instanceof MenuStruct) {
                popupMenu.add(((MenuStruct)global).getComponent());
            } else if (global instanceof MenuItemStruct) {
                popupMenu.add(((MenuItemStruct)global).getComponent());
            } else {
                popupMenu.add(((MenuItemCheckStruct)global).getComponent());
            }
        }
    }
    public void removeMenu(Struct global) {
        if (global instanceof AbsMenuStruct) {
            int i_ = 0;
            int index_ = -1;
            for (AbsMenuStruct a: menus) {
                if (a.sameReference(global)) {
                    index_ = i_;
                    break;
                }
                i_++;
            }
            if (index_ < 0) {
                return;
            }
            menus.remove(index_);
            if (global instanceof MenuStruct) {
                popupMenu.remove(((MenuStruct)global).getComponent());
            } else if (global instanceof MenuItemStruct) {
                popupMenu.remove(((MenuItemStruct)global).getComponent());
            } else {
                popupMenu.remove(((MenuItemCheckStruct)global).getComponent());
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
    protected CustComponent getComponent() {
        return popupMenu;
    }
}
