package aiki.sml;

import aiki.db.DataBase;
import aiki.facade.SexListInt;
import aiki.game.Game;
import code.expressionlanguage.filenames.AbstractNameValidating;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.PathsUtil;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.core.StringUtil;

public final class DefConfPkStream implements IntConfPkStream{
    private final AbstractProgramInfos programInfos;

    public DefConfPkStream(AbstractProgramInfos _pr) {
        this.programInfos = _pr;
    }

    @Override
    public LoadedGameConf load(String _tmpFolder,StringList _args, SexListInt _sexListInt) {
        LoadedGameConf loadedGameConf_ = new LoadedGameConf();
        String fileConfig_ = DataBase.EMPTY_STRING;
        LoadingGame param_ = null;
        String zip_ = DataBase.EMPTY_STRING;
        String gameSavePath_ = DataBase.EMPTY_STRING;
        //String pathConfig_ = DataBase.EMPTY_STRING;
        if (!_args.isEmpty()) {
            gameSavePath_ = _args.first();
            String file_ = StreamTextFile.contentsOfFile(gameSavePath_, programInfos.getFileCoreStream(), programInfos.getStreams());
            Game g_ = DocumentReaderAikiCoreUtil.getGame(file_,_sexListInt);
            loadedGameConf_.setGame(g_);
            if (g_ != null) {
                zip_ = StringUtil.nullToEmpty(g_.getZippedRom());
//                if (zip_ == null) {
//                    zip_ = DataBase.EMPTY_STRING;
//                }
                if (zip_.isEmpty() || programInfos.getFileCoreStream().newFile(zip_).exists()) {
                    fileConfig_ = StringUtil.concat(_tmpFolder,Resources.LOAD_CONFIG_FILE);
                }
            }
        }
        if (fileConfig_.isEmpty()) {
            if (!_args.isEmpty()) {
                String file_ = StreamTextFile.contentsOfFile(_args.first(), programInfos.getFileCoreStream(), programInfos.getStreams());
                LoadingGame loadingGame_ = DocumentReaderAikiCoreUtil.getLoadingGame(file_);
                fileConfig_ = _args.first();
                param_ = loadingGame_;
//                if (loadingGame_ != null) {
//                    fileConfig_ = _args.first();
//                    param_ = loadingGame_;
//                } else {
//                    fileConfig_ = StringUtil.concat(_tmpFolder, Resources.LOAD_CONFIG_FILE);
//                }
            } else {
                fileConfig_ = StringUtil.concat(_tmpFolder, Resources.LOAD_CONFIG_FILE);
            }
        } else {
            String xmlString_ = StreamTextFile.contentsOfFile(StringUtil.concat(StreamFolderFile.getCurrentPath(programInfos.getFileCoreStream()),fileConfig_), programInfos.getFileCoreStream(), programInfos.getStreams());
            param_ = DocumentReaderAikiCoreUtil.getLoadingGame(xmlString_);
            param_.setLastSavedGame(gameSavePath_);
            param_.setLastRom(zip_);
        }
        if (param_ == null) {
            param_ = defValue(fileConfig_);
        }
        loadedGameConf_.setLoadingGame(param_);
        return loadedGameConf_;
    }

    private LoadingGame defValue(String _fileConfig) {
        LoadingGame param_;
        String xmlString_ = StreamTextFile.contentsOfFile(_fileConfig, programInfos.getFileCoreStream(), programInfos.getStreams());
        param_ = DocumentReaderAikiCoreUtil.getLoadingGame(xmlString_);
        AbstractNameValidating def_ = programInfos.getValidator();
        if (!def_.okPath(PathsUtil.getRelativeRootPath(param_.getLastSavedGame(), programInfos.getFileCoreStream()),'/','\\')) {
            param_.setLastSavedGame("");
        }
        if (!def_.okPath(PathsUtil.getRelativeRootPath(param_.getLastRom(), programInfos.getFileCoreStream()),'/','\\')) {
            param_.setLastRom("");
        }
        return param_;
    }

    @Override
    public LoadingGame save(String _file, LoadingGame _conf) {
        StreamTextFile.saveTextFile(_file, DocumentWriterAikiCoreUtil.setLoadingGame(_conf),programInfos.getStreams());
        return _conf;
    }

}
