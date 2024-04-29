package aiki.gui;

import aiki.gui.components.walk.DefTileRender;
import aiki.gui.components.walk.IntTileRender;
import aiki.gui.events.LoadGameEventAiki;
import aiki.gui.events.LoadZipEvent;
import aiki.gui.events.SaveGameEventAiki;
import aiki.main.AikiFactory;
import code.gui.EnabledMenu;
import code.gui.GroupFrame;
import code.gui.GuiConstants;
import code.gui.events.AbsActionListenerAct;

public final class WindowAikiCore {

    private EnabledMenu zipLoad;

    private EnabledMenu folderLoad;

    private EnabledMenu gameLoad;

    private EnabledMenu gameSave;
    private final AikiFactory aikiFactory;
    private IntTileRender tileRender;
    public WindowAikiCore(AikiFactory _fact) {
        aikiFactory = _fact;
        setTileRender(new DefTileRender());
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

}
