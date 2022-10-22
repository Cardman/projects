package aiki.main;

import aiki.db.DataBase;
import aiki.facade.SexListImpl;
import aiki.sml.Resources;
import aiki.game.Game;
import aiki.sml.LoadingGame;
import aiki.sml.DocumentReaderAikiCoreUtil;
import code.expressionlanguage.filenames.AbstractNameValidating;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.LoadLanguageUtil;
import code.scripts.messages.gui.MessPkVideoGr;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public class LaunchingPokemon extends AdvSoftApplicationCore {

    private static final String TEMP_FOLDER = "pokemon";

    private final AikiFactory aikiFactory;

    public LaunchingPokemon(AbstractProgramInfos _frames, AikiFactory _aikiFactory) {
        super(_frames);
        aikiFactory = _aikiFactory;
    }

    protected static void loadLaungage(String[] _args, LaunchingPokemon _soft) {
        //loadLaungage(_args, _icon_);
//        ThreadInvoker.invokeNow(new LoadLanguage(getTempFolder(), this, _args, getIcon()));
        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
    }

    @Override
    protected void launch(String _language, String[] _args) {
        String fileConfig_ = DataBase.EMPTY_STRING;
        LoadingGame param_ = null;
        String zip_ = DataBase.EMPTY_STRING;
        String gameSavePath_ = DataBase.EMPTY_STRING;
        StringList args_ = getFile(_args);
        //String pathConfig_ = DataBase.EMPTY_STRING;
        if (!args_.isEmpty()) {
            gameSavePath_ = args_.first();
            String file_ = StreamTextFile.contentsOfFile(gameSavePath_, getFrames().getFileCoreStream(), getFrames().getStreams());
            Game g_ = DocumentReaderAikiCoreUtil.getGame(file_,new SexListImpl());
            if (g_ != null) {
                zip_ = g_.getZippedRom();
                if (zip_ == null) {
                    zip_ = DataBase.EMPTY_STRING;
                }
                if (zip_.isEmpty() || getFrames().getFileCoreStream().newFile(zip_).exists()) {
                    fileConfig_ = StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE);
                }
            }
        }
        if (fileConfig_.isEmpty()) {
            if (!args_.isEmpty()) {
                String file_ = StreamTextFile.contentsOfFile(args_.first(), getFrames().getFileCoreStream(), getFrames().getStreams());
                LoadingGame loadingGame_ = DocumentReaderAikiCoreUtil.getLoadingGame(file_);
                if (loadingGame_ != null) {
                    fileConfig_ = args_.first();
                    param_ = loadingGame_;
                } else {
                    fileConfig_ = StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()), Resources.LOAD_CONFIG_FILE);
                }
            } else {
                fileConfig_ = StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()), Resources.LOAD_CONFIG_FILE);
            }
        } else {
            String xmlString_ = StreamTextFile.contentsOfFile(StringUtil.concat(StreamFolderFile.getCurrentPath(getFrames().getFileCoreStream()),fileConfig_), getFrames().getFileCoreStream(), getFrames().getStreams());
            param_ = DocumentReaderAikiCoreUtil.getLoadingGame(xmlString_);
            param_.setLastSavedGame(gameSavePath_);
            param_.setLastRom(zip_);
        }
        if (param_ == null) {
            String xmlString_ = StreamTextFile.contentsOfFile(fileConfig_, getFrames().getFileCoreStream(), getFrames().getStreams());
            param_ = DocumentReaderAikiCoreUtil.getLoadingGame(xmlString_);
            AbstractNameValidating def_ = getFrames().getValidator();
            if (!def_.okPath(StreamFolderFile.getRelativeRootPath(param_.getLastSavedGame(), getFrames().getFileCoreStream()),'/','\\')) {
                param_.setLastSavedGame("");
            }
            if (!def_.okPath(StreamFolderFile.getRelativeRootPath(param_.getLastRom(), getFrames().getFileCoreStream()),'/','\\')) {
                param_.setLastRom("");
            }
        }
        //String path_ = getFolderJarPath();
        TopLeftFrame topLeft_ = loadCoords(getTempFolder(getFrames()), Resources.COORDS, getFrames().getFileCoreStream(), getFrames().getStreams());
        //path_ = pathConfig_;
        String path_ = StreamFolderFile.getCurrentPath(getFrames().getFileCoreStream());
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
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateMainWindowAiki(param_, args_, path_, topLeft_, _language, getFrames(), aikiFactory), getFrames());
    }

    protected StringList getFile(String[] _args) {
        StringList files_ = new StringList();
        if (_args.length > 0) {
            String fileName_ = getFrames().getFileCoreStream().newFile(_args[0]).getAbsolutePath();
            fileName_ = StringUtil.replaceBackSlash(fileName_);
            files_.add(fileName_);
        }
        return files_;
    }

    public static AbstractImage getIcon(AbstractImageFactory _fact) {
        return getImage(MessPkVideoGr.ms().getVal(StringUtil.concat(Resources.RESOURCES_FOLDER, StreamTextFile.SEPARATEUR, Resources.ICON_TXT)), _fact);
    }

    public static String getTempFolderSl(AbstractProgramInfos _tmpUserFolderSl) {
        return StringUtil.concat(getTempFolder(_tmpUserFolderSl), StreamTextFile.SEPARATEUR);
    }

    public static String getTempFolder(AbstractProgramInfos _tmpUserFolderSl) {
        return getTempFolder(_tmpUserFolderSl,TEMP_FOLDER);
    }

    @Override
    protected String getApplicationName() {
        return getMainWindowClass();
    }
    public static String getMainWindowClass() {
        return "aiki";
    }

}
