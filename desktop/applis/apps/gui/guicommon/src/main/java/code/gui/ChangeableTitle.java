package code.gui;

import code.gui.images.MetaPoint;



public interface ChangeableTitle extends Iconifiable {

    String getTitle();

    void setTitle(String _title);
    MetaPoint getLocationOnScreen();

}
