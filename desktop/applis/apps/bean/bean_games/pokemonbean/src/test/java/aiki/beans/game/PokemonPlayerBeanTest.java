package aiki.beans.game;

import aiki.beans.StringMapObject;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Fossil;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.pokemon.PokemonData;
import aiki.game.Game;
import aiki.game.HostPokemonDuo;
import aiki.game.UsesOfMove;
import aiki.game.params.Difficulty;
import aiki.instances.Instances;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.enums.Gender;
import code.maths.Rate;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class PokemonPlayerBeanTest extends InitDbPkBean {
    static final int IMG_MAX_RAI = 143;
    static final int IMG_MAX_RAI2 = IMG_MAX_RAI + 1;
    static final String NICKNAME = "CARDTEAM";

    @Test
    public void getName1() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(PIKACHU_TR,callPokemonPlayerBeanNameGet(bean(fac_)));
    }

    @Test
    public void getName2() {
        DataBase init_ = one();
        addFossile(init_);
        FacadeGame fac_ = fac(init_);
        revive(fac_);
        fac_.openMenu();
        fac_.searchPokemonFirstBox();
        fac_.checkLinePokemonFirstBox(0);
        assertEq(PIKACHU_TR,callPokemonPlayerBeanNameGet(bean(fac_)));
    }
    @Test
    public void getName3() {
        DataBase init_ = one();
        setAbilities(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.initTrading();
        PokemonPlayer pk_ = newPokemonPlayer(PIKACHU, PARATONNERRE, Gender.NO_GENDER, NULL_REF);
        fac_.receivePokemonPlayer(pk_);
        assertEq(PIKACHU_TR,callPokemonPlayerBeanNameGet(bean(fac_)));
    }
    @Test
    public void getName4() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        addHost(fac_.getGame());
        fac_.setHostedPokemon(true,newCoords(0,0,0,0));
        assertEq(PIKACHU_TR,callPokemonPlayerBeanNameGet(bean(fac_)));
    }
    @Test
    public void getName5() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        addHost(fac_.getGame());
        fac_.setHostedPokemon(false,newCoords(0,0,0,0));
        assertEq(PIKACHU_TR,callPokemonPlayerBeanNameGet(bean(fac_)));
    }
    @Test
    public void getUsedBallCatching1() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(NULL_REF,callPokemonPlayerBeanUsedBallCatchingGet(bean(fac_)));
    }
    @Test
    public void getUsedBallCatching2() {
        DataBase init_ = one();
        setAbilities(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.initTrading();
        PokemonPlayer pk_ = newPokemonPlayer(PIKACHU, PARATONNERRE, Gender.NO_GENDER, NULL_REF);
        fac_.receivePokemonPlayer(pk_);
        assertEq(POKE_BALL_TR,callPokemonPlayerBeanUsedBallCatchingGet(bean(fac_)));
    }
    @Test
    public void getLevel() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setLevel( 10);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(10,callPokemonPlayerBeanLevelGet(bean(fac_)));
    }
    @Test
    public void getGender() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setGender(Gender.NO_GENDER);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(NO_G,callPokemonPlayerBeanGenderGet(bean(fac_)));
    }
    @Test
    public void getAbility() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setAbility(PARATONNERRE);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(PARATONNERRE_TR,callPokemonPlayerBeanAbilityGet(bean(fac_)));
    }
    @Test
    public void getItem1() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setItem(NULL_REF);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(NULL_REF,callPokemonPlayerBeanItemGet(bean(fac_)));
    }
    @Test
    public void getItem2() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setItem(POKE_BALL);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(POKE_BALL_TR,callPokemonPlayerBeanItemGet(bean(fac_)));
    }
    @Test
    public void rem1() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        divide(fac_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(Rate.newRate("1931/200"),callPokemonPlayerBeanRemainingHpGet(bean(fac_)));
    }

    @Test
    public void rem2() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        divide(fac_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq("5.0"+Rate.POWER+"1",callPokemonPlayerBeanRemainingHpPerCentGet(bean(fac_)));
    }

    @Test
    public void full() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        divide(fac_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(Rate.newRate("1931/100"),callPokemonPlayerBeanFullHpGet(bean(fac_)));
    }


    @Test
    public void getNickname() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setNickname(NICKNAME);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(NICKNAME,callPokemonPlayerBeanNicknameGet(bean(fac_)));
    }

    @Test
    public void wonExp() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setWonExpSinceLastLevel(Rate.newRate("1"));
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(Rate.newRate("1"),callPokemonPlayerBeanWonExpSinceLastLevelGet(bean(fac_)));
    }

    @Test
    public void necExp() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setWonExpSinceLastLevel(Rate.newRate("2"));
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(Rate.newRate("13"),callPokemonPlayerBeanNecessaryPointsNextLevelGet(bean(fac_)));
    }

    @Test
    public void happiness() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setHappiness( 25);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(25,callPokemonPlayerBeanHappinessGet(bean(fac_)));
    }

    @Test
    public void lead() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setNbStepsTeamLead( 25);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(25,callPokemonPlayerBeanNbStepsTeamLeadGet(bean(fac_)));
    }

    @Test
    public void types1() {
        DataBase init_ = one();
        types(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertSizeEq(1,callPokemonPlayerBeanTypesGet(bean(fac_)));
    }

    @Test
    public void types2() {
        DataBase init_ = one();
        types(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(ELECTRICK_TR,elt(callPokemonPlayerBeanTypesGet(bean(fac_)),0));
    }

    @Test
    public void status1() {
        DataBase init_ = one();
        stat(init_);
        FacadeGame fac_ = fac(init_);
        first(fac_).setStatus(new StringList(STATUE));
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertSizeEq(1,callPokemonPlayerBeanStatusGet(bean(fac_)));
    }

    @Test
    public void status2() {
        DataBase init_ = one();
        stat(init_);
        FacadeGame fac_ = fac(init_);
        first(fac_).setStatus(new StringList(STATUE));
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(STATUE_TR,elt(callPokemonPlayerBeanStatusGet(bean(fac_)),0));
    }

    @Test
    public void img() {
        DataBase init_ = one();
        image(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(one(IMG_MAX_PIKA),callPokemonPlayerBeanImageGet(bean(fac_)));
    }

    @Test
    public void moves1() {
        DataBase init_ = one();
        moves(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertSizeEq(2,callPokemonPlayerBeanMovesGet(bean(fac_)));
    }

    @Test
    public void moves2() {
        DataBase init_ = one();
        moves(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(ECLAIR_TR, nameMove(eltUses(callPokemonPlayerBeanMovesGet(bean(fac_)),0)));
    }

    @Test
    public void moves3() {
        DataBase init_ = one();
        moves(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(CHARGE_TR, nameMove(eltUses(callPokemonPlayerBeanMovesGet(bean(fac_)),1)));
    }

    @Test
    public void moves4() {
        DataBase init_ = one();
        moves(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(15,callUsesOfMoveGetMax(useMove(eltUses(callPokemonPlayerBeanMovesGet(bean(fac_)),0))));
    }

    @Test
    public void moves5() {
        DataBase init_ = one();
        moves(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(20,callUsesOfMoveGetMax(useMove(eltUses(callPokemonPlayerBeanMovesGet(bean(fac_)),1))));
    }

    @Test
    public void moves6() {
        DataBase init_ = one();
        moves(init_);
        FacadeGame fac_ = fac(init_);
        first(fac_).getMoves().getVal(ECLAIR).setCurrent( 2);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(2,callUsesOfMoveGetCurrent(useMove(eltUses(callPokemonPlayerBeanMovesGet(bean(fac_)),0))));
    }

    @Test
    public void moves7() {
        DataBase init_ = one();
        moves(init_);
        FacadeGame fac_ = fac(init_);
        first(fac_).getMoves().getVal(CHARGE).setCurrent( 7);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(7,callUsesOfMoveGetCurrent(useMove(eltUses(callPokemonPlayerBeanMovesGet(bean(fac_)),1))));
    }

    @Test
    public void stats1() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertSizeEq(6,callPokemonPlayerBeanStatisticsGet(bean(fac_)));
    }

    @Test
    public void stats2() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).getEv().set(Statistic.SPEED,3L);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(SPEED_TR,callStatisticInfoPkPlayerGetName(eltStat(callPokemonPlayerBeanStatisticsGet(bean(fac_)),Statistic.getStatisticsWithBase().indexOfObj(Statistic.SPEED))));
    }

    @Test
    public void stats3() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).getEv().set(Statistic.SPEED,3L);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(3,callStatisticInfoPkPlayerGetEv(eltStat(callPokemonPlayerBeanStatisticsGet(bean(fac_)),Statistic.getStatisticsWithBase().indexOfObj(Statistic.SPEED))));
    }

    @Test
    public void stats4() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).getIv().set(Statistic.SPEED,4L);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(4,callStatisticInfoPkPlayerGetIv(eltStat(callPokemonPlayerBeanStatisticsGet(bean(fac_)),Statistic.getStatisticsWithBase().indexOfObj(Statistic.SPEED))));
    }

    @Test
    public void stats5() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).getEv().set(Statistic.SPEED,2L);
        first(fac_).getIv().set(Statistic.SPEED,5L);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(Rate.newRate("221/40"),callStatisticInfoPkPlayerGetRate(eltStat(callPokemonPlayerBeanStatisticsGet(bean(fac_)),Statistic.getStatisticsWithBase().indexOfObj(Statistic.SPEED))));
    }

    @Test
    public void evos1() {
        DataBase init_ = one();
        evos(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertSizeEq(1,callPokemonPlayerBeanEvolutionsGet(bean(fac_)));
    }

    @Test
    public void evos2() {
        DataBase init_ = one();
        evos(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(RAI,callPokemonPlayerBeanGetEvoKey(eltEvo(callPokemonPlayerBeanEvolutionsGet(bean(fac_)),0)));
    }

    @Test
    public void evos3() {
        DataBase init_ = one();
        evos(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(one(IMG_MAX_RAI),callPokemonPlayerBeanGetEvoImg(eltEvo(callPokemonPlayerBeanEvolutionsGet(bean(fac_)),0)));
    }

    @Test
    public void evos4() {
        DataBase init_ = one();
        evos(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon( 0);
        assertEq(RAI_TR,callPokemonPlayerBeanGetEvo(eltEvo(callPokemonPlayerBeanEvolutionsGet(bean(fac_)),0)));
    }

    private PokemonPlayerBean bean(FacadeGame _fac) {
        PokemonPlayerBean b_ = new PokemonPlayerBean();
        b_.setDataBase(_fac);
        b_.setForms(new StringMapObject());
        b_.setLanguage(EN);
        b_.beforeDisplaying();
        return b_;
    }

    private void evos(DataBase _init) {
        PokemonData ev_ = Instances.newPokemonData();
        ev_.setBaseEvo(PIKACHU);
        _init.completeMembers(RAI, ev_);
        _init.getPokemon(PIKACHU).getEvolutions().addEntry(RAI,Instances.newEvolutionHappiness());
        _init.getMaxiPkFront().put(RAI, instance(IMG_MAX_RAI));
        _init.getTranslatedPokemon().getVal(EN).addEntry(RAI,RAI_TR);
    }
    private void evos2(DataBase _init) {
        PokemonData ev_ = Instances.newPokemonData();
        ev_.setBaseEvo(PIKACHU);
        _init.completeMembers(RAI, ev_);
        PokemonData ev2_ = Instances.newPokemonData();
        ev2_.setBaseEvo(PIKACHU);
        _init.completeMembers(RAI2, ev2_);
        _init.getPokemon(PIKACHU).getEvolutions().addEntry(RAI,Instances.newEvolutionHappiness());
        _init.getPokemon(PIKACHU).getEvolutions().addEntry(RAI2,Instances.newEvolutionHappiness());
        _init.getMaxiPkFront().put(RAI, instance(IMG_MAX_RAI));
        _init.getMaxiPkFront().put(RAI2, instance(IMG_MAX_RAI2));
        _init.getTranslatedPokemon().getVal(EN).addEntry(RAI,RAI_TR);
        _init.getTranslatedPokemon().getVal(EN).addEntry(RAI2,RAI2_TR);
    }
    private void types(DataBase _init) {
        _init.getPokemon(PIKACHU).setTypes(new StringList(ELECTRICK));
    }

    private void stat(DataBase _init) {
        _init.completeMembers(STATUE,Instances.newStatusSimple());
        _init.getTranslatedStatus().getVal(EN).addEntry(STATUE,STATUE_TR);
    }

    private PokemonPlayer first(FacadeGame _fac) {
        return _fac.getPlayer().getPokemonPlayerList().getValue(0);
    }

    private void image(DataBase _init) {
        _init.getMaxiPkFront().put(PIKACHU, instance(IMG_MAX_PIKA));
    }
    private void moves(DataBase _init) {
        _init.addConstNumTest(DataBase.DEF_MAX_ATT,Rate.newRate("2"));
        DamagingMoveData one_ = Instances.newDamagingMoveData();
        one_.setPp( 15);
        _init.completeMembers(ECLAIR, one_);
        DamagingMoveData two_ = Instances.newDamagingMoveData();
        two_.setPp( 20);
        _init.completeMembers(CHARGE,two_);
    }
    private void revive(FacadeGame _fac) {
        Game game_ = _fac.getGame();
        game_.getPlayer().getItem(FOSSIL);
        game_.getPlayer().doRevivingFossil(FOSSIL, game_.getDifficulty(), _fac.getData());
    }

    private void addFossile(DataBase _db) {
        Fossil fo_ = Instances.newFossil();
        fo_.setLevel( 2);
        fo_.setPokemon(PIKACHU);
        _db.completeMembers(FOSSIL, fo_);
    }


    private void setAbilities(DataBase _db) {
        _db.getPokemon(PIKACHU).setAbilities(new StringList(PARATONNERRE));
        _db.completeMembers(PARATONNERRE,Instances.newAbilityData());
    }

    private void addHost(Game _game) {
        HostPokemonDuo h_ = Instances.newHostPokemonDuo();
        h_.setFirstPokemon((PokemonPlayer) _game.getPlayer().getTeam().get(0));
        h_.setSecondPokemon((PokemonPlayer) _game.getPlayer().getTeam().get(0));
        _game.getHostedPk().addEntry(newCoords(0,0,0,0), h_);
    }

    private Game game(DataBase _init) {
        Game game_ = new Game(_init);
        Difficulty diff_ = new Difficulty();
        game_.initUtilisateur(NICKNAME, diff_, _init);
        return game_;
    }

    private FacadeGame fac(DataBase _init) {
        FacadeGame fac_ = new FacadeGame();
        fac_.setLanguages(indexes());
        StringMap<String> displayLanguages_ = new StringMap<String>();
        displayLanguages_.addEntry(EN,EN);
        fac_.setDisplayLanguages(displayLanguages_);
        fac_.setData(_init);
        _init.setMessages(fac_.getData());
        fac_.setLoadedData(true);
        fac_.setZipName("");
        fac_.setData(_init);
        fac_.setLanguage(EN);
        fac_.setGame(game(_init));
        return fac_;
    }


    private static PokemonPlayer newPokemonPlayer(String _name, String _ability, Gender _gender, String _item) {
        PokemonPlayer sent_ = new PokemonPlayer();
        sent_.setName(_name);
        sent_.setLevel( 1);
        sent_.setAbility(_ability);
        sent_.setItem(_item);
        sent_.setGender(_gender);
        sent_.setMoves(new StringMap<UsesOfMove>());
        sent_.getMoves().put(CHARGE, new UsesOfMove( 10));
        sent_.setHappiness( 70);
        sent_.setWonExpSinceLastLevel(Rate.one());
        sent_.setUsedBallCatching(POKE_BALL);
        return sent_;
    }

    private void divide(FacadeGame _fac) {
        first(_fac).setRemainingHp(Rate.divide(first(_fac).pvMax(_fac.getData()),Rate.newRate("2")));
    }

}
