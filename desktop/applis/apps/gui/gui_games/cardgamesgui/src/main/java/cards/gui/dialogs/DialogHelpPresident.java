package cards.gui.dialogs;

import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.containers.ContainerSingleImpl;
import cards.president.enumerations.CardPresident;
import code.gui.AbsPanel;

import code.gui.AbsPlainLabel;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicBoolean;
import code.util.AbsBasicTreeMap;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class DialogHelpPresident extends DialogHelpCards {

//    private final AbsCommonFrame absDialog;
//    private final AbsCompoFactory compo;
//    private StringMap<String> messages = new StringMap<String>();

    public DialogHelpPresident(AbstractProgramInfos _fact, AbstractAtomicBoolean _modal) {
        super(_fact, _modal);
//        absDialog = WindowCards.frame(_fact);
//        absDialog.addWindowListener(new ClosingFileFrameEvent(absDialog,_modal));
//        compo = _fact.getCompoFactory();
    }

//    public void setTitleDialog(WindowCardsInt _fenetre, String _title) {
//        absDialog.setIconImage(_fenetre.getCommonFrame().getImageIconFrame());
//        absDialog.setLocationRelativeTo(_fenetre.getCommonFrame());
//        absDialog.setTitle(_title);
////        initMessageName(_fenetre);
//    }

    public void setDialoguePresident(AbsBasicTreeMap<CardPresident, Integer> _playedCards, boolean _reversed, int _nbStacks, TranslationsLg _lg) {
        StringMap<String> messages_ = ContainerSingleImpl.file(_lg);
        int count_ = Suit.couleursOrdinaires().size() * _nbStacks;
        AbsPanel contentPane_ = getCompo().newPageBox();
        AbsPanel panelCards_ = getCompo().newGrid(0, 3);
        panelCards_.add(getCompo().newPlainLabel(messages_.getVal(MessagesGuiCards.MAIN_LEVEL)));
        panelCards_.add(getCompo().newPlainLabel(messages_.getVal(MessagesGuiCards.MAIN_NB_PLAYED)));
        panelCards_.add(getCompo().newPlainLabel(messages_.getVal(MessagesGuiCards.MAIN_NB_REM)));
        for (EntryCust<CardPresident, Integer> c: _playedCards.entryList()) {
            panelCards_.add(getCompo().newPlainLabel(Games.toString(c.getKey(),_lg)));
            long pl_ = c.getValue();
            panelCards_.add(getCompo().newPlainLabel(Long.toString(pl_)));
            panelCards_.add(getCompo().newPlainLabel(Long.toString(count_ - pl_)));
        }
        contentPane_.add(panelCards_);
        String message_ = messages_.getVal(MessagesGuiCards.MAIN_REVERSED);
        String value_;
        if (_reversed) {
            value_ = messages_.getVal(MessagesGuiCards.MAIN_YES);
        } else {
            value_ = messages_.getVal(MessagesGuiCards.MAIN_NO);
        }
        message_ = StringUtil.simpleStringsFormat(message_, value_);
        AbsPlainLabel reversed_ = getCompo().newPlainLabel(message_);
        contentPane_.add(reversed_);
        getAbsDialog().setContentPane(contentPane_);
        getAbsDialog().pack();
        getAbsDialog().setVisible(true);
    }
//
//    private void initMessageName(WindowCardsInt _parent) {
////        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
////        messages = WindowCards.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _parent.getLanguageKey(), absDialog.getAccessFile());
//        messages = ContainerSingleImpl.file(_parent.getFrames().currentLg());
//    }

}
