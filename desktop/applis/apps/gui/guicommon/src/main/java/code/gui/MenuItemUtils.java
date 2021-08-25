package code.gui;

import code.util.IdList;
import code.util.core.IndexConstants;


final class MenuItemUtils {

    private MenuItemUtils() {
    }

    static EnabledMenu get(IdList<EnabledMenu> _list, int _i) {
        if (_list.isValidIndex(_i)) {
            return _list.get(_i);
        }
        return null;
    }
    static void setEnabled(boolean _b, EnabledMenu _subMenu) {
        Menu mPar_ = _subMenu.getParentMenu();
        while (mPar_ != null) {
            if (!_b) {
                int nbSubMenus_ = mPar_.getSubCount();
                for (int i = IndexConstants.FIRST_INDEX; i < nbSubMenus_; i++) {
                    EnabledMenu m_ = mPar_.getItem(i);
                    if (m_ == null) {
                        continue;
                    }
                    if (m_.isEnabled()) {
                        return;
                    }
                }
            }
            mPar_.setEnabled(_b);
            mPar_ = mPar_.getParentMenu();
        }
    }
}
