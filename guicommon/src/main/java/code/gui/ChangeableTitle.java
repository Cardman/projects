package code.gui;

import java.awt.*;

public interface ChangeableTitle extends Iconifiable {

    String getTitle();

    void setTitle(String _title);
    Point getLocationOnScreen();

}
