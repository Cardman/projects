package aiki.sml;

import code.sml.util.*;

public final class MessagesPkGame {
    public static final String PK = "pk";
    public static final String PK_DETAIL_CONTENT = "detail";
    public static final String SOFT_PARAMS = "params";
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
    public static final String WINDOW_PK = "win_pk";
    public static final String SCENE_PANEL = "scene_panel";
    public static final String BATTLE = "battle";
    public static final String GAME_ACCESS = "aiki.game.game";
    public static final String FIGHT_ACCESS = "aiki.game.fight.fight";
    public static final String TEAM_ACCESS = "aiki.game.fight.team";
    public static final String FIGHTER_ACCESS = "aiki.game.fight.fighter";
    public static final String PLAYER_ACCESS = "aiki.game.player.player";
    public static final String POKEMON_PLAYER = "aiki.map.pokemon.pokemonplayer";
    public static final String BASE_FILE = "sixty_four";
    public static final String TEMP_FOLDER = Translations.TEMP_FOLDER;
    public static final String DEFAULT_SAVE_GAME = "1";
    public static final String PORT_INI = "2";
    public static final String LOAD_CONFIG_FILE = "3";
    public static final String COORDS="4";
    public static final String VIDEO = "5";
    private MessagesPkGame() {
    }

    public static TranslationsAppli initAppliTr(TranslationsLg _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getMapping().addEntry(PK, a_);
        return a_;
    }

    public static TranslationsAppli getAppliTr(TranslationsLg _lgs) {
        return _lgs.getMapping().getVal(PK);
    }

    public static TranslationsAppli initAppliFilesTr(Translations _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getFiles().addEntry(PK, a_);
        return a_;
    }

    public static TranslationsAppli getAppliFilesTr(Translations _lgs) {
        return _lgs.getFiles().getVal(PK);
    }

    public static void enTr(TranslationsAppli _lgs) {
        appendPkGameDetailContent(_lgs,MessagesRenderPkGameDetail.en());
        appendSoftParamsContent(_lgs,MessagesRenderPkSoftParams.en());
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
        appendWindowPkContent(_lgs,MessagesRenderWindowPk.en());
        appendScenePanelContent(_lgs,MessagesRenderScenePanel.en());
        appendBattleContent(_lgs,MessagesRenderBattle.en());
        _lgs.getMapping().addEntry(GAME_ACCESS,MessagesCorePk.enGame());
        _lgs.getMapping().addEntry(FIGHT_ACCESS,MessagesCorePk.enFight());
        _lgs.getMapping().addEntry(TEAM_ACCESS,MessagesCorePk.enTeam());
        _lgs.getMapping().addEntry(FIGHTER_ACCESS,MessagesCorePk.enFighter());
        _lgs.getMapping().addEntry(PLAYER_ACCESS,MessagesCorePk.enPlayer());
        _lgs.getMapping().addEntry(POKEMON_PLAYER,MessagesCorePk.enPokemonPlayer());
    }

    public static void frTr(TranslationsAppli _lgs) {
        appendPkGameDetailContent(_lgs,MessagesRenderPkGameDetail.fr());
        appendSoftParamsContent(_lgs,MessagesRenderPkSoftParams.fr());
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
        appendWindowPkContent(_lgs,MessagesRenderWindowPk.fr());
        appendScenePanelContent(_lgs,MessagesRenderScenePanel.fr());
        appendBattleContent(_lgs,MessagesRenderBattle.fr());
        _lgs.getMapping().addEntry(GAME_ACCESS,MessagesCorePk.frGame());
        _lgs.getMapping().addEntry(FIGHT_ACCESS,MessagesCorePk.frFight());
        _lgs.getMapping().addEntry(TEAM_ACCESS,MessagesCorePk.frTeam());
        _lgs.getMapping().addEntry(FIGHTER_ACCESS,MessagesCorePk.frFighter());
        _lgs.getMapping().addEntry(PLAYER_ACCESS,MessagesCorePk.frPlayer());
        _lgs.getMapping().addEntry(POKEMON_PLAYER,MessagesCorePk.frPokemonPlayer());
    }

    public static void sys(TranslationsAppli _lgs) {
        _lgs.sys(mes());
        _lgs.getMapping().addEntry(BASE_FILE,MessagesCorePk.parsingBase());
    }
    public static TranslationsFile mes(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(TEMP_FOLDER,"pokemon");
        t_.add(DEFAULT_SAVE_GAME,"last_save.pkgame");
        t_.add(PORT_INI,"pokemon_port.ini");
        t_.add(LOAD_CONFIG_FILE,"loadconfig.pkconf");
        t_.add(COORDS,"pk.coords");
        t_.add(VIDEO,"video");
        return t_;
    }


    public static void appendPkGameDetailContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(PK_DETAIL_CONTENT, _f);
    }

    public static void appendSoftParamsContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(SOFT_PARAMS, _f);
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

    public static void appendWindowPkContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(WINDOW_PK, _f);
    }

    public static void appendScenePanelContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(SCENE_PANEL, _f);
    }

    public static void appendBattleContent(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(BATTLE, _f);
    }

    public static TranslationsFile getPkGameDetailContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(PK_DETAIL_CONTENT);
    }

    public static TranslationsFile getSoftParamsContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(SOFT_PARAMS);
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

    public static TranslationsFile getWindowPkContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(WINDOW_PK);
    }

    public static TranslationsFile getScenePanelContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(SCENE_PANEL);
    }

    public static TranslationsFile getBattleContentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(BATTLE);
    }
}
