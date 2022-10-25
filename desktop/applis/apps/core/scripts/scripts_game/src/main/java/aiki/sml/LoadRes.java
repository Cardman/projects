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
import code.util.consts.Constants;
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
import code.sml.DocumentBuilder;
public final class LoadRes{

    private static final String SEPARATOR_KEY_HEROS = ";";

    private static final char TAB_CHAR = '\t';
    private static final String TAB = "\t";

    private static final char RETURN_LINE_CHAR = '\n';

    private LoadRes(){}

    public static StringMap<String> dis() {
//        StringMap<String> displayLanguages_ = new StringMap<String>();
//        for (String s: _lgs) {
//            displayLanguages_.put(s, Constants.getDisplayLanguages(s));
//        }
        return Constants.getDisplayLanguages();
    }

    public static void loadResources(AbstractGenerator _gene, FacadeGame _f, PerCent _p, LoadFlag _l, LoadingData _loading) {
        DataBase data_ = _loading.loadResource(_gene, _p, _l);
        LoadRes.postLoad(_f, data_);
    }
    public static DataBase loadResource(AbstractGenerator _gene, PerCent _p, LoadFlag _l, StringList _languages, StringMap<String> _displayLanguages, SexListInt _sexList) {
        DataBase data_ = new DataBase(_gene);
        data_.setLanguages(_languages);
        data_.setDisplayLanguages(_displayLanguages);
        _l.set(true);
        loadResources(data_, _p,_sexList);
        return data_;
    }

    public static void postLoad(FacadeGame _f, DataBase _data) {
        if (_f.getData() != null) {
            _data.setMessages(_f.getData());
        }
        _data.setLanguage(_f.getLanguage());
        _f.setData(_data);
        _f.setLoadedData(true);
        _f.setZipName(DataBase.EMPTY_STRING);
    }

