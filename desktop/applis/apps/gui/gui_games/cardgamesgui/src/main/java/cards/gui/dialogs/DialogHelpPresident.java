package cards.gui.dialogs;

import cards.consts.CardChar;
import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.WindowCards;
import cards.president.enumerations.CardPresident;
import code.gui.AbsDialog;
import code.gui.AbsPanel;
import code.gui.Panel;
import code.gui.TextLabel;
import code.gui.initialize.AbsFrameFactory;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.core.StringUtil;

public final class DialogHelpPresident {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialoghelppresident";

    private static final String LEVEL = "level";
    private static final String NB_PLAYED = "nbPlayed";
    private static final String NB_REM = "nbRem";
    private static final String REVERSED = "reversed";
    private static final String YES = "yes";
    private static final String NO = "no";
    private final AbsDialog absDialog;
    private StringMap<String> messages = new StringMap<String>();

    public DialogHelpPresident(AbsFrameFactory _frameFactory) {
        absDialog = _frameFactory.newDialog();
        absDialog.setAccessFile(DIALOG_ACCESS);
    }

    private void voir() {
        absDialog.setResizable(false);
        absDialog.setVisible(true);
    }
    public static void setTitleDialog(WindowCards _fenetre, String _title) {
        _fenetre.getDialogHelpPresident().absDialog.setDialogIcon(_fenetre.getImageFactory(),_fenetre);
        _fenetre.getDialogHelpPresident().absDialog.setLocationRelativeTo(_fenetre);
        _fenetre.getDialogHelpPresident().absDialog.setTitle(_title);
        _fenetre.getDialogHelpPresident().initMessageName(_fenetre);
    }

    public static void setDialoguePresident(TreeMap<CardPresident, Byte> _playedCards, boolean _reversed, int _nbStacks, String _lg, DialogHelpPresident _dialog) {
        int count_ = Suit.couleursOrdinaires().size() * _nbStacks;
        AbsPanel contentPane_ = Panel.newPageBox();
        AbsPanel panelCards_ = Panel.newGrid(0, 3);
        panelCards_.add(new TextLabel(_dialog.messages.getVal(LEVEL)));
        panelCards_.add(new TextLabel(_dialog.messages.getVal(NB_PLAYED)));
        panelCards_.add(new TextLabel(_dialog.messages.getVal(NB_REM)));
        for (CardPresident c: _playedCards.getKeys()) {
            CardChar char_ = c.getNomFigure();
            if (char_ == CardChar.UNDEFINED) {
                panelCards_.add(new TextLabel(Long.toString(c.valeur())));
            } else {
                panelCards_.add(new TextLabel(Games.toString(char_,_lg)));
            }
            long pl_ = _playedCards.getVal(c);
            panelCards_.add(new TextLabel(Long.toString(pl_)));
            panelCards_.add(new TextLabel(Long.toString(count_ - pl_)));
        }
        contentPane_.add(panelCards_);
        String message_ = _dialog.messages.getVal(REVERSED);
        String value_;
        if (_reversed) {
            value_ = _dialog.messages.getVal(YES);
        } else {
            value_ = _dialog.messages.getVal(NO);
        }
        message_ = StringUtil.simpleStringsFormat(message_, value_);
        TextLabel reversed_ = new TextLabel(message_);
        contentPane_.add(reversed_);
        _dialog.absDialog.setContentPane(contentPane_);
        _dialog.absDialog.pack();
        _dialog.voir();
    }

    private void initMessageName(WindowCards _parent) {
//        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
        messages = WindowCards.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _parent.getLanguageKey(), absDialog.getAccessFile());
    }

}
