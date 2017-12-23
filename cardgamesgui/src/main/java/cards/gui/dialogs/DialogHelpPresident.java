package cards.gui.dialogs;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cards.consts.CardChar;
import cards.consts.Suit;
import cards.gui.MainWindow;
import cards.president.enumerations.CardPresident;
import code.gui.Dialog;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.consts.Constants;

public final class DialogHelpPresident extends Dialog {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.DialogHelpPresident";

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
        DIALOG.initMessageName();
    }

    public static void setDialoguePresident(TreeMap<CardPresident, Byte> _playedCards, boolean _reversed, int _nbStacks) {
        int count_ = Suit.couleursOrdinaires().size() * _nbStacks;
        JPanel contentPane_ = new JPanel();
        contentPane_.setLayout(new BoxLayout(contentPane_, BoxLayout.PAGE_AXIS));
        JPanel panelCards_ = new JPanel();
        panelCards_.setLayout(new GridLayout(0, 3));
        panelCards_.add(new JLabel(DIALOG.messages.getVal(LEVEL)));
        panelCards_.add(new JLabel(DIALOG.messages.getVal(NB_PLAYED)));
        panelCards_.add(new JLabel(DIALOG.messages.getVal(NB_REM)));
        for (CardPresident c: _playedCards.getKeys()) {
            CardChar char_ = c.getNomFigure();
            if (char_ == CardChar.UNDEFINED) {
                panelCards_.add(new JLabel(Byte.toString(c.valeur())));
            } else {
                panelCards_.add(new JLabel(char_.toString(Constants.getLanguage())));
            }
            byte pl_ = _playedCards.getVal(c);
            panelCards_.add(new JLabel(Byte.toString(pl_)));
            panelCards_.add(new JLabel(Integer.toString(count_ - pl_)));
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
        JLabel reversed_ = new JLabel(message_);
        contentPane_.add(reversed_);
        DIALOG.setContentPane(contentPane_);
        DIALOG.pack();
        DIALOG.voir();
    }

    private void initMessageName() {
//        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
        messages = getMessages(FileConst.FOLDER_MESSAGES_GUI);
    }
}
