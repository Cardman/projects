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
    private final AbstractProgramInfos compoFactory;
    private final FacadeGame facade;
    private final GeneComponentModelRate weight;
    private final GeneComponentModelRate height;
    private final GeneComponentModelLsStrSub types;
    private final IdMap<Statistic, FormStatBaseEv> statistics = new IdMap<Statistic, FormStatBaseEv>();
    private final GeneComponentModelEltEnum<GenderRepartition> genderRep;
    private final GeneComponentModelEltStrSub baseEvo;
    private final GeneComponentModelEltEnum<ExpType> expEvo;
    private final CrudGeneFormListSubLevelMove levMoves;
    private final CrudGeneFormEvolutions evolutions;
    private PokemonData element;

    public GeneComponentModelPokemonData(AbsCommonFrame _fr, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        this.compoFactory = _core;
        facade = _facade;
        weight = new GeneComponentModelRate(compoFactory);
        height = new GeneComponentModelRate(compoFactory);
        types = ConverterCommonMapUtil.buildTypeList(compoFactory,facade);
        genderRep =ConverterCommonMapUtil.buildGenderRepartition(compoFactory);
        baseEvo = ConverterCommonMapUtil.buildPkFull(compoFactory,_facade);
        expEvo =ConverterCommonMapUtil.buildExpType(compoFactory,_facade);
        levMoves = new CrudGeneFormListSubLevelMove(compoFactory,_facade,_sub);
        levMoves.setFrame(_fr);
        evolutions = new CrudGeneFormEvolutions(_core,_facade,_sub);
        evolutions.setFrame(_fr);
    }
    public static CrudGeneFormPkTr crudTr(AbsCommonFrame _fr, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        CrudGeneFormPkTr c_ = new CrudGeneFormPkTr(_core, _facade,_sub);
        c_.initForm(_fr,_core);
        return c_;
    }
    public static CrudGeneFormPk crud(AbsCommonFrame _fr, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        CrudGeneFormPk c_ = new CrudGeneFormPk(_core, _facade,_sub);
        c_.initForm(_fr,_core);
        return c_;
    }
    @Override
    public AbsCustComponent gene() {
        element = Instances.newPokemonData();
        return group();
    }

    private AbsCustComponent group() {
        statistics.clear();
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
        form_.add(baseEvo.geneEnum(element.getBaseEvo()));
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
        ids_.addAllElts(getTypes().subsTy());
        ids_.addAllElts(getBaseEvo().subsPk());
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
