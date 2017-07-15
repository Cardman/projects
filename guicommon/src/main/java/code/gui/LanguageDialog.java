package code.gui;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import code.gui.events.LanguageChoice;
import code.gui.events.SetterLanguage;
import code.util.consts.Constants;


public final class LanguageDialog extends Dialog implements SetterLanguage {

    private static final LanguageDialog DIALOG = new LanguageDialog();

    private static final String NO_TITLE = " ";

    private ButtonGroup groupe = new ButtonGroup();
    private String langue;

    private LanguageDialog(){
    }

    public static void setLanguageDialog(GroupFrame _owner) {
        init(_owner);
    }

    private static void init(GroupFrame _owner) {
        setLanguageDialog(_owner, NO_TITLE);
    }

    public static void setLanguageDialog(GroupFrame _owner, String _title) {
        DIALOG.init(_owner, _title);
    }

    private void init(GroupFrame _owner, String _title) {
        setDialogIcon(_owner);
        setLocationRelativeTo(_owner);
        setTitle(_title);
        JPanel panneau_ = new JPanel();
        panneau_.setLayout(new GridLayout(0,1));
        for (String l: Constants.getAvailableLanguages()) {
            JRadioButton radio_ = new JRadioButton(Constants.getDisplayLanguage(l));
            radio_.addMouseListener(new LanguageChoice(l, this));
            groupe.add(radio_);
            panneau_.add(radio_);
        }
        setContentPane(panneau_);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
    }

    @Override
    public void setLanguage(String _language) {
        langue = _language;
        closeWindow();
    }

    public static String getStaticLanguage() {
        return DIALOG.getLanguage();
    }

    @Override
    public String getLanguage() {
        setVisible(true);
        return langue;
    }
}
