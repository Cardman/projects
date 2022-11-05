package aiki.db;

import aiki.fight.Combos;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.EndTurnType;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.status.Status;
import aiki.fight.status.StatusSimple;
import aiki.fight.status.StatusType;
import aiki.fight.util.ListEffectCombo;
import aiki.fight.util.TypesDuos;
import aiki.game.fight.InitializationDataBase;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.instances.Instances;
import aiki.util.ImageHeroKeys;
import aiki.util.LawNumber;
import aiki.util.ScreenCoordssInt;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.DefaultGenerator;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloString;
import code.util.*;
import code.util.core.StringUtil;
import org.junit.Test;


public class DataBaseTest extends EquallablePkUtil {

    @Test
    public void initBase1() {
        DataBase data_ = newData();
        data_.initValue(DataBase.DEF_MOVE,"_");
        assertEq("_",data_.getDefMove());
    }

    @Test
    public void initBase2() {
        DataBase data_ = newData();
        data_.initValue(DataBase.RATE_BOOST,"_");
        assertEq("_",data_.getRateBoost());
    }

    @Test
    public void initBase3() {
        DataBase data_ = newData();
        data_.initValue(DataBase.RATE_BOOST_CRITICAL_HIT,"_");
        assertEq("_",data_.getRateBoostCriticalHit());
    }

    @Test
    public void initBase4() {
        DataBase data_ = newData();
        data_.initValue(DataBase.RATE_FLEEING,"_");
        assertEq("_",data_.getRateFleeing());
    }

    @Test
    public void initBase5() {
        DataBase data_ = newData();
        data_.initValue(DataBase.RATE_CATCHING,"_");
        assertEq("_",data_.getRateCatching());
    }

    @Test
    public void initBase6() {
        DataBase data_ = newData();
        data_.initValue(DataBase.BALL_DEF,"_");
        assertEq("_",data_.getBallDef());
    }

    @Test
    public void initBase7() {
        DataBase data_ = newData();
        data_.initValue(DataBase.DEFAULT_EGG_GROUP,"_");
        assertEq("_",data_.getDefaultEggGroup());
    }

    @Test
    public void initBase8() {
        DataBase data_ = newData();
        data_.initValue(DataBase.DAMAGE_FORMULA,"_");
        assertEq("_",data_.getDamageFormula());
    }

    @Test
    public void initBase9() {
        DataBase data_ = newData();
        data_.initValue(DataBase.DEF_CAT,"_");
        assertEq("_",data_.getDefCategory());
    }

    @Test
    public void initBase10() {
        DataBase data_ = newData();
        data_.initValue("","_");
        assertEq("",data_.getDefMove());
        assertEq("",data_.getRateBoost());
        assertEq("",data_.getRateBoostCriticalHit());
        assertEq("",data_.getRateCatching());
        assertEq("",data_.getRateFleeing());
        assertEq("",data_.getBallDef());
        assertEq("",data_.getDefaultEggGroup());
        assertEq("",data_.getDamageFormula());
        assertEq("",data_.getDefCategory());
    }
    @Test
    public void test() {
        DataBase data_ = InitializationDataBase.initDataBase();
        assertTrue(!data_.isError());
    }

    @Test
    public void test0() {
        DataBase data_ = newData();
        data_.initTranslations();
        data_.setLanguages(new StringList());
        data_.setLanguage("");
        data_.setMiniPk(new StringMap<int[][]>());
        data_.setMaxiPkBack(new StringMap<int[][]>());
        data_.setMaxiPkFront(new StringMap<int[][]>());
        data_.setMiniItems(new StringMap<int[][]>());
        data_.setFrontHeros(new ImageHeroKeys());
        data_.setBackHeros(new ImageHeroKeys());
        data_.setOverWorldHeros(new ImageHeroKeys());
        data_.setTrainers(new StringMap<int[][]>());
        data_.setPeople(new StringMap<int[][]>());
        data_.setTypesColors(new StringMap<String>());
        data_.setTypesImages(new StringMap<int[][]>());
        data_.setLinks(new StringMap<int[][]>());
        data_.setImages(new StringMap<int[][]>());
        data_.setImagesTiles(new StringMap<ScreenCoordssInt>());
        data_.setMiniMap(new StringMap<int[][]>());
        data_.setCombos(new Combos());
        data_.setConstNum(new StringMap<Rate>());
        data_.setRateBoostCriticalHit("");
        data_.setRateBoost("");
        data_.setRateCatching("");
        data_.setRateFleeing("");
        data_.setRates(new IdMap<DifficultyWinPointsFight, String>());
        data_.setBallDef("");
        data_.setDefaultEggGroup("");
        data_.setDefMove("");
        data_.setDamageFormula("");
        data_.setDefCategory("");
        data_.setExpGrowth(new IdMap<ExpType, String>());
        data_.setTableTypes(new TypesDuos());
        data_.setTypes(new StringList());
        data_.setLawsDamageRate(new IdMap<DifficultyModelLaw, LawNumber>());
        data_.setMaxHeightPk(0);
        data_.setMaxWidthPk(0);
        data_.setDisplayLanguages(new StringMap<String>());
        data_.setMessagesFight(new StringMap<String>());
        data_.setMessagesFighter(new StringMap<String>());
        data_.setMessagesGame(new StringMap<String>());
        data_.setMessagesPlayer(new StringMap<String>());
        data_.setMessagesPokemonPlayer(new StringMap<String>());
        data_.setMessagesTeam(new StringMap<String>());
        data_.setMessages(data_);
        data_.getExpGrowth(ExpType.P);
        assertEq(DataBase.EMPTY_STRING,data_.getRateCatching());
        assertEq(DataBase.EMPTY_STRING,data_.getRateFleeing());
        assertEq(DataBase.EMPTY_STRING,data_.getDefaultEggGroup());
        assertEq(DataBase.EMPTY_STRING,data_.getBallDef());

        assertEq(DataBase.EMPTY_STRING, data_.getBallDef());
        assertEq(DataBase.EMPTY_STRING,data_.getDefMove());
        assertEq(0,data_.getImagesTiles().size());
        assertEq(0,data_.getDisplayLanguages().size());
        assertEq(0,data_.getLanguages().size());
        data_.getEndGameImage();
        data_.getAnimAbsorb();
        assertTrue(!data_.isError());
    }

