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
import code.gui.events.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class GeneComponentModelPokemonData implements GeneComponentModel<PokemonData> {
    private final SubscribedTranslationList subscribedTranslationList;
    private final AbstractProgramInfos compoFactory;
    private final FacadeGame facade;
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
    private final CrudGeneFormListSubLevelMove levMoves;
    private final CrudGeneFormEvolutions evolutions;
    private final CrudGeneFormList<String> eggGroups;
    private PokemonData element;

    public GeneComponentModelPokemonData(AbsCommonFrame _fr, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        this.compoFactory = _core;
        facade = _facade;
        subscribedTranslationList = _sub;
        weight = new GeneComponentModelRate(compoFactory);
        height = new GeneComponentModelRate(compoFactory);
        catchingRate = new GeneComponentModelInt(compoFactory);
        expRate = new GeneComponentModelLong(compoFactory);
        hatchingSteps = new GeneComponentModelLgInt(compoFactory);
        eggGroups = new CrudGeneFormList<String>(compoFactory);
        happiness = new GeneComponentModelInt(compoFactory);
        happinessHatch = new GeneComponentModelInt(compoFactory);
        levMoves = new CrudGeneFormListSubLevelMove(compoFactory,_facade,_sub,_fr);
        evolutions = new CrudGeneFormEvolutions(_core,_facade,_sub,_fr);
    }
    @Override
    public AbsCustComponent gene() {
        element = Instances.newPokemonData();
        statistics.clear();
        genderRep =ConverterCommonMapUtil.buildGenderRepartition(compoFactory);
        expEvo =ConverterCommonMapUtil.buildExpType(compoFactory,facade);
        types = ConverterCommonMapUtil.buildTypeList(compoFactory,facade,subscribedTranslationList);
        abilities = ConverterCommonMapUtil.buildAbilityList(compoFactory,facade,subscribedTranslationList);
        moveTutors = ConverterCommonMapUtil.buildMoveList(compoFactory,facade,subscribedTranslationList);
        baseEvo = ConverterCommonMapUtil.buildPkFull(compoFactory,facade,subscribedTranslationList);
        technicalMoves = ConverterCommonMapUtil.buildTmList(compoFactory,facade,subscribedTranslationList);
        hiddenMoves = ConverterCommonMapUtil.buildHmList(compoFactory,facade,subscribedTranslationList);
        AbsCompoFactory compoFactory_ = compoFactory.getCompoFactory();
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(weight.geneRate(element.getWeight()));
        form_.add(height.geneRate(element.getHeight()));
        form_.add(types.geneCommon(element.getTypes()));
        AbsPanel stats_ = compoFactory_.newPageBox();
        for (Statistic s: Statistic.getStatisticsWithBase()) {
            FormStatBaseEv f_ = new FormStatBaseEv(compoFactory);
            statistics.addEntry(s, f_);
            stats_.add(f_.row(facade.getData().getTranslatedStatistics().getVal(compoFactory.getLanguage()).getVal(s)));
        }
        updateStats(element);
        form_.add(stats_);
        form_.add(genderRep.geneEnum(element.getGenderRep()));
        form_.add(abilities.geneCommon(element.getAbilities()));
        form_.add(moveTutors.geneCommon(element.getMoveTutors()));
        form_.add(baseEvo.geneEnum());
        form_.add(expEvo.geneEnum(element.getExpEvo()));
        levMoves.initForm(compoFactory, element.getLevMoves());
        form_.add(levMoves.getGroup());
        evolutions.initForm();
        evolutions.initForm(compoFactory, element.getEvolutions());
        form_.add(evolutions.getGroup());
        form_.add(technicalMoves.geneCommon(element.getTechnicalMoves()));
        form_.add(hiddenMoves.geneCommon(element.getHiddenMoves()));
        form_.add(catchingRate.geneInt());
        form_.add(expRate.gene(0L));
        form_.add(hatchingSteps.gene(LgInt.zero()));
        form_.add(happiness.geneInt());
        form_.add(happinessHatch.geneInt());
        eggGroups.initForm();
        eggGroups.initForm(new IntStringDisplayEntryCust(),new GeneComponentModelString(compoFactory,new StringList(),new DefValidateText()),new CustList<String>());
        form_.add(eggGroups.getGroup());
        sc_.setViewportView(form_);
        return sc_;
    }

    @Override
    public PokemonData value() {
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
        ent_.setBaseEvo(baseEvo.tryRet(DataBase.EMPTY_STRING));
        ent_.setExpEvo(expEvo.tryRet(ExpType.M));
        ent_.setLevMoves(levMoves.getList());
        ent_.setEvolutions(new StringMap<Evolution>(evolutions.getList()));
        ent_.setTechnicalMoves(new Shorts(technicalMoves.tryRet()));
        ent_.setHiddenMoves(new Shorts(hiddenMoves.tryRet()));
        ent_.setCatchingRate((short) catchingRate.valueInt());
        ent_.setExpRate(expRate.valueLong());
        ent_.setHatchingSteps(hatchingSteps.valueLgInt());
        ent_.setHappiness((short) happiness.valueInt());
        ent_.setHappinessHatch((short) happinessHatch.valueInt());
        ent_.setEggGroups(new StringList(eggGroups.getList()));
        return ent_;
    }

    @Override
    public PokemonData value(PokemonData _v) {
        updateForm(_v);
        return element;
    }

    private void updateForm(PokemonData _v) {
        getWeight().valueRate(_v.getWeight());
        getHeight().valueRate(_v.getHeight());
        getTypes().setupValue(_v.getTypes());
        getAbilities().setupValue(_v.getAbilities());
        getMoveTutors().setupValue(_v.getMoveTutors());
        updateStats(_v);
        getGenderRep().setupValue(_v.getGenderRep());
        getBaseEvo().setupValue(_v.getBaseEvo());
        getExpEvo().setupValue(_v.getExpEvo());
        getLevMoves().setupValues(_v.getLevMoves());
        getEvolutions().setupValues(_v.getEvolutions());
        getTechnicalMoves().setupValue(_v.getTechnicalMoves());
        getHiddenMoves().setupValue(_v.getHiddenMoves());
        getCatchingRate().valueInt(_v.getCatchingRate());
        getExpRate().valueLong(_v.getCatchingRate());
        getHatchingSteps().valueLgInt(_v.getHatchingSteps());
        getHappiness().valueInt(_v.getHappiness());
        getHappinessHatch().valueInt(_v.getHappinessHatch());
        getEggGroups().setupValues(_v.getEggGroups());
    }

    private void updateStats(PokemonData _v) {
        for (EntryCust<Statistic,FormStatBaseEv> e: getStatistics().entryList()) {
            StatBaseEv stat_ = _v.getStatistics().getVal(e.getKey());
            if (stat_ != null) {
                e.getValue().getEv().setValue(stat_.getEv());
                e.getValue().getBase().setValue(stat_.getBase());
            }
        }
    }
    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(getTypes().getSubs());
        ids_.addAllElts(getAbilities().getSubs());
        ids_.addAllElts(getMoveTutors().getSubs());
        ids_.addAllElts(getBaseEvo().getSubs());
        ids_.addAllElts(getEvolutions().subscribeButtons());
        ids_.addAllElts(getLevMoves().subscribeButtons());
        ids_.addAllElts(getTechnicalMoves().getSubs());
        ids_.addAllElts(getHiddenMoves().getSubs());
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

    public CrudGeneFormListSubLevelMove getLevMoves() {
        return levMoves;
    }

    public CrudGeneFormEvolutions getEvolutions() {
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