    public static void loadResources(DataBase _d, PerCent _perCentLoading, SexListInt _sexList) {
        int delta_ = (100 - _perCentLoading.getPercent()) / 6;

        _d.initializeMembers();
		for (EntryCust<String,String> c: Cst.cs().entryList()) {
            short cle_ = (short) NumberUtil.parseInt(c.getKey());
            _d.getHm().addEntry(cle_, c.getValue());
        }
        for (EntryCust<String,String> c: Cst.ct().entryList()) {
            StringList infos_ = StringUtil.splitChars(c.getValue(), TAB_CHAR);
            short cle_ = (short) NumberUtil.parseInt(c.getKey());
            _d.getTm().addEntry(cle_, infos_.first().trim());
            LgInt price_ = new LgInt(infos_.last().trim());
            _d.getTmPrice().addEntry(cle_, price_);
        }
        _d.setFrontHeros(new ImageHeroKeys());
        StringMap<String> heFr_ = HeFront.im();
        for (EntryCust<String,String> e:heFr_.entryList()){
            StringList keyStrings_ = StringUtil.splitStrings(e.getKey(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = getEnvByName(keyStrings_.first());
            Sex sex_ = getSexByName(keyStrings_.last(),_sexList);
            _d.getFrontHeros().addEntry(new ImageHeroKey(env_, sex_),
                    BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
        _d.setBackHeros(new ImageHeroKeys());
        StringMap<String> heBk_ = HeBack.im();
        for (EntryCust<String,String> e:heBk_.entryList()) {
            StringList keyStrings_ = StringUtil.splitStrings(e.getKey(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = getEnvByName(keyStrings_
                    .first());
            Sex sex_ = getSexByName(keyStrings_.last(),_sexList);
            _d.getBackHeros().addEntry(new ImageHeroKey(env_, sex_),
                    BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
        _d.setOverWorldHeros(new ImageHeroKeys());
        StringMap<String> heMi_ = HeMini.im();
        for (EntryCust<String,String> e:heMi_.entryList()) {
            StringList keyStrings_ = StringUtil.splitStrings(e.getKey(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = getEnvByName(keyStrings_
                    .first());
            Direction dir_ = Direction.getDirectionByName(keyStrings_
                    .get(IndexConstants.SECOND_INDEX));
            Sex sex_ = getSexByName(keyStrings_.last(),_sexList);
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
//        StringList lines_;
        //= StringUtil.splitChars(cts_.getVal(CONST_NUM),
          //      RETURN_LINE_CHAR);
        _d.getConstNum().addAllEntries(Cst.cn());
//        for (EntryCust<String, Rate> l : Cst.cn().entryList()) {
//            if (l.isEmpty()) {
//                continue;
//            }
//            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
//            _d.getConstNum().addEntry(infos_.first(), new Rate(infos_.last()));
//        }

//        lines_ = StringUtil.splitChars(cts_.getVal(CONST_NOT_NUM),
//                RETURN_LINE_CHAR);
        for (EntryCust<String, String> l : Cst.cnn().entryList()) {
//            if (l.isEmpty()) {
//                continue;
//            }
//            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
            String key_ = l.getKey();
            String value_ = l.getValue();
            _d.initValue(key_, value_);

        }
        _d.setTableTypes(new TypesDuos());
//        StringList linesTableTypes_ = StringUtil.splitChars(cts_.getVal(TABLE_TYPES),
//                RETURN_LINE_CHAR);
//        String head_ = linesTableTypes_.first();
//        StringList typesOff_ = StringUtil.splitChars(head_, TAB_CHAR);
//        typesOff_.removeString(DataBase.EMPTY_STRING);
//        StringList typesDef_ = new StringList();
//        for (String l : linesTableTypes_.leftMinusOne(linesTableTypes_.size())) {
//            typesDef_.add(StringUtil.getFirstToken(l, TAB_CHAR));
//        }
//        typesDef_.removeString(DataBase.EMPTY_STRING);
        _d.getTableTypes().getList().addAllElts(Cst.eff().getList());
//        for (String pkType_ : typesDef_) {
//
//            String l_ = getElements(linesTableTypes_, pkType_).first();
//            StringList infos_ = StringUtil.splitChars(l_, TAB_CHAR);
//            infos_.removeString(pkType_);
//            int i_ = 0;
//            for (String damageType_ : typesOff_) {
//                TypesDuo t_ = new TypesDuo(damageType_, pkType_);
//                Rate r_;
//                if (Rate.isValid(infos_.get(i_))) {
//                    r_ = new Rate(infos_.get(i_));
//                } else {
//                    r_ = DataBase.defRateProduct();
//                }
//                _d.getTableTypes().addEntry(t_, r_);
//
//                i_++;
//            }
//        }
//        _d.initTypesByTable();
        _d.setTypes(Cst.tp());
        _d.setLawsDamageRate(new IdMap<DifficultyModelLaw, LawNumber>());
//        StringMap<String> cts_ = Cst.tr();
//        StringList laws_ = StringUtil.splitChars(cts_.getVal(LOIS_RANDOM),
//                RETURN_LINE_CHAR);
//        for (String l : laws_) {
//            if (l.isEmpty()) {
//                continue;
//            }
//            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
//            MonteCarloNumber law_ = new MonteCarloNumber();
//
//            for (String evt_ : StringUtil.splitStrings(infos_.get(1),
//                    DataBase.SEPARATOR_RAND)) {
//                StringList infosLoc_ = StringUtil.splitStrings(evt_,
//                        DataBase.SEPARATOR_RAND_EVENTS);
//                boolean defaultLaw_ = false;
//                if (!Rate.isValid(infosLoc_.first())) {
//                    defaultLaw_ = true;
//                } else if (!LgInt.isValid(infosLoc_.get(1))) {
//                    defaultLaw_ = true;
//                }
//                if (defaultLaw_) {
//                    law_ = new MonteCarloNumber();
//
//                    law_.addQuickEvent(new Rate(1), DataBase.defElementaryEvent());
//                    break;
//                }
//
//                law_.addQuickEvent(new Rate(infosLoc_.first()),
//                        new LgInt(infosLoc_.get(1)));
//
//            }
//
////            if (!law_.checkEvents()) {
////                _d.setError(true);
////                return;
////            }
//            _d.getLawsDamageRate().addEntry(
//                    getModelByName(infos_.first()),
//                    new LawNumber(law_, (short) NumberUtil.parseInt(infos_.last())));
//        }
        _d.getLawsDamageRate().addAllEntries(Cst.lg());
        _d.setExpGrowth(new IdMap<ExpType, String>());
//        StringList courbes_ = StringUtil.splitChars(cts_.getVal(COURBE_PTS_EXP),
//                RETURN_LINE_CHAR);
//        for (String l : courbes_) {
//            if (l.isEmpty()) {
//                continue;
//            }
//            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
//            _d.getExpGrowth().addEntry(getExpTypeByName(infos_.first()),
//                    infos_.get(1));
//        }
        _d.getExpGrowth().addAllEntries(Cst.ex());
        _d.setRates(new IdMap<DifficultyWinPointsFight, String>());
//        StringList rates_ = StringUtil.splitChars(cts_.getVal(RATE_WON_POINTS),
//                RETURN_LINE_CHAR);
//        for (String l : rates_) {
//            if (l.isEmpty()) {
//                continue;
//            }
//            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
//            _d.getRates().addEntry(getDiffWonPtsByName(infos_
//                    .first()), infos_.get(1));
//        }
        _d.getRates().addAllEntries(Cst.di());
        _d.setTypesColors(new StringMap<String>());
//        StringList rates_ = StringUtil.splitChars(cts_.getVal(DataBase.TYPES_COLOR_CODE + DataBase.IMG_FILES_RES_EXT_TXT), RETURN_LINE_CHAR);
//        for (String l : rates_) {
//            if (l.isEmpty()) {
//                continue;
//            }
//            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
//            String colorStr_ = infos_.get(1);
//            _d.getTypesColors().addEntry(infos_.first(), colorStr_);
//        }
        _d.getTypesColors().addAllEntries(Cst.tc());
        _d.setEndGameImage(BaseSixtyFourUtil.getImageByString(ImEndGame.im()));
        _d.initTranslations();
        StringMap<String> trs_ = Trs.tr();
        for (String l : _d.getLanguages()) {
            IdMap<Gender, String> genders_ = trGenders(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_GENDERS));
            _d.getTranslatedGenders().addEntry(l, genders_);
            IdMap<SelectedBoolean, String> booleans_ = trBooleans(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_BOOLEANS));
            _d.getTranslatedBooleans().addEntry(l, booleans_);
            IdMap<DifficultyWinPointsFight, String> diffWinPts_ = trDiffWinPts(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_DIFF_WIN_PTS));
            _d.getTranslatedDiffWinPts().addEntry(l, diffWinPts_);
            IdMap<DifficultyModelLaw, String> diffLaw_ = trDiffLaw(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_DIFF_MODEL_LAW));
            _d.getTranslatedDiffModelLaw().addEntry(l, diffLaw_);
            IdMap<EnvironmentType, String> environments_ = trEnv(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_ENVIRONMENTS));
            _d.getTranslatedEnvironment().addEntry(l, environments_);
            IdMap<Statistic, String> statistics_ = trStat(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_STATISTICS));
            _d.getTranslatedStatistics().addEntry(l, statistics_);
            IdMap<TargetChoice, String> targets_ = trTargets(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_TARGETS));
            _d.getTranslatedTargets().addEntry(l, targets_);
            StringMap<String> categories_ = tr(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_CATEGORIES));
            _d.getTranslatedCategories().addEntry(l, categories_);
            StringMap<String> types_ = tr(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_TYPES));
            _d.getTranslatedTypes().addEntry(l, types_);
            StringMap<String> pokemon_ = tr(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_POKEMON));
            _d.getTranslatedPokemon().addEntry(l, pokemon_);
            StringMap<String> moves_ = tr(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_MOVES));
            _d.getTranslatedMoves().addEntry(l, moves_);
            StringMap<String> items_ = tr(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_ITEMS));
            _d.getTranslatedItems().addEntry(l, items_);
            StringMap<String> abilities_ = tr(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_ABILITIES));
            _d.getTranslatedAbilities().addEntry(l, abilities_);
            StringMap<String> status_ = tr(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_STATUS));
            _d.getTranslatedStatus().addEntry(l, status_);
            StringMap<String> fctsMath_ = tr(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_MATH));
            _d.getTranslatedFctMath().addEntry(l, fctsMath_);
            StringMap<String> descrClasses_ = tr(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_CLASSES));
            _d.getTranslatedClassesDescriptions().addEntry(l, descrClasses_);
            StringMap<String> litteral_ = trLitt(trs_.getVal(l + DataBase.SEPARATOR_FILES + DataBase.TRANSLATION_LITTERAL));
            _d.getLitterals().addEntry(l, litteral_);
        }
        _perCentLoading.addPercent(delta_);
        feedImgs(AnStatis.im(), _d.getAnimStatis());
        feedImgs(AnStatus.im(), _d.getAnimStatus());
        _d.setAnimAbsorb(BaseSixtyFourUtil.getImageByString(AnAbs.im().firstValue()));
        for (EntryCust<String,PokemonData> e: PkInit.pk().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
        _d.calculateAvgPound();
        for (EntryCust<String,MoveData> e: MvInit.mv().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
        patch(_d);
        for (EntryCust<String,Item> e: ItInit.it().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
        for (EntryCust<String,AbilityData> e: AbInit.ab().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
        for (EntryCust<String,Status> e: StInit.st().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
        _d.completeVariables();
        _d.sortEndRound();
        _perCentLoading.addPercent(delta_);
        _d.completeMoveTutors();
//        for (PokemonData pk_ : _d.getPokedex().values()) {
//            for (short hm_ : pk_.getHiddenMoves()) {
//                String move_ = _d.getHm().getVal(hm_);
//                pk_.getMoveTutors().add(move_);
//            }
//            for (short hm_ : pk_.getTechnicalMoves()) {
//                String move_ = _d.getTm().getVal(hm_);
//                pk_.getMoveTutors().add(move_);
//            }
//            for (LevelMove l : pk_.getLevMoves()) {
//                pk_.getMoveTutors().add(l.getMove());
//            }
//            pk_.getMoveTutors().removeDuplicates();
//        }
        _d.setMaxiPkBack(new StringMap<int[][]>());
        feedImgs(Bk.im(), _d.getMaxiPkBack());
        _d.setMaxiPkFront(new StringMap<int[][]>());
        feedImgs(Ft.im(), _d.getMaxiPkFront());
        _d.setMiniPk(new StringMap<int[][]>());
        feedImgs(Mn.im(), _d.getMiniPk());
        _d.setMiniItems(new StringMap<int[][]>());
        feedImgs(ItIm.im(), _d.getMiniItems());
        _d.setTypesImages(new StringMap<int[][]>());
        feedImgs(TypeImg.im(), _d.getTypesImages());
        _perCentLoading.addPercent(delta_);
        _d.getMap().initializeLinks();
        _d.getMap().initInteractiveElements();
        _d.getMap().initializeTree();
        _d.getMap().initializeAccessibility();
        _d.setTrainers(new StringMap<int[][]>());
        _d.setPeople(new StringMap<int[][]>());
        _d.setImages(new StringMap<int[][]>());
        _d.setImagesTiles(new StringMap<ScreenCoordssInt>());
        _d.setLinks(new StringMap<int[][]>());
        _d.setMiniMap(new StringMap<int[][]>());
        feedImgs(TrainerImg.im(), _d.getTrainers());
        feedImgs(PeopleImg.im(), _d.getPeople());
        feedImgs(ImgMap.im(), _d.getImages());
        feedImgs(LinkImg.im(), _d.getLinks());
        feedImgs(MiniMapImg.im(), _d.getLinks());
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

        _d.getFamilies().addAllEntries(PkInit.fs());
//        _d.initFamilies();
        _d.boundsPk();
//        for (int[][] i : _d.getMaxiPkBack().values()) {
//            if (i.length == 0) {
//                _d.setError(true);
//                return;
//            }
//            if (i[0].length > _d.getMaxWidthPk()) {
//                _d.setMaxWidthPk(i[0].length);
//            }
//            if (i.length > _d.getMaxHeightPk()) {
//                _d.setMaxHeightPk(i.length);
//            }
//
//        }
//        for (int[][] i : _d.getMaxiPkFront().values()) {
//            if (i.length == 0) {
//                _d.setError(true);
//                return;
//            }
//            if (i[0].length > _d.getMaxWidthPk()) {
//                _d.setMaxWidthPk(i[0].length);
//            }
//            if (i.length > _d.getMaxHeightPk()) {
//                _d.setMaxHeightPk(i.length);
//            }
//
//        }
        _d.setupPseudoImages();
        _d.getConstNum().addEntry(DataBase.DEF_BASE_MOVE,new Rate("1"));
        _perCentLoading.setPercent(100);
    }

    private static void patch(DataBase _d) {
        for (EntryCust<String,MoveData> e: _d.getMoves().entryList()) {
            if (e.getValue() instanceof DamagingMoveData) {
                for (Effect f: e.getValue().getEffects()) {
                    if (f instanceof EffectDamage) {
                        EffectDamage dam_ = (EffectDamage) f;
                        dam_.patch();
                    }
                }
            }
        }
    }

    private static StringMap<String> trLitt(String _str) {
        StringMap<String> litteral_ = new StringMap<String>();
        for (String l2_ : StringUtil.splitChars(_str,
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
        return litteral_;
    }

    private static IdMap<TargetChoice, String> trTargets(String _str) {
        IdMap<TargetChoice, String> targets_ = new IdMap<TargetChoice, String>();
        for (String l2_ : StringUtil.splitChars(_str,
                RETURN_LINE_CHAR)) {
            if (l2_.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
            targets_.addEntry(
                    getTargetChoiceByName(infos_.first()),
                    DocumentBuilder.transformSpecialChars(infos_.last()));
        }
        return targets_;
    }

    private static IdMap<Statistic, String> trStat(String _str) {
        IdMap<Statistic, String> statistics_ = new IdMap<Statistic, String>();
        for (String l2_ : StringUtil.splitChars(_str,
                RETURN_LINE_CHAR)) {
            if (l2_.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
            statistics_.addEntry(Statistic.getStatisticByName(infos_.first()),
                    DocumentBuilder.transformSpecialChars(infos_.last()));
        }
        return statistics_;
    }

    private static IdMap<EnvironmentType, String> trEnv(String _str) {
        IdMap<EnvironmentType, String> environments_ = new IdMap<EnvironmentType, String>();
        for (String l2_ : StringUtil.splitChars(_str,
                RETURN_LINE_CHAR)) {
            if (l2_.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
            environments_.addEntry(getEnvByName(infos_.first()),
                    DocumentBuilder.transformSpecialChars(infos_.last()));
        }
        return environments_;
    }

    private static IdMap<DifficultyModelLaw, String> trDiffLaw(String _str) {
        IdMap<DifficultyModelLaw, String> diffLaw_ = new IdMap<DifficultyModelLaw, String>();
        for (String l2_ : StringUtil.splitChars(_str,
                RETURN_LINE_CHAR)) {
            if (l2_.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
            diffLaw_.addEntry(getModelByName(infos_.first()),
                    DocumentBuilder.transformSpecialChars(infos_.last()));
        }
        return diffLaw_;
    }

    private static IdMap<DifficultyWinPointsFight, String> trDiffWinPts(String _str) {
        IdMap<DifficultyWinPointsFight, String> diffWinPts_ = new IdMap<DifficultyWinPointsFight, String>();
        for (String l2_ : StringUtil.splitChars(_str,
                RETURN_LINE_CHAR)) {
            if (l2_.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
            diffWinPts_.addEntry(
                    getDiffWonPtsByName(infos_.first()), DocumentBuilder
                    .transformSpecialChars(infos_.last()));
        }
        return diffWinPts_;
    }

    private static IdMap<SelectedBoolean, String> trBooleans(String _str) {
        IdMap<SelectedBoolean, String> booleans_ = new IdMap<SelectedBoolean, String>();
        for (String l2_ : StringUtil.splitChars(_str,
                RETURN_LINE_CHAR)) {
            if (l2_.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
            booleans_.addEntry(getBoolByName(infos_.first()),
                    DocumentBuilder.transformSpecialChars(infos_.last()));
        }
        return booleans_;
    }

    private static IdMap<Gender, String> trGenders(String _str) {
        IdMap<Gender, String> genders_ = new IdMap<Gender, String>();
        for (String l2_ : StringUtil.splitChars(_str,
                RETURN_LINE_CHAR)) {
            if (l2_.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
            genders_.addEntry(getGenderByName(infos_.first()),
                    DocumentBuilder.transformSpecialChars(infos_.last()));
        }
        return genders_;
    }

    private static void feedImgs(StringMap<String> _imgs, StringMap<int[][]> _dest) {
        for (EntryCust<String,String> e: _imgs.entryList()) {
            _dest.addEntry(e.getKey(), BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
    }

    private static StringMap<String> tr(String _str) {
        StringMap<String> out_ = new StringMap<String>();
        for (String l2_ : StringUtil.splitChars(_str,
                RETURN_LINE_CHAR)) {
            if (l2_.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
            out_.addEntry(infos_.first(),
                    DocumentBuilder.transformSpecialChars(infos_.last()));
        }
        return out_;
    }

    public static SelectedBoolean getBoolByName(String _env) {
        return SelectedBoolean.getBoolByName(_env);
    }
    public static DifficultyModelLaw getModelByName(String _env) {
        return DifficultyModelLaw.getModelByName(_env);
    }
    public static DifficultyWinPointsFight getDiffWonPtsByName(String _env) {
        return DifficultyWinPointsFight.getDiffWonPtsByName(_env);
    }

    public static EnvironmentType getEnvByName(String _env) {
        return EnvironmentType.getEnvByName(_env);
    }
	public static Sex getSexByName(String _env, SexListInt _sexList) {
        return Sex.getSexByName(_env,_sexList);
    }
	public static Gender getGenderByName(String _env) {
        return Gender.getGenderByName(_env);
    }
	public static TargetChoice getTargetChoiceByName(String _env) {
        return TargetChoice.getTargetChoiceByName(_env);
    }
}