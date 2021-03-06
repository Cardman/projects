package aiki.sml;
import aiki.db.*;
import aiki.facade.*;
import aiki.fight.abilities.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.pokemon.*;
import aiki.fight.moves.enums.*;
import aiki.fight.status.*;
import aiki.fight.util.*;
import code.images.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.util.*;
import aiki.facade.enums.*;
import code.util.core.*;
import aiki.sml.init.*;
import aiki.sml.imgs.*;
import aiki.sml.trs.*;
import aiki.map.levels.enums.*;
import aiki.map.enums.*;
import aiki.game.params.enums.*;
import aiki.game.player.enums.*;
import aiki.util.*;
import aiki.fight.pokemon.enums.*;
import aiki.map.pokemon.enums.*;
import aiki.map.util.*;
import aiki.map.tree.util.*;
import code.maths.litteralcom.MathExpUtil;
import code.sml.DocumentBuilder;
public final class LoadRes{
    public static final String EMPTY_STRING = "";
    public static final String SEPARATOR_RAND = ";";
    public static final String SEPARATOR_RAND_EVENTS = " ";
    public static final String DEF_MOVE = "DEF_MOVE";
    public static final String RATE_CATCHING = "RATE_CATCHING";
    public static final String RATE_FLEEING = "RATE_FLEEING";
    public static final String RATE_BOOST = "RATE_BOOST";
    public static final String RATE_BOOST_CRITICAL_HIT = "RATE_BOOST_CRITICAL_HIT";
    public static final String DAMAGE_FORMULA = "DAMAGE_FORMULA";
    public static final String DEFAULT_EGG_GROUP = "DEFAULT_EGG_GROUP";
    public static final String IMG_FILES_RES_EXT_TXT = ".txt";
    public static final String FILES_RES_EXT = ".xml";
    /**
     * The custom beans can be modified but they must have a common base package
     * Avoid to recompile classes in standard packages like java, javax, and
     * even projects core, gui ...
     */

    public static final String IMAGES_FOLDER = "images";
    public static final String LINKS_FOLDER = "links";
    public static final String PEOPLE_FOLDER = "people";
    public static final String TRAINERS_FOLDER = "trainers";
    public static final String HERO_FOLDER = "heros";
    public static final String MINI_MAP_FOLDER = "mini_map";
    public static final String HERO_FRONT = "heros_front.txt";
    public static final String HERO_BACK = "heros_back.txt";
    public static final String HERO_MINI = "heros_mini.txt";
    public static final String FRONT_IMAGES_FOLDER = "front";
    public static final String BACK_IMAGES_FOLDER = "back";
    public static final String MINI_IMAGES_FOLDER = "mini";

    public static final String OBJECTS_IMAGES_FOLDER = "items_images";
    public static final String TYPES_IMAGES_FOLDER = "types_images";
    public static final String IMAGE_TM_HM_FILES = "hm_tm";
    public static final String IMAGE_STORAGE_FILES = "storage";
    public static final String TYPES_COLOR_CODE = "types_color";
    public static final String SEPARATOR_FILES = "/";
    public static final String END_GAME_IMAGE = "end_game";

    public static final String ANIM_STATIS = "anim_statis";

    public static final String ANIM_STATUS = "anim_status";

    public static final String ANIM_ABSORB = "anim_absorb/absorb.txt";

    private static final String POKEDEX_FOLDER = "pokedex";
    private static final String MOVES_FOLDER = "moves";
    private static final String ABILITIES_FOLDER = "abilities";
    private static final String STATUS_FOLDER = "status";

    private static final String ITEMS_FOLDER = "items";
    private static final String SEPARATOR_KEY_HEROS = ";";

    private static final char TAB_CHAR = '\t';
    private static final String TAB = "\t";

