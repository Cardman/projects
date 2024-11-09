package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.pokemon.evolution.*;
import code.gui.AbsCommonFrame;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormEvolutions extends CrudGeneFormBasicSub<String, Evolution> {
    private GeneComponentModelEvolution geneComponentModelEvolution;

    public CrudGeneFormEvolutions(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact, _facade, _sub, _fr);

    }
    public void initForm(AbstractProgramInfos _core, StringMap<Evolution> _evos) {
        getCrudGeneFormSubContent().clear();
//        geneComponentModelSelectKey = ConverterCommonMapUtil.buildPkFull(getFactory(), getCrudGeneFormSubContent().getFacadeGame(), subscription());
        StringMap<String> messages_ = subscription().getFactoryPk().buildMessages(_core,getCrudGeneFormSubContent().getFacadeGame());
        geneComponentModelEvolution = new GeneComponentModelEvolution(getFrame(),_core, getCrudGeneFormSubContent().getFacadeGame(),subscription());
//        getCrudGeneFormSubContent().addAllSub(subscribe());
//        setGeneKey(geneComponentModelSelectKey.getSelectUniq());
//        getCrudGeneFormSubContent().subscribeAll();
        initForm();
        initForm(messages_, geneComponentModelEvolution, _evos);
    }

    public IdList<SubscribedTranslation> subscribeButtons() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationMessages(getMessages(),subscription().getFactoryPk()));
        ids_.add(new SubscribedTranslationPkKey(this));
        return ids_;
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> all_ = new IdList<SubscribedTranslation>();
        all_.addAllElts(geneComponentModelEvolution.all());
        return all_;
    }

}
