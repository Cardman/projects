package aiki.sml;

import aiki.db.DataBase;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import code.maths.montecarlo.AbstractGenerator;
import code.sml.util.*;
import code.threads.AbstractAtomicBooleanCore;
import code.threads.AbstractAtomicIntegerCoreAdd;
import code.util.StringMap;

public final class GamesPk {
    private GamesPk() {
    }

    public static void initMessages(DataBase _d, TranslationsAppli _app) {
        _d.setMessagesPokemonPlayer(messages(_app, MessagesPkGame.POKEMON_PLAYER));
        _d.setMessagesPlayer(messages(_app, MessagesPkGame.PLAYER_ACCESS));
        _d.setMessagesFighter(messages(_app, MessagesPkGame.FIGHTER_ACCESS));
        _d.setMessagesTeam(messages(_app, MessagesPkGame.TEAM_ACCESS));
        _d.setMessagesFight(messages(_app, MessagesPkGame.FIGHT_ACCESS));
        _d.setMessagesGame(messages(_app, MessagesPkGame.GAME_ACCESS));
    }

    public static String baseEncode(Translations _msg) {
        return GamesPk.messages(MessagesPkGame.getAppliFilesTr(_msg),MessagesPkGame.BASE_FILE).getVal(MessagesDataBaseConstants.BASE_KEY);
    }
    public static StringMap<String> messages(TranslationsAppli _app, String _key) {
        TranslationsAppli a_;
        if (_app == null) {
            a_ = new TranslationsAppli();
        } else {
            a_ = _app;
        }
        TranslationsFile file_ = a_.getMapping().getVal(_key);
        if (file_ == null) {
            return new StringMap<String>();
        }
        return file_.getMapping();
    }

    // Load rom option
    public static void loadRomAndCheck(AbstractGenerator _gene, FacadeGame _f, String _fileName,
                                       StringMap<String> _files, AbstractAtomicIntegerCoreAdd _p, AbstractAtomicBooleanCore _l, String _baseParse) {
        DataBase data_ = loadedRom(_gene,_f,_files,_p,_l, _baseParse);
        postLoad(_f, _fileName, _p, _l, data_);
    }

    public static void postLoad(FacadeGame _f, String _fileName, AbstractAtomicIntegerCoreAdd _p, AbstractAtomicBooleanCore _l, DataBase _data) {
        if (!_l.get()) {
            return;
        }
        if (_data != null) {
            _data.patchPartialEvos();
            _data.validate(_p, _l, _f.getSexList());
        }
        postValidate(_f, _fileName, _p, _l, _data);
    }

    static void postValidate(FacadeGame _f, String _fileName, AbstractAtomicIntegerCoreAdd _p, AbstractAtomicBooleanCore _l, DataBase _data) {
        if (_data == null) {
            _f.setLoadedData(false);
            return;
        }
        if (!_l.get() || _data.isError()) {
            if (_data.isError()) {
                _f.setLoadedData(false);
            }
            return;
        }
        _data.initializeWildPokemon();
        _p.set(99);
        finish(_f, _fileName, _p, _l, _data);
    }

    static void finish(FacadeGame _f, String _fileName, AbstractAtomicIntegerCoreAdd _p, AbstractAtomicBooleanCore _l, DataBase _data) {
        if (!_l.get()) {
            return;
        }
        _f.setZipName(_fileName);
        DataBase old_ = _f.getData();
        if (old_ != null) {
            _data.setMessages(old_);
        }
        _f.setData(_data);
        _f.updateTrs();
        _p.set(100);
        _f.setLoadedData(true);
    }

    // Load rom first
    private static DataBase loadedRom(AbstractGenerator _gene, FacadeGame _f, StringMap<String> _files, AbstractAtomicIntegerCoreAdd _p, AbstractAtomicBooleanCore _l, String _baseParse) {
        DataBase data_ = new DataBase(_gene);
        MessagesDataBaseConstants.initEmpty(data_);
        data_.setLanguages(_f.getLanguages());
        data_.setDisplayLanguages(_f.getDisplayLanguages());
        _l.set(true);
        data_.setLanguage(_f.getLanguage());
        DocumentReaderAikiCoreUtil.loadRom(data_,_files,_p,_f.getSexList(), _baseParse);
        return tryInitLinks(data_);
    }

    public static DataBase tryInitLinks(DataBase _data) {
        if (!_data.getMap().validSavedLink()) {
            _data.setError(true);
            return null;

        }
        _data.getMap().initializeLinks();
        return _data;
    }
}
