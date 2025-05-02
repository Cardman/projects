package aiki.gui;

import aiki.db.*;
import aiki.facade.*;
import aiki.fight.abilities.*;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.evolution.*;
import aiki.fight.status.StatusSimple;
import aiki.fight.util.*;
import aiki.gui.components.editor.*;
import aiki.instances.*;
import aiki.map.*;
import aiki.map.buildings.*;
import aiki.map.characters.*;
import aiki.map.enums.Direction;
import aiki.map.levels.*;
import aiki.map.levels.enums.*;
import aiki.map.places.*;
import aiki.map.util.*;
import aiki.sml.*;
import code.gui.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.threads.*;
import code.util.*;
import org.junit.Test;

public final class EditorAppFormTest extends InitEditorPkForm {
    @Test
    public void validateCopy() {
        DataBase dataBase_ = ConverterCommonMapUtil.validateData(initDb(), new ConcreteInteger(), new ConcreteBoolean(), new MockLSexList());
        assertFalse(dataBase_.isError());
    }
    @Test
    public void saveLoad() {
        FacadeGame f_ = new FacadeGame();
        f_.setSexList(new MockLSexList());
        f_.setData(initDb());
        MockProgramInfos api_ = initForms();
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        ConverterCommonMapUtil.saveData(api_,"/__/_", f_);
        assertFalse(ConverterCommonMapUtil.endValidate(new ConcreteInteger(), new ConcreteBoolean(),f_.getSexList(),ConverterCommonMapUtil.loadData(api_,"/__/_",f_)).isError());
    }
    @Test
    public void patchData1() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        db_.getLitterals().clear();
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getLitterals().size());
        assertEq(DataBaseConstants.MAX_EXCLUSIVE-1,res_.getLitterals().getValue(0).size());
        assertEq(DataBaseConstants.MAX_EXCLUSIVE-1,res_.getLitterals().getValue(1).size());
    }
    @Test
    public void patchData2() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        db_.getLitterals().getValue(0).clear();
        db_.getLitterals().getValue(1).clear();
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getLitterals().size());
        assertEq(DataBaseConstants.MAX_EXCLUSIVE-1,res_.getLitterals().getValue(0).size());
        assertEq(DataBaseConstants.MAX_EXCLUSIVE-1,res_.getLitterals().getValue(1).size());
    }
    @Test
    public void patchData3() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        db_.defValues();
        db_.getLitterals().getValue(0).clear();
        db_.getLitterals().getValue(1).clear();
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getLitterals().size());
        assertEq(DataBaseConstants.MAX_EXCLUSIVE-1,res_.getLitterals().getValue(0).size());
        assertEq(DataBaseConstants.MAX_EXCLUSIVE-1,res_.getLitterals().getValue(1).size());
    }
    @Test
    public void patchData4() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        db_.getAbilities().addEntry(DataBase.EMPTY_STRING, Instances.newAbilityData());
        db_.getItems().addEntry(DataBase.EMPTY_STRING, Instances.newSellingItem());
        db_.getMoves().addEntry(DataBase.EMPTY_STRING, Instances.newDamagingMoveData());
        db_.getMoves().addEntry(M_1, Instances.newDamagingMoveData());
        db_.getMoves().addEntry(M_2, Instances.newStatusMoveData());
        db_.getPokedex().addEntry(DataBase.EMPTY_STRING, Instances.newPokemonData());
        PokemonData pk_ = Instances.newPokemonData();
        pk_.getTypes().add(DataBase.EMPTY_STRING);
        pk_.getTypes().add(T_1);
        db_.getPokedex().addEntry(P_1, pk_);
        db_.getStatus().addEntry(DataBase.EMPTY_STRING, Instances.newStatusSimple());
        db_.getMiniPk().addEntry(DataBase.EMPTY_STRING,instance(new int[0][0]));
        db_.getTranslatedCategories().getValue(0).addEntry(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        db_.getTranslatedCategories().getValue(1).addEntry(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        db_.getTypesColors().addEntry(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedPokemon().size());
        assertEq(1,res_.getTranslatedPokemon().getValue(0).size());
        assertEq(1,res_.getTranslatedPokemon().getValue(1).size());
        assertEq(2,res_.getTranslatedTypes().size());
        assertEq(1,res_.getTranslatedTypes().getValue(0).size());
        assertEq(T_1,res_.getTranslatedTypes().getValue(0).getKey(0));
        assertEq(1,res_.getTranslatedTypes().getValue(1).size());
        assertEq(T_1,res_.getTranslatedTypes().getValue(1).getKey(0));
    }
    @Test
    public void patchData5() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        db_.getTranslatedStatistics().clear();
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedStatistics().size());
        assertEq(10,res_.getTranslatedStatistics().getValue(0).size());
        assertEq(10,res_.getTranslatedStatistics().getValue(1).size());
    }
    @Test
    public void patchData6() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        db_.getTranslatedPokemon().getValue(0).addEntry(P_1,P_1);
        db_.getTranslatedPokemon().getValue(0).addEntry(P_2,P_1);
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedPokemon().size());
        assertEq(2,res_.getTranslatedPokemon().getValue(0).size());
        assertEq(2,res_.getTranslatedPokemon().getValue(1).size());
    }
    @Test
    public void patchData7() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        PokemonData p_ = Instances.newPokemonData();
        p_.getEvolutions().addEntry(P_2,Instances.newEvolutionHappiness());
        p_.getEvolutions().addEntry(NULL_REF,Instances.newEvolutionHappiness());
        db_.getPokedex().addEntry(P_1, p_);
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedPokemon().size());
        assertEq(2,res_.getTranslatedPokemon().getValue(0).size());
        assertEq(2,res_.getTranslatedPokemon().getValue(1).size());
    }
    @Test
    public void patchData8() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        PokemonData p_ = Instances.newPokemonData();
        EvolutionItem e_ = Instances.newEvolutionItem();
        e_.setItem(I_1);
        p_.getEvolutions().addEntry(P_2, e_);
        db_.getPokedex().addEntry(P_1, p_);
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedPokemon().size());
        assertEq(2,res_.getTranslatedPokemon().getValue(0).size());
        assertEq(2,res_.getTranslatedPokemon().getValue(1).size());
        assertEq(2,res_.getTranslatedItems().size());
        assertEq(1,res_.getTranslatedItems().getValue(0).size());
        assertEq(1,res_.getTranslatedItems().getValue(1).size());
    }
    @Test
    public void patchData9() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        PokemonData p_ = Instances.newPokemonData();
        EvolutionStone e_ = Instances.newEvolutionStoneSimple();
        e_.setStone(I_1);
        p_.getEvolutions().addEntry(P_2, e_);
        db_.getPokedex().addEntry(P_1, p_);
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedPokemon().size());
        assertEq(2,res_.getTranslatedPokemon().getValue(0).size());
        assertEq(2,res_.getTranslatedPokemon().getValue(1).size());
        assertEq(2,res_.getTranslatedItems().size());
        assertEq(1,res_.getTranslatedItems().getValue(0).size());
        assertEq(1,res_.getTranslatedItems().getValue(1).size());
    }
    @Test
    public void patchData10() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        PokemonData p_ = Instances.newPokemonData();
        EvolutionMove e_ = Instances.newEvolutionMove();
        e_.setMove(M_1);
        p_.getEvolutions().addEntry(P_2, e_);
        db_.getPokedex().addEntry(P_1, p_);
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedPokemon().size());
        assertEq(2,res_.getTranslatedPokemon().getValue(0).size());
        assertEq(2,res_.getTranslatedPokemon().getValue(1).size());
        assertEq(2,res_.getTranslatedMoves().size());
        assertEq(1,res_.getTranslatedMoves().getValue(0).size());
        assertEq(1,res_.getTranslatedMoves().getValue(1).size());
    }
    @Test
    public void patchData11() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        PokemonData p_ = Instances.newPokemonData();
        EvolutionMoveType e_ = Instances.newEvolutionMoveType();
        e_.setType(T_1);
        p_.getEvolutions().addEntry(P_2, e_);
        EvolutionTeam t_ = Instances.newEvolutionTeam();
        t_.setPokemon(P_4);
        p_.getEvolutions().addEntry(P_3, t_);
        db_.getPokedex().addEntry(P_1, p_);
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedPokemon().size());
        assertEq(4,res_.getTranslatedPokemon().getValue(0).size());
        assertEq(4,res_.getTranslatedPokemon().getValue(1).size());
        assertEq(2,res_.getTranslatedTypes().size());
        assertEq(1,res_.getTranslatedTypes().getValue(0).size());
        assertEq(1,res_.getTranslatedTypes().getValue(1).size());
    }
    @Test
    public void patchData12() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        MoveData m_ = Instances.newDamagingMoveData();
        m_.getSecEffectsByItem().addEntry(NULL_REF,new Ints());
        m_.getSecEffectsByItem().addEntry(I_1,new Ints());
        m_.getSecEffectsByItem().addEntry("__",new Ints());
        m_.getTypesByOwnedItem().addEntry(NULL_REF,T_1);
        m_.getTypesByOwnedItem().addEntry(I_2,T_2);
        m_.getTypesByOwnedItem().addEntry("__",NULL_REF);
        m_.getTypesByOwnedItem().addEntry(I_3,"__");
        m_.getTypesByWeather().addEntry(NULL_REF,T_1);
        m_.getTypesByWeather().addEntry(M_2,T_3);
        db_.getMoves().addEntry(M_1,m_);
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedMoves().size());
        assertEq(2,res_.getTranslatedMoves().getValue(0).size());
        assertEq(2,res_.getTranslatedMoves().getValue(1).size());
        assertEq(2,res_.getTranslatedTypes().size());
        assertEq(3,res_.getTranslatedTypes().getValue(0).size());
        assertEq(3,res_.getTranslatedTypes().getValue(1).size());
        assertEq(2,res_.getTranslatedItems().size());
        assertEq(3,res_.getTranslatedItems().getValue(0).size());
        assertEq(3,res_.getTranslatedItems().getValue(1).size());
    }
    @Test
    public void patchData13() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        MoveData m_ = Instances.newDamagingMoveData();
        EffectCounterAttack c_ = Instances.newEffectCounterAttack();
        c_.getSufferingDamageTypes().addEntry(T_1, Rate.one());
        c_.getSufferingDamageTypes().addEntry(NULL_REF, Rate.one());
        m_.getEffects().add(c_);
        EffectDamage d_ = Instances.newEffectDamage();
        d_.getMultDamageAgainst().addEntry(C_1, Rate.one());
        d_.getMultDamageAgainst().addEntry(NULL_REF, Rate.one());
        m_.getEffects().add(d_);
        db_.getMoves().addEntry(M_1,m_);
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedMoves().size());
        assertEq(1,res_.getTranslatedMoves().getValue(0).size());
        assertEq(1,res_.getTranslatedMoves().getValue(1).size());
        assertEq(2,res_.getTranslatedTypes().size());
        assertEq(1,res_.getTranslatedTypes().getValue(0).size());
        assertEq(1,res_.getTranslatedTypes().getValue(1).size());
        assertEq(2,res_.getTranslatedCategories().size());
        assertEq(1,res_.getTranslatedCategories().getValue(0).size());
        assertEq(1,res_.getTranslatedCategories().getValue(1).size());
    }
    @Test
    public void patchData14() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        MoveData m_ = Instances.newDamagingMoveData();
        m_.getEffects().add(Instances.newEffectEndRoundIndividual());
        m_.getEffects().add(Instances.newEffectEndRoundMultiRelation());
        EffectGlobal d_ = Instances.newEffectGlobal();
        d_.getEfficiencyMoves().addEntry(new TypesDuo(T_1,NULL_REF),Rate.one());
        d_.getEfficiencyMoves().addEntry(new TypesDuo(NULL_REF,T_2),Rate.one());
        d_.getEfficiencyMoves().addEntry(new TypesDuo(T_1,T_2),Rate.one());
        d_.getMultStatIfContainsType().addEntry(new StatisticType(Statistic.SPEED,T_3),Rate.one());
        d_.getMultStatIfContainsType().addEntry(new StatisticType(Statistic.SPEED,NULL_REF),Rate.one());
        m_.getEffects().add(d_);
        db_.getMoves().addEntry(M_1,m_);
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedTypes().size());
        assertEq(3,res_.getTranslatedTypes().getValue(0).size());
        assertEq(3,res_.getTranslatedTypes().getValue(1).size());
    }
    @Test
    public void patchData15() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        MoveData m_ = Instances.newDamagingMoveData();
        EffectInvoke i_ = Instances.newEffectInvoke();
        i_.getMoveFctEnv().addEntry(EnvironmentType.NOTHING,NULL_REF);
        i_.getMoveFctEnv().addEntry(EnvironmentType.ROAD,M_2);
        m_.getEffects().add(i_);
        m_.getEffects().add(Instances.newEffectMultUsedMovePower());
        m_.getEffects().add(Instances.newEffectProtectFromTypes());
        m_.getEffects().add(Instances.newEffectStatus());
        m_.getEffects().add(Instances.newEffectSwitchAbilities());
        EffectSwitchMoveTypes mt_ = Instances.newEffectSwitchMoveTypes();
        mt_.getChangeTypes().addEntry(NULL_REF,T_3);
        mt_.getChangeTypes().addEntry(T_3,NULL_REF);
        mt_.getChangeTypes().addEntry(T_3,T_3);
        m_.getEffects().add(mt_);
        m_.getEffects().add(Instances.newEffectSwitchTypes());
        EffectTeam t_ = Instances.newEffectTeam();
        t_.getMultDamage().addEntry(new CategoryMult(C_1, 0),Rate.one());
        t_.getMultDamage().addEntry(new CategoryMult(NULL_REF, 0),Rate.one());
        m_.getEffects().add(t_);
        EffectUnprotectFromTypes u_ = Instances.newEffectUnprotectFromTypes();
        u_.getTypes().add(new TypesDuo(NULL_REF,NULL_REF));
        u_.getTypes().add(new TypesDuo(T_1,T_2));
        m_.getEffects().add(u_);
        m_.getEffects().add(Instances.newEffectTeamWhileSendFoe());
        db_.getMoves().addEntry(M_1,m_);
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedCategories().size());
        assertEq(1,res_.getTranslatedCategories().getValue(0).size());
        assertEq(1,res_.getTranslatedCategories().getValue(1).size());
        assertEq(2,res_.getTranslatedMoves().size());
        assertEq(2,res_.getTranslatedMoves().getValue(0).size());
        assertEq(2,res_.getTranslatedMoves().getValue(1).size());
        assertEq(2,res_.getTranslatedTypes().size());
        assertEq(3,res_.getTranslatedTypes().getValue(0).size());
        assertEq(3,res_.getTranslatedTypes().getValue(1).size());
    }
    @Test
    public void patchData16() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        AbilityData a_ = Instances.newAbilityData();
        a_.getHealHpByTypeIfWeather().addEntry(new WeatherType(M_1,T_1),Rate.one());
        a_.getHealHpByTypeIfWeather().addEntry(new WeatherType(NULL_REF,T_1),Rate.one());
        a_.getHealHpByTypeIfWeather().addEntry(new WeatherType(M_1,NULL_REF),Rate.one());
        a_.getImmuStatusTypes().addEntry(T_1,new StringList());
        a_.getImmuStatusTypes().addEntry(NULL_REF,new StringList());
        a_.getImmuStatus().addEntry(M_1,new StringList());
        a_.getImmuStatus().addEntry(NULL_REF,new StringList());
        a_.getImmuStatus().addEntry("__",new StringList());
        a_.getChangingBoostTypes().addEntry(T_1,new TypeDamageBoost(T_1,Rate.one()));
        a_.getChangingBoostTypes().addEntry(T_1,new TypeDamageBoost(NULL_REF,Rate.one()));
        a_.getChangingBoostTypes().addEntry(NULL_REF,new TypeDamageBoost(T_1,Rate.one()));
        a_.getImmuLowStatIfStatus().add(new StatisticStatus(Statistic.SPEED,NULL_REF));
        a_.getImmuLowStatIfStatus().add(new StatisticStatus(Statistic.SPEED,S_1));
        a_.getMultStatIfStatutRank().addEntry(new StatisticStatus(Statistic.SPEED,NULL_REF),1L);
        a_.getMultStatIfStatutRank().addEntry(new StatisticStatus(Statistic.SPEED,S_1),1L);
        a_.getMultStatIfDamgeType().addEntry(new StatisticType(Statistic.SPEED,NULL_REF),1L);
        a_.getMultStatIfDamgeType().addEntry(new StatisticType(Statistic.SPEED,T_1),1L);
        a_.getMultStatIfDamageCat().addEntry(new StatisticCategory(Statistic.SPEED,NULL_REF),1L);
        a_.getMultStatIfDamageCat().addEntry(new StatisticCategory(Statistic.SPEED,C_1),1L);
        a_.getMultStatIfCat().addEntry(new StatisticCategory(Statistic.SPEED,NULL_REF),Rate.one());
        a_.getMultStatIfCat().addEntry(new StatisticCategory(Statistic.SPEED,C_1),Rate.one());
        a_.getEffectSending().add(Instances.newEffectWhileSendingSimple());
        a_.getEffectEndRound().add(Instances.newEffectEndRoundFoe());
        db_.getItems().addEntry(I_1,Instances.newHealingHpStatus());
        db_.getAbilities().addEntry(A_1,a_);
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedCategories().size());
        assertEq(1,res_.getTranslatedCategories().getValue(0).size());
        assertEq(1,res_.getTranslatedCategories().getValue(1).size());
        assertEq(2,res_.getTranslatedMoves().size());
        assertEq(1,res_.getTranslatedMoves().getValue(0).size());
        assertEq(1,res_.getTranslatedMoves().getValue(1).size());
        assertEq(2,res_.getTranslatedStatus().size());
        assertEq(1,res_.getTranslatedStatus().getValue(0).size());
        assertEq(1,res_.getTranslatedStatus().getValue(1).size());
        assertEq(2,res_.getTranslatedTypes().size());
        assertEq(1,res_.getTranslatedTypes().getValue(0).size());
        assertEq(1,res_.getTranslatedTypes().getValue(1).size());
    }
    @Test
    public void patchData17() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        ItemForBattle i_ = Instances.newItemForBattle();
        i_.getMultStatPokemonRank().addEntry(new StatisticPokemon(Statistic.SPEED,P_1),1L);
        i_.getMultStatPokemonRank().addEntry(new StatisticPokemon(Statistic.SPEED,NULL_REF),1L);
        i_.getEffectSending().add(Instances.newEffectWhileSendingSimple());
        i_.getEffectEndRound().add(Instances.newEffectEndRoundFoe());
        db_.getItems().addEntry(I_1,i_);
        db_.getItems().addEntry(I_2,Instances.newBoost());
        db_.getItems().addEntry(I_3,Instances.newFossil());
        db_.getItems().addEntry(I_4,Instances.newBerry());
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedPokemon().size());
        assertEq(1,res_.getTranslatedPokemon().getValue(0).size());
        assertEq(1,res_.getTranslatedPokemon().getValue(1).size());
    }
    @Test
    public void patchData18() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        EffectCombo combo_ = Instances.newEffectCombo();
        combo_.getTeamMove().add(Instances.newEffectTeam());
        combo_.getEffectEndRound().add(Instances.newEffectEndRoundFoe());
        db_.getCombos().getEffects().add(new ListEffectCombo(new StringList(M_1,M_2), combo_));
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedMoves().size());
        assertEq(2,res_.getTranslatedMoves().getValue(0).size());
        assertEq(2,res_.getTranslatedMoves().getValue(1).size());
    }
    @Test
    public void patchData19() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        Cave ca_ = Instances.newCave();
        LevelCave lv_ = Instances.newLevelCave();
        lv_.getBlocks().addEntry(newPoint(0,0),newBlock(2,2,EnvironmentType.ROAD,NULL_REF,-1));
        lv_.getBlocks().addEntry(newPoint(2,2),newBlock(2,2,EnvironmentType.ROAD,NULL_REF,-1));
        lv_.getBlocks().addEntry(newPoint(4,4),newBlock(2,2,EnvironmentType.ROAD,NULL_REF,-1));
        lv_.getBlocks().addEntry(newPoint(6,6),newBlock(2,2,EnvironmentType.ROAD,NULL_REF,-1));
        lv_.getBlocks().addEntry(newPoint(8,8),newBlock(2,2,EnvironmentType.ROAD,NULL_REF,-1));
        lv_.getLinksOtherLevels().addEntry(newPoint(0,0),new Link(NULL_REF,newCoords(0,0,1,1)));
        lv_.getLinksOtherLevels().addEntry(newPoint(0,1),new Link(NULL_REF,newCoords(-1,0,1,1)));
        lv_.getCharacters().addEntry(newPoint(2,2),Instances.newDealerItem());
        TrainerMultiFights tmf_ = Instances.newTrainerMultiFights();
        tmf_.getTeamsRewards().add(Instances.newPokemonTeam());
        lv_.getCharacters().addEntry(newPoint(3,3), tmf_);
        lv_.getDualFights().addEntry(newPoint(4,4),Instances.newDualFight());
        DualFight dual_ = Instances.newDualFight();
        dual_.getFoeTrainer().setImageMiniSecondTrainerFileName(TRAINER);
        lv_.getDualFights().addEntry(newPoint(4,5), dual_);
        lv_.getLegendaryPks().addEntry(newPoint(5,4),Instances.newWildPk());
        lv_.getItems().addEntry(newPoint(6,6),NULL_REF);
        lv_.getItems().addEntry(newPoint(7,7),I_1);
        lv_.getTm().addEntry(newPoint(6,7),1);
        lv_.getTm().addEntry(newPoint(6,7),-1);
        lv_.getTm().addEntry(newPoint(8,8),1);
        lv_.getHm().addEntry(newPoint(8,8),1);
        ca_.getLinksWithOtherPlaces().addEntry(newLevelPoint(-1,0,0),new Link(NULL_REF,newCoords(0,0,0,0)));
        ca_.getLinksWithOtherPlaces().addEntry(newLevelPoint(0,2,3),new Link(NULL_REF,newCoords(-1,0,0,0)));
        ca_.getLevels().add(lv_);
        db_.getMap().addPlace(ca_);
        City ci_ = Instances.newCity();
        ci_.getLevel().getBlocks().addEntry(newPoint(0,0),newBlock(2,2,EnvironmentType.BUILDING,NULL_REF,-1));
        PokemonCenter pc_ = Instances.newPokemonCenter();
        Seller sell_ = Instances.newSeller();
        sell_.getTm().add(-1);
        sell_.getTm().add(1);
        pc_.getIndoor().getGerants().addEntry(newPoint(0,0), sell_);
        ci_.getBuildings().addEntry(newPoint(0,0), pc_);
        ci_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0), Direction.UP),newCoords(-1,0,0,0));
        db_.getMap().addPlace(ci_);
        db_.getMap().addPlace(Instances.newLeague());
        db_.getMap().setBegin(newCoords(0,0,-1,-1));
        TileMiniMap tm_ = Instances.newTileMiniMap();
        tm_.setPlace(-1);
        db_.getMap().getMiniMap().addEntry(new MiniMapCoords(-1,-1), tm_);
        db_.getTm().addEntry(1,M_1);
        db_.getHm().addEntry(1,M_1);
        db_.getPeople().addEntry(TRAINER,instance(new int[1][1]));
        db_.getMap().getAccessCondition().put(newCoords(-1,0,1,1), Condition.newList(newCoords(0,0,0,1)));
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertFalse(res_.getMap().getPlaces().isEmpty());
    }
    @Test
    public void patchData20() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        MoveData m_ = Instances.newDamagingMoveData();
        EffectDamage law_ = Instances.newEffectDamage();
        law_.getDamageLaw().addQuickEvent("",LgInt.one());
        m_.getEffects().add(law_);
        m_.getEffects().add(Instances.newEffectFullHpRate());
        m_.getEffects().add(Instances.newEffectCommonStatistics());
        m_.getEffects().add(Instances.newEffectStatus());
        m_.getEffects().add(Instances.newEffectStatistic());
        EffectSwitchMoveTypes mt_ = Instances.newEffectSwitchMoveTypes();
        mt_.getChangeTypes().addEntry(NULL_REF,T_3);
        mt_.getChangeTypes().addEntry(T_3,NULL_REF);
        mt_.getChangeTypes().addEntry(T_3,T_3);
        m_.getEffects().add(mt_);
        m_.getEffects().add(Instances.newEffectSwitchTypes());
        EffectTeam t_ = Instances.newEffectTeam();
        t_.getMultDamage().addEntry(new CategoryMult(C_1, 0),Rate.one());
        t_.getMultDamage().addEntry(new CategoryMult(NULL_REF, 0),Rate.one());
        m_.getEffects().add(t_);
        EffectUnprotectFromTypes u_ = Instances.newEffectUnprotectFromTypes();
        db_.getConstNonNum().setPrefixVar(MessagesDataBaseConstants.VAR_DEF);
        db_.getConstNonNum().setCiblePp(MessagesDataBaseConstants.DEF_CIBLE_PP);
        db_.getConstNonNum().setLieuCombat(MessagesDataBaseConstants.DEF_LIEU_COMBAT);
        u_.setFail(db_.prefixCiblePp(M_2)+"+"+db_.prefixLieuCombat());
        u_.getTypes().add(new TypesDuo(NULL_REF,NULL_REF));
        u_.getTypes().add(new TypesDuo(T_1,T_2));
        m_.getEffects().add(u_);
        m_.getEffects().add(Instances.newEffectTeamWhileSendFoe());
        db_.getMoves().addEntry(M_1,m_);
        ItemForBattle iBat_ = Instances.newItemForBattle();
        iBat_.getEffectEndRound().add(Instances.newEffectEndRoundFoe());
        EffectWhileSendingWithStatistic stat_ = Instances.newEffectWhileSendingWithStatistic();
        stat_.getEffect().getLocalFailStatis().addEntry(Statistic.SPEED,db_.prefixCiblePp(M_1)+"+"+db_.prefixLieuCombat());
        iBat_.getEffectSending().add(stat_);
        iBat_.getFailStatus().addEntry(S_1,"");
        db_.getItems().addEntry(I_1, iBat_);
        StatusSimple st_ = Instances.newStatusSimple();
        st_.getEffectEndRound().add(Instances.newEffectEndRoundSingleStatus());
        db_.getStatus().addEntry(S_1, st_);
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedCategories().size());
        assertEq(1,res_.getTranslatedCategories().getValue(0).size());
        assertEq(1,res_.getTranslatedCategories().getValue(1).size());
        assertEq(2,res_.getTranslatedMoves().size());
        assertEq(2,res_.getTranslatedMoves().getValue(0).size());
        assertEq(2,res_.getTranslatedMoves().getValue(1).size());
        assertEq(2,res_.getTranslatedTypes().size());
        assertEq(3,res_.getTranslatedTypes().getValue(0).size());
        assertEq(3,res_.getTranslatedTypes().getValue(1).size());
    }
    @Test
    public void crudMc1() {
        MonteCarloNumber m_ = new MonteCarloNumber();
        m_.addQuickEvent(Rate.one(),new LgInt(15));
        CrudGeneFormMonteCarlo<Rate> c_ = crudMc(m_);
        assertEq(1,c_.getList().size());
        MonteCarloNumber map_ = retrieve(c_);
        assertEq(Rate.one(),map_.getEvent(0));
        assertEq(new LgInt(15),map_.getFreq(0));
        assertFalse(c_.isVisibleSingle());
        assertEq(4,c_.getButtons().getComponentCount());
    }
    @Test
    public void crudMc2() {
        MonteCarloNumber m_ = new MonteCarloNumber();
        m_.addQuickEvent(Rate.one(),new LgInt(15));
        CrudGeneFormMonteCarlo<Rate> c_ = crudMc(m_);
        c_.getAdd().getActionListeners().get(0).action();
        assertTrue(c_.isVisibleSingle());
        update(c_, new Rate(2), new LgInt(20));
        c_.getValidAddEdit().getActionListeners().get(0).action();
        MonteCarloNumber map_ = retrieve(c_);
        assertEq(2,map_.size());
        assertEq(Rate.one(),map_.getEvent(0));
        assertEq(new LgInt(15),map_.getFreq(0));
        assertEq(new Rate(2),map_.getEvent(1));
        assertEq(new LgInt(20),map_.getFreq(1));
    }
    @Test
    public void crudMc3() {
        MonteCarloNumber m_ = new MonteCarloNumber();
        m_.addQuickEvent(Rate.one(),new LgInt(15));
        CrudGeneFormMonteCarlo<Rate> c_ = crudMc(m_);
        c_.getAdd().getActionListeners().get(0).action();
        update(c_, new Rate(2), new LgInt(20));
        c_.getValidAddEdit().getActionListeners().get(0).action();
        ((AbsButton) compo(c_.getElements())).getActionListeners().get(0).action();
        update(c_, new Rate(1), new LgInt(16));
        c_.getValidAddEdit().getActionListeners().get(0).action();
        MonteCarloNumber map_ = retrieve(c_);
        assertEq(2,map_.size());
        assertEq(Rate.one(),map_.getEvent(0));
        assertEq(new LgInt(16),map_.getFreq(0));
        assertEq(new Rate(2),map_.getEvent(1));
        assertEq(new LgInt(20),map_.getFreq(1));
    }
    @Test
    public void crudMc4() {
        MonteCarloNumber m_ = new MonteCarloNumber();
        m_.addQuickEvent(Rate.one(),new LgInt(15));
        CrudGeneFormMonteCarlo<Rate> c_ = crudMc(m_);
        c_.getAdd().getActionListeners().get(0).action();
        update(c_, new Rate(2), new LgInt(20));
        c_.getValidAddEdit().getActionListeners().get(0).action();
        ((AbsButton) compo(c_.getElements())).getActionListeners().get(0).action();
        c_.getValidRemove().getActionListeners().get(0).action();
        MonteCarloNumber map_ = retrieve(c_);
        assertEq(1,map_.size());
        assertEq(new Rate(2),map_.getEvent(0));
        assertEq(new LgInt(20),map_.getFreq(0));
    }
    @Test
    public void crudMc5() {
        MonteCarloNumber m_ = new MonteCarloNumber();
        m_.addQuickEvent(Rate.one(),new LgInt(15));
        CrudGeneFormMonteCarlo<Rate> c_ = crudMc(m_);
        c_.getAdd().getActionListeners().get(0).action();
        update(c_, new Rate(2), new LgInt(20));
        c_.getValidAddEdit().getActionListeners().get(0).action();
        ((AbsButton) compo(c_.getElements())).getActionListeners().get(0).action();
        update(c_, new Rate(1), new LgInt(16));
        c_.getCancel().getActionListeners().get(0).action();
        MonteCarloNumber map_ = retrieve(c_);
        assertEq(2,map_.size());
        assertEq(Rate.one(),map_.getEvent(0));
        assertEq(new LgInt(15),map_.getFreq(0));
        assertEq(new Rate(2),map_.getEvent(1));
        assertEq(new LgInt(20),map_.getFreq(1));
    }
    @Test
    public void crudMc6() {
        MonteCarloNumber m_ = new MonteCarloNumber();
        m_.addQuickEvent(Rate.one(),new LgInt(15));
        CrudGeneFormMonteCarlo<Rate> c_ = crudMc(m_);
        c_.getAdd().getActionListeners().get(0).action();
        assertTrue(c_.isVisibleSingle());
        update(c_, new Rate(1), new LgInt(20));
        c_.getValidAddEdit().getActionListeners().get(0).action();
        MonteCarloNumber map_ = retrieve(c_);
        assertEq(1,map_.size());
        assertEq(Rate.one(),map_.getEvent(0));
        assertEq(new LgInt(15),map_.getFreq(0));
    }

    private AbsCustComponent compo(AbsPanel _pan) {
        return ((MockPanel)_pan).getComponent(0);
    }
    public static MonteCarloNumber retrieve(CrudGeneFormMonteCarlo<Rate> _evts) {
        MonteCarloNumber out_ = new MonteCarloNumber();
        new MapToEntriesListUtil<Rate,LgInt>().feedMap(_evts.getList(), out_);
        return out_;
    }
    private CrudGeneFormMonteCarlo<Rate> crudMc(AbMonteCarlo<Rate> _map) {
        MockProgramInfos pr_ = initForms();
        AbsCommonFrame f_ = pr_.getFrameFactory().newCommonFrame();
        CrudGeneFormMonteCarlo<Rate> c_ = new CrudGeneFormMonteCarlo<Rate>(null, pr_,new ComparingRateKey<LgInt>());
        c_.setFrame(f_);
        c_.initForm();
        c_.initFormKeys(new RateLgIntDisplayEntryCust(),new GeneComponentModelEventRate(pr_,"","",""), new ComparingRateKey<LgInt>());
        c_.setupValues(new MapToEntriesListUtil<Rate, LgInt>().build(_map));
        return c_;
    }

    private static void update(CrudGeneFormMonteCarlo<Rate> _form, Rate _key, LgInt _value) {
        ((GeneComponentModelEventRate)_form.getGene()).getEvent().valueRate(_key);
        ((GeneComponentModelEventRate)_form.getGene()).getProba().valueLgInt(_value);
    }
}
