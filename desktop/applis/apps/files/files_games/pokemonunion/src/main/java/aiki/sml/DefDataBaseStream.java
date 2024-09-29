package aiki.sml;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.StreamBinaryFile;
import code.stream.StreamFolderFile;
import code.stream.core.ContentTime;
import code.threads.AbstractAtomicBooleanCore;
import code.threads.AbstractAtomicIntegerCoreAdd;
import code.threads.AbstractFutureParam;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class DefDataBaseStream implements IntDataBaseStream {

    @Override
    public StringMap<ContentTime> exportRom(AbstractProgramInfos _api, FacadeGame _f, LoadingGame _loadingGame) {
        String export_ = _loadingGame.getExport();
        if (!export_.isEmpty()) {
            String path_ = _api.getFileCoreStream().newFile(export_).getAbsolutePath();
            path_ = StringUtil.replaceBackSlash(path_);
            StringMap<String> textFiles_ = DocumentWriterAikiCoreUtil.getTextFiles(_f.getData());
            StringMap<ContentTime> meta_ = new StringMap<ContentTime>();
            for (EntryCust<String,String> e: textFiles_.entryList()) {
                meta_.addEntry(e.getKey(),new ContentTime(StringUtil.encode(e.getValue()),_api.getThreadFactory().millis()));
            }
            StreamFolderFile.makeParent(path_,_api.getFileCoreStream());
            StreamBinaryFile.writeFile(path_,_api.getZipFact().zipBinFiles(meta_),_api.getStreams());
            return meta_;
        }
        return new StringMap<ContentTime>();
    }

    @Override
    public AbstractAtomicBooleanCore loadRomAndCheck(AbstractProgramInfos _api, AbstractFutureParam<DataBase> _db, FacadeGame _f, String _fileName, AbstractAtomicIntegerCoreAdd _p, AbstractAtomicBooleanCore _l) {
        StringMap<String> files_ = StreamFolderFile.getFiles(_fileName,_api.getFileCoreStream(),_api.getStreams());
        GamesPk.loadRomAndCheck(_api.getGenerator(),_f,_fileName,files_,_p,_l, GamesPk.baseEncode(_api.getTranslations()));
        if (!_f.isLoadedData()) {
            FacadeGame.postLoad(_f, _db.attendreResultat());
            _p.set(100);
            _l.set(true);
        }
        return _l;
    }

}
