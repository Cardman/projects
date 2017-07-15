package code.gui;
import javax.swing.JMenuItem;

import code.util.CustList;


final class MenuItemUtils {

    private MenuItemUtils() {
    }

    static void setEnabled(boolean _b, EnabledMenu _subMenu) {
        Menu mPar_ = _subMenu.getParentMenu();
        if (mPar_ == null) {
            return;
        }
        if (!_b) {
            int nbSubMenus_ = mPar_.getItemCount();
            for (int i = CustList.FIRST_INDEX; i < nbSubMenus_; i++) {
                JMenuItem m_ = mPar_.getItem(i);
                if (m_ == null) {
                    continue;
                }
                if (m_.isEnabled()) {
                    return;
                }
            }
        }
        mPar_.setEnabled(_b);
    }
}
