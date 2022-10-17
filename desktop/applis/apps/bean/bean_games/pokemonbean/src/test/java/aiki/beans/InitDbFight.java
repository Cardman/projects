package aiki.beans;

import aiki.beans.fight.*;
import aiki.beans.game.InitDbPkBean;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.NameLevel;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.util.LevelMove;
import aiki.fight.util.ListEffectCombo;
import aiki.fight.util.ListEffectCombos;
import aiki.game.Game;
import aiki.game.fight.ActivityOfMove;
import aiki.game.fight.Fight;
import aiki.game.fight.FightFacade;
import aiki.game.fight.Team;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.game.player.Player;
import aiki.instances.Instances;
import aiki.map.DataMap;
import aiki.map.characters.TrainerLeague;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.Gender;
import aiki.util.CoordsLists;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.maths.Rate;
import code.scripts.confs.PkScriptPagesInit;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class InitDbFight extends InitDbBean {

    protected static final String M_TEAM_TR = "team_move";
    protected static final String M_TEAM_SEND_TR = "team_move_send";
    protected static final String M_USE_TR = "use_move";
    protected static final String M_STACK_TR = "stack_move";
    protected static final String M_ANT_TR = "ant_move";
    protected static final String NO_TEAM = "no_team";
    protected static final String M_TEAM = "M_TEAM";
    protected static final String M_TEAM_SEND = "M_TEAM_SEND";
    protected static final String M_USE = "M_USE";
    protected static final String M_STACK = "M_STACK";
    protected static final String M_ANT = "M_ANT";
    protected static final String PIKA_2 = "PIKACHU2";
    protected static final String PIKA_TR_2 = "PIKACHU_TR2";
    static final String NICKNAME = "CARDTEAM";
    private static final String FIGHT="fight";
    private static final String M_CLICK_FOE="clickFoe";
    private static final String M_CLICK_PLAYER="clickPlayer";

    public static Struct beanFight(String _language, FacadeGame _dataBase) {
        return beanFight(new PkFight(),_language,_dataBase);
    }

    public static Struct beanFight(PkFight _stds,String _language, FacadeGame _dataBase) {
        _stds.setDataBase(_dataBase);
        return _stds.beanFight(_language);
    }

    public static Struct beanTeam(PkFight _stds,String _language, FacadeGame _dataBase) {
        _stds.setDataBase(_dataBase);
        return _stds.beanTeam(_language);
    }

    public static String navigateFightPlayer(Struct _str, long... _args) {
        return navigateFight(clickPlayerCaller(),CommonBean.DEST_WEB_FIGHT_HTML_TEAM_HTML,FIGHT+NAV_SEP+M_CLICK_PLAYER,_str,_args);
    }

    public static String navigateFightFoe(Struct _str, long... _args) {
        return navigateFight(clickFoeCaller(),CommonBean.DEST_WEB_FIGHT_HTML_TEAM_HTML,FIGHT+NAV_SEP+M_CLICK_FOE,_str,_args);
    }

    public static NatCaller clickPlayerCaller() {
        return new FightBeanClickPlayer();
    }

    public static NatCaller clickFoeCaller() {
        return new FightBeanClickFoe();
    }

    public static String navigateFight(NatCaller _caller, String _url, String _concat, Struct _str, long... _args) {
        return navigate(_caller,_url, PkScriptPagesInit.initConfFight(new Configuration()),_concat,_str,_args);
    }

    public static Struct callTeamBeanPlayerFightersAgainstFoeGet(Struct _str, long... _args) {
        return InitDbPkBean.callLongs(new TeamBeanPlayerFightersAgainstFoeGet(),_str,_args);
    }

    public static Struct callTeamBeanGetPlayerFigtherAgainstFoe(Struct _str, long... _args) {
        return InitDbPkBean.callLongs(new TeamBeanGetPlayerFigtherAgainstFoe(),_str,_args);
    }

    public static Struct callTeamBeanGetFoeFigtherAgainstFoe(Struct _str, long... _args) {
        return InitDbPkBean.callLongs(new TeamBeanGetFoeFigtherAgainstFoe(),_str,_args);
    }

    public static Struct callTeamBeanGetTrPokemonLink(Struct _str, long... _args) {
        return InitDbPkBean.callLongs(new TeamBeanGetTrPokemonLink(),_str,_args);
    }

    public static Struct callTeamBeanGetMembers(Struct _str, long... _args) {
        return InitDbPkBean.callLongs(new TeamBeanGetMembers(),_str,_args);
    }
    public static Struct callTeamBeanFoeTeamGet(Struct _str, long... _args) {
        return InitDbPkBean.callLongs(new TeamBeanFoeTeamGet(),_str,_args);
    }

    public static Struct callFightBeanMultGet(Struct _str, long... _args) {
        return InitDbPkBean.callLongs(new FightBeanMultGet(),_str,_args);
    }

    public static Struct callFightBeanEnabledMovesGet(Struct _str, long... _args) {
        return InitDbPkBean.callLongs(new FightBeanEnabledMovesGet(),_str,_args);
    }

    public static Struct callFightBeanIsStillEnabled(Struct _str, long... _args) {
        return InitDbPkBean.callLongs(new FightBeanIsStillEnabled(),_str,_args);
    }

    public static Struct callFightBeanWinningMoneyGet(Struct _str, long... _args) {
        return InitDbPkBean.callLongs(new FightBeanWinningMoneyGet(),_str,_args);
    }

    public static Struct callFightBeanNbRoundsGet(Struct _str, long... _args) {
        return InitDbPkBean.callLongs(new FightBeanNbRoundsGet(),_str,_args);
    }

    public static Struct callFightBeanNbFleeAttemptGet(Struct _str, long... _args) {
        return InitDbPkBean.callLongs(new FightBeanNbFleeAttemptGet(),_str,_args);
    }

    public static Struct callActivityOfMoveGetNbTurn(Struct _str, long... _args) {
        return InitDbPkBean.callLongs(new ActivityOfMoveGetNbTurn(),_str,_args);
    }

    public static Struct callActivityOfMoveIsEnabled(Struct _str, long... _args) {
        return InitDbPkBean.callLongs(new ActivityOfMoveIsEnabled(),_str,_args);
    }

    public static Struct callActivityOfMoveIsIncrementCount(Struct _str, long... _args) {
        return InitDbPkBean.callLongs(new ActivityOfMoveIsIncrementCount(),_str,_args);
    }

    public static Struct callActivityOfMoveGetNbTurn(ActivityOfMove _str, long... _args) {
        return InitDbPkBean.callLongs(new ActivityOfMoveGetNbTurn(),new ActivityOfMoveStruct(_str),_args);
    }

    public static Struct callActivityOfMoveIsEnabled(ActivityOfMove _str, long... _args) {
        return InitDbPkBean.callLongs(new ActivityOfMoveIsEnabled(),new ActivityOfMoveStruct(_str),_args);
    }

    public static Struct callActivityOfMoveIsIncrementCount(ActivityOfMove _str, long... _args) {
        return InitDbPkBean.callLongs(new ActivityOfMoveIsIncrementCount(),new ActivityOfMoveStruct(_str),_args);
    }

    protected DataBase dbTeam() {
        DataBase data_ = dbBase();
        PokemonData pkData_ = Instances.newPokemonData();
        pkData_.setBaseEvo(PIKA_2);
        pkData_.setEggGroups(new StringList(data_.getDefaultEggGroup()));
        pkData_.setTypes(new StringList(ELECTRICK));
        statBase(pkData_);
        pkData_.getLevMoves().add(new LevelMove((short)1,ECLAIR));
        pkData_.getLevMoves().add(new LevelMove((short)1,CHARGE));
        pkData_.setExpRate(1);
        pkData_.setHeight(Rate.one());
        pkData_.setWeight(Rate.one());
        pkData_.setCatchingRate((short) 1);
        pkData_.setHappiness((short) 1);
        pkData_.setHappinessHatch((short) 1);
        pkData_.setAbilities(new StringList(PARATONNERRE));
        data_.completeMembers(PIKA_2,pkData_);
        StringMap<String> trPks_ = new StringMap<String>();
        trPks_.addEntry(PIKACHU,PIKACHU_TR);
        trPks_.addEntry(PIKA_2,PIKA_TR_2);
        data_.getTranslatedPokemon().clear();
        data_.getTranslatedPokemon().addEntry(LANGUAGE,trPks_);
        StatusMoveData mteam_ = Instances.newStatusMoveData();
        mteam_.getEffects().add(Instances.newEffectTeam());
        data_.completeMembers(M_TEAM, mteam_);
        StatusMoveData send_ = Instances.newStatusMoveData();
        send_.getEffects().add(Instances.newEffectTeamWhileSendFoe());
        data_.completeMembers(M_TEAM_SEND, send_);
        DamagingMoveData used_ = Instances.newDamagingMoveData();
        EffectDamage dam_ = Instances.newEffectDamage();
        dam_.setPower(DataBase.VAR_PREFIX+Team.EQUIPE_NB_UTILISATION+DataBase.SEP_BETWEEN_KEYS+M_USE);
        used_.getEffects().add(dam_);
        data_.completeMembers(M_USE, used_);
        StatusMoveData heal_ = Instances.newStatusMoveData();
        heal_.getEffects().add(Instances.newEffectEndRoundPositionRelation());
        data_.completeMembers(M_STACK,heal_);
        StatusMoveData ant_ = Instances.newStatusMoveData();
        ant_.getEffects().add(Instances.newEffectEndRoundPositionTargetRelation());
        data_.completeMembers(M_ANT,ant_);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_TEAM, M_TEAM_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_TEAM_SEND, M_TEAM_SEND_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_USE, M_USE_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_STACK, M_STACK_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_ANT, M_ANT_TR);
        data_.getCombos().getEffects().add(new ListEffectCombo(new StringList(M_TEAM,M_TEAM_SEND),Instances.newEffectCombo()));
        data_.completeVariables();
        return data_;
    }
    protected DataBase db() {
        DataBase data_ = dbBase();
        data_.completeVariables();
        return data_;
    }

    private DataBase dbBase() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        move_.setPp((short) 20);
        data_.completeMembers(ECLAIR, move_);
        StatusMoveData sta_ = Instances.newStatusMoveData();
        EffectGlobal egl_ = Instances.newEffectGlobal();
        egl_.setWeather(true);
        sta_.getEffects().add(egl_);
        data_.completeMembers(CHARGE, sta_);
        StatusMoveData staTmp_ = Instances.newStatusMoveData();
        staTmp_.getEffects().add(Instances.newEffectGlobal());
        data_.completeMembers(CHARGE2, staTmp_);
        PokemonData pkData_ = Instances.newPokemonData();
        pkData_.setBaseEvo(PIKACHU);
        pkData_.setEggGroups(new StringList(data_.getDefaultEggGroup()));
        pkData_.setTypes(new StringList(ELECTRICK));
        statBase(pkData_);
        pkData_.getLevMoves().add(new LevelMove((short)1,ECLAIR));
        pkData_.getLevMoves().add(new LevelMove((short)1,CHARGE));
        pkData_.setExpRate(1);
        pkData_.setHeight(Rate.one());
        pkData_.setWeight(Rate.one());
        pkData_.setCatchingRate((short) 1);
        pkData_.setHappiness((short) 1);
        pkData_.setHappinessHatch((short) 1);
        pkData_.setAbilities(new StringList(PARATONNERRE));
        data_.completeMembers(PIKACHU,pkData_);
        Ball ball_ = Instances.newBall();
        data_.completeMembers(POKE_BALL,ball_);
        data_.getMiniPk().addEntry(PIKACHU,new int[1][1]);
        data_.getMaxiPkBack().addEntry(PIKACHU,new int[1][1]);
        data_.getMaxiPkFront().addEntry(PIKACHU,new int[1][1]);
        IdMap<Gender, String> gdrs_ = new IdMap<Gender, String>();
        gdrs_.addEntry(Gender.NO_GENDER, NO_G);
        gdrs_.addEntry(Gender.FEMALE,"FE");
        gdrs_.addEntry(Gender.MALE,"MA");
        data_.getTranslatedGenders().addEntry(LANGUAGE, gdrs_);
        IdMap<SelectedBoolean, String> bools_ = new IdMap<SelectedBoolean, String>();
        bools_.addEntry(SelectedBoolean.NO,"NO");
        bools_.addEntry(SelectedBoolean.YES,"YES");
        bools_.addEntry(SelectedBoolean.YES_AND_NO,"YES_AND_NO");
        data_.getTranslatedBooleans().addEntry(LANGUAGE, bools_);
        IdMap<DifficultyWinPointsFight, String> wfn_ = new IdMap<DifficultyWinPointsFight, String>();
        wfn_.addEntry(DifficultyWinPointsFight.TRES_DIFFICILE,"TRES_DIFFICILE");
        wfn_.addEntry(DifficultyWinPointsFight.DIFFICILE,"DIFFICILE");
        wfn_.addEntry(DifficultyWinPointsFight.FACILE,"FACILE");
        wfn_.addEntry(DifficultyWinPointsFight.TRES_FACILE,"TRES_FACILE");
        data_.getTranslatedDiffWinPts().addEntry(LANGUAGE, wfn_);
        IdMap<DifficultyModelLaw, String> ml_ = new IdMap<DifficultyModelLaw, String>();
        ml_.addEntry(DifficultyModelLaw.CONSTANT_MIN,"CONSTANT_MIN");
        ml_.addEntry(DifficultyModelLaw.CROISSANT,"CROISSANT");
        ml_.addEntry(DifficultyModelLaw.UNIFORME,"UNIFORME");
        ml_.addEntry(DifficultyModelLaw.DECROISSANT,"DECROISSANT");
        ml_.addEntry(DifficultyModelLaw.CONSTANT_MAX,"CONSTANT_MAX");
        data_.getTranslatedDiffModelLaw().addEntry(LANGUAGE, ml_);
        IdMap<EnvironmentType, String> et_ = new IdMap<EnvironmentType, String>();
        et_.addEntry(EnvironmentType.ROAD,"ROAD");
        et_.addEntry(EnvironmentType.ROCK,"ROCK");
        et_.addEntry(EnvironmentType.NOTHING,"NOTHING");
        et_.addEntry(EnvironmentType.BUILDING,"BUILDING");
        et_.addEntry(EnvironmentType.GRASS,"GRASS");
        et_.addEntry(EnvironmentType.DESERT,"DESERT");
        et_.addEntry(EnvironmentType.ICE,"ICE");
        et_.addEntry(EnvironmentType.SNOW,"SNOW");
        et_.addEntry(EnvironmentType.WATER,"WATER");
        data_.getTranslatedEnvironment().addEntry(LANGUAGE, et_);
        IdMap<Statistic, String> stats_ = new IdMap<Statistic, String>();
        stats_.addEntry(Statistic.ATTACK,"ATTACK");
        stats_.addEntry(Statistic.SPECIAL_ATTACK,"SPECIAL_ATTACK");
        stats_.addEntry(Statistic.DEFENSE,"DEFENSE");
        stats_.addEntry(Statistic.SPECIAL_DEFENSE,"SPECIAL_DEFENSE");
        stats_.addEntry(Statistic.SPEED, SPEED_TR);
        stats_.addEntry(Statistic.ACCURACY,"ACCURACY");
        stats_.addEntry(Statistic.EVASINESS,"EVASINESS");
        stats_.addEntry(Statistic.HP,"HP");
        stats_.addEntry(Statistic.PV_RESTANTS,"PV_RESTANTS");
        stats_.addEntry(Statistic.CRITICAL_HIT,"CRITICAL_HIT");
        data_.getTranslatedStatistics().addEntry(LANGUAGE, stats_);
        IdMap<TargetChoice, String> tar_ = new IdMap<TargetChoice, String>();
        tar_.addEntry(TargetChoice.ADJ_ADV,"ADJ_ADV");
        tar_.addEntry(TargetChoice.ADJ_MULT,"ADJ_MULT");
        tar_.addEntry(TargetChoice.ANY_FOE,"ANY_FOE");
        tar_.addEntry(TargetChoice.ALLIE,"ALLIE");
        tar_.addEntry(TargetChoice.ALLIES,"ALLIES");
        tar_.addEntry(TargetChoice.AUTRE_UNIQ,"AUTRE_UNIQ");
        tar_.addEntry(TargetChoice.ADJ_UNIQ,"ADJ_UNIQ");
        tar_.addEntry(TargetChoice.GLOBALE,"GLOBALE");
        tar_.addEntry(TargetChoice.LANCEUR,"LANCEUR");
        tar_.addEntry(TargetChoice.NOTHING,"NOTHING");
        tar_.addEntry(TargetChoice.TOUS_ADV,"TOUS_ADV");
        tar_.addEntry(TargetChoice.PSEUDO_GLOBALE,"PSEUDO_GLOBALE");
        tar_.addEntry(TargetChoice.UNIQUE_IMPORTE,"UNIQUE_IMPORTE");
        data_.getTranslatedTargets().addEntry(LANGUAGE, tar_);
        data_.getTranslatedTypes().addEntry(LANGUAGE,tr(ELECTRICK,ELECTRICK_TR));
        data_.getTranslatedPokemon().addEntry(LANGUAGE,tr(PIKACHU,PIKACHU_TR));
        data_.getTranslatedItems().addEntry(LANGUAGE,tr(POKE_BALL,POKE_BALL_TR));
        data_.getTranslatedAbilities().addEntry(LANGUAGE,tr(PARATONNERRE,PARATONNERRE_TR));
        StringMap<String> trMov_ = tr(LUTTE, LUTTE);
        trMov_.addAllEntries(tr(ECLAIR,ECLAIR_TR));
        trMov_.addAllEntries(tr(CHARGE,CHARGE_TR));
        trMov_.addAllEntries(tr(CHARGE2,CHARGE_TR2));
        data_.getTranslatedMoves().addEntry(LANGUAGE, trMov_);
        data_.getTranslatedStatus().addEntry(LANGUAGE,new StringMap<String>());
        data_.getTranslatedCategories().addEntry(LANGUAGE,tr("SPEC","SPEC"));
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        WildPk pkm_ = new WildPk();
        pkm_.setName(PIKACHU);
        pkm_.setAbility(PARATONNERRE);
        pkm_.setGender(Gender.NO_GENDER);
        pkm_.setItem(NULL_REF);
        pkm_.setLevel((short) 7);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 1));
        data_.getCombos().setEffects(new ListEffectCombos());
        pts(data_);
        return data_;
    }

    protected FacadeGame facadeEnMoves(DataBase _data) {
        FacadeGame f_ = facade(_data);
        Fight fight_ = f_.getFight();
        ActivityOfMove ever_ = new ActivityOfMove(false);
        ever_.setEnabled(true);
        fight_.getEnabledMoves().set(CHARGE2, ever_);
        ActivityOfMove some_ = new ActivityOfMove(true);
        some_.setEnabled(false);
        fight_.getEnabledMoves().set(CHARGE, some_);
        return f_;
    }

    protected FacadeGame facadeEnMovesAct(DataBase _data) {
        FacadeGame f_ = facadeEnMoves(_data);
        Fight fight_ = f_.getFight();
        ActivityOfMove some_ = new ActivityOfMove(true);
        some_.setEnabled(true);
        some_.setNbTurn((short) 1);
        fight_.getEnabledMoves().set(CHARGE, some_);
        return f_;
    }
    protected FacadeGame facadeBigTeams(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,4),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,5),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,6),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,7),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,8),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,9),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,10),move(move(new StringMap<Short>(),CHARGE,8),CHARGE2,5),diff_,_data));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(ECLAIR,M_TEAM)));
        TrainerLeague trainer_ = Instances.newTrainerLeague();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 4);
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        return fac_;
    }
    protected FacadeGame facade(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = new Player(NICKNAME,null,diff_,true,_data);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
//        PkTrainer foePokemon_ = Instances.newPkTrainer();
//        foePokemon_.setName(PIKACHU);
////        foePokemon_.setItem(MAGNET);
//        foePokemon_.setAbility(PARATONNERRE);
//        foePokemon_.setGender(Gender.NO_GENDER);
//        foePokemon_.setLevel((short) 3);
//        foePokemon_.setMoves(new StringList(ECLAIR));
//        foeTeam_.add(foePokemon_);
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR)));
//        foePokemon_ = Instances.newPkTrainer();
//        foePokemon_.setName(PIKACHU);
////        foePokemon_.setItem(MAGNET);
//        foePokemon_.setAbility(PARATONNERRE);
//        foePokemon_.setGender(Gender.NO_GENDER);
//        foePokemon_.setLevel((short) 4);
//        foePokemon_.setMoves(new StringList(ECLAIR));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,4),new StringList(ECLAIR)));
        TrainerLeague trainer_ = Instances.newTrainerLeague();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward((short) 200);
        trainer_.setMultiplicityFight((byte) 2);
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        return fac_;
    }

    private FacadeGame initFacade(DataBase _data) {
        FacadeGame fac_ = new FacadeGame();
        fac_.setLanguages(new StringList(EN));
        StringMap<String> displayLanguages_ = new StringMap<String>();
        displayLanguages_.addEntry(EN,EN);
        fac_.setDisplayLanguages(displayLanguages_);
        fac_.setData(_data);
        _data.setMessages(fac_.getData());
        fac_.setLoadedData(true);
        fac_.setZipName("");
        fac_.setData(_data);
        fac_.setLanguage(EN);
        return fac_;
    }

    private static void pts(DataBase _data) {
        _data.getExpGrowth().put(ExpType.E,"2*NIVEAU");
        _data.getExpGrowth().put(ExpType.L,"5/4*puis(VAR__NIVEAU,3)");
        _data.getExpGrowth().put(ExpType.M,"puis(VAR__NIVEAU,3)");
        _data.getExpGrowth().put(ExpType.P,"puis(VAR__NIVEAU,2)");
        _data.getExpGrowth().put(ExpType.F,"VAR__NIVEAU");
        _data.getExpGrowth().put(ExpType.R,"4/5*puis(VAR__NIVEAU,3)");
        _data.getRates().put(DifficultyWinPointsFight.TRES_FACILE, "4");
        _data.getRates().put(DifficultyWinPointsFight.FACILE, "2");
        _data.getRates().put(DifficultyWinPointsFight.DIFFICILE, "1");
        _data.getRates().put(DifficultyWinPointsFight.TRES_DIFFICILE, "1/2");
    }
    private static StringMap<Short> move(StringMap<Short> _moves, String _move, int _pp) {
        _moves.addEntry(_move, (short) _pp);
        return _moves;
    }
    private static PokemonPlayer pkPlayer(NameLevel _nameLevel, StringMap<Short> _moves, Difficulty _diff, DataBase _data) {
        WildPk w_ = Instances.newWildPk();
        w_.setName(_nameLevel.getName());
        w_.setLevel(_nameLevel.getLevel());
        w_.setAbility(PARATONNERRE);
        w_.setItem(NULL_REF);
        w_.setGender(Gender.NO_GENDER);
        PokemonPlayer lasPk_ = new PokemonPlayer(w_, _data, _moves);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data);
        return lasPk_;
    }

    private static PkTrainer toPkTrainer(NameLevel _nameLevel, StringList _moves) {
        PkTrainer pk_ = Instances.newPkTrainer();
        pk_.setName(_nameLevel.getName());
        pk_.setLevel(_nameLevel.getLevel());
        pk_.setAbility(PARATONNERRE);
        pk_.setItem(NULL_REF);
        pk_.setMoves(_moves);
        pk_.setGender(Gender.NO_GENDER);
        return pk_;
    }
}