    private static DataBase newData() {
        return new DataBase(DefaultGenerator.oneElt());
    }

    @Test
    public void toUpperCaseTest() {
        assertEq("Aa!<>_{}[]?".length(), DataBase.toUpperCase("Aa!<>_{}[]?").length());
    }
    @Test
    public void checkCaseOfFiles1Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StringList files_ = new StringList();
        data_.checkCaseOfFiles("folder", files_);
        assertEq(0, data_.getFilesWithSameNameDifferentCase().size());
    }

    @Test
    public void checkCaseOfFiles2Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StringList files_ = new StringList();
        files_.add("file_one");
        files_.add("file_TWO");
        data_.checkCaseOfFiles("folder", files_);
        assertEq(0, data_.getFilesWithSameNameDifferentCase().size());
    }

    @Test
    public void checkCaseOfFiles3Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StringList files_ = new StringList();
        files_.add("file_one");
        files_.add("file_ONE");
        files_.add("file_TWO");
        data_.checkCaseOfFiles("folder", files_);
        assertEq(1, data_.getFilesWithSameNameDifferentCase().size());
        assertTrue(StringUtil.contains(data_.getFilesWithSameNameDifferentCase(), "folder/FILE_ONE"));
    }

    @Test
    public void checkCaseOfFiles4Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StringList files_ = new StringList();
        files_.add("file_one");
        files_.add("file_ONE");
        files_.add("File_ONE");
        files_.add("file_TWO");
        data_.checkCaseOfFiles("folder", files_);
        assertEq(1, data_.getFilesWithSameNameDifferentCase().size());
        assertTrue(StringUtil.contains(data_.getFilesWithSameNameDifferentCase(), "folder/FILE_ONE"));
    }

    @Test
    public void checkCaseOfFiles5Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StringList files_ = new StringList();
        files_.add("!file_one");
        files_.add("!file_ONE");
        files_.add("!File_ONE");
        files_.add("!file_TWO");
        data_.checkCaseOfFiles("folder", files_);
        assertEq(1, data_.getFilesWithSameNameDifferentCase().size());
        assertTrue(StringUtil.contains(data_.getFilesWithSameNameDifferentCase(), "folder/!FILE_ONE"));
    }

    @Test
    public void checkCaseOfFiles6Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StringList files_ = new StringList();
        files_.add("<file_one");
        files_.add("<file_ONE");
        files_.add("<File_ONE");
        files_.add("<file_TWO");
        data_.checkCaseOfFiles("folder", files_);
        assertEq(1, data_.getFilesWithSameNameDifferentCase().size());
        assertTrue(StringUtil.contains(data_.getFilesWithSameNameDifferentCase(), "folder/<FILE_ONE"));
        DataBase.toUpperCase("Aa!<>{}[]?");
    }

    @Test
    public void isVariableTest() {
        assertTrue(!DataBase.isVariable(DataBase.VAR_PREFIX));
    }
    @Test
    public void completeMembers1Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setCategory("SPECIAL");
        moveDamage_.setDirect(false);
        moveDamage_.setPp((short) 40);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("ECLAIR", moveDamage_);
        assertEq(1, data_.getCategories().size());
        assertTrue(StringUtil.contains(data_.getCategories(), "SPECIAL"));
        assertEq(1, data_.getAllCategories().size());
        assertTrue(StringUtil.contains(data_.getAllCategories(), "SPECIAL"));
    }

    @Test
    public void completeMembers2Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setCategory("PHYSIQUE");
        moveDamage_.setDirect(false);
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("TONNERRE", moveDamage_);
        assertEq(1, data_.getCategories().size());
        assertTrue(StringUtil.contains(data_.getCategories(), "PHYSIQUE"));
        assertEq(1, data_.getAllCategories().size());
        assertTrue(StringUtil.contains(data_.getAllCategories(), "PHYSIQUE"));
    }

    @Test
    public void completeMembers3Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        data_.initValue(DataBase.DEF_CAT,AUTRE);
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getCategories().size());
        assertEq(1, data_.getAllCategories().size());
        assertTrue(StringUtil.contains(data_.getAllCategories(), data_.getDefCategory()));
    }

    @Test
    public void completeMembers4Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy(StringUtil.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"));
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getCategories().size());
        assertEq(1, data_.getVariables().size());
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(DataBase.VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers5Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getCategories().size());
        assertEq(0, data_.getVariables().size());
    }

    @Test
    public void completeMembers6Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectDamage damage_;
        damage_ = new EffectDamage();
        damage_.setFail("");
        damage_.setPower("50");
        damage_.setDamageLaw(new MonteCarloString());
        moveDamage_.getEffects().add(damage_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("CHARGE", moveDamage_);
        assertEq(0, data_.getVariables().size());
    }

    @Test
    public void completeMembers7Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectDamage damage_;
        damage_ = new EffectDamage();
        damage_.setFail(StringUtil.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"));
        damage_.setPower("50");
        damage_.setDamageLaw(new MonteCarloString());
        moveDamage_.getEffects().add(damage_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("CHARGE", moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(DataBase.VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers8Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectDamage damage_;
        damage_ = new EffectDamage();
        damage_.setFail("");
        damage_.setPower(StringUtil.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"));
        damage_.setDamageLaw(new MonteCarloString());
        moveDamage_.getEffects().add(damage_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("CHARGE", moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(DataBase.VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers9Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectDamage damage_;
        damage_ = new EffectDamage();
        damage_.setFail("");
        damage_.setPower("50");
        damage_.setDamageLaw(new MonteCarloString());
        damage_.getDamageLaw().addQuickEvent(StringUtil.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"), LgInt.one());
        moveDamage_.getEffects().add(damage_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("CHARGE", moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(DataBase.VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers10Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatistic effStatis_;
        effStatis_ = new EffectStatistic();
        effStatis_.setFail("");
        effStatis_.setLocalFailStatis(new IdMap<Statistic,String>());
        effStatis_.setLocalFailSwapBoostStatis(new IdMap<Statistic,String>());
        moveDamage_.getEffects().add(effStatis_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getVariables().size());
    }

    @Test
    public void completeMembers11Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatistic effStatis_;
        effStatis_ = new EffectStatistic();
        effStatis_.setFail("");
        effStatis_.setLocalFailStatis(new IdMap<Statistic,String>());
        effStatis_.getLocalFailStatis().put(Statistic.ATTACK, StringUtil.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"));
        effStatis_.setLocalFailSwapBoostStatis(new IdMap<Statistic,String>());
        moveDamage_.getEffects().add(effStatis_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(DataBase.VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers12Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatistic effStatis_;
        effStatis_ = new EffectStatistic();
        effStatis_.setFail("");
        effStatis_.setLocalFailStatis(new IdMap<Statistic,String>());
        effStatis_.setLocalFailSwapBoostStatis(new IdMap<Statistic,String>());
        effStatis_.getLocalFailSwapBoostStatis().put(Statistic.ATTACK, StringUtil.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"));
        moveDamage_.getEffects().add(effStatis_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(DataBase.VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers13Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatus effStatus_;
        effStatus_ = new EffectStatus();
        effStatus_.setFail("");
        effStatus_.setLocalFailStatus(new StringMap<String>());
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getVariables().size());
    }

    @Test
    public void completeMembers14Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatus effStatus_;
        effStatus_ = new EffectStatus();
        effStatus_.setFail("");
        effStatus_.setLocalFailStatus(new StringMap<String>());
        effStatus_.getLocalFailStatus().put("PSN", StringUtil.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"));
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(DataBase.VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers15Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectCommonStatistics effStatus_;
        effStatus_ = new EffectCommonStatistics();
        effStatus_.setFail("");
        effStatus_.setCommonValue(new IdMap<Statistic,String>());
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getVariables().size());
    }

    @Test
    public void completeMembers16Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectCommonStatistics effStatus_;
        effStatus_ = new EffectCommonStatistics();
        effStatus_.setFail("");
        effStatus_.setCommonValue(new IdMap<Statistic,String>());
        effStatus_.getCommonValue().put(Statistic.ATTACK, StringUtil.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"));
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(DataBase.VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers17Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectFullHpRate effStatus_;
        effStatus_ = new EffectFullHpRate();
        effStatus_.setFail("");
        effStatus_.setRestoredHp("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getVariables().size());
    }

    @Test
    public void completeMembers18Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectFullHpRate effStatus_;
        effStatus_ = new EffectFullHpRate();
        effStatus_.setFail("");
        effStatus_.setRestoredHp(StringUtil.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"));
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(DataBase.VAR_PREFIX, "NB_TURN")));
    }

    @Test
    public void completeMembers19Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectTeamWhileSendFoe effStatus_;
        effStatus_ = new EffectTeamWhileSendFoe();
        effStatus_.setFail("");
        effStatus_.setDamageRateAgainstFoe("");
        effStatus_.setFailSending("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getVariables().size());
        assertEq(1, data_.getMovesEffectWhileSending().size());
        assertTrue(StringUtil.contains(data_.getMovesEffectWhileSending(), "QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers20Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectTeamWhileSendFoe effStatus_;
        effStatus_ = new EffectTeamWhileSendFoe();
        effStatus_.setFail("");
        effStatus_.setFailSending("");
        effStatus_.setDamageRateAgainstFoe(StringUtil.concat("1+",DataBase.VAR_PREFIX,"NB_TURN*100"));
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(DataBase.VAR_PREFIX, "NB_TURN")));
        assertEq(1, data_.getMovesEffectWhileSending().size());
        assertTrue(StringUtil.contains(data_.getMovesEffectWhileSending(), "QUEUE_DE_CHEVAL"));
        assertEq(0, data_.ppCopiedMove("QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers21Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectCopyMove effStatus_;
        effStatus_ = new EffectCopyMove();
        effStatus_.setFail("");
        effStatus_.setCopyingMoveForUser((short) 0);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesCopyingTemp().size());
        assertEq(0, data_.ppCopiedMove("QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers22Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectCopyMove effStatus_;
        effStatus_ = new EffectCopyMove();
        effStatus_.setFail("");
        effStatus_.setCopyingMoveForUser((short) 1);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesCopyingTemp().size());
        assertEq(1, data_.ppCopiedMove("QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers23Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setFail("");
        effStatus_.setProtSingleAgainstKo(Rate.zero());
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesProtAgainstPrio().size());
        assertEq(0, data_.getMovesProtSingleTarget().size());
        assertEq(0, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(0, data_.getMovesProtAgainstMultiTarget().size());
        assertEq(0, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstDamageMoves().size());
    }

    @Test
    public void completeMembers24Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setProtSingleAgainstKo(Rate.zero());
        effStatus_.setProtTeamAgainstPrio(true);
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesProtAgainstPrio().size());
        assertEq(0, data_.getMovesProtSingleTarget().size());
        assertEq(0, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(0, data_.getMovesProtAgainstMultiTarget().size());
        assertEq(0, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstDamageMoves().size());
    }

    @Test
    public void completeMembers25Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setProtSingleAgainstKo(Rate.zero());
        effStatus_.setProtSingle(true);
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesProtAgainstPrio().size());
        assertEq(1, data_.getMovesProtSingleTarget().size());
        assertEq(0, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(0, data_.getMovesProtAgainstMultiTarget().size());
        assertEq(0, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstDamageMoves().size());
    }

    @Test
    public void completeMembers26Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setProtSingleAgainstKo(Rate.zero());
        effStatus_.setProtTeamAgainstMultTargets(true);
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesProtAgainstPrio().size());
        assertEq(0, data_.getMovesProtSingleTarget().size());
        assertEq(0, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(1, data_.getMovesProtAgainstMultiTarget().size());
        assertEq(0, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstDamageMoves().size());
    }

    @Test
    public void completeMembers27Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setProtSingleAgainstKo(Rate.one());
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesProtAgainstPrio().size());
        assertEq(0, data_.getMovesProtSingleTarget().size());
        assertEq(1, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(0, data_.getMovesProtAgainstMultiTarget().size());
        assertEq(0, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstDamageMoves().size());
    }

    @Test
    public void completeMembers28Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectAccuracy effStatus_;
        effStatus_ = new EffectAccuracy();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesAccuracy().size());
    }

    @Test
    public void completeMembers29Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectAlly effStatus_;
        effStatus_ = new EffectAlly();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesEffectAlly().size());
    }

    @Test
    public void completeMembers30Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectTeam effStatus_;
        effStatus_ = new EffectTeam();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesEffectTeam().size());
    }

    @Test
    public void completeMembers31Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectUnprotectFromTypes effStatus_;
        effStatus_ = new EffectUnprotectFromTypes();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesEffectUnprot().size());
    }

    @Test
    public void completeMembers32Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtectFromTypes effStatus_;
        effStatus_ = new EffectProtectFromTypes();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesEffectProt().size());
    }

    @Test
    public void completeMembers33Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectGlobal effStatus_;
        effStatus_ = new EffectGlobal();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesEffectGlobal().size());
        assertEq(0, data_.getMovesEffectGlobalWeather().size());
    }

    @Test
    public void completeMembers34Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectGlobal effStatus_;
        effStatus_ = new EffectGlobal();
        effStatus_.setFail("");
        effStatus_.setWeather(true);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesEffectGlobal().size());
        assertEq(1, data_.getMovesEffectGlobalWeather().size());
    }

    @Test
    public void completeMembers35Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectRestriction effStatus_;
        effStatus_ = new EffectRestriction();
        effStatus_.setFail("");
        effStatus_.setChoiceRestriction(MoveChoiceRestrictionType.FORCE);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesEffectIndiv().size());
        assertEq(1, data_.getMovesActingMoveUses().size());
        assertEq(0, data_.getMovesForbidding().size());
    }

    @Test
    public void completeMembers36Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectRestriction effStatus_;
        effStatus_ = new EffectRestriction();
        effStatus_.setFail("");
        effStatus_.setChoiceRestriction(MoveChoiceRestrictionType.FORBIDDEN);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesEffectIndiv().size());
        assertEq(1, data_.getMovesActingMoveUses().size());
        assertEq(0, data_.getMovesForbidding().size());
    }

    @Test
    public void completeMembers37Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectRestriction effStatus_;
        effStatus_ = new EffectRestriction();
        effStatus_.setFail("");
        effStatus_.setChoiceRestriction(MoveChoiceRestrictionType.LANCEUR_ATTAQUES);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesEffectIndiv().size());
        assertEq(0, data_.getMovesActingMoveUses().size());
        assertEq(1, data_.getMovesForbidding().size());
    }

    @Test
    public void completeMembers38Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectRestriction effStatus_;
        effStatus_ = new EffectRestriction();
        effStatus_.setFail("");
        effStatus_.setChoiceRestriction(MoveChoiceRestrictionType.DER);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesEffectIndivIncr().size());
        assertEq(1, data_.getMovesEffectIndiv().size());
        assertEq(0, data_.getMovesActingMoveUses().size());
        assertEq(0, data_.getMovesForbidding().size());
    }

    @Test
    public void completeMembers39Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectRestriction effStatus_;
        effStatus_ = new EffectRestriction();
        effStatus_.setFail("");
        effStatus_.setChoiceRestriction(MoveChoiceRestrictionType.DER);
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        moveDamage_.getRepeatRoundLaw().addQuickEvent(Rate.one(), LgInt.one());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesEffectIndivIncr().size());
        assertEq(1, data_.getMovesEffectIndiv().size());
        assertEq(0, data_.getMovesActingMoveUses().size());
        assertEq(0, data_.getMovesForbidding().size());
    }

    @Test
    public void completeMembers40Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtectFromTypes effStatus_;
        effStatus_ = new EffectProtectFromTypes();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        moveDamage_.getRepeatRoundLaw().addQuickEvent(Rate.one(), LgInt.one());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesEffectIndivIncr().size());
        assertEq(1, data_.getMovesEffectProt().size());
    }

    @Test
    public void completeMembers41Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRankIncrementNbRound((short) 1);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(1, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq("QUEUE_DE_CHEVAL", data_.getEvtEndRound().first().getElement());
        assertEq(EndTurnType.ATTAQUE, data_.getEvtEndRound().first().getEndRoundType());
        assertTrue(data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers42Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundGlobal effect_;
        effect_ = new EffectEndRoundGlobal();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(2, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq("QUEUE_DE_CHEVAL", data_.getEvtEndRound().first().getElement());
        assertEq(EndTurnType.ATTAQUE, data_.getEvtEndRound().first().getEndRoundType());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getTrappingMoves().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers43Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundPositionTargetRelation effect_;
        effect_ = new EffectEndRoundPositionTargetRelation();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getTrappingMoves().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(1, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers44Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundPositionRelation effect_;
        effect_ = new EffectEndRoundPositionRelation();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getTrappingMoves().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(1, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers45Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundIndividual effect_;
        effect_ = new EffectEndRoundIndividual();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getTrappingMoves().size());
        assertEq(1, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers46Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundIndividual effect_;
        effect_ = new EffectEndRoundIndividual();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        moveDamage_.getRepeatRoundLaw().addQuickEvent(Rate.one(), LgInt.one());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getTrappingMoves().size());
        assertEq(1, data_.getMovesEffEndRoundIndiv().size());
        assertEq(1, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers47Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundIndividual effect_;
        effect_ = new EffectEndRoundIndividual();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        moveDamage_.getRepeatRoundLaw().addQuickEvent(Rate.one(), LgInt.one());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getTrappingMoves().size());
        assertEq(1, data_.getMovesEffEndRoundIndiv().size());
        assertEq(1, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers48Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectEndRoundSingleRelation effect_;
        effect_ = new EffectEndRoundSingleRelation();
        effect_.setFail("");
        effect_.setFailEndRound("");
        effect_.setEndRoundRank(2);
        effect_.setRateDamageFunctionOfNbRounds(new LongMap<Rate>());
        effect_.setLawForEnablingEffect(new MonteCarloNumber());
        effect_.getLawForEnablingEffect().addQuickEvent(Rate.one(), LgInt.zero());
        effect_.getRateDamageFunctionOfNbRounds().put(1L, new Rate(1,2));
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        moveDamage_.getRepeatRoundLaw().addQuickEvent(Rate.one(), LgInt.one());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(1, data_.getTrappingMoves().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers49Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        moveDamage_.setConstUserChoice(true);
        moveDamage_.getRepeatRoundLaw().addQuickEvent(Rate.one(), LgInt.one());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesConstChoices().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers50Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatus effect_ = new EffectStatus();
        effect_.setFail("");
        effect_.setLocalFailStatus(new StringMap<String>());
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers51Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectStatus effect_ = new EffectStatus();
        effect_.setFail("");
        effect_.setLocalFailStatus(new StringMap<String>());
        effect_.setKoUserHealSubst(true);
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(1, data_.getMovesFullHeal().size());
        assertEq(0, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers52Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectInvoke effect_ = new EffectInvoke();
        effect_.setFail("");
        moveDamage_.getEffects().add(effect_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(0, data_.getMovesConstChoices().size());
        assertEq(0, data_.getMovesEffEndRoundIndiv().size());
        assertEq(0, data_.getMovesEffEndRoundIndivIncr().size());
        assertEq(0, data_.getMovesAnticipation().size());
        assertEq(0, data_.getMovesHealingAfter().size());
        assertEq(0, data_.getMovesFullHeal().size());
        assertEq(1, data_.getMovesInvoking().size());
    }

    @Test
    public void completeMembers53Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        Berry berry_;
        berry_ = new Berry();
        data_.completeMembers("STRAW_BERRY", berry_);
        assertEq(0, data_.getEvtEndRound().size());
    }

    @Test
    public void completeMembers54Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        ItemForBattle berry_;
        berry_ = new ItemForBattle();
        berry_.setEffectEndRound(new CustList<EffectEndRound>());
        berry_.setMultDamage("");
        berry_.setMultPower("");
        berry_.setMultStat(new IdMap<Statistic,String>());
        berry_.setFailStatus(new StringMap<String>());
        data_.completeMembers("STRAW_BERRY", berry_);
        assertEq(0, data_.getEvtEndRound().size());
    }

    @Test
    public void completeMembers55Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        ItemForBattle berry_;
        berry_ = new ItemForBattle();
        berry_.setMultDamage("");
        berry_.setMultPower("");
        berry_.setMultStat(new IdMap<Statistic,String>());
        berry_.setFailStatus(new StringMap<String>());
        berry_.setEffectEndRound(new CustList<EffectEndRound>());
        EffectEndRoundIndividual effect_;
        effect_ = new EffectEndRoundIndividual();
        effect_.setEndRoundRank(3);
        effect_.setHealHp(new Rate(1,2));
        berry_.getEffectEndRound().add(effect_);
        data_.completeMembers("STRAW_BERRY", berry_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(3, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.OBJET, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("STRAW_BERRY", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.INDIVIDUEL, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers56Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        AbilityData ability_;
        ability_ = new AbilityData();
        ability_.setEffectEndRound(new CustList<EffectEndRound>());
        ability_.setMultDamage("");
        ability_.setMultPower("");
        ability_.setMultStat(new IdMap<Statistic,String>());
        ability_.setFailStatus(new StringMap<String>());
        data_.completeMembers("FLYING", ability_);
        assertEq(0, data_.getEvtEndRound().size());
    }

    @Test
    public void completeMembers57Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        AbilityData ability_;
        ability_ = new AbilityData();
        ability_.setEffectEndRound(new CustList<EffectEndRound>());
        ability_.setMultDamage("");
        ability_.setMultPower("");
        ability_.setMultStat(new IdMap<Statistic,String>());
        ability_.setFailStatus(new StringMap<String>());
        EffectEndRoundTeam effect_;
        effect_ = new EffectEndRoundTeam();
        effect_.setEndRoundRank(3);
        effect_.setDeleteAllStatusAlly(new Rate(1,2));
        ability_.getEffectEndRound().add(effect_);
        data_.completeMembers("FLYING", ability_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(3, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.CAPACITE, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("FLYING", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.EQUIPE, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers58Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        Status status_;
        status_ = new StatusSimple();
        status_.setEffectEndRound(new CustList<EffectEndRoundStatus>());
        data_.completeMembers("PAR", status_);
        assertEq(0, data_.getEvtEndRound().size());
    }

    @Test
    public void completeMembers59Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        Status status_;
        status_ = new StatusSimple();
        status_.setEffectEndRound(new CustList<EffectEndRoundStatus>());
        EffectEndRoundSingleStatus eff_ = new EffectEndRoundSingleStatus();
        eff_.setEndRoundRank(2);
        status_.getEffectEndRound().add(eff_);
        data_.completeMembers("PAR", status_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(2, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.STATUT, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("PAR", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.STATUT, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers60Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        Status status_;
        status_ = new StatusSimple();
        status_.setEffectEndRound(new CustList<EffectEndRoundStatus>());
        EffectEndRoundStatusRelation eff_ = new EffectEndRoundStatusRelation();
        eff_.setEndRoundRank(2);
        status_.getEffectEndRound().add(eff_);
        data_.completeMembers("PAR", status_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(2, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.STATUT, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("PAR", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.STATUT_RELATION, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers61Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        Status status_;
        status_ = new StatusSimple();
        status_.setEffectEndRound(new CustList<EffectEndRoundStatus>());
        status_.setIncrementingEndRound(true);
        status_.setIncrementEndRound(4);
        status_.setStatusType(StatusType.INDIVIDUEL);
        data_.completeMembers("PAR", status_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(4, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.STATUT, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("PAR", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.STATUT, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers62Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        Status status_;
        status_ = new StatusSimple();
        status_.setEffectEndRound(new CustList<EffectEndRoundStatus>());
        status_.setIncrementingEndRound(true);
        status_.setIncrementEndRound(4);
        status_.setStatusType(StatusType.RELATION_UNIQUE);
        data_.completeMembers("PAR", status_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(4, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.STATUT, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("PAR", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.STATUT_RELATION, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers63Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        EffectCombo eff_;
        eff_ = new EffectCombo();
        eff_.setEffectEndRound(new CustList<EffectEndRoundFoe>());
        data_.completeMembers(new StringList("FLYING","SURF"), eff_);
        assertEq(0, data_.getEvtEndRound().size());
    }

    @Test
    public void completeMembers64Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        EffectCombo eff_;
        eff_ = new EffectCombo();
        eff_.setEffectEndRound(new CustList<EffectEndRoundFoe>());
        eff_.setRankIncrementNbRound((short) 2);
        data_.completeMembers(new StringList("FLYING","SURF"), eff_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(2, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.ATTAQUE_COMBI, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("FLYING;SURF", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.EQUIPE, data_.getEvtEndRound().first().getRelation());
        assertTrue(data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers65Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        EffectCombo eff_;
        eff_ = new EffectCombo();
        eff_.setEffectEndRound(new CustList<EffectEndRoundFoe>());
        EffectEndRoundFoe effect_ = new EffectEndRoundFoe();
        effect_.setEndRoundRank(4);
        eff_.getEffectEndRound().add(effect_);
        data_.completeMembers(new StringList("FLYING","SURF"), eff_);
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(4, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.ATTAQUE_COMBI, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("FLYING;SURF", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.ADV, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeMembers66Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectTeamWhileSendFoe effStatus_;
        effStatus_ = new EffectTeamWhileSendFoe();
        effStatus_.setFail("");
        effStatus_.setFailSending(StringUtil.concat(DataBase.VAR_PREFIX,"NB_TURN>1"));
        effStatus_.setDamageRateAgainstFoe("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(DataBase.VAR_PREFIX, "NB_TURN")));
        assertEq(1, data_.getMovesEffectWhileSending().size());
        assertTrue(StringUtil.contains(data_.getMovesEffectWhileSending(), "QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers67Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectCounterAttack effStatus_;
        effStatus_ = new EffectCounterAttack();
        effStatus_.setFail("");
        effStatus_.setCounterFail(StringUtil.concat(DataBase.VAR_PREFIX,"NB_TURN>1"));
        effStatus_.setProtectFail(StringUtil.concat(DataBase.VAR_PREFIX,"USED_MOVE=TACKLE"));
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(DataBase.VAR_PREFIX, "NB_TURN")));
        assertTrue(StringUtil.contains(data_.getVariables(), StringUtil.concat(DataBase.VAR_PREFIX, "USED_MOVE")));
        assertEq(1, data_.getMovesCountering().size());
        assertTrue(StringUtil.contains(data_.getMovesCountering(), "QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers68Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectSwitchMoveTypes effStatus_;
        effStatus_ = new EffectSwitchMoveTypes();
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesChangingTypes().size());
        assertTrue(StringUtil.contains(data_.getMovesChangingTypes(), "QUEUE_DE_CHEVAL"));
    }

    @Test
    public void completeMembers69Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setProtSingleAgainstKo(Rate.zero());
        effStatus_.setProtTeamAgainstStatusMoves(true);
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstDamageMoves().size());
        assertEq(0, data_.getMovesProtAgainstPrio().size());
        assertEq(0, data_.getMovesProtSingleTarget().size());
        assertEq(0, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(0, data_.getMovesProtAgainstMultiTarget().size());
    }

    @Test
    public void completeMembers70Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        StatusMoveData moveDamage_;
        moveDamage_ = new StatusMoveData();
        moveDamage_.setPp((short) 20);

        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectProtection effStatus_;
        effStatus_ = new EffectProtection();
        effStatus_.setProtSingleAgainstKo(Rate.zero());
        effStatus_.setProtTeamAgainstDamageMoves(true);
        effStatus_.setFail("");
        moveDamage_.getEffects().add(effStatus_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("QUEUE_DE_CHEVAL", moveDamage_);
        assertEq(1, data_.getMovesProtAgainstDamageMoves().size());
        assertEq(0, data_.getMovesProtAgainstStatusMoves().size());
        assertEq(0, data_.getMovesProtAgainstPrio().size());
        assertEq(0, data_.getMovesProtSingleTarget().size());
        assertEq(0, data_.getMovesProtSingleTargetAgainstKo().size());
        assertEq(0, data_.getMovesProtAgainstMultiTarget().size());
    }

    @Test
    public void completeMembersCombos1Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        EffectCombo eff_;
        eff_ = new EffectCombo();
        eff_.setEffectEndRound(new CustList<EffectEndRoundFoe>());
        EffectEndRoundFoe effect_ = new EffectEndRoundFoe();
        effect_.setEndRoundRank(4);
        eff_.getEffectEndRound().add(effect_);
        data_.initCombosTest();
        data_.getCombos().getEffects().add(new ListEffectCombo(new StringList("FLYING","SURF"), eff_));
        data_.completeMembersCombos();
        assertEq(1, data_.getEvtEndRound().size());
        assertEq(4, data_.getEvtEndRound().first().getNumberIncrement());
        assertEq(EndTurnType.ATTAQUE_COMBI, data_.getEvtEndRound().first().getEndRoundType());
        assertEq("FLYING;SURF", data_.getEvtEndRound().first().getElement());
        assertEq(RelationType.ADV, data_.getEvtEndRound().first().getRelation());
        assertTrue(!data_.getEvtEndRound().first().isIncrementNumberOfRounds());
    }

    @Test
    public void completeVariables1Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        DamagingMoveData moveDamage_;
        moveDamage_ = new DamagingMoveData();
        moveDamage_.setPp((short) 20);
        moveDamage_.setAccuracy("100");
        moveDamage_.setEffects(new CustList<Effect>());
        EffectDamage damage_;
        damage_ = new EffectDamage();
        damage_.setFail("");
        damage_.setPower(StringUtil.concat("1+",DataBase.VAR_PREFIX,"NB_TURN__CHARGE*100+",DataBase.VAR_PREFIX,"NB_TURN__FLYING*100"));
        damage_.setDamageLaw(new MonteCarloString());
        moveDamage_.getEffects().add(damage_);
        moveDamage_.setRepeatRoundLaw(new MonteCarloNumber());
        data_.completeMembers("CHARGE", moveDamage_);
        data_.completeVariables();
        assertEq(1, data_.getVarParamsMove().size());
        assertTrue(data_.getVarParamsMove().contains("NB_TURN"));
        assertEq(2, data_.getVarParamsMove().getVal("NB_TURN").size());
        assertTrue(StringUtil.contains(data_.getVarParamsMove().getVal("NB_TURN"), "CHARGE"));
        assertTrue(StringUtil.contains(data_.getVarParamsMove().getVal("NB_TURN"), "FLYING"));
    }
    @Test
    public void calculateAvgPound1Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        data_.calculateAvgPound();
        assertEq(Rate.newRate("0"),data_.getAvgWeight());
    }
    @Test
    public void calculateAvgPoun2Test() {
        DataBase data_ = newData();
        data_.initializeMembers();
        PokemonData pokemon_ = Instances.newPokemonData();
        pokemon_.setWeight(Rate.one());
        data_.completeMembers("QUEUE_DE_CHEVAL", pokemon_);
        data_.calculateAvgPound();
        assertEq(Rate.newRate("1"),data_.getAvgWeight());
    }
}
