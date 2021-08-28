package code.gui;

import javax.swing.*;

public final class GuiConstants {

    public static final String FOLDER_MESSAGES_GUI = "resources_gui/gui/components";

    private GuiConstants() {
    }

    public static int toSplitOrientation(int _orientation) {
        if (_orientation == JSplitPane.VERTICAL_SPLIT) {
            return _orientation;
        }
        return JSplitPane.HORIZONTAL_SPLIT;
    }
}
