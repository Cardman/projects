package aiki.sml;
import aiki.db.*;
import aiki.facade.*;
import aiki.facade.enums.*;
import aiki.fight.*;
import aiki.fight.abilities.*;
import aiki.fight.effects.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.*;
import aiki.fight.moves.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.pokemon.evolution.*;
import aiki.fight.status.*;
import aiki.fight.status.effects.*;
import aiki.fight.util.*;
import aiki.game.*;
import aiki.game.fight.*;
import aiki.game.fight.actions.*;
import aiki.game.fight.enums.*;
import aiki.game.fight.util.*;
import aiki.game.params.*;
import aiki.game.params.enums.*;
import aiki.game.player.*;
import aiki.game.player.enums.*;
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
import aiki.util.*;
import code.images.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.sml.maths.*;
import code.sml.*;
import code.sml.core.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;

public final class DocumentReaderAikiCoreUtil {
    public static final String MAIN_TAG = "4";
    public static final String LOADING_GAME = "0";
    public static final String GAME = "1";

    private DocumentReaderAikiCoreUtil() {
    }

    public static void loadRom(DataBase _d, StringMap<String> _files, AbstractAtomicIntegerCoreAdd _perCentLoading, SexListInt _sexList, String _base) {
        _perCentLoading.set(0);
        _d.initializeMembers();
        _d.initTranslations();
        _d.setCombos(Instances.newCombos());
        _d.setMap(Instances.newDataMap());

        _d.setImages(new StringMap<ImageArrayBaseSixtyFour>());
        _d.setImagesTiles(new StringMap<ScreenCoordssInt>());


        _d.setMiniMap(new StringMap<ImageArrayBaseSixtyFour>());


        _d.setLinks(new StringMap<ImageArrayBaseSixtyFour>());

        _d.setPeople(new StringMap<ImageArrayBaseSixtyFour>());

        _d.setTrainers(new StringMap<ImageArrayBaseSixtyFour>());

        _d.setMaxiPkBack(new StringMap<ImageArrayBaseSixtyFour>());

        _d.setMaxiPkFront(new StringMap<ImageArrayBaseSixtyFour>());

        _d.setMiniPk(new StringMap<ImageArrayBaseSixtyFour>());

        _d.setMiniItems(new StringMap<ImageArrayBaseSixtyFour>());


        _d.setTypesImages(new StringMap<ImageArrayBaseSixtyFour>());

        _d.setTableTypes(new TypesDuos());
        _d.setLawsDamageRate(new IdMap<DifficultyModelLaw, LawNumber>());
        _d.setExpGrowth(new IdMap<ExpType, String>());
        _d.setRates(new IdMap<DifficultyWinPointsFight, String>());
        _d.setEndGameImage(ImageArrayBaseSixtyFour.instance());
        _d.setStorage(ImageArrayBaseSixtyFour.instance());
        _d.setAnimAbsorb(ImageArrayBaseSixtyFour.instance());
        _d.setImageTmHm(ImageArrayBaseSixtyFour.instance());
        allTrs(_d);
        int percent_ = NumberUtil.max(_files.size() / 50, 1);
        int i = 0;

        for (String v: _files.values()) {
            feedByContent(_d,DocumentBuilder.parseNoTextDocument(v),_sexList,_base);
            incr(_perCentLoading,percent_,i);
            i++;
        }
        _perCentLoading.set(50);
        _d.calculateAvgPound();
        _d.completeVariables();
        _d.completeMembersCombos();
        _d.sortEndRound();
    }
    static void incr(AbstractAtomicIntegerCoreAdd _i, int _percent, int _current) {
        if (_current % _percent == 0) {
            _i.addAndGet(1);
        }
    }

    private static void allTrs(DataBase _d) {
        for (String l : _d.getLanguages()) {
            _d.getTranslatedAbilities().addEntry(l,new StringMap<String>());
            _d.getTranslatedCategories().addEntry(l,new StringMap<String>());
            _d.getTranslatedFctMath().addEntry(l,new StringMap<String>());
            _d.getTranslatedItems().addEntry(l,new StringMap<String>());
            _d.getTranslatedMoves().addEntry(l,new StringMap<String>());
            _d.getTranslatedPokemon().addEntry(l,new StringMap<String>());
            _d.getTranslatedStatus().addEntry(l,new StringMap<String>());
            _d.getTranslatedTypes().addEntry(l,new StringMap<String>());
            _d.getTranslatedGenders().addEntry(l,new IdMap<Gender, String>());
            _d.getTranslatedBooleans().addEntry(l,new IdMap<SelectedBoolean, String>());
            _d.getTranslatedDiffModelLaw().addEntry(l,new IdMap<DifficultyModelLaw, String>());
            _d.getTranslatedDiffWinPts().addEntry(l,new IdMap<DifficultyWinPointsFight, String>());
            _d.getTranslatedEnvironment().addEntry(l,new IdMap<EnvironmentType, String>());
            _d.getTranslatedStatistics().addEntry(l,new IdMap<Statistic, String>());
            _d.getTranslatedTargets().addEntry(l,new IdMap<TargetChoice, String>());
            _d.getLitterals().addEntry(l,new StringMap<String>());
        }
    }


    static void tableTypes(TypesDuos _dest, Element _table) {
        for (Element e : _table.getChildElements()) {
            String d_ = e.getAttribute(DocumentWriterCoreUtil.FIELD);
            String p_ = e.getAttribute(DocumentWriterCoreUtil.VALUE);
            TypesDuo t_ = new TypesDuo(d_, p_);
            Rate r_ = rateToOne(e.getAttribute(DocumentWriterAikiCoreUtil.ATTR_IMG));
            _dest.addEntry(t_, r_);
        }
    }

    private static void feedByContent(DataBase _d, Document _doc, SexListInt _sexList, String _base) {
        //AbstractAtomicIntegerCoreAdd _perCentLoading,
        if (_doc == null) {
            return;
        }
        images(_d, _doc,_sexList,_base);
        data(_d, _doc);
        trs(_d,_doc);
        csts(_d,_doc);
    }

    private static void csts(DataBase _d, Document _doc) {
        Element c_ = _doc.getDocumentElement();
        if (notCst(_doc)) {
            return;
        }
        if (StringUtil.quickEq(c_.getAttribute(DocumentWriterCoreUtil.FIELD),DocumentWriterAikiCoreUtil.KIND_CST_HM)) {
            for (Element e : c_.getChildElements()) {
                short cle_ = (short) NumberUtil.parseInt(e.getAttribute(DocumentWriterCoreUtil.FIELD));
                _d.getHm().put(cle_, e.getAttribute(DocumentWriterCoreUtil.VALUE));
            }
        }
        if (StringUtil.quickEq(c_.getAttribute(DocumentWriterCoreUtil.FIELD),DocumentWriterAikiCoreUtil.KIND_CST_TM)) {
            tm(_d, c_);
        }
        if (StringUtil.quickEq(c_.getAttribute(DocumentWriterCoreUtil.FIELD),DocumentWriterAikiCoreUtil.KIND_CST_NUM)) {
            cstNum(_d, c_);
        }
        if (StringUtil.quickEq(c_.getAttribute(DocumentWriterCoreUtil.FIELD),DocumentWriterAikiCoreUtil.KIND_CST_OTH)) {
            cstNotNum(_d, c_);
        }
        if (StringUtil.quickEq(c_.getAttribute(DocumentWriterCoreUtil.FIELD),DocumentWriterAikiCoreUtil.KIND_CST_TYPES)) {
            tableTypes(_d.getTableTypes(),c_);
        }
        if (StringUtil.quickEq(c_.getAttribute(DocumentWriterCoreUtil.FIELD),DocumentWriterAikiCoreUtil.KIND_CST_VARS)) {
            for (Element e : c_.getChildElements()) {
                _d.getExpGrowth().put(getExpTypeByName(e.getAttribute(DocumentWriterCoreUtil.FIELD)), e.getAttribute(DocumentWriterCoreUtil.VALUE));
            }
        }
        if (StringUtil.quickEq(c_.getAttribute(DocumentWriterCoreUtil.FIELD),DocumentWriterAikiCoreUtil.KIND_CST_RATES)) {
            for (Element e : c_.getChildElements()) {
                _d.getRates().put(getDiffWonPtsByName(e.getAttribute(DocumentWriterCoreUtil.FIELD)), e.getAttribute(DocumentWriterCoreUtil.VALUE));
            }
        }
        if (StringUtil.quickEq(c_.getAttribute(DocumentWriterCoreUtil.FIELD),DocumentWriterAikiCoreUtil.KIND_CST_RANDS)) {
            rands(_d,c_);
        }
    }

    private static boolean notCst(Document _doc) {
        return !StringUtil.quickEq(_doc.getDocumentElement().getAttribute(DocumentWriterAikiCoreUtil.ATTR_CST),DocumentWriterAikiCoreUtil.CST_FLAG_VALUE);
    }

    private static void rands(DataBase _d, Element _c) {
        for (Element e : _c.getChildElements()) {
            MonteCarloNumber law_ = new MonteCarloNumber();

            for (String evt_ : StringUtil.splitStrings(e.getAttribute(DocumentWriterCoreUtil.VALUE),
                    DataBase.SEPARATOR_RAND)) {
                StringList infosLoc_ = StringUtil.splitStrings(evt_,
                        DataBase.SEPARATOR_RAND_EVENTS);
                boolean defaultLaw_ = defaultLaw(infosLoc_);
                if (defaultLaw_) {
                    law_ = new MonteCarloNumber();
                    law_.addQuickEvent(new Rate(1),DataBase.defElementaryEvent());

                    break;
                }

                law_.addEvent(new Rate(infosLoc_.first()),
                        new LgInt(infosLoc_.last()));

            }
            if (!law_.checkEvents()) {
                law_ = new MonteCarloNumber();
                law_.addQuickEvent(new Rate(1),DataBase.defElementaryEvent());
            }
            _d.getLawsDamageRate().put(
                    getModelByName(e.getAttribute(DocumentWriterCoreUtil.FIELD)),
                    new LawNumber(law_, (short) 0));
        }
    }

    private static void cstNum(DataBase _d, Element _c) {
        for (Element e : _c.getChildElements()) {
            _d.getConstNum().put(e.getAttribute(DocumentWriterCoreUtil.FIELD), new Rate(e.getAttribute(DocumentWriterCoreUtil.VALUE)));
        }
    }

    private static void cstNotNum(DataBase _d, Element _c) {
        for (Attr a: _c.getAttributes()) {
            if (!StringUtil.quickEq(a.getName(),DocumentWriterCoreUtil.FIELD)&&!StringUtil.quickEq(a.getName(),DocumentWriterAikiCoreUtil.ATTR_CST)) {
                _d.initValue(a.getName(),((DefAttr)a).getValue());
            }
        }
    }

    private static void tm(DataBase _d, Element _c) {
        for (Element e : _c.getChildElements()) {
            short cle_ = (short) NumberUtil.parseInt(e.getAttribute(DocumentWriterCoreUtil.FIELD));
            _d.getTm().put(cle_, e.getAttribute(DocumentWriterCoreUtil.VALUE));
            LgInt price_ = price(e.getAttribute(DocumentWriterAikiCoreUtil.ATTR_IMG));
            _d.getTmPrice().put(cle_, price_);
        }
    }

    static LgInt price(String _v) {
        LgInt price_;
        if (LgInt.isValid(_v)) {
            price_ = new LgInt(_v);
        } else {
            price_ = new LgInt(1000);
        }
        return price_;
    }

    private static void trs(DataBase _d, Document _doc) {
        Element c_ = _doc.getDocumentElement();
        String valueTr_ = c_.getAttribute(DocumentWriterAikiCoreUtil.ATTR_TRS);
        if (valueTr_.isEmpty()) {
            return;
        }
        allTrsMap(_d, c_, valueTr_);
        otherTrsMap(_d,c_,valueTr_);
    }

    private static void otherTrsMap(DataBase _d, Element _c, String _valueTr) {
        if (StringUtil.quickEq(_c.getAttribute(DocumentWriterCoreUtil.VALUE),DocumentWriterAikiCoreUtil.KIND_TRS_GDR)) {
            IdMap<Gender, String> genders_ = new IdMap<Gender, String>();
            for (Element c : _c.getChildElements()) {
                genders_.put(getGenderByName(c.getAttribute(DocumentWriterCoreUtil.FIELD)),
                        c.getAttribute(DocumentWriterCoreUtil.VALUE));
            }
            _d.getTranslatedGenders().put(_valueTr, genders_);
        }
        if (StringUtil.quickEq(_c.getAttribute(DocumentWriterCoreUtil.VALUE),DocumentWriterAikiCoreUtil.KIND_TRS_BOOLS)) {
            IdMap<SelectedBoolean, String> booleans_ = new IdMap<SelectedBoolean, String>();
            for (Element c : _c.getChildElements()) {
                booleans_.put(getBoolByName(c.getAttribute(DocumentWriterCoreUtil.FIELD)),
                        c.getAttribute(DocumentWriterCoreUtil.VALUE));
            }
            _d.getTranslatedBooleans().put(_valueTr, booleans_);
        }
        if (StringUtil.quickEq(_c.getAttribute(DocumentWriterCoreUtil.VALUE),DocumentWriterAikiCoreUtil.KIND_TRS_D_WIN)) {
            IdMap<DifficultyWinPointsFight, String> diffWinPts_ = new IdMap<DifficultyWinPointsFight, String>();
            for (Element c : _c.getChildElements()) {
                diffWinPts_.put(
                        getDiffWonPtsByName(c.getAttribute(DocumentWriterCoreUtil.FIELD)),
                        c.getAttribute(DocumentWriterCoreUtil.VALUE));
            }
            _d.getTranslatedDiffWinPts().put(_valueTr, diffWinPts_);
        }
        if (StringUtil.quickEq(_c.getAttribute(DocumentWriterCoreUtil.VALUE),DocumentWriterAikiCoreUtil.KIND_TRS_D_MODEL)) {
            IdMap<DifficultyModelLaw, String> diffModel_ = new IdMap<DifficultyModelLaw, String>();
            for (Element c : _c.getChildElements()) {
                diffModel_.put(
                        getModelByName(c.getAttribute(DocumentWriterCoreUtil.FIELD)),
                        c.getAttribute(DocumentWriterCoreUtil.VALUE));
            }
            _d.getTranslatedDiffModelLaw().put(_valueTr, diffModel_);
        }
        def(_d, _c, _valueTr);
    }

    private static void def(DataBase _d, Element _c, String _valueTr) {
        if (StringUtil.quickEq(_c.getAttribute(DocumentWriterCoreUtil.VALUE),DocumentWriterAikiCoreUtil.KIND_TRS_ENV)) {
            IdMap<EnvironmentType, String> env_ = new IdMap<EnvironmentType, String>();
            for (Element c : _c.getChildElements()) {
                env_.put(
                        getEnvByName(c.getAttribute(DocumentWriterCoreUtil.FIELD)),
                        c.getAttribute(DocumentWriterCoreUtil.VALUE));
            }
            _d.getTranslatedEnvironment().put(_valueTr, env_);
        }
        if (StringUtil.quickEq(_c.getAttribute(DocumentWriterCoreUtil.VALUE),DocumentWriterAikiCoreUtil.KIND_TRS_STAT)) {
            IdMap<Statistic, String> sta_ = new IdMap<Statistic, String>();
            for (Element c : _c.getChildElements()) {
                sta_.put(
                        Statistic.getStatisticByName(c.getAttribute(DocumentWriterCoreUtil.FIELD)),
                        c.getAttribute(DocumentWriterCoreUtil.VALUE));
            }
            _d.getTranslatedStatistics().put(_valueTr, sta_);
        }
        if (StringUtil.quickEq(_c.getAttribute(DocumentWriterCoreUtil.VALUE),DocumentWriterAikiCoreUtil.KIND_TRS_TARG)) {
            IdMap<TargetChoice, String> tar_ = new IdMap<TargetChoice, String>();
            for (Element c : _c.getChildElements()) {
                tar_.put(
                        getTargetChoiceByName(c.getAttribute(DocumentWriterCoreUtil.FIELD)),
                        c.getAttribute(DocumentWriterCoreUtil.VALUE));
            }
            _d.getTranslatedTargets().put(_valueTr, tar_);
        }
    }

    private static void allTrsMap(DataBase _d, Element _c, String _valueTr) {
        if (StringUtil.quickEq(_c.getAttribute(DocumentWriterCoreUtil.VALUE),DocumentWriterAikiCoreUtil.KIND_AB)) {
            trsMap(_c, _valueTr, _d.getTranslatedAbilities());
        }
        if (StringUtil.quickEq(_c.getAttribute(DocumentWriterCoreUtil.VALUE),DocumentWriterAikiCoreUtil.KIND_IT)) {
            trsMap(_c, _valueTr, _d.getTranslatedItems());
        }
        if (StringUtil.quickEq(_c.getAttribute(DocumentWriterCoreUtil.VALUE),DocumentWriterAikiCoreUtil.KIND_MV)) {
            trsMap(_c, _valueTr, _d.getTranslatedMoves());
        }
        if (StringUtil.quickEq(_c.getAttribute(DocumentWriterCoreUtil.VALUE),DocumentWriterAikiCoreUtil.KIND_PK)) {
            trsMap(_c, _valueTr, _d.getTranslatedPokemon());
        }
        if (StringUtil.quickEq(_c.getAttribute(DocumentWriterCoreUtil.VALUE),DocumentWriterAikiCoreUtil.KIND_ST)) {
            trsMap(_c, _valueTr, _d.getTranslatedStatus());
        }
        if (StringUtil.quickEq(_c.getAttribute(DocumentWriterCoreUtil.VALUE),DocumentWriterAikiCoreUtil.KIND_TRS_TYPES)) {
            trsMap(_c, _valueTr, _d.getTranslatedTypes());
        }
        if (StringUtil.quickEq(_c.getAttribute(DocumentWriterCoreUtil.VALUE),DocumentWriterAikiCoreUtil.KIND_TRS_MATH)) {
            trsMap(_c, _valueTr, _d.getTranslatedFctMath());
        }
        if (StringUtil.quickEq(_c.getAttribute(DocumentWriterCoreUtil.VALUE),DocumentWriterAikiCoreUtil.KIND_TRS_DESCS)) {
            trsMap(_c, _valueTr, _d.getTranslatedClassesDescriptions());
        }
        if (StringUtil.quickEq(_c.getAttribute(DocumentWriterCoreUtil.VALUE),DocumentWriterAikiCoreUtil.KIND_TRS_CATS)) {
            trsMap(_c, _valueTr, _d.getTranslatedCategories());
        }
        if (StringUtil.quickEq(_c.getAttribute(DocumentWriterCoreUtil.VALUE),DocumentWriterAikiCoreUtil.KIND_TRS_LITTS)) {
            trsMap(_c, _valueTr,_d.getLitterals());
        }
    }

    private static void trsMap(Element _e, String _l, StringMap<StringMap<String>> _dest) {
        StringMap<String> categories_ = new StringMap<String>();
        for (Element c : _e.getChildElements()) {
            categories_.put(c.getAttribute(DocumentWriterCoreUtil.FIELD),
                    c.getAttribute(DocumentWriterCoreUtil.VALUE));
        }
        _dest.put(_l, categories_);
    }

    private static void data(DataBase _d, Document _doc) {
        Element e_ = _doc.getDocumentElement();
        String valueImg_ = e_.getAttribute(DocumentWriterAikiCoreUtil.ATTR_IMG);
        if (!valueImg_.isEmpty()) {
            return;
        }
        String kindImg_ = kind(e_);
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_AB)) {
            PkFileElement<AbilityData> ab_ = new PkFileElement<AbilityData>(e_.getAttribute(DocumentReaderCoreUtil.VALUE), getAbilityData(e_));
            _d.completeMembers(check(ab_.getKey(),_d), ab_.getData());
        }
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_IT)) {
            PkFileElement<Item> it_ = new PkFileElement<Item>(e_.getAttribute(DocumentReaderCoreUtil.VALUE), getItem(e_));
            _d.completeMembers(check(it_.getKey(),_d), it_.getData());
        }
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_MV)) {
            PkFileElement<MoveData> mv_ = new PkFileElement<MoveData>(e_.getAttribute(DocumentReaderCoreUtil.VALUE), getMoveData(e_));
            _d.completeMembers(check(mv_.getKey(),_d), mv_.getData());
        }
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_PK)) {
            PkFileElement<PokemonData> pk_ = new PkFileElement<PokemonData>(e_.getAttribute(DocumentReaderCoreUtil.VALUE), getPokemonData(e_));
            _d.completeMembers(check(pk_.getKey(),_d), pk_.getData());
        }
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_ST)) {
            PkFileElement<Status> st_ = new PkFileElement<Status>(e_.getAttribute(DocumentReaderCoreUtil.VALUE), getStatus(e_));
            _d.completeMembers(check(st_.getKey(),_d), st_.getData());
        }
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_MAP)) {
            _d.setMap(DocumentReaderAikiCoreUtil.getDataMap(e_));
        }
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_COMBOS)) {
            _d.setCombos(DocumentReaderAikiCoreUtil.getCombos(e_));
        }
    }

    private static String kind(Element _e) {
        String k_ = _e.getAttribute(DocumentWriterCoreUtil.FIELD);
        if (StringUtil.quickEq(k_, DocumentWriterAikiCoreUtil.KIND_MAP) || StringUtil.quickEq(k_, DocumentWriterAikiCoreUtil.KIND_COMBOS)) {
            if (_e.getAttributes().getLength() == 1) {
                return k_;
            }
            return "";
        }
        if (_e.getAttribute(DocumentReaderCoreUtil.VALUE).isEmpty()) {
            return "";
        }
        return k_;
    }

    private static void images(DataBase _d, Document _doc, SexListInt _sexList, String _base) {
        String valueImg_ = _doc.getDocumentElement().getAttribute(DocumentWriterAikiCoreUtil.ATTR_IMG);
        if (valueImg_.isEmpty()) {
            return;
        }
        String baseMerged_ = BaseSixtyFourUtil.checkBase(_doc.getDocumentElement().getAttribute(DocumentWriterAikiCoreUtil.ATTR_IMG_BASE), _base);
        String kindImg_ = _doc.getDocumentElement().getAttribute(DocumentWriterCoreUtil.FIELD);
        String nameImg_ = _doc.getDocumentElement().getAttribute(DocumentWriterCoreUtil.VALUE);
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_IMG_PK_FR)) {
            _d.getMaxiPkFront().addEntry(nameImg_,ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(valueImg_,baseMerged_),baseMerged_));
        }
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_IMG_PK_BK)) {
            _d.getMaxiPkBack().addEntry(nameImg_,ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(valueImg_,baseMerged_),baseMerged_));
        }
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_IMG_PK_MI)) {
            _d.getMiniPk().addEntry(nameImg_,ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(valueImg_,baseMerged_),baseMerged_));
        }
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_IMG_IT_MI)) {
            _d.getMiniItems().addEntry(nameImg_,ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(valueImg_,baseMerged_),baseMerged_));
        }
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_IMG_TY)) {
            _d.getTypesImages().addEntry(nameImg_,ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(valueImg_,baseMerged_),baseMerged_));
        }
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_IMG_AI)) {
            _d.getAnimStatis().addEntry(nameImg_,ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(valueImg_,baseMerged_),baseMerged_));
        }
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_IMG_AU)) {
            _d.getAnimStatus().addEntry(nameImg_,ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(valueImg_,baseMerged_),baseMerged_));
        }
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_IMG_TR)) {
            _d.getTrainers().addEntry(nameImg_,ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(valueImg_,baseMerged_),baseMerged_));
        }
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_IMG_IM)) {
            _d.getImages().addEntry(nameImg_,ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(valueImg_,baseMerged_),baseMerged_));
        }
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_IMG_LK)) {
            _d.getLinks().addEntry(nameImg_,ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(valueImg_,baseMerged_),baseMerged_));
        }
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_IMG_PEOPLE)) {
            _d.getPeople().addEntry(nameImg_,ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(valueImg_,baseMerged_),baseMerged_));
        }
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_IMG_MINI_MAP)) {
            _d.getMiniMap().addEntry(nameImg_,ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(valueImg_,baseMerged_),baseMerged_));
        }
        if (StringUtil.quickEq(kindImg_, DocumentWriterAikiCoreUtil.KIND_IMG_INDIV)) {
            imagesIndiv(_d,_doc,valueImg_,_sexList, baseMerged_);
        }
    }
    private static void imagesIndiv(DataBase _d, Document _doc, String _valueImg, SexListInt _sexList, String _base) {
        String nameImg_ = _doc.getDocumentElement().getAttribute(DocumentWriterCoreUtil.VALUE);
        if (StringUtil.quickEq(nameImg_, DocumentWriterAikiCoreUtil.KIND_IMG_TM)) {
            _d.setImageTmHm(ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(_valueImg,_base),_base));
        }
        if (StringUtil.quickEq(nameImg_, DocumentWriterAikiCoreUtil.KIND_IMG_STORE)) {
            _d.setStorage(ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(_valueImg,_base),_base));
        }
        if (StringUtil.quickEq(nameImg_, DocumentWriterAikiCoreUtil.KIND_IMG_ABS)) {
            _d.setAnimAbsorb(ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(_valueImg,_base),_base));
        }
        if (StringUtil.quickEq(nameImg_, DocumentWriterAikiCoreUtil.KIND_IMG_END)) {
            _d.setEndGameImage(ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(_valueImg,_base),_base));
        }
        if (StringUtil.quickEq(nameImg_, DocumentWriterAikiCoreUtil.KIND_IMG_CODE)) {
            _d.setTypesColors(new StringMap<String>());
            for (Element c: _doc.getDocumentElement().getChildElements()) {
                _d.getTypesColors().put(c.getAttribute(DocumentWriterCoreUtil.FIELD),c.getAttribute(DocumentWriterCoreUtil.VALUE));
            }
        }
        heros(_d, _doc, _sexList, nameImg_, _base);
    }

    static void heros(DataBase _d, Document _doc, SexListInt _sexList, String _nameImg, String _base) {
        if (StringUtil.quickEq(_nameImg, DocumentWriterAikiCoreUtil.KIND_IMG_HEROS_FR)) {
            _d.setFrontHeros(new ImageHeroKeys());
            for (Element c: _doc.getDocumentElement().getChildElements()) {
                StringList keyStrings_ = StringUtil.splitStrings(c.getAttribute(DocumentWriterCoreUtil.FIELD),
                        DocumentWriterAikiCoreUtil.SEPARATOR_KEY_HEROS);
                EnvironmentType env_ = getEnvByName(keyStrings_
                        .first());
                Sex sex_ = getSexByName(keyStrings_.last(), _sexList);
                _d.getFrontHeros().put(new ImageHeroKey(env_, sex_),
                        ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(c.getAttribute(DocumentWriterCoreUtil.VALUE),_base),_base));
            }
        }
        if (StringUtil.quickEq(_nameImg, DocumentWriterAikiCoreUtil.KIND_IMG_HEROS_BK)) {
            _d.setBackHeros(new ImageHeroKeys());
            for (Element c: _doc.getDocumentElement().getChildElements()) {
                StringList keyStrings_ = StringUtil.splitStrings(c.getAttribute(DocumentWriterCoreUtil.FIELD),
                        DocumentWriterAikiCoreUtil.SEPARATOR_KEY_HEROS);
                EnvironmentType env_ = getEnvByName(keyStrings_
                        .first());
                Sex sex_ = getSexByName(keyStrings_.last(), _sexList);
                _d.getBackHeros().put(new ImageHeroKey(env_, sex_),
                        ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(c.getAttribute(DocumentWriterCoreUtil.VALUE),_base),_base));
            }
        }
        if (StringUtil.quickEq(_nameImg, DocumentWriterAikiCoreUtil.KIND_IMG_HEROS_MIN)) {
            _d.setOverWorldHeros(new ImageHeroKeys());
            for (Element c: _doc.getDocumentElement().getChildElements()) {
                StringList keyStrings_ = StringUtil.splitStrings(c.getAttribute(DocumentWriterCoreUtil.FIELD),
                        DocumentWriterAikiCoreUtil.SEPARATOR_KEY_HEROS);
                if (keyStrings_.size() < 3) {
                    continue;
                }
                EnvironmentType env_ = getEnvByName(keyStrings_
                        .first());
                Direction dir_ = Direction.getDirectionByName(keyStrings_
                        .get(IndexConstants.SECOND_INDEX));
                Sex sex_ = getSexByName(keyStrings_.last(), _sexList);
                _d.getOverWorldHeros().put(new ImageHeroKey(env_, dir_, sex_),
                        ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(c.getAttribute(DocumentWriterCoreUtil.VALUE),_base),_base));
            }
        }
    }

    static Rate rateToOne(String _str) {
        Rate r_;
        if (Rate.isValid(_str)) {
            r_ = new Rate(_str);
        } else {
            r_ = DataBase.defRateProduct();
        }
        return r_;
    }

    static boolean defaultLaw(StringList _infos) {
        return !Rate.isValid(_infos.first()) || !LgInt.isValid(_infos.last());
    }


    static String check(String _n, DataBase _d) {
        if (!isCorrectIdentifier(_n)) {
            _d.setError(true);
        }
        return _n;
    }
    /*public static void loadResources(DataBase _d,PerCent _perCentLoading, String _lg) {
        int delta_ = (100 - _perCentLoading.getPercent()) / 6;

        _d.initializeMembers();
        String common_ = Resources.ACCESS_TO_DEFAULT_FILES;
		StringMap<String> cts_ = Cst.tr();
        StringList tmHm_ = StringUtil.splitChars(cts_.getVal(CT_CS_FILE),
                RETURN_LINE_CHAR);
        /*StringList tmHm_ = StringUtil.splitChars(ResourceFiles
                        .ressourceFichier(StringUtil.concat(common_, CT_CS_FILE)),
                RETURN_LINE_CHAR);*/
        /*for (String l : tmHm_) {
            if (l.startsWith(CT)) {
                StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
                short cle_ = (short) NumberUtil.parseInt(infos_.first().substring(2));
                _d.getTm().put(cle_, infos_.get(1).trim());
                LgInt price_;
                if (LgInt.isValid(infos_.get(2).trim())) {
                    price_ = new LgInt(infos_.get(2).trim());
                } else {
                    price_ = new LgInt(1000);
                }
                _d.getTmPrice().put(cle_, price_);

            }
            if (l.startsWith(CS)) {
                StringList infos_ = StringUtil.splitChars(l.trim(), TAB_CHAR);
                short cle_ = (short) NumberUtil.parseInt(infos_.first().substring(2));
                _d.getHm().put(cle_, infos_.get(1));
            }
        }
        _d.setFrontHeros(new ImageHeroKeys());
        StringMap<String> heFr_ = HeFront.im();
        for (EntryCust<String,String> e:heFr_.entryList()){
            StringList keyStrings_ = StringUtil.splitStrings(e.getKey(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = getEnvByName(keyStrings_.first());
            Sex sex_ = getSexByName(keyStrings_.last());
            _d.getFrontHeros().addEntry(new ImageHeroKey(env_, sex_),
                    BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
        /*for (String l : StringUtil.splitChars(ResourceFiles
                .ressourceFichier(StringUtil.concat(common_, HERO_FOLDER,
                        SEPARATOR_FILES, HERO_FRONT)), RETURN_LINE_CHAR)) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
            StringList keyStrings_ = StringUtil.splitStrings(infos_.first(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = getEnvByName(keyStrings_
                    .first());
            Sex sex_ = getSexByName(keyStrings_.last());
            _d.getFrontHeros().put(new ImageHeroKey(env_, sex_),
                    BaseSixtyFourUtil.getImageByString(infos_.last()));
        }*/
        /*_d.setBackHeros(new ImageHeroKeys());
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
        /*for (String l : StringUtil.splitChars(ResourceFiles
                .ressourceFichier(StringUtil.concat(common_, HERO_FOLDER,
                        SEPARATOR_FILES, HERO_BACK)), RETURN_LINE_CHAR)) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
            StringList keyStrings_ = StringUtil.splitStrings(infos_.first(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = getEnvByName(keyStrings_
                    .first());
            Sex sex_ = getSexByName(keyStrings_.last());
            _d.getBackHeros().put(new ImageHeroKey(env_, sex_),
                    BaseSixtyFourUtil.getImageByString(infos_.last()));
        }*/
        /*_d.setOverWorldHeros(new ImageHeroKeys());
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
        /*for (String l : StringUtil.splitChars(ResourceFiles
                .ressourceFichier(StringUtil.concat(common_, HERO_FOLDER,
                        SEPARATOR_FILES, HERO_MINI)), RETURN_LINE_CHAR)) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
            StringList keyStrings_ = StringUtil.splitStrings(infos_.first(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = getEnvByName(keyStrings_
                    .first());
            Direction dir_ = Direction.getDirectionByName(keyStrings_
                    .get(IndexConstants.SECOND_INDEX));
            Sex sex_ = getSexByName(keyStrings_.last());
            _d.getOverWorldHeros().put(new ImageHeroKey(env_, dir_, sex_),
                    BaseSixtyFourUtil.getImageByString(infos_.last()));
        }*/
        //_d.setImageTmHm(BaseSixtyFourUtil.getImageByString(ImHmTm.im()));
        /*_d.setImageTmHm(BaseSixtyFourUtil.getImageByString(ResourceFiles
                .ressourceFichier(StringUtil.concat(common_, IMAGE_TM_HM_FILES,
                        IMG_FILES_RES_EXT_TXT))));*/
        //_d.setStorage(BaseSixtyFourUtil.getImageByString(ImStorage.im()));
        /*_d.setStorage(BaseSixtyFourUtil.getImageByString(ResourceFiles
                .ressourceFichier(StringUtil.concat(common_,
                        IMAGE_STORAGE_FILES, IMG_FILES_RES_EXT_TXT))));*/
        /*_d.setCombos(CoInit.co());
//        _d.setCombos(DocumentReaderAikiCoreUtil.getCombos(ResourceFiles
//                .ressourceFichier(StringUtil.concat(common_, COMBOS))));
        _d.completeMembersCombos();
        _d.setMap(Dm.map());
//        _d.setMap(DocumentReaderAikiCoreUtil.getDataMap(ResourceFiles
//                .ressourceFichier(StringUtil.concat(common_, MAP_FILE))));
        _perCentLoading.addPercent(delta_);
        _d.setConstNum(new StringMap<Rate>());
        StringList lines_ = StringUtil.splitChars(cts_.getVal(CONST_NUM),
                RETURN_LINE_CHAR);
        /*StringList lines_ = StringUtil.splitChars(ResourceFiles
                        .ressourceFichier(StringUtil.concat(common_, CONST_NUM)),
                RETURN_LINE_CHAR);*/
        /*for (String l : lines_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
            _d.getConstNum().put(infos_.first(), new Rate(infos_.last()));
        }

        lines_ = StringUtil.splitChars(cts_.getVal(CONST_NOT_NUM),
                RETURN_LINE_CHAR);
        /*lines_ = StringUtil.splitChars(ResourceFiles
                        .ressourceFichier(StringUtil.concat(common_, CONST_NOT_NUM)),
                RETURN_LINE_CHAR);*/
        /*for (String l : lines_) {
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
        /*StringList linesTableTypes_ = StringUtil.splitChars(ResourceFiles
                        .ressourceFichier(StringUtil.concat(common_, TABLE_TYPES)),
                RETURN_LINE_CHAR);*/
        /*StringList linesTableTypes_ = StringUtil.splitChars(cts_.getVal(TABLE_TYPES),
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
        /*StringList laws_ = StringUtil.splitChars(ResourceFiles
                        .ressourceFichier(StringUtil.concat(common_, LOIS_RANDOM)),
                RETURN_LINE_CHAR);*/
        /*StringList laws_ = StringUtil.splitChars(cts_.getVal(LOIS_RANDOM),
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
            _d.getLawsDamageRate().put(
                    getModelByName(infos_.first()),
                    new LawNumber(law_, (short) NumberUtil.parseInt(infos_.last())));
        }
        _d.setExpGrowth(new EnumMap<ExpType, String>());
        /*StringList courbes_ = StringUtil.splitChars(ResourceFiles
                        .ressourceFichier(StringUtil.concat(common_, COURBE_PTS_EXP)),
                RETURN_LINE_CHAR);*/
        /*StringList courbes_ = StringUtil.splitChars(cts_.getVal(COURBE_PTS_EXP),
                RETURN_LINE_CHAR);
        for (String l : courbes_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
            _d.getExpGrowth().put(getExpTypeByName(infos_.first()),
                    infos_.get(1));
        }
        _d.setRates(new EnumMap<DifficultyWinPointsFight, String>());
        /*StringList rates_ = StringUtil.splitChars(ResourceFiles
                        .ressourceFichier(StringUtil.concat(common_, RATE_WON_POINTS)),
                RETURN_LINE_CHAR);*/
        /*StringList rates_ = StringUtil.splitChars(cts_.getVal(RATE_WON_POINTS),
                RETURN_LINE_CHAR);
        for (String l : rates_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
            _d.getRates().put(getDiffWonPtsByName(infos_
                    .first()), infos_.get(1));
        }
        _d.setTypesColors(new StringMap<String>());
        /*rates_ = StringUtil.splitChars(ResourceFiles
                .ressourceFichier(StringUtil.concat(common_, TYPES_COLOR_CODE,
                        IMG_FILES_RES_EXT_TXT)), RETURN_LINE_CHAR);*/
        /*rates_ = StringUtil.splitChars(cts_.getVal(TYPES_COLOR_CODE+IMG_FILES_RES_EXT_TXT), RETURN_LINE_CHAR);
        for (String l : rates_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringUtil.splitChars(l, TAB_CHAR);
            String colorStr_ = infos_.get(1);
            _d.getTypesColors().put(infos_.first(), colorStr_);
        }
        _d.setEndGameImage(BaseSixtyFourUtil.getImageByString(ImEndGame.im()));
        /*_d.setEndGameImage(BaseSixtyFourUtil.getImageByString(ResourceFiles
                .ressourceFichier(StringUtil.concat(common_, END_GAME_IMAGE,
                        IMG_FILES_RES_EXT_TXT))));*/
        /*_d.initTranslations();
		StringMap<String> trs_ = Trs.tr();
		for (String l : _d.getLanguages()) {
			EnumMap<Gender, String> genders_ = new EnumMap<Gender, String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_GENDERS),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                genders_.put(getGenderByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedGenders().put(l, genders_);
			EnumMap<SelectedBoolean, String> booleans_ = new EnumMap<SelectedBoolean, String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_BOOLEANS),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                booleans_.put(getBoolByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedBooleans().put(l, booleans_);
            EnumMap<DifficultyWinPointsFight, String> diffWinPts_ = new EnumMap<DifficultyWinPointsFight, String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_DIFF_WIN_PTS),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                diffWinPts_.put(
                        getDiffWonPtsByName(infos_.first()), DocumentBuilder
                        .transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedDiffWinPts().put(l, diffWinPts_);
            EnumMap<DifficultyModelLaw, String> diffLaw_ = new EnumMap<DifficultyModelLaw, String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_DIFF_MODEL_LAW),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                diffLaw_.put(getModelByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedDiffModelLaw().put(l, diffLaw_);
            EnumMap<EnvironmentType, String> environments_ = new EnumMap<EnvironmentType, String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_ENVIRONMENTS),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                environments_.put(getEnvByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedEnvironment().put(l, environments_);
            EnumMap<Statistic, String> statistics_ = new EnumMap<Statistic, String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_STATISTICS),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                statistics_.put(Statistic.getStatisticByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedStatistics().put(l, statistics_);
            EnumMap<TargetChoice, String> targets_ = new EnumMap<TargetChoice, String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_TARGETS),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                targets_.put(
                        getTargetChoiceByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedTargets().put(l, targets_);
            StringMap<String> categories_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_CATEGORIES),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                categories_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedCategories().put(l, categories_);
            StringMap<String> types_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_TYPES),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                types_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedTypes().put(l, types_);
            StringMap<String> pokemon_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_POKEMON),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                pokemon_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedPokemon().put(l, pokemon_);
            StringMap<String> moves_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_MOVES),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                moves_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedMoves().put(l, moves_);
            StringMap<String> items_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_ITEMS),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                items_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedItems().put(l, items_);
            StringMap<String> abilities_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_ABILITIES),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                abilities_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedAbilities().put(l, abilities_);
            StringMap<String> status_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_STATUS),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                status_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedStatus().put(l, status_);
            StringMap<String> fctsMath_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_MATH),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                fctsMath_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedFctMath().put(l, fctsMath_);
            StringMap<String> descrClasses_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_CLASSES),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                descrClasses_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedClassesDescriptions().put(l, descrClasses_);
            StringMap<String> litteral_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(trs_.getVal(l+SEPARATOR_FILES+TRANSLATION_LITTERAL),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                litteral_
                        .put(infos_.first(), DocumentBuilder
                                .transformSpecialChars(StringUtil
                                        .join(infos_.leftMinusOne(
                                                infos_.size()), TAB)));
            }
            _d.getLitterals().put(l, litteral_);
		}
        /*for (String l : _d.getLanguages()) {
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_GENDERS);
            EnumMap<Gender, String> genders_ = new EnumMap<Gender, String>();
            for (String l2_ : StringUtil.splitChars(ResourceFiles
                            .ressourceFichier(StringUtil.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                genders_.put(getGenderByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedGenders().put(l, genders_);
            fileName_ = StringUtil.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_BOOLEANS);
            EnumMap<SelectedBoolean, String> booleans_ = new EnumMap<SelectedBoolean, String>();
            for (String l2_ : StringUtil.splitChars(ResourceFiles
                            .ressourceFichier(StringUtil.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                booleans_.put(getBoolByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedBooleans().put(l, booleans_);
            fileName_ = StringUtil.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_DIFF_WIN_PTS);
            EnumMap<DifficultyWinPointsFight, String> diffWinPts_ = new EnumMap<DifficultyWinPointsFight, String>();
            for (String l2_ : StringUtil.splitChars(ResourceFiles
                            .ressourceFichier(StringUtil.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                diffWinPts_.put(
                        getDiffWonPtsByName(infos_.first()), DocumentBuilder
                        .transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedDiffWinPts().put(l, diffWinPts_);
            fileName_ = StringUtil.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil
                    .concat(fileName_, TRANSLATION_DIFF_MODEL_LAW);
            EnumMap<DifficultyModelLaw, String> diffLaw_ = new EnumMap<DifficultyModelLaw, String>();
            for (String l2_ : StringUtil.splitChars(ResourceFiles
                            .ressourceFichier(StringUtil.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                diffLaw_.put(getModelByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedDiffModelLaw().put(l, diffLaw_);
            fileName_ = StringUtil.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_ENVIRONMENTS);
            EnumMap<EnvironmentType, String> environments_ = new EnumMap<EnvironmentType, String>();
            for (String l2_ : StringUtil.splitChars(ResourceFiles
                            .ressourceFichier(StringUtil.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                environments_.put(getEnvByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedEnvironment().put(l, environments_);
            fileName_ = StringUtil.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_STATISTICS);
            EnumMap<Statistic, String> statistics_ = new EnumMap<Statistic, String>();
            for (String l2_ : StringUtil.splitChars(ResourceFiles
                            .ressourceFichier(StringUtil.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                statistics_.put(Statistic.getStatisticByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedStatistics().put(l, statistics_);
            fileName_ = StringUtil.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_TARGETS);
            EnumMap<TargetChoice, String> targets_ = new EnumMap<TargetChoice, String>();
            for (String l2_ : StringUtil.splitChars(ResourceFiles
                            .ressourceFichier(StringUtil.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                targets_.put(
                        getTargetChoiceByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedTargets().put(l, targets_);
            fileName_ = StringUtil.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_CATEGORIES);
            StringMap<String> categories_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(ResourceFiles
                            .ressourceFichier(StringUtil.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                categories_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedCategories().put(l, categories_);
            fileName_ = StringUtil.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_TYPES);
            StringMap<String> types_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(ResourceFiles
                            .ressourceFichier(StringUtil.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                types_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedTypes().put(l, types_);
            fileName_ = StringUtil.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_POKEMON);
            StringMap<String> pokemon_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(ResourceFiles
                            .ressourceFichier(StringUtil.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                pokemon_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedPokemon().put(l, pokemon_);
            fileName_ = StringUtil.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_MOVES);
            StringMap<String> moves_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(ResourceFiles
                            .ressourceFichier(StringUtil.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                moves_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedMoves().put(l, moves_);
            fileName_ = StringUtil.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_ITEMS);
            StringMap<String> items_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(ResourceFiles
                            .ressourceFichier(StringUtil.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                items_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedItems().put(l, items_);
            fileName_ = StringUtil.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_ABILITIES);
            StringMap<String> abilities_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(ResourceFiles
                            .ressourceFichier(StringUtil.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                abilities_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedAbilities().put(l, abilities_);
            fileName_ = StringUtil.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_STATUS);
            StringMap<String> status_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(ResourceFiles
                            .ressourceFichier(StringUtil.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                status_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedStatus().put(l, status_);
            fileName_ = StringUtil.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_MATH);
            StringMap<String> fctsMath_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(ResourceFiles
                            .ressourceFichier(StringUtil.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                fctsMath_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedFctMath().put(l, fctsMath_);
            fileName_ = StringUtil.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_CLASSES);
            StringMap<String> descrClasses_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(ResourceFiles
                            .ressourceFichier(StringUtil.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                descrClasses_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedClassesDescriptions().put(l, descrClasses_);
            fileName_ = StringUtil.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_LITTERAL);
            StringMap<String> litteral_ = new StringMap<String>();
            for (String l2_ : StringUtil.splitChars(ResourceFiles
                            .ressourceFichier(StringUtil.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringUtil.splitChars(l2_, TAB_CHAR);
                litteral_
                        .put(infos_.first(), DocumentBuilder
                                .transformSpecialChars(StringUtil
                                        .join(infos_.leftMinusOne(
                                                infos_.size()), TAB)));
            }
            _d.getLitterals().put(l, litteral_);
        }*/
        /*_perCentLoading.addPercent(delta_);
		for(EntryCust<String,String> e: AnStatis.im().entryList()){
			_d.getAnimStatis().addEntry(e.getKey(), BaseSixtyFourUtil
                    .getImageByString(e.getValue()));
		}
        /*for (Statistic f : _d.getTranslatedStatistics().getVal(_lg)
                .getKeys()) {
            if (!f.isBoost()) {
                continue;
            }
            String f_ = StringUtil.concat(ANIM_STATIS, SEPARATOR_FILES,
                    f.name(), IMG_FILES_RES_EXT_TXT);
            _d.getAnimStatis().put(f.name(), BaseSixtyFourUtil
                    .getImageByString(ResourceFiles.ressourceFichier(StringUtil
                            .concat(common_, f_))));
        }*/
		/*for(EntryCust<String,String> e: AnStatus.im().entryList()){
			_d.getAnimStatus().addEntry(e.getKey(), BaseSixtyFourUtil.getImageByString(e.getValue()));
		}
        /*for (String f : _d.getTranslatedStatus().getVal(_lg)
                .getKeys()) {
            String f_ = StringUtil.concat(ANIM_STATUS, SEPARATOR_FILES, f,
                    IMG_FILES_RES_EXT_TXT);
            _d.getAnimStatus().put(DataBase.toUpperCase(f), BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringUtil.concat(common_, f_))));
        }*/
        //_d.setAnimAbsorb(BaseSixtyFourUtil.getImageByString(AnAbs.im().firstValue()));
        /*_d.setAnimAbsorb(BaseSixtyFourUtil.getImageByString(ResourceFiles
                .ressourceFichier(StringUtil.concat(common_, ANIM_ABSORB))));*/
        /*StringList filesNames_;
        filesNames_ = new StringList();
        for (EntryCust<String,PokemonData> e: PkInit.pk().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
//        for (String f : _d.getTranslatedPokemon().getVal(_lg)
//                .getKeys()) {
//            String n_ = StringUtil.concat(POKEDEX_FOLDER, SEPARATOR_FILES, f,
//                    FILES_RES_EXT);
//            filesNames_.add(f);
//            PokemonData f_ = DocumentReaderAikiCoreUtil
//                    .getPokemonData(ResourceFiles.ressourceFichier(StringUtil
//                            .concat(common_, n_)));
//            _d.completeMembers(DataBase.toUpperCase(f), f_);
//        }
        _d.calculateAvgPound();
        filesNames_.clear();
        for (EntryCust<String,MoveData> e: MvInit.mv().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
//        for (String f : _d.getTranslatedMoves().getVal(_lg)
//                .getKeys()) {
//            String n_ = StringUtil.concat(MOVES_FOLDER, SEPARATOR_FILES, f,
//                    FILES_RES_EXT);
//            filesNames_.add(n_);
//            MoveData move_ = DocumentReaderAikiCoreUtil
//                    .getMoveData(ResourceFiles.ressourceFichier(StringUtil
//                            .concat(common_, n_)));
//            _d.completeMembers(DataBase.toUpperCase(f), move_);
//        }
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
//        for (String f : _d.getTranslatedItems().getVal(_lg)
//                .getKeys()) {
//            String n_ = StringUtil.concat(ITEMS_FOLDER, SEPARATOR_FILES, f,
//                    FILES_RES_EXT);
//            filesNames_.add(n_);
//            Item o_ = DocumentReaderAikiCoreUtil.getItem(ResourceFiles
//                    .ressourceFichier(StringUtil.concat(common_, n_)));
//            _d.completeMembers(DataBase.toUpperCase(f), o_);
//        }
        filesNames_.clear();
        for (EntryCust<String,AbilityData> e: AbInit.ab().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
//        for (String f : _d.getTranslatedAbilities().getVal(_lg)
//                .getKeys()) {
//            String n_ = StringUtil.concat(ABILITIES_FOLDER, SEPARATOR_FILES, f,
//                    FILES_RES_EXT);
//            filesNames_.add(n_);
//            AbilityData ab_ = DocumentReaderAikiCoreUtil
//                    .getAbilityData(ResourceFiles.ressourceFichier(StringUtil
//                            .concat(common_, n_)));
//            _d.completeMembers(DataBase.toUpperCase(f), ab_);
//        }
        filesNames_.clear();
        for (EntryCust<String,Status> e: StInit.st().entryList()) {
            _d.completeQuickMembers(e.getKey(),e.getValue());
        }
//        for (String f : _d.getTranslatedStatus().getVal(_lg)
//                .getKeys()) {
//            String n_ = StringUtil.concat(STATUS_FOLDER, SEPARATOR_FILES, f,
//                    FILES_RES_EXT);
//            filesNames_.add(n_);
//            Status st_ = DocumentReaderAikiCoreUtil.getStatus(ResourceFiles
//                    .ressourceFichier(StringUtil.concat(common_, n_)));
//            _d.completeMembers(DataBase.toUpperCase(f), st_);
//        }
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
        /*for (String s : _d.getPokedex().getKeys()) {
            String n_ = StringUtil.concat(BACK_IMAGES_FOLDER, SEPARATOR_FILES,
                    s, IMG_FILES_RES_EXT_TXT);
            filesNames_.add(n_);
            _d.getMaxiPkBack().put(s, BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringUtil.concat(common_, n_))));
        }*/
		/*for (EntryCust<String,String> e: Bk.im().entryList()) {
            _d.getMaxiPkBack().addEntry(e.getKey(), BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
        filesNames_.clear();
        _d.setMaxiPkFront(new StringMap<int[][]>());
        /*for (String s : _d.getPokedex().getKeys()) {
            String n_ = StringUtil.concat(FRONT_IMAGES_FOLDER, SEPARATOR_FILES,
                    s, IMG_FILES_RES_EXT_TXT);
            filesNames_.add(n_);
            _d.getMaxiPkFront().put(s, BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringUtil.concat(common_, n_))));
        }*/
		/*for (EntryCust<String,String> e: Ft.im().entryList()) {
            _d.getMaxiPkFront().addEntry(e.getKey(), BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
        filesNames_.clear();
        _d.setMiniPk(new StringMap<int[][]>());
        /*for (String s : _d.getPokedex().getKeys()) {
            String n_ = StringUtil.concat(MINI_IMAGES_FOLDER, SEPARATOR_FILES,
                    s, IMG_FILES_RES_EXT_TXT);
            filesNames_.add(n_);
            _d.getMiniPk().put(s, BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringUtil.concat(common_, n_))));
        }*/
		/*for (EntryCust<String,String> e: Mn.im().entryList()) {
            _d.getMiniPk().addEntry(e.getKey(), BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
        filesNames_.clear();
        _d.setMiniItems(new StringMap<int[][]>());
        /*for (String s : _d.getItems().getKeys()) {
            String n_ = StringUtil.concat(OBJECTS_IMAGES_FOLDER,
                    SEPARATOR_FILES, s, IMG_FILES_RES_EXT_TXT);
            filesNames_.add(n_);
            _d.getMiniItems().put(s, BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringUtil.concat(common_, n_))));
        }*/
		/*for (EntryCust<String,String> e: ItIm.im().entryList()) {
            _d.getMiniItems().addEntry(e.getKey(), BaseSixtyFourUtil.getImageByString(e.getValue()));
        }
        filesNames_.clear();
        _d.setTypesImages(new StringMap<int[][]>());
       /* for (String s : _d.getTypes()) {
            String n_ = StringUtil.concat(TYPES_IMAGES_FOLDER, SEPARATOR_FILES,
                    s, IMG_FILES_RES_EXT_TXT);
            filesNames_.add(n_);
            _d.getTypesImages().put(s, BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringUtil.concat(common_, n_))));
        }*/
		/*for (EntryCust<String,String> e: TypeImg.im().entryList()) {
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
        _d.setImagesTiles(new StringMap<ScreenCoordssInt>());
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
        /*for (TileMiniMap t : _d.getMap().getMiniMap().values()) {
            String f_ = t.getFile();
            String file_ = StringUtil.concat(MINI_MAP_FOLDER, SEPARATOR_FILES,
                    f_);
            _d.getMiniMap().put(f_, BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringUtil.concat(common_, file_))));
        }
        _d.getMiniMap().put(_d.getMap().getUnlockedCity(), BaseSixtyFourUtil
                .getImageByString(ResourceFiles.ressourceFichier(StringUtil
                        .concat(common_, MINI_MAP_FOLDER, SEPARATOR_FILES,
                                _d.getMap().getUnlockedCity()))));*/
        /*_perCentLoading.addPercent(delta_);
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
            ScreenCoordssInt tiles_;
            tiles_ = new ScreenCoordssInt();
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
    }*/

    private static boolean isCorrectIdentifier(String _string) {
        return DataBase.isCorrectIdentifier(_string);
//        if (_string.trim().isEmpty()) {
//            return false;
//        }
//        int len_ = _string.length();
//        for (int i = IndexConstants.SECOND_INDEX; i < len_; i++) {
//            char curr_ = _string.charAt(i);
//            boolean ok_ = false;
//            if (curr_ >= 'a' && curr_ <= 'z') {
//                ok_ = true;
//            }
//            if (curr_ >= 'A' && curr_ <= 'Z') {
//                ok_ = true;
//            }
//            if (curr_ >= '0' && curr_ <= '9') {
//                ok_ = true;
//            }
//            if (curr_ == UNDERSCORE) {
//                ok_ = true;
//            }
//            if (!ok_) {
//                return false;
//            }
//            if (curr_ == UNDERSCORE) {
//                if (i + 1 == len_) {
//                    continue;
//                }
//                if (_string.charAt(i + 1) == UNDERSCORE) {
//                    return false;
//                }
//            }
//        }
//        return true;
    }

    public static Combos getCombos(String _string) {
        Document doc_ = DocumentBuilder.parseNoTextDocument(_string);
        if (doc_ == null) {
            return Instances.newCombos();
        }
        return getCombos(doc_.getDocumentElement());
    }

    private static Combos getCombos(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Combos object_ = Instances.newCombos();
        for (Element c: childElements_) {
            getCombos(object_, c);
        }
        return object_;
    }

    private static void getCombos(Combos _object, Element _element) {
        _object.setEffects(getMapStringListEffectCombo(_element));
    }

    public static PkFileElement<AbilityData> getAbilityData(Document _doc) {
        Element e_ = _doc.getDocumentElement();
        return new PkFileElement<AbilityData>(e_.getAttribute(DocumentReaderCoreUtil.VALUE), getAbilityData(e_));
    }

    private static AbilityData getAbilityData(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        AbilityData object_ = Instances.newAbilityData();
        for (Element c: childElements_) {
            getAbilityData(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getAbilityData(AbilityData _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BREAK_FOE_IMMUNE)) {
            _object.setBreakFoeImmune(getListTypesDuo(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FORBID_USE_BERRY_AGAINST_FOES)) {
            _object.setForbidUseBerryAgainstFoes(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CHGT_TYPE_BY_WEATHER)) {
            _object.setChgtTypeByWeather(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CHGT_TYPE_BY_DAMAGE)) {
            _object.setChgtTypeByDamage(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_RECOIL_DAMAGE_FOE)) {
            _object.setRecoilDamageFoe(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DECREASE_NEC_STEPS_HATCH)) {
            _object.setDecreaseNecStepsHatch(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DIVIDE_STATUS_ROUND)) {
            _object.setDivideStatusRound(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEAL_HP_BY_WEATHER)) {
            _object.setHealHpByWeather(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IGN_ABILITY)) {
            _object.setIgnAbility(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IGN_FOE_TEAM_MOVE)) {
            _object.setIgnFoeTeamMove(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IGN_FOE_STATIS_BOOST)) {
            _object.setIgnFoeStatisBoost(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_MOVE)) {
            _object.setImmuMove(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_LOW_STAT)) {
            _object.setImmuLowStat(getListStatistic(_element));
            return;
        }
        getAbilityData5(_object, _fieldName, _element);
    }

    private static void getAbilityData5(AbilityData _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_LOW_STAT_IF_STATUS)) {
            _object.setImmuLowStatIfStatus(getListStatisticStatus(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_CH)) {
            _object.setImmuCh(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_WEATHER)) {
            _object.setImmuWeather(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_DAMAGE_TRAPPING_MOVES)) {
            _object.setImmuDamageTrappingMoves(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_DAMAGE_ALLY_MOVES)) {
            _object.setImmuDamageAllyMoves(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_DAMAGE_RECOIL)) {
            _object.setImmuDamageRecoil(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_ABILITY)) {
            _object.setImmuAbility(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_STATUS_BEGIN_ROUND)) {
            _object.setImmuStatusBeginRound(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_RECHARGE_ROUND)) {
            _object.setImmuRechargeRound(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_STATUS)) {
            _object.setImmuStatus(DocumentReaderCoreUtil.getStringMapStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SLOWING)) {
            _object.setSlowing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_DAMAGE_FOE)) {
            _object.setMultDamageFoe(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_DAMAGE_CH)) {
            _object.setMultDamageCh(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_ALLY_DAMAGE)) {
            _object.setMultAllyDamage(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_SUFFERED_DAMAGE_SUPER_EFF)) {
            _object.setMultSufferedDamageSuperEff(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getAbilityData4(_object, _fieldName, _element);
    }

    private static void getAbilityData4(AbilityData _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_SUFFERED_DAMAGE_LOW_EFF)) {
            _object.setImmuSufferedDamageLowEff(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_EVT_RATE_CH)) {
            _object.setMultEvtRateCh(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CANCEL_SEC_EFFECT_OTHER)) {
            _object.setCancelSecEffectOther(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CANCEL_SEC_EFFECT_OWNER)) {
            _object.setCancelSecEffectOwner(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_EVT_RATE_SEC_EFFECT_OWNER)) {
            _object.setMultEvtRateSecEffectOwner(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_POWER)) {
            _object.setMultPower(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_DAMAGE)) {
            _object.setMultDamage(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getAbilityData3(_object, _fieldName, _element);
    }

    private static void getAbilityData3(AbilityData _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_STAB)) {
            _object.setMultStab(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BONUS_STAT_RANK)) {
            _object.setBonusStatRank(getMapStatisticByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BOOST_STAT_RANK_PROTECTED)) {
            _object.setBoostStatRankProtected(getMapStatisticByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BOOST_STAT_RANK_END_ROUND)) {
            _object.setBoostStatRankEndRound(getMapStatisticByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_STAT_ALLY)) {
            _object.setMultStatAlly(getMapStatisticRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_STAT_IF_KO_FOE)) {
            _object.setMultStatIfKoFoe(getMapStatisticByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_STAT_IF_LOW_STAT)) {
            _object.setMultStatIfLowStat(getMapStatisticByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_STAT_IF_CAT)) {
            _object.setMultStatIfCat(getMapStatisticCategoryRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_STAT_IF_STATUT_RANK)) {
            _object.setMultStatIfStatutRank(getMapStatisticStatusByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_STAT_IF_DAMAGE_CAT)) {
            _object.setMultStatIfDamageCat(getMapStatisticCategoryByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_STAT_IF_DAMGE_TYPE)) {
            _object.setMultStatIfDamgeType(getMapStatisticTypeByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_STAT)) {
            _object.setMultStat(getMapStatisticString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INFLICTING_DAMAGE_INSTEAD_OF_SUFFERING)) {
            _object.setInflictingDamageInsteadOfSuffering(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_VAR_BOOST)) {
            _object.setMultVarBoost(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NB_USED_PP)) {
            _object.setNbUsedPp(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        getAbilityData2(_object, _fieldName, _element);
    }

    private static void getAbilityData2(AbilityData _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NB_HITS)) {
            _object.setNbHits(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BREAK_PROTECTION)) {
            _object.setBreakProtection(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PLATE)) {
            _object.setPlate(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEALED_STATUS_BY_SWITCH)) {
            _object.setHealedStatusBySwitch(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEALED_HP_RATE_BY_SWITCH)) {
            _object.setHealedHpRateBySwitch(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INCREASED_PRIO)) {
            _object.setIncreasedPrio(DocumentReaderCoreUtil.getStringMapShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INCREASED_PRIO_TYPES)) {
            _object.setIncreasedPrioTypes(DocumentReaderCoreUtil.getStringMapShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MAX_STATISTICS_IF_CH)) {
            _object.setMaxStatisticsIfCh(getListStatistic(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SINGLE_STATUS)) {
            _object.setSingleStatus(DocumentReaderMathUtil.getMonteCarloString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ACHIEVED_DISAPPEARED_PK)) {
            _object.setAchievedDisappearedPk(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FORWARD_STATUS)) {
            _object.setForwardStatus(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FAIL_STATUS)) {
            _object.setFailStatus(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TYPE_FOR_MOVES)) {
            _object.setTypeForMoves(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MAX_HP_FOR_USING_BERRY)) {
            _object.setMaxHpForUsingBerry(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MUMY)) {
            _object.setMumy(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getAbilityData1(_object, _fieldName, _element);
    }

    private static void getAbilityData1(AbilityData _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEAL_HP_BY_TYPE_IF_WEATHER)) {
            _object.setHealHpByTypeIfWeather(getMapWeatherTypeRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_MOVE_TYPES_BY_WEATHER)) {
            _object.setImmuMoveTypesByWeather(DocumentReaderCoreUtil.getStringMapStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EFFECT_END_ROUND)) {
            _object.setEffectEndRound(getListEffectEndRound(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EFFECT_SENDING)) {
            _object.setEffectSending(getListEffectWhileSending(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CHANGING_BOOST_TYPES)) {
            _object.setChangingBoostTypes(getStringMapTypeDamageBoost(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_ALLY_FROM_MOVES)) {
            _object.setImmuAllyFromMoves(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_STATUS_TYPES)) {
            _object.setImmuStatusTypes(DocumentReaderCoreUtil.getStringMapStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_LOW_STATIS_TYPES)) {
            _object.setImmuLowStatisTypes(getStringMapListStatistic(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LOW_STAT_FOE_HIT)) {
            _object.setLowStatFoeHit(getMapStatisticByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_COPY_MOVES_TYPES)) {
            _object.setCopyMovesTypes(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_POWER_MOVES_TYPES_GLOBAL)) {
            _object.setMultPowerMovesTypesGlobal(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_REVERSE_EFFECTS_POWER_MOVES_TYPES_GLOBAL)) {
            _object.setReverseEffectsPowerMovesTypesGlobal(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEAL_HP_WHILE_USING_BERRY)) {
            _object.setHealHpWhileUsingBerry(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TAKE_ITEM_BY_DAMAGING_MOVE)) {
            _object.setTakeItemByDamagingMove(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        _object.setGiveItemToAllyHavingUsed(DocumentReaderCoreUtil.getBoolean(_element));
    }

    private static EffectWhileSendingWithStatistic getEffectWhileSending(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EFFECT_WHILE_SENDING_WITH_STATISTIC)) {
            EffectWhileSendingWithStatistic object_ = Instances.newEffectWhileSendingWithStatistic();
            for (Element c: childElements_) {
                getEffectWhileSendingWithStatistic(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        EffectWhileSendingWithStatistic object_ = Instances.newEffectWhileSendingSimple();
        for (Element c: childElements_) {
            getEffectWhileSending(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getEffectWhileSending(EffectWhileSendingWithStatistic _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DISABLE_WEATHER)) {
            _object.setDisableWeather(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ENABLED_WEATHER)) {
            _object.setEnabledWeather(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_COPYING_ABILITY)) {
            _object.setCopyingAbility(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        _object.setMultWeight(DocumentReaderMathUtil.getRate(_element));
    }

    private static void getEffectWhileSendingWithStatistic(EffectWhileSendingWithStatistic _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EFFECT)) {
            _object.setEffect(getEffectStatistic(_element));
            return;
        }
        getEffectWhileSending(_object, _fieldName, _element);
    }

    private static Statistic getStatistic(Element _elt) {
        return Statistic.getStatisticByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static void getBall(Ball _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CATCHING_RATE)) {
            _object.setCatchingRate(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getItem(_object, _element);
    }

    private static void getBerry(Berry _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEAL_HP_BY_SUPER_EFF_MOVE)) {
            _object.setHealHpBySuperEffMove(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LAW_FOR_ATTACK_FIRST)) {
            _object.setLawForAttackFirst(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_FOES_DAMAGE)) {
            _object.setMultFoesDamage(getStringMapEfficiencyRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_STAT)) {
            _object.setMultStat(getMapStatisticBoostHpRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_WITHOUT_FAIL)) {
            _object.setWithoutFail(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEAL_PP)) {
            _object.setHealPp(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEAL_HP)) {
            _object.setHealHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MAX_HP_HEALING_HP)) {
            _object.setMaxHpHealingHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEAL_STATUS)) {
            _object.setHealStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEAL_HP_RATE)) {
            _object.setHealHpRate(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MAX_HP_HEALING_HP_RATE)) {
            _object.setMaxHpHealingHpRate(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DAMAGE_RATE_RECOIL_FOE)) {
            _object.setDamageRateRecoilFoe(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CATEGORY_BOOSTING)) {
            _object.setCategoryBoosting(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BOOST_STATIS)) {
            _object.setBoostStatis(getMapStatisticByte(_element));
            return;
        }
        getItem(_object, _element);
    }

    private static void getBoost(Boost _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_WIN_PP)) {
            _object.setWinPp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HAPPINESS)) {
            _object.setHappiness(DocumentReaderCoreUtil.getStringMapShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EVS)) {
            _object.setEvs(getMapStatisticShort(_element));
            return;
        }
        getItem(_object, _element);
    }

    private static void getFossil(Fossil _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_POKEMON)) {
            _object.setPokemon(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LEVEL)) {
            _object.setLevel(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        getItem(_object, _element);
    }

    private static void getHealingHp(HealingHp _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HP)) {
            _object.setHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getHealingItem(_object, _fieldName, _element);
    }

    private static void getHealingHpStatus(HealingHpStatus _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEALED_HP_RATE)) {
            _object.setHealedHpRate(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getHealingStatus(_object, _fieldName, _element);
    }

    private static void getHealingItem(HealingItem _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HAPPINESS)) {
            _object.setHappiness(DocumentReaderCoreUtil.getStringMapShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEALING_TEAM)) {
            _object.setHealingTeam(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getItem(_object, _element);
    }

    private static void getHealingPp(HealingPp _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEALED_MOVE_PP)) {
            _object.setHealedMovePp(DocumentReaderCoreUtil.getLong(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEALING_ALL_MOVES_FULLPP)) {
            _object.setHealingAllMovesFullpp(DocumentReaderCoreUtil.getLong(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEALING_ALL_MOVES_PP)) {
            _object.setHealingAllMovesPp(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEALING_MOVE_FULLPP)) {
            _object.setHealingMoveFullpp(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getHealingItem(_object, _fieldName, _element);
    }

    private static void getHealingStatus(HealingStatus _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STATUS)) {
            _object.setStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEALING_KO)) {
            _object.setHealingKo(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getHealingItem(_object, _fieldName, _element);
    }

    public static PkFileElement<Item> getItem(Document _doc) {
        Element e_ = _doc.getDocumentElement();
        return new PkFileElement<Item>(e_.getAttribute(DocumentReaderCoreUtil.VALUE), getItem(e_));
    }

    private static Item getItem(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_BALL)) {
            Ball object_ = Instances.newBall();
            for (Element c: childElements_) {
                getBall(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_BERRY)) {
            Berry object_ = Instances.newBerry();
            for (Element c: childElements_) {
                getBerry(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_BOOST)) {
            Boost object_ = Instances.newBoost();
            for (Element c: childElements_) {
                getBoost(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EVOLVING_ITEM)) {
            EvolvingItem object_ = Instances.newEvolvingItem();
            for (Element c: childElements_) {
                getItem(object_, c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EVOLVING_STONE)) {
            EvolvingStone object_ = Instances.newEvolvingStone();
            for (Element c: childElements_) {
                getItem(object_, c);
            }
            return object_;
        }
        return getItem2(childElements_, tagName_);
    }

    private static Item getItem2(ElementList _childElements, String _tagName) {
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_FOSSIL)) {
            Fossil object_ = Instances.newFossil();
            for (Element c: _childElements) {
                getFossil(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_HEALING_HP)) {
            HealingHp object_ = Instances.newHealingHp();
            for (Element c: _childElements) {
                getHealingHp(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_HEALING_HP_STATUS)) {
            HealingHpStatus object_ = Instances.newHealingHpStatus();
            for (Element c: _childElements) {
                getHealingHpStatus(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_HEALING_PP)) {
            HealingPp object_ = Instances.newHealingPp();
            for (Element c: _childElements) {
                getHealingPp(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        return getItem1(_childElements, _tagName);
    }

    private static Item getItem1(ElementList _childElements, String _tagName) {
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_HEALING_SIMPLE_ITEM)) {
            HealingSimpleItem object_ = Instances.newHealingSimpleItem();
            for (Element c: _childElements) {
                getHealingItem(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_HEALING_SIMPLE_STATUS)) {
            HealingSimpleStatus object_ = Instances.newHealingSimpleStatus();
            for (Element c: _childElements) {
                getHealingStatus(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_REPEL)) {
            Repel object_ = Instances.newRepel();
            for (Element c: _childElements) {
                getRepel(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_SELLING_ITEM)) {
            SellingItem object_ = Instances.newSellingItem();
            for (Element c: _childElements) {
                getItem(object_, c);
            }
            return object_;
        }
        ItemForBattle object_ = Instances.newItemForBattle();
        for (Element c: _childElements) {
            getItemForBattle(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getItem(Item _object, Element _element) {
        _object.setPrice(DocumentReaderCoreUtil.getInteger(_element));
    }

    private static void getItemForBattle(ItemForBattle _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TYPES_PK)) {
            _object.setTypesPk(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CANCEL_IMMU_TYPE)) {
            _object.setCancelImmuType(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_AGAINST_EVO)) {
            _object.setAgainstEvo(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ATTACK_LAST)) {
            _object.setAttackLast(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BOOST_EXP)) {
            _object.setBoostExp(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_STATUS)) {
            _object.setImmuStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_LOW_STATIS)) {
            _object.setImmuLowStatis(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INCREASING_MAX_NB_ROUND_TRAP)) {
            _object.setIncreasingMaxNbRoundTrap(DocumentReaderCoreUtil.getStringMapShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ATTACKS_SOON)) {
            _object.setAttacksSoon(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SYNCHRO_STATUS)) {
            _object.setSynchroStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FAIL_STATUS)) {
            _object.setFailStatus(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PROTECT_AGAINST_KO)) {
            _object.setProtectAgainstKo(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PROTECT_AGAINST_KO_IF_FULL_HP)) {
            _object.setProtectAgainstKoIfFullHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DRAINED_HP_BY_DAMAGE_RATE)) {
            _object.setDrainedHpByDamageRate(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_WIN_EV_FIGHT)) {
            _object.setWinEvFight(getMapStatisticShort(_element));
            return;
        }
        getItemForBattle2(_object, _fieldName, _element);
    }

    private static void getItemForBattle2(ItemForBattle _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LAW_FOR_ATTACK_FIRST)) {
            _object.setLawForAttackFirst(DocumentReaderMathUtil.getMonteCarloBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_TRAPPING_DAMAGE)) {
            _object.setMultTrappingDamage(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_WINNING_HAPPINESS)) {
            _object.setMultWinningHappiness(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_WINNING_EV)) {
            _object.setMultWinningEv(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_WINNING_EXP)) {
            _object.setMultWinningExp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_POWER)) {
            _object.setMultPower(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_DAMAGE)) {
            _object.setMultDamage(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_DRAINED_HP)) {
            _object.setMultDrainedHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DAMAGE_RECOIL)) {
            _object.setDamageRecoil(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_STAT_RANK)) {
            _object.setMultStatRank(getMapStatisticByte(_element));
            return;
        }
        getItemForBattle1(_object, _fieldName, _element);
    }

    private static void getItemForBattle1(ItemForBattle _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_STAT_POKEMON_RANK)) {
            _object.setMultStatPokemonRank(getMapStatisticPokemonByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_STAT)) {
            _object.setMultStat(getMapStatisticString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INCREASING_MAX_NB_ROUND_GLOBAL_MOVE)) {
            _object.setIncreasingMaxNbRoundGlobalMove(DocumentReaderCoreUtil.getStringMapShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INCREASING_MAX_NB_ROUND_TEAM_MOVE)) {
            _object.setIncreasingMaxNbRoundTeamMove(DocumentReaderCoreUtil.getStringMapShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_MOVES)) {
            _object.setImmuMoves(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HATCHING)) {
            _object.setHatching(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_TYPES)) {
            _object.setImmuTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_WEATHER)) {
            _object.setImmuWeather(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BOOST_STATIS_SUPER_EFF)) {
            _object.setBoostStatisSuperEff(getMapStatisticByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BOOST_STATIS_TYPES)) {
            _object.setBoostStatisTypes(getStringMapMapStatisticByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EFFECT_END_ROUND)) {
            _object.setEffectEndRound(getListEffectEndRound(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EFFECT_SENDING)) {
            _object.setEffectSending(getListEffectWhileSending(_element));
            return;
        }
        getItem(_object, _element);
    }

    private static void getRepel(Repel _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STEPS)) {
            _object.setSteps(DocumentReaderCoreUtil.getLong(_element));
            return;
        }
        getItem(_object, _element);
    }

    private static void getDamagingMoveData(DamagingMoveData _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CATEGORY)) {
            _object.setCategory(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DIRECT)) {
            _object.setDirect(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CANNOT_KO)) {
            _object.setCannotKo(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STOPPABLE_MOVE_KO_SINGLE)) {
            _object.setStoppableMoveKoSingle(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getMoveData(_object, _fieldName, _element);
    }

    public static PkFileElement<MoveData> getMoveData(Document _doc) {
        Element e_ = _doc.getDocumentElement();
        return new PkFileElement<MoveData>(e_.getAttribute(DocumentReaderCoreUtil.VALUE), getMoveData(e_));
    }

    private static MoveData getMoveData(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_STATUS_MOVE_DATA)) {
            StatusMoveData object_ = Instances.newStatusMoveData();
            for (Element c: childElements_) {
                getStatusMoveData(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        DamagingMoveData object_ = Instances.newDamagingMoveData();
        for (Element c: childElements_) {
            getDamagingMoveData(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getMoveData(MoveData _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PP)) {
            _object.setPp(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TYPES)) {
            _object.setTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BOOSTED_TYPES)) {
            _object.setBoostedTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PRIORITY)) {
            _object.setPriority(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ACCURACY)) {
            _object.setAccuracy(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EFFECTS)) {
            _object.setEffects(getListEffect(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NB_PREPA_ROUND)) {
            _object.setNbPrepaRound(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DISAPPEAR_BEFORE_USE)) {
            _object.setDisappearBeforeUse(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_REPEAT_ROUND_LAW)) {
            _object.setRepeatRoundLaw(DocumentReaderMathUtil.getMonteCarloNumber(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_RANK_INCREMENT_NB_ROUND)) {
            _object.setRankIncrementNbRound(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_RECHARGE_ROUND)) {
            _object.setRechargeRound(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CONST_USER_CHOICE)) {
            _object.setConstUserChoice(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STOPPABLE_MOVE_SOLO)) {
            _object.setStoppableMoveSolo(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getMoveData1(_object, _fieldName, _element);
    }

    private static void getMoveData1(MoveData _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STOPPABLE_MOVE_MULTI)) {
            _object.setStoppableMoveMulti(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STOPPABLE_MOVE_PRIO)) {
            _object.setStoppableMovePrio(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SEC_EFFECT_IF_NO_DAMAGE)) {
            _object.setSecEffectIfNoDamage(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SEC_EFFECTS_BY_ITEM)) {
            _object.setSecEffectsByItem(DocumentReaderCoreUtil.getStringMapListInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IGN_VAR_ACCUR_USER_NEG)) {
            _object.setIgnVarAccurUserNeg(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IGN_VAR_EVAS_TARGET_POS)) {
            _object.setIgnVarEvasTargetPos(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ACHIEVE_DISAPPEARED_PK_USING_MOVE)) {
            _object.setAchieveDisappearedPkUsingMove(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SWITCH_TYPE)) {
            _object.setSwitchType(getSwitchType(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TYPES_BY_OWNED_ITEM)) {
            _object.setTypesByOwnedItem(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TYPES_BY_WEATHER)) {
            _object.setTypesByWeather(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TARGET_CHOICE)) {
            _object.setTargetChoice(getTargetChoice(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DELETED_STATUS)) {
            _object.setDeletedStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        _object.setRequiredStatus(DocumentReaderCoreUtil.getStringList(_element));
    }

    private static void getStatusMoveData(StatusMoveData _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_THIEVABLE_MOVE)) {
            _object.setThievableMove(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_COUNTERABLE_MOVE)) {
            _object.setCounterableMove(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getMoveData(_object, _fieldName, _element);
    }

    private static Effect getEffect(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EFFECT_ACCURACY)) {
            EffectAccuracy object_ = Instances.newEffectAccuracy();
            for (Element c: childElements_) {
                getEffect(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EFFECT_ALLY)) {
            EffectAlly object_ = Instances.newEffectAlly();
            for (Element c: childElements_) {
                getEffectAlly(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EFFECT_BATON_PASS)) {
            EffectBatonPass object_ = Instances.newEffectBatonPass();
            for (Element c: childElements_) {
                getEffect(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EFFECT_CLONE)) {
            EffectClone object_ = Instances.newEffectClone();
            for (Element c: childElements_) {
                getEffectClone(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EFFECT_COMMON_STATISTICS)) {
            EffectCommonStatistics object_ = Instances.newEffectCommonStatistics();
            for (Element c: childElements_) {
                getEffectCommonStatistics(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        return getEffect7(_element, childElements_, tagName_);
    }

    private static Effect getEffect7(Element _element, ElementList _childElements, String _tagName) {
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_COPY_FIGHTER)) {
            EffectCopyFighter object_ = Instances.newEffectCopyFighter();
            for (Element c: _childElements) {
                getEffectCopyFighter(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_COPY_MOVE)) {
            EffectCopyMove object_ = Instances.newEffectCopyMove();
            for (Element c: _childElements) {
                getEffectCopyMove(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_COUNTER_ATTACK)) {
            EffectCounterAttack object_ = Instances.newEffectCounterAttack();
            for (Element c: _childElements) {
                getEffectCounterAttack(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_DAMAGE)) {
            EffectDamage object_ = Instances.newEffectDamage();
            for (Element c: _childElements) {
                getEffectDamage(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        return getEffect6(_element, _childElements, _tagName);
    }

    private static Effect getEffect6(Element _element, ElementList _childElements, String _tagName) {
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_DAMAGE_RATE)) {
            EffectDamageRate object_ = Instances.newEffectDamageRate();
            for (Element c: _childElements) {
                getEffectDamageRate(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_FULL_HP_RATE)) {
            EffectFullHpRate object_ = Instances.newEffectFullHpRate();
            for (Element c: _childElements) {
                getEffectFullHpRate(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_GLOBAL)) {
            EffectGlobal object_ = Instances.newEffectGlobal();
            for (Element c: _childElements) {
                getEffectGlobal(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_INVOKE)) {
            EffectInvoke object_ = Instances.newEffectInvoke();
            for (Element c: _childElements) {
                getEffectInvoke(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        return getEffect5(_element, _childElements, _tagName);
    }

    private static Effect getEffect5(Element _element, ElementList _childElements, String _tagName) {
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_MULT_SUFFERED_MOVE_POWER)) {
            EffectMultSufferedMovePower object_ = Instances.newEffectMultSufferedMovePower();
            for (Element c: _childElements) {
                getEffectMultSufferedMovePower(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_MULT_USED_MOVE_POWER)) {
            EffectMultUsedMovePower object_ = Instances.newEffectMultUsedMovePower();
            for (Element c: _childElements) {
                getEffectMultUsedMovePower(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_ORDER)) {
            EffectOrder object_ = Instances.newEffectOrder();
            for (Element c: _childElements) {
                getEffectOrder(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_PROTECT_FROM_TYPES)) {
            EffectProtectFromTypes object_ = Instances.newEffectProtectFromTypes();
            for (Element c: _childElements) {
                getEffectProtectFromTypes(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        return getEffect4(_element, _childElements, _tagName);
    }

    private static Effect getEffect4(Element _element, ElementList _childElements, String _tagName) {
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_PROTECTION)) {
            EffectProtection object_ = Instances.newEffectProtection();
            for (Element c: _childElements) {
                getEffectProtection(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_REMAINED_HP_RATE)) {
            EffectRemainedHpRate object_ = Instances.newEffectRemainedHpRate();
            for (Element c: _childElements) {
                getEffectRemainedHpRate(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_RESTRICTION)) {
            EffectRestriction object_ = Instances.newEffectRestriction();
            for (Element c: _childElements) {
                getEffectRestriction(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_STATISTIC)) {
            EffectStatistic object_ = Instances.newEffectStatistic();
            for (Element c: _childElements) {
                getEffectStatistic(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        return getEffect3(_element, _childElements, _tagName);
    }

    private static Effect getEffect3(Element _element, ElementList _childElements, String _tagName) {
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_STATUS)) {
            EffectStatus object_ = Instances.newEffectStatus();
            for (Element c: _childElements) {
                getEffectStatus(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_SWITCH_ABILITIES)) {
            EffectSwitchAbilities object_ = Instances.newEffectSwitchAbilities();
            for (Element c: _childElements) {
                getEffectSwitchAbilities(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_SWITCH_ITEMS)) {
            EffectSwitchItems object_ = Instances.newEffectSwitchItems();
            for (Element c: _childElements) {
                getEffectSwitchItems(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_SWITCH_MOVE_TYPES)) {
            EffectSwitchMoveTypes object_ = Instances.newEffectSwitchMoveTypes();
            for (Element c: _childElements) {
                getEffectSwitchMoveTypes(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        return getEffect2(_element, _childElements, _tagName);
    }

    private static Effect getEffect2(Element _element, ElementList _childElements, String _tagName) {
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_SWITCH_POINT_VIEW)) {
            EffectSwitchPointView object_ = Instances.newEffectSwitchPointView();
            for (Element c: _childElements) {
                getEffectSwitchPointView(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_SWITCH_POSITION)) {
            EffectSwitchPosition object_ = Instances.newEffectSwitchPosition();
            for (Element c: _childElements) {
                getEffect(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_SWITCH_TYPES)) {
            EffectSwitchTypes object_ = Instances.newEffectSwitchTypes();
            for (Element c: _childElements) {
                getEffectSwitchTypes(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_TEAM)) {
            EffectTeam object_ = Instances.newEffectTeam();
            for (Element c: _childElements) {
                getEffectTeam(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        return getEffect1(_element, _childElements, _tagName);
    }

    private static Effect getEffect1(Element _element, ElementList _childElements, String _tagName) {
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_TEAM_WHILE_SEND_FOE)) {
            EffectTeamWhileSendFoe object_ = Instances.newEffectTeamWhileSendFoe();
            for (Element c: _childElements) {
                getEffectTeamWhileSendFoe(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_UNPROTECT_FROM_TYPES)) {
            EffectUnprotectFromTypes object_ = Instances.newEffectUnprotectFromTypes();
            for (Element c: _childElements) {
                getEffectUnprotectFromTypes(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_VAR_P_P)) {
            EffectVarPP object_ = Instances.newEffectVarPP();
            for (Element c: _childElements) {
                getEffectVarPP(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_WIN_MONEY)) {
            EffectWinMoney object_ = Instances.newEffectWinMoney();
            for (Element c: _childElements) {
                getEffectWinMoney(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        return getEffectEndRound(_element);
    }

    private static void getEffect(Effect _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TARGET_CHOICE)) {
            _object.setTargetChoice(getTargetChoice(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FAIL)) {
            _object.setFail(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        _object.setRequiredSuccessfulEffects(DocumentReaderCoreUtil.getListInteger(_element));
    }

    private static void getEffectAlly(EffectAlly _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_ALLY_DAMAGE)) {
            _object.setMultAllyDamage(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectClone(EffectClone _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HP_RATE_CLONE)) {
            _object.setHpRateClone(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static EffectCombo getEffectCombo(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        EffectCombo object_ = Instances.newEffectCombo();
        for (Element c: childElements_) {
            getEffectCombo(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getEffectCombo(EffectCombo _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_EVT_RATE_SEC_EFF)) {
            _object.setMultEvtRateSecEff(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_REPEATED_ROUNDS_LAW)) {
            _object.setRepeatedRoundsLaw(DocumentReaderMathUtil.getMonteCarloNumber(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_RANK_INCREMENT_NB_ROUND)) {
            _object.setRankIncrementNbRound(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EFFECT_END_ROUND)) {
            _object.setEffectEndRound(getListEffectEndRoundFoe(_element));
            return;
        }
        _object.setTeamMove(getListEffectTeam(_element));
    }

    private static void getEffectCommonStatistics(EffectCommonStatistics _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_COMMON_VALUE)) {
            _object.setCommonValue(getMapStatisticString(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectCopyFighter(EffectCopyFighter _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PP_FOR_MOVES)) {
            _object.setPpForMoves(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectCopyMove(EffectCopyMove _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_COPYING_MOVE_FOR_USER)) {
            _object.setCopyingMoveForUser(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_COPYING_MOVE_FOR_USER_DEF)) {
            _object.setCopyingMoveForUserDef(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MOVES_NOT_TO_BE_COPIED)) {
            _object.setMovesNotToBeCopied(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectCounterAttack(EffectCounterAttack _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SUFFERING_DAMAGE_TYPES)) {
            _object.setSufferingDamageTypes(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DROPPED_STAT_DIRECT_MOVE)) {
            _object.setDroppedStatDirectMove(getMapStatisticByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SUFFERING_DAMAGE_DIRECT_MOVE)) {
            _object.setSufferingDamageDirectMove(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PROTECT_FAIL)) {
            _object.setProtectFail(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_COUNTER_FAIL)) {
            _object.setCounterFail(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectDamage(EffectDamage _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CH_RATE)) {
            _object.setChRate(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CONST_DAMAGE)) {
            _object.setConstDamage(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DAMAGE_LAW)) {
            _object.setDamageLaw(DocumentReaderMathUtil.getMonteCarloString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_DAMAGE_AGAINST)) {
            _object.setMultDamageAgainst(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CH_LAW)) {
            _object.setChLaw(DocumentReaderMathUtil.getMonteCarloNumber(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HITS_LAW)) {
            _object.setHitsLaw(DocumentReaderMathUtil.getMonteCarloNumber(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_POWER)) {
            _object.setPower(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_RAND_MAX)) {
            _object.setRandMax(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SUMMING_USER_TEAM_OK_FIGHTER)) {
            _object.setSummingUserTeamOkFighter(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getEffectDamage1(_object, _fieldName, _element);
    }

    private static void getEffectDamage1(EffectDamage _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IGN_VAR_STAT_TARGET_POS)) {
            _object.setIgnVarStatTargetPos(getListStatistic(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IGN_VAR_STAT_USER_NEG)) {
            _object.setIgnVarStatUserNeg(getListStatistic(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_USER_ATTACK)) {
            _object.setUserAttack(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STATIS_ATT)) {
            _object.setStatisAtt(getStatistic(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TARGET_DEFENSE)) {
            _object.setTargetDefense(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STATIS_DEF)) {
            _object.setStatisDef(getStatistic(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BOOST_STATIS_ONCE_KO_FOE)) {
            _object.setBoostStatisOnceKoFoe(getMapStatisticByte(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectDamageRate(EffectDamageRate _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_RATE_DAMAGE)) {
            _object.setRateDamage(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static EffectEndRound getEffectEndRound(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EFFECT_END_ROUND_FOE)) {
            EffectEndRoundFoe object_ = Instances.newEffectEndRoundFoe();
            for (Element c: childElements_) {
                getEffectEndRoundFoe(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EFFECT_END_ROUND_GLOBAL)) {
            EffectEndRoundGlobal object_ = Instances.newEffectEndRoundGlobal();
            for (Element c: childElements_) {
                getEffectEndRound(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EFFECT_END_ROUND_MULTI_RELATION)) {
            EffectEndRoundMultiRelation object_ = Instances.newEffectEndRoundMultiRelation();
            for (Element c: childElements_) {
                getEffectEndRoundMultiRelation(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EFFECT_END_ROUND_POSITION_RELATION)) {
            EffectEndRoundPositionRelation object_ = Instances.newEffectEndRoundPositionRelation();
            for (Element c: childElements_) {
                getEffectEndRoundPositionRelation(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EFFECT_END_ROUND_POSITION_TARGET_RELATION)) {
            EffectEndRoundPositionTargetRelation object_ = Instances.newEffectEndRoundPositionTargetRelation();
            for (Element c: childElements_) {
                getEffectEndRound(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        return getEffectEndRound1(childElements_, tagName_);
    }

    private static EffectEndRound getEffectEndRound1(ElementList _childElements, String _tagName) {
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_END_ROUND_SINGLE_RELATION)) {
            EffectEndRoundSingleRelation object_ = Instances.newEffectEndRoundSingleRelation();
            for (Element c: _childElements) {
                getEffectEndRoundSingleRelation(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_END_ROUND_SINGLE_STATUS)) {
            EffectEndRoundSingleStatus object_ = Instances.newEffectEndRoundSingleStatus();
            for (Element c: _childElements) {
                getEffectEndRoundSingleStatus(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_END_ROUND_STATUS_RELATION)) {
            EffectEndRoundStatusRelation object_ = Instances.newEffectEndRoundStatusRelation();
            for (Element c: _childElements) {
                getEffectEndRoundStatusRelation(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EFFECT_END_ROUND_TEAM)) {
            EffectEndRoundTeam object_ = Instances.newEffectEndRoundTeam();
            for (Element c: _childElements) {
                getEffectEndRoundTeam(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        EffectEndRoundIndividual object_ = Instances.newEffectEndRoundIndividual();
        for (Element c: _childElements) {
            getEffectEndRoundIndividual(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getEffectEndRound(EffectEndRound _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FAIL_END_ROUND)) {
            _object.setFailEndRound(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_END_ROUND_RANK)) {
            _object.setEndRoundRank(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static EffectEndRoundFoe getEffectEndRoundFoe(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        EffectEndRoundFoe object_ = Instances.newEffectEndRoundFoe();
        for (Element c: childElements_) {
            getEffectEndRoundFoe(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getEffectEndRoundFoe(EffectEndRoundFoe _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INFLICTED_RATE_HP_TARGET)) {
            _object.setInflictedRateHpTarget(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffectEndRound(_object, _fieldName, _element);
    }

    private static void getEffectEndRoundIndividual(EffectEndRoundIndividual _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DELETE_ALL_STATUS)) {
            _object.setDeleteAllStatus(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_RECOIL_DAMAGE)) {
            _object.setRecoilDamage(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEAL_HP)) {
            _object.setHealHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEAL_HP_BY_OWNER_TYPES)) {
            _object.setHealHpByOwnerTypes(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_DAMAGE_STATUS)) {
            _object.setMultDamageStatus(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_USER_STATUS_END_ROUND)) {
            _object.setUserStatusEndRound(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getEffectEndRound(_object, _fieldName, _element);
    }

    private static void getEffectEndRoundMultiRelation(EffectEndRoundMultiRelation _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DAMAGE_BY_STATUS)) {
            _object.setDamageByStatus(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        getEffectEndRound(_object, _fieldName, _element);
    }

    private static void getEffectEndRoundPositionRelation(EffectEndRoundPositionRelation _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEAL_HP)) {
            _object.setHealHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffectEndRound(_object, _fieldName, _element);
    }

    private static void getEffectEndRoundSingleRelation(EffectEndRoundSingleRelation _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_RATE_DAMAGE_FUNCTION_OF_NB_ROUNDS)) {
            _object.setRateDamageFunctionOfNbRounds(DocumentReaderMathUtil.getMapLongRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LAW_FOR_ENABLING_EFFECT)) {
            _object.setLawForEnablingEffect(DocumentReaderMathUtil.getMonteCarloNumber(_element));
            return;
        }
        getEffectEndRound(_object, _fieldName, _element);
    }

    private static void getEffectEndRoundSingleStatus(EffectEndRoundSingleStatus _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INCREMENTING_DAMAGE_BY_ROUNDS)) {
            _object.setIncrementingDamageByRounds(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getEffectEndRoundStatus(_object, _fieldName, _element);
    }

    private static EffectEndRoundStatus getEffectEndRoundStatus(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EFFECT_END_ROUND_STATUS_RELATION)) {
            EffectEndRoundStatusRelation object_ = Instances.newEffectEndRoundStatusRelation();
            for (Element c: childElements_) {
                getEffectEndRoundStatusRelation(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        EffectEndRoundSingleStatus object_ = Instances.newEffectEndRoundSingleStatus();
        for (Element c: childElements_) {
            getEffectEndRoundSingleStatus(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getEffectEndRoundStatus(EffectEndRoundStatus _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INFLICTED_RATE_HP_TARGET)) {
            _object.setInflictedRateHpTarget(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffectEndRound(_object, _fieldName, _element);
    }

    private static void getEffectEndRoundStatusRelation(EffectEndRoundStatusRelation _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_THIEVED_HP_RATE_TARGET_TO_USER)) {
            _object.setThievedHpRateTargetToUser(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffectEndRoundStatus(_object, _fieldName, _element);
    }

    private static void getEffectEndRoundTeam(EffectEndRoundTeam _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DELETE_ALL_STATUS)) {
            _object.setDeleteAllStatus(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DELETE_ALL_STATUS_ALLY)) {
            _object.setDeleteAllStatusAlly(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffectEndRound(_object, _fieldName, _element);
    }

    private static void getEffectFullHpRate(EffectFullHpRate _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LEFT_USER_HP)) {
            _object.setLeftUserHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_RESTORED_HP)) {
            _object.setRestoredHp(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CLOSEST_FOE_DAMAGE_RATE_HP)) {
            _object.setClosestFoeDamageRateHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectGlobal(EffectGlobal _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_WEATHER)) {
            _object.setWeather(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CANCELED_IF_USED)) {
            _object.setCanceledIfUsed(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_REVERSE_ORDER_OF_SORT_BY_SPEED)) {
            _object.setReverseOrderOfSortBySpeed(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PUTTING_KO)) {
            _object.setPuttingKo(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_ACCURACY)) {
            _object.setMultAccuracy(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_UNUSABLE_ITEM)) {
            _object.setUnusableItem(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PREVENT_STATUS)) {
            _object.setPreventStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMUNE_TYPES)) {
            _object.setImmuneTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DAMAGE_END_ROUND)) {
            _object.setDamageEndRound(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEALING_END_ROUND)) {
            _object.setHealingEndRound(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEALING_END_ROUND_GROUND)) {
            _object.setHealingEndRoundGround(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EFFICIENCY_MOVES)) {
            _object.setEfficiencyMoves(getMapTypesDuoRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DISABLE_IMMU_AGAINST_TYPES)) {
            _object.setDisableImmuAgainstTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        getEffectGlobal1(_object, _fieldName, _element);
    }

    private static void getEffectGlobal1(EffectGlobal _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CANCEL_PROTECTING_ABILITIES)) {
            _object.setCancelProtectingAbilities(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_UNUSABLE_MOVES)) {
            _object.setUnusableMoves(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_DAMAGE_PREPA_ROUND)) {
            _object.setMultDamagePrepaRound(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MOVES_USED_BY_TARGETED_FIGHTERS)) {
            _object.setMovesUsedByTargetedFighters(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_EFFECT_LOVING_ALLY)) {
            _object.setMultEffectLovingAlly(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_POWER_MOVES)) {
            _object.setMultPowerMoves(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_STAT_IF_CONTAINS_TYPE)) {
            _object.setMultStatIfContainsType(getMapStatisticTypeRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CANCEL_EFFECTS)) {
            _object.setCancelEffects(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_DAMAGE_TYPES_MOVES)) {
            _object.setMultDamageTypesMoves(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CANCEL_CHGT_STAT)) {
            _object.setCancelChgtStat(getListStatistic(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INVOKED_MOVE_TERRAIN)) {
            _object.setInvokedMoveTerrain(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CHANGED_TYPES_TERRAIN)) {
            _object.setChangedTypesTerrain(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectInvoke(EffectInvoke _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MOVE_FCT_ENV)) {
            _object.setMoveFctEnv(getMapEnvironmentTypeString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INVOKING_MOVE_BUT_USER)) {
            _object.setInvokingMoveButUser(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INVOKING_TARGET_CHOSEN_MOVE)) {
            _object.setInvokingTargetChosenMove(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INVOKING_USER_MOVE_WHILE_SLEEP)) {
            _object.setInvokingUserMoveWhileSleep(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INVOKING_ALLY_MOVE)) {
            _object.setInvokingAllyMove(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INVOKING_TARGET_SUCCESFUL_MOVE)) {
            _object.setInvokingTargetSuccesfulMove(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INVOKING_SUFFERED_MOVE)) {
            _object.setInvokingSufferedMove(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INVOKING_MOVE_BY_USER_TYPES)) {
            _object.setInvokingMoveByUserTypes(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MOVES_NOT_TO_BE_INVOKED)) {
            _object.setMovesNotToBeInvoked(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_RATE_INVOKATION_MOVE)) {
            _object.setRateInvokationMove(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectMultSufferedMovePower(EffectMultSufferedMovePower _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_MOVE_POWER_FCT_TYPE)) {
            _object.setMultMovePowerFctType(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectMultUsedMovePower(EffectMultUsedMovePower _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_MOVE_POWER_FCT_TYPE)) {
            _object.setMultMovePowerFctType(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectOrder(EffectOrder _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TARGET_ATTACKS_LAST)) {
            _object.setTargetAttacksLast(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectProtectFromTypes(EffectProtectFromTypes _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMMU_AGAINST_TYPES)) {
            _object.setImmuAgainstTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectProtection(EffectProtection _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PROT_SINGLE)) {
            _object.setProtSingle(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PROT_SINGLE_AGAINST_KO)) {
            _object.setProtSingleAgainstKo(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PROT_TEAM_AGAINST_MULT_TARGETS)) {
            _object.setProtTeamAgainstMultTargets(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PROT_TEAM_AGAINST_PRIO)) {
            _object.setProtTeamAgainstPrio(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PROT_TEAM_AGAINST_STATUS_MOVES)) {
            _object.setProtTeamAgainstStatusMoves(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PROT_TEAM_AGAINST_DAMAGE_MOVES)) {
            _object.setProtTeamAgainstDamageMoves(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectRemainedHpRate(EffectRemainedHpRate _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_RATE_HP)) {
            _object.setRateHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectRestriction(EffectRestriction _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FORBID_TARGET_USING_ITEM)) {
            _object.setForbidTargetUsingItem(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CHOICE_RESTRICTION)) {
            _object.setChoiceRestriction(getMoveChoiceRestrictionType(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static EffectStatistic getEffectStatistic(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        EffectStatistic object_ = Instances.newEffectStatistic();
        for (Element c: childElements_) {
            getEffectStatistic(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getEffectStatistic(EffectStatistic _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STATIS_VAR_RANK)) {
            _object.setStatisVarRank(getMapStatisticByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LOCAL_FAIL_STATIS)) {
            _object.setLocalFailStatis(getMapStatisticString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EVT_RATE)) {
            _object.setEvtRate(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_COPY_BOOST)) {
            _object.setCopyBoost(getListStatistic(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SWAP_BOOST_STATIS)) {
            _object.setSwapBoostStatis(getListStatistic(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LOCAL_FAIL_SWAP_BOOST_STATIS)) {
            _object.setLocalFailSwapBoostStatis(getMapStatisticString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LAW_BOOST)) {
            _object.setLawBoost(getMonteCarloEnumStatistic(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CANCEL_LOW_STAT)) {
            _object.setCancelLowStat(getListStatistic(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CANCEL_CHGT_STAT)) {
            _object.setCancelChgtStat(getListStatistic(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectStatus(EffectStatus _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LAW_STATUS)) {
            _object.setLawStatus(DocumentReaderMathUtil.getMonteCarloString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DELETED_STATUS)) {
            _object.setDeletedStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LOCAL_FAIL_STATUS)) {
            _object.setLocalFailStatus(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_KO_USER_HEAL_SUBST)) {
            _object.setKoUserHealSubst(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STATUS_FROM_USER)) {
            _object.setStatusFromUser(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectSwitchAbilities(EffectSwitchAbilities _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EXCHANGE_ABILITY)) {
            _object.setExchangeAbility(getExchangeType(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CONST_ABILITY)) {
            _object.setConstAbility(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectSwitchItems(EffectSwitchItems _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MOVE_OBJECT)) {
            _object.setMoveObject(getMoveItemType(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectSwitchMoveTypes(EffectSwitchMoveTypes _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CHANGE_TYPES)) {
            _object.setChangeTypes(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_REPLACING_TYPES)) {
            _object.setReplacingTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectSwitchPointView(EffectSwitchPointView _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_POINT_VIEW_CHANGEMENT)) {
            _object.setPointViewChangement(getPointViewChangementType(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectSwitchTypes(EffectSwitchTypes _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CHGT_TYPE_BY_ENV)) {
            _object.setChgtTypeByEnv(getMapEnvironmentTypeString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CONST_VALUES_TYPE)) {
            _object.setConstValuesType(getConstValuesType(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EXCHANGE_TYPES)) {
            _object.setExchangeTypes(getExchangeType(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CONST_TYPES)) {
            _object.setConstTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ADDED_TYPES)) {
            _object.setAddedTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static EffectTeam getEffectTeam(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        EffectTeam object_ = Instances.newEffectTeam();
        for (Element c: childElements_) {
            getEffectTeam(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getEffectTeam(EffectTeam _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FORBIDDING_HEALING)) {
            _object.setForbiddingHealing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FORBIDDEN_BOOST)) {
            _object.setForbiddenBoost(getListStatistic(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_UNUSABLE_MOVES)) {
            _object.setUnusableMoves(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CANCEL_CHGT_STAT_FOE_TEAM)) {
            _object.setCancelChgtStatFoeTeam(getListStatistic(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CANCEL_CHGT_STAT_TEAM)) {
            _object.setCancelChgtStatTeam(getListStatistic(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_DAMAGE)) {
            _object.setMultDamage(getMapCategoryMultRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_STATISTIC)) {
            _object.setMultStatistic(getMapStatisticRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_STATISTIC_FOE)) {
            _object.setMultStatisticFoe(getMapStatisticRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PROTECT_AGAINST_LOW_STAT)) {
            _object.setProtectAgainstLowStat(getListStatistic(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PROTECT_AGAINST_CH)) {
            _object.setProtectAgainstCh(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PROTECT_AGAINST_STATUS)) {
            _object.setProtectAgainstStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DISABLE_FOE_TEAM_EFFECTS)) {
            _object.setDisableFoeTeamEffects(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DISABLE_FOE_TEAM_STATUS)) {
            _object.setDisableFoeTeamStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectTeamWhileSendFoe(EffectTeamWhileSendFoe _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FAIL_SENDING)) {
            _object.setFailSending(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STATUS_BY_NB_USES)) {
            _object.setStatusByNbUses(DocumentReaderCoreUtil.getMapShortString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DELETED_BY_FOE_TYPES)) {
            _object.setDeletedByFoeTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DAMAGE_RATE_AGAINST_FOE)) {
            _object.setDamageRateAgainstFoe(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STATISTICS)) {
            _object.setStatistics(getMapStatisticByte(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectUnprotectFromTypes(EffectUnprotectFromTypes _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TYPES)) {
            _object.setTypes(getListTypesDuo(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DISABLE_IMMU_AGAINST_TYPES)) {
            _object.setDisableImmuAgainstTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DISABLE_IMMU_FROM_MOVES)) {
            _object.setDisableImmuFromMoves(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ATTACK_TARGET_WITH_TYPES)) {
            _object.setAttackTargetWithTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectVarPP(EffectVarPP _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DELETE_PP)) {
            _object.setDeletePp(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectWinMoney(EffectWinMoney _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_WINNING_RATE_BY_SUM_TARGET_USER)) {
            _object.setWinningRateBySumTargetUser(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static ConstValuesType getConstValuesType(Element _elt) {
        return ConstValuesType.getConstValuesTypeByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static ExchangeType getExchangeType(Element _elt) {
        return ExchangeType.getExchangeTypeByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static MoveChoiceRestrictionType getMoveChoiceRestrictionType(Element _elt) {
        return MoveChoiceRestrictionType.getMoveChoiceRestrictionTypeByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static MoveItemType getMoveItemType(Element _elt) {
        return MoveItemType.getMoveItemTypeTypeByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static PointViewChangementType getPointViewChangementType(Element _elt) {
        return PointViewChangementType.getPointViewChangementTypeByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static SwitchType getSwitchType(Element _elt) {
        return SwitchType.getSwitchTypeByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static TargetChoice getTargetChoice(Element _elt) {
        return TargetChoice.getTargetChoiceByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    public static PkFileElement<PokemonData> getPokemonData(Document _doc) {
        Element e_ = _doc.getDocumentElement();
        return new PkFileElement<PokemonData>(e_.getAttribute(DocumentReaderCoreUtil.VALUE), getPokemonData(e_));
    }

    private static PokemonData getPokemonData(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        PokemonData object_ = Instances.newPokemonData();
        for (Element c: childElements_) {
            getPokemonData(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getPokemonData(PokemonData _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_WEIGHT)) {
            _object.setWeight(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TYPES)) {
            _object.setTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STATISTICS)) {
            _object.setStatistics(getMapStatisticStatBaseEv(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LEV_MOVES)) {
            _object.setLevMoves(getListLevelMove(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_GENDER_REP)) {
            _object.setGenderRep(getGenderRepartition(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ABILITIES)) {
            _object.setAbilities(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MOVE_TUTORS)) {
            _object.setMoveTutors(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HIDDEN_MOVES)) {
            _object.setHiddenMoves(DocumentReaderCoreUtil.getListShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TECHNICAL_MOVES)) {
            _object.setTechnicalMoves(DocumentReaderCoreUtil.getListShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BASE_EVO)) {
            _object.setBaseEvo(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EVOLUTIONS)) {
            _object.setEvolutions(getStringMapEvolution(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CATCHING_RATE)) {
            _object.setCatchingRate(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEIGHT)) {
            _object.setHeight(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EXP_EVO)) {
            _object.setExpEvo(getExpType(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EXP_RATE)) {
            _object.setExpRate(DocumentReaderCoreUtil.getLong(_element));
            return;
        }
        getPokemonData1(_object, _fieldName, _element);
    }

    private static void getPokemonData1(PokemonData _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EGG_GROUPS)) {
            _object.setEggGroups(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HATCHING_STEPS)) {
            _object.setHatchingSteps(DocumentReaderMathUtil.getLgInt(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HAPPINESS)) {
            _object.setHappiness(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        _object.setHappinessHatch(DocumentReaderCoreUtil.getShort(_element));
    }

    private static ExpType getExpType(Element _elt) {
        return ExpType.getExpTypeByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    public static GenderRepartition getGenderRepartition(Element _elt) {
        return GenderRepartition.getGenderRepartitionByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static Evolution getEvolution(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EVOLUTION_ITEM)) {
            EvolutionItem object_ = Instances.newEvolutionItem();
            for (Element c: childElements_) {
                getEvolutionItem(object_, c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EVOLUTION_LEVEL_GENDER)) {
            EvolutionLevelGender object_ = Instances.newEvolutionLevelGender();
            for (Element c: childElements_) {
                getEvolutionLevelGender(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EVOLUTION_LEVEL_SIMPLE)) {
            EvolutionLevelSimple object_ = Instances.newEvolutionLevelSimple();
            for (Element c: childElements_) {
                getEvolutionLevel(object_, c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EVOLUTION_MOVE)) {
            EvolutionMove object_ = Instances.newEvolutionMove();
            for (Element c: childElements_) {
                getEvolutionMove(object_, c);
            }
            return object_;
        }
        return getEvolution1(childElements_, tagName_);
    }

    private static Evolution getEvolution1(ElementList _childElements, String _tagName) {
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EVOLUTION_MOVE_TYPE)) {
            EvolutionMoveType object_ = Instances.newEvolutionMoveType();
            for (Element c: _childElements) {
                getEvolutionMoveType(object_, c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EVOLUTION_STONE_GENDER)) {
            EvolutionStoneGender object_ = Instances.newEvolutionStoneGender();
            for (Element c: _childElements) {
                getEvolutionStoneGender(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EVOLUTION_STONE_SIMPLE)) {
            EvolutionStoneSimple object_ = Instances.newEvolutionStoneSimple();
            for (Element c: _childElements) {
                getEvolutionStone(object_, c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_EVOLUTION_TEAM)) {
            EvolutionTeam object_ = Instances.newEvolutionTeam();
            for (Element c: _childElements) {
                getEvolutionTeam(object_, c);
            }
            return object_;
        }
        return Instances.newEvolutionHappiness();
    }

    private static void getEvolutionItem(EvolutionItem _object, Element _element) {
        _object.setItem(DocumentReaderCoreUtil.getString(_element));
    }

    private static void getEvolutionLevel(EvolutionLevel _object, Element _element) {
        _object.setLevel(DocumentReaderCoreUtil.getShort(_element));
    }

    private static void getEvolutionLevelGender(EvolutionLevelGender _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_GENDER)) {
            _object.setGender(getGender(_element));
            return;
        }
        getEvolutionLevel(_object, _element);
    }

    private static void getEvolutionMove(EvolutionMove _object, Element _element) {
        _object.setMove(DocumentReaderCoreUtil.getString(_element));
    }

    private static void getEvolutionMoveType(EvolutionMoveType _object, Element _element) {
        _object.setType(DocumentReaderCoreUtil.getString(_element));
    }

    private static void getEvolutionStone(EvolutionStone _object, Element _element) {
        _object.setStone(DocumentReaderCoreUtil.getString(_element));
    }

    private static void getEvolutionStoneGender(EvolutionStoneGender _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_GENDER)) {
            _object.setGender(getGender(_element));
            return;
        }
        getEvolutionStone(_object, _element);
    }

    private static void getEvolutionTeam(EvolutionTeam _object, Element _element) {
        _object.setPokemon(DocumentReaderCoreUtil.getString(_element));
    }

    public static PkFileElement<Status> getStatus(Document _doc) {
        Element e_ = _doc.getDocumentElement();
        return new PkFileElement<Status>(e_.getAttribute(DocumentReaderCoreUtil.VALUE), getStatus(e_));
    }

    private static Status getStatus(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_STATUS_BEGIN_ROUND_AUTO_DAMAGE)) {
            StatusBeginRoundAutoDamage object_ = Instances.newStatusBeginRoundAutoDamage();
            for (Element c: childElements_) {
                getStatusBeginRoundAutoDamage(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_STATUS_BEGIN_ROUND_SIMPLE)) {
            StatusBeginRoundSimple object_ = Instances.newStatusBeginRoundSimple();
            for (Element c: childElements_) {
                getStatusBeginRound(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        StatusSimple object_ = Instances.newStatusSimple();
        for (Element c: childElements_) {
            getStatus(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getStatus(Status _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STATUS_TYPE)) {
            _object.setStatusType(getStatusType(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CATCHING_RATE)) {
            _object.setCatchingRate(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EFFECT_END_ROUND)) {
            _object.setEffectEndRound(getListEffectEndRoundStatus(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EFFECTS_PARTNER)) {
            _object.setEffectsPartner(getListEffectPartnerStatus(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DISABLED_EFF_IF_SWITCH)) {
            _object.setDisabledEffIfSwitch(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INCREMENT_END_ROUND)) {
            _object.setIncrementEndRound(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INCREMENTING_END_ROUND)) {
            _object.setIncrementingEndRound(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_STAT)) {
            _object.setMultStat(getMapStatisticRate(_element));
            return;
        }
        _object.setFail(DocumentReaderCoreUtil.getString(_element));
    }

    private static void getStatusBeginRound(StatusBeginRound _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LAW_FOR_USING_A_MOVE)) {
            _object.setLawForUsingAMove(DocumentReaderMathUtil.getMonteCarloBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LAW_FOR_USING_A_MOVE_NB_ROUND)) {
            _object.setLawForUsingAMoveNbRound(DocumentReaderMathUtil.getMonteCarloNumber(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LAW_FOR_USING_A_MOVE_IF_FOE)) {
            _object.setLawForUsingAMoveIfFoe(DocumentReaderMathUtil.getMonteCarloBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LAW_FOR_FULL_HEAL_IF_MOVE)) {
            _object.setLawForFullHealIfMove(DocumentReaderMathUtil.getMonteCarloBoolean(_element));
            return;
        }
        getStatus(_object, _fieldName, _element);
    }

    private static void getStatusBeginRoundAutoDamage(StatusBeginRoundAutoDamage _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_POWER)) {
            _object.setPower(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ATTACK)) {
            _object.setAttack(getStatistic(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DEFENSE)) {
            _object.setDefense(getStatistic(_element));
            return;
        }
        getStatusBeginRound(_object, _fieldName, _element);
    }

    private static StatusType getStatusType(Element _elt) {
        return StatusType.getStatusTypeByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static EffectPartnerStatus getEffectPartnerStatus(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        EffectPartnerStatus object_ = Instances.newEffectPartnerStatus();
        for (Element c: childElements_) {
            getEffectPartnerStatus(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getEffectPartnerStatus(EffectPartnerStatus _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_DAMAGE_AGAINST_FOE)) {
            _object.setMultDamageAgainstFoe(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_WEDDING_ALLY)) {
            _object.setWeddingAlly(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        _object.setRestoredHpRateLovedAlly(DocumentReaderMathUtil.getRate(_element));
    }

    private static BoostHpRate getBoostHpRate(Element _elt) {
        return BoostHpRate.newBoostHpRate(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static CategoryMult getCategoryMult(Element _elt) {
        return CategoryMult.newCategoryMult(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static EfficiencyRate getEfficiencyRate(Element _elt) {
        return EfficiencyRate.newEfficiencyRate(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static LevelMove getLevelMove(Element _elt) {
        return LevelMove.newLevelMove(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static StatBaseEv getStatBaseEv(Element _elt) {
        return StatBaseEv.newStatBaseEv(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static StatisticCategory getStatisticCategory(Element _elt) {
        return StatisticCategory.newStatisticCategory(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static StatisticPokemon getStatisticPokemon(Element _elt) {
        return StatisticPokemon.newStatisticPokemon(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static StatisticStatus getStatisticStatus(Element _elt) {
        return StatisticStatus.newStatisticStatus(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static StatisticType getStatisticType(Element _elt) {
        return StatisticType.newStatisticType(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static TypeDamageBoost getTypeDamageBoost(Element _elt) {
        return TypeDamageBoost.newTypeDamageBoost(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static TypesDuo getTypesDuo(Element _elt) {
        return TypesDuo.newTypesDuo(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static WeatherType getWeatherType(Element _elt) {
        return WeatherType.newWeatherType(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    public static Game getGame(String _string, SexListInt _sexListInt) {
        return getGameOrNull(DocumentBuilder.parseNoTextDocument(_string),_sexListInt);
    }

    public static Game getGameOrNull(Document _string, SexListInt _sexList) {
        if (_string == null) {
            return null;
        }
        Element documentElement_ = _string.getDocumentElement();
        String tagName_ = documentElement_.getTagName();
        if (!StringUtil.quickEq(tagName_, MAIN_TAG) || !StringUtil.quickEq(documentElement_.getAttribute(DocumentWriterCoreUtil.FIELD), GAME)) {
            return null;
        }
        return getGame(_string.getDocumentElement(),_sexList);
    }

    private static Game getGame(Element _element, SexListInt _sexList) {
        ElementList childElements_ = _element.getChildElements();
        Game object_ = Instances.newGame();
        for (Element c: childElements_) {
            getGame(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c,_sexList);
        }
        return object_;
    }

    private static void getGame(Game _object, String _fieldName, Element _element, SexListInt _sexList) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ZIPPED_ROM)) {
            _object.setZippedRom(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PLAYER)) {
            _object.setPlayer(getPlayer(_element,_sexList));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_RANK_LEAGUE)) {
            _object.setRankLeague(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BEAT_GYM_TRAINER)) {
            _object.setBeatGymTrainer(getMapShortListPoint(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BEAT_GYM_LEADER)) {
            _object.setBeatGymLeader(getMapCoordsBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BEAT_TRAINER)) {
            _object.setBeatTrainer(getMapNbFightCoordsBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TAKEN_OBJECTS)) {
            _object.setTakenObjects(getMapCoordsBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TAKEN_POKEMON)) {
            _object.setTakenPokemon(getMapCoordsBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PLAYER_COORDS)) {
            _object.setPlayerCoords(getCoords(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PLAYER_ORIENTATION)) {
            _object.setPlayerOrientation(getDirection(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HOSTED_PK)) {
            _object.setHostedPk(getMapCoordsHostPokemonDuo(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FIGHT)) {
            _object.setFight(getFight(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DIFFICULTY)) {
            _object.setDifficulty(getDifficulty(_element));
            return;
        }
        getGame1(_object, _fieldName, _element);
    }

    private static void getGame1(Game _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INDEX_PERIOD_FISHING)) {
            _object.setIndexPeriodFishing(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INDEX_PERIOD)) {
            _object.setIndexPeriod(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INDEX_STEP)) {
            _object.setIndexStep(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        _object.setVisitedPlaces(getMapCoordsBoolean(_element));
    }

    private static HostPokemonDuo getHostPokemonDuo(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        HostPokemonDuo object_ = Instances.newHostPokemonDuo();
        for (Element c: childElements_) {
            getHostPokemonDuo(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getHostPokemonDuo(HostPokemonDuo _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FIRST_POKEMON)) {
            _object.setFirstPokemon(getPokemonPlayer(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SECOND_POKEMON)) {
            _object.setSecondPokemon(getPokemonPlayer(_element));
            return;
        }
        _object.setNbSteps(DocumentReaderCoreUtil.getInteger(_element));
    }

    private static NbFightCoords getNbFightCoords(Element _elt) {
        return NbFightCoords.newNbFightCoords(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static UsesOfMove getUsesOfMove(Element _elt) {
        return UsesOfMove.newUsesOfMove(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static ActivityOfMove getActivityOfMove(Element _elt) {
        return ActivityOfMove.newActivityOfMove(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static Anticipation getAnticipation(Element _elt) {
        return Anticipation.newAnticipation(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static ChoiceOfEvolutionAndMoves getChoiceOfEvolutionAndMoves(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        ChoiceOfEvolutionAndMoves object_ = Instances.newChoiceOfEvolutionAndMoves();
        for (Element c: childElements_) {
            getChoiceOfEvolutionAndMoves(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getChoiceOfEvolutionAndMoves(ChoiceOfEvolutionAndMoves _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_KEPT_MOVES)) {
            _object.setKeptMoves(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        _object.setAbility(DocumentReaderCoreUtil.getString(_element));
    }

    private static Fight getFight(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Fight object_ = Instances.newFight();
        for (Element c: childElements_) {
            getFight(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getFight(Fight _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FIGHT_TYPE)) {
            _object.setFightType(getFightType(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ENV_TYPE)) {
            _object.setEnvType(getEnvironmentType(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT)) {
            _object.setMult(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PLAYER_MAX_NUMBER_FRONT_FIGHTERS)) {
            _object.setPlayerMaxNumberFrontFighters(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ENABLED_MOVES)) {
            _object.setEnabledMoves(getStringMapActivityOfMove(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STILL_ENABLED_MOVES)) {
            _object.setStillEnabledMoves(DocumentReaderCoreUtil.getStringMapBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TEAMS)) {
            _object.setTeams(getMapByteTeam(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NB_FLEE_ATTEMPT)) {
            _object.setNbFleeAttempt(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NB_ROUNDS)) {
            _object.setNbRounds(DocumentReaderMathUtil.getLgInt(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_WINNING_MONEY)) {
            _object.setWinningMoney(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getFight1(_object, _fieldName, _element);
    }

    private static void getFight1(Fight _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CATCHING_BALL)) {
            _object.setCatchingBalls(getListCatchingBallFoeAction(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CURRENT_USER)) {
            _object.setCurrentUser(getTeamPosition(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STATE)) {
            _object.setState(getFightState(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_USED_ITEMS_WHILE_ROUND)) {
            _object.setUsedItemsWhileRound(DocumentReaderCoreUtil.getStringMapShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FIRST_POSIT_PLAYER_FIGHTERS)) {
            _object.setFirstPositPlayerFighters(DocumentReaderCoreUtil.getMapByteByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FIRST_POSIT_FOE_FIGHTERS)) {
            _object.setFirstPositFoeFighters(DocumentReaderCoreUtil.getMapByteByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ALLY_CHOICE)) {
            _object.setAllyChoice(getMapMoveTargetMoveTarget(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LOST_OBJECTS)) {
            _object.setLostObjects(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CHOICES)) {
            _object.setChoices(getMapByteChoiceOfEvolutionAndMoves(_element));
            return;
        }
        _object.setCaughtEvolutions(DocumentReaderCoreUtil.getStringList(_element));
    }

    private static Fighter getFighter(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Fighter object_ = Instances.newFighter();
        for (Element c: childElements_) {
            getFighter(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getFighter(Fighter _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NICKNAME)) {
            _object.setNickname(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_GENDER)) {
            _object.setGender(getGender(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_WEIGHT)) {
            _object.setWeight(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEIGHT)) {
            _object.setHeight(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CURRENT_NAME)) {
            _object.setCurrentName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CURRENT_GENDER)) {
            _object.setCurrentGender(getGender(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LAST_USED_ITEM)) {
            _object.setLastUsedItem(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ITEM)) {
            _object.setItem(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EXP_ITEM)) {
            _object.setExpItem(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ABILITY)) {
            _object.setAbility(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CURRENT_ABILITY)) {
            _object.setCurrentAbility(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STATUS)) {
            _object.setStatus(DocumentReaderCoreUtil.getStringMapShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STATUS_RELAT)) {
            _object.setStatusRelat(getMapMoveTeamPositionShort(_element));
            return;
        }
        getFighter4(_object, _fieldName, _element);
    }

    private static void getFighter4(Fighter _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NB_ROUNDS)) {
            _object.setNbRounds(DocumentReaderMathUtil.getLgInt(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TYPES)) {
            _object.setTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MOVES)) {
            _object.setMoves(getStringMapUsesOfMove(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CURRENT_MOVES)) {
            _object.setCurrentMoves(getStringMapUsesOfMove(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EV)) {
            _object.setEv(getMapStatisticShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STATIS_BASE)) {
            _object.setStatisBase(getMapStatisticRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STATIS_BOOST)) {
            _object.setStatisBoost(getMapStatisticByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_REMAINING_HP)) {
            _object.setRemainingHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CLONE)) {
            _object.setClone(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ENABLED_MOVES)) {
            _object.setEnabledMoves(getStringMapActivityOfMove(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ENABLED_MOVES_PROT)) {
            _object.setEnabledMovesProt(getStringMapActivityOfMove(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PROTECTED_AGAINST_MOVE_TYPES)) {
            _object.setProtectedAgainstMoveTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ENABLED_MOVES_UNPROT)) {
            _object.setEnabledMovesUnprot(getStringMapActivityOfMove(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ENABLED_MOVES_END_ROUND)) {
            _object.setEnabledMovesEndRound(getStringMapActivityOfMove(_element));
            return;
        }
        getFighter3(_object, _fieldName, _element);
    }

    private static void getFighter3(Fighter _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ENABLED_MOVES_CONST_CHOICES)) {
            _object.setEnabledMovesConstChoices(getStringMapActivityOfMove(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ENABLED_CHANGING_TYPES_MOVES)) {
            _object.setEnabledChangingTypesMoves(getStringMapActivityOfMove(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ENABLED_COUNTERING_MOVES)) {
            _object.setEnabledCounteringMoves(getStringMapActivityOfMove(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ENABLED_MOVES_FOR_ALLY)) {
            _object.setEnabledMovesForAlly(DocumentReaderCoreUtil.getStringMapBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DAMAGE_RATE_INFLICTED_BY_TYPE)) {
            _object.setDamageRateInflictedByType(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DAMAGE_RATE_SUFFERED_BY_TYPE)) {
            _object.setDamageRateSufferedByType(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ACTED)) {
            _object.setActed(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_GROUND_PLACE)) {
            _object.setGroundPlace(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_GROUND_PLACE_SUBST)) {
            _object.setGroundPlaceSubst(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        getFighter2(_object, _fieldName, _element);
    }

    private static void getFighter2(Fighter _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_WON_EXP)) {
            _object.setWonExp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_WON_EXP_SINCE_LAST_LEVEL)) {
            _object.setWonExpSinceLastLevel(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LEVEL)) {
            _object.setLevel(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HAPPINESS)) {
            _object.setHappiness(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_USED_BALL_CATCHING)) {
            _object.setUsedBallCatching(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INCR_USER_ACCURACY)) {
            _object.setIncrUserAccuracy(getMapMoveTeamPositionBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NB_USES_MOVES)) {
            _object.setNbUsesMoves(DocumentReaderCoreUtil.getStringMapInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NB_PREPA_ROUND)) {
            _object.setNbPrepaRound(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DISAPPEARED)) {
            _object.setDisappeared(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NEEDING_TO_RECHARGE)) {
            _object.setNeedingToRecharge(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TRACKING_MOVES)) {
            _object.setTrackingMoves(getMapMoveTeamPositionAffectedMove(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TRAPPING_MOVES)) {
            _object.setTrappingMoves(getMapMoveTeamPositionActivityOfMove(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LAST_SUFFERED_MOVE)) {
            _object.setLastSufferedMove(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LAST_SUFFERED_MOVE_TYPES)) {
            _object.setLastSufferedMoveTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DAMAGE_SUFFERED_CATEG)) {
            _object.setDamageSufferedCateg(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        getFighter1(_object, _fieldName, _element);
    }

    private static void getFighter1(Fighter _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DAMAGE_SUFFERED_CATEG_ROUND)) {
            _object.setDamageSufferedCategRound(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LAST_USED_MOVE)) {
            _object.setLastUsedMove(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_USED_MOVE_LAST_ROUND)) {
            _object.setUsedMoveLastRound(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ALREADY_INVOKED_MOVES_ROUND)) {
            _object.setAlreadyInvokedMovesRound(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LAST_SUCCESSFUL_MOVE)) {
            _object.setLastSuccessfulMove(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_COPIED_MOVES)) {
            _object.setCopiedMoves(getStringMapCopiedMove(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NB_REPEATING_SUCCESSFUL_MOVES)) {
            _object.setNbRepeatingSuccessfulMoves(DocumentReaderMathUtil.getLgInt(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_USING_ITEM)) {
            _object.setUsingItem(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SUCCESSFUL_MOVE)) {
            _object.setSuccessfulMove(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CHANGED)) {
            _object.setChanged(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PRIVATE_MOVES)) {
            _object.setPrivateMoves(getMapMoveTeamPositionStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BELONGING_TO_PLAYER)) {
            _object.setBelongingToPlayer(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ACTION)) {
            _object.setAction(getAbstractAction(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MOVES_TO_BE_LEARNT)) {
            _object.setMovesToBeLearnt(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        _object.setMovesAbilitiesEvos(getStringMapMovesAbilities(_element));
    }

    private static CatchingBallFoeAction getCatchingBallFoeAction(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        CatchingBallFoeAction object_ = Instances.newCatchingBallFoeAction();
        for (Element c: childElements_) {
            getCatchingBallFoeAction(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getCatchingBallFoeAction(CatchingBallFoeAction _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CATCH_BALL)) {
            _object.setCatchingBall(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CATCH_NICKNAME_CATCH)) {
            _object.setNickname(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CATCH_PLAYER)) {
            _object.setPlayer(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CATCH_TEAM)) {
            _object.setTeam(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        _object.setCaught(DocumentReaderCoreUtil.getBoolean(_element));
    }

    private static MoveTeamPosition getMoveTeamPosition(Element _elt) {
        return MoveTeamPosition.newMoveTeamPosition(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static StacksOfUses getStacksOfUses(Element _elt) {
        return StacksOfUses.newStacksOfUses(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static TargetCoords getTargetCoords(Element _elt) {
        return TargetCoords.newTargetCoords(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static Team getTeam(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Team object_ = Instances.newTeam();
        for (Element c: childElements_) {
            getTeam(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getTeam(Team _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ENABLED_MOVES_BY_GROUP)) {
            _object.setEnabledMovesByGroup(getMapStringListActivityOfMove(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ENABLED_MOVES)) {
            _object.setEnabledMoves(getStringMapActivityOfMove(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ENABLED_MOVES_WHILE_SENDING_FOE)) {
            _object.setEnabledMovesWhileSendingFoe(DocumentReaderCoreUtil.getStringMapBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ENABLED_MOVES_WHILE_SENDING_FOE_USES)) {
            _object.setEnabledMovesWhileSendingFoeUses(DocumentReaderMathUtil.getStringMapLgInt(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NB_USES_MOVES)) {
            _object.setNbUsesMoves(DocumentReaderCoreUtil.getStringMapInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NB_USES_MOVES_ROUND)) {
            _object.setNbUsesMovesRound(DocumentReaderCoreUtil.getStringMapInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEAL_AFTER)) {
            _object.setHealAfter(getStringMapMapByteStacksOfUses(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MOVES_ANTICIPATION)) {
            _object.setMovesAnticipation(getStringMapMapByteAnticipation(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MEMBERS)) {
            _object.setMembers(getMapByteFighter(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PLAYER_FIGHTERS_AGAINST_FOE)) {
            _object.setPlayerFightersAgainstFoe(DocumentReaderCoreUtil.getMapByteListByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NB_KO_ROUND)) {
            _object.setNbKoRound(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NB_KO_PREVIOUS_ROUND)) {
            _object.setNbKoPreviousRound(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        _object.setSuccessfulMovesRound(DocumentReaderCoreUtil.getStringList(_element));
    }

    private static TeamPosition getTeamPosition(Element _elt) {
        return TeamPosition.newTeamPosition(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static AbstractAction getAbstractAction(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_ACTION_HEAL_MOVE)) {
            ActionHealMove object_ = Instances.newActionHealMove();
            for (Element c: childElements_) {
                getActionHealMove(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_ACTION_MOVE)) {
            ActionMove object_ = Instances.newActionMove();
            for (Element c: childElements_) {
                getActionMove(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_ACTION_SIMPLE_HEAL)) {
            ActionSimpleHeal object_ = Instances.newActionSimpleHeal();
            for (Element c: childElements_) {
                getActionSimpleHeal(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_ACTION_SWITCH)) {
            ActionSwitch object_ = Instances.newActionSwitch();
            for (Element c: childElements_) {
                getActionSwitch(object_, c);
            }
            return object_;
        }
        return null;
    }

    private static void getActionHeal(ActionHeal _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CHOSEN_HEALING_ITEM)) {
            _object.setChosenHealingItem(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        _object.setTeam(DocumentReaderCoreUtil.getBoolean(_element));
    }

    private static void getActionHealMove(ActionHealMove _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FIRST_CHOSEN_MOVE)) {
            _object.setFirstChosenMove(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getActionHeal(_object, _fieldName, _element);
    }

    private static void getActionMove(ActionMove _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FIRST_CHOSEN_MOVE)) {
            _object.setFirstChosenMove(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FINAL_CHOSEN_MOVE)) {
            _object.setFinalChosenMove(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CHOSEN_TARGETS)) {
            _object.setChosenTargets(getListTargetCoords(_element));
            return;
        }
        _object.setSubstitute(DocumentReaderCoreUtil.getByte(_element));
    }

    private static void getActionSimpleHeal(ActionSimpleHeal _object, String _fieldName, Element _element) {
        getActionHeal(_object, _fieldName, _element);
    }

    private static void getActionSwitch(ActionSwitch _object, Element _element) {
        _object.setSubstitute(DocumentReaderCoreUtil.getByte(_element));
    }

    private static FightState getFightState(Element _elt) {
        return FightState.getFightStateByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static FightType getFightType(Element _elt) {
        return FightType.getFightTypeByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static AffectedMove getAffectedMove(Element _elt) {
        return AffectedMove.newAffectedMove(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static CopiedMove getCopiedMove(Element _elt) {
        return CopiedMove.newCopiedMove(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static MoveTarget getMoveTarget(Element _elt) {
        return MoveTarget.newMoveTarget(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static MovesAbilities getMovesAbilities(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        MovesAbilities object_ = Instances.newMovesAbilities();
        for (Element c: childElements_) {
            getMovesAbilities(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getMovesAbilities(MovesAbilities _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MOVES)) {
            _object.setMoves(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        _object.setAbilities(DocumentReaderCoreUtil.getStringList(_element));
    }

    private static Difficulty getDifficulty(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Difficulty object_ = Instances.newDifficulty();
        for (Element c: childElements_) {
            getDifficulty(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getDifficulty(Difficulty _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ALLOW_CATCHING_KO)) {
            _object.setAllowCatchingKo(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ALLOWED_SWITCH_PLACES_END_ROUND)) {
            _object.setAllowedSwitchPlacesEndRound(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DIFF_WINNING_EXP_PTS_FIGHT)) {
            _object.setDiffWinningExpPtsFight(getDifficultyWinPointsFight(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_RATE_WINNING_EXP_PTS_FIGHT)) {
            _object.setRateWinningExpPtsFight(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_WIN_TRAINER_EXP)) {
            _object.setWinTrainerExp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DAMAGE_RATE_PLAYER)) {
            _object.setDamageRatePlayer(getDifficultyModelLaw(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DAMAGE_RATE_LAW_FOE)) {
            _object.setDamageRateLawFoe(getDifficultyModelLaw(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_END_FIGHT_IF_ONE_TEAM_KO)) {
            _object.setEndFightIfOneTeamKo(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_RATE_WIN_MONEY_BASE)) {
            _object.setRateWinMoneyBase(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_RATE_LOOSE_MONEY_WIN)) {
            _object.setRateLooseMoneyWin(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IV_PLAYER)) {
            _object.setIvPlayer(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IV_FOE)) {
            _object.setIvFoe(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        getDifficulty1(_object, _fieldName, _element);
    }

    private static void getDifficulty1(Difficulty _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STILL_POSSIBLE_FLEE)) {
            _object.setStillPossibleFlee(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_RESTORED_MOVES_END_FIGHT)) {
            _object.setRestoredMovesEndFight(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ENABLED_CLOSING)) {
            _object.setEnabledClosing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_RANDOM_WILD_FIGHT)) {
            _object.setRandomWildFight(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        _object.setSkipLearningMovesWhileNotGrowingLevel(DocumentReaderCoreUtil.getBoolean(_element));
    }

    public static LoadingGame getLoadingGameOrNull(Document _string) {
        if (_string == null) {
            return null;
        }
        Element documentElement_ = _string.getDocumentElement();
        String tagName_ = documentElement_.getTagName();
        if (!StringUtil.quickEq(tagName_, MAIN_TAG) || !StringUtil.quickEq(documentElement_.getAttribute(DocumentWriterCoreUtil.FIELD), LOADING_GAME)) {
            return null;
        }
        return getLoadingGame(documentElement_);
    }
    public static LoadingGame getLoadingGame(String _string) {
        Document doc_ = DocumentBuilder.parseNoTextDocument(_string);
        if (doc_ == null) {
            return newLoadingGame();
        }
        return getLoadingGame(doc_.getDocumentElement());
    }

    private static LoadingGame getLoadingGame(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        LoadingGame object_ = newLoadingGame();
        for (Element c: childElements_) {
            getLoadingGame(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getLoadingGame(LoadingGame _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LAST_ROM)) {
            _object.setLastRom(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LAST_SAVED_GAME)) {
            _object.setLastSavedGame(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SAVE_HOME_FOLDER)) {
            _object.setSaveHomeFolder(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LOAD_HOME_FOLDER)) {
            _object.setLoadHomeFolder(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LOAD_LAST_ROM)) {
            _object.setLoadLastRom(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LOAD_LAST_GAME)) {
            _object.setLoadLastGame(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SAVE_GAME_AT_EXIT)) {
            _object.setSaveGameAtExit(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ENABLE_ANIMATION)) {
            _object.setEnableAnimation(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ENABLE_MOVING_HEROS_ANIMATION)) {
            _object.setEnableMovingHerosAnimation(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CLICK_BUTTONS_PAD)) {
            _object.setClickButtonsPad(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EXPORT)) {
            _object.setExport(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        _object.setEnabledKeyPad(DocumentReaderCoreUtil.getBoolean(_element));
    }

    private static DifficultyModelLaw getDifficultyModelLaw(Element _elt) {
        return DifficultyModelLaw.getModelByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static DifficultyWinPointsFight getDifficultyWinPointsFight(Element _elt) {
        return DifficultyWinPointsFight.getDiffWonPtsByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static Inventory getInventory(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Inventory object_ = Instances.newInventory();
        for (Element c: childElements_) {
            getInventory(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getInventory(Inventory _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ITEMS)) {
            _object.setItems(DocumentReaderMathUtil.getStringMapLgInt(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TM)) {
            _object.setTm(DocumentReaderCoreUtil.getMapShortBoolean(_element));
            return;
        }
        _object.setHm(DocumentReaderCoreUtil.getMapShortBoolean(_element));
    }

    private static Player getPlayer(Element _element, SexListInt _sexList) {
        ElementList childElements_ = _element.getChildElements();
        Player object_ = Instances.newPlayer();
        for (Element c: childElements_) {
            getPlayer(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c,_sexList);
        }
        return object_;
    }

    private static void getPlayer(Player _object, String _fieldName, Element _element, SexListInt _sexList) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NICKNAME)) {
            _object.setNickname(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SEX)) {
            _object.setSex(getSex(_element,_sexList));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TEAM)) {
            _object.setTeam(getListUsablePokemon(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BOX)) {
            _object.setBox(getListUsablePokemon(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INVENTORY)) {
            _object.setInventory(getInventory(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CAUGHT_PK)) {
            _object.setCaughtPk(DocumentReaderCoreUtil.getStringMapBoolean(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MONEY)) {
            _object.setMoney(DocumentReaderMathUtil.getLgInt(_element));
            return;
        }
        _object.setRemainingRepelSteps(DocumentReaderCoreUtil.getInteger(_element));
    }

    private static Sex getSex(Element _elt, SexListInt _sexList) {
        return Sex.getSexByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE),_sexList);
    }

    public static DataMap getDataMap(String _string) {
        Document doc_ = DocumentBuilder.parseNoTextDocument(_string);
        if (doc_ == null) {
            return Instances.newDataMap();
        }
        return getDataMap(doc_.getDocumentElement());
    }

    private static DataMap getDataMap(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DataMap object_ = Instances.newDataMap();
        for (Element c: childElements_) {
            getDataMap(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getDataMap(DataMap _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PLACES)) {
            _object.setPlaces(getMapShortPlace(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ACCESS_CONDITION)) {
            _object.setAccessCondition(getMapCoordsListCoords(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MINI_MAP)) {
            _object.setMiniMap(getMapMiniMapCoordsTileMiniMap(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_UNLOCKED_CITY)) {
            _object.setUnlockedCity(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BEGIN)) {
            _object.setBegin(getCoords(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FIRST_POKEMON)) {
            _object.setFirstPokemon(getWildPk(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SCREEN_WIDTH)) {
            _object.setScreenWidth(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SCREEN_HEIGHT)) {
            _object.setScreenHeight(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SPACE_BETWEEN_LEFT_AND_HEROS)) {
            _object.setSpaceBetweenLeftAndHeros(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SPACE_BETWEEN_TOP_AND_HEROS)) {
            _object.setSpaceBetweenTopAndHeros(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        _object.setSideLength(DocumentReaderCoreUtil.getInteger(_element));
    }

    private static Building getBuilding(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_GYM)) {
            Gym object_ = Instances.newGym();
            for (Element c: childElements_) {
                getGym(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        PokemonCenter object_ = Instances.newPokemonCenter();
        for (Element c: childElements_) {
            getPokemonCenter(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getBuilding(Building _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMAGE_FILE_NAME)) {
            _object.setImageFileName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        _object.setExitCity(getNullablePoint(_element));
    }

    private static void getGym(Gym _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LEVEL)) {
            _object.setLevel(getLevelIndoorGym(_element));
            return;
        }
        getBuilding(_object, _fieldName, _element);
    }

    private static void getPokemonCenter(PokemonCenter _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LEVEL)) {
            _object.setLevel(getLevelIndoorPokemonCenter(_element));
            return;
        }
        getBuilding(_object, _fieldName, _element);
    }

    private static Ally getAlly(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Ally object_ = Instances.newAlly();
        for (Element c: childElements_) {
            getAlly(object_, c);
        }
        return object_;
    }

    private static void getAlly(Ally _object, Element _element) {
        _object.setTeam(getListPkTrainer(_element));
    }

    private static CharacterInRoadCave getCharacterInRoadCave(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_TRAINER_MULTI_FIGHTS)) {
            TrainerMultiFights object_ = Instances.newTrainerMultiFights();
            for (Element c: childElements_) {
                getTrainerMultiFights(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        DealerItem object_ = Instances.newDealerItem();
        for (Element c: childElements_) {
            getDealerItem(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getDealerItem(DealerItem _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ITEMS)) {
            _object.setItems(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TECHNICAL_MOVES)) {
            _object.setTechnicalMoves(DocumentReaderCoreUtil.getListShort(_element));
            return;
        }
        getPerson(_object, _element);
    }

    private static DualFight getDualFight(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DualFight object_ = Instances.newDualFight();
        for (Element c: childElements_) {
            getDualFight(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getDualFight(DualFight _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ALLY)) {
            _object.setAlly(getAlly(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FOE_TRAINER)) {
            _object.setFoeTrainer(getTempTrainer(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NAMES)) {
            _object.setNames(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        _object.setPt(getNullablePoint(_element));
    }

    private static void getGerantPokemon(GerantPokemon _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_GERANCE)) {
            _object.setGerance(getGeranceType(_element));
            return;
        }
        getPerson(_object, _element);
    }

    private static GymLeader getGymLeader(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        GymLeader object_ = Instances.newGymLeader();
        for (Element c: childElements_) {
            getGymLeader(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getGymLeader(GymLeader _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TM)) {
            _object.setTm(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getTrainerOneFight(_object, _fieldName, _element);
    }

    private static GymTrainer getGymTrainer(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        GymTrainer object_ = Instances.newGymTrainer();
        for (Element c: childElements_) {
            getGymTrainer(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getGymTrainer(GymTrainer _object, String _fieldName, Element _element) {
        getTrainerOneFight(_object, _fieldName, _element);
    }

    private static Person getPerson(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_GERANT_POKEMON)) {
            GerantPokemon object_ = Instances.newGerantPokemon();
            for (Element c: childElements_) {
                getGerantPokemon(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_GYM_LEADER)) {
            GymLeader object_ = Instances.newGymLeader();
            for (Element c: childElements_) {
                getGymLeader(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_GYM_TRAINER)) {
            GymTrainer object_ = Instances.newGymTrainer();
            for (Element c: childElements_) {
                getGymTrainer(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_SELLER)) {
            Seller object_ = Instances.newSeller();
            for (Element c: childElements_) {
                getSeller(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        return getPerson1(childElements_, tagName_);
    }

    private static Person getPerson1(ElementList _childElements, String _tagName) {
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_TEMP_TRAINER)) {
            TempTrainer object_ = Instances.newTempTrainer();
            for (Element c: _childElements) {
                getTempTrainer(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_TRAINER_LEAGUE)) {
            TrainerLeague object_ = Instances.newTrainerLeague();
            for (Element c: _childElements) {
                getTrainerLeague(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(_tagName,DocumentWriterAikiCoreUtil.TYPE_TRAINER_MULTI_FIGHTS)) {
            TrainerMultiFights object_ = Instances.newTrainerMultiFights();
            for (Element c: _childElements) {
                getTrainerMultiFights(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        DealerItem object_ = Instances.newDealerItem();
        for (Element c: _childElements) {
            getDealerItem(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getPerson(Person _object, Element _element) {
        _object.setImageMiniFileName(DocumentReaderCoreUtil.getString(_element));
    }

    private static void getSeller(Seller _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SELL)) {
            _object.setSell(getSellType(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ITEMS)) {
            _object.setItems(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TM)) {
            _object.setTm(DocumentReaderCoreUtil.getListShort(_element));
            return;
        }
        getPerson(_object, _element);
    }

    private static TempTrainer getTempTrainer(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TempTrainer object_ = Instances.newTempTrainer();
        for (Element c: childElements_) {
            getTempTrainer(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getTempTrainer(TempTrainer _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMAGE_MINI_SECOND_TRAINER_FILE_NAME)) {
            _object.setImageMiniSecondTrainerFileName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getTrainerOneFight(_object, _fieldName, _element);
    }

    private static void getTrainer(Trainer _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULTIPLICITY_FIGHT)) {
            _object.setMultiplicityFight(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_IMAGE_MAXI_FILE_NAME)) {
            _object.setImageMaxiFileName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getPerson(_object, _element);
    }

    private static TrainerLeague getTrainerLeague(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TrainerLeague object_ = Instances.newTrainerLeague();
        for (Element c: childElements_) {
            getTrainerLeague(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getTrainerLeague(TrainerLeague _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getTrainerOneFight(_object, _fieldName, _element);
    }

    private static void getTrainerMultiFights(TrainerMultiFights _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TEAMS_REWARDS)) {
            _object.setTeamsRewards(getListPokemonTeam(_element));
            return;
        }
        getTrainer(_object, _fieldName, _element);
    }

    private static void getTrainerOneFight(TrainerOneFight _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_REWARD)) {
            _object.setReward(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TEAM)) {
            _object.setTeam(getListPkTrainer(_element));
            return;
        }
        getTrainer(_object, _fieldName, _element);
    }

    private static GeranceType getGeranceType(Element _elt) {
        return GeranceType.getGeranceTypeByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static SellType getSellType(Element _elt) {
        return SellType.getSellTypeByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static Direction getDirection(Element _elt) {
        return Direction.getDirectionByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static AbsAreaApparition getAreaApparition(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        AbsAreaApparition candidate_ = null;
        for (Element c: childElements_) {
            candidate_ = createAreaApparition(candidate_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        if (candidate_ == null) {
            candidate_ = new MultAreaApparition();
        }
        for (Element c: childElements_) {
            getAreaApparition(candidate_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return candidate_;
    }

    private static AbsAreaApparition createAreaApparition(AbsAreaApparition _object, String _fieldName, Element _element) {
        if (_object != null) {
            return _object;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_FIGHT)) {
            AreaApparition ar_ = new AreaApparition();
            ar_.setMultFight(DocumentReaderCoreUtil.getByte(_element));
            return ar_;
        }
        return null;
    }

    private static void getAreaApparition(AbsAreaApparition _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_AVG_NB_STEPS)) {
            _object.setAvgNbSteps(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (_object instanceof AreaApparition) {
            if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MULT_FIGHT)) {
                ((AreaApparition)_object).setMultFight(DocumentReaderCoreUtil.getByte(_element));
                return;
            }
            if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_WILD_POKEMON)) {
                ((AreaApparition)_object).setWildPokemon(getListWildPk(_element));
                return;
            }
            ((AreaApparition)_object).setWildPokemonFishing(getListWildPk(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_WILD_POKEMON)) {
            _object.setWildPokemonList(getListListWildPk(_element));
            return;
        }
        _object.setWildPokemonFishingList(getListListWildPk(_element));
    }

    private static Block getBlock(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Block object_ = Instances.newBlock();
        for (Element c: childElements_) {
            getBlock(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getBlock(Block _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_WIDTH)) {
            _object.setWidth(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HEIGHT)) {
            _object.setHeight(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_INDEX_APPARITION)) {
            _object.setIndexApparition(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TILE_FILE_NAME)) {
            _object.setTileFileName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        _object.setType(getEnvironmentType(_element));
    }

    private static void getLevel(Level _object, Element _element) {
        _object.setBlocks(getMapPointBlock(_element));
    }

    private static LevelCave getLevelCave(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        LevelCave object_ = Instances.newLevelCave();
        for (Element c: childElements_) {
            getLevelCave(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getLevelCave(LevelCave _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LINKS_OTHER_LEVELS)) {
            _object.setLinksOtherLevels(getMapPointLink(_element));
            return;
        }
        getLevelWithWildPokemon(_object, _fieldName, _element);
    }

    private static LevelIndoorGym getLevelIndoorGym(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        LevelIndoorGym object_ = Instances.newLevelIndoorGym();
        for (Element c: childElements_) {
            getLevelIndoorGym(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getLevelIndoorGym(LevelIndoorGym _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_GYM_TRAINERS)) {
            _object.setGymTrainers(getMapPointGymTrainer(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_GYM_LEADER_COORDS)) {
            _object.setGymLeaderCoords(getNullablePoint(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_GYM_LEADER)) {
            _object.setGymLeader(getGymLeader(_element));
            return;
        }
        getLevel(_object, _element);
    }

    private static LevelIndoorPokemonCenter getLevelIndoorPokemonCenter(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        LevelIndoorPokemonCenter object_ = Instances.newLevelIndoorPokemonCenter();
        for (Element c: childElements_) {
            getLevelIndoorPokemonCenter(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getLevelIndoorPokemonCenter(LevelIndoorPokemonCenter _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_GERANTS)) {
            _object.setGerants(getMapPointPerson(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STORAGE_COORDS)) {
            _object.setStorageCoords(getNullablePoint(_element));
            return;
        }
        getLevel(_object, _element);
    }

    private static LevelLeague getLevelLeague(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        LevelLeague object_ = Instances.newLevelLeague();
        for (Element c: childElements_) {
            getLevelLeague(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getLevelLeague(LevelLeague _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TRAINER_COORDS)) {
            _object.setTrainerCoords(getNullablePoint(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TRAINER)) {
            _object.setTrainer(getTrainerLeague(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ACCESS_POINT)) {
            _object.setAccessPoint(getNullablePoint(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NEXT_LEVEL_TARGET)) {
            _object.setNextLevelTarget(getNullablePoint(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FILE_NAME)) {
            _object.setFileName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getLevel(_object, _element);
    }

    private static LevelOutdoor getLevelOutdoor(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        LevelOutdoor object_ = Instances.newLevelOutdoor();
        for (Element c: childElements_) {
            getLevelOutdoor(object_, c);
        }
        return object_;
    }

    private static void getLevelOutdoor(LevelOutdoor _object, Element _element) {
        getLevel(_object, _element);
    }

    private static LevelRoad getLevelRoad(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        LevelRoad object_ = Instances.newLevelRoad();
        for (Element c: childElements_) {
            getLevelRoad(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getLevelRoad(LevelRoad _object, String _fieldName, Element _element) {
        getLevelWithWildPokemon(_object, _fieldName, _element);
    }

    private static void getLevelWithWildPokemon(LevelWithWildPokemon _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_WILD_POKEMON_AREAS)) {
            _object.setWildPokemonAreas(getListAreaApparition(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_CHARACTERS)) {
            _object.setCharacters(getMapPointCharacterInRoadCave(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_DUAL_FIGHTS)) {
            _object.setDualFights(getMapPointDualFight(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LEGENDARY_PKS)) {
            _object.setLegendaryPks(getMapPointWildPk(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ITEMS)) {
            _object.setItems(getMapPointString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TM)) {
            _object.setTm(getMapPointShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HM)) {
            _object.setHm(getMapPointShort(_element));
            return;
        }
        getLevel(_object, _element);
    }

    private static Link getLink(Element _elt) {
        return Link.newLink(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static EnvironmentType getEnvironmentType(Element _elt) {
        return EnvironmentType.getEnvByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static void getCave(Cave _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LEVELS)) {
            _object.setLevels(getMapByteLevelCave(_element));
            return;
        }
        _object.setLinksWithOtherPlaces(getMapLevelPointLink(_element));
    }

    private static void getCity(City _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_BUILDINGS)) {
            _object.setBuildings(getMapPointBuilding(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LEVEL)) {
            _object.setLevel(getLevelOutdoor(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_SAVEDLINKS)) {
            _object.setSavedlinks(getMapPlaceInterConnectCoords(_element));
            return;
        }
        _object.setLinksWithCaves(getMapPointLink(_element));
    }

    private static void getLeague(League _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ROOMS)) {
            _object.setRooms(getListLevelLeague(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ACCESS_COORDS)) {
            _object.setAccessCoords(getCoords(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FILE_NAME)) {
            _object.setFileName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        _object.setBegin(getNullablePoint(_element));
    }

    private static Place getPlace(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_CAVE)) {
            Cave object_ = Instances.newCave();
            for (Element c: childElements_) {
                getCave(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_CITY)) {
            City object_ = Instances.newCity();
            for (Element c: childElements_) {
                getCity(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_LEAGUE)) {
            League object_ = Instances.newLeague();
            for (Element c: childElements_) {
                getLeague(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
            }
            return object_;
        }
        Road object_ = Instances.newRoad();
        for (Element c: childElements_) {
            getRoad(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getRoad(Road _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LEVEL)) {
            _object.setLevel(getLevelRoad(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LINKS_WITH_CAVES)) {
            _object.setLinksWithCaves(getMapPointLink(_element));
            return;
        }
        _object.setSavedlinks(getMapPlaceInterConnectCoords(_element));
    }

    private static Egg getEgg(Element _elt) {
        return Egg.newEgg(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static PkTrainer getPkTrainer(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        PkTrainer object_ = Instances.newPkTrainer();
        for (Element c: childElements_) {
            getPkTrainer(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getPkTrainer(PkTrainer _object, String _fieldName, Element _element) {
        if (getWildPk(_object,_fieldName,_element)) {
            return;
        }
        _object.setMoves(DocumentReaderCoreUtil.getStringList(_element));
    }

    public static PokemonPlayer getPokemonPlayer(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        PokemonPlayer object_ = Instances.newPokemonPlayer();
        for (Element c: childElements_) {
            getPokemonPlayer(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getPokemonPlayer(PokemonPlayer _object, String _fieldName, Element _element) {
        if (getWildPk(_object,_fieldName,_element)) {
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_REMAINING_HP)) {
            _object.setRemainingHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_STATUS)) {
            _object.setStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NICKNAME)) {
            _object.setNickname(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_MOVES)) {
            _object.setMoves(getStringMapUsesOfMove(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_EV)) {
            _object.setEv(getMapStatisticShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_WON_EXP_SINCE_LAST_LEVEL)) {
            _object.setWonExpSinceLastLevel(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_HAPPINESS)) {
            _object.setHappiness(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_USED_BALL_CATCHING)) {
            _object.setUsedBallCatching(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        _object.setNbStepsTeamLead(DocumentReaderCoreUtil.getShort(_element));
    }

    private static PokemonTeam getPokemonTeam(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        PokemonTeam object_ = Instances.newPokemonTeam();
        for (Element c: childElements_) {
            getPokemonTeam(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getPokemonTeam(PokemonTeam _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_TEAM)) {
            _object.setTeam(getListPkTrainer(_element));
            return;
        }
        _object.setReward(DocumentReaderCoreUtil.getShort(_element));
    }

    private static UsablePokemon getUsablePokemon(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        if (StringUtil.quickEq(tagName_,DocumentWriterAikiCoreUtil.TYPE_EGG)) {
            return getEgg(_element);
        }
        PokemonPlayer object_ = Instances.newPokemonPlayer();
        for (Element c: childElements_) {
            getPokemonPlayer(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static WildPk getWildPk(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        WildPk object_ = Instances.newWildPk();
        for (Element c: childElements_) {
            getWildPk(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static boolean getWildPk(Pokemon _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return true;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_LEVEL)) {
            _object.setLevel(DocumentReaderCoreUtil.getShort(_element));
            return true;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_GENDER)) {
            _object.setGender(getGender(_element));
            return true;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ABILITY)) {
            _object.setAbility(DocumentReaderCoreUtil.getString(_element));
            return true;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_ITEM)) {
            _object.setItem(DocumentReaderCoreUtil.getString(_element));
            return true;
        }
        return false;
    }

    private static Gender getGender(Element _elt) {
        return Gender.getGenderByName(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static MiniMapCoords getMiniMapCoords(Element _elt) {
        return MiniMapCoords.newMiniMapCoords(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static PlaceInterConnect getPlaceInterConnect(Element _elt) {
        return PlaceInterConnect.newPlaceInterConnect(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static TileMiniMap getTileMiniMap(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TileMiniMap object_ = Instances.newTileMiniMap();
        for (Element c: childElements_) {
            getTileMiniMap(object_,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
        return object_;
    }

    private static void getTileMiniMap(TileMiniMap _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_FILE)) {
            _object.setFile(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringUtil.quickEq(_fieldName, DocumentWriterAikiCoreUtil.FIELD_PLACE)) {
            _object.setPlace(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        _object.setHeros(DocumentReaderCoreUtil.getBoolean(_element));
    }

    private static Coords getCoords(Element _elt) {
        return Coords.newCoords(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static LevelPoint getLevelPoint(Element _elt) {
        return LevelPoint.newLevelPoint(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static Point getPoint(Element _elt) {
        return Point.newPoint(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static NullablePoint getNullablePoint(Element _elt) {
        return new NullablePoint(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    private static MonteCarloEnum<Statistic> getMonteCarloEnumStatistic(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        MonteCarloEnum<Statistic> object_ = new MonteCarloEnum<Statistic>();
        for (Element c: childElements_) {
            object_.setLaw(getMapStatisticLgInt(c));
        }
        return object_;
    }

    private static IdMap<Statistic,LgInt> getMapStatisticLgInt(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        IdMap<Statistic,LgInt> map_ = new IdMap<Statistic,LgInt>(cap_);
        IdList<Statistic> keys_ = new IdList<Statistic>(cap_);
        CustList<LgInt> values_ = new CustList<LgInt>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatistic(c));
            } else {
                values_.add(DocumentReaderMathUtil.getLgInt(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static CustList<EffectWhileSendingWithStatistic> getListEffectWhileSending(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<EffectWhileSendingWithStatistic> list_ = new CustList<EffectWhileSendingWithStatistic>(cap_);
        for (Element c: childElements_) {
            list_.add(getEffectWhileSending(c));
        }
        return list_;
    }

    private static CustList<Effect> getListEffect(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<Effect> list_ = new CustList<Effect>(cap_);
        for (Element c: childElements_) {
            list_.add(getEffect(c));
        }
        return list_;
    }

    private static CustList<EffectEndRound> getListEffectEndRound(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<EffectEndRound> list_ = new CustList<EffectEndRound>(cap_);
        for (Element c: childElements_) {
            list_.add(getEffectEndRound(c));
        }
        return list_;
    }

    private static CustList<EffectEndRoundFoe> getListEffectEndRoundFoe(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<EffectEndRoundFoe> list_ = new CustList<EffectEndRoundFoe>(cap_);
        for (Element c: childElements_) {
            list_.add(getEffectEndRoundFoe(c));
        }
        return list_;
    }

    private static CustList<EffectEndRoundStatus> getListEffectEndRoundStatus(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<EffectEndRoundStatus> list_ = new CustList<EffectEndRoundStatus>(cap_);
        for (Element c: childElements_) {
            list_.add(getEffectEndRoundStatus(c));
        }
        return list_;
    }

    private static CustList<EffectTeam> getListEffectTeam(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<EffectTeam> list_ = new CustList<EffectTeam>(cap_);
        for (Element c: childElements_) {
            list_.add(getEffectTeam(c));
        }
        return list_;
    }

    private static CustList<EffectPartnerStatus> getListEffectPartnerStatus(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<EffectPartnerStatus> list_ = new CustList<EffectPartnerStatus>(cap_);
        for (Element c: childElements_) {
            list_.add(getEffectPartnerStatus(c));
        }
        return list_;
    }

    private static CustList<AbsAreaApparition> getListAreaApparition(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<AbsAreaApparition> list_ = new CustList<AbsAreaApparition>(cap_);
        for (Element c: childElements_) {
            list_.add(getAreaApparition(c));
        }
        return list_;
    }

    private static CustList<LevelLeague> getListLevelLeague(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<LevelLeague> list_ = new CustList<LevelLeague>(cap_);
        for (Element c: childElements_) {
            list_.add(getLevelLeague(c));
        }
        return list_;
    }

    private static CustList<PkTrainer> getListPkTrainer(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<PkTrainer> list_ = new CustList<PkTrainer>(cap_);
        for (Element c: childElements_) {
            list_.add(getPkTrainer(c));
        }
        return list_;
    }

    private static CustList<PokemonTeam> getListPokemonTeam(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<PokemonTeam> list_ = new CustList<PokemonTeam>(cap_);
        for (Element c: childElements_) {
            list_.add(getPokemonTeam(c));
        }
        return list_;
    }

    public static CustList<UsablePokemon> getListUsablePokemon(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<UsablePokemon> list_ = new CustList<UsablePokemon>(cap_);
        for (Element c: childElements_) {
            list_.add(getUsablePokemon(c));
        }
        return list_;
    }

    private static IdList<Statistic> getListStatistic(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        IdList<Statistic> list_ = new IdList<Statistic>(cap_);
        for (Element c: childElements_) {
            list_.add(getStatistic(c));
        }
        return list_;
    }

    private static IdMap<Statistic,BoostHpRate> getMapStatisticBoostHpRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        IdMap<Statistic,BoostHpRate> map_ = new IdMap<Statistic,BoostHpRate>(cap_);
        CustList<Statistic> keys_ = new CustList<Statistic>(cap_);
        CustList<BoostHpRate> values_ = new CustList<BoostHpRate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatistic(c));
            } else {
                values_.add(getBoostHpRate(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static IdMap<Statistic,StatBaseEv> getMapStatisticStatBaseEv(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        IdMap<Statistic,StatBaseEv> map_ = new IdMap<Statistic,StatBaseEv>(cap_);
        CustList<Statistic> keys_ = new CustList<Statistic>(cap_);
        CustList<StatBaseEv> values_ = new CustList<StatBaseEv>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatistic(c));
            } else {
                values_.add(getStatBaseEv(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static IdMap<Statistic,Rate> getMapStatisticRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        IdMap<Statistic,Rate> map_ = new IdMap<Statistic,Rate>(cap_);
        CustList<Statistic> keys_ = new CustList<Statistic>(cap_);
        CustList<Rate> values_ = new CustList<Rate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatistic(c));
            } else {
                values_.add(DocumentReaderMathUtil.getRate(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static IdMap<Statistic,Byte> getMapStatisticByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        IdMap<Statistic,Byte> map_ = new IdMap<Statistic,Byte>(cap_);
        CustList<Statistic> keys_ = new CustList<Statistic>(cap_);
        CustList<Byte> values_ = new CustList<Byte>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatistic(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getByte(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static IdMap<Statistic,Short> getMapStatisticShort(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        IdMap<Statistic,Short> map_ = new IdMap<Statistic,Short>(cap_);
        CustList<Statistic> keys_ = new CustList<Statistic>(cap_);
        CustList<Short> values_ = new CustList<Short>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatistic(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getShort(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static IdMap<Statistic,String> getMapStatisticString(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        IdMap<Statistic,String> map_ = new IdMap<Statistic,String>(cap_);
        CustList<Statistic> keys_ = new CustList<Statistic>(cap_);
        CustList<String> values_ = new CustList<String>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatistic(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getString(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static IdMap<EnvironmentType,String> getMapEnvironmentTypeString(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        IdMap<EnvironmentType,String> map_ = new IdMap<EnvironmentType,String>(cap_);
        CustList<EnvironmentType> keys_ = new CustList<EnvironmentType>(cap_);
        CustList<String> values_ = new CustList<String>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getEnvironmentType(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getString(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static CustList<LevelMove> getListLevelMove(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<LevelMove> list_ = new CustList<LevelMove>(cap_);
        for (Element c: childElements_) {
            list_.add(getLevelMove(c));
        }
        return list_;
    }

    private static CustList<StatisticStatus> getListStatisticStatus(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<StatisticStatus> list_ = new CustList<StatisticStatus>(cap_);
        for (Element c: childElements_) {
            list_.add(getStatisticStatus(c));
        }
        return list_;
    }

    private static CustList<TypesDuo> getListTypesDuo(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<TypesDuo> list_ = new CustList<TypesDuo>(cap_);
        for (Element c: childElements_) {
            list_.add(getTypesDuo(c));
        }
        return list_;
    }

    private static TargetCoordsList getListTargetCoords(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        TargetCoordsList list_ = new TargetCoordsList(cap_);
        for (Element c: childElements_) {
            list_.add(getTargetCoords(c));
        }
        return list_;
    }

    private static CustList<CustList<WildPk>> getListListWildPk(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<CustList<WildPk>> list_ = new CustList<CustList<WildPk>>(cap_);
        for (Element c: childElements_) {
            list_.add(getListWildPk(c));
        }
        return list_;
    }

    private static CustList<WildPk> getListWildPk(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<WildPk> list_ = new CustList<WildPk>(cap_);
        for (Element c: childElements_) {
            list_.add(getWildPk(c));
        }
        return list_;
    }

    private static Condition getListCoords(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        Condition list_ = new Condition(cap_);
        for (Element c: childElements_) {
            list_.add(getCoords(c));
        }
        return list_;
    }

    private static PointEqList getListPoint(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        PointEqList list_ = new PointEqList(cap_);
        for (Element c: childElements_) {
            list_.add(getPoint(c));
        }
        return list_;
    }

    private static ByteMap<Anticipation> getMapByteAnticipation(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ByteMap<Anticipation> map_ = new ByteMap<Anticipation>(cap_);
        CustList<Byte> keys_ = new CustList<Byte>(cap_);
        CustList<Anticipation> values_ = new CustList<Anticipation>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getByte(c));
            } else {
                values_.add(getAnticipation(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ByteMap<ChoiceOfEvolutionAndMoves> getMapByteChoiceOfEvolutionAndMoves(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ByteMap<ChoiceOfEvolutionAndMoves> map_ = new ByteMap<ChoiceOfEvolutionAndMoves>(cap_);
        CustList<Byte> keys_ = new CustList<Byte>(cap_);
        CustList<ChoiceOfEvolutionAndMoves> values_ = new CustList<ChoiceOfEvolutionAndMoves>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getByte(c));
            } else {
                values_.add(getChoiceOfEvolutionAndMoves(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ByteMap<Fighter> getMapByteFighter(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ByteMap<Fighter> map_ = new ByteMap<Fighter>(cap_);
        CustList<Byte> keys_ = new CustList<Byte>(cap_);
        CustList<Fighter> values_ = new CustList<Fighter>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getByte(c));
            } else {
                values_.add(getFighter(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static CustList<CatchingBallFoeAction> getListCatchingBallFoeAction(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<CatchingBallFoeAction> values_ = new CustList<CatchingBallFoeAction>(cap_);
        for (Element c: childElements_) {
            values_.add(getCatchingBallFoeAction(c));
        }
        return values_;
    }
    private static ByteMap<StacksOfUses> getMapByteStacksOfUses(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ByteMap<StacksOfUses> map_ = new ByteMap<StacksOfUses>(cap_);
        CustList<Byte> keys_ = new CustList<Byte>(cap_);
        CustList<StacksOfUses> values_ = new CustList<StacksOfUses>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getByte(c));
            } else {
                values_.add(getStacksOfUses(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ByteMap<Team> getMapByteTeam(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ByteMap<Team> map_ = new ByteMap<Team>(cap_);
        CustList<Byte> keys_ = new CustList<Byte>(cap_);
        CustList<Team> values_ = new CustList<Team>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getByte(c));
            } else {
                values_.add(getTeam(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static CustList<LevelCave> getMapByteLevelCave(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<LevelCave> values_ = new CustList<LevelCave>(cap_);
        for (Element c: childElements_) {
            values_.add(getLevelCave(c));
        }
        return values_;
    }
    private static CustList<Place> getMapShortPlace(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<Place> values_ = new CustList<Place>(cap_);
        for (Element c: childElements_) {
            values_.add(getPlace(c));
        }
        return values_;
    }
    private static ShortMap<PointEqList> getMapShortListPoint(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ShortMap<PointEqList> map_ = new ShortMap<PointEqList>(cap_);
        CustList<Short> keys_ = new CustList<Short>(cap_);
        CustList<PointEqList> values_ = new CustList<PointEqList>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getShort(c));
            } else {
                values_.add(getListPoint(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static CategoryMults getMapCategoryMultRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        CategoryMults map_ = new CategoryMults(cap_);
        CustList<CategoryMult> keys_ = new CustList<CategoryMult>(cap_);
        CustList<Rate> values_ = new CustList<Rate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getCategoryMult(c));
            } else {
                values_.add(DocumentReaderMathUtil.getRate(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.addEntry(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StatisticCategoryList<Rate> getMapStatisticCategoryRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StatisticCategoryList<Rate> map_ = new StatisticCategoryRate(cap_);
        CustList<StatisticCategory> keys_ = new CustList<StatisticCategory>(cap_);
        CustList<Rate> values_ = new CustList<Rate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatisticCategory(c));
            } else {
                values_.add(DocumentReaderMathUtil.getRate(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.addEntry(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StatisticCategoryList<Byte> getMapStatisticCategoryByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StatisticCategoryList<Byte> map_ = new StatisticCategoryByte(cap_);
        CustList<StatisticCategory> keys_ = new CustList<StatisticCategory>(cap_);
        CustList<Byte> values_ = new CustList<Byte>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatisticCategory(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getByte(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.addEntry(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StatisticPokemons getMapStatisticPokemonByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StatisticPokemons map_ = new StatisticPokemons(cap_);
        CustList<StatisticPokemon> keys_ = new CustList<StatisticPokemon>(cap_);
        CustList<Byte> values_ = new CustList<Byte>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatisticPokemon(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getByte(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.addEntry(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StatisticStatusList getMapStatisticStatusByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StatisticStatusList map_ = new StatisticStatusList(cap_);
        CustList<StatisticStatus> keys_ = new CustList<StatisticStatus>(cap_);
        CustList<Byte> values_ = new CustList<Byte>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatisticStatus(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getByte(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.addEntry(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StatisticTypeList<Rate> getMapStatisticTypeRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StatisticTypeList<Rate> map_ = new StatisticTypeRate(cap_);
        CustList<StatisticType> keys_ = new CustList<StatisticType>(cap_);
        CustList<Rate> values_ = new CustList<Rate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatisticType(c));
            } else {
                values_.add(DocumentReaderMathUtil.getRate(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.addEntry(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StatisticTypeList<Byte> getMapStatisticTypeByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StatisticTypeList<Byte> map_ = new StatisticTypeByte(cap_);
        CustList<StatisticType> keys_ = new CustList<StatisticType>(cap_);
        CustList<Byte> values_ = new CustList<Byte>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatisticType(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getByte(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.addEntry(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static TypesDuos getMapTypesDuoRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        TypesDuos map_ = new TypesDuos(cap_);
        CustList<TypesDuo> keys_ = new CustList<TypesDuo>(cap_);
        CustList<Rate> values_ = new CustList<Rate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getTypesDuo(c));
            } else {
                values_.add(DocumentReaderMathUtil.getRate(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.addEntry(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static WeatherTypes getMapWeatherTypeRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        WeatherTypes map_ = new WeatherTypes(cap_);
        CustList<WeatherType> keys_ = new CustList<WeatherType>(cap_);
        CustList<Rate> values_ = new CustList<Rate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getWeatherType(c));
            } else {
                values_.add(DocumentReaderMathUtil.getRate(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.addEntry(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static NbFightCoordss getMapNbFightCoordsBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        NbFightCoordss map_ = new NbFightCoordss(cap_);
        CustList<NbFightCoords> keys_ = new CustList<NbFightCoords>(cap_);
        CustList<BoolVal> values_ = new CustList<BoolVal>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getNbFightCoords(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getBoolVal(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static MoveTeamPositionsActivityOfMove getMapMoveTeamPositionActivityOfMove(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        MoveTeamPositionsActivityOfMove map_ = new MoveTeamPositionsActivityOfMove(cap_);
        CustList<MoveTeamPosition> keys_ = new CustList<MoveTeamPosition>(cap_);
        CustList<ActivityOfMove> values_ = new CustList<ActivityOfMove>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getMoveTeamPosition(c));
            } else {
                values_.add(getActivityOfMove(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static MoveTeamPositionsAffectedMove getMapMoveTeamPositionAffectedMove(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        MoveTeamPositionsAffectedMove map_ = new MoveTeamPositionsAffectedMove(cap_);
        CustList<MoveTeamPosition> keys_ = new CustList<MoveTeamPosition>(cap_);
        CustList<AffectedMove> values_ = new CustList<AffectedMove>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getMoveTeamPosition(c));
            } else {
                values_.add(getAffectedMove(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static MoveTeamPositionsStringList getMapMoveTeamPositionStringList(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        MoveTeamPositionsStringList map_ = new MoveTeamPositionsStringList(cap_);
        CustList<MoveTeamPosition> keys_ = new CustList<MoveTeamPosition>(cap_);
        CustList<StringList> values_ = new CustList<StringList>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getMoveTeamPosition(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getStringList(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static MoveTeamPositionsBoolVal getMapMoveTeamPositionBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        MoveTeamPositionsBoolVal map_ = new MoveTeamPositionsBoolVal(cap_);
        CustList<MoveTeamPosition> keys_ = new CustList<MoveTeamPosition>(cap_);
        CustList<BoolVal> values_ = new CustList<BoolVal>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getMoveTeamPosition(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getBoolVal(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static MoveTeamPositionsShort getMapMoveTeamPositionShort(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        MoveTeamPositionsShort map_ = new MoveTeamPositionsShort(cap_);
        CustList<MoveTeamPosition> keys_ = new CustList<MoveTeamPosition>(cap_);
        CustList<Short> values_ = new CustList<Short>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getMoveTeamPosition(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getShort(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static MoveTargets getMapMoveTargetMoveTarget(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        MoveTargets map_ = new MoveTargets(cap_);
        CustList<MoveTarget> keys_ = new CustList<MoveTarget>(cap_);
        CustList<MoveTarget> values_ = new CustList<MoveTarget>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getMoveTarget(c));
            } else {
                values_.add(getMoveTarget(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static MiniMapCoordsList getMapMiniMapCoordsTileMiniMap(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        MiniMapCoordsList map_ = new MiniMapCoordsList(cap_);
        CustList<MiniMapCoords> keys_ = new CustList<MiniMapCoords>(cap_);
        CustList<TileMiniMap> values_ = new CustList<TileMiniMap>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getMiniMapCoords(c));
            } else {
                values_.add(getTileMiniMap(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.addEntry(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static PlaceInterConnects getMapPlaceInterConnectCoords(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        PlaceInterConnects map_ = new PlaceInterConnects(cap_);
        CustList<PlaceInterConnect> keys_ = new CustList<PlaceInterConnect>(cap_);
        CustList<Coords> values_ = new CustList<Coords>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPlaceInterConnect(c));
            } else {
                values_.add(getCoords(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.addEntry(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static CoordssHostPokemonDuo getMapCoordsHostPokemonDuo(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        CoordssHostPokemonDuo map_ = new CoordssHostPokemonDuo(cap_);
        CustList<Coords> keys_ = new CustList<Coords>(cap_);
        CustList<HostPokemonDuo> values_ = new CustList<HostPokemonDuo>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getCoords(c));
            } else {
                values_.add(getHostPokemonDuo(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static CoordsLists getMapCoordsListCoords(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        CoordsLists map_ = new CoordsLists(cap_);
        CustList<Coords> keys_ = new CustList<Coords>(cap_);
        CustList<Condition> values_ = new CustList<Condition>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getCoords(c));
            } else {
                values_.add(getListCoords(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.addEntry(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static CoordssBoolVal getMapCoordsBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        CoordssBoolVal map_ = new CoordssBoolVal(cap_);
        CustList<Coords> keys_ = new CustList<Coords>(cap_);
        CustList<BoolVal> values_ = new CustList<BoolVal>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getCoords(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getBoolVal(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static LevelPoints getMapLevelPointLink(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        LevelPoints map_ = new LevelPoints(cap_);
        CustList<LevelPoint> keys_ = new CustList<LevelPoint>(cap_);
        CustList<Link> values_ = new CustList<Link>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getLevelPoint(c));
            } else {
                values_.add(getLink(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.addEntry(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static Points<Building> getMapPointBuilding(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        Points<Building> map_ = new PointsBuilding(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<Building> values_ = new CustList<Building>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(getBuilding(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static Points<CharacterInRoadCave> getMapPointCharacterInRoadCave(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        Points<CharacterInRoadCave> map_ = new PointsCharacterInRoadCave(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<CharacterInRoadCave> values_ = new CustList<CharacterInRoadCave>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(getCharacterInRoadCave(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static Points<DualFight> getMapPointDualFight(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        Points<DualFight> map_ = new PointsDualFight(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<DualFight> values_ = new CustList<DualFight>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(getDualFight(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static Points<GymTrainer> getMapPointGymTrainer(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        Points<GymTrainer> map_ = new PointsGymTrainer(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<GymTrainer> values_ = new CustList<GymTrainer>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(getGymTrainer(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static Points<Person> getMapPointPerson(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        Points<Person> map_ = new PointsPerson(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<Person> values_ = new CustList<Person>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(getPerson(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static Points<Block> getMapPointBlock(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        Points<Block> map_ = new PointsBlock(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<Block> values_ = new CustList<Block>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(getBlock(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static Points<Link> getMapPointLink(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        Points<Link> map_ = new PointsLink(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<Link> values_ = new CustList<Link>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(getLink(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static Points<WildPk> getMapPointWildPk(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        Points<WildPk> map_ = new PointsWildPk(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<WildPk> values_ = new CustList<WildPk>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(getWildPk(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static Points<Short> getMapPointShort(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        Points<Short> map_ = new PointsShort(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<Short> values_ = new CustList<Short>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getShort(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static Points<String> getMapPointString(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        Points<String> map_ = new PointsString(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<String> values_ = new CustList<String>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getString(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ListEffectCombos getMapStringListEffectCombo(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ListEffectCombos map_ = new ListEffectCombos(cap_);
        CustList<StringList> keys_ = new CustList<StringList>(cap_);
        CustList<EffectCombo> values_ = new CustList<EffectCombo>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getStringList(c));
            } else {
                values_.add(getEffectCombo(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.add(new ListEffectCombo(keys_.get(i), values_.get(i)));
        }
        return map_;
    }
    private static ListActivityOfMoves getMapStringListActivityOfMove(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ListActivityOfMoves map_ = new ListActivityOfMoves(cap_);
        CustList<StringList> keys_ = new CustList<StringList>(cap_);
        CustList<ActivityOfMove> values_ = new CustList<ActivityOfMove>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getStringList(c));
            } else {
                values_.add(getActivityOfMove(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.add(new ListActivityOfMove(keys_.get(i), values_.get(i)));
        }
        return map_;
    }

    private static StringMap<Evolution> getStringMapEvolution(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<Evolution> map_ = new StringMap<Evolution>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<Evolution> values_ = new CustList<Evolution>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getEvolution(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<EfficiencyRate> getStringMapEfficiencyRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<EfficiencyRate> map_ = new StringMap<EfficiencyRate>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<EfficiencyRate> values_ = new CustList<EfficiencyRate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getEfficiencyRate(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<TypeDamageBoost> getStringMapTypeDamageBoost(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<TypeDamageBoost> map_ = new StringMap<TypeDamageBoost>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<TypeDamageBoost> values_ = new CustList<TypeDamageBoost>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getTypeDamageBoost(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<UsesOfMove> getStringMapUsesOfMove(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<UsesOfMove> map_ = new StringMap<UsesOfMove>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<UsesOfMove> values_ = new CustList<UsesOfMove>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getUsesOfMove(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<ActivityOfMove> getStringMapActivityOfMove(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<ActivityOfMove> map_ = new StringMap<ActivityOfMove>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<ActivityOfMove> values_ = new CustList<ActivityOfMove>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getActivityOfMove(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<CopiedMove> getStringMapCopiedMove(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<CopiedMove> map_ = new StringMap<CopiedMove>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<CopiedMove> values_ = new CustList<CopiedMove>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getCopiedMove(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<MovesAbilities> getStringMapMovesAbilities(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<MovesAbilities> map_ = new StringMap<MovesAbilities>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<MovesAbilities> values_ = new CustList<MovesAbilities>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getMovesAbilities(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<IdList<Statistic>> getStringMapListStatistic(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<IdList<Statistic>> map_ = new StringMap<IdList<Statistic>>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<IdList<Statistic>> values_ = new CustList<IdList<Statistic>>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getListStatistic(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<IdMap<Statistic,Byte>> getStringMapMapStatisticByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<IdMap<Statistic,Byte>> map_ = new StringMap<IdMap<Statistic,Byte>>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<IdMap<Statistic,Byte>> values_ = new CustList<IdMap<Statistic,Byte>>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getMapStatisticByte(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<ByteMap<Anticipation>> getStringMapMapByteAnticipation(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<ByteMap<Anticipation>> map_ = new StringMap<ByteMap<Anticipation>>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<ByteMap<Anticipation>> values_ = new CustList<ByteMap<Anticipation>>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getMapByteAnticipation(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<ByteMap<StacksOfUses>> getStringMapMapByteStacksOfUses(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<ByteMap<StacksOfUses>> map_ = new StringMap<ByteMap<StacksOfUses>>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<ByteMap<StacksOfUses>> values_ = new CustList<ByteMap<StacksOfUses>>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getMapByteStacksOfUses(c));
            }
        }
        int min_ = NumberUtil.min(keys_.size(), values_.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }

    public static LoadingGame newLoadingGame() {
        LoadingGame object_ = new LoadingGame();
        object_.setLastRom(DataBase.EMPTY_STRING);
        object_.setLastSavedGame(DataBase.EMPTY_STRING);
        return object_;
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

    public static Sex getSexByName(String _env, SexListInt _sexList) {
        return Sex.getSexByName(_env,_sexList);
    }

    public static ExpType getExpTypeByName(String _env) {
        return ExpType.getExpTypeByName(_env);
    }
    public static EnvironmentType getEnvByName(String _env) {
        return EnvironmentType.getEnvByName(_env);
    }
    public static Gender getGenderByName(String _env) {
        return Gender.getGenderByName(_env);
    }

    public static TargetChoice getTargetChoiceByName(String _env) {
        return TargetChoice.getTargetChoiceByName(_env);
    }
}
