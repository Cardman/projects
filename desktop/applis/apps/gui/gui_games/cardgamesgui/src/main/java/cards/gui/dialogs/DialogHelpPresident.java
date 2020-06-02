package cards.gui.dialogs;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import cards.consts.CardChar;
import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.MainWindow;
import cards.president.enumerations.CardPresident;
import code.gui.Dialog;
import code.gui.Panel;
import code.gui.TextLabel;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;

public final class DialogHelpPresident extends Dialog {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialoghelppresident";

    private static final DialogHelpPresident DIALOG = new DialogHelpPresident();
    private static final String LEVEL = "level";
    private static final String NB_PLAYED = "nbPlayed";
    private static final String NB_REM = "nbRem";
    private static final String REVERSED = "reversed";
    private static final String YES = "yes";
    private static final String NO = "no";
    private StringMap<String> messages = new StringMap<String>();

    private DialogHelpPresident() {
        setAccessFile(DIALOG_ACCESS);
    }

    private void voir() {
        setResizable(false);
        setVisible(true);
    }
    public static void setTitleDialog(MainWindow _fenetre,String _title) {
        DIALOG.setDialogIcon(_fenetre);
        DIALOG.setLocationRelativeTo(_fenetre);
        DIALOG.setTitle(_title);
        DIALOG.initMessageName(_fenetre);
    }

    public static void setDialoguePresident(TreeMap<CardPresident, Byte> _playedCards, boolean _reversed, int _nbStacks, String _lg) {
        int count_ = Suit.couleursOrdinaires().size() * _nbStacks;
        Panel contentPane_ = Panel.newPageBox();
        Panel panelCards_ = Panel.newGrid(0, 3);
        panelCards_.add(new TextLabel(DIALOG.messages.getVal(LEVEL)));
        panelCards_.add(new TextLabel(DIALOG.messages.getVal(NB_PLAYED)));
        panelCards_.add(new TextLabel(DIALOG.messages.getVal(NB_REM)));
        for (CardPresident c: _playedCards.getKeys()) {
            CardChar char_ = c.getNomFigure();
            if (char_ == CardChar.UNDEFINED) {
                panelCards_.add(new TextLabel(Byte.toString(c.valeur())));
            } else {
                panelCards_.add(new TextLabel(Games.toString(char_,_lg)));
            }
            byte pl_ = _playedCards.getVal(c);
            panelCards_.add(new TextLabel(Byte.toString(pl_)));
            panelCards_.add(new TextLabel(Integer.toString(count_ - pl_)));
        }
        contentPane_.add(panelCards_);
        String message_ = DIALOG.messages.getVal(REVERSED);
        String value_;
        if (_reversed) {
            value_ = DIALOG.messages.getVal(YES);
        } else {
            value_ = DIALOG.messages.getVal(NO);
        }
        message_ = StringList.simpleStringsFormat(message_, value_);
        TextLabel reversed_ = new TextLabel(message_);
        contentPane_.add(reversed_);
        DIALOG.setContentPane(contentPane_);
        DIALOG.pack();
        DIALOG.voir();
    }

    private void initMessageName(MainWindow _parent) {
//        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
        messages = getMessages(_parent,FileConst.FOLDER_MESSAGES_GUI);
    }
}
