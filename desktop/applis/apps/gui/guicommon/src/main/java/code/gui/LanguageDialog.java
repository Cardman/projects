package code.gui;

import javax.swing.WindowConstants;

import code.gui.events.LanguageChoice;
import code.gui.events.SetterLanguage;
import code.util.consts.Constants;


public final class LanguageDialog extends Dialog implements SetterLanguage {

    private static final String NO_TITLE = " ";

    private CustButtonGroup groupe = new CustButtonGroup();
    private String langue;

    public static void setLanguageDialog(GroupFrame _owner) {
        initWithoutTitle(_owner);
    }

    private static void initWithoutTitle(GroupFrame _owner) {
        setLanguageDialog(_owner, NO_TITLE);
    }

    public static void setLanguageDialog(GroupFrame _owner, String _title) {
        _owner.getLanguageDialog().init(_owner, _title);
    }

    private void init(GroupFrame _owner, String _title) {
        setDialogIcon(_owner);
        setLocationRelativeTo(_owner);
        setTitle(_title);
        Panel panneau_ = Panel.newGrid(0,1);
        for (String l: Constants.getAvailableLanguages()) {
            RadioButton radio_ = new RadioButton(Constants.getDisplayLanguage(l));
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

    public static String getStaticLanguage(LanguageDialog _dialog) {
        return _dialog.getLanguage();
    }

    @Override
    public String getLanguage() {
        setVisible(true);
        return langue;
    }
}
