package aiki.gui;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.Game;
import aiki.gui.components.walk.DefTileRender;
import aiki.gui.components.walk.IntTileRender;
import aiki.gui.events.LoadGameEventAiki;
import aiki.gui.events.LoadZipEvent;
import aiki.gui.events.SaveGameEventAiki;
import aiki.main.AikiFactory;
import aiki.main.VideoLoading;
import aiki.sml.*;
import code.gui.*;
import code.gui.events.AbsActionListenerAct;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;
import code.util.core.StringUtil;

public final class WindowAikiCore {

    private EnabledMenu zipLoad;

    private EnabledMenu folderLoad;

    private EnabledMenu gameLoad;

    private EnabledMenu gameSave;
    private final AikiFactory aikiFactory;
    private IntTileRender tileRender;
    private final VideoLoading videoLoading = new VideoLoading();

    private final AbsPlainLabel lastSavedGameDate;

    private final FacadeGame facade;
    private String dateLastSaved = DataBase.EMPTY_STRING;
    private final AbstractProgramInfos api;
    private final ReportingFrame resultFile;

    private StringMap<String> messages = new StringMap<String>();
    private IntGameChecker gameCheck;

    public WindowAikiCore(AikiFactory _fact, AbstractProgramInfos _list, ReportingFrame _resFile) {
        setGameCheck(new DefGameChecker());
        aikiFactory = _fact;
        resultFile = _resFile;
        api = _list;
        lastSavedGameDate = _list.getCompoFactory().newPlainLabel("");
        setTileRender(new DefTileRender());
        facade = new FacadeGame();
        facade.setLanguages(_list.getLanguages());
        facade.setDisplayLanguages(_list.getDisplayLanguages());
        facade.setSimplyLanguage(_list.getLanguage());
    }

    public boolean showErrorMessageDialog(String _fileName) {
//        if (_fileName.isEmpty()) {
//            return false;
//        }
        resultFile.display(getMessages().getVal(MessagesRenderWindowPk.ERROR_LOADING),_fileName);
//        getFrames().getMessageDialogAbs().input(getCommonFrame(), _fileName, messages.getVal(ERROR_LOADING), GuiConstants.ERROR_MESSAGE);
        return true;
    }

    public void save(String _fileName) {
        Game game_ = facade.getGame();
        if (game_ == null) {
            return;
        }
        game_.setZippedRom(facade.getZipName());
        getAikiFactory().getGamePkStream().save(_fileName,game_);
        dateLastSaved = Clock.getDateTimeText(api.getThreadFactory());
        lastSavedGameDate.setText(StringUtil.simpleStringsFormat(MessagesPkGame.getWindowPkContentTr(MessagesPkGame.getAppliTr(api.currentLg())).getMapping().getVal(MessagesRenderWindowPk.LAST_SAVED_GAME), dateLastSaved));
    }
    public AikiFactory getAikiFactory() {
        return aikiFactory;
    }

    public void fileMenu(EnabledMenu _file, WindowAikiInt _aiki, GroupFrame _gr, AbsActionListenerAct _act) {
        zipLoad = _gr.getCompoFactory().newMenuItem();
        zipLoad.addActionListener(_act,new LoadZipEvent(_aiki,false));
        zipLoad.setAccelerator(GuiConstants.VK_M, GuiConstants.CTRL_DOWN_MASK);
        _file.addMenuItem(zipLoad);
        folderLoad = _gr.getCompoFactory().newMenuItem();
        folderLoad.addActionListener(_act,new LoadZipEvent(_aiki,true));
        folderLoad.setAccelerator(GuiConstants.VK_D, GuiConstants.CTRL_DOWN_MASK);
        _file.addMenuItem(folderLoad);
        gameLoad = _gr.getCompoFactory().newMenuItem();
        gameLoad.addActionListener(_act,new LoadGameEventAiki(_aiki));
        gameLoad.setAccelerator(GuiConstants.VK_O, GuiConstants.CTRL_DOWN_MASK);
        _file.addMenuItem(gameLoad);
        gameSave = _gr.getCompoFactory().newMenuItem();
        gameSave.addActionListener(_act,new SaveGameEventAiki(_aiki));
        gameSave.setAccelerator(GuiConstants.VK_S, GuiConstants.CTRL_DOWN_MASK);
        _file.addMenuItem(gameSave);
    }

    public IntTileRender getTileRender() {
        return tileRender;
    }

    public void setTileRender(IntTileRender _t) {
        this.tileRender = _t;
    }

    public IntGameChecker getGameCheck() {
        return gameCheck;
    }

    public void setGameCheck(IntGameChecker _g) {
        this.gameCheck = _g;
    }

    public String getDateLastSaved() {
        return dateLastSaved;
    }

    public StringMap<String> getMessages() {
        return messages;
    }

    public void setMessages(StringMap<String> _m) {
        this.messages = _m;
    }

    public void setDateLastSaved(String _d) {
        this.dateLastSaved = _d;
    }

    public FacadeGame getFacade() {
        return facade;
    }

    public AbsPlainLabel getLastSavedGameDate() {
        return lastSavedGameDate;
    }

    public EnabledMenu getFolderLoad() {
        return folderLoad;
    }

    public EnabledMenu getGameLoad() {
        return gameLoad;
    }

    public EnabledMenu getGameSave() {
        return gameSave;
    }

    public EnabledMenu getZipLoad() {
        return zipLoad;
    }

    public VideoLoading getVideoLoading() {
        return videoLoading;
    }
}
