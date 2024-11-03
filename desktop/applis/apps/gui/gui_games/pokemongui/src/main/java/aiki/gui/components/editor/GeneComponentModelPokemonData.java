package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.util.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelPokemonData implements GeneComponentModel<PokemonData> {
    private final SubscribedTranslationList subscribedTranslationList;
    private final AbstractProgramInfos compoFactory;
    private final FacadeGame facade;
    private final GeneComponentModelRate weight;
    private final GeneComponentModelRate height;
    private GeneComponentModelLsStrSub types;
    private GeneComponentModelLsStrSub abilities;
    private GeneComponentModelLsStrSub moveTutors;
    private final IdMap<Statistic, FormStatBaseEv> statistics = new IdMap<Statistic, FormStatBaseEv>();
    private GeneComponentModelEltEnum<GenderRepartition> genderRep;
    private GeneComponentModelEltStrSub baseEvo;
    private GeneComponentModelEltEnum<ExpType> expEvo;
    private final CrudGeneFormListSubLevelMove levMoves;
    private final CrudGeneFormEvolutions evolutions;
    private PokemonData element;

    public GeneComponentModelPokemonData(AbsCommonFrame _fr, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        this.compoFactory = _core;
        facade = _facade;
        subscribedTranslationList = _sub;
        weight = new GeneComponentModelRate(compoFactory);
        height = new GeneComponentModelRate(compoFactory);
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
        return ids_;
    }

    public GeneComponentModelRate getWeight() {
        return weight;
    }

    public GeneComponentModelRate getHeight() {
        return height;
    }

    public GeneComponentModelLsStrSub getTypes() {
        return types;
    }

    public GeneComponentModelLsStrSub getAbilities() {
        return abilities;
    }

    public GeneComponentModelLsStrSub getMoveTutors() {
        return moveTutors;
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
}
