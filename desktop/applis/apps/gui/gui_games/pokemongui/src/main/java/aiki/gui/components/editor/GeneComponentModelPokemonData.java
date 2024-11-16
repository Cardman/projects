package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.pokemon.evolution.*;
import aiki.fight.util.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class GeneComponentModelPokemonData extends GeneComponentModelEntity<PokemonData> {
    private final GeneComponentModelRate weight;
    private final GeneComponentModelRate height;
    private final GeneComponentModelInt catchingRate;
    private final GeneComponentModelLong expRate;
    private final GeneComponentModelLgInt hatchingSteps;
    private final GeneComponentModelInt happiness;
    private final GeneComponentModelInt happinessHatch;
    private GeneComponentModelLsStrSub<String> types;
    private GeneComponentModelLsStrSub<String> abilities;
    private GeneComponentModelLsStrSub<String> moveTutors;
    private GeneComponentModelLsStrSub<Short> technicalMoves;
    private GeneComponentModelLsStrSub<Short> hiddenMoves;
    private final IdMap<Statistic, FormStatBaseEv> statistics = new IdMap<Statistic, FormStatBaseEv>();
    private GeneComponentModelEltEnum<GenderRepartition> genderRep;
    private GeneComponentModelEltStrSub baseEvo;
    private GeneComponentModelEltEnum<ExpType> expEvo;
    private final CrudGeneFormSimpleElement<LevelMove> levMoves;
    private final CrudGeneFormSimpleForm<String,Evolution> evolutions;
    private final CrudGeneFormList<String> eggGroups;
    private final IdList<SubscribedTranslation> subscribedTranslations = new IdList<SubscribedTranslation>();

    public GeneComponentModelPokemonData(AbsCommonFrame _fr, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_core, _facade, _sub);
        weight = new GeneComponentModelRate(getCompoFactory());
        height = new GeneComponentModelRate(getCompoFactory());
        catchingRate = new GeneComponentModelInt(getCompoFactory());
        expRate = new GeneComponentModelLong(getCompoFactory());
        hatchingSteps = new GeneComponentModelLgInt(getCompoFactory());
        eggGroups = new CrudGeneFormList<String>(getCompoFactory());
        happiness = new GeneComponentModelInt(getCompoFactory());
        happinessHatch = new GeneComponentModelInt(getCompoFactory());
        levMoves = new CrudGeneFormSimpleElement<LevelMove>(getCompoFactory(),_facade,_sub,_fr);
        evolutions = new CrudGeneFormSimpleForm<String,Evolution>(_core,_facade,_sub,_fr);
    }
    @Override
    public AbsCustComponent gene(int _select) {
        SubscribedTranslationMessagesFactoryPk factoryPk_ = getSubscribedTranslationList().getFactoryPk();
        buildKey(_select,factoryPk_,factoryPk_.all(getFacade()).getKeys());
        statistics.clear();
        genderRep =ConverterCommonMapUtil.buildGenderRepartition(getCompoFactory());
        expEvo =ConverterCommonMapUtil.buildExpType(getCompoFactory(),getFacade());
        types = ConverterCommonMapUtil.buildTypeList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        abilities = ConverterCommonMapUtil.buildAbilityList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        moveTutors = ConverterCommonMapUtil.buildMoveList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        baseEvo = ConverterCommonMapUtil.buildPkFull(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        technicalMoves = ConverterCommonMapUtil.buildTmList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        hiddenMoves = ConverterCommonMapUtil.buildHmList(getCompoFactory(),getFacade(),getSubscribedTranslationList());
        AbsCompoFactory compoFactory_ = getCompoFactory().getCompoFactory();
        AbsPanel page_ = compoFactory_.newPageBox();
        page_.add(getGeneComponentModelSelectKey().geneEnum());
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(weight.geneRate(Rate.zero()));
        form_.add(height.geneRate(Rate.zero()));
        form_.add(types.geneEnum());
        AbsPanel stats_ = compoFactory_.newPageBox();
        subscribedTranslations.clear();
        for (Statistic s: Statistic.getStatisticsWithBase()) {
            FormStatBaseEv f_ = new FormStatBaseEv(getCompoFactory());
            statistics.addEntry(s, f_);
            subscribedTranslations.add(new UpdateStatEvMessage(s, f_));
            stats_.add(f_.row(getFacade().getData().getTranslatedStatistics().getVal(getCompoFactory().getLanguage()).getVal(s)));
        }
        updateStats(new IdMap<Statistic, StatBaseEv>());
        form_.add(stats_);
        form_.add(genderRep.geneEnum(GenderRepartition.MIXED));
        form_.add(abilities.geneEnum());
        form_.add(moveTutors.geneEnum());
        form_.add(baseEvo.geneEnum());
        form_.add(expEvo.geneEnum(ExpType.M));
        levMoves.initForm(new DisplayEntryCustSubElementLevelMove(getCompoFactory(),getFacade(),getSubscribedTranslationList()),getCompoFactory(), new GeneComponentModelSubscribeFactoryLevelMove(getCompoFactory(),getFacade(),getSubscribedTranslationList()), new CustList<LevelMove>());
        form_.add(levMoves.getGroup());
        evolutions.initForm();
        evolutions.initForm(new DisplayEntryCustSubImpl<String>(getSubscribedTranslationList().getFactoryPk(), new StringMap<String>()),getSubscribedTranslationList().getFactoryPk().buildMessages(getCompoFactory(),getFacade()),getCompoFactory(),buildPart(getSubscribedTranslationList().getFactoryPk(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryEvolution(getCompoFactory(),getFacade(),getSubscribedTranslationList(),evolutions.getFrame()), new StringMap<Evolution>());
        form_.add(evolutions.getGroup());
        form_.add(technicalMoves.geneEnum());
        form_.add(hiddenMoves.geneEnum());
        form_.add(catchingRate.geneInt());
        form_.add(expRate.gene(0L));
        form_.add(hatchingSteps.gene(LgInt.zero()));
        form_.add(happiness.geneInt());
        form_.add(happinessHatch.geneInt());
        eggGroups.initForm();
        eggGroups.initForm(new IntStringDisplayEntryCust(),new GeneComponentModelString(getCompoFactory(),new StringList(),new DefValidateText()),new CustList<String>());
        form_.add(eggGroups.getGroup());
        sc_.setViewportView(form_);
        page_.add(sc_);
        return page_;
    }

    private GeneComponentModelSubscribeFactorySelElt buildPart(SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(getCompoFactory(), getFacade(), _facto, _abs);
    }

    @Override
    public EditedCrudPair<String,PokemonData> value() {
        PokemonData ent_ = Instances.newPokemonData();
        ent_.setWeight(weight.valueRate());
        ent_.setHeight(height.valueRate());
        ent_.setTypes(new StringList(types.tryRet()));
        ent_.setAbilities(new StringList(abilities.tryRet()));
        ent_.setMoveTutors(new StringList(moveTutors.tryRet()));
        for (EntryCust<Statistic,FormStatBaseEv> e: statistics.entryList()) {
            ent_.getStatistics().addEntry(e.getKey(),new StatBaseEv((short)e.getValue().getBase().getValue(),(short)e.getValue().getEv().getValue()));
        }
        ent_.setGenderRep(genderRep.tryRet(GenderRepartition.MIXED));
        ent_.setBaseEvo(baseEvo.tryRet());
        ent_.setExpEvo(expEvo.tryRet(ExpType.M));
        ent_.setLevMoves(levMoves.getList());
        ent_.setEvolutions(ConverterCommonMapUtil.buildStringMapEvolution(evolutions.getList()));
        ent_.setTechnicalMoves(new Shorts(technicalMoves.tryRet()));
        ent_.setHiddenMoves(new Shorts(hiddenMoves.tryRet()));
        ent_.setCatchingRate((short) catchingRate.valueInt());
        ent_.setExpRate(expRate.valueLong());
        ent_.setHatchingSteps(hatchingSteps.valueLgInt());
        ent_.setHappiness((short) happiness.valueInt());
        ent_.setHappinessHatch((short) happinessHatch.valueInt());
        ent_.setEggGroups(new StringList(eggGroups.getList()));
        return new EditedCrudPair<String, PokemonData>(getGeneComponentModelSelectKey().tryRet(),ent_);
    }

    @Override
    public void value(EditedCrudPair<String,PokemonData> _v) {
        getGeneComponentModelSelectKey().setupValue(_v.getKey());
        updateSelector();
        updateForm(_v.getValue());
    }

    private void updateForm(PokemonData _v) {
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
        getCatchingRate().valueInt(_v.getCatchingRate());
        getExpRate().valueLong(_v.getCatchingRate());
        getHatchingSteps().valueLgInt(_v.getHatchingSteps());
        getHappiness().valueInt(_v.getHappiness());
        getHappinessHatch().valueInt(_v.getHappinessHatch());
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
        ids_.addAllElts(subscribedTranslations);
        return ids_;
    }

    public GeneComponentModelRate getWeight() {
        return weight;
    }

    public GeneComponentModelRate getHeight() {
        return height;
    }

    public GeneComponentModelLsStrSub<String> getTypes() {
        return types;
    }

    public GeneComponentModelLsStrSub<String> getAbilities() {
        return abilities;
    }

    public GeneComponentModelLsStrSub<String> getMoveTutors() {
        return moveTutors;
    }

    public GeneComponentModelLsStrSub<Short> getTechnicalMoves() {
        return technicalMoves;
    }

    public GeneComponentModelLsStrSub<Short> getHiddenMoves() {
        return hiddenMoves;
    }

    public IdMap<Statistic, FormStatBaseEv> getStatistics() {
        return statistics;
    }

    public GeneComponentModelEltEnum<GenderRepartition> getGenderRep() {
        return genderRep;
    }

    public GeneComponentModelEltStrSub getBaseEvo() {
        return baseEvo;
    }

    public GeneComponentModelEltEnum<ExpType> getExpEvo() {
        return expEvo;
    }

    public CrudGeneFormSimpleElement<LevelMove> getLevMoves() {
        return levMoves;
    }

    public CrudGeneFormSimpleForm<String,Evolution> getEvolutions() {
        return evolutions;
    }

    public CrudGeneFormList<String> getEggGroups() {
        return eggGroups;
    }

    public GeneComponentModelInt getCatchingRate() {
        return catchingRate;
    }

    public GeneComponentModelInt getHappiness() {
        return happiness;
    }

    public GeneComponentModelInt getHappinessHatch() {
        return happinessHatch;
    }

    public GeneComponentModelLgInt getHatchingSteps() {
        return hatchingSteps;
    }

    public GeneComponentModelLong getExpRate() {
        return expRate;
    }
}
