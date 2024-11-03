package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.pokemon.evolution.*;
import code.gui.AbsCommonFrame;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormEvolutions extends CrudGeneFormBasicSub<String, Evolution> {
    private final GeneComponentModelEltStrSub geneComponentModelSelectKey = ConverterCommonMapUtil.buildPkFull(getFactory(), getCrudGeneFormSubContent().getFacadeGame());
    private GeneComponentModelEvolution geneComponentModelEvolution;

    public CrudGeneFormEvolutions(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact, _facade, _sub, _fr);

    }
    public void initForm(AbstractProgramInfos _core, StringMap<Evolution> _evos) {
        getCrudGeneFormSubContent().clear();
        StringMap<String> messages_ = new StringMap<String>(getCrudGeneFormSubContent().getFacadeGame().getData().getTranslatedPokemon().getVal(_core.getLanguage()));
        geneComponentModelEvolution = new GeneComponentModelEvolution(getFrame(),_core, getCrudGeneFormSubContent().getFacadeGame());
//        getCrudGeneFormSubContent().addAllSub(subscribe());
        setGeneKey(geneComponentModelSelectKey.getSelectUniq());
//        getCrudGeneFormSubContent().subscribeAll();
        initForm();
        initForm(messages_, getGeneKey(), geneComponentModelEvolution, _evos);
    }

    @Override
    public IdList<SubscribedTranslation> subscribe() {
        setGeneKey(geneComponentModelSelectKey.getSelectUniq());
        return new IdList<SubscribedTranslation>(geneComponentModelSelectKey.getSubs());
    }

    public IdList<SubscribedTranslation> subscribeButtons() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationPkMessages(getMessages()));
        ids_.add(new SubscribedTranslationPkKey(this));
        return ids_;
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> all_ = new IdList<SubscribedTranslation>();
        all_.addAllElts(geneComponentModelSelectKey.getSubs());
        all_.addAllElts(geneComponentModelEvolution.all());
        return all_;
    }

}
