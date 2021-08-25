package code.gui;

import code.util.core.IndexConstants;


final class MenuItemUtils {

    private MenuItemUtils() {
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
