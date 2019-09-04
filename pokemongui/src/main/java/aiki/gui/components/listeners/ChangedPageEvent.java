package aiki.gui.components.listeners;
import aiki.gui.components.Paginator;
import code.gui.ListSelection;
import code.gui.SelectionInfo;

public class ChangedPageEvent implements ListSelection {

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
    public void valueChanged(SelectionInfo _e) {
        paginator.changePageNumber();
    }

}
