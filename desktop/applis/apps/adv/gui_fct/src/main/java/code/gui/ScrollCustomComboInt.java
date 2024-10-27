package code.gui;

import code.gui.initialize.*;
import code.util.*;

public interface ScrollCustomComboInt extends Input, SelectableIndexes{
    AbsCompoFactory getCompoFactory();
    boolean isEmpty();
    int size();
    boolean remove(int _index);
    void clearRevalidate();
    void clear();
    void select(int _index);
    void move(int _down);
    int forceRefresh();
    void goBound(int _down);
    void movePage(int _down);
    void repaint();
    void revalidate();
    void refreshFocused();
    int getSelectedIndex();
    void addListener(ListSelection _listener);
    void setListener(ListSelection _listener);
    void removeListener(ListSelection _listener);
    AbsPanel getElements();
    boolean isEnabled();
    void setEnabled(boolean _e);
    void changeSelect();
    void text();
    AbsPreparedLabel getSelected();

    void togglePopup();

    void hidePopup();

    void showPopup();
    AbsPopupMenu getPopupMenu();

    void events(SelectionInfo _e);
    IdList<ListSelection> getSelections();
}
