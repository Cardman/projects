package aiki.gui;

import aiki.db.*;
import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.items.Item;
import aiki.fight.moves.enums.*;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.gui.components.editor.*;
import aiki.instances.*;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.enums.*;
import code.gui.initialize.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.sml.util.*;
import code.util.*;

public abstract class InitEditorPkForm extends EquallableAikiGuiUtil {

    public static final String P_1 = "P1";
    public static final String T_1 = "T1";
    public static final String T_2 = "T2";
    public static final String T_3 = "T3";
    public static final String P_2 = "P2";
    public static final String P_3 = "P3";
    public static final String P_4 = "P_4";
    public static final String M_1 = "M1";
    public static final String M_2 = "M2";
    public static final String M_3 = "M3";
    public static final String M_4 = "M4";
    public static final String I_1 = "I1";
    public static final String I_2 = "I2";
    public static final String I_3 = "I3";
    public static final String I_4 = "I4";
    public static final String A_1 = "A1";
    public static final String A_2 = "A2";
    public static final String A_3 = "A3";
    public static final String A_4 = "A4";
    public static final String S_1 = "S1";
    public static final String S_2 = "S2";
    public static final String S_3 = "S3";
    public static final String S_4 = "S4";
    public static final String C_1 = "C1";
    public static final String C_2 = "C2";
    public static final String C_3 = "C3";

