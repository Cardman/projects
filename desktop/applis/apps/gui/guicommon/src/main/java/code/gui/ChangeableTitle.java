package code.gui;

import java.awt.Point;

public interface ChangeableTitle extends Iconifiable {

    String getTitle();

    void setTitle(String _title);
    Point getLocationOnScreen();

}
