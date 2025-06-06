package aiki.beans.help;

import aiki.beans.*;
import aiki.beans.db.*;
import aiki.db.*;
import aiki.facade.*;
import aiki.facade.enums.*;
import aiki.fight.enums.*;
import aiki.fight.moves.enums.*;
import aiki.instances.*;
import aiki.map.levels.enums.*;
import aiki.map.pokemon.enums.*;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;
import code.util.*;

public abstract class InitDbLangs extends InitDbConstr {
    public static final String C_CA = "C_CA";
    public static final String T_T = "T_T";

    public static CustList<String> callLangsBeanGetKeysAbilities() {
        return str().getKeysAbilities();
    }

    public static CustList<String> callLangsBeanGetKeysBooleans() {
        return str().getKeysBooleans();
    }

    public static CustList<String> callLangsBeanGetKeysCategories() {
        return str().getKeysCategories();
    }

    public static CustList<String> callLangsBeanGetKeysDesc() {
        return str().getKeysDesc();
    }

    public static CustList<String> callLangsBeanGetKeysEnvironments() {
        return str().getKeysEnvironments();
    }

    public static CustList<String> callLangsBeanGetKeysGenders() {
        return str().getKeysGenders();
    }

    public static CustList<String> callLangsBeanGetKeysItems() {
        return str().getKeysItems();
    }

    public static CustList<String> callLangsBeanGetKeysMath() {
        return str().getKeysMath();
    }

    public static CustList<String> callLangsBeanGetKeysMoves() {
        return str().getKeysMoves();
    }

    public static CustList<String> callLangsBeanGetKeysPokemon() {
        return str().getKeysPokemon();
    }

    public static CustList<String> callLangsBeanGetKeysStatistics() {
        return str().getKeysStatistics();
    }

    public static CustList<String> callLangsBeanGetKeysStatus() {
        return str().getKeysStatus();
    }

    public static CustList<String> callLangsBeanGetKeysTargets() {
        return str().getKeysTargets();
    }

    public static CustList<String> callLangsBeanGetKeysTypes() {
        return str().getKeysTypes();
    }

    public static CustList<String> callLangsBeanGetRowAbility(int _args) {
        return str().getRowAbility(_args);
    }

    public static CustList<String> callLangsBeanGetRowBoolean(int _args) {
        return str().getRowBoolean(_args);
    }

    public static CustList<String> callLangsBeanGetRowCategory(int _args) {
        return str().getRowCategory(_args);
    }

    public static CustList<String> callLangsBeanGetRowDesc(int _args) {
        return str().getRowDesc(_args);
    }

    public static CustList<String> callLangsBeanGetRowEnvironment(int _args) {
        return str().getRowEnvironment(_args);
    }

    public static CustList<String> callLangsBeanGetRowGender(int _args) {
        return str().getRowGender(_args);
    }

    public static CustList<String> callLangsBeanGetRowItem(int _args) {
        return str().getRowItem(_args);
    }

    public static CustList<String> callLangsBeanGetRowMath(int _args) {
        return str().getRowMath(_args);
    }

    public static CustList<String> callLangsBeanGetRowMove(int _args) {
        return str().getRowMove(_args);
    }

    public static CustList<String> callLangsBeanGetRowPokemon(int _args) {
        return str().getRowPokemon(_args);
    }

    public static CustList<String> callLangsBeanGetRowStatistic(int _args) {
        return str().getRowStatistic(_args);
    }

    public static CustList<String> callLangsBeanGetRowStatus(int _args) {
        return str().getRowStatus(_args);
    }

    public static CustList<String> callLangsBeanGetRowTarget(int _args) {
        return str().getRowTarget(_args);
    }

    public static CustList<String> callLangsBeanGetRowType(int _args) {
        return str().getRowType(_args);
    }

    public static String callLangsBeanGetTrLang(int _args) {
        return str().getTrLang(_args);
    }

