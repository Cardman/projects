package aiki.gui.components.editor;

import aiki.comparators.ComparatorTr;
import aiki.db.DataBase;
import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.enums.GenderRepartition;
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
    private final GeneComponentModelLsStr types;
    private final IdMap<Statistic, FormStatBaseEv> statistics = new IdMap<Statistic, FormStatBaseEv>();
    private final GeneComponentModelEltEnum<GenderRepartition> genderRep;
    private final GeneComponentModelEltStr baseEvo;
    private PokemonData element;
    public GeneComponentModelPokemonData(AbstractProgramInfos _core, FacadeGame _facade) {
        this.compoFactory = _core;
        facade = _facade;
        weight = new GeneComponentModelRate(compoFactory);
        height = new GeneComponentModelRate(compoFactory);
        types = ConverterCommonMapUtil.buildTypeList(compoFactory,facade);
        genderRep =ConverterCommonMapUtil.buildGenderRepartition(compoFactory);
        baseEvo = ConverterCommonMapUtil.buildPkFull(compoFactory,_facade);
    }
    public static CrudGeneFormPk crud(AbsCommonFrame _fr, AbstractProgramInfos _core, FacadeGame _facade) {
        StringMap<String> messages_ = _facade.getData().getTranslatedPokemon().getVal(_core.getLanguage());
        ComparatorTr<String> cmp_ = new ComparatorTr<String>(messages_);
        CrudGeneFormPk c_ = new CrudGeneFormPk(_core, cmp_, _facade);
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

    public GeneComponentModelRate getWeight() {
        return weight;
    }

    public GeneComponentModelRate getHeight() {
        return height;
    }

    public GeneComponentModelLsStr getTypes() {
        return types;
    }

    public IdMap<Statistic, FormStatBaseEv> getStatistics() {
        return statistics;
    }

    public GeneComponentModelEltEnum<GenderRepartition> getGenderRep() {
        return genderRep;
    }

    public GeneComponentModelEltStr getBaseEvo() {
        return baseEvo;
    }
}
