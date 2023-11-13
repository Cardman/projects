package code.gui;

import code.util.CustList;
import code.util.core.IndexConstants;


public final class MenuItemUtils {

    private MenuItemUtils() {
    }

    public static void setEnabledMenu(EnabledMenu _subMenu,boolean _b) {
        _subMenu.setEnabledMenu(_b);
        setEnabled(_b,_subMenu);
    }
    public static void setEnabled(boolean _b, EnabledMenu _subMenu) {
        EnabledMenu mPar_ = _subMenu.getParentMenu();
        while (mPar_ != null) {
            if (!_b) {
                CustList<AbsCustComponent> items_ = mPar_.getItems();
                int nbSubMenus_ = items_.size();
                for (int i = IndexConstants.FIRST_INDEX; i < nbSubMenus_; i++) {
                    AbsCustComponent m_ = items_.get(i);
                    if (m_ instanceof EnabledMenu&&m_.isEnabled()) {
                        return;
                    }
                }
            }
            mPar_.setEnabled(_b);
            mPar_ = mPar_.getParentMenu();
        }
    }
}
