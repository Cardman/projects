package code.gui;

import code.gui.events.AbsWindowListener;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.gui.MessGuiGr;
import code.sml.util.ResourcesMessagesUtil;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class FrameUtil {
    private static final String ACCESS = "gui.groupframe";
    private static final String TITLE = "title";
    private static final String MESSAGE = "message";

    private FrameUtil() {
    }

    public static boolean tryToReopen(String _applicationName, AbstractProgramInfos _list) {
        for (AbsGroupFrame g: _list.getFrames()) {
            if (StringUtil.quickEq(g.getApplicationName(), _applicationName)) {
                g.pack();
                g.getCommonFrame().setVisible(true);
                return true;
            }
        }
        return false;
    }

    public static void changeStaticLanguage(String _language, AbstractProgramInfos _list) {
        _list.getFrames().first().setMessages(group(_language));
        _list.getFrames().first().changeLanguage(_language);
    }

    public static StringMap<String> group(String _language) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, _language, ACCESS);
        String loadedResourcesMessages_ = MessGuiGr.ms().getVal(fileName_);
        return ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
    }

    public static void removeAllListeners(AbsGroupFrame _groupFrame) {
        for (AbsWindowListener l: _groupFrame.getCommonFrame().getWindowListeners()) {
            _groupFrame.getCommonFrame().removeWindowListener(l);
        }
    }

    public static void tryExit(AbsGroupFrame _groupFrame) {
        if(!_groupFrame.getCommonFrame().getFrames().getFrames().first().isOpened()) {
            _groupFrame.exit();
        }
    }

    public static void choose(String _lg, AbstractProgramInfos _list, AbsGroupFrame _this) {
        _list.getFrames().add(_this);
        AbsGroupFrame first_ = _list.getFrames().first();
        if (_list.getFrames().size() == 1) {
            _this.init(_list);
            first_.setMessages(group(_lg));
        } else {
            _this.setByFirst(first_);
        }
    }

    public static void showDialogError(AbsGroupFrame _group, int _errorMessage) {
        StringMap<String> messages_ = _group.getCommonFrame().getFrames().getFrames().first().getMessages();
        ConfirmDialog.showMessage(_group, messages_.getVal(MESSAGE), messages_.getVal(TITLE), _group.getCommonFrame().getFrames().getFrames().first().getCommonFrame().getLanguageKey(), _errorMessage);
    }

    public static void setLocationRelativeToWin(Iconifiable _i, AbsDialog _to) {
        if (_i instanceof AbsGroupFrame) {
            _to.setLocationRelativeTo((AbsGroupFrame) _i);
        } else if (_i instanceof AbsDialog) {
            _to.setLocationRelativeTo((AbsDialog) _i);
        }
    }
}
