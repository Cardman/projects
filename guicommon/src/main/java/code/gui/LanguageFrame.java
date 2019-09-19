package code.gui;

import javax.swing.WindowConstants;

import code.gui.events.LanguageChoice;
import code.gui.events.SetterLanguage;
import code.util.StringMap;
import code.util.consts.Constants;

import java.awt.image.BufferedImage;

public class LanguageFrame extends CommonFrame implements SetterLanguage {

    private static final String TITLE = " ";

    private CustButtonGroup group = new CustButtonGroup();

    private String langue;

    private String[] args;

    private SoftApplicationCore soft;

    private String dir;

    LanguageFrame(String _dir, String[] _args, SoftApplicationCore _soft, BufferedImage _icon) {
        super(Constants.getDefaultLanguage());
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
        Panel panneau_ = Panel.newGrid(0,1);
        for (String l: Constants.getAvailableLanguages()) {
            RadioButton radio_ = new RadioButton(Constants.getDisplayLanguage(l));
            radio_.addMouseListener(new LanguageChoice(l, this));
            group.add(radio_);
            panneau_.add(radio_);
        }
        setContentPane(panneau_);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }

    @Override
    public void setLanguage(String _language) {
        langue = _language;
        dispose();
        SoftApplicationCore.saveLanguage(dir, _language);
        getPane().removeAll();
        StringMap<Object> file_ = soft.getFile(args);
        soft.launch(langue, file_);
    }

    @Override
    public String getLanguage() {
        return langue;
    }

    @Override
    public BufferedImage getImageIconFrame() {
        return null;
    }


}

