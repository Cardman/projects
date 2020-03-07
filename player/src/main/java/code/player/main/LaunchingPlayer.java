package code.player.main;


import code.gui.ConstFiles;
import code.gui.LoadLanguage;
import code.gui.SoftApplicationCore;
import code.gui.ThreadInvoker;
import code.player.gui.CreateMainWindow;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.StringMap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class LaunchingPlayer extends SoftApplicationCore {

    private static final String RESOURCES_FOLDER = "resources_player";
    private static final String ICON = "player.txt";
    private static final String TEMP_FOLDER = "playersongs";

    private static final AtomicInteger COUNT = new AtomicInteger();

    protected static void loadLaungage(String[] _args) {
        ThreadInvoker.invokeNow(new LoadLanguage(getTempFolder(), new LaunchingPlayer(), _args, null));
    }

    @Override
    public Object getObject(String _fileName) {
        return null;
    }

    @Override
    public void launchWithoutLanguage(String _language, StringMap<Object> _args) {
        launch(_language, _args);
    }

    @Override
    protected void launch(String _language, StringMap<Object> _args) {
        ThreadInvoker.invokeNow(new CreateMainWindow(_language,_args));
    }

    public static void increment() {
        COUNT.incrementAndGet();
    }

    public static void decrement() {
        COUNT.decrementAndGet();
    }

    public static boolean alreadyLaunched() {
        return COUNT.get() > 0;
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

    public static String getTempFolderSl() {
        return StringList.concat(getTempFolder(), StreamTextFile.SEPARATEUR);
    }

    public static String getTempFolder() {
        new File(StringList.concat(ConstFiles.getTmpUserFolderSl(),TEMP_FOLDER)).mkdirs();
        return StringList.concat(ConstFiles.getTmpUserFolderSl(),TEMP_FOLDER);
    }
}

