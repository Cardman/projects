package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.LgInt;
import code.util.*;

public final class CrudGeneFormNb extends CrudGeneFormSub<Integer, String> {
    private final FacadeGame facadeGame;
    private final AbsSpinner destination;
    private final SubscribedTranslationList subscribedTranslations;
    private final SubscribedTranslationMessagesNbFactory factoryMessage;
    private GeneComponentModelEltStrSub geneComponentModelSelectKey;
    private GeneComponentModelLgInt price;
    private boolean tm;
    public CrudGeneFormNb(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr, SubscribedTranslationMessagesNbFactory _facto) {
        super(_fact,_facade,_sub,_fr);
        factoryMessage = _facto;
        subscribedTranslations = _sub;
        facadeGame = _facade;
        destination = _fact.getCompoFactory().newSpinner(0,0,Integer.MAX_VALUE,1);
        destination.addChangeListener(new RenameNbEvent(this));
    }
    public void initFormTr(boolean _t) {
        tm = _t;
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
            int old_ = factoryMessage.retrieveMap(getFactory(), facadeGame).size();
            factoryMessage.delete(facadeGame,k_);
            if (old_ <= factoryMessage.retrieveMap(getFactory(), facadeGame).size()) {
                return;
            }
            if (tm) {
                getCrudGeneFormSubContent().getFacadeGame().getData().getTmPrice().removeKey(k_);
            }
            getList().remove(_index);
            afterChange();
            update();
            return;
        }
        if (getSelectedIndex() < 0) {
            factoryMessage.retrieveMap(getFactory(),facadeGame).addEntry(k_,_value);
            if (tm) {
                getCrudGeneFormSubContent().getFacadeGame().getData().getTmPrice().addEntry(k_,price.value());
            }
            afterChange();
            update();
            return;
        }
        factoryMessage.retrieveMap(getFactory(),facadeGame).set(k_,_value);
        if (tm) {
            getCrudGeneFormSubContent().getFacadeGame().getData().getTmPrice().set(k_,price.value());
        }
        afterChange();
        update();
    }

    public void tryRename() {
        if (getSelectedIndex() >= 0) {
            short next_ = (short) destination.getValue();
            int key_ = getList().getKey(getSelectedIndex());
            short old_ = (short) key_;
            factoryMessage.rename(facadeGame,old_,next_);
            ShortMap<String> after_ = factoryMessage.retrieveMap(getFactory(), facadeGame);
            if (!after_.contains(old_)) {
                getList().move((int)old_,(int)next_);
                if (tm) {
                    getCrudGeneFormSubContent().getFacadeGame().getData().getTmPrice().move(old_,next_);
                }
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
    public void selectOrAdd() {
        price = new GeneComponentModelLgInt(getFactory());
        LgInt priceValue_;
        if (getSelectedIndex() > -1) {
            int key_ = getList().getKey(getSelectedIndex());
            priceValue_ = getCrudGeneFormSubContent().getFacadeGame().getData().getTmPrice().getVal((short) key_);
        } else {
            priceValue_ = null;
        }
        AbsCustComponent gene_;
        if (priceValue_ != null) {
            gene_ = price.gene(priceValue_);
            getElement().add(gene_);
        } else {
            gene_ = price.gene();
            getElement().add(gene_);
        }
        gene_.setVisible(tm);
        super.selectOrAdd();
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> all_ = new IdList<SubscribedTranslation>();
        all_.addAllElts(geneComponentModelSelectKey.getSubs());
        return all_;
    }

    public AbsSpinner getDestination() {
        return destination;
    }

    private void update() {
        subscribedTranslations.update();
    }

}
