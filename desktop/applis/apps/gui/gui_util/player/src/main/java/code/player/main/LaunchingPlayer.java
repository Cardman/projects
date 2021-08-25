package code.player.main;


import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.LoadLanguageUtil;
import code.player.gui.CreateMainWindowPlayer;
import code.scripts.messages.gui.MessPlayerGr;
import code.stream.StreamTextFile;
import code.util.StringMap;
import code.util.core.StringUtil;

public class LaunchingPlayer extends AdvSoftApplicationCore {

    private static final String RESOURCES_FOLDER = "resources_player";
    private static final String ICON = "player.txt";
    private static final String TEMP_FOLDER = "playersongs";

    public LaunchingPlayer(AbstractProgramInfos _frames) {
        super(_frames);
    }

    protected static void loadLaungage(String[] _args, LaunchingPlayer _soft) {
        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
    }

    @Override
    public Object getObject(String _fileName) {
        return null;
    }

    @Override
    protected void launch(String _language, StringMap<Object> _args) {
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateMainWindowPlayer(_language,_args, getFrames()), getFrames());
    }

    @Override
    protected String getApplicationName() {
        return getMainWindowClass();
    }
    public static String getMainWindowClass() {
        return "musicplayer";
    }

    public static AbstractImage getIcon(AbstractImageFactory _fact) {
        return getImage(MessPlayerGr.ms().getVal(StringUtil.concat(RESOURCES_FOLDER, StreamTextFile.SEPARATEUR, ICON)), _fact);
    }

}

