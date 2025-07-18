package aiki.gui;

import aiki.db.*;
import aiki.facade.*;
import aiki.facade.enums.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.pokemon.evolution.*;
import aiki.fight.status.StatusSimple;
import aiki.fight.util.*;
import aiki.game.params.enums.*;
import aiki.game.player.enums.*;
import aiki.gui.components.editor.*;
import aiki.instances.*;
import aiki.map.*;
import aiki.map.buildings.*;
import aiki.map.characters.*;
import aiki.map.characters.enums.*;
import aiki.map.enums.*;
import aiki.map.levels.*;
import aiki.map.levels.enums.*;
import aiki.map.places.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.*;
import aiki.map.util.*;
import aiki.sml.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.images.*;
import code.maths.*;
import code.maths.litteral.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.*;
import code.sml.core.*;
import code.sml.util.*;
import code.util.*;
import code.util.core.*;

public abstract class InitEditorPkForm extends EquallableAikiGuiUtil {
    public static final String BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
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

    public static final String MINI6 = "mini6";
    public static final String MINI5 = "mini5";
    public static final String MINI4 = "mini4";
    public static final String MINI3 = "mini3";
    public static final String MINI2 = "mini2";
    public static final String MINI1 = "mini1";
    public static final String MINI = "mini";
    public static final String PIKACHU ="PIKACHU";
    public static final String PROG_PK2="PK_2";
    public static final String LANGUAGE = StringUtil.EN;
    public static final String ECLAIR = "ECLAIR";
    public static final String ECLAIR_2 = "ECLAIR2";
    public static final String ECLAIR_3 = "ECLAIR3";
    public static final String PARATONNERRE = "PARATONNERRE";
    public static final String ELECTRICK = "ELECTRICK";
    public static final String POKE_BALL = "POKE_BALL";
    public static final String ALLY = "ally";
    public static final String SNOW = "snow";
    public static final String GRASS = "grass";
    public static final String DAFAULT = "dafault";
    public static final String GERANT = "gerant";
    public static final String LINK = "link";
    public static final String TRAINER_TWO = "trainer_two";
    public static final String TRAINER_ONE = "trainer_one";
    public static final String PERSON = "person";
    public static final String TRAINER = "trainer";
    public static final String DEFAULT = "default";
    public static final String NOTHING = "nothing";
    public static final String DESERT = "desert";
    public static final String ROCK = "rock";
    public static final String WATER = "water";
    public static final String ROAD = "road";
    public static final String BUILDING = "building";
    public static final String NULL_REF = DataBase.EMPTY_STRING;
    public static final String TAB = "\t";
    public static final String VAR_PREFIX = MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS;
    public static void enterTextField(AbsTextField _t) {
        ((MockTextField)_t).getAbsAdvActionListeners().get(0).action(null,null);
    }

    public static String imgDoc(int[][] _img, MockProgramInfos _pr) {
//    private static String imgDoc(ImageArrayBaseSixtyFour _img, String _kind, String _name)
        String base_ = GamesPk.baseEncode(_pr.getTranslations());
        String res_ = BaseSixtyFourUtil.getStringByImage(_img, base_);
        Document doc_ = DocumentBuilder.newXmlDocument();
        Element element_ = doc_.createElement(DocumentWriterCoreUtil.ANON_TAG);
//        element_.setAttribute(DocumentWriterCoreUtil.FIELD,_kind);
//        element_.setAttribute(DocumentWriterCoreUtil.VALUE,_name);
        element_.setAttribute(DocumentWriterAikiCoreUtil.ATTR_IMG,res_);
        element_.setAttribute(DocumentWriterAikiCoreUtil.ATTR_IMG_BASE, base_);
        doc_.appendChild(element_);
        return doc_.export();
    }

    public static void tryLoad(GeneComponentModelImg _g, String _f) {
        tryLoad(_g.getContent(), _f);
    }

    public static void tryLoad(GeneComponentModelImgFree _g, String _f) {
        tryLoad(_g.getContent(), _f);
    }

    public static void tryLoad(ContentGeneComponentModelImg _g, String _f) {
        _g.getFileDialogContent().getFileName().setText(_f);
        ((MockAbstractAction) GuiBaseUtil.getAction(_g.getFileDialogContent().getFileName(), GuiConstants.VK_ENTER,0)).action();
        ((MockPlainButton) ((MockPanel)_g.getFileDialogContent().getButtons()).getComponent(0)).getActionListeners().first().action();
    }

    protected WindowPkEditor window(AbstractProgramInfos _core, FacadeGame _facade) {
        WindowPkEditor w_ = new WindowPkEditor(_core);
        w_.dataBase(_facade.getData());
        return w_;
    }