    private static final char RETURN_LINE_CHAR = '\n';
    private static final String CS = "CS";
    private static final String CT = "CT";
    private static final String CT_CS_FILE = "ct_cs.txt";
    private static final String CONST_NUM = "const_num.txt";
    private static final String CONST_NOT_NUM = "constantes_non_num.txt";
    private static final String TABLE_TYPES = "table_types.txt";
    private static final String LOIS_RANDOM = "lois_random.txt";
    private static final String COURBE_PTS_EXP = "courbe_pts_exp.txt";
    private static final String RATE_WON_POINTS = "rate_won_points.txt";
    private static final String COMBOS = "combos.xml";
    private static final String MAP_FILE = "map.xml";
    private static final String TRANSLATION_FOLDER = "translations";
    private static final String TRANSLATION_CATEGORIES = "categories.txt";
    private static final String TRANSLATION_GENDERS = "genders.txt";
    private static final String TRANSLATION_ENVIRONMENTS = "environments.txt";
    private static final String TRANSLATION_BOOLEANS = "booleans.txt";
    private static final String TRANSLATION_DIFF_WIN_PTS = "winpts.txt";
    private static final String TRANSLATION_DIFF_MODEL_LAW = "modellaw.txt";
    private static final String TRANSLATION_STATISTICS = "statistics.txt";
    private static final String TRANSLATION_TARGETS = "targets.txt";
    private static final String TRANSLATION_TYPES = "types.txt";
    private static final String TRANSLATION_POKEMON = "pokemon.txt";
    private static final String TRANSLATION_MOVES = "moves.txt";
    private static final String TRANSLATION_ITEMS = "items.txt";
    private static final String TRANSLATION_ABILITIES = "abilities.txt";
    private static final String TRANSLATION_STATUS = "status.txt";
    private static final String TRANSLATION_MATH = "math.txt";
    private static final String TRANSLATION_CLASSES = "classes.txt";
    private static final String TRANSLATION_LITTERAL = "litteral.txt";

