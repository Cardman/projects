package cards.gui.dialogs;

import cards.gui.WindowCards;
import cards.gui.dialogs.enums.SaveDealMode;
import cards.gui.dialogs.events.SavingDealEvent;
import code.gui.AbsButton;
import code.gui.files.AbsButtonsSavePanel;
import code.gui.files.FileSaveDialogContent;
import code.scripts.messages.cards.MessagesEditorCards;

public final class EditorButtonsSavePanel implements AbsButtonsSavePanel {
    private final EditorCards editorCards;
    private final WindowCards window;
    private final SetterSelectedCardList dialog;

    public EditorButtonsSavePanel(EditorCards _a, WindowCards _w, SetterSelectedCardList _d) {
        this.editorCards = _a;
        this.window = _w;
        this.dialog = _d;
    }

    @Override
    public void build(FileSaveDialogContent _content) {
        AbsButton bouton_=_content.getProgramInfos().getCompoFactory().newPlainButton(editorCards.translate(MessagesEditorCards.SAVE_WITHOUT_CLOSING));
        bouton_.addActionListener(new SavingDealEvent(dialog, SaveDealMode.SAVE_WITHOUT_CLOSING));
        _content.getButtons().add(bouton_);
        bouton_=_content.getProgramInfos().getCompoFactory().newPlainButton(editorCards.translate(MessagesEditorCards.SAVE_THEN_PLAY));
        bouton_.addActionListener(new EditorCardsNonModalEvent(window),new SavingDealEvent(dialog, SaveDealMode.SAVE_THEN_PLAY));
        _content.getButtons().add(bouton_);
        bouton_=_content.getProgramInfos().getCompoFactory().newPlainButton(editorCards.translate(MessagesEditorCards.PLAY_WITHOUT_SAVING));
        bouton_.addActionListener(new EditorCardsNonModalEvent(window),new SavingDealEvent(dialog, SaveDealMode.PLAY_WITHOUT_SAVING));
        _content.getButtons().add(bouton_);
        bouton_=_content.getProgramInfos().getCompoFactory().newPlainButton(editorCards.translate(MessagesEditorCards.SAVE_THEN_CLOSE));
        bouton_.addActionListener(new SavingDealEvent(dialog, SaveDealMode.SAVE_THEN_CLOSE));
        _content.getButtons().add(bouton_);
    }
}
