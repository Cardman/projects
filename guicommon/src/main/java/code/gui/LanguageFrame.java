package code.gui;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import code.gui.events.LanguageChoice;
import code.gui.events.SetterLanguage;
import code.util.StringMap;
import code.util.consts.Constants;

public class LanguageFrame extends JFrame implements SetterLanguage, Packable {

    private static final String TITLE = " ";

    private ButtonGroup group = new ButtonGroup();

    private String langue;

    private String[] args;

    private SoftApplicationCore soft;

    private String dir;

    LanguageFrame(String _dir, String[] _args, SoftApplicationCore _soft, Image _icon) {
        dir = _dir;
        if (_icon != null) {
            setIconImage(_icon);
        }
        init(_args, _soft);
    }

    private void init(String[] _args, SoftApplicationCore _soft) {
        soft = _soft;
        args = _args;
        setTitle(TITLE);
        JPanel panneau_ = new JPanel();
        panneau_.setLayout(new GridLayout(0,1));
        for (String l: Constants.getAvailableLanguages()) {
            JRadioButton radio_ = new JRadioButton(Constants.getDisplayLanguage(l));
            radio_.addMouseListener(new LanguageChoice(l, this));
            group.add(radio_);
            panneau_.add(radio_);
        }
        setContentPane(panneau_);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
        SetStyle.setupStyle(this);
    }

    @Override
    public void setLanguage(String _language) {
        langue = _language;
        dispose();
        SoftApplicationCore.saveLanguage(dir, _language);
        Constants.setSystemLanguage(_language);
        removeAll();
        StringMap<Object> file_ = soft.getFile(args);
        soft.launch(langue, file_);
    }

    @Override
    public String getLanguage() {
        return langue;
    }


}

