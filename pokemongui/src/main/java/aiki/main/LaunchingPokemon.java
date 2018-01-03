package aiki.main;
import java.awt.Image;
import java.io.File;

import aiki.DataBase;
import aiki.Resources;
import aiki.game.Game;
import aiki.game.params.LoadingGame;
import code.gui.LoadLanguage;
import code.gui.SoftApplication;
import code.gui.ThreadInvoker;
import code.gui.TopLeftFrame;
import code.serialize.SerializeXmlObject;
import code.serialize.exceptions.BadObjectException;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.ConstFiles;

public class LaunchingPokemon extends SoftApplication {

    private static final String TEMP_FOLDER = "pokemon";

    private static int _nbInstances_;

    public static void increment() {
        _nbInstances_++;
    }

    public static void decrement() {
        _nbInstances_--;
    }

    public static boolean alreadyLaunched() {
        return _nbInstances_ > 0;
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
        try {
//            String gameSave_ = _args[List.FIRST_INDEX];
//            gameSavePath_ = new File(gameSave_).getAbsolutePath().replace(StreamTextFile.SEPARATEUR_WIN, StreamTextFile.SEPARATEUR);
//            String xmlString_ = StreamTextFile.contentsOfFile(gameSave_);
//            Game game_ = (Game) SerializeXmlObject.newObjectFromXmlString(xmlString_);
            gameSavePath_ = _args.getKeys().first();
            Game game_ = (Game) _args.values().first();
            zip_ = game_.getZippedRom();
            if (zip_ == null) {
                zip_ = DataBase.EMPTY_STRING;
            }
            if (zip_.isEmpty() || new File(zip_).exists()) {
                fileConfig_ = StringList.concat(LaunchingPokemon.getTempFolderSl(),Resources.LOAD_CONFIG_FILE);
            }
        } catch (RuntimeException _0) {
        }
        if (fileConfig_.isEmpty()) {
            try {
//                fileConfig_ = _args[List.FIRST_INDEX];
                fileConfig_ = _args.getKeys().first();
//                String xmlString_ = StreamTextFile.contentsOfFile(fileConfig_);
//                param_ = (LoadingGame) SerializeXmlObject.newObjectFromXmlStringOrNull(xmlString_);
                param_ = (LoadingGame) _args.values().first();
                //pathConfig_ = new File(fileConfig_).getParentFile().getAbsolutePath().replace(StreamTextFile.B_SEPARATEUR, StreamTextFile.SEPARATEUR);
            } catch (RuntimeException _0) {
                param_ = null;
                fileConfig_ = StringList.concat(LaunchingPokemon.getTempFolderSl(),Resources.LOAD_CONFIG_FILE);
            }
        } else {
            try {
//                String xmlString_ = StreamTextFile.contentsOfFile(getFolderJarPath()+fileConfig_);
                String xmlString_ = StreamTextFile.contentsOfFile(StringList.concat(ConstFiles.getInitFolder(),fileConfig_));
                param_ = (LoadingGame) SerializeXmlObject.newObjectFromXmlString(xmlString_);
                param_.setLastSavedGame(gameSavePath_);
                param_.setLastRom(zip_);
            } catch (RuntimeException _0) {
                param_ = new LoadingGame();
                param_.setLastSavedGame(gameSavePath_);
                param_.setLastRom(zip_);
            }
        }
        if (param_ == null) {
            try {
//                String xmlString_ = StreamTextFile.contentsOfFile(fileConfig_);
//                param_ = (LoadingGame) SerializeXmlObject.newObjectFromXmlStringOrNull(xmlString_);
                param_ = (LoadingGame) StreamTextFile.loadObject(fileConfig_);
            } catch (RuntimeException _0) {
            }
        }
        if (param_ == null) {
            param_ = new LoadingGame();
        }
        //String path_ = getFolderJarPath();
        TopLeftFrame topLeft_;
        try {
            topLeft_ = loadCoords(getTempFolder(), Resources.COORDS);
            if (topLeft_ == null) {
                topLeft_ = new TopLeftFrame();
            }
        } catch(ClassCastException _0) {
            topLeft_ = new TopLeftFrame();
            _0.printStackTrace();
        } catch (BadObjectException _0) {
            topLeft_ = new TopLeftFrame();
            _0.printStackTrace();
        }
        //path_ = pathConfig_;
        String path_ = ConstFiles.getInitFolder();
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
        ThreadInvoker.invokeNow(new CreateMainWindow(param_, _args, path_, topLeft_));
    }

    public static Image getIcon() {
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
    protected Image getImageIcon() {
        return getIcon();
    }
}
