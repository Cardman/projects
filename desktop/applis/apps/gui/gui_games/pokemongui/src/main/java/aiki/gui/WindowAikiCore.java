package aiki.gui;

import aiki.gui.events.LoadGameEventAiki;
import aiki.gui.events.LoadZipEvent;
import aiki.gui.events.SaveGameEventAiki;
import code.gui.AbsMenu;
import code.gui.AbsMenuItem;
import code.gui.GroupFrame;
import code.gui.GuiConstants;

public final class WindowAikiCore {

    private AbsMenuItem zipLoad;

    private AbsMenuItem folderLoad;

    private AbsMenuItem gameLoad;

    private AbsMenuItem gameSave;
    public void fileMenu(AbsMenu _file, WindowAikiInt _aiki, GroupFrame _gr) {
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

    public AbsMenuItem getFolderLoad() {
        return folderLoad;
    }

    public AbsMenuItem getGameLoad() {
        return gameLoad;
    }

    public AbsMenuItem getGameSave() {
        return gameSave;
    }

    public AbsMenuItem getZipLoad() {
        return zipLoad;
    }
}
