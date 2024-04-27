package code.gui;


import code.gui.events.AbsActionListener;
import code.gui.events.ClosingChildFrameEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;


public final class LanguageDialogButtons implements AbsChildFrame {

    private final AbsCommonFrame commonFrame;
    private final LanguageComponentButtons content;

    private String langue;
    private final EnabledMenu lgMenu;

    public LanguageDialogButtons(AbstractProgramInfos _frameFactory, EnabledMenu _lgMenu) {
        lgMenu = _lgMenu;
        content = new LanguageComponentButtons(_frameFactory);
        commonFrame = _frameFactory.getFrameFactory().newCommonFrame("",_frameFactory,null);
        commonFrame.addWindowListener(new ClosingChildFrameEvent(this));
    }

    public void init(AbsCommonFrame _owner,AbstractProgramInfos _pr, String _title, AbsChangeLanguage _frame) {
        setDialogIcon(_owner);
        commonFrame.setLocationRelativeTo(_owner);
        commonFrame.setTitle(_title);
        content.init(_pr,this, _frame);
        commonFrame.setContentPane(content.getPanel());
//        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        commonFrame.pack();
        commonFrame.setVisible(true);
        enable(lgMenu, false);
    }

    @Override
    public void setDialogIcon(AbsCommonFrame _group) {
        commonFrame.setIconImage(_group.getImageIconFrame());
    }

    public LanguageComponentButtons getContent() {
        return content;
    }

    @Override
    public void closeWindow() {
        commonFrame.setVisible(false);
        enable(lgMenu, true);
    }

    public static void enable(AbsCustComponent _lgMenu, boolean _value) {
        if (_lgMenu != null) {
            _lgMenu.setEnabled(_value);
        }
    }

    public void translate(String _txt) {
        translate(lgMenu, _txt);
    }

    public static void translate(TranslatableComponent _lgMenu, String _txt) {
        if (_lgMenu != null) {
            _lgMenu.setText(_txt);
        }
    }

    public static boolean translate(TranslatableComponent _lgMenu, StringMap<String> _trs, String _key) {
        if (_lgMenu != null) {
            _lgMenu.setText(_trs.getVal(_key));
            return true;
        }
        return false;
    }

    public void commonParametersMenu(EnabledMenu _params, AbsActionListener _listener, int _key, int _mod) {
        if (lgMenu != null) {
            lgMenu.addActionListener(_listener);
            lgMenu.setAccelerator(_key, _mod);
            _params.addMenuItem(lgMenu);
        }
    }

    public void setLanguage(String _language) {
        langue = _language;
    }

    @Override
    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    public String getLanguage() {
        return langue;
    }
}
