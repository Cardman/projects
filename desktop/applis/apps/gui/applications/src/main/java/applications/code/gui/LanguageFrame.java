package applications.code.gui;



import code.gui.*;
import code.gui.events.AlwaysActionListenerAct;
import code.gui.events.QuittingEvent;
import code.gui.events.SetterLanguage;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.StreamLanguageUtil;

public final class LanguageFrame implements SetterLanguage, AbsQuit, AbsChangeLanguage {

    private static final String TITLE = " ";
    private String langue;

    private final String[] args;

    private final SoftApplicationCore soft;

    private final String dir;
//    private final AbsCommonFrame commonFrame;
    private final LanguageDialogButtons content;

    LanguageFrame(String _dir, String[] _args, SoftApplicationCore _soft, AbstractImage _icon) {
//        GuiBaseUtil.choose("", this, MessGuiGr.ms());
//        commonFrame = _soft.getFrames().getFrameFactory().newCommonFrame(_soft.getFrames(), null);
        content = new LanguageDialogButtons(_soft.getFrames(),null,new AlwaysActionListenerAct());
//        _soft.getFrames().getFrames().add(this);
        dir = _dir;
//        if (_icon != null) {
//            content.getCommonFrame().setIconImage(_icon);
//        }
        content.getCommonFrame().setIconImage(_icon);
        soft = _soft;
        args = _args;
        init(null,_soft.getFrames(), TITLE);
    }

    public AbsCommonFrame getCommonFrame() {
        return content.getCommonFrame();
    }

    @Override
    public void init(AbsCommonFrame _owner, AbstractProgramInfos _pr, String _title) {
//        commonFrame.setTitle(TITLE);
        content.init(_pr,TITLE,this);
//        commonFrame.setContentPane(content.getPanel());
//        commonFrame.setDefaultCloseOperation(GuiConstants.EXIT_ON_CLOSE);
        content.getCommonFrame().setLocationRelativeToNull();
        content.getCommonFrame().removeWindowListener(content.getCommonFrame().getWindowListenersDef().get(0));
        content.getCommonFrame().addWindowListener(new QuittingEvent(this));
//        commonFrame.setVisible(true);
//        commonFrame.pack();
    }

    @Override
    public void setLanguage(String _language) {
        langue = _language;
//        commonFrame.dispose();
        StreamLanguageUtil.saveLanguage(dir, _language,soft.getFrames());
        getCommonFrame().setVisible(false);
//        commonFrame.getPane().removeAll();
//        getFrames().getFrames().clear();
        soft.launchFile(args,langue);
    }

    @Override
    public String getLanguage() {
        return langue;
    }

//    @Override
//    public boolean canChangeLanguage() {
//        return false;
//    }

    @Override
    public void changeLanguage(String _language) {
        setLanguage(_language);
    }

//    @Override
//    public String getApplicationName() {
//        return "";
//    }

    @Override
    public void quit() {
        getCommonFrame().setVisible(false);
        GuiBaseUtil.removeAllListeners(getCommonFrame());
    }
}

