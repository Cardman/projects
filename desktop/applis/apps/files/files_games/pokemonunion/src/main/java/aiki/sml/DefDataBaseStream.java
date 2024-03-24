package aiki.sml;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.StreamFolderFile;
import code.threads.AbstractAtomicBooleanCore;
import code.threads.AbstractAtomicIntegerCoreAdd;
import code.threads.AbstractFutureParam;
import code.util.StringMap;

public final class DefDataBaseStream implements IntDataBaseStream {

    @Override
    public AbstractAtomicBooleanCore loadRomAndCheck(AbstractProgramInfos _api, AbstractFutureParam<DataBase> _db, FacadeGame _f, String _fileName, AbstractAtomicIntegerCoreAdd _p, AbstractAtomicBooleanCore _l) {
        StringMap<String> files_ = StreamFolderFile.getFiles(_fileName,_api.getFileCoreStream(),_api.getStreams());
        return loadRomAndCheck(_api, _db, _f, _fileName, _p, _l, files_);
    }

    public AbstractAtomicBooleanCore loadRomAndCheck(AbstractProgramInfos _api, AbstractFutureParam<DataBase> _db, FacadeGame _f, String _fileName, AbstractAtomicIntegerCoreAdd _p, AbstractAtomicBooleanCore _l, StringMap<String> _files) {
        GamesPk.loadRomAndCheck(_api.getGenerator(),_f,_fileName,_files,_p,_l);
        if (!_f.isLoadedData()) {
            FacadeGame.postLoad(_f, _db.attendreResultat());
            _p.set(100);
            _l.set(true);
        }
        return _l;
    }

}
