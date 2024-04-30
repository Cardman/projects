package aiki.sml;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.Game;
import aiki.game.fight.Fight;
import aiki.game.fight.Fighter;
import aiki.game.fight.Team;
import aiki.game.player.Player;
import aiki.map.pokemon.PokemonPlayer;
import code.maths.montecarlo.AbstractGenerator;
import code.scripts.messages.aiki.MessPkGr;
import code.sml.util.*;
import code.threads.AbstractAtomicBooleanCore;
import code.threads.AbstractAtomicIntegerCoreAdd;
import code.util.StringMap;

public final class GamesPk {
    public static final String PK = "pk";
    public static final String PK_DETAIL_CONTENT = "detail";
    public static final String HEROS_CONTENT = "heros";
    private GamesPk() {
    }
    public static TranslationsAppli initAppliTr(TranslationsLg _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getMapping().addEntry(PK, a_);
        return a_;
    }

    public static TranslationsAppli getAppliTr(TranslationsLg _lgs) {
        return _lgs.getMapping().getVal(PK);
    }

    public static void enTr(TranslationsAppli _lgs) {
        appendPkGameDetailContent(_lgs,MessagesRenderPkGameDetail.en());
        appendHerosContent(_lgs,MessagesRenderHeros.en());
    }

    public static void frTr(TranslationsAppli _lgs) {
        appendPkGameDetailContent(_lgs,MessagesRenderPkGameDetail.fr());
        appendHerosContent(_lgs,MessagesRenderHeros.fr());
    }

    public static void appendPkGameDetailContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(PK_DETAIL_CONTENT, _f);
    }

    public static void appendHerosContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(HEROS_CONTENT, _f);
    }

    public static TranslationsFile getPkGameDetailContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(PK_DETAIL_CONTENT);
    }

    public static TranslationsFile getHerosContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(HEROS_CONTENT);
    }

    public static void initMessages(DataBase _d, String _lg) {
        StringMap<String> map_ = MessPkGr.ms();
        _d.setMessagesPokemonPlayer(getMessagesFromLocaleClass(map_,Resources.MESSAGES_FOLDER, _lg, PokemonPlayer.POKEMON_PLAYER));
        _d.setMessagesPlayer(getMessagesFromLocaleClass(map_,Resources.MESSAGES_FOLDER, _lg, Player.PLAYER_ACCESS));
        _d.setMessagesFighter(getMessagesFromLocaleClass(map_,Resources.MESSAGES_FOLDER, _lg, Fighter.FIGHTER_ACCESS));
        _d.setMessagesTeam(getMessagesFromLocaleClass(map_,Resources.MESSAGES_FOLDER, _lg, Team.TEAM_ACCESS));
        _d.setMessagesFight(getMessagesFromLocaleClass(map_,Resources.MESSAGES_FOLDER, _lg, Fight.FIGHT_ACCESS));
        _d.setMessagesGame(getMessagesFromLocaleClass(map_,Resources.MESSAGES_FOLDER, _lg, Game.GAME_ACCESS));
    }

    private static StringMap<String> getMessagesFromLocaleClass(StringMap<String> _map, String _folder, String _loc, String _class) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(_folder, _loc, _class);
        String loadedResourcesMessages_ = _map.getVal(fileName_);
        return ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
    }

    // Load rom option
    public static void loadRomAndCheck(AbstractGenerator _gene, FacadeGame _f, String _fileName,
                                       StringMap<String> _files, AbstractAtomicIntegerCoreAdd _p, AbstractAtomicBooleanCore _l) {
        DataBase data_ = loadedRom(_gene,_f,_files,_p,_l);
        if (data_.isError()) {
            postLoad(_f, _fileName, _p, _l, null);
            return;
        }
        postLoad(_f, _fileName, _p, _l, data_);
    }

    static void postLoad(FacadeGame _f, String _fileName, AbstractAtomicIntegerCoreAdd _p, AbstractAtomicBooleanCore _l, DataBase _data) {
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
        _p.set(100);
        _f.setLoadedData(true);
    }

    // Load rom first
    private static DataBase loadedRom(AbstractGenerator _gene, FacadeGame _f, StringMap<String> _files, AbstractAtomicIntegerCoreAdd _p, AbstractAtomicBooleanCore _l) {
        DataBase data_ = new DataBase(_gene);
        data_.setLanguages(_f.getLanguages());
        data_.setDisplayLanguages(_f.getDisplayLanguages());
        _l.set(true);
        data_.setLanguage(_f.getLanguage());
        DocumentReaderAikiCoreUtil.loadRom(data_,_files,_p,_f.getSexList());
        if (!data_.getMap().validSavedLink()) {
            data_.setError(true);
            return data_;

        }
        data_.getMap().initializeLinks();
        return data_;
    }
}
