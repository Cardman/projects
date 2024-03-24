package aiki.gui;

import aiki.gui.events.LoadGameEventAiki;
import aiki.gui.events.LoadZipEvent;
import aiki.gui.events.SaveGameEventAiki;
import aiki.main.AikiFactory;
import aiki.sml.DefDataBaseStream;
import aiki.sml.IntDataBaseStream;
import code.gui.EnabledMenu;
import code.gui.GroupFrame;
import code.gui.GuiConstants;

public final class WindowAikiCore {

    private EnabledMenu zipLoad;

    private EnabledMenu folderLoad;

    private EnabledMenu gameLoad;

    private EnabledMenu gameSave;
    private final AikiFactory aikiFactory;
    private IntDataBaseStream dataBaseStream;
    public WindowAikiCore(AikiFactory _fact) {
        aikiFactory = _fact;
        setDataBaseStream(new DefDataBaseStream());
    }

    public AikiFactory getAikiFactory() {
        return aikiFactory;
    }

    public void fileMenu(EnabledMenu _file, WindowAikiInt _aiki, GroupFrame _gr) {
        zipLoad = _gr.getCompoFactory().newMenuItem();
        zipLoad.addActionListener(new LoadZipEvent(_aiki,false));
        zipLoad.setAccelerator(GuiConstants.VK_M, GuiConstants.CTRL_DOWN_MASK);
        _file.addMenuItem(zipLoad);
        folderLoad = _gr.getCompoFactory().newMenuItem();
        folderLoad.addActionListener(new LoadZipEvent(_aiki,true));
        folderLoad.setAccelerator(GuiConstants.VK_D, GuiConstants.CTRL_DOWN_MASK);
        _file.addMenuItem(folderLoad);
        gameLoad = _gr.getCompoFactory().newMenuItem();
        gameLoad.addActionListener(new LoadGameEventAiki(_aiki));
        gameLoad.setAccelerator(GuiConstants.VK_O, GuiConstants.CTRL_DOWN_MASK);
        _file.addMenuItem(gameLoad);
        gameSave = _gr.getCompoFactory().newMenuItem();
        gameSave.addActionListener(new SaveGameEventAiki(_aiki));
        gameSave.setAccelerator(GuiConstants.VK_S, GuiConstants.CTRL_DOWN_MASK);
        _file.addMenuItem(gameSave);
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

    public IntDataBaseStream getDataBaseStream() {
        return dataBaseStream;
    }

    public void setDataBaseStream(IntDataBaseStream _d) {
        this.dataBaseStream = _d;
    }
}
