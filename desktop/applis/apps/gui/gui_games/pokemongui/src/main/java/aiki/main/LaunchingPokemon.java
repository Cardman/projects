package aiki.main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

import aiki.db.DataBase;
import aiki.sml.Resources;
import aiki.game.Game;
import aiki.sml.LoadingGame;
import aiki.sml.DocumentReaderAikiCoreUtil;
import code.gui.ConstFiles;
import code.gui.LoadLanguage;
import code.gui.SoftApplicationCore;
import code.gui.ThreadInvoker;
import code.gui.TopLeftFrame;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.StringMap;

public class LaunchingPokemon extends SoftApplicationCore {

    private static final String TEMP_FOLDER = "pokemon";

    private static final AtomicInteger COUNT = new AtomicInteger();

    public static void increment() {
        COUNT.incrementAndGet();
    }

    public static void decrement() {
        COUNT.decrementAndGet();
    }

    public static boolean alreadyLaunched() {
        return COUNT.get() > 0;
    }

    protected static void loadLaungage(String[] _args) {
        //loadLaungage(_args, _icon_);
//        ThreadInvoker.invokeNow(new LoadLanguage(getTempFolder(), this, _args, getIcon()));
        ThreadInvoker.invokeNow(new LoadLanguage(getTempFolder(), new LaunchingPokemon(), _args, null));
    }

    @Override
    protected void launch(String _language, StringMap<Object> _args) {
        increment();
        String fileConfig_ = DataBase.EMPTY_STRING;
        LoadingGame param_ = null;
        String zip_ = DataBase.EMPTY_STRING;
        String gameSavePath_ = DataBase.EMPTY_STRING;
        //String pathConfig_ = DataBase.EMPTY_STRING;
        if (!_args.isEmpty()) {
            gameSavePath_ = _args.getKeys().first();
            if (_args.values().first() instanceof Game) {
                Game game_ = (Game) _args.values().first();
                zip_ = game_.getZippedRom();
                if (zip_ == null) {
                    zip_ = DataBase.EMPTY_STRING;
                }
                if (zip_.isEmpty() || new File(zip_).exists()) {
                    fileConfig_ = StringList.concat(LaunchingPokemon.getTempFolderSl(),Resources.LOAD_CONFIG_FILE);
                }
            }
        }
        if (fileConfig_.isEmpty()) {
            if (!_args.isEmpty() && _args.values().first() instanceof LoadingGame) {
                fileConfig_ = _args.getKeys().first();
                param_ = (LoadingGame) _args.values().first();
            } else {
                fileConfig_ = StringList.concat(LaunchingPokemon.getTempFolderSl(),Resources.LOAD_CONFIG_FILE);
            }
        } else {
            String xmlString_ = StreamTextFile.contentsOfFile(StringList.concat(StreamFolderFile.getCurrentPath(),fileConfig_));
            param_ = DocumentReaderAikiCoreUtil.getLoadingGame(xmlString_);
            param_.setLastSavedGame(gameSavePath_);
            param_.setLastRom(zip_);
        }
        if (param_ == null) {
            String xmlString_ = StreamTextFile.contentsOfFile(fileConfig_);
            param_ = DocumentReaderAikiCoreUtil.getLoadingGame(xmlString_);
        }
        //String path_ = getFolderJarPath();
        TopLeftFrame topLeft_ = loadCoords(getTempFolder(), Resources.COORDS);
        //path_ = pathConfig_;
        String path_ = StreamFolderFile.getCurrentPath();
//        if (!_args.isEmpty()) {
//            //open with
////            CreateMainWindow create_ = new CreateMainWindow(param_, true, path_, topLeft_);
////            create_.start();
//            ThreadInvoker.invokeNow(new CreateMainWindow(param_, _args, path_, topLeft_));
//            return;
//        }
//        path_ = Constants.getInitFolder();
//        CreateMainWindow create_ = new CreateMainWindow(param_, false, path_, topLeft_);
//        create_.start();
        ThreadInvoker.invokeNow(new CreateMainWindow(param_, _args, path_, topLeft_, _language));
    }

    public static BufferedImage getIcon() {
        return getImage(Resources.RESOURCES_FOLDER, Resources.ICON_TXT);
    }

    @Override
    public void launchWithoutLanguage(String _language, StringMap<Object> _args) {
        launch(_language, _args);
    }

    public static String getTempFolderSl() {
        return StringList.concat(getTempFolder(), StreamTextFile.SEPARATEUR);
    }

    public static String getTempFolder() {
        new File(StringList.concat(ConstFiles.getTmpUserFolderSl(),TEMP_FOLDER)).mkdirs();
        return StringList.concat(ConstFiles.getTmpUserFolderSl(),TEMP_FOLDER);
    }

    public static String getMainWindowClass() {
        return "aiki";
    }

    @Override
    protected BufferedImage getImageIcon() {
        return getIcon();
    }

    @Override
    public Object getObject(String _fileName) {
        String file_ = StreamTextFile.contentsOfFile(_fileName);
        Object o_ = DocumentReaderAikiCoreUtil.getGame(file_);
        if (o_ != null) {
            return o_;
        }
        return DocumentReaderAikiCoreUtil.getLoadingGame(file_);
    }
}
