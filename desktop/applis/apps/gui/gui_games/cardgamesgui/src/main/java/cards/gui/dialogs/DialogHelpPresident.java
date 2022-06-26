package cards.gui.dialogs;

import cards.consts.CardChar;
import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.WindowCards;
import cards.president.enumerations.CardPresident;
import code.gui.AbsDialog;
import code.gui.AbsPanel;

import code.gui.AbsPlainLabel;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
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
    private final AbsCompoFactory compo;
    private StringMap<String> messages = new StringMap<String>();

    public DialogHelpPresident(AbstractProgramInfos _fact) {
        absDialog = _fact.getFrameFactory().newDialog();
        absDialog.setAccessFile(DIALOG_ACCESS);
        compo = _fact.getCompoFactory();
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

    public void setDialoguePresident(TreeMap<CardPresident, Byte> _playedCards, boolean _reversed, int _nbStacks, String _lg) {
        int count_ = Suit.couleursOrdinaires().size() * _nbStacks;
        AbsPanel contentPane_ = compo.newPageBox();
        AbsPanel panelCards_ = compo.newGrid(0, 3);
        panelCards_.add(compo.newPlainLabel(messages.getVal(LEVEL)));
        panelCards_.add(compo.newPlainLabel(messages.getVal(NB_PLAYED)));
        panelCards_.add(compo.newPlainLabel(messages.getVal(NB_REM)));
        for (CardPresident c: _playedCards.getKeys()) {
            CardChar char_ = c.getId().getNomFigure();
            if (char_ == CardChar.UNDEFINED) {
                panelCards_.add(compo.newPlainLabel(Long.toString(c.getId().getValeur())));
            } else {
                panelCards_.add(compo.newPlainLabel(Games.toString(char_,_lg)));
            }
            long pl_ = _playedCards.getVal(c);
            panelCards_.add(compo.newPlainLabel(Long.toString(pl_)));
            panelCards_.add(compo.newPlainLabel(Long.toString(count_ - pl_)));
        }
        contentPane_.add(panelCards_);
        String message_ = messages.getVal(REVERSED);
        String value_;
        if (_reversed) {
            value_ = messages.getVal(YES);
        } else {
            value_ = messages.getVal(NO);
        }
        message_ = StringUtil.simpleStringsFormat(message_, value_);
        AbsPlainLabel reversed_ = compo.newPlainLabel(message_);
        contentPane_.add(reversed_);
        absDialog.setContentPane(contentPane_);
        absDialog.pack();
        voir();
    }

    private void initMessageName(WindowCards _parent) {
//        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
        messages = WindowCards.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _parent.getLanguageKey(), absDialog.getAccessFile());
    }

}
