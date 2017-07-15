package aiki.gui.components.listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aiki.gui.components.Paginator;

public class ChangedPageEvent implements ActionListener {

    private Paginator paginator;

    public ChangedPageEvent(Paginator _paginator) {
        paginator = _paginator;
    }

//    @Override
//    public void itemStateChanged(ItemEvent _e) {
//        if (_e.getStateChange() == ItemEvent.DESELECTED) {
//            return;
//        }
//        paginator.changePageNumber();
//    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        paginator.changePageNumber();
    }

}
