package aiki.sml;

import aiki.db.*;
import aiki.facade.*;
import aiki.facade.enums.*;
import aiki.fight.*;
import aiki.fight.abilities.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.status.*;
import aiki.fight.util.*;
import aiki.game.*;
import aiki.game.fight.*;
import aiki.game.fight.actions.*;
import aiki.game.fight.util.*;
import aiki.game.params.enums.*;
import aiki.game.player.enums.*;
import aiki.instances.*;
import aiki.map.*;
import aiki.map.buildings.*;
import aiki.map.characters.*;
import aiki.map.enums.*;
import aiki.map.levels.*;
import aiki.map.levels.enums.*;
import aiki.map.places.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.*;
import aiki.map.util.*;
import aiki.util.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.sml.*;
import code.sml.core.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class DocumentAikiTest extends EquallableAikiSerialUtil {

    public static final String LOADING_GAME_MINI = "<" +DocumentReaderAikiCoreUtil.MAIN_TAG+" "+DocumentWriterCoreUtil.FIELD+"=\""+ DocumentReaderAikiCoreUtil.LOADING_GAME + "\"/>";
    public static final String GAME_MINI = "<" + DocumentReaderAikiCoreUtil.MAIN_TAG+" "+DocumentWriterCoreUtil.FIELD+"=\""+ DocumentReaderAikiCoreUtil.GAME + "\"/>";
    public static final String OTHER_MINI = "<" + DocumentReaderAikiCoreUtil.MAIN_TAG + "/>";
    public static final String OTHER_SEC_MINI = "<" + DocumentReaderAikiCoreUtil.MAIN_TAG + "_/>";

    @Test
    public void t1() {
        assertFalse(StringUtil.nullToEmpty(DocumentWriterAikiCoreUtil.nullToPk(null).getNickname()+" ").isEmpty());
    }
    @Test
    public void t2() {
        assertFalse(StringUtil.nullToEmpty(DocumentWriterAikiCoreUtil.nullToPl(null).getName()+" ").isEmpty());
    }
    @Test
    public void t3() {
        assertFalse(StringUtil.nullToEmpty(DocumentWriterAikiCoreUtil.nullToTr(null).getImageMiniFileName()+" ").isEmpty());
    }
    @Test
    public void t4() {
        assertFalse(StringUtil.nullToEmpty(DocumentWriterAikiCoreUtil.nullToTrBis(null).getImageMiniFileName()+" ").isEmpty());
    }
    @Test
    public void t5() {
        assertFalse(StringUtil.nullToEmpty(DocumentWriterAikiCoreUtil.nullToCent(null).getImageFileName()+" ").isEmpty());
    }
    @Test
    public void t6() {
        assertFalse(StringUtil.nullToEmpty(DocumentWriterAikiCoreUtil.nullToSt(null).getFail()+" ").isEmpty());
    }
    @Test
    public void t7() {
        assertFalse(StringUtil.nullToEmpty(DocumentWriterAikiCoreUtil.nullToEvo(null).getPokemon()+" ").isEmpty());
    }
    @Test
    public void t8() {
        assertFalse(StringUtil.nullToEmpty(DocumentWriterAikiCoreUtil.nullToEffEndSt(null).getFail()+" ").isEmpty());
    }
    @Test
    public void t9() {
        assertFalse(StringUtil.nullToEmpty(DocumentWriterAikiCoreUtil.nullToEffEnd(null).getFail()+" ").isEmpty());
    }
    @Test
    public void t10() {
        assertFalse(StringUtil.nullToEmpty(DocumentWriterAikiCoreUtil.nullToEffEnd(null).getFail()+" ").isEmpty());
    }
    @Test
    public void t11() {
        assertFalse(StringUtil.nullToEmpty(DocumentWriterAikiCoreUtil.nullToMove(null).getMoveType()+" ").isEmpty());
    }
    @Test
    public void t12() {
        assertFalse(StringUtil.nullToEmpty(DocumentWriterAikiCoreUtil.nullToIt(null).getItemType()+" ").isEmpty());
    }
    @Test
    public void t13() {
        AbilityData a_ = Instances.newAbilityData();
        a_.getBreakFoeImmune().add(new TypesDuo("",""));
        a_.getImmuLowStat().add(Statistic.ACCURACY);
        a_.getImmuLowStatIfStatus().add(new StatisticStatus(Statistic.ACCURACY,""));
        a_.getImmuLowStatisTypes().addEntry("",new IdList<Statistic>(Statistic.ACCURACY));
        a_.getBonusStatRank().addEntry(Statistic.ACCURACY,(byte)1);
        a_.getMultStatAlly().addEntry(Statistic.ACCURACY, Rate.one());
        a_.getMultStatIfCat().addEntry(new StatisticCategory(Statistic.ACCURACY,""),Rate.one());
        a_.getMultStatIfStatutRank().addEntry(new StatisticStatus(Statistic.ACCURACY,""),(byte)1);
        a_.getMultStatIfDamageCat().addEntry(new StatisticCategory(Statistic.ACCURACY,""),(byte)1);
        a_.getMultStatIfDamgeType().addEntry(new StatisticType(Statistic.ACCURACY,""),(byte)1);
        a_.getMultStat().addEntry(Statistic.ACCURACY,"");
        a_.getChangingBoostTypes().addEntry("",new TypeDamageBoost("",Rate.one()));
        a_.getHealHpByTypeIfWeather().addEntry(new WeatherType("",""),Rate.one());
        a_.getEffectEndRound().add(Instances.newEffectEndRoundGlobal());
        a_.getEffectSending().add(Instances.newEffectWhileSendingWithStatistic());
        a_.getEffectSending().add(Instances.newEffectWhileSendingSimple());
        AbilityData o_ = save(a_);
        assertEq(1,o_.getEffectEndRound().size());
        assertEq(2,o_.getEffectSending().size());
    }
    @Test
    public void t14() {
        assertEq(new LgInt(1000),DocumentReaderAikiCoreUtil.price(""));
    }
    @Test
    public void t15() {
        ItemForBattle a_ = Instances.newItemForBattle();
        a_.getBoostStatisTypes().addEntry("", new IdMap<Statistic, Byte>());
        a_.getMultStatPokemonRank().addEntry(new StatisticPokemon(Statistic.ACCURACY,""),(byte)1);
        a_.getWinEvFight().addEntry(Statistic.ACCURACY,(short)1);
        a_.getEffectEndRound().add(Instances.newEffectEndRoundFoe());
        a_.getEffectEndRound().add(Instances.newEffectEndRoundGlobal());
        a_.getEffectEndRound().add(Instances.newEffectEndRoundIndividual());
        a_.getEffectEndRound().add(Instances.newEffectEndRoundMultiRelation());
        a_.getEffectEndRound().add(Instances.newEffectEndRoundPositionRelation());
        a_.getEffectEndRound().add(Instances.newEffectEndRoundPositionTargetRelation());
        a_.getEffectEndRound().add(Instances.newEffectEndRoundSingleRelation());
        a_.getEffectEndRound().add(Instances.newEffectEndRoundSingleStatus());
        a_.getEffectEndRound().add(Instances.newEffectEndRoundStatusRelation());
        a_.getEffectEndRound().add(Instances.newEffectEndRoundTeam());
        ItemForBattle o_ = save(a_);
        assertEq(0,o_.getEffectSending().size());
    }
    @Test
    public void t16() {
        Berry a_ = Instances.newBerry();
        a_.getMultFoesDamage().addEntry("", new EfficiencyRate(Rate.one(),Rate.one()));
        a_.getMultStat().addEntry(Statistic.ACCURACY,new BoostHpRate((byte) 1,Rate.one()));
        Berry o_ = save(a_);
        assertEq(1,o_.getMultFoesDamage().size());
        assertEq(1,o_.getMultStat().size());
    }
    @Test
    public void t17() {
        Berry a_ = Instances.newBerry();
        a_.getMultFoesDamage().addEntry("", new EfficiencyRate(Rate.one(),Rate.one()));
        a_.getMultStat().addEntry(Statistic.ACCURACY,new BoostHpRate((byte) 1,Rate.one()));
        Berry o_ = save(a_);
        assertEq(1,o_.getMultFoesDamage().size());
        assertEq(1,o_.getMultStat().size());
    }
    @Test
    public void t18() {
        Ball a_ = Instances.newBall();
        a_.setCatchingRate("_");
        Ball o_ = save(a_);
        assertEq("_",o_.getCatchingRate());
    }
    @Test
    public void t19() {
        Boost a_ = Instances.newBoost();
        a_.setPrice(123);
        Boost o_ = save(a_);
        assertEq(123,o_.getPrice());
    }
    @Test
    public void t20() {
        EvolvingItem a_ = Instances.newEvolvingItem();
        a_.setPrice(123);
        EvolvingItem o_ = save(a_);
        assertEq(123,o_.getPrice());
    }
    @Test
    public void t21() {
        EvolvingStone a_ = Instances.newEvolvingStone();
        a_.setPrice(123);
        EvolvingStone o_ = save(a_);
        assertEq(123,o_.getPrice());
    }
    @Test
    public void t22() {
        Fossil a_ = Instances.newFossil();
        a_.setPrice(123);
        Fossil o_ = save(a_);
        assertEq(123,o_.getPrice());
    }
    @Test
    public void t23() {
        HealingHp a_ = Instances.newHealingHp();
        a_.setPrice(123);
        HealingHp o_ = save(a_);
        assertEq(123,o_.getPrice());
    }
    @Test
    public void t24() {
        HealingPp a_ = Instances.newHealingPp();
        a_.setPrice(123);
        HealingPp o_ = save(a_);
        assertEq(123,o_.getPrice());
    }
    @Test
    public void t25() {
        HealingSimpleItem a_ = Instances.newHealingSimpleItem();
        a_.setPrice(123);
        HealingSimpleItem o_ = save(a_);
        assertEq(123,o_.getPrice());
    }
    @Test
    public void t26() {
        HealingHpStatus a_ = Instances.newHealingHpStatus();
        a_.setPrice(123);
        HealingHpStatus o_ = save(a_);
        assertEq(123,o_.getPrice());
    }
    @Test
    public void t27() {
        HealingSimpleStatus a_ = Instances.newHealingSimpleStatus();
        a_.setPrice(123);
        HealingSimpleStatus o_ = save(a_);
        assertEq(123,o_.getPrice());
    }
    @Test
    public void t28() {
        Repel a_ = Instances.newRepel();
        a_.setPrice(123);
        Repel o_ = save(a_);
        assertEq(123,o_.getPrice());
    }
    @Test
    public void t29() {
        SellingItem a_ = Instances.newSellingItem();
        a_.setPrice(123);
        SellingItem o_ = save(a_);
        assertEq(123,o_.getPrice());
    }
    @Test
    public void t30() {
        assertTrue(DocumentReaderAikiCoreUtil.defaultLaw(new StringList("1","")));
    }
    @Test
    public void t31() {
        PokemonData p_ = Instances.newPokemonData();
        p_.getStatistics().addEntry(Statistic.ACCURACY,new StatBaseEv((short) 1,(short) 1));
        p_.getLevMoves().add(new LevelMove((short) 1,""));
        p_.getEvolutions().addEntry("1", Instances.newEvolutionItem());
        p_.getEvolutions().addEntry("2", Instances.newEvolutionLevelSimple());
        p_.getEvolutions().addEntry("3", Instances.newEvolutionLevelGender());
        p_.getEvolutions().addEntry("4", Instances.newEvolutionMove());
        p_.getEvolutions().addEntry("5", Instances.newEvolutionMoveType());
        p_.getEvolutions().addEntry("6", Instances.newEvolutionStoneGender());
        p_.getEvolutions().addEntry("7", Instances.newEvolutionStoneSimple());
        p_.getEvolutions().addEntry("8", Instances.newEvolutionTeam());
        p_.getEvolutions().addEntry("9", Instances.newEvolutionHappiness());
        PokemonData o_ = save(p_);
        assertEq(9,o_.getEvolutions().size());
    }
    @Test
    public void t32() {
        StatusMoveData m_ = Instances.newStatusMoveData();
        StatusMoveData o_ = save(m_);
        assertEq(0,o_.getEffects().size());
    }
    @Test
    public void t33() {
        DamagingMoveData m_ = Instances.newDamagingMoveData();
        m_.getEffects().add(Instances.newEffectAccuracy());
        m_.getEffects().add(Instances.newEffectAlly());
        m_.getEffects().add(Instances.newEffectBatonPass());
        m_.getEffects().add(Instances.newEffectClone());
        m_.getEffects().add(Instances.newEffectCommonStatistics());
        m_.getEffects().add(Instances.newEffectCopyFighter());
        m_.getEffects().add(Instances.newEffectCopyMove());
        m_.getEffects().add(Instances.newEffectCounterAttack());
        m_.getEffects().add(Instances.newEffectDamage());
        m_.getEffects().add(Instances.newEffectDamageRate());
        m_.getEffects().add(Instances.newEffectEndRoundFoe());
        m_.getEffects().add(Instances.newEffectEndRoundGlobal());
        m_.getEffects().add(Instances.newEffectEndRoundIndividual());
        m_.getEffects().add(Instances.newEffectEndRoundMultiRelation());
        m_.getEffects().add(Instances.newEffectEndRoundPositionRelation());
        m_.getEffects().add(Instances.newEffectEndRoundPositionTargetRelation());
        m_.getEffects().add(Instances.newEffectEndRoundSingleRelation());
        m_.getEffects().add(Instances.newEffectEndRoundSingleStatus());
        m_.getEffects().add(Instances.newEffectEndRoundStatusRelation());
        m_.getEffects().add(Instances.newEffectEndRoundTeam());
        m_.getEffects().add(Instances.newEffectFullHpRate());
        EffectGlobal g_ = Instances.newEffectGlobal();
        g_.getMultStatIfContainsType().addEntry(new StatisticType(Statistic.ACCURACY,""),Rate.one());
        g_.getEfficiencyMoves().addEntry(new TypesDuo("",""),Rate.one());
        m_.getEffects().add(g_);
        EffectInvoke i_ = Instances.newEffectInvoke();
        i_.getMoveFctEnv().addEntry(EnvironmentType.ROCK,"");
        m_.getEffects().add(i_);
        m_.getEffects().add(Instances.newEffectMultUsedMovePower());
        m_.getEffects().add(Instances.newEffectMultSufferedMovePower());
        m_.getEffects().add(Instances.newEffectOrder());
        m_.getEffects().add(Instances.newEffectProtectFromTypes());
        m_.getEffects().add(Instances.newEffectProtection());
        m_.getEffects().add(Instances.newEffectRemainedHpRate());
        m_.getEffects().add(Instances.newEffectRestriction());
        EffectStatistic s_ = Instances.newEffectStatistic();
        s_.getLawBoost().addQuickEvent(Statistic.ACCURACY,LgInt.one());
        m_.getEffects().add(s_);
        m_.getEffects().add(Instances.newEffectStatus());
        m_.getEffects().add(Instances.newEffectSwitchAbilities());
        m_.getEffects().add(Instances.newEffectSwitchItems());
        m_.getEffects().add(Instances.newEffectSwitchMoveTypes());
        m_.getEffects().add(Instances.newEffectSwitchPosition());
        m_.getEffects().add(Instances.newEffectSwitchPointView());
        m_.getEffects().add(Instances.newEffectSwitchTypes());
        EffectTeam t_ = Instances.newEffectTeam();
        t_.getMultDamage().addEntry(new CategoryMult("",(short) 1),Rate.one());
        m_.getEffects().add(t_);
        m_.getEffects().add(Instances.newEffectTeamWhileSendFoe());
        m_.getEffects().add(Instances.newEffectUnprotectFromTypes());
        m_.getEffects().add(Instances.newEffectVarPP());
        m_.getEffects().add(Instances.newEffectWinMoney());
        DamagingMoveData o_ = save(m_);
        assertEq(m_.getEffects().size(),o_.getEffects().size());
    }
    @Test
    public void t34() {
        DataBase d_ = new DataBase(new DefaultGenerator(new CustomSeedGene()));
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element e_ = doc_.createElement(DocumentWriterCoreUtil.ANON_TAG);
        doc_.appendChild(e_);
        e_.appendChild(doc_.createElement(DocumentWriterCoreUtil.ANON_TAG));
        DocumentReaderAikiCoreUtil.heros(d_, doc_,new SexListImpl(),DocumentWriterAikiCoreUtil.KIND_IMG_HEROS_MIN, BASE);
        assertEq(0,d_.getOverWorldHeros().getList().size());
    }
    @Test
    public void t35() {
        ConcreteInteger i_ = new ConcreteInteger(1);
        DocumentReaderAikiCoreUtil.incr(i_,50,49);
        assertEq(1,i_.get());
    }
    @Test
    public void t36() {
        StatusSimple s_ = Instances.newStatusSimple();
        s_.getEffectEndRound().add(Instances.newEffectEndRoundSingleStatus());
        s_.getEffectEndRound().add(Instances.newEffectEndRoundStatusRelation());
        s_.getEffectsPartner().add(Instances.newEffectPartnerStatus());
        StatusSimple o_ = save(s_);
        assertEq(2,o_.getEffectEndRound().size());
    }
    @Test
    public void t37() {
        StatusBeginRoundAutoDamage s_ = Instances.newStatusBeginRoundAutoDamage();
        s_.getEffectEndRound().add(Instances.newEffectEndRoundSingleStatus());
        s_.getEffectEndRound().add(Instances.newEffectEndRoundStatusRelation());
        StatusBeginRoundAutoDamage o_ = save(s_);
        assertEq(2,o_.getEffectEndRound().size());
    }
    @Test
    public void t38() {
        StatusBeginRoundSimple s_ = Instances.newStatusBeginRoundSimple();
        s_.getEffectEndRound().add(Instances.newEffectEndRoundSingleStatus());
        s_.getEffectEndRound().add(Instances.newEffectEndRoundStatusRelation());
        StatusBeginRoundSimple o_ = save(s_);
        assertEq(2,o_.getEffectEndRound().size());
    }
    @Test
    public void t39() {
        ConcreteInteger i_ = new ConcreteInteger(1);
        DocumentReaderAikiCoreUtil.incr(i_,50,50);
        assertEq(2,i_.get());
    }
    @Test
    public void t40() {
        Combos c_ = Instances.newCombos();
        EffectCombo combo_ = Instances.newEffectCombo();
        combo_.getTeamMove().add(Instances.newEffectTeam());
        combo_.getEffectEndRound().add(Instances.newEffectEndRoundFoe());
        c_.getEffects().add(new ListEffectCombo(new StringList(), combo_));
        Combos o_ = save(c_);
        assertEq(1,o_.getEffects().size());
        assertEq(1,o_.getEffects().get(0).getCombo().getTeamMove().size());
        assertEq(1,o_.getEffects().get(0).getCombo().getEffectEndRound().size());
    }
    @Test
    public void t41() {
        Combos o_ = DocumentReaderAikiCoreUtil.getCombos("");
        assertEq(0,o_.getEffects().size());
    }
    @Test
    public void t42() {
        DataMap c_ = Instances.newDataMap();
        Condition i_ = Instances.newCondition();
        i_.add(Coords.newCoords(""));
        c_.getAccessCondition().addEntry(Coords.newCoords(""), i_);
        c_.getMiniMap().addEntry(new MiniMapCoords((short) 0,(short) 0),Instances.newTileMiniMap());
        Road r_ = Instances.newRoad();
        AreaApparition a_ = Instances.newAreaApparition();
        WildPk w_ = Instances.newWildPk();
        w_.setName("_");
        a_.getWildPokemon().add(w_);
        r_.getLevelRoad().getWildPokemonAreas().add(a_);
        DualFight d_ = Instances.newDualFight();
        CustList<PkTrainer> tp_ = new CustList<PkTrainer>();
        tp_.add(Instances.newPkTrainer());
        d_.getFoeTrainer().setTeam(tp_);
        r_.getLevelRoad().getDualFights().addEntry(new Point(""), d_);
        r_.getLevelRoad().getItems().addEntry(new Point(""),"");
        r_.getLevelRoad().getBlocks().addEntry(new Point(""),Instances.newBlock());
        r_.getLevelRoad().getTm().addEntry(new Point(""),(short)1);
        r_.getLevelRoad().getCharacters().addEntry(new Point("0"),Instances.newDealerItem());
        TrainerMultiFights t_ = Instances.newTrainerMultiFights();
        t_.getTeamsRewards().add(Instances.newPokemonTeam());
        r_.getLevelRoad().getCharacters().addEntry(new Point("1"),t_);
        r_.getLevelRoad().getLegendaryPks().addEntry(new Point(""),Instances.newWildPk());
        PokemonTeam tps_ = Instances.newPokemonTeam();
        tps_.getTeam().add(Instances.newPkTrainer());
        r_.getLinksWithCaves().addEntry(new Point(""),new Link(""));
        r_.getSavedlinks().addEntry(new PlaceInterConnect(""),new Coords(""));
        c_.getPlaces().add(r_);
        City ci_ = Instances.newCity();
        Gym g_ = Instances.newGym();
        g_.getIndoor().getGymTrainers().addEntry(new Point(""),Instances.newGymTrainer());
        ci_.getBuildings().addEntry(new Point("0"), g_);
        PokemonCenter pc_ = Instances.newPokemonCenter();
        pc_.getIndoor().getGerants().addEntry(new Point("0"),Instances.newGerantPokemon());
        pc_.getIndoor().getGerants().addEntry(new Point("1"),Instances.newSeller());
        pc_.getIndoor().getGerants().addEntry(new Point("2"),Instances.newDealerItem());
        pc_.getIndoor().getGerants().addEntry(new Point("3"),Instances.newGymTrainer());
        pc_.getIndoor().getGerants().addEntry(new Point("4"),Instances.newGymLeader());
        pc_.getIndoor().getGerants().addEntry(new Point("5"),Instances.newTrainerMultiFights());
        pc_.getIndoor().getGerants().addEntry(new Point("6"),Instances.newTrainerLeague());
        pc_.getIndoor().getGerants().addEntry(new Point("7"),Instances.newTempTrainer());
        ci_.getBuildings().addEntry(new Point("1"), pc_);
        c_.getPlaces().add(ci_);
        League l_ = Instances.newLeague();
        l_.getRooms().add(Instances.newLevelLeague());
        c_.getPlaces().add(l_);
        Cave ca_ = Instances.newCave();
        LevelCave lc_ = Instances.newLevelCave();
        lc_.getLinksOtherLevels().addEntry(new Point(""),new Link(""));
        ca_.getLevels().add(lc_);
        ca_.getLinksWithOtherPlaces().addEntry(new LevelPoint(""),new Link(""));
        c_.getPlaces().add(ca_);
        DataMap o_ = save(c_);
        assertEq(4,o_.getPlaces().size());
        CustList<AbsAreaApparition> wa_ = ((Road) o_.getPlace(0)).getLevelRoad().getWildPokemonAreas();
        assertEq(1, wa_.size());
        assertEq(1, wa_.get(0).getWildPokemon().size());
        assertEq("_", wa_.get(0).getWildPokemon().get(0).getName());
    }
    @Test
    public void t43() {
        DataMap o_ = DocumentReaderAikiCoreUtil.getDataMap("");
        assertEq(0,o_.getPlaces().size());
    }
    @Test
    public void t44() {
        Game game_ = Instances.newGame();
        HostPokemonDuo h_ = Instances.newHostPokemonDuo();
        PokemonPlayer pp_ = Instances.newPokemonPlayer();
        pp_.getMoves().addEntry("",new UsesOfMove(""));
        h_.setSecondPokemon(pp_);
        game_.getHostedPk().addEntry(new Coords(""), h_);
        game_.getPlayer().getTeam().add(Instances.newPokemonPlayer());
        game_.getPlayer().getTeam().add(new Egg(""));
        game_.getPlayer().setNickname("_");
        Team t_ = Instances.newTeam();
        Fighter f_ = Instances.newFighter();
        f_.getStatusRelat().addEntry(new MoveTeamPosition("",new TeamPosition((byte) 0,(byte) 0)),(short)0);
        f_.getIncrUserAccuracy().addEntry(new MoveTeamPosition("",new TeamPosition((byte) 0,(byte) 0)),BoolVal.FALSE);
        f_.getEnabledMoves().addEntry("",new ActivityOfMove(""));
        f_.getTrappingMoves().addEntry(new MoveTeamPosition("",new TeamPosition((byte) 0,(byte) 0)),new ActivityOfMove(""));
        f_.getCopiedMoves().addEntry("",new CopiedMove(""));
        f_.getPrivateMoves().addEntry(new MoveTeamPosition("",new TeamPosition((byte) 0,(byte) 0)),new StringList());
        f_.getTrackingMoves().addEntry(new MoveTeamPosition("",new TeamPosition((byte) 0,(byte) 0)),new AffectedMove(""));
        f_.getMovesAbilitiesEvos().addEntry("",Instances.newMovesAbilities());
        f_.setAction(null);
        t_.getMembers().addEntry((byte)0, f_);
        Fighter f1_ = Instances.newFighter();
        ActionMove tcp_ = Instances.newActionMove();
        tcp_.getChosenTargets().add(new TargetCoords(""));
        f1_.setAction(tcp_);
        t_.getMembers().addEntry((byte)1, f1_);
        Fighter f2_ = Instances.newFighter();
        f2_.setAction(Instances.newActionSwitch());
        t_.getMembers().addEntry((byte)2, f2_);
        Fighter f3_ = Instances.newFighter();
        f3_.setAction(Instances.newActionHealMove());
        t_.getMembers().addEntry((byte)3, f3_);
        Fighter f4_ = Instances.newFighter();
        f4_.setAction(Instances.newActionSimpleHeal());
        t_.getMembers().addEntry((byte)4, f4_);
        ByteMap<Anticipation> bm_ = new ByteMap<Anticipation>();
        bm_.addEntry((byte)0,new Anticipation());
        t_.getMovesAnticipation().addEntry("", bm_);
        t_.getEnabledMovesByGroup().add(new ListActivityOfMove(new StringList(),new ActivityOfMove("")));
        ByteMap<StacksOfUses> bs_ = new ByteMap<StacksOfUses>();
        bs_.addEntry((byte)0,new StacksOfUses(""));
        t_.getHealAfter().addEntry("", bs_);
        game_.getFight().getChoices().addEntry((byte)0,Instances.newChoiceOfEvolutionAndMoves());
        game_.getFight().getTeams().addEntry((byte)0, t_);
        game_.getFight().getAllyChoice().addEntry(new MoveTarget(""),new MoveTarget(""));
        game_.getBeatTrainer().addEntry(new NbFightCoords(new Coords(""),0),BoolVal.FALSE);
        CatchingBallFoeAction ca1_ = Instances.newCatchingBallFoeAction();
        ca1_.setCaught(true);
        ca1_.setTeam(true);
        game_.getFight().getCatchingBalls().add(ca1_);
        CatchingBallFoeAction ca2_ = Instances.newCatchingBallFoeAction();
        ca2_.setCaught(false);
        ca2_.setTeam(false);
        game_.getFight().getCatchingBalls().add(ca2_);
        game_.getBeatGymLeader().addEntry(new Coords(""),BoolVal.FALSE);
        PointEqList pts_ = new PointEqList();
        pts_.add(new Point(""));
        game_.getBeatGymTrainer().addEntry((short)0, pts_);
        Game s_ = save(game_);
        assertEq("_",s_.getPlayer().getNickname());
    }
    @Test
    public void t45() {
        assertNull(DocumentReaderAikiCoreUtil.getGame("",new SexListImpl()));
    }
    @Test
    public void t46() {
        assertNull(DocumentReaderAikiCoreUtil.getGame(LOADING_GAME_MINI,new SexListImpl()));
    }
    @Test
    public void t47() {
        assertNull(getGameOrNull(""));
    }
    @Test
    public void t48() {
        assertNull(getGameOrNull(LOADING_GAME_MINI));
    }
    @Test
    public void t_48() {
        assertNull(getGameOrNull(OTHER_MINI));
    }
    @Test
    public void t__48() {
        assertNull(getGameOrNull(OTHER_SEC_MINI));
    }
    @Test
    public void t49() {
        Game g_ = getGameOrNull(GAME_MINI);
        assertNotNull(g_);
    }
    @Test
    public void t50() {
        LoadingGame l_ = new LoadingGame();
        LoadingGame o_ = save(l_);
        assertEq("",o_.getLastRom());
    }
    @Test
    public void t51() {
        LoadingGame o_ = DocumentReaderAikiCoreUtil.getLoadingGame("");
        assertEq("",o_.getLastRom());
    }
    @Test
    public void t52() {
        assertNull(getLoadingGameOrNull(""));
    }
    @Test
    public void t53() {
        assertNull(getLoadingGameOrNull(GAME_MINI));
    }
    @Test
    public void t_53() {
        assertNull(getLoadingGameOrNull(OTHER_MINI));
    }
    @Test
    public void t__53() {
        assertNull(getLoadingGameOrNull(OTHER_SEC_MINI));
    }
    @Test
    public void t54() {
        LoadingGame o_ = getLoadingGameOrNull(LOADING_GAME_MINI);
        assertNotNull(o_);
    }
    @Test
    public void t55() {
        FacadeGame f_ = new FacadeGame();
        f_.setData(new DataBase(new DefaultGenerator(DefaultGenerator.oneEltGene())));
        f_.getData().setLanguage("");
        f_.getData().setLanguages(new StringList(""));
        f_.getData().getPokedex().addEntry("P",Instances.newPokemonData());
        f_.getData().getMoves().addEntry("",Instances.newDamagingMoveData());
        f_.getData().getMoves().addEntry("M",Instances.newDamagingMoveData());
        f_.getData().getStatus().addEntry("",Instances.newStatusSimple());
        f_.getData().getStatus().addEntry("S",Instances.newStatusSimple());
        f_.getData().getItems().addEntry("",Instances.newItemForBattle());
        f_.getData().getItems().addEntry("I",Instances.newItemForBattle());
        f_.getData().getAbilities().addEntry("",Instances.newAbilityData());
        f_.getData().getAbilities().addEntry("A",Instances.newAbilityData());
        f_.getData().getCombos().setEffects(new ListEffectCombos());
        f_.getData().setMap(Instances.newDataMap());
        f_.getData().setAnimAbsorb(new int[1][1]);
        f_.getData().setImageTmHm(new int[1][1]);
        f_.getData().setStorage(new int[1][1]);
        f_.getData().setEndGameImage(new int[1][1]);
        FacadeGame s_ = save(f_);
        assertEq(1,s_.getData().getPokedex().size());
        assertEq("",DocumentReaderAikiCoreUtil.check("",f_.getData()));
    }
    @Test
    public void t56() {
        FacadeGame f_ = new FacadeGame();
        f_.setData(new DataBase(new DefaultGenerator(DefaultGenerator.oneEltGene())));
        f_.getData().setLanguage("");
        f_.getData().setLanguages(new StringList(""));
        f_.getData().getPokedex().addEntry("P",Instances.newPokemonData());
        f_.getData().getMoves().addEntry("",Instances.newDamagingMoveData());
        f_.getData().getMoves().addEntry("M",Instances.newDamagingMoveData());
        f_.getData().getStatus().addEntry("",Instances.newStatusSimple());
        f_.getData().getItems().addEntry("",Instances.newItemForBattle());
        f_.getData().getAbilities().addEntry("",Instances.newAbilityData());
        f_.getData().getCombos().setEffects(new ListEffectCombos());
        f_.getData().setMap(Instances.newDataMap());
        f_.getData().setAnimAbsorb(new int[1][1]);
        f_.getData().setImageTmHm(new int[1][1]);
        f_.getData().setStorage(new int[1][1]);
        f_.getData().setEndGameImage(new int[1][1]);
        IdMap<Statistic, String> st_ = new IdMap<Statistic, String>();
        st_.addEntry(Statistic.ACCURACY,"_");
        f_.getData().getTranslatedStatistics().addEntry("_", st_);
        f_.getData().getTranslatedPokemon().addEntry("_", one());
        f_.getData().getTranslatedMoves().addEntry("_", one());
        f_.getData().getTranslatedItems().addEntry("_", one());
        f_.getData().getTranslatedStatus().addEntry("_", one());
        f_.getData().getTranslatedAbilities().addEntry("_", one());
        IdMap<DifficultyWinPointsFight, String> dwp_ = new IdMap<DifficultyWinPointsFight, String>();
        dwp_.addEntry(DifficultyWinPointsFight.FACILE, "_");
        f_.getData().getTranslatedDiffWinPts().addEntry("_", dwp_);
        IdMap<DifficultyModelLaw, String> dml_ = new IdMap<DifficultyModelLaw, String>();
        dml_.addEntry(DifficultyModelLaw.CONSTANT_MAX, "_");
        f_.getData().getTranslatedDiffModelLaw().addEntry("_", dml_);
        f_.getData().getTranslatedTypes().addEntry("_",one());
        f_.getData().getTranslatedClassesDescriptions().addEntry("_",one());
        f_.getData().getTranslatedFctMath().addEntry("_",one());
        f_.getData().getTranslatedCategories().addEntry("_",one());
        f_.getData().getLitterals().addEntry("_",one());
        IdMap<Gender, String> g_ = new IdMap<Gender, String>();
        g_.addEntry(Gender.NONE, "");
        f_.getData().getTranslatedGenders().addEntry("_", g_);
        IdMap<SelectedBoolean, String> bol_ = new IdMap<SelectedBoolean, String>();
        bol_.addEntry(SelectedBoolean.YES_AND_NO, "_");
        f_.getData().getTranslatedBooleans().addEntry("_", bol_);
        IdMap<EnvironmentType, String> et_ = new IdMap<EnvironmentType, String>();
        et_.addEntry(EnvironmentType.NOTHING, "_");
        f_.getData().getTranslatedEnvironment().addEntry("_", et_);
        IdMap<TargetChoice, String> tc_ = new IdMap<TargetChoice, String>();
        tc_.addEntry(TargetChoice.NOTHING, "_");
        f_.getData().getTranslatedTargets().addEntry("_", tc_);
        f_.getData().getAnimStatus().addEntry("",new int[1][1]);
        f_.getData().getAnimStatis().addEntry("",new int[1][1]);
        f_.getData().getAnimStatus().addEntry("_",new int[1][1]);
        f_.getData().getAnimStatis().addEntry("_",new int[1][1]);
        f_.getData().getExpGrowth().addEntry(ExpType.E, "_");
        f_.getData().getRates().addEntry(DifficultyWinPointsFight.FACILE, "_");
        f_.getData().getTypesColors().addEntry("_","_");
        f_.getData().getLawsDamageRate().addEntry(DifficultyModelLaw.UNIFORME, new LawNumber(new MonteCarloNumber(),(short)0));
        MonteCarloNumber badLaw_ = new MonteCarloNumber();
        badLaw_.addQuickEvent(Rate.one(), LgInt.minusOne());
        f_.getData().getLawsDamageRate().addEntry(DifficultyModelLaw.CONSTANT_MIN, new LawNumber(badLaw_,(short)1));
        f_.getData().getTableTypes().addEntry(new TypesDuo("_1","_2"),Rate.one());
        f_.getData().getTableTypes().addEntry(new TypesDuo("_2","_1"),Rate.one());
        f_.getData().getTableTypes().addEntry(new TypesDuo("_1","_1"),Rate.one());
        f_.getData().getTableTypes().addEntry(new TypesDuo("_2","_2"),Rate.one());
        f_.getData().getTableTypes().addEntry(new TypesDuo("10","10"),Rate.one());
        f_.getData().getTableTypes().addEntry(new TypesDuo("1_","1_"),Rate.one());
        f_.getData().getTableTypes().addEntry(new TypesDuo("10","1_"),Rate.one());
        f_.getData().getTableTypes().addEntry(new TypesDuo("1_","10"),Rate.one());
        f_.getData().getTypesImages().addEntry("",new int[1][1]);
        f_.getData().getTypesImages().addEntry("_",new int[1][1]);
        f_.getData().getMiniItems().addEntry("",new int[1][1]);
        f_.getData().getMiniItems().addEntry("_",new int[1][1]);
        f_.getData().getMiniPk().addEntry("",new int[1][1]);
        f_.getData().getMiniPk().addEntry("_",new int[1][1]);
        f_.getData().getMaxiPkFront().addEntry("",new int[1][1]);
        f_.getData().getMaxiPkFront().addEntry("_",new int[1][1]);
        f_.getData().getMaxiPkBack().addEntry("",new int[1][1]);
        f_.getData().getMaxiPkBack().addEntry("_",new int[1][1]);
        f_.getData().getTrainers().addEntry("_",new int[1][1]);
        f_.getData().getPeople().addEntry("_",new int[1][1]);
        f_.getData().getLinks().addEntry("_",new int[1][1]);
        f_.getData().getMiniMap().addEntry("_",new int[1][1]);
        f_.getData().getImages().addEntry("_",new int[1][1]);
        f_.getData().getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING, Direction.UP, Sex.NO), new int[1][1]);
        f_.getData().getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING, Direction.UP, Sex.NO), new int[1][1]);
        f_.getData().getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.NOTHING, Direction.UP, Sex.NO), new int[1][1]);
        f_.getData().getTmPrice().addEntry((short)1,LgInt.one());
        f_.getData().getTm().addEntry((short)1,"");
        f_.getData().getHm().addEntry((short)1,"");
        f_.getData().getConstNum().addEntry("_",Rate.one());
        FacadeGame s_ = save(f_);
        assertEq(1,s_.getData().getPokedex().size());
    }

    @Test
    public void t57() {
        TypesDuos from_ = new TypesDuos();
        from_.addEntry(new TypesDuo("_1","_1"),Rate.one());
        from_.addEntry(new TypesDuo("_2","_1"),new Rate(2));
        from_.addEntry(new TypesDuo("_1","_2"),new Rate(3));
        from_.addEntry(new TypesDuo("_2","_2"),new Rate(4));
        TypesDuos table_ = new TypesDuos();
        DocumentReaderAikiCoreUtil.tableTypes(table_, DocumentWriterAikiCoreUtil.tableTypes(from_));
        assertEq(4,table_.getList().size());
        assertEq(Rate.one(),table_.getVal(new TypesDuo("_1","_1")));
        assertEq(new Rate(2),table_.getVal(new TypesDuo("_2","_1")));
        assertEq(new Rate(3),table_.getVal(new TypesDuo("_1","_2")));
        assertEq(new Rate(4),table_.getVal(new TypesDuo("_2","_2")));
    }

    @Test
    public void t58() {
        assertEq(Rate.one(),DocumentReaderAikiCoreUtil.rateToOne(""));
    }

    @Test
    public void t59() {
        LoadingGame lg_ = new LoadingGame();
        lg_.setSaveGameAtExit(false);
        assertFalse(lg_.loadRomAndGame());
    }
    @Test
    public void t60() {
        assertTrue(new LoadingGame().loadRomAndGame());
    }
    @Test
    public void t61() {
        LoadingGame lg_ = new LoadingGame();
        lg_.setLoadLastGame(true);
        assertTrue(lg_.loadRomAndGame());
    }
    @Test
    public void t62() {
        assertEq(NAME,retrieve(DocumentReaderAikiCoreUtil.getStatus(doc()).getData()).getKey());
    }
    @Test
    public void t63() {
        assertEq(NAME,retrieve(DocumentReaderAikiCoreUtil.getMoveData(doc()).getData()).getKey());
    }
    @Test
    public void t64() {
        assertEq(NAME,retrieve(DocumentReaderAikiCoreUtil.getItem(doc()).getData()).getKey());
    }
    @Test
    public void t65() {
        assertEq(NAME,retrieve(DocumentReaderAikiCoreUtil.getPokemonData(doc()).getData()).getKey());
    }
    @Test
    public void t66() {
        assertEq(NAME,retrieve(DocumentReaderAikiCoreUtil.getAbilityData(doc()).getData()).getKey());
    }
    @Test
    public void t67() {
        FacadeGame f_ = new FacadeGame();
        f_.setData(new DataBase(new DefaultGenerator(DefaultGenerator.oneEltGene())));
        f_.getData().setLanguage("");
        f_.getData().setLanguages(new StringList(""));
        StringMap<String> txt_ = new StringMap<String>();
        txt_.addEntry("_","");
        DocumentReaderAikiCoreUtil.loadRom(f_.getData(), txt_,new ConcreteInteger(),new SexListImpl(), baseParse());
        assertEq(0,f_.getData().getPokedex().size());
    }
    @Test
    public void t68() {
        DataMap c_ = Instances.newDataMap();
        Condition i_ = Instances.newCondition();
        i_.add(Coords.newCoords(""));
        c_.getAccessCondition().addEntry(Coords.newCoords(""), i_);
        c_.getMiniMap().addEntry(new MiniMapCoords((short) 0,(short) 0),Instances.newTileMiniMap());
        Road r_ = Instances.newRoad();
        MultAreaApparition a_ = new MultAreaApparition();
        WildPk w_ = Instances.newWildPk();
        w_.setName("P1");
        a_.getWildPokemonList().add(new CustList<WildPk>(w_));
        WildPk w2_ = Instances.newWildPk();
        w2_.setName("P2");
        WildPk w3_ = Instances.newWildPk();
        w3_.setName("P3");
        a_.getWildPokemonList().add(new CustList<WildPk>(w2_,w3_));
        r_.getLevelRoad().getWildPokemonAreas().add(a_);
        DualFight d_ = Instances.newDualFight();
        CustList<PkTrainer> tp_ = new CustList<PkTrainer>();
        tp_.add(Instances.newPkTrainer());
        d_.getFoeTrainer().setTeam(tp_);
        r_.getLevelRoad().getDualFights().addEntry(new Point(""), d_);
        r_.getLevelRoad().getItems().addEntry(new Point(""),"");
        r_.getLevelRoad().getBlocks().addEntry(new Point(""),Instances.newBlock());
        r_.getLevelRoad().getTm().addEntry(new Point(""),(short)1);
        r_.getLevelRoad().getCharacters().addEntry(new Point("0"),Instances.newDealerItem());
        TrainerMultiFights t_ = Instances.newTrainerMultiFights();
        t_.getTeamsRewards().add(Instances.newPokemonTeam());
        r_.getLevelRoad().getCharacters().addEntry(new Point("1"),t_);
        r_.getLevelRoad().getLegendaryPks().addEntry(new Point(""),Instances.newWildPk());
        PokemonTeam tps_ = Instances.newPokemonTeam();
        tps_.getTeam().add(Instances.newPkTrainer());
        r_.getLinksWithCaves().addEntry(new Point(""),new Link(""));
        r_.getSavedlinks().addEntry(new PlaceInterConnect(""),new Coords(""));
        c_.getPlaces().add(r_);
        City ci_ = Instances.newCity();
        Gym g_ = Instances.newGym();
        g_.getIndoor().getGymTrainers().addEntry(new Point(""),Instances.newGymTrainer());
        ci_.getBuildings().addEntry(new Point("0"), g_);
        PokemonCenter pc_ = Instances.newPokemonCenter();
        pc_.getIndoor().getGerants().addEntry(new Point("0"),Instances.newGerantPokemon());
        pc_.getIndoor().getGerants().addEntry(new Point("1"),Instances.newSeller());
        pc_.getIndoor().getGerants().addEntry(new Point("2"),Instances.newDealerItem());
        pc_.getIndoor().getGerants().addEntry(new Point("3"),Instances.newGymTrainer());
        pc_.getIndoor().getGerants().addEntry(new Point("4"),Instances.newGymLeader());
        pc_.getIndoor().getGerants().addEntry(new Point("5"),Instances.newTrainerMultiFights());
        pc_.getIndoor().getGerants().addEntry(new Point("6"),Instances.newTrainerLeague());
        pc_.getIndoor().getGerants().addEntry(new Point("7"),Instances.newTempTrainer());
        ci_.getBuildings().addEntry(new Point("1"), pc_);
        c_.getPlaces().add(ci_);
        League l_ = Instances.newLeague();
        l_.getRooms().add(Instances.newLevelLeague());
        c_.getPlaces().add(l_);
        Cave ca_ = Instances.newCave();
        LevelCave lc_ = Instances.newLevelCave();
        lc_.getLinksOtherLevels().addEntry(new Point(""),new Link(""));
        ca_.getLevels().add(lc_);
        ca_.getLinksWithOtherPlaces().addEntry(new LevelPoint(""),new Link(""));
        c_.getPlaces().add(ca_);
        DataMap o_ = save(c_);
        assertEq(4,o_.getPlaces().size());
        CustList<AbsAreaApparition> wa_ = ((Road) o_.getPlace(0)).getLevelRoad().getWildPokemonAreas();
        assertEq(1, wa_.size());
        assertEq(2, wa_.get(0).getWildPokemonList().size());
        assertEq(1, wa_.get(0).getWildPokemonList().get(0).size());
        assertEq("P1", wa_.get(0).getWildPokemonList().get(0).get(0).getName());
        assertEq(2, wa_.get(0).getWildPokemonList().get(1).size());
        assertEq("P2", wa_.get(0).getWildPokemonList().get(1).get(0).getName());
        assertEq("P3", wa_.get(0).getWildPokemonList().get(1).get(1).getName());
    }
    private StringMap<String> one() {
        StringMap<String> o_ = new StringMap<String>();
        o_.addEntry("_","_");
        return o_;
    }

    private Document doc() {
        Document d_ = DocumentBuilder.newXmlDocument();
        d_.appendChild(d_.createElement(DocumentWriterCoreUtil.ANON_TAG));
        return d_;
    }

    private Game getGameOrNull(String _str) {
        return DocumentReaderAikiCoreUtil.getGameOrNull(DocumentBuilder.parseNoTextDocument(_str), new SexListImpl());
    }

    private LoadingGame getLoadingGameOrNull(String _str) {
        return DocumentReaderAikiCoreUtil.getLoadingGameOrNull(DocumentBuilder.parseNoTextDocument(_str));
    }

}
