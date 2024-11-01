package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.pokemon.evolution.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormEvolutions extends CrudGeneFormBasicSub<String, Evolution> {
    private GeneComponentModelEvolution geneComponentModelEvolution;

    public CrudGeneFormEvolutions(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_fact, _facade, _sub);

    }
    public void initForm(AbstractProgramInfos _core, StringMap<Evolution> _evos) {
        getCrudGeneFormSubContent().clear();
        StringMap<String> messages_ = new StringMap<String>(getCrudGeneFormSubContent().getFacadeGame().getData().getTranslatedPokemon().getVal(_core.getLanguage()));
        geneComponentModelEvolution = new GeneComponentModelEvolution(getFrame(),_core, getCrudGeneFormSubContent().getFacadeGame());
        subscribeAll();
        initForm();
        initForm(messages_, getGeneKey(), geneComponentModelEvolution, _evos);
    }

    @Override
    protected IdList<SubscribedTranslation> subscribe() {
        GeneComponentModelEltStrSub key_ = ConverterCommonMapUtil.buildPk(getFactory(), getCrudGeneFormSubContent().getFacadeGame());
        setGeneKey(key_.getSelectUniq());
        return new IdList<SubscribedTranslation>(key_.subsPk());
    }
    @Override
    public void updateDisplayEntry(AbstractProgramInfos _api, FacadeGame _facade) {
        StringMap<String> messages_ = new StringMap<String>(_facade.getData().getTranslatedPokemon().getVal(_api.getLanguage()));
        setDisplayEntry(new DisplayKeyOnly<String, Evolution>(messages_));
    }
    @Override
    protected IdList<SubscribedTranslation> all() {
        return geneComponentModelEvolution.all();
    }

    @Override
    protected void afterChange() {
        getCrudGeneFormSubContent().removeOpenSub();
        subscribeAll();
        afterModif();
    }
}
