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
    public static final String CONSULT_HOST = "host";
    public static final String HEROS_CONTENT = "heros";
    public static final String PAGINATOR_MODE = "search_mode";
    public static final String PAGINATOR_BUTTONS = "search_buttons";
    public static final String PAGINATOR_EGG = "pag_spec_egg";
    public static final String SEL_EGG = "sel_egg";
    public static final String PAGINATOR_PK = "pag_spec_pk";
    public static final String SEL_PK = "sel_pk";
    public static final String PAGINATOR_MV = "pag_spec_mv";
    public static final String SEL_MV = "sel_mv";
    public static final String PAGINATOR_IT = "pag_spec_it";
    public static final String SEL_IT = "sel_it";
    public static final String PAGINATOR_HEAL_IT = "pag_spec_heal_it";
    public static final String SEL_HEAL_IT = "sel_heal_it";
    public static final String SELECT_DIALOG = "select_dialog";
    public static final String FIGHT_ACTION = "fight_action";
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
        appendConsultHostContent(_lgs,MessagesRenderConsultHost.en());
        appendHerosContent(_lgs,MessagesRenderHeros.en());
        appendPaginatorContent(_lgs,MessagesRenderPaginatorSearchMode.en());
        appendPaginatorButtonsContent(_lgs,MessagesRenderPaginatorButtons.en());
        appendSelectDialogContent(_lgs,MessagesRenderPaginatorButtons.enSel());
        appendPaginatorEggContent(_lgs,MessagesRenderPaginatorEgg.en());
        appendPaginatorSelEggContent(_lgs,MessagesRenderPaginatorEgg.enTitle());
        appendPaginatorPkContent(_lgs,MessagesRenderPaginatorPk.en());
        appendPaginatorSelPkContent(_lgs,MessagesRenderPaginatorPk.enTitle());
        appendPaginatorMvContent(_lgs,MessagesRenderPaginatorMove.en());
        appendPaginatorSelMvContent(_lgs,MessagesRenderPaginatorMove.enTitle());
        appendPaginatorItContent(_lgs,MessagesRenderPaginatorItem.en());
        appendPaginatorSelItContent(_lgs,MessagesRenderPaginatorItem.enTitle());
        appendPaginatorHealItContent(_lgs,MessagesRenderPaginatorHealingItem.en());
        appendPaginatorSelHealItContent(_lgs,MessagesRenderPaginatorHealingItem.enTitle());
        appendFightActionContent(_lgs,MessagesRenderActionType.en());
    }

    public static void frTr(TranslationsAppli _lgs) {
        appendPkGameDetailContent(_lgs,MessagesRenderPkGameDetail.fr());
        appendConsultHostContent(_lgs,MessagesRenderConsultHost.fr());
        appendHerosContent(_lgs,MessagesRenderHeros.fr());
        appendPaginatorContent(_lgs,MessagesRenderPaginatorSearchMode.fr());
        appendPaginatorButtonsContent(_lgs,MessagesRenderPaginatorButtons.fr());
        appendSelectDialogContent(_lgs,MessagesRenderPaginatorButtons.frSel());
        appendPaginatorEggContent(_lgs,MessagesRenderPaginatorEgg.fr());
        appendPaginatorSelEggContent(_lgs,MessagesRenderPaginatorEgg.frTitle());
        appendPaginatorPkContent(_lgs,MessagesRenderPaginatorPk.fr());
        appendPaginatorSelPkContent(_lgs,MessagesRenderPaginatorPk.frTitle());
        appendPaginatorMvContent(_lgs,MessagesRenderPaginatorMove.fr());
        appendPaginatorSelMvContent(_lgs,MessagesRenderPaginatorMove.frTitle());
        appendPaginatorItContent(_lgs,MessagesRenderPaginatorItem.fr());
        appendPaginatorSelItContent(_lgs,MessagesRenderPaginatorItem.frTitle());
        appendPaginatorHealItContent(_lgs,MessagesRenderPaginatorHealingItem.fr());
        appendPaginatorSelHealItContent(_lgs,MessagesRenderPaginatorHealingItem.frTitle());
        appendFightActionContent(_lgs,MessagesRenderActionType.fr());
    }

    public static void appendPkGameDetailContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(PK_DETAIL_CONTENT, _f);
    }

    public static void appendConsultHostContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(CONSULT_HOST, _f);
    }

    public static void appendHerosContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(HEROS_CONTENT, _f);
    }

    public static void appendPaginatorContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(PAGINATOR_MODE, _f);
    }

    public static void appendPaginatorButtonsContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(PAGINATOR_BUTTONS, _f);
    }

    public static void appendSelectDialogContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(SELECT_DIALOG, _f);
    }

    public static void appendPaginatorEggContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(PAGINATOR_EGG, _f);
    }

    public static void appendPaginatorSelEggContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(SEL_EGG, _f);
    }

    public static void appendPaginatorPkContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(PAGINATOR_PK, _f);
    }

    public static void appendPaginatorSelPkContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(SEL_PK, _f);
    }

    public static void appendPaginatorMvContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(PAGINATOR_MV, _f);
    }

    public static void appendPaginatorSelMvContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(SEL_MV, _f);
    }

    public static void appendPaginatorItContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(PAGINATOR_IT, _f);
    }

    public static void appendPaginatorSelItContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(SEL_IT, _f);
    }

    public static void appendPaginatorHealItContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(PAGINATOR_HEAL_IT, _f);
    }

    public static void appendPaginatorSelHealItContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(SEL_HEAL_IT, _f);
    }

    public static void appendFightActionContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(FIGHT_ACTION, _f);
    }

    public static TranslationsFile getPkGameDetailContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(PK_DETAIL_CONTENT);
    }

    public static TranslationsFile getConsultHostContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(CONSULT_HOST);
    }

    public static TranslationsFile getHerosContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(HEROS_CONTENT);
    }

    public static TranslationsFile getPaginatorContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(PAGINATOR_MODE);
    }

    public static TranslationsFile getPaginatorButtonsContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(PAGINATOR_BUTTONS);
    }

    public static TranslationsFile getSelectDialogContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(SELECT_DIALOG);
    }

    public static TranslationsFile getPaginatorEggContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(PAGINATOR_EGG);
    }

    public static TranslationsFile getPaginatorSelEggContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(SEL_EGG);
    }

    public static TranslationsFile getPaginatorPkContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(PAGINATOR_PK);
    }

    public static TranslationsFile getPaginatorSelPkContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(SEL_PK);
    }

    public static TranslationsFile getPaginatorMvContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(PAGINATOR_MV);
    }

    public static TranslationsFile getPaginatorSelMvContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(SEL_MV);
    }

    public static TranslationsFile getPaginatorItContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(PAGINATOR_IT);
    }

    public static TranslationsFile getPaginatorSelItContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(SEL_IT);
    }

    public static TranslationsFile getPaginatorHealItContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(PAGINATOR_HEAL_IT);
    }

    public static TranslationsFile getPaginatorSelHealItContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(SEL_HEAL_IT);
    }

    public static TranslationsFile getFightActionContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(FIGHT_ACTION);
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