    private static final String BALL_DEF = "BALL_DEF";
	private LoadRes(){}
	public static void loadResources(AbstractGenerator _gene, FacadeGame _f, PerCent _p,LoadFlag _l) {
        DataBase data_ = new DataBase(_gene);
        data_.setLanguages(_f.getLanguages());
        data_.setDisplayLanguages(_f.getDisplayLanguages());
        _l.set(true);
        data_.setLanguage(_f.getLanguage());
        loadResources(data_,_p, _f.getLanguage());
        if (_f.getData() != null) {
            data_.setMessages(_f.getData());
        }
        _f.setData(data_);
        _f.setLoadedData(true);
        _f.setZipName(DataBase.EMPTY_STRING);
    }
    public static void loadResources(DataBase _d,PerCent _perCentLoading, String _lg) {
        int delta_ = (100 - _perCentLoading.getPercent()) / 6;

        _d.initializeMembers();
		StringMap<String> cts_ = Cst.tr();
        StringList tmHm_ = StringUtil.splitChars(cts_.getVal(CT_CS_FILE),
                RETURN_LINE_CHAR);
        for (String l : tmHm_) {
            if (l.startsWith(CT)) {
                StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
                short cle_ = (short) NumberUtil.parseInt(infos_.first().substring(2));
                _d.getTm().addEntry(cle_, infos_.get(1).trim());
                LgInt price_;
                if (LgInt.isValid(infos_.get(2).trim())) {
                    price_ = new LgInt(infos_.get(2).trim());
                } else {
                    price_ = new LgInt(1000);
                }
                _d.getTmPrice().addEntry(cle_, price_);

            }
            if (l.startsWith(CS)) {
                StringList infos_ = StringUtil.splitChars(l.trim(), TAB_CHAR);
                short cle_ = (short) NumberUtil.parseInt(infos_.first().substring(2));
                _d.getHm().addEntry(cle_, infos_.get(1));
            }
        }
        _d.setFrontHeros(new ObjectMap<ImageHeroKey, int[][]>());
        StringMap<String> heFr_ = HeFront.im();
        for (EntryCust<String,String> e:heFr_.entryList()){
            StringList keyStrings_ = StringUtil.splitStrings(e.getKey(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = getEnvByName(keyStrings_.first());
            Sex sex_ = getSexByName(keyStrings_.last());
            _d.getFrontHeros().addEntry(new ImageHeroKey(env_, sex_),
                    BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
        _d.setBackHeros(new ObjectMap<ImageHeroKey, int[][]>());
        StringMap<String> heBk_ = HeBack.im();
        for (EntryCust<String,String> e:heBk_.entryList()) {
            StringList keyStrings_ = StringUtil.splitStrings(e.getKey(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = getEnvByName(keyStrings_
                    .first());
            Sex sex_ = getSexByName(keyStrings_.last());
            _d.getBackHeros().addEntry(new ImageHeroKey(env_, sex_),
                    BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
        _d.setOverWorldHeros(new ObjectMap<ImageHeroKey, int[][]>());
        StringMap<String> heMi_ = HeMini.im();
        for (EntryCust<String,String> e:heMi_.entryList()) {
            StringList keyStrings_ = StringUtil.splitStrings(e.getKey(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = getEnvByName(keyStrings_
                    .first());
            Direction dir_ = Direction.getDirectionByName(keyStrings_
                    .get(IndexConstants.SECOND_INDEX));
            Sex sex_ = getSexByName(keyStrings_.last());
            _d.getOverWorldHeros().addEntry(new ImageHeroKey(env_, dir_, sex_),
                    BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
        _d.setImageTmHm(BaseSixtyFourUtil.getImageByString(ImHmTm.im()));
        _d.setStorage(BaseSixtyFourUtil.getImageByString(ImStorage.im()));
        _d.setCombos(CoInit.co());
        _d.completeMembersCombos();
        _d.setMap(Dm.map());
        _perCentLoading.addPercent(delta_);
        _d.setConstNum(new StringMap<Rate>());
        StringList lines_ = StringUtil.splitChars(cts_.getVal(CONST_NUM),
                RETURN_LINE_CHAR);
        for (String l : lines_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
            _d.getConstNum().addEntry(infos_.first(), new Rate(infos_.last()));
        }

        lines_ = StringUtil.splitChars(cts_.getVal(CONST_NOT_NUM),
                RETURN_LINE_CHAR);
        for (String l : lines_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
            if (StringUtil.quickEq(infos_.first(), DEF_MOVE)) {
                _d.setDefMove(infos_.last());
            } else if (StringUtil.quickEq(infos_.first(), RATE_BOOST)) {
                _d.setRateBoost(infos_.last());
            } else if (StringUtil.quickEq(infos_.first(),
                    RATE_BOOST_CRITICAL_HIT)) {
                _d.setRateBoostCriticalHit(infos_.last());
            } else if (StringUtil.quickEq(infos_.first(), RATE_FLEEING)) {
                _d.setRateFleeing(infos_.last());
            } else if (StringUtil.quickEq(infos_.first(), RATE_CATCHING)) {
                _d.setRateCatching(infos_.last());
            } else if (StringUtil.quickEq(infos_.first(), BALL_DEF)) {
                _d.setBallDef(infos_.last());
            } else if (StringUtil.quickEq(infos_.first(), DEFAULT_EGG_GROUP)) {
                _d.setDefaultEggGroup(infos_.last());
            } else if (StringUtil.quickEq(infos_.first(), DAMAGE_FORMULA)) {
                _d.setDamageFormula(infos_.last());
            }

        }
        _d.setTableTypes(new TypesDuos());
        StringList linesTableTypes_ = StringUtil.splitChars(cts_.getVal(TABLE_TYPES),
                RETURN_LINE_CHAR);
        String head_ = linesTableTypes_.first();
        StringList typesOff_ = StringUtil.splitChars(head_, TAB_CHAR);
        typesOff_.removeString(EMPTY_STRING);
        StringList typesDef_ = new StringList();
        for (String l : linesTableTypes_.leftMinusOne(linesTableTypes_.size())) {
            typesDef_.add(StringUtil.getFirstToken(l, TAB_CHAR));
        }
        typesDef_.removeString(EMPTY_STRING);
        for (String pkType_ : typesDef_) {

            String l_ = getElements(linesTableTypes_, pkType_).first();
            StringList infos_ = StringUtil.splitChars(l_, TAB_CHAR);
            infos_.removeString(pkType_);
            int i_ = 0;
            for (String damageType_ : typesOff_) {
                TypesDuo t_ = new TypesDuo(damageType_, pkType_);
                Rate r_;
                if (Rate.isValid(infos_.get(i_))) {
                    r_ = new Rate(infos_.get(i_));
                } else {
                    r_ = DataBase.defRateProduct();
                }
                _d.getTableTypes().addEntry(t_, r_);

                i_++;
            }
        }
        _d.initTypesByTable();
        _d.setLawsDamageRate(new EnumMap<DifficultyModelLaw, LawNumber>());
        StringList laws_ = StringUtil.splitChars(cts_.getVal(LOIS_RANDOM),
                RETURN_LINE_CHAR);
        for (String l : laws_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
            MonteCarloNumber law_ = new MonteCarloNumber();

            for (String evt_ : StringUtil.splitStrings(infos_.get(1),
                    SEPARATOR_RAND)) {
                StringList infosLoc_ = StringUtil.splitStrings(evt_,
                        SEPARATOR_RAND_EVENTS);
                boolean defaultLaw_ = false;
                if (!Rate.isValid(infosLoc_.first())) {
                    defaultLaw_ = true;
                } else if (!LgInt.isValid(infosLoc_.get(1))) {
                    defaultLaw_ = true;
                }
                if (defaultLaw_) {
                    law_ = new MonteCarloNumber();

                    law_.addQuickEvent(new Rate(1), DataBase.defElementaryEvent());
                    break;
                }

                law_.addQuickEvent(new Rate(infosLoc_.first()),
                        new LgInt(infosLoc_.get(1)));

            }

            if (!law_.checkEvents()) {
                _d.setError(true);
                return;
            }
            _d.getLawsDamageRate().addEntry(
                    getModelByName(infos_.first()),
                    new LawNumber(law_, (short) NumberUtil.parseInt(infos_.last())));
        }
        _d.setExpGrowth(new EnumMap<ExpType, String>());
        StringList courbes_ = StringUtil.splitChars(cts_.getVal(COURBE_PTS_EXP),
                RETURN_LINE_CHAR);
        for (String l : courbes_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
            _d.getExpGrowth().addEntry(getExpTypeByName(infos_.first()),
                    infos_.get(1));
        }
        _d.setRates(new EnumMap<DifficultyWinPointsFight, String>());
        StringList rates_ = StringUtil.splitChars(cts_.getVal(RATE_WON_POINTS),
                RETURN_LINE_CHAR);
        for (String l : rates_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
            _d.getRates().addEntry(getDiffWonPtsByName(infos_
                    .first()), infos_.get(1));
        }
        _d.setTypesColors(new StringMap<String>());
        rates_ = StringUtil.splitChars(cts_.getVal(TYPES_COLOR_CODE+IMG_FILES_RES_EXT_TXT), RETURN_LINE_CHAR);
        for (String l : rates_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
            String colorStr_ = infos_.get(1);
            _d.getTypesColors().addEntry(infos_.first(), colorStr_);
        }
        _d.setEndGameImage(BaseSixtyFourUtil.getImageByString(ImEndGame.im()));
        _d.initTranslations();
		StringMap<String> trs_ = Trs.tr();
		for (String l : _d.getLanguages()) {
			EnumMap<Gender, String> genders_ = new EnumMap<Gender, String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_GENDERS),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                genders_.addEntry(getGenderByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedGenders().addEntry(l, genders_);
			EnumMap<SelectedBoolean, String> booleans_ = new EnumMap<SelectedBoolean, String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_BOOLEANS),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                booleans_.addEntry(getBoolByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedBooleans().addEntry(l, booleans_);
            EnumMap<DifficultyWinPointsFight, String> diffWinPts_ = new EnumMap<DifficultyWinPointsFight, String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_DIFF_WIN_PTS),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                diffWinPts_.addEntry(
                        getDiffWonPtsByName(infos_.first()), DocumentBuilder
                        .transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedDiffWinPts().addEntry(l, diffWinPts_);
            EnumMap<DifficultyModelLaw, String> diffLaw_ = new EnumMap<DifficultyModelLaw, String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_DIFF_MODEL_LAW),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                diffLaw_.addEntry(getModelByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedDiffModelLaw().addEntry(l, diffLaw_);
            EnumMap<EnvironmentType, String> environments_ = new EnumMap<EnvironmentType, String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_ENVIRONMENTS),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                environments_.addEntry(getEnvByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedEnvironment().addEntry(l, environments_);
            EnumMap<Statistic, String> statistics_ = new EnumMap<Statistic, String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_STATISTICS),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                statistics_.addEntry(Statistic.getStatisticByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedStatistics().addEntry(l, statistics_);
            EnumMap<TargetChoice, String> targets_ = new EnumMap<TargetChoice, String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_TARGETS),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                targets_.addEntry(
                        getTargetChoiceByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedTargets().addEntry(l, targets_);
            StringMap<String> categories_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_CATEGORIES),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                categories_.addEntry(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedCategories().addEntry(l, categories_);
            StringMap<String> types_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_TYPES),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                types_.addEntry(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedTypes().addEntry(l, types_);
            StringMap<String> pokemon_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_POKEMON),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                pokemon_.addEntry(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedPokemon().addEntry(l, pokemon_);
            StringMap<String> moves_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_MOVES),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                moves_.addEntry(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedMoves().addEntry(l, moves_);
            StringMap<String> items_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_ITEMS),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                items_.addEntry(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedItems().addEntry(l, items_);
            StringMap<String> abilities_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_ABILITIES),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                abilities_.addEntry(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedAbilities().addEntry(l, abilities_);
            StringMap<String> status_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_STATUS),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                status_.addEntry(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedStatus().addEntry(l, status_);
            StringMap<String> fctsMath_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_MATH),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                fctsMath_.addEntry(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedFctMath().addEntry(l, fctsMath_);
            StringMap<String> descrClasses_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_CLASSES),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                descrClasses_.addEntry(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedClassesDescriptions().addEntry(l, descrClasses_);
            StringMap<String> litteral_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_LITTERAL),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                litteral_
                        .addEntry(infos_.first(), DocumentBuilder
                                .transformSpecialChars(StringUtil
                                        .join(infos_.leftMinusOne(
                                                infos_.size()), TAB)));
            }
            _d.getLitterals().addEntry(l, litteral_);
		}
        _perCentLoading.addPercent(delta_);
		for(EntryCust<String,String> e: AnStatis.im().entryList()){
			_d.getAnimStatis().addEntry(e.getKey(), BaseSixtyFourUtil
                    .getImageByString(e.getValue()));
		}
		for(EntryCust<String,String> e: AnStatus.im().entryList()){
			_d.getAnimStatus().addEntry(e.getKey(), BaseSixtyFourUtil.getImageByString(e.getValue()));
		}
        _d.setAnimAbsorb(BaseSixtyFourUtil.getImageByString(AnAbs.im().firstValue()));
        StringList filesNames_;
        filesNames_ = new StringList();
        for (EntryCust<String,PokemonData> e: PkInit.pk().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
        _d.calculateAvgPound();
        filesNames_.clear();
        for (EntryCust<String,MoveData> e: MvInit.mv().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
        for (EntryCust<String,MoveData> e: _d.getMoves().entryList()) {
            if (e.getValue() instanceof DamagingMoveData) {
                for (Effect f: e.getValue().getEffects()) {
                    if (f instanceof EffectDamage) {
                        EffectDamage dam_ = (EffectDamage) f;
                        if (dam_.getChLaw().events().isEmpty()) {
                            dam_.getChLaw().addQuickEvent(Rate.one(),LgInt.one());
                        }
                        if (dam_.getHitsLaw().events().isEmpty()) {
                            dam_.getHitsLaw().addQuickEvent(Rate.one(),LgInt.one());
                        }
                    }
                }
            }
        }
        filesNames_.clear();
        for (EntryCust<String,Item> e: ItInit.it().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
        filesNames_.clear();
        for (EntryCust<String,AbilityData> e: AbInit.ab().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
        filesNames_.clear();
        for (EntryCust<String,Status> e: StInit.st().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
        _d.completeVariables();
        filesNames_.clear();
        _d.sortEndRound();
        _perCentLoading.addPercent(delta_);
        for (PokemonData pk_ : _d.getPokedex().values()) {
            for (short hm_ : pk_.getHiddenMoves()) {
                String move_ = _d.getHm().getVal(hm_);
                pk_.getMoveTutors().add(move_);
            }
            for (short hm_ : pk_.getTechnicalMoves()) {
                String move_ = _d.getTm().getVal(hm_);
                pk_.getMoveTutors().add(move_);
            }
            for (LevelMove l : pk_.getLevMoves()) {
                pk_.getMoveTutors().add(l.getMove());
            }
            pk_.getMoveTutors().removeDuplicates();
        }
        _d.setMaxiPkBack(new StringMap<int[][]>());
		for (EntryCust<String,String> e: Bk.im().entryList()) {
            _d.getMaxiPkBack().addEntry(e.getKey(), BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
        filesNames_.clear();
        _d.setMaxiPkFront(new StringMap<int[][]>());
		for (EntryCust<String,String> e: Ft.im().entryList()) {
            _d.getMaxiPkFront().addEntry(e.getKey(), BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
        filesNames_.clear();
        _d.setMiniPk(new StringMap<int[][]>());
		for (EntryCust<String,String> e: Mn.im().entryList()) {
            _d.getMiniPk().addEntry(e.getKey(), BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
        filesNames_.clear();
        _d.setMiniItems(new StringMap<int[][]>());
		for (EntryCust<String,String> e: ItIm.im().entryList()) {
            _d.getMiniItems().addEntry(e.getKey(), BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
        filesNames_.clear();
        _d.setTypesImages(new StringMap<int[][]>());
		for (EntryCust<String,String> e: TypeImg.im().entryList()) {
            _d.getTypesImages().addEntry(e.getKey(), BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
        _perCentLoading.addPercent(delta_);
        filesNames_.clear();
        _d.getMap().initializeLinks();
        _d.getMap().initInteractiveElements();
        _d.getMap().initializeTree();
        _d.getMap().initializeAccessibility();
        _d.setTrainers(new StringMap<int[][]>());
        _d.setPeople(new StringMap<int[][]>());
        _d.setImages(new StringMap<int[][]>());
        _d.setImagesTiles(new StringMap<ObjectMap<ScreenCoords, int[][]>>());
        _d.setLinks(new StringMap<int[][]>());
        _d.setMiniMap(new StringMap<int[][]>());
		for (EntryCust<String,String> e: TrainerImg.im().entryList()) {
            _d.getTrainers().addEntry(e.getKey(), BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
		for (EntryCust<String,String> e: PeopleImg.im().entryList()) {
            _d.getPeople().addEntry(e.getKey(), BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
		for (EntryCust<String,String> e: ImgMap.im().entryList()) {
            _d.getImages().addEntry(e.getKey(), BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
		for (EntryCust<String,String> e: LinkImg.im().entryList()) {
            _d.getLinks().addEntry(e.getKey(), BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
		for (EntryCust<String,String> e: MiniMapImg.im().entryList()) {
            _d.getLinks().addEntry(e.getKey(), BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
		StringMap<String> imMiMap_ = MiniMapImg.im();
        for (TileMiniMap t : _d.getMap().getMiniMap().values()) {
            String f_ = t.getFile();
            _d.getMiniMap().addEntry(f_, BaseSixtyFourUtil.getImageByString(imMiMap_.getVal(f_)));
        }
        _d.getMiniMap().addEntry(_d.getMap().getUnlockedCity(), BaseSixtyFourUtil
                .getImageByString(imMiMap_.getVal(_d.getMap().getUnlockedCity())));
        _perCentLoading.addPercent(delta_);
        _d.initializeWildPokemon();
        _perCentLoading.addPercent(delta_);

        _d.initFamilies();
        for (int[][] i : _d.getMaxiPkBack().values()) {
            if (i.length == 0) {
                _d.setError(true);
                return;
            }
            if (i[0].length > _d.getMaxWidthPk()) {
                _d.setMaxWidthPk(i[0].length);
            }
            if (i.length > _d.getMaxHeightPk()) {
                _d.setMaxHeightPk(i.length);
            }

        }
        for (int[][] i : _d.getMaxiPkFront().values()) {
            if (i.length == 0) {
                _d.setError(true);
                return;
            }
            if (i[0].length > _d.getMaxWidthPk()) {
                _d.setMaxWidthPk(i[0].length);
            }
            if (i.length > _d.getMaxHeightPk()) {
                _d.setMaxHeightPk(i.length);
            }

        }
        int side_ = _d.getMap().getSideLength();
        for (EntryCust<String, int[][]> i : _d.getImages().entryList()) {
            int[][] img_ = i.getValue();
            String name_ = i.getKey();
            Dims d_ = new Dims();
            d_.setWidth((short) (img_[0].length / side_));
            d_.setHeight((short) (img_.length / side_));
            ObjectMap<ScreenCoords, int[][]> tiles_;
            tiles_ = new ObjectMap<ScreenCoords, int[][]>();
            for (short x = 0; x < d_.getWidth(); x++) {
                for (short y = 0; y < d_.getHeight(); y++) {
                    ScreenCoords sc_ = new ScreenCoords(x, y);
                    tiles_.addEntry(sc_, BaseSixtyFourUtil.clipSixtyFour(img_, x * side_, y
                            * side_, side_, side_));
                }
            }
            _d.getImagesTiles().addEntry(name_, tiles_);
        }
        _perCentLoading.setPercent(100);
    }

    public static SelectedBoolean getBoolByName(String _env) {
        for (SelectedBoolean e : SelectedBoolean.values()) {
            if (StringUtil.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return SelectedBoolean.YES_AND_NO;
    }
    public static DifficultyModelLaw getModelByName(String _env) {
        for (DifficultyModelLaw e: DifficultyModelLaw.values()) {
            if (StringUtil.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return DifficultyModelLaw.UNIFORME;
    }
    public static DifficultyWinPointsFight getDiffWonPtsByName(String _env) {
        for (DifficultyWinPointsFight e: DifficultyWinPointsFight.values()) {
            if (StringUtil.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return DifficultyWinPointsFight.TRES_FACILE;
    }


    public static ExpType getExpTypeByName(String _env) {
        for (ExpType e: ExpType.values()) {
            if (StringUtil.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return ExpType.M;
    }
    public static EnvironmentType getEnvByName(String _env) {
        for (EnvironmentType e: EnvironmentType.values()) {
            if (StringUtil.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return EnvironmentType.NOTHING;
    }
	
    private static StringList getElements(StringList _list, String _prefixWord) {
        StringList elts_ = new StringList();
        for (String l : _list) {
            if (!l.startsWith(_prefixWord)) {
                continue;
            }
            if (StringUtil.quickEq(l, _prefixWord)) {
                elts_.add(l);
                continue;
            }
            char next_ = l.charAt(_prefixWord.length());
            if (!MathExpUtil.isWordChar(next_)) {
                elts_.add(l);
            }
        }
        return elts_;
    }
	public static Sex getSexByName(String _env) {
        Sex[] values_ = Sex.values();
        for (Sex e: values_) {
            if (StringUtil.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return values_[0];
    }
	public static Gender getGenderByName(String _env) {
        for (Gender e: Gender.values()) {
            if (StringUtil.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return Gender.NO_GENDER;
    }
	public static TargetChoice getTargetChoiceByName(String _env) {
        for (TargetChoice e: TargetChoice.values()) {
            if (StringUtil.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return TargetChoice.NOTHING;
    }
}