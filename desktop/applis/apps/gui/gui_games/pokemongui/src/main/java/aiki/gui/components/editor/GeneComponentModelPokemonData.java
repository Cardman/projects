package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.pokemon.evolution.*;
import aiki.fight.util.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.MessagesDataPokemonData;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.*;

public final class GeneComponentModelPokemonData extends GeneComponentModelEntity<PokemonData> {
    private final GeneComponentModelRate weight;
    private final GeneComponentModelRate height;
    private final GeneComponentModelLong catchingRate;
    private final GeneComponentModelLong expRate;
    private final GeneComponentModelLgInt hatchingSteps;
    private final GeneComponentModelLong happiness;
    private final GeneComponentModelLong happinessHatch;
    private GeneComponentModelLsStrSub<String,StringList> types;
    private GeneComponentModelLsStrSub<String,StringList> abilities;
    private GeneComponentModelLsStrSub<String,StringList> moveTutors;
    private GeneComponentModelLsStrSub<Integer,Ints> technicalMoves;
    private GeneComponentModelLsStrSub<Integer,Ints> hiddenMoves;
    private final IdMap<Statistic, FormStatBaseEv> statistics = new IdMap<Statistic, FormStatBaseEv>();
    private GeneComponentModelElt<GenderRepartition> genderRep;
    private GeneComponentModelEltEnumSub<String> baseEvo;
    private GeneComponentModelElt<ExpType> expEvo;
    private final CrudGeneFormSimpleElementSub<LevelMove> levMoves;
    private final CrudGeneFormSimpleFormSub<String,Evolution> evolutions;
    private final CrudGeneFormSimpleElementSub<String> eggGroups;
    private final IdList<SubscribedTranslation> subscribedTranslations = new IdList<SubscribedTranslation>();
    private PokemonData edited;

    public GeneComponentModelPokemonData(AbsCommonFrame _fr, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_fr,_core, _facade, _sub);
        weight = new GeneComponentModelRate(getCompoFactory());
        height = new GeneComponentModelRate(getCompoFactory());
        catchingRate = new GeneComponentModelLong(getCompoFactory());
        expRate = new GeneComponentModelLong(getCompoFactory());
        hatchingSteps = new GeneComponentModelLgInt(getCompoFactory());
        eggGroups = new CrudGeneFormSimpleElementSub<String>(getCompoFactory(),getFacade(),getSubscribedTranslationList(),getFrame());
        happiness = new GeneComponentModelLong(getCompoFactory());
        happinessHatch = new GeneComponentModelLong(getCompoFactory());
        levMoves = new CrudGeneFormSimpleElementSub<LevelMove>(getCompoFactory(),_facade,_sub,_fr);
        evolutions = new CrudGeneFormSimpleFormSub<String,Evolution>(_core,_facade,_sub,_fr);
    }
    @Override
    public AbsCustComponent gene(int _select) {
        SubscribedTranslationMessagesFactoryPk factoryPk_ = getSubscribedTranslationList().getFactoryPk();
        buildKey(_select,factoryPk_,factoryPk_.all(getFacade()).getKeys());
        statistics.clear();
        genderRep =ConverterCommonMapUtil.buildGenderRepartition(getCompoFactory());
        expEvo =ConverterCommonMapUtil.buildExpType(getCompoFactory());
        types = ConverterCommonMapUtil.buildTypeList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        abilities = ConverterCommonMapUtil.buildAbilityList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        moveTutors = ConverterCommonMapUtil.buildMoveList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        baseEvo = ConverterCommonMapUtil.buildPkFull(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        technicalMoves = ConverterCommonMapUtil.buildTmList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        hiddenMoves = ConverterCommonMapUtil.buildHmList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        AbsCompoFactory compoFactory_ = getCompoFactory().getCompoFactory();
        AbsPanel page_ = compoFactory_.newPageBox();
        page_.add(geneComponentModelSelectKey());
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(line(MessagesDataPokemonData.M_P_72_WEIGHT_INTRO, weight.geneRate()));
        form_.add(line(MessagesDataPokemonData.M_P_72_HEIGHT_INTRO, height.geneRate()));
        form_.add(line(MessagesDataPokemonData.M_P_72_TYPES_INTRO,types.geneEnum()));
        AbsPanel stats_ = compoFactory_.newPageBox();
        subscribedTranslations.clear();
        for (Statistic s: Statistic.getStatisticsWithBase()) {
            FormStatBaseEv f_ = new FormStatBaseEv(getCompoFactory());
            statistics.addEntry(s, f_);
            subscribedTranslations.add(new UpdateStatEvMessage(s, f_));
            stats_.add(f_.row(getFacade().getData().getTranslatedStatistics().getVal(getCompoFactory().getLanguage()).getVal(s)));
        }
        updateStats(new IdMap<Statistic, StatBaseEv>());
        form_.add(line(MessagesDataPokemonData.M_P_72_STATISTICS_INTRO,stats_));
        form_.add(line(MessagesDataPokemonData.M_P_72_GENDERS_INTRO,genderRep.geneEnum()));
        form_.add(line(MessagesDataPokemonData.M_P_72_ABILITIES_INTRO,abilities.geneEnum()));
        form_.add(line(MessagesDataPokemonData.M_P_72_MOVE_TUTORS_INTRO,moveTutors.geneEnum()));
        form_.add(line(MessagesDataPokemonData.M_P_72_BASE_INTRO,baseEvo.geneEnum()));
        form_.add(line(MessagesDataPokemonData.M_P_72_EXP_GROWTH_INTRO,expEvo.geneEnum()));
        levMoves.initForm(new DisplayEntryCustSubElementLevelMove(getCompoFactory(),getFacade(),getSubscribedTranslationList()), new GeneComponentModelSubscribeFactoryDirect<LevelMove>(new GeneComponentModelSubscribeLevelMove(getCompoFactory(),getFacade(),getSubscribedTranslationList())));
        form_.add(line(MessagesDataPokemonData.M_P_72_LEVEL_MOVES_INTRO,levMoves.getGroup()));
        evolutions.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Evolution>(getSubscribedTranslationList().getFactoryPk(),getCompoFactory(),getFacade(), new StringMap<String>()),buildPart(getSubscribedTranslationList().getFactoryPk(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Evolution>(new GeneComponentModelSubscribeEvolution(getCompoFactory(),getFacade(),getSubscribedTranslationList(),evolutions.getCommonFrame())));
        form_.add(line(MessagesDataPokemonData.M_P_72_EVOLUTIONS_TITLE,evolutions.getGroup()));
        form_.add(line(MessagesDataPokemonData.M_P_72_TECHNICAL_MOVES_INTRO,technicalMoves.geneEnum()));
        form_.add(line(MessagesDataPokemonData.M_P_72_HIDDEN_MOVES_INTRO,hiddenMoves.geneEnum()));
        form_.add(line(MessagesDataPokemonData.M_P_72_CATCHINGRATE_INTRO,catchingRate.geneLong()));
        form_.add(line(MessagesDataPokemonData.M_P_72_PTS_EXP_INTRO,expRate.geneLong()));
        form_.add(line(MessagesDataPokemonData.M_P_72_HATCHING_INTRO,hatchingSteps.geneLgInt()));
        form_.add(line(MessagesDataPokemonData.M_P_72_HAPPINESS,happiness.geneLong()));
        form_.add(line(MessagesDataPokemonData.M_P_72_HAPPINESS_HATCH,happinessHatch.geneLong()));
        eggGroups.initForm(new DisplayEntryCustSubElementString(),new GeneComponentModelSubscribeFactoryDirect<String>(new GeneComponentModelSubscribeString(getCompoFactory(),getFacade())));
        form_.add(line(MessagesDataPokemonData.M_P_72_EGG_GROUPS_INTRO,eggGroups.getGroup()));
        sc_.setViewportView(form_);
        page_.add(sc_);
        edited = Instances.newPokemonData();
        getFacade().getData().getPokedex().put(DataBase.EMPTY_STRING,edited);
        return page_;
    }
    private AbsCustComponent line(String _key, AbsCustComponent _input) {
        return line(MessagesPkBean.PK_DATA,_key,_input);
    }

    private GeneComponentModelSubscribeFactorySelElt buildPart(SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(getCompoFactory(), getFacade(), _facto, _abs);
    }

    @Override
    public EditedCrudPair<String,PokemonData> value() {
        edited.setWeight(weight.valueRate());
        edited.setHeight(height.valueRate());
        edited.setTypes(types.tryRet());
        edited.setAbilities(abilities.tryRet());
        edited.setMoveTutors(moveTutors.tryRet());
        edited.setStatistics(new IdMap<Statistic, StatBaseEv>());
        for (EntryCust<Statistic,FormStatBaseEv> e: statistics.entryList()) {
            edited.getStatistics().addEntry(e.getKey(),new StatBaseEv(e.getValue().getBase().getValue(),e.getValue().getEv().getValue()));
        }
        edited.setGenderRep(genderRep.tryRet());
        edited.setBaseEvo(baseEvo.tryRet());
        edited.setExpEvo(expEvo.tryRet());
        edited.setLevMoves(levMoves.getList());
        edited.setEvolutions(ConverterCommonMapUtil.buildStringMapEvolution(evolutions.getList()));
        edited.setTechnicalMoves(technicalMoves.tryRet());
        edited.setHiddenMoves(hiddenMoves.tryRet());
        edited.setCatchingRate(catchingRate.valueLong());
        edited.setExpRate(expRate.valueLong());
        edited.setHatchingSteps(hatchingSteps.valueLgInt());
        edited.setHappiness(happiness.valueLong());
        edited.setHappinessHatch(happinessHatch.valueLong());
        edited.setEggGroups(new StringList(eggGroups.getList()));
        return new EditedCrudPair<String, PokemonData>(getGeneComponentModelSelectKey().tryRet(),edited);
    }

    @Override
    public void value(EditedCrudPair<String,PokemonData> _v) {
        getGeneComponentModelSelectKey().setupValue(_v.getKey());
        updateSelector();
        updateForm(ConverterCommonMapUtil.copyPokemonData(_v.getValue()));
    }

    private void updateForm(PokemonData _v) {
        getFacade().getData().getPokedex().put(DataBase.EMPTY_STRING,_v);
        edited = _v;
        getWeight().valueRate(_v.getWeight());
        getHeight().valueRate(_v.getHeight());
        getTypes().setupValue(_v.getTypes());
        getAbilities().setupValue(_v.getAbilities());
        getMoveTutors().setupValue(_v.getMoveTutors());
        updateStats(_v.getStatistics());
        getGenderRep().setupValue(_v.getGenderRep());
        getBaseEvo().setupValue(_v.getBaseEvo());
        getExpEvo().setupValue(_v.getExpEvo());
        getLevMoves().setupValues(_v.getLevMoves());
        getEvolutions().setupValues(new MapToEntriesListUtil<String,Evolution>().build(_v.getEvolutions()));
        getTechnicalMoves().setupValue(_v.getTechnicalMoves());
        getHiddenMoves().setupValue(_v.getHiddenMoves());
        getCatchingRate().valueLong(_v.getCatchingRate());
        getExpRate().valueLong(_v.getCatchingRate());
        getHatchingSteps().valueLgInt(_v.getHatchingSteps());
        getHappiness().valueLong(_v.getHappiness());
        getHappinessHatch().valueLong(_v.getHappinessHatch());
        getEggGroups().setupValues(_v.getEggGroups());
    }

    private void updateStats(IdMap<Statistic, StatBaseEv> _v) {
        for (EntryCust<Statistic,FormStatBaseEv> e: getStatistics().entryList()) {
            StatBaseEv stat_ = _v.getVal(e.getKey());
            if (stat_ != null) {
                e.getValue().getEv().setValue(stat_.getEv());
                e.getValue().getBase().setValue(stat_.getBase());
            }
        }
    }
    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(getGeneComponentModelSelectKey().getSubs());
        ids_.addAllElts(getTypes().getSubs());
        ids_.addAllElts(getAbilities().getSubs());
        ids_.addAllElts(getMoveTutors().getSubs());
        ids_.addAllElts(getBaseEvo().getSubs());
        ids_.addAllElts(getEvolutions().subscribeButtons());
        ids_.addAllElts(getLevMoves().subscribeButtons());
        ids_.addAllElts(getTechnicalMoves().getSubs());
        ids_.addAllElts(getHiddenMoves().getSubs());
        ids_.addAllElts(getEggGroups().subscribeButtons());
        ids_.addAllElts(subscribedTranslations);
        return ids_;
    }

    public GeneComponentModelRate getWeight() {
        return weight;
    }

    public GeneComponentModelRate getHeight() {
        return height;
    }

    public GeneComponentModelLsStrSub<String,StringList> getTypes() {
        return types;
    }

    public GeneComponentModelLsStrSub<String,StringList> getAbilities() {
        return abilities;
    }

    public GeneComponentModelLsStrSub<String,StringList> getMoveTutors() {
        return moveTutors;
    }

    public GeneComponentModelLsStrSub<Integer,Ints> getTechnicalMoves() {
        return technicalMoves;
    }

    public GeneComponentModelLsStrSub<Integer,Ints> getHiddenMoves() {
        return hiddenMoves;
    }

    public IdMap<Statistic, FormStatBaseEv> getStatistics() {
        return statistics;
    }

    public GeneComponentModelElt<GenderRepartition> getGenderRep() {
        return genderRep;
    }

    public GeneComponentModelEltEnumSub<String> getBaseEvo() {
        return baseEvo;
    }

    public GeneComponentModelElt<ExpType> getExpEvo() {
        return expEvo;
    }

    public CrudGeneFormSimpleElementSub<LevelMove> getLevMoves() {
        return levMoves;
    }

    public CrudGeneFormSimpleFormSub<String,Evolution> getEvolutions() {
        return evolutions;
    }

    public CrudGeneFormSimpleElementSub<String> getEggGroups() {
        return eggGroups;
    }

    public GeneComponentModelLong getCatchingRate() {
        return catchingRate;
    }

    public GeneComponentModelLong getHappiness() {
        return happiness;
    }

    public GeneComponentModelLong getHappinessHatch() {
        return happinessHatch;
    }

    public GeneComponentModelLgInt getHatchingSteps() {
        return hatchingSteps;
    }

    public GeneComponentModelLong getExpRate() {
        return expRate;
    }
}
