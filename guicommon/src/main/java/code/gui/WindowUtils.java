package code.gui;
import java.awt.Window;

import code.util.CustList;

public final class WindowUtils {

//    private static final String REMOVE = "remove";
//    private static final String APP_CONTEXT = "appContext";

    private static final CustList<Window> WINDOWS = new CustList<Window>();

    private WindowUtils() {
    }

    static void addInArray(Window _window) {
        WINDOWS.add(_window);
    }

    /**It is too dangerous to let the user access to the windows
    because the user can close accidentally a window or else add components
    from "bean" classes
    @throws SecurityException if called from bean classes
    and there is a security manager*/
    public static CustList<Window> getWindows() {
        return WINDOWS;
    }

    /** Optimize the memory */
    static void removeWindow(Window _window) {
        int i_ = CustList.FIRST_INDEX;
        int len_ = WINDOWS.size();
        while (i_ < len_) {
            if (WINDOWS.get(i_) == _window) {
                WINDOWS.removeAt(i_);
                break;
            }
            i_ ++;
        }
    }
}
