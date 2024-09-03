package cards.gui.dialogs;

import cards.gui.WindowCardsInt;
import code.gui.AbsButton;
import code.gui.AbsPanel;
import code.gui.files.AbsPostFileDialogEvent;
import code.gui.files.FileSaveDialogContent;
import code.gui.files.MessagesGuiFct;
import code.gui.initialize.AbsCompoFactory;

public final class EditorPostFileDialogEvent implements AbsPostFileDialogEvent {
    private final FileSaveDialogContent saveDialogContent;
    private final EditorCards editorCards;
    private final WindowCardsInt window;
    private final SetterSelectedCardList dialog;

    public EditorPostFileDialogEvent(FileSaveDialogContent _s, EditorCards _e, WindowCardsInt _w, SetterSelectedCardList _d) {
        this.saveDialogContent = _s;
        this.editorCards = _e;
        this.window = _w;
        this.dialog = _d;
    }

    @Override
    public String act(String _path) {
        saveDialogContent.setSelectedAbsolutePath(_path);
        saveDialogContent.setSelectedPath(_path);
        return _path;
    }

    @Override
    public String title(String _path) {
        return _path;
    }

    @Override
    public String visible(AbsPanel _panel) {
        AbsPanel panelDeal_ = editorCards.getPanelDeal();
        AbsCompoFactory compoFactory_ = editorCards.getProgramInfos().getCompoFactory();
        panelDeal_.add(compoFactory_.newHorizontalSplitPane(compoFactory_.newAbsScrollPane(editorCards.getBorder()),compoFactory_.newAbsScrollPane(_panel)), MessagesGuiFct.BORDER_LAYOUT_CENTER);
        AbsPanel line_ = compoFactory_.newLineBox();
        AbsButton bouton_= editorCards.buildBackToRules(window,dialog);
        line_.add(bouton_);
        line_.add(editorCards.getErrors());
        panelDeal_.add(line_, MessagesGuiFct.BORDER_LAYOUT_SOUTH);
        return "";
    }
}