    protected WindowPkEditor window(AbstractProgramInfos _core, FacadeGame _facade) {
        return new WindowPkEditor(_core,_facade);
    }
    protected MockProgramInfos initForms() {
        MockProgramInfos pr_ = build("/__/", "/_/", dbs(0.75));
        pr_.setLanguages(new StringList(EN,FR));
        TranslationsLg en_ = pr_.lg(EN);
        TranslationsLg fr_ = pr_.lg(FR);
        MessagesPkEditor.enTr(MessagesPkEditor.initAppliTr(en_));
        MessagesPkEditor.frTr(MessagesPkEditor.initAppliTr(fr_));
        pr_.setLanguage(EN);
        return pr_;
    }
    protected FacadeGame facade(MockProgramInfos _m) {
        FacadeGame facade_ = core(_m);
        StringMap<String> allTypes_ = new StringMap<String>();
        allTypes_.addEntry(T_1,"t1");
        allTypes_.addEntry(T_2,"t2");
        allTypes_.addEntry(T_3,"t3");
        facade_.getData().getTranslatedTypes().addEntry(_m.getLanguage(), allTypes_);
        StringMap<String> allCa_ = new StringMap<String>();
        allCa_.addEntry(C_1,"c1");
        allCa_.addEntry(C_2,"c2");
        allCa_.addEntry(C_3,"c3");
        facade_.getData().getTranslatedCategories().addEntry(_m.getLanguage(), allCa_);
        IdMap<Statistic, String> trsStat_ = new IdMap<Statistic, String>();
        trsStat_.addEntry(Statistic.ATTACK, "a");
        trsStat_.addEntry(Statistic.DEFENSE, "d");
        trsStat_.addEntry(Statistic.SPECIAL_ATTACK, "_a");
        trsStat_.addEntry(Statistic.SPECIAL_DEFENSE, "_d");
        trsStat_.addEntry(Statistic.SPEED, "s");
        trsStat_.addEntry(Statistic.HP, "h");
        facade_.getData().getTranslatedStatistics().addEntry(_m.getLanguage(), trsStat_);
        StringMap<String> allPk_ = new StringMap<String>();
        allPk_.addEntry(P_1,"p1");
        allPk_.addEntry(P_2,"p2");
        allPk_.addEntry(P_3,"p3");
        facade_.getData().getTranslatedPokemon().addEntry(_m.getLanguage(), allPk_);
        StringMap<String> allMv_ = new StringMap<String>();
        allMv_.addEntry(M_1,"m1");
        allMv_.addEntry(M_2,"m2");
        allMv_.addEntry(M_3,"m3");
        facade_.getData().getTranslatedMoves().addEntry(_m.getLanguage(), allMv_);
        StringMap<String> allIt_ = new StringMap<String>();
        allIt_.addEntry(I_1,"i1");
        allIt_.addEntry(I_2,"i2");
        allIt_.addEntry(I_3,"i3");
        facade_.getData().getTranslatedItems().addEntry(_m.getLanguage(), allIt_);
        StringMap<String> allAb_ = new StringMap<String>();
        allAb_.addEntry(A_1,"a1");
        allAb_.addEntry(A_2,"a2");
        allAb_.addEntry(A_3,"a3");
        facade_.getData().getTranslatedAbilities().addEntry(_m.getLanguage(), allAb_);
        StringMap<String> allSt_ = new StringMap<String>();
        allSt_.addEntry(S_1,"s1");
        allSt_.addEntry(S_2,"s2");
        allSt_.addEntry(S_3,"s3");
        facade_.getData().getTranslatedStatus().addEntry(_m.getLanguage(), allSt_);
        IdMap<Gender, String> g_ = new IdMap<Gender, String>();
        g_.addEntry(Gender.NO_GENDER,"_");
        facade_.getData().getTranslatedGenders().addEntry(_m.getLanguage(), g_);
        IdMap<TargetChoice, String> t_ = new IdMap<TargetChoice, String>();
        t_.addEntry(TargetChoice.ANY_FOE,"_");
        facade_.getData().getTranslatedTargets().addEntry(_m.getLanguage(), t_);
        IdMap<EnvironmentType, String> e_ = new IdMap<EnvironmentType, String>();
        e_.addEntry(EnvironmentType.NOTHING,"0");
        e_.addEntry(EnvironmentType.ROAD,"1");
        facade_.getData().getTranslatedEnvironment().addEntry(_m.getLanguage(), e_);
        StringMap<String> itCl_ = new StringMap<String>();
        itCl_.addEntry(Item.BALL,Item.BALL);
        itCl_.addEntry(Item.BERRY,Item.BERRY);
        itCl_.addEntry(Item.BOOST,Item.BOOST);
        itCl_.addEntry(Item.EVOLVING_ITEM,Item.EVOLVING_ITEM);
        itCl_.addEntry(Item.EVOLVING_STONE,Item.EVOLVING_STONE);
        itCl_.addEntry(Item.FOSSIL,Item.FOSSIL);
        itCl_.addEntry(Item.HEALING_HP,Item.HEALING_HP);
        itCl_.addEntry(Item.HEALING_HP_STATUS,Item.HEALING_HP_STATUS);
        itCl_.addEntry(Item.HEALING_ITEM,Item.HEALING_ITEM);
        itCl_.addEntry(Item.HEALING_PP,Item.HEALING_PP);
        itCl_.addEntry(Item.HEALING_STATUS,Item.HEALING_STATUS);
        itCl_.addEntry(Item.ITEM_FOR_BATTLE,Item.ITEM_FOR_BATTLE);
        itCl_.addEntry(Item.REPEL,Item.REPEL);
        itCl_.addEntry(Item.SELLING_ITEM,Item.SELLING_ITEM);
        facade_.getData().getTranslatedClassesDescriptions().addEntry(_m.getLanguage(), itCl_);
        return facade_;
    }

    protected FacadeGame core(MockProgramInfos _m) {
        FacadeGame facade_ = new FacadeGame();
        facade_.setLanguages(_m.getLanguages());
        facade_.setDisplayLanguages(_m.getDisplayLanguages());
        facade_.setSimplyLanguage(_m.getLanguage());
        facade_.setData(new DataBase(new DefaultGenerator(new CustomSeedGene())));
        facade_.getData().initTranslations();
        facade_.getData().initializeMembers();
        facade_.getData().setCombos(Instances.newCombos());
        facade_.getData().setMap(Instances.newDataMap());
        facade_.getData().setLanguages(_m.getLanguages());
        facade_.getData().setVarParamsMove(new StringMap<StringList>());
        return facade_;
    }

    protected AbsGeneComponentModelSubscribe<String> keyEvos(CrudGeneFormSimpleForm<String, Evolution> _evos) {
        return _evos.getKey();
    }

    protected GeneComponentModelEvolution valueEvos(CrudGeneFormSimpleForm<String,Evolution> _evos) {
        return ((GeneComponentModelSubscribeEvolution)_evos.getValue()).getCrud();
    }
}
