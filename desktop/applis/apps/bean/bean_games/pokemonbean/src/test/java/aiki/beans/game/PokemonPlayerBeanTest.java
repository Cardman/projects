package aiki.beans.game;

import aiki.beans.DetPkGameInit;
import aiki.beans.InitDbBean;
import aiki.beans.PkInd;
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
import code.formathtml.Navigation;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.images.BaseSixtyFourUtil;
import code.maths.Rate;
import code.scripts.pages.aiki.CssInit;
import code.scripts.pages.aiki.MessagesInit;
import code.scripts.pages.aiki.PagesInit;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import org.junit.Test;

public final class PokemonPlayerBeanTest extends InitDbBean {
    static final String NICKNAME = "CARDTEAM";

    @Test
    public void getName1() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(PIKACHU_TR,callPokemonPlayerBeanNameGet(displaying(beanPk(EN, fac_))));
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
        assertEq(PIKACHU_TR,callPokemonPlayerBeanNameGet(displaying(beanPk(EN, fac_))));
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
        assertEq(PIKACHU_TR,callPokemonPlayerBeanNameGet(displaying(beanPk(EN, fac_))));
    }
    @Test
    public void getName4() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        addHost(fac_.getGame());
        fac_.setHostedPokemon(true,newCoords(0,0,0,0));
        assertEq(PIKACHU_TR,callPokemonPlayerBeanNameGet(displaying(beanPk(EN, fac_))));
    }
    @Test
    public void getName5() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        addHost(fac_.getGame());
        fac_.setHostedPokemon(false,newCoords(0,0,0,0));
        assertEq(PIKACHU_TR,callPokemonPlayerBeanNameGet(displaying(beanPk(EN, fac_))));
    }
    @Test
    public void getUsedBallCatching1() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(NULL_REF,callPokemonPlayerBeanUsedBallCatchingGet(displaying(beanPk(EN, fac_))));
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
        assertEq(POKE_BALL_TR,callPokemonPlayerBeanUsedBallCatchingGet(displaying(beanPk(EN, fac_))));
    }
    @Test
    public void getLevel() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setLevel((short) 10);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(10,callPokemonPlayerBeanLevelGet(displaying(beanPk(EN, fac_))));
    }
    @Test
    public void getGender() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setGender(Gender.NO_GENDER);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(NO_G,callPokemonPlayerBeanGenderGet(displaying(beanPk(EN, fac_))));
    }
    @Test
    public void getAbility() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setAbility(PARATONNERRE);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(PARATONNERRE_TR,callPokemonPlayerBeanAbilityGet(displaying(beanPk(EN, fac_))));
    }
    @Test
    public void getItem1() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setItem(NULL_REF);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(NULL_REF,callPokemonPlayerBeanItemGet(displaying(beanPk(EN, fac_))));
    }
    @Test
    public void getItem2() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setItem(POKE_BALL);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(POKE_BALL_TR,callPokemonPlayerBeanItemGet(displaying(beanPk(EN, fac_))));
    }
    @Test
    public void rem1() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        divide(fac_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(Rate.newRate("1931/200"),callPokemonPlayerBeanRemainingHpGet(displaying(beanPk(EN, fac_))));
    }

    @Test
    public void rem2() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        divide(fac_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq("5.0E1",callPokemonPlayerBeanRemainingHpPerCentGet(displaying(beanPk(EN, fac_))));
    }

    @Test
    public void full() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        divide(fac_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(Rate.newRate("1931/100"),callPokemonPlayerBeanFullHpGet(displaying(beanPk(EN, fac_))));
    }


    @Test
    public void getNickname() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setNickname(NICKNAME);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(NICKNAME,callPokemonPlayerBeanNicknameGet(displaying(beanPk(EN, fac_))));
    }

    @Test
    public void wonExp() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setWonExpSinceLastLevel(Rate.newRate("1"));
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(Rate.newRate("1"),callPokemonPlayerBeanWonExpSinceLastLevelGet(displaying(beanPk(EN, fac_))));
    }

    @Test
    public void necExp() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setWonExpSinceLastLevel(Rate.newRate("2"));
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(Rate.newRate("13"),callPokemonPlayerBeanNecessaryPointsNextLevelGet(displaying(beanPk(EN, fac_))));
    }

    @Test
    public void happiness() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setHappiness((short) 25);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(25,callPokemonPlayerBeanHappinessGet(displaying(beanPk(EN, fac_))));
    }

    @Test
    public void lead() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).setNbStepsTeamLead((short) 25);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(25,callPokemonPlayerBeanNbStepsTeamLeadGet(displaying(beanPk(EN, fac_))));
    }

    @Test
    public void types1() {
        DataBase init_ = one();
        types(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertSizeEq(1,callPokemonPlayerBeanTypesGet(displaying(beanPk(EN, fac_))));
    }

    @Test
    public void types2() {
        DataBase init_ = one();
        types(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(ELECTRICK_TR,elt(callPokemonPlayerBeanTypesGet(displaying(beanPk(EN, fac_))),0));
    }

    @Test
    public void status1() {
        DataBase init_ = one();
        stat(init_);
        FacadeGame fac_ = fac(init_);
        first(fac_).setStatus(new StringList(STATUE));
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertSizeEq(1,callPokemonPlayerBeanStatusGet(displaying(beanPk(EN, fac_))));
    }

    @Test
    public void status2() {
        DataBase init_ = one();
        stat(init_);
        FacadeGame fac_ = fac(init_);
        first(fac_).setStatus(new StringList(STATUE));
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(STATUE_TR,elt(callPokemonPlayerBeanStatusGet(displaying(beanPk(EN, fac_))),0));
    }

    @Test
    public void img() {
        DataBase init_ = one();
        image(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(MAX_PIKA,callPokemonPlayerBeanImageGet(displaying(beanPk(EN, fac_))));
    }

    @Test
    public void moves1() {
        DataBase init_ = one();
        moves(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertSizeEq(2,callPokemonPlayerBeanMovesGet(displaying(beanPk(EN, fac_))));
    }

    @Test
    public void moves2() {
        DataBase init_ = one();
        moves(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(ECLAIR_TR,first(elt(callPokemonPlayerBeanMovesGet(displaying(beanPk(EN, fac_))),0)));
    }

    @Test
    public void moves3() {
        DataBase init_ = one();
        moves(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(CHARGE_TR,first(elt(callPokemonPlayerBeanMovesGet(displaying(beanPk(EN, fac_))),1)));
    }

    @Test
    public void moves4() {
        DataBase init_ = one();
        moves(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(15,callUsesOfMoveGetMax(second(elt(callPokemonPlayerBeanMovesGet(displaying(beanPk(EN, fac_))),0))));
    }

    @Test
    public void moves5() {
        DataBase init_ = one();
        moves(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(20,callUsesOfMoveGetMax(second(elt(callPokemonPlayerBeanMovesGet(displaying(beanPk(EN, fac_))),1))));
    }

    @Test
    public void moves6() {
        DataBase init_ = one();
        moves(init_);
        FacadeGame fac_ = fac(init_);
        first(fac_).getMoves().getVal(ECLAIR).setCurrent((short) 2);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(2,callUsesOfMoveGetCurrent(second(elt(callPokemonPlayerBeanMovesGet(displaying(beanPk(EN, fac_))),0))));
    }

    @Test
    public void moves7() {
        DataBase init_ = one();
        moves(init_);
        FacadeGame fac_ = fac(init_);
        first(fac_).getMoves().getVal(CHARGE).setCurrent((short) 7);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(7,callUsesOfMoveGetCurrent(second(elt(callPokemonPlayerBeanMovesGet(displaying(beanPk(EN, fac_))),1))));
    }

    @Test
    public void stats1() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertSizeEq(6,callPokemonPlayerBeanStatisticsGet(displaying(beanPk(EN, fac_))));
    }

    @Test
    public void stats2() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).getEv().set(Statistic.SPEED, (short) 3);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(SPEED_TR,callStatisticInfoPkPlayerGetName(elt(callPokemonPlayerBeanStatisticsGet(displaying(beanPk(EN, fac_))),Statistic.getStatisticsWithBase().indexOfObj(Statistic.SPEED))));
    }

    @Test
    public void stats3() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).getEv().set(Statistic.SPEED, (short) 3);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(3,callStatisticInfoPkPlayerGetEv(elt(callPokemonPlayerBeanStatisticsGet(displaying(beanPk(EN, fac_))),Statistic.getStatisticsWithBase().indexOfObj(Statistic.SPEED))));
    }

    @Test
    public void stats4() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).getIv().set(Statistic.SPEED, (short) 4);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(4,callStatisticInfoPkPlayerGetIv(elt(callPokemonPlayerBeanStatisticsGet(displaying(beanPk(EN, fac_))),Statistic.getStatisticsWithBase().indexOfObj(Statistic.SPEED))));
    }

    @Test
    public void stats5() {
        DataBase init_ = one();
        FacadeGame fac_ = fac(init_);
        first(fac_).getEv().set(Statistic.SPEED, (short) 2);
        first(fac_).getIv().set(Statistic.SPEED, (short) 5);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(Rate.newRate("221/40"),callStatisticInfoPkPlayerGetRate(elt(callPokemonPlayerBeanStatisticsGet(displaying(beanPk(EN, fac_))),Statistic.getStatisticsWithBase().indexOfObj(Statistic.SPEED))));
    }

    @Test
    public void evos1() {
        DataBase init_ = one();
        evos(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertSizeEq(1,callPokemonPlayerBeanEvolutionsGet(displaying(beanPk(EN, fac_))));
    }

    @Test
    public void evos2() {
        DataBase init_ = one();
        evos(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(RAI,first(elt(callPokemonPlayerBeanEvolutionsGet(displaying(beanPk(EN, fac_))),0)));
    }

    @Test
    public void evos3() {
        DataBase init_ = one();
        evos(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(MAX_RAI,second(elt(callPokemonPlayerBeanEvolutionsGet(displaying(beanPk(EN, fac_))),0)));
    }

    @Test
    public void evos4() {
        DataBase init_ = one();
        evos(init_);
        FacadeGame fac_ = fac(init_);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        assertEq(RAI_TR,callPokemonPlayerBeanGetEvo(displaying(beanPk(EN, fac_)),0));
    }
    @Test
    public void init() {
        DataBase init_ = one();
        evos2(init_);
        moves(init_);
        image(init_);
        FacadeGame fac_ = fac(init_);
        first(fac_).getEv().set(Statistic.SPEED, (short) 2);
        first(fac_).getIv().set(Statistic.SPEED, (short) 5);
        fac_.openMenu();
        fac_.setChosenTeamPokemon((short) 0);
        StringMap<String> builtMessages_ = MessagesInit.ms();
        AnaRendBlock.adjust(builtMessages_);
        StringMap<String> builtOther_ = CssInit.ms();
        PkInd pk_ = new PkInd();
        Navigation nav_ = pk_.nav(Constants.getAvailableLanguages(),EN,new DetPkGameInit(), PagesInit.buildInd(),builtOther_,builtMessages_,ACCESS_TO_DEFAULT_FILES);
        pk_.setDataBase(fac_);
        pk_.initializeRendSessionDoc(nav_);
        assertEq("<html xmlns:c=\"javahtml\"><head><title>Data about the pokemon PIKA</title><link href=\"web_pk/css/pokemon.css\" rel=\"stylesheet\" type=\"text/css\"/><style>p{\n" +
                "\ttext-indent:25px;\n" +
                "}\n" +
                "body{\n" +
                "\ttext-align:justify;\n" +
                "}\n" +
                "td{\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "th{\n" +
                "\tbackground: yellow;\n" +
                "\tborder:1px solid black;\n" +
                "}\n" +
                "table{\n" +
                "\tborder-spacing:0;\n" +
                "}\n" +
                "h1{\n" +
                "\tcolor:red;\n" +
                "}\n" +
                "h2{\n" +
                "\tcolor:blue;\n" +
                "}\n" +
                "</style></head><body>The name of the pokemon is PIKA.<img src=\"AAABAACO\"/><br/>The possible evolutions of the pokemon are:<ul><li>2RE<img src=\"AAABAACQ\"/></li><li>RE<img src=\"AAABAACP\"/></li></ul>The level of the pokemon is 7.The gender of the pokemon is NO_G.The ability of the pokemon is PARRA.The pokemon has no item.The pokemon has 1931/100 health points being about 1.0E2 % of its full life (1931/100).The nickname of the pokemon is PIKACHU.The pokemon won 0 experience points since its last growth of level.The nombre of necessary points for growing level is 15.The happiness level is 1.The number of walked steps at the heading is 0.The types of the pokemon are the following one:<ul><li>ELECTRICK_TR</li></ul>The moves of the pokemon are the following one:<table><thead><tr><th>Move</th><th>Curent pp</th><th>Full pp</th></tr></thead><tbody><tr><td>ECLA</td><td>15</td><td>15</td></tr><tr><td>TANK</td><td>20</td><td>20</td></tr></tbody></table>The statistics of the pokemon are the following one:<table><thead><tr><th>Statistic</th><th>Ev</th><th>Iv</th><th>Value</th></tr></thead><tbody><tr><td>ATTACK</td><td>0</td><td>31</td><td>731/100</td></tr><tr><td>DEFENSE</td><td>0</td><td>31</td><td>731/100</td></tr><tr><td>HP</td><td>0</td><td>31</td><td>1931/100</td></tr><tr><td>SPECIAL_ATTACK</td><td>0</td><td>31</td><td>731/100</td></tr><tr><td>SPECIAL_DEFENSE</td><td>0</td><td>31</td><td>731/100</td></tr><tr><td>ZZ</td><td>2</td><td>5</td><td>221/40</td></tr></tbody></table></body></html>",nav_.getHtmlText());
    }
    private void evos(DataBase _init) {
        PokemonData ev_ = Instances.newPokemonData();
        ev_.setBaseEvo(PIKACHU);
        _init.completeMembers(RAI, ev_);
        _init.getPokemon(PIKACHU).getEvolutions().addEntry(RAI,Instances.newEvolutionHappiness());
        _init.getMaxiPkFront().put(RAI, BaseSixtyFourUtil.getImageByString(MAX_RAI));
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
        _init.getMaxiPkFront().put(RAI, BaseSixtyFourUtil.getImageByString(MAX_RAI));
        _init.getMaxiPkFront().put(RAI2, BaseSixtyFourUtil.getImageByString(MAX_RAI2));
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
        _init.getMaxiPkFront().put(PIKACHU, BaseSixtyFourUtil.getImageByString(MAX_PIKA));
    }
    private void moves(DataBase _init) {
        _init.addConstNumTest(DEF_MAX_ATT,Rate.newRate("2"));
        DamagingMoveData one_ = Instances.newDamagingMoveData();
        one_.setPp((short) 15);
        _init.completeMembers(ECLAIR, one_);
        DamagingMoveData two_ = Instances.newDamagingMoveData();
        two_.setPp((short) 20);
        _init.completeMembers(CHARGE,two_);
    }
    private void revive(FacadeGame _fac) {
        Game game_ = _fac.getGame();
        game_.getPlayer().getItem(FOSSIL);
        game_.getPlayer().doRevivingFossil(FOSSIL, game_.getDifficulty(), _fac.getData());
    }

    private void addFossile(DataBase _db) {
        Fossil fo_ = Instances.newFossil();
        fo_.setLevel((short) 2);
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
        game_.initUtilisateur(NICKNAME, null, diff_, _init);
        return game_;
    }

    private FacadeGame fac(DataBase _init) {
        FacadeGame fac_ = new FacadeGame();
        fac_.setLanguages(new StringList(EN));
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
        sent_.setLevel((short) 1);
        sent_.setAbility(_ability);
        sent_.setItem(_item);
        sent_.setGender(_gender);
        sent_.setMoves(new StringMap<UsesOfMove>());
        sent_.getMoves().put(CHARGE, new UsesOfMove((short) 10));
        sent_.setHappiness((short) 70);
        sent_.setWonExpSinceLastLevel(Rate.one());
        sent_.setUsedBallCatching(POKE_BALL);
        return sent_;
    }

    private void divide(FacadeGame _fac) {
        first(_fac).setRemainingHp(Rate.divide(first(_fac).pvMax(_fac.getData()),Rate.newRate("2")));
    }

}
