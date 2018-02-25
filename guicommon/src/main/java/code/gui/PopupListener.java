package code.gui;

import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class PopupListener implements PopupMenuListener {

    private GraphicStringList gr;

    public PopupListener(GraphicStringList _gr) {
        gr = _gr;
    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent _e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent _e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent _e) {
        gr.clearSelection();
    }

}
