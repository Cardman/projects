package code.player.main;


import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.LoadLanguageUtil;
import code.gui.initialize.ProgramInfos;
import code.player.gui.CreateMainWindow;
import code.util.StringMap;

import java.awt.image.BufferedImage;

public class LaunchingPlayer extends AdvSoftApplicationCore {

    private static final String RESOURCES_FOLDER = "resources_player";
    private static final String ICON = "player.txt";
    private static final String TEMP_FOLDER = "playersongs";

    public LaunchingPlayer() {
        this(new ProgramInfos());
    }

    public LaunchingPlayer(AbstractProgramInfos _frames) {
        super(_frames);
    }

    protected static void loadLaungage(String[] _args) {
        LoadLanguageUtil.loadLaungage(new LaunchingPlayer(), TEMP_FOLDER, _args);
    }

    @Override
    public Object getObject(String _fileName) {
        return null;
    }

    @Override
    protected void launch(String _language, StringMap<Object> _args) {
        ThreadInvoker.invokeNow(new CreateMainWindow(_language,_args, getFrames()));
    }

    @Override
    protected String getApplicationName() {
        return getMainWindowClass();
    }
    public static String getMainWindowClass() {
        return "musicplayer";
    }

    @Override
    protected BufferedImage getImageIcon() {
        return getIcon();
    }

    public static BufferedImage getIcon() {
        return getImage(RESOURCES_FOLDER,ICON);
    }

}

