package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public abstract class AbsCrudGeneFormNb extends CrudGeneFormSub<Integer, String> {
    private final FacadeGame facadeGame;
    private final AbsSpinner destination;
    private final SubscribedTranslationList subscribedTranslations;
    private final SubscribedTranslationMessagesNbFactory factoryMessage;
    private GeneComponentModelEltStrSub geneComponentModelSelectKey;
    protected AbsCrudGeneFormNb(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr, SubscribedTranslationMessagesNbFactory _facto) {
        super(_fact,_facade,_sub,_fr);
        factoryMessage = _facto;
        subscribedTranslations = _sub;
        facadeGame = _facade;
        destination = _fact.getCompoFactory().newSpinner(0,0,Integer.MAX_VALUE,1);
        destination.addChangeListener(new RenameNbEvent(this));
    }
    public void initFormTr() {
        initForm();
        IntMap<String> nbs_ = new IntMap<String>();
        for (EntryCust<Short,String> e: factoryMessage.retrieveMap(getFactory(),facadeGame).entryList()) {
            nbs_.addEntry((int)e.getKey(),e.getValue());
        }
        initForm(new IntStringDisplayEntryCust(), new GeneComponentModelInt(getFactory()), getGeneValue(), new IntIdComparator(), nbs_);
        getButtons().add(destination);
        getFrame().setContentPane(getGroup());
        getFrame().setVisible(true);
        getFrame().pack();
    }

    @Override
    protected void afterModif(int _index, Integer _key, String _value) {
        int i_ = _key;
        short k_ = (short) i_;
        if (_index > -1) {
            if (already(k_)) {
                return;
            }
            getList().remove(_index);
            afterChange();
            update();
            return;
        }
        if (getSelectedIndex() < 0) {
            factoryMessage.retrieveMap(getFactory(),facadeGame).addEntry(k_,_value);
            afterChange();
            update();
            return;
        }
        factoryMessage.retrieveMap(getFactory(),facadeGame).set(k_,_value);
        afterChange();
        update();
    }

    public void tryRename() {
        if (getSelectedIndex() >= 0) {
            short next_ = (short) destination.getValue();
            int key_ = getList().getKey(getSelectedIndex());
            short old_ = (short) key_;
            if (renamed(old_, next_)) {
                getList().move((int)old_,(int)next_);
                refresh();
                afterModif();
                update();
            }
        }
    }

    @Override
    protected void build() {
        geneComponentModelSelectKey = ConverterCommonMapUtil.buildMvFull(getFactory(), getCrudGeneFormSubContent().getFacadeGame(), subscription());
        setGeneValue(geneComponentModelSelectKey.getSelectUniq());
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> all_ = new IdList<SubscribedTranslation>();
        all_.addAllElts(geneComponentModelSelectKey.getSubs());
        return all_;
    }
    protected abstract boolean already(short _key);

    protected abstract boolean renamed(short _previous, short _next);

    public AbsSpinner getDestination() {
        return destination;
    }

    private void update() {
        subscribedTranslations.update();
    }

}