    public static CustList<String> callLangsBeanLanguagesGet() {
        return str().getLanguages();
    }
    protected static LangsBean str() {
        FacadeGame db_ = db();
        pkDataByFacade(db_);
        LangsBean g_ = new LangsBean();
        g_.setBuilder(builder(db_));
        g_.getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.LANGS,new TranslationsFile());
        g_.getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.LANGS,new TranslationsFile());
        initBean(g_,EN,db_);
        beforeDisplaying(g_);
        return g_;
    }
    protected static FacadeGame db(){
        DataBase data_ = newData();
        data_.setLanguage(EN);
        data_.setLanguages(indexesAll());
        data_.initializeMembers();
        data_.initValue(DataBase.DEF_CAT,AUTRE);
        FacadeGame fac_ = new FacadeGame();
        fac_.setLanguages(indexesAll());
        StringMap<String> displayLanguages_ = new StringMap<String>();
        displayLanguages_.addEntry(EN,"EN1");
        displayLanguages_.addEntry(FR,"FR2");
        fac_.setDisplayLanguages(displayLanguages_);
        data_.setDisplayLanguages(displayLanguages_);
        fac_.setData(data_);
        data_.setMessages(fac_.getData());
        fac_.setLoadedData(true);
        fac_.setZipName("");
        fac_.setData(data_);
        fac_.setLanguage(EN);
        data_.getTypes().add(T_T);
        data_.completeMembers(P_POKEMON,Instances.newPokemonData());
        data_.completeMembers(I_ITEM,Instances.newBall());
        data_.completeMembers(M_DAM,Instances.newDamagingMoveData());
        data_.completeMembers(A_ABILITY,Instances.newAbilityData());
        data_.completeMembers(S_STA_SIM,Instances.newStatusSimple());
        firstLg(data_);
        secLg(data_);
        data_.getAllCategories().clear();
        data_.getAllCategories().add(C_CA);
        return fac_;
    }

    private static void secLg(DataBase _data) {
        IdMap<Gender, String> gdrs2_ = new IdMap<Gender, String>();
        gdrs2_.addEntry(Gender.NO_GENDER, "NO_G2");
        gdrs2_.addEntry(Gender.FEMALE,"FE2");
        gdrs2_.addEntry(Gender.MALE,"MA2");
        _data.getTranslatedGenders().addEntry(FR, gdrs2_);
        IdMap<SelectedBoolean, String> bools2_ = new IdMap<SelectedBoolean, String>();
        bools2_.addEntry(SelectedBoolean.NO,"NO2");
        bools2_.addEntry(SelectedBoolean.YES,"YES2");
        bools2_.addEntry(SelectedBoolean.YES_AND_NO,"YES_AND_NO2");
        _data.getTranslatedBooleans().addEntry(FR, bools2_);
        IdMap<EnvironmentType, String> et2_ = new IdMap<EnvironmentType, String>();
        et2_.addEntry(EnvironmentType.ROAD,"ROAD2");
        et2_.addEntry(EnvironmentType.ROCK,"ROCK2");
        et2_.addEntry(EnvironmentType.NOTHING,"NOTHING2");
        et2_.addEntry(EnvironmentType.BUILDING,"BUILDING2");
        et2_.addEntry(EnvironmentType.GRASS,"GRASS2");
        et2_.addEntry(EnvironmentType.DESERT,"DESERT2");
        et2_.addEntry(EnvironmentType.ICE,"ICE2");
        et2_.addEntry(EnvironmentType.SNOW,"SNOW2");
        et2_.addEntry(EnvironmentType.WATER,"WATER2");
        _data.getTranslatedEnvironment().addEntry(FR, et2_);
        IdMap<Statistic, String> stats2_ = new IdMap<Statistic, String>();
        stats2_.addEntry(Statistic.ATTACK,"ATTACK2");
        stats2_.addEntry(Statistic.SPECIAL_ATTACK,"SPECIAL_ATTACK2");
        stats2_.addEntry(Statistic.DEFENSE,"DEFENSE2");
        stats2_.addEntry(Statistic.SPECIAL_DEFENSE,"SPECIAL_DEFENSE2");
        stats2_.addEntry(Statistic.SPEED, "SPEED2");
        stats2_.addEntry(Statistic.ACCURACY,"ACCURACY2");
        stats2_.addEntry(Statistic.EVASINESS,"EVASINESS2");
        stats2_.addEntry(Statistic.HP,"HP2");
        stats2_.addEntry(Statistic.PV_RESTANTS,"PV_RESTANTS2");
        stats2_.addEntry(Statistic.CRITICAL_HIT,"CRITICAL_HIT2");
        _data.getTranslatedStatistics().addEntry(FR, stats2_);
        IdMap<TargetChoice, String> tar2_ = new IdMap<TargetChoice, String>();
        tar2_.addEntry(TargetChoice.ADJ_ADV,"ADJ_ADV2");
        tar2_.addEntry(TargetChoice.ADJ_MULT,"ADJ_MULT2");
        tar2_.addEntry(TargetChoice.ANY_FOE,"ANY_FOE2");
        tar2_.addEntry(TargetChoice.ALLIE,"ALLIE2");
        tar2_.addEntry(TargetChoice.ALLIES,"ALLIES2");
        tar2_.addEntry(TargetChoice.AUTRE_UNIQ,"AUTRE_UNIQ2");
        tar2_.addEntry(TargetChoice.ADJ_UNIQ,"ADJ_UNIQ2");
        tar2_.addEntry(TargetChoice.GLOBALE,"GLOBALE2");
        tar2_.addEntry(TargetChoice.LANCEUR,"LANCEUR2");
        tar2_.addEntry(TargetChoice.NOTHING,"NOTHING2");
        tar2_.addEntry(TargetChoice.TOUS_ADV,"TOUS_ADV2");
        tar2_.addEntry(TargetChoice.PSEUDO_GLOBALE,"PSEUDO_GLOBALE2");
        tar2_.addEntry(TargetChoice.UNIQUE_IMPORTE,"UNIQUE_IMPORTE2");
        _data.getTranslatedTargets().addEntry(FR, tar2_);
        _data.getTranslatedCategories().addEntry(FR,  withTr(C_CA, "C_CA2"));
        _data.getTranslatedTypes().addEntry(FR, withTr(T_T,"T_T2"));
        _data.getTranslatedPokemon().addEntry(FR, withTr(P_POKEMON,"P_POKEMON2"));
        _data.getTranslatedItems().addEntry(FR, withTr(I_ITEM,"I_ITEM2"));
        _data.getTranslatedClassesDescriptions().addEntry(FR, withTr(_data.getItem(I_ITEM).getItemType(),"I_ITEM_2"));
        _data.getTranslatedMoves().addEntry(FR, withTr(M_DAM,"M_DAM2"));
        _data.getTranslatedAbilities().addEntry(FR, withTr(A_ABILITY,"A_ABILITY2"));
        _data.getTranslatedStatus().addEntry(FR, withTr(S_STA_SIM,"S_STA_SIM2"));
        _data.getTranslatedFctMath().addEntry(FR, withTr("lg","lg2"));
    }

    private static void firstLg(DataBase _data) {
        IdMap<Gender, String> gdrs_ = new IdMap<Gender, String>();
        gdrs_.addEntry(Gender.NO_GENDER, "NO_G1");
        gdrs_.addEntry(Gender.FEMALE,"FE1");
        gdrs_.addEntry(Gender.MALE,"MA1");
        _data.getTranslatedGenders().addEntry(EN, gdrs_);
        IdMap<SelectedBoolean, String> bools_ = new IdMap<SelectedBoolean, String>();
        bools_.addEntry(SelectedBoolean.NO,"NO1");
        bools_.addEntry(SelectedBoolean.YES,"YES1");
        bools_.addEntry(SelectedBoolean.YES_AND_NO,"YES_AND_NO1");
        _data.getTranslatedBooleans().addEntry(EN, bools_);
        IdMap<EnvironmentType, String> et_ = new IdMap<EnvironmentType, String>();
        et_.addEntry(EnvironmentType.ROAD,"ROAD1");
        et_.addEntry(EnvironmentType.ROCK,"ROCK1");
        et_.addEntry(EnvironmentType.NOTHING,"NOTHING1");
        et_.addEntry(EnvironmentType.BUILDING,"BUILDING1");
        et_.addEntry(EnvironmentType.GRASS,"GRASS1");
        et_.addEntry(EnvironmentType.DESERT,"DESERT1");
        et_.addEntry(EnvironmentType.ICE,"ICE1");
        et_.addEntry(EnvironmentType.SNOW,"SNOW1");
        et_.addEntry(EnvironmentType.WATER,"WATER1");
        _data.getTranslatedEnvironment().addEntry(EN, et_);
        IdMap<Statistic, String> stats_ = new IdMap<Statistic, String>();
        stats_.addEntry(Statistic.ATTACK,"ATTACK1");
        stats_.addEntry(Statistic.SPECIAL_ATTACK,"SPECIAL_ATTACK1");
        stats_.addEntry(Statistic.DEFENSE,"DEFENSE1");
        stats_.addEntry(Statistic.SPECIAL_DEFENSE,"SPECIAL_DEFENSE1");
        stats_.addEntry(Statistic.SPEED, "SPEED1");
        stats_.addEntry(Statistic.ACCURACY,"ACCURACY1");
        stats_.addEntry(Statistic.EVASINESS,"EVASINESS1");
        stats_.addEntry(Statistic.HP,"HP1");
        stats_.addEntry(Statistic.PV_RESTANTS,"PV_RESTANTS1");
        stats_.addEntry(Statistic.CRITICAL_HIT,"CRITICAL_HIT1");
        _data.getTranslatedStatistics().addEntry(EN, stats_);
        IdMap<TargetChoice, String> tar_ = new IdMap<TargetChoice, String>();
        tar_.addEntry(TargetChoice.ADJ_ADV,"ADJ_ADV1");
        tar_.addEntry(TargetChoice.ADJ_MULT,"ADJ_MULT1");
        tar_.addEntry(TargetChoice.ANY_FOE,"ANY_FOE1");
        tar_.addEntry(TargetChoice.ALLIE,"ALLIE1");
        tar_.addEntry(TargetChoice.ALLIES,"ALLIES1");
        tar_.addEntry(TargetChoice.AUTRE_UNIQ,"AUTRE_UNIQ1");
        tar_.addEntry(TargetChoice.ADJ_UNIQ,"ADJ_UNIQ1");
        tar_.addEntry(TargetChoice.GLOBALE,"GLOBALE1");
        tar_.addEntry(TargetChoice.LANCEUR,"LANCEUR1");
        tar_.addEntry(TargetChoice.NOTHING,"NOTHING1");
        tar_.addEntry(TargetChoice.TOUS_ADV,"TOUS_ADV1");
        tar_.addEntry(TargetChoice.PSEUDO_GLOBALE,"PSEUDO_GLOBALE1");
        tar_.addEntry(TargetChoice.UNIQUE_IMPORTE,"UNIQUE_IMPORTE1");
        _data.getTranslatedTargets().addEntry(EN, tar_);
        _data.getTranslatedCategories().addEntry(EN, withTr(C_CA, "C_CA1"));
        _data.getTranslatedTypes().addEntry(EN, withTr(T_T,"T_T1"));
        _data.getTranslatedPokemon().addEntry(EN, withTr(P_POKEMON,"P_POKEMON1"));
        _data.getTranslatedItems().addEntry(EN, withTr(I_ITEM,"I_ITEM1"));
        _data.getTranslatedClassesDescriptions().addEntry(EN, withTr(_data.getItem(I_ITEM).getItemType(),"I_ITEM_1"));
        _data.getTranslatedMoves().addEntry(EN, withTr(M_DAM,"M_DAM1"));
        _data.getTranslatedAbilities().addEntry(EN, withTr(A_ABILITY,"A_ABILITY1"));
        _data.getTranslatedStatus().addEntry(EN, withTr(S_STA_SIM,"S_STA_SIM1"));
        _data.getTranslatedFctMath().addEntry(EN, withTr("lg","lg1"));
    }

    private static StringMap<String> withTr(String _key, String _value) {
        StringMap<String> cat_ = new StringMap<String>();
        cat_.addEntry(_key, _value);
        return cat_;
    }
}