    protected WindowPkEditor windowNew(AbstractProgramInfos _core) {
        WindowPkEditor w_ = new WindowPkEditor(_core);
        w_.newData();
        return w_;
    }
    protected MockProgramInfos initForms() {
        MockProgramInfos pr_ = build("/__/", "/_/", dbs(0.75));
        pr_.setLanguages(new StringList(EN,FR));
        TranslationsLg en_ = pr_.lg(EN);
        TranslationsLg fr_ = pr_.lg(FR);
        MessagesPkEditor.enTr(MessagesPkEditor.initAppliTr(en_));
        MessagesPkEditor.frTr(MessagesPkEditor.initAppliTr(fr_));
        en_.getMapping().addEntry(MessagesDataBaseConstants.SC_APP,appli());
        fr_.getMapping().addEntry(MessagesDataBaseConstants.SC_APP,appli());
        TranslationsAppli data_ = data();
        en_.getMapping().addEntry(MessagesPkBean.APP_BEAN_DATA, data_);
        fr_.getMapping().addEntry(MessagesPkBean.APP_BEAN_DATA, data_);
        pr_.setLanguage(EN);
        return pr_;
    }
    private static TranslationsAppli data(){
        TranslationsAppli m = new TranslationsAppli();
        m.getMapping().addEntry(MessagesPkBean.INDEX,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.POKEDEX,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.PK_DATA,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EVO_HAPPINESS,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EVO_ITEM,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EVO_LEVEL,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EVO_LEVEL_GENDER,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EVO_MOVE,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EVO_STONE,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EVO_STONE_GENDER,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EVO_TEAM,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EVO_TYPE,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.MV_DATA,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.COMBO,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_ACCURACY,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_ALLY,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_BATONPASS,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_CLONE,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_COMMONSTATISTICS,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_COPYFIGHTER,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_COPYMOVE,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_COUNTERATTACK,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_DAMAGE,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_DAMAGERATE,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_ENDROUND,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_FULLHPRATE,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_GLOBAL,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_INVOKE,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_MULTMOVEPOWER,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_MULTSUFFEREDMOVEPOWER,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_MULTUSEDMOVEPOWER,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_ORDER,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_PROTECTFROMTYPES,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_PROTECTION,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_REMAINEDHPRATE,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_RESTRICTION,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_STATIS,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_STATUS,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_SWITCHABILITIES,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_SWITCHITEMS,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_SWITCHMOVESTYPES,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_SWITCHPOINTVIEW,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_SWITCHPOSITION,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_SWITCHTYPES,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_TEAM,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_TEAMWHILESENDINGFOE,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_UNPROTECTFROMTYPES,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_VARPP,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.EFF_WINMONEY,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.ENDROUND_ENDROUND,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.ENDROUND_EVENT,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.ENDROUND_FOE,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.ENDROUND_INDIVIDUAL,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.ENDROUND_MULTIRELATION,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.ENDROUND_POSITIONRELATION,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.ENDROUND_POSITIONTARGET,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.ENDROUND_SINGLERELATION,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.ENDROUND_STATUS,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.ENDROUND_STATUSRELATION,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.ENDROUND_TEAM,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.SENDING,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.AB_DATA,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.ABILITIES,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.MOVES,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.IT_BALL,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.IT_BERRY,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.IT_BOOST,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.IT_EVOITEM,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.IT_EVOSTONE,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.IT_FOSSIL,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.IT_HEALINGHP,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.IT_HEALINGHPSTATUS,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.IT_HEALINGITEM,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.IT_HEALINGPP,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.IT_HEALINGSTATUS,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.IT_ITEM,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.IT_ITEMFORBATTLE,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.IT_REPEL,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.ITEMS,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.STATUS,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.STATUSSET,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.MAP,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.NPC,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.GENERAL,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.LANGS,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.SOLUTION,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.ROUND,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.SIMU,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.SIMU_LEVEL,new TranslationsFile());
        m.getMapping().addEntry(MessagesPkBean.DIFFICULTY,new TranslationsFile());
        return m;
    }
    private static TranslationsAppli appli() {
        TranslationsAppli ta_ = new TranslationsAppli();
        ta_.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_STATISTICS,stats());
        ta_.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_TARGETS,target());
        ta_.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_ENVIRONMENTS,environment());
        ta_.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_GENDERS,gender());
        ta_.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_BOOLEANS,booleans());
        ta_.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_MODELLAW,variat());
        ta_.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_WINPTS,diff());
        ta_.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_CLASSES,clDesc());
        ta_.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_MATH,fctMath());
        return ta_;
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
        IdMap<DifficultyModelLaw, String> dm_ = new IdMap<DifficultyModelLaw, String>();
        dm_.addEntry(DifficultyModelLaw.UNIFORME,"0");
        facade_.getData().getTranslatedDiffModelLaw().addEntry(_m.getLanguage(), dm_);
        IdMap<DifficultyWinPointsFight, String> dw_ = new IdMap<DifficultyWinPointsFight, String>();
        dw_.addEntry(DifficultyWinPointsFight.DIFFICILE,"0");
        facade_.getData().getTranslatedDiffWinPts().addEntry(_m.getLanguage(), dw_);
        IdMap<SelectedBoolean, String> sel_ = new IdMap<SelectedBoolean, String>();
        sel_.addEntry(SelectedBoolean.YES_AND_NO,"0");
        facade_.getData().getTranslatedBooleans().addEntry(_m.getLanguage(), sel_);
        facade_.getData().getTranslatedClassesDescriptions().addEntry(_m.getLanguage(), clDesc().getMapping());
        StringMap<String> litteral_ = new StringMap<String>();
        litteral_.addEntry(MessagesDataBaseConstants.DEF_NIVEAU, StringUtil.concat("level","\t","l","\t","The level of the Pokemon"));
        litteral_.addEntry(MessagesDataBaseConstants.DEF_BOOST, StringUtil.concat("boost","\t","b","\t","The boost of the Pokemon"));
        facade_.getData().getLitterals().addEntry(_m.getLanguage(),litteral_);
        facade_.getData().getTranslatedFctMath().addEntry(_m.getLanguage(),fctMath().getMapping());
        return facade_;
    }

    private static TranslationsFile fctMath() {
        TranslationsFile fct_ = new TranslationsFile();
        fct_.add(MbOperationNode.PUIS,"_");
        return fct_;
    }

    private static TranslationsFile clDesc() {
        TranslationsFile itCl_ = new TranslationsFile();
        itCl_.add(Item.BALL,Item.BALL);
        itCl_.add(Item.BERRY,Item.BERRY);
        itCl_.add(Item.BOOST,Item.BOOST);
        itCl_.add(Item.EVOLVING_ITEM,Item.EVOLVING_ITEM);
        itCl_.add(Item.EVOLVING_STONE,Item.EVOLVING_STONE);
        itCl_.add(Item.FOSSIL,Item.FOSSIL);
        itCl_.add(Item.HEALING_HP,Item.HEALING_HP);
        itCl_.add(Item.HEALING_HP_STATUS,Item.HEALING_HP_STATUS);
        itCl_.add(Item.HEALING_ITEM,Item.HEALING_ITEM);
        itCl_.add(Item.HEALING_PP,Item.HEALING_PP);
        itCl_.add(Item.HEALING_STATUS,Item.HEALING_STATUS);
        itCl_.add(Item.ITEM_FOR_BATTLE,Item.ITEM_FOR_BATTLE);
        itCl_.add(Item.REPEL,Item.REPEL);
        itCl_.add(Item.SELLING_ITEM,Item.SELLING_ITEM);
        return itCl_;
    }

    private static TranslationsFile stats() {
        TranslationsFile trsStat_ = new TranslationsFile();
        trsStat_.add(DataBase.DEF_STAT_ATTACK, "a");
        trsStat_.add(DataBase.DEF_STAT_DEFENSE, "d");
        trsStat_.add(DataBase.DEF_STAT_SPECIAL_ATTACK, "_a");
        trsStat_.add(DataBase.DEF_STAT_SPECIAL_DEFENSE, "_d");
        trsStat_.add(DataBase.DEF_STAT_SPEED, "s");
        trsStat_.add(DataBase.DEF_STAT_HP, "h");
        return trsStat_;
    }

    private static TranslationsFile gender() {
        TranslationsFile trsStat_ = new TranslationsFile();
        trsStat_.add(DataBase.DEF_GENDER_NO_GENDER,"_");
        return trsStat_;
    }

    private static TranslationsFile target() {
        TranslationsFile trsStat_ = new TranslationsFile();
        trsStat_.add(DataBase.DEF_TARGET_ANY_FOE,"_");
        return trsStat_;
    }

    private static TranslationsFile environment() {
        TranslationsFile trsStat_ = new TranslationsFile();
        trsStat_.add(DataBase.DEF_ENV_NOTHING,"0");
        trsStat_.add(DataBase.DEF_ENV_ROAD,"1");
        return trsStat_;
    }

    private static TranslationsFile booleans() {
        TranslationsFile trsStat_ = new TranslationsFile();
        trsStat_.add(DataBase.DEF_SEL_BOOL_YES_AND_NO,"0");
        return trsStat_;
    }

    private static TranslationsFile variat() {
        TranslationsFile trsStat_ = new TranslationsFile();
        trsStat_.add(DataBase.DEF_UNIFORME,"0");
        return trsStat_;
    }

    private static TranslationsFile diff() {
        TranslationsFile trsStat_ = new TranslationsFile();
        trsStat_.add(DataBase.DEF_DIFFICILE,"0");
        return trsStat_;
    }

    /*IdMap<DifficultyModelLaw, String> dm_ = new IdMap<DifficultyModelLaw, String>();
        dm_.addEntry(DifficultyModelLaw.UNIFORME,"0");
        facade_.getData().getTranslatedDiffModelLaw().addEntry(_m.getLanguage(), dm_);
        IdMap<DifficultyWinPointsFight, String> dw_ = new IdMap<DifficultyWinPointsFight, String>();
        dw_.addEntry(DifficultyWinPointsFight.DIFFICILE,"0");
        facade_.getData().getTranslatedDiffWinPts().addEntry(_m.getLanguage(), dw_);*/
    protected FacadeGame core(MockProgramInfos _m) {
        FacadeGame facade_ = ConverterCommonMapUtil.facadeInit(_m);
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
    public static DataBase initDb() {
        DataBase data_ = coreDataBase();
        data_.sortEndRound();
        data_.getMap().setMiniMap(new MiniMapCoordsList());
        data_.getMap().setUnlockedCity(NULL_REF);
        data_.getMap().setSideLength(1);
        data_.addLink(LINK, inst(new int[][]{new int[]{-255}}));
        data_.setImageTmHm(inst(new int[][]{new int[]{-800}}));
        data_.setAnimAbsorb(inst(new int[][]{new int[]{-700}}));
        data_.setStorage(inst(new int[][]{new int[]{-3}}));
        data_.addImage(BUILDING, inst(new int[][]{new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985}}));
        data_.addImage(NOTHING, inst(new int[][]{new int[]{-16777216,-16777216},new int[]{-16777216,-16777216}}));
        data_.addImage(ROAD, inst(new int[][]{new int[]{-7369361,-7369361},new int[]{-7369361,-7369361}}));
        data_.addImage(WATER, inst(new int[][]{new int[]{-16776961,-16776961},new int[]{-16776961,-16776961}}));
        data_.addPerson(TRAINER, inst(new int[][]{new int[]{-18000}}));
        data_.addPerson(PERSON, inst(new int[][]{new int[]{-1800}}));
        data_.addPerson(TRAINER_ONE, inst(new int[][]{new int[]{-19000}}));
        data_.addPerson(TRAINER_TWO, inst(new int[][]{new int[]{-19008}}));
        data_.addPerson(ALLY, inst(new int[][]{new int[]{-19508}}));
        data_.addPerson(GERANT, inst(new int[][]{new int[]{-20508}}));
        data_.addTrainerImage(TRAINER, inst(new int[][]{new int[]{-18000}}));
        data_.addTrainerImage(TRAINER_ONE, inst(new int[][]{new int[]{-19000}}));
        data_.addTrainerImage(TRAINER_TWO, inst(new int[][]{new int[]{-19008}}));
        data_.addTrainerImage(ALLY, inst(new int[][]{new int[]{-19508}}));
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Direction.DOWN, Sex.NO), inst(new int[][]{new int[]{1}}));
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Direction.UP,Sex.NO), inst(new int[][]{new int[]{1}}));
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Direction.LEFT,Sex.NO), inst(new int[][]{new int[]{1}}));
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Direction.RIGHT,Sex.NO), inst(new int[][]{new int[]{1}}));
        data_.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Sex.NO), inst(new int[][]{new int[]{1}}));
        data_.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Sex.NO), inst(new int[][]{new int[]{1}}));
        data_.getMiniPk().addEntry(PIKACHU, inst(new int[][]{new int[]{2}}));
        data_.getMaxiPkBack().addEntry(PIKACHU, inst(new int[][]{new int[]{2}}));
        data_.getMaxiPkFront().addEntry(PIKACHU, inst(new int[][]{new int[]{2}}));
        data_.getMiniItems().addEntry(POKE_BALL, inst(new int[][]{new int[]{2}}));
        data_.getAnimStatus().addEntry(PERSON, inst(new int[][]{new int[]{3}}));
        data_.getAnimStatis().addEntry(Statistic.ATTACK.getStatName(), inst(new int[][]{new int[]{3}}));
        data_.getAnimStatis().addEntry(Statistic.SPECIAL_ATTACK.getStatName(), inst(new int[][]{new int[]{3}}));
        data_.getAnimStatis().addEntry(Statistic.DEFENSE.getStatName(), inst(new int[][]{new int[]{3}}));
        data_.getAnimStatis().addEntry(Statistic.SPECIAL_DEFENSE.getStatName(), inst(new int[][]{new int[]{3}}));
        data_.getAnimStatis().addEntry(Statistic.SPEED.getStatName(), inst(new int[][]{new int[]{3}}));
        data_.getAnimStatis().addEntry(Statistic.ACCURACY.getStatName(), inst(new int[][]{new int[]{3}}));
        data_.getAnimStatis().addEntry(Statistic.EVASINESS.getStatName(), inst(new int[][]{new int[]{3}}));
        data_.getAnimStatis().addEntry(Statistic.CRITICAL_HIT.getStatName(), inst(new int[][]{new int[]{3}}));
        data_.getTypesImages().addEntry(ELECTRICK, inst(new int[][]{new int[]{4}}));
        data_.getTypesColors().addEntry(ELECTRICK,"1;3;5");
        data_.setEndGameImage(inst(new int[][]{new int[1]}));
        initBegin(data_);

        initBlockFirstCity(data_);
        initBuildingsFirstCity(data_);
        initTrainersFirstCity(data_);
        initPokemonCenterFirstCity(data_);

        initLeague(data_);
        initBlockLeague(data_);
        initLeagueTrainers(data_);

        initBlockFirstRoad(data_);
        initFirstRoadAreas(data_);

        data_.getMap().join(0, 2,newPoint(0,0),newPoint(5,0), Direction.LEFT);

        Cave cave_ = Instances.newCave();
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getBlocks().addEntry(newPoint(0,0), newBlock(2, 2,EnvironmentType.ROAD,ROAD,-1));
        cave_.getLevels().add(levelCave_);
        data_.getMap().addPlace(cave_);

        Road road_ = (Road) data_.getMap().getPlace(2);
        road_.getLinksWithCaves().addEntry(newPoint(5,5),new Link(LINK, newCoords(3,0,0,0)));
        cave_.getLinksWithOtherPlaces().addEntry(newLevelPoint(0,0,0),new Link(LINK, newCoords(2,0,5,5)));



        initMiniMap(data_);
        data_.completeVariables();
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.initTypesByTable();
        initTranslations(data_);
        data_.initFamilies();
        return data_;
    }

    private static ImageArrayBaseSixtyFour inst(int[][] _a) {
        ImageArrayBaseSixtyFour in_ = instance(_a);
        in_.setBase(BASE);
        return in_;
    }

    public static void initBegin(DataBase _data) {
        DataMap map_ = _data.getMap();
        WildPk pkm_ = new WildPk();
        pkm_.setName(PIKACHU);
        pkm_.setAbility(PARATONNERRE);
        pkm_.setGender(Gender.NO_GENDER);
        pkm_.setItem(NULL_REF);
        pkm_.setLevel( 7);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 2, 1));
    }
    public static DataBase coreDataBase() {
        DataBase data_ = new DataBase(DefaultGenerator.oneElt());
        data_.defValues();
        updateLg(data_);
        data_.initializeMembers();
        initConstants(data_);
        PokemonData pkData_ = Instances.newPokemonData();
        pkData_.setBaseEvo(PIKACHU);
        pkData_.setEggGroups(new StringList(data_.getDefaultEggGroup()));
        pkData_.setTypes(new StringList(ELECTRICK));
        statBase(pkData_);
        pkData_.getLevMoves().add(new LevelMove(1,ECLAIR));
        pkData_.setExpRate(1);
        pkData_.setHeight(Rate.one());
        pkData_.setWeight(Rate.one());
        pkData_.setCatchingRate( 1);
        pkData_.setHappiness( 1);
        pkData_.setHappinessHatch( 1);
        pkData_.setAbilities(new StringList(PARATONNERRE));
        data_.completeMembers(PIKACHU,pkData_);
        data_.completeMembers(PARATONNERRE,Instances.newAbilityData());
        data_.completeMembers(ECLAIR,learn(Statistic.ATTACK));
        data_.completeMembers(ECLAIR_2, learn(Statistic.SPECIAL_ATTACK));
        data_.completeMembers(ECLAIR_3, def());
        data_.completeMembers(POKE_BALL,Instances.newBall());
        StatusSimple status_ = Instances.newStatusSimple();
        status_.setCatchingRate(Rate.one());
        data_.completeMembers(PERSON, status_);
        EffectCombo combo_ = Instances.newEffectCombo();
        combo_.getRepeatedRoundsLaw().addQuickEvent(Rate.one(),LgInt.one());
        data_.getTableTypes().addEntry(new TypesDuo(ELECTRICK,ELECTRICK),Rate.one());
        data_.setMap(Instances.newDataMap());
        data_.setCombos(Instances.newCombos());
        data_.getCombos().getEffects().add(new ListEffectCombo(new StringList(ECLAIR,ECLAIR_2), combo_));
        data_.getConstNum().addEntry(DataBase.STRONG_MOVE,Rate.newRate("90"));
        return data_;
    }

    protected static void updateLg(DataBase _db) {
        _db.setLanguage(LANGUAGE);
        _db.setLanguages(new StringList(LANGUAGE));
    }
    private static DamagingMoveData def() {
        EffectDamage eff_;
        DamagingMoveData sec_ = Instances.newDamagingMoveData();
        eff_ = Instances.newEffectDamage();
        eff_.setPower("100");
        eff_.setFail("");
        eff_.setTargetChoice(TargetChoice.TOUS_ADV);
        eff_.setStatisAtt(Statistic.ATTACK);
        sec_.getEffects().add(eff_);
        sec_.setTargetChoice(TargetChoice.TOUS_ADV);
        sec_.setTypes(new StringList(ELECTRICK));
        sec_.setCategory("SPEC");
        sec_.setAccuracy("1");
        sec_.setPp( 1);
        return sec_;
    }

    private static DamagingMoveData learn(Statistic _stat) {
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage eff_ = Instances.newEffectDamage();
        eff_.setPower("100");
        eff_.setFail("");
        eff_.setTargetChoice(TargetChoice.TOUS_ADV);
        eff_.setStatisAtt(_stat);
        move_.getEffects().add(eff_);
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setTypes(new StringList(ELECTRICK));
        move_.setCategory("SPEC");
        move_.setAccuracy("1");
        move_.setPp( 1);
        return move_;
    }

    public static void statBase(PokemonData _pk) {
        _pk.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv(1,0));
        _pk.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv(1,0));
        _pk.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv(1,0));
        _pk.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv(1,0));
        _pk.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv(1,0));
        _pk.getStatistics().addEntry(Statistic.HP,new StatBaseEv(1,0));
    }

    public static void initConstants(DataBase _data) {
        _data.addConstNumTest(DataBase.MAX_EV, new Rate(20));
        _data.addConstNumTest(DataBase.MAX_IV, new Rate(31));
        _data.addConstNumTest(DataBase.DEF_MAX_ATT, new Rate(4));
        _data.addConstNumTest(DataBase.DEF_PKEQ, new Rate(6));
        _data.addConstNumTest(DataBase.ARGENT, new Rate(3000));
        _data.addConstNumTest(DataBase.NIVEAU_PK_ECLOSION, new Rate(1));
        _data.addConstNumTest(DataBase.NIVEAU_PK_MAX, new Rate(100));
        _data.addConstNumTest(DataBase.EVO_BONHEUR, new Rate(110));
        _data.addConstNumTest(DataBase.MAX_BONHEUR, new Rate(170));
        _data.addConstNumTest(DataBase.GAIN_BONHEUR_NIV, new Rate(2));
        _data.addConstNumTest(DataBase.PAS_NECES_INCREMENT_BONHEUR, new Rate(10));
        _data.addConstNumTest(DataBase.PP_MAX, new Rate(80));
        _data.addConstNumTest(DataBase.VALEUR_DEF_STATIS, new Rate(0));
        _data.addConstNumTest(DataBase.MAX_BOOST, new Rate(6));
        _data.addConstNumTest(DataBase.MIN_BOOST, new Rate(-6));
        _data.addConstNumTest(DataBase.MIN_HP, new Rate(1));
        _data.addConstNumTest(DataBase.BONUS_BOOST, new Rate("3/2"));
        _data.addConstNumTest(DataBase.MAX_STEPS, new Rate("1024"));
        _data.addConstNumTest(DataBase.MAX_STEPS_SAME_EVO_BASE, new Rate("256"));
        _data.addConstNumTest(DataBase.DEF_BASE_MOVE, new Rate("0"));
        initDefaultConsts(POKE_BALL,
                "1",
                "1",
                MbOperationNode.DIV_FCT+"(2*"+MbOperationNode.CARAC_GAUCHE_OUVERT+"("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_BOOST+",0),"+MbOperationNode.MAX+"(2-"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_BOOST+",1))+"+MbOperationNode.DIV_FCT+"((2+"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_BOOST+")*"+MbOperationNode.CARAC_DROITE_FERME+"("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_BOOST+",0),2)",
                MbOperationNode.PUIS+"(2,"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_BOOST+"-4)",
                "1",
                ECLAIR_3,
                MessagesDataBaseConstants.DEFAULT_EGG_GROUP_VALUE, _data);
    }

    public static void initDefaultConsts(String _ballDef, String _rateCatching,
                                         String _rateFleeing, String _rateBoost,
                                         String _rateBoostCriticalHit, String _damageFormula,
                                         String _defMove, String _defaultEggGoup, DataBase _db) {
        _db.setBallDef(_ballDef);
        _db.setRateCatching(_rateCatching);
        _db.setRateFleeing(_rateFleeing);
        _db.setRateBoost(_rateBoost);
        _db.setRateBoostCriticalHit(_rateBoostCriticalHit);
        _db.setDamageFormula(_damageFormula);
        _db.setDefMove(_defMove);
        _db.setDefaultEggGroup(_defaultEggGoup);
        _db.setDefCategory("_");
        _db.defValues();
    }
    public static void initExpPoints(DataBase _data) {
        _data.getExpGrowth().addEntry(ExpType.E,"2*"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU);
        _data.getExpGrowth().addEntry(ExpType.L,"5/4*"+MbOperationNode.PUIS+"("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU+",3)");
        _data.getExpGrowth().addEntry(ExpType.M,MbOperationNode.PUIS+"("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU+",3)");
        _data.getExpGrowth().addEntry(ExpType.P,MbOperationNode.PUIS+"("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU+",2)");
        _data.getExpGrowth().addEntry(ExpType.F,VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU);
        _data.getExpGrowth().addEntry(ExpType.R,"4/5*"+MbOperationNode.PUIS+"("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU+",3)");
        _data.getRates().addEntry(DifficultyWinPointsFight.TRES_FACILE, "4");
        _data.getRates().addEntry(DifficultyWinPointsFight.FACILE, "2");
        _data.getRates().addEntry(DifficultyWinPointsFight.DIFFICILE, "1");
        _data.getRates().addEntry(DifficultyWinPointsFight.TRES_DIFFICILE, "1/2");
    }

    public static void initTranslations(DataBase _data) {
        IdMap<SelectedBoolean,String> bools_;
        bools_ = new IdMap<SelectedBoolean,String>();
        bools_.addEntry(SelectedBoolean.YES, SelectedBoolean.YES.getBoolName());
        bools_.addEntry(SelectedBoolean.NO, SelectedBoolean.NO.getBoolName());
        bools_.addEntry(SelectedBoolean.YES_AND_NO, SelectedBoolean.YES_AND_NO.getBoolName());
        _data.getTranslatedBooleans().addEntry(LANGUAGE, bools_);
        IdMap<DifficultyWinPointsFight,String> diffsWin_;
        diffsWin_ = new IdMap<DifficultyWinPointsFight,String>();
        diffsWin_.addEntry(DifficultyWinPointsFight.TRES_FACILE, DifficultyWinPointsFight.TRES_FACILE.getWinName());
        diffsWin_.addEntry(DifficultyWinPointsFight.FACILE, DifficultyWinPointsFight.FACILE.getWinName());
        diffsWin_.addEntry(DifficultyWinPointsFight.DIFFICILE, DifficultyWinPointsFight.DIFFICILE.getWinName());
        diffsWin_.addEntry(DifficultyWinPointsFight.TRES_DIFFICILE, DifficultyWinPointsFight.TRES_DIFFICILE.getWinName());
        _data.getTranslatedDiffWinPts().addEntry(LANGUAGE, diffsWin_);
        IdMap<DifficultyModelLaw,String> diffsLaw_;
        diffsLaw_ = new IdMap<DifficultyModelLaw,String>();
        diffsLaw_.addEntry(DifficultyModelLaw.CONSTANT_MIN, DifficultyModelLaw.CONSTANT_MIN.getModelName());
        diffsLaw_.addEntry(DifficultyModelLaw.CROISSANT, DifficultyModelLaw.CROISSANT.getModelName());
        diffsLaw_.addEntry(DifficultyModelLaw.UNIFORME, DifficultyModelLaw.UNIFORME.getModelName());
        diffsLaw_.addEntry(DifficultyModelLaw.DECROISSANT, DifficultyModelLaw.DECROISSANT.getModelName());
        diffsLaw_.addEntry(DifficultyModelLaw.CONSTANT_MAX, DifficultyModelLaw.CONSTANT_MAX.getModelName());
        _data.getTranslatedDiffModelLaw().addEntry(LANGUAGE, diffsLaw_);
        IdMap<EnvironmentType,String> envs_;
        envs_ = new IdMap<EnvironmentType,String>();
        envs_.addEntry(EnvironmentType.NOTHING, EnvironmentType.NOTHING.getEnvName());
        envs_.addEntry(EnvironmentType.ROAD, EnvironmentType.ROAD.getEnvName());
        envs_.addEntry(EnvironmentType.DESERT, EnvironmentType.DESERT.getEnvName());
        envs_.addEntry(EnvironmentType.ROCK, EnvironmentType.ROCK.getEnvName());
        envs_.addEntry(EnvironmentType.BUILDING, EnvironmentType.BUILDING.getEnvName());
        envs_.addEntry(EnvironmentType.WATER, EnvironmentType.WATER.getEnvName());
        envs_.addEntry(EnvironmentType.GRASS, EnvironmentType.GRASS.getEnvName());
        envs_.addEntry(EnvironmentType.SNOW, EnvironmentType.SNOW.getEnvName());
        envs_.addEntry(EnvironmentType.ICE, EnvironmentType.ICE.getEnvName());
        _data.getTranslatedEnvironment().addEntry(LANGUAGE, envs_);
        IdMap<Gender,String> genders_;
        genders_ = new IdMap<Gender,String>();
        genders_.addEntry(Gender.FEMALE, Gender.FEMALE.getGenderName());
        genders_.addEntry(Gender.NO_GENDER, Gender.NO_GENDER.getGenderName());
        genders_.addEntry(Gender.MALE, Gender.MALE.getGenderName());
        _data.getTranslatedGenders().addEntry(LANGUAGE, genders_);
        IdMap<Statistic,String> statistics_;
        statistics_ = new IdMap<Statistic,String>();
        statistics_.addEntry(Statistic.ATTACK, Statistic.ATTACK.getStatName());
        statistics_.addEntry(Statistic.DEFENSE, Statistic.DEFENSE.getStatName());
        statistics_.addEntry(Statistic.SPECIAL_ATTACK, Statistic.SPECIAL_ATTACK.getStatName());
        statistics_.addEntry(Statistic.SPECIAL_DEFENSE, Statistic.SPECIAL_DEFENSE.getStatName());
        statistics_.addEntry(Statistic.SPEED, Statistic.SPEED.getStatName());
        statistics_.addEntry(Statistic.CRITICAL_HIT, Statistic.CRITICAL_HIT.getStatName());
        statistics_.addEntry(Statistic.ACCURACY, Statistic.ACCURACY.getStatName());
        statistics_.addEntry(Statistic.EVASINESS, Statistic.EVASINESS.getStatName());
        statistics_.addEntry(Statistic.PV_RESTANTS, Statistic.PV_RESTANTS.getStatName());
        statistics_.addEntry(Statistic.HP, Statistic.HP.getStatName());
        _data.getTranslatedStatistics().addEntry(LANGUAGE, statistics_);
        IdMap<TargetChoice,String> targets_;
        targets_ = new IdMap<TargetChoice,String>();
        targets_.addEntry(TargetChoice.ADJ_ADV, TargetChoice.ADJ_ADV.getTargetName());
        targets_.addEntry(TargetChoice.ADJ_MULT, TargetChoice.ADJ_MULT.getTargetName());
        targets_.addEntry(TargetChoice.ADJ_UNIQ, TargetChoice.ADJ_UNIQ.getTargetName());
        targets_.addEntry(TargetChoice.ALLIE, TargetChoice.ALLIE.getTargetName());
        targets_.addEntry(TargetChoice.ALLIES, TargetChoice.ALLIES.getTargetName());
        targets_.addEntry(TargetChoice.ANY_FOE, TargetChoice.ANY_FOE.getTargetName());
        targets_.addEntry(TargetChoice.AUTRE_UNIQ, TargetChoice.AUTRE_UNIQ.getTargetName());
        targets_.addEntry(TargetChoice.GLOBALE, TargetChoice.GLOBALE.getTargetName());
        targets_.addEntry(TargetChoice.LANCEUR, TargetChoice.LANCEUR.getTargetName());
        targets_.addEntry(TargetChoice.PSEUDO_GLOBALE, TargetChoice.PSEUDO_GLOBALE.getTargetName());
        targets_.addEntry(TargetChoice.TOUS_ADV, TargetChoice.TOUS_ADV.getTargetName());
        targets_.addEntry(TargetChoice.UNIQUE_IMPORTE, TargetChoice.UNIQUE_IMPORTE.getTargetName());
        targets_.addEntry(TargetChoice.NOTHING, TargetChoice.NOTHING.getTargetName());
        _data.getTranslatedTargets().addEntry(LANGUAGE, targets_);
        StringMap<String> words_ = DataBase.basicTranslation(_data.getPokedex().getKeys());
        _data.getTranslatedPokemon().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(_data.getMoves().getKeys());
        _data.getTranslatedMoves().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(_data.getItems().getKeys());
        _data.getTranslatedItems().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(_data.getAbilities().getKeys());
        _data.getTranslatedAbilities().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(_data.getStatus().getKeys());
        _data.getTranslatedStatus().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslationItemsType(_data);
        _data.getTranslatedClassesDescriptions().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(_data.getTypes());
        _data.getTranslatedTypes().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(_data.getAllCategories());
        _data.getTranslatedCategories().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(EvolvedMathFactory.getFunctions());
        _data.getTranslatedFctMath().addEntry(LANGUAGE, words_);
        StringMap<String> litteral_ = new StringMap<String>();
        litteral_.addEntry(MessagesDataBaseConstants.DEF_NIVEAU, StringUtil.concat("level",TAB,"l",TAB,"The level of the Pokemon"));
        litteral_.addEntry(MessagesDataBaseConstants.DEF_BOOST, StringUtil.concat("boost",TAB,"b",TAB,"The boost of the Pokemon"));
        _data.getLitterals().addEntry(LANGUAGE,litteral_);
    }

    public static void initRandomLaws(DataBase _data) {
        MonteCarloNumber monteCarloNumber_;
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("49/50"),new LgInt("14"));
        monteCarloNumber_.addQuickEvent(new Rate("23/25"),new LgInt("8"));
        monteCarloNumber_.addQuickEvent(new Rate("89/100"),new LgInt("5"));
        monteCarloNumber_.addQuickEvent(new Rate("19/20"),new LgInt("11"));
        monteCarloNumber_.addQuickEvent(new Rate("99/100"),new LgInt("15"));
        monteCarloNumber_.addQuickEvent(new Rate("24/25"),new LgInt("12"));
        monteCarloNumber_.addQuickEvent(new Rate("43/50"),new LgInt("2"));
        monteCarloNumber_.addQuickEvent(new Rate("91/100"),new LgInt("7"));
        monteCarloNumber_.addQuickEvent(new Rate("47/50"),new LgInt("10"));
        monteCarloNumber_.addQuickEvent(new Rate("93/100"),new LgInt("9"));
        monteCarloNumber_.addQuickEvent(new Rate("97/100"),new LgInt("13"));
        monteCarloNumber_.addQuickEvent(new Rate("9/10"),new LgInt("6"));
        monteCarloNumber_.addQuickEvent(new Rate("22/25"),new LgInt("4"));
        monteCarloNumber_.addQuickEvent(new Rate("87/100"),new LgInt("3"));
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("16"));
        monteCarloNumber_.addQuickEvent(new Rate("17/20"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.CROISSANT,new LawNumber(monteCarloNumber_,4));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("99/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("9/10"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("47/50"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("49/50"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("93/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("24/25"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("19/20"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("22/25"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("97/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("43/50"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("89/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("91/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("17/20"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("23/25"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("87/100"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.UNIFORME,new LawNumber(monteCarloNumber_,3));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("24/25"),new LgInt("5"));
        monteCarloNumber_.addQuickEvent(new Rate("19/20"),new LgInt("6"));
        monteCarloNumber_.addQuickEvent(new Rate("9/10"),new LgInt("11"));
        monteCarloNumber_.addQuickEvent(new Rate("99/100"),new LgInt("2"));
        monteCarloNumber_.addQuickEvent(new Rate("43/50"),new LgInt("15"));
        monteCarloNumber_.addQuickEvent(new Rate("87/100"),new LgInt("14"));
        monteCarloNumber_.addQuickEvent(new Rate("23/25"),new LgInt("9"));
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("93/100"),new LgInt("8"));
        monteCarloNumber_.addQuickEvent(new Rate("91/100"),new LgInt("10"));
        monteCarloNumber_.addQuickEvent(new Rate("47/50"),new LgInt("7"));
        monteCarloNumber_.addQuickEvent(new Rate("22/25"),new LgInt("13"));
        monteCarloNumber_.addQuickEvent(new Rate("97/100"),new LgInt("4"));
        monteCarloNumber_.addQuickEvent(new Rate("17/20"),new LgInt("16"));
        monteCarloNumber_.addQuickEvent(new Rate("49/50"),new LgInt("3"));
        monteCarloNumber_.addQuickEvent(new Rate("89/100"),new LgInt("12"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.DECROISSANT,new LawNumber(monteCarloNumber_,2));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("17/20"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.CONSTANT_MIN,new LawNumber(monteCarloNumber_,1));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.CONSTANT_MAX,new LawNumber(monteCarloNumber_,5));
    }

    public static void initMiniMap(DataBase _data) {
        _data.getMiniMap().addEntry(MINI, inst(new int[][]{new int[]{118}}));
        _data.getMiniMap().addEntry(MINI1, inst(new int[][]{new int[]{218}}));
        _data.getMiniMap().addEntry(MINI2, inst(new int[][]{new int[]{112}}));
        _data.getMiniMap().addEntry(MINI3, inst(new int[][]{new int[]{200}}));
        _data.getMiniMap().addEntry(MINI4, inst(new int[][]{new int[]{128}}));
        _data.getMiniMap().addEntry(MINI5, inst(new int[][]{new int[]{211}}));
        _data.getMiniMap().addEntry(MINI6, inst(new int[][]{new int[]{221}}));
        DataMap map_ = _data.getMap();
        TileMiniMap tile_;
        map_.setMiniMap(new MiniMapCoordsList());
        tile_ = new TileMiniMap();
        tile_.setFile(MINI);
        tile_.setPlace(0);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,0), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI1);
        tile_.setPlace(1);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,1), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI2);
        tile_.setPlace(2);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,2), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI3);
        tile_.setPlace(3);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,3), tile_);
        map_.setUnlockedCity(MINI5);
    }

    public static void initBlockFirstCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addCity("__");
        City city_ = (City) map_.getPlaces().last();
        Block block_;
        block_ = newBlock(2, 2,EnvironmentType.NOTHING,NOTHING,-1);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD,-1);
        city_.getLevel().getBlocks().addEntry(newPoint(2,0), block_);
        block_ = newBlock(2, 2,EnvironmentType.NOTHING,NOTHING,-1);
        city_.getLevel().getBlocks().addEntry(newPoint(4,0), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD,-1);
        city_.getLevel().getBlocks().addEntry(newPoint(0,2), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD,-1);
        city_.getLevel().getBlocks().addEntry(newPoint(2,2), block_);
        block_ = newBlock(2, 2,EnvironmentType.WATER,WATER,-1);
        city_.getLevel().getBlocks().addEntry(newPoint(4,2), block_);
        block_ = newBlock(2, 2,EnvironmentType.WATER,WATER,-1);
        city_.getLevel().getBlocks().addEntry(newPoint(0,4), block_);
        block_ = newBlock(2, 2,EnvironmentType.WATER,WATER,-1);
        city_.getLevel().getBlocks().addEntry(newPoint(2,4), block_);
        block_ = newBlock(2, 2,EnvironmentType.WATER,WATER,-1);
        city_.getLevel().getBlocks().addEntry(newPoint(4,4), block_);
    }

    public static void initBuildingsFirstCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_ = (City) map_.getPlaces().last();
        Gym gym_;
        gym_ = new Gym();
        gym_.setImageFileName(LINK);
        gym_.setLevel(new LevelIndoorGym());
        gym_.setExitCity(newPoint(4,8));
        gym_.getLevel().setBlocks(new PointsBlock());
        gym_.getLevel().getBlocks().addEntry(newPoint(0,0), newBlock(9, 9,EnvironmentType.BUILDING,BUILDING,-1));
        gym_.getIndoor().setGymTrainers(new PointsGymTrainer());
        city_.setBuildings(new PointsBuilding());
        city_.getBuildings().addEntry(newPoint(5, 1), gym_);
        PokemonCenter pkCenter_;
        pkCenter_ = new PokemonCenter();
        pkCenter_.setImageFileName(LINK);
        pkCenter_.setLevel(new LevelIndoorPokemonCenter());
        pkCenter_.setExitCity(newPoint(4,8));
        pkCenter_.getLevel().setBlocks(new PointsBlock());
        pkCenter_.getLevel().getBlocks().addEntry(newPoint(0,0), newBlock(9, 9,EnvironmentType.BUILDING,BUILDING,-1));
        pkCenter_.getIndoor().setStorageCoords(newPoint(4, 0));
        pkCenter_.getIndoor().setGerants(new PointsPerson());
        city_.getBuildings().addEntry(newPoint(1, 1), pkCenter_);
    }

    public static void initTrainersFirstCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_ = (City) map_.getPlaces().last();
        Gym gym_;
        GymTrainer gymTrainer_;
        GymLeader gymLeader_;
        gym_ = (Gym) city_.getBuildings().getVal(newPoint(5, 1));
        CustList<PkTrainer> team_ = new CustList<PkTrainer>();
        team_.add(toPkTrainer(new NameLevel(PIKACHU, 1), new StringList(ECLAIR)));
        gymTrainer_ = nvGymTrainer( 200, 1, team_);
        gym_.getIndoor().getGymTrainers().addEntry(newPoint(1, 7), gymTrainer_);
        CustList<PkTrainer> teamTwo_ = new CustList<PkTrainer>();
        teamTwo_.add(toPkTrainer(new NameLevel(PIKACHU, 1), new StringList(ECLAIR)));
        gymTrainer_ = nvGymTrainer( 200, 1, teamTwo_);
        gym_.getIndoor().getGymTrainers().addEntry(newPoint(7, 7), gymTrainer_);
        gym_.getIndoor().setGymLeaderCoords(newPoint(4, 1));
        CustList<PkTrainer> teamThree_ = new CustList<PkTrainer>();
        teamThree_.add(toPkTrainer(new NameLevel(PIKACHU, 1), new StringList(ECLAIR)));
        gymLeader_ = nvGymLeader( 500, 1, teamThree_);
        gymLeader_.setName("__");
        gym_.getIndoor().setGymLeader(gymLeader_);
    }

    public static void initPokemonCenterFirstCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_ = (City) map_.getPlaces().last();
        PokemonCenter pk_;
        pk_ = (PokemonCenter) city_.getBuildings().getVal(newPoint(1, 1));
        pk_.getIndoor().getGerants().addEntry(newPoint(0, 4), newGerantPokemon(GeranceType.HEAL));
        Seller seller_;
        seller_ = new Seller();
        seller_.setItems(new StringList(POKE_BALL));
        seller_.setTm(Ints.newList());
        seller_.setSell(SellType.ITEM);
        seller_.setImageMiniFileName(GERANT);
        pk_.getIndoor().getGerants().addEntry(newPoint(8, 4), seller_);
        seller_ = new Seller();
        seller_.setItems(new StringList());
        seller_.setTm(Ints.newList());
        seller_.setSell(SellType.MOVE);
        seller_.setImageMiniFileName(GERANT);
        pk_.getIndoor().getGerants().addEntry(newPoint(8, 6), seller_);
    }
    public static void initLeague(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addLeague(LINK, newCoords(0, 0, 2, 0));
        League league_ =(League) map_.getPlaces().last();
        league_.getRooms().last().setFileName(LINK);
        league_.setName("__");
    }

    public static void initBlockLeague(DataBase _data) {
        DataMap map_ = _data.getMap();
        League road_ = (League) map_.getPlaces().last();
        LevelLeague level_;
        Block block_;
        level_ = (LevelLeague) road_.getLevelsMap().getVal(0);
        block_ = newBlock(9, 9,EnvironmentType.BUILDING,BUILDING,-1);
        level_.getBlocks().addEntry(newPoint(0,0), block_);
        road_.setBegin(newPoint(4,8));
        level_ = (LevelLeague) road_.getLevelsMap().getVal(0);
        level_.setAccessPoint(newPoint(4, 0));
    }

    public static void initLeagueTrainers(DataBase _data) {
        DataMap map_ = _data.getMap();
        League league_ = (League) map_.getPlaces().last();
        league_.getRooms().get(0).setTrainerCoords(newPoint(4, 4));
        league_.getRooms().get(0).setTrainer(trainerLeagueOne());
        league_.getRooms().get(0).getTrainer().setName("__");
        map_.getAccessCondition().addEntry(newCoords(0, 0, 2, 0), Condition.newList(newCoords(0, 0, 5, 1, 4, 1)));
    }

    public static void initBlockFirstRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addRoad();
        Road road_ = (Road) map_.getPlaces().last();
        road_.setName("___");
        Block block_;
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(2,0), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(4,0), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(0,2), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(2,2), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(4,2), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(0,4), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(2,4), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(4,4), block_);
    }

    public static void initFirstRoadAreas(DataBase _data) {
        DataMap map_ = _data.getMap();
        Road road_ = (Road) map_.getPlaces().last();
        AreaApparition area_;
        WildPk wild_;
        area_ = new AreaApparition();
        area_.setAvgNbSteps(1);
        area_.setMultFight(1);
        wild_ = new WildPk();
        wild_.setName(PIKACHU);
        wild_.setLevel( 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.setWildPokemon(new CustList<WildPk>());
        area_.setWildPokemonFishing(new CustList<WildPk>());
        area_.getWildPokemon().add(wild_);
        road_.getLevelRoad().getWildPokemonAreas().add(area_);
    }

    private static TrainerLeague trainerLeagueOne() {
        CustList<PkTrainer> team_ = new CustList<PkTrainer>();
        team_.add(toPkTrainer(new NameLevel(PIKACHU, 35), new StringList(ECLAIR)));
        return nvTrainerLeague( 2000, 1, team_);
    }
    private static GymLeader nvGymLeader(int _reward, int _mult, CustList<PkTrainer> _team) {
        GymLeader gymLeader_ = new GymLeader();
        gymLeader_.setTeam(_team);
        gymLeader_.setReward(_reward);
        gymLeader_.setMultiplicityFight(_mult);
        gymLeader_.setName(NULL_REF);
        gymLeader_.setTm( 2);
        gymLeader_.setImageMiniFileName(TRAINER);
        gymLeader_.setImageMaxiFileName(TRAINER);
        return gymLeader_;
    }

    private static GymTrainer nvGymTrainer(int _reward, int _mult, CustList<PkTrainer> _team) {
        GymTrainer gymTrainer_ = new GymTrainer();
        gymTrainer_.setTeam(_team);
        gymTrainer_.setReward(_reward);
        gymTrainer_.setMultiplicityFight(_mult);
        gymTrainer_.setImageMiniFileName(TRAINER);
        gymTrainer_.setImageMaxiFileName(TRAINER);
        return gymTrainer_;
    }

    private static TrainerLeague nvTrainerLeague(int _reward, int _mult, CustList<PkTrainer> _team) {
        TrainerLeague trainerLeague_ = new TrainerLeague();
        trainerLeague_.setTeam(_team);
        trainerLeague_.setReward(_reward);
        trainerLeague_.setMultiplicityFight(_mult);
        trainerLeague_.setImageMiniFileName(TRAINER);
        trainerLeague_.setImageMaxiFileName(TRAINER);
        trainerLeague_.setName(NULL_REF);
        return trainerLeague_;
    }

    private static PkTrainer toPkTrainer(NameLevel _nameLevel, StringList _moves) {
        PkTrainer pk_ = new PkTrainer();
        pk_.setName(_nameLevel.getName());
        pk_.setLevel(_nameLevel.getLevel());
        pk_.setAbility(PARATONNERRE);
        pk_.setItem(NULL_REF);
        pk_.setMoves(_moves);
        return pk_;
    }
    private static PokemonTeam nvTeam(int _reward, CustList<PkTrainer> _team) {
        PokemonTeam teamReward_ = new PokemonTeam();
        teamReward_.setTeam(_team);
        teamReward_.setReward(_reward);
        return teamReward_;
    }

    private static TrainerMultiFights newTrainer(
            CustList<PokemonTeam> _teams, int _mult) {
        TrainerMultiFights trainer_ = new TrainerMultiFights();
        trainer_.setTeamsRewards(_teams);
        trainer_.setMultiplicityFight(_mult);
        trainer_.setImageMiniFileName(TRAINER);
        trainer_.setImageMaxiFileName(TRAINER);
        return trainer_;
    }

    private static GerantPokemon newGerantPokemon(GeranceType _gerance) {
        GerantPokemon gerant_ = new GerantPokemon();
        gerant_.setGerance(_gerance);
        gerant_.setImageMiniFileName(GERANT);
        return gerant_;
    }

    private static DealerItem newDealerObject(StringList _obj, Ints _tm) {
        DealerItem dealer_ = new DealerItem();
        dealer_.setItems(new StringList(_obj));
        dealer_.setTechnicalMoves(_tm);
        dealer_.setImageMiniFileName(PERSON);
        return dealer_;
    }

    public static Block newBlock(int _h, int _w, EnvironmentType _type, String _tileFileName, int _index) {
        //black
        Block block_;
        block_ = new Block();
        block_.setHeight(_h);
        block_.setWidth(_w);
        block_.setType(_type);
        block_.setTileFileName(_tileFileName);
        block_.setIndexApparition( _index);
        return block_;
    }

    public static Coords newCoords(int _pl, int _xi, int _yi, int _x, int _y) {
        Coords c_ = new Coords();
        c_.setNumberPlace(_pl);
        c_.affectInside(newPoint(_xi,_yi));
        c_.setLevel(new LevelPoint());
        c_.getLevel().setLevelIndex(0);
        c_.getLevel().setPoint(newPoint(_x,_y));
        return c_;
    }
    public static Coords newCoords(int _place, int _level, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace(_place);
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex(_level);
        begin_.getLevel().setPoint(newPoint(_x,_y));
        return begin_;
    }

    public static Coords newCoords(int _place, int _level, int _xi, int _yi, int _x, int _y) {
        Coords begin_ = new Coords();
        begin_.setNumberPlace(_place);
        begin_.affectInside(newPoint(_xi, _yi));
        begin_.setLevel(new LevelPoint());
        begin_.getLevel().setLevelIndex(_level);
        begin_.getLevel().setPoint(newPoint(_x,_y));
        return begin_;
    }

    public static LevelPoint newLevelPoint(int _level, int _x,int _y) {
        LevelPoint l_ = new LevelPoint();
        l_.setLevelIndex(_level);
        l_.setPoint(newPoint(_x, _y));
        return l_;
    }

    public MiniMapCoords mini(int _x, int _y) {
        return new MiniMapCoords(_x, _y);
    }

    public static Point newPoint(int _x,int _y) {
        return new Point(_x,_y);
    }
}
