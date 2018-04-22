package code.gui;

import java.awt.Window;

public interface ChangeableTitle extends Iconifiable {

    String getTitle();

    void setTitle(String _title);

    Window getComponent();

}
