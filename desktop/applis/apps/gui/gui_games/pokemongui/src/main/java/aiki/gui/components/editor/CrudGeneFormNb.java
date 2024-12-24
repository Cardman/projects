package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class CrudGeneFormNb extends CrudGeneFormListSub<EditedCrudPair<Integer, String>> implements AbsCrudGeneFormTrCstOpen{
    private final FacadeGame facadeGame;
    private AbsSpinner destination;
    private final SubscribedTranslationList subscribedTranslations;
    private final SubscribedTranslationMessagesNbFactory factoryMessage;
    private GeneComponentModelLgInt price;
    private final boolean tm;
    private GeneComponentModelNb geneComponentModelNb;

    public CrudGeneFormNb(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr, SubscribedTranslationMessagesNbFactory _facto, boolean _t) {
        super(_fact,_facade,_sub,_fr, null);
        factoryMessage = _facto;
        subscribedTranslations = _sub;
        facadeGame = _facade;
        tm = _t;
    }

    @Override
    public void initFormAll() {
        initFormTr();
    }

    public void initFormTr() {
        destination = getFactory().getCompoFactory().newSpinner(0,0,Integer.MAX_VALUE,1);
        destination.addChangeListener(new RenameNbEvent(this));
        initForm();
        CustList<EditedCrudPair<Integer, String>> nbs_ = new CustList<EditedCrudPair<Integer, String>>();
        for (EntryCust<Short,String> e: factoryMessage.retrieveMap(getFactory(),facadeGame).entryList()) {
            nbs_.add(new EditedCrudPair<Integer, String>((int)e.getKey(),e.getValue()));
        }
        IntIdComparator cmp_ = new IntIdComparator();
        geneComponentModelNb = new GeneComponentModelNb(getFactory(), facadeGame, subscription());
        initForm(new DisplayKeyOnlyInt(), geneComponentModelNb, cmp_, new ValidateElementPair<Integer, String>(cmp_));
        setupValues(nbs_);
        getButtons().add(destination);
        getFrame().setContentPane(getGroup());
        getFrame().setVisible(true);
        getFrame().pack();
    }

    @Override
    public void afterModif(int _index, EditedCrudPair<Integer, String> _value) {
        int i_ = _value.getKey();
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
            getCrudGeneFormSubContent().afterChange();
            update();
            return;
        }
        if (getSelectedIndex() < 0) {
            factoryMessage.retrieveMap(getFactory(),facadeGame).addEntry(k_,_value.getValue());
            if (tm) {
                getCrudGeneFormSubContent().getFacadeGame().getData().getTmPrice().addEntry(k_,price.valueLgInt());
            }
            getCrudGeneFormSubContent().afterChange();
            update();
            return;
        }
        factoryMessage.retrieveMap(getFactory(),facadeGame).set(k_,_value.getValue());
        if (tm) {
            getCrudGeneFormSubContent().getFacadeGame().getData().getTmPrice().set(k_,price.valueLgInt());
        }
        getCrudGeneFormSubContent().afterChange();
        update();
    }

    public void tryRename() {
        int selectedIndex_ = getSelectedIndex();
        if (selectedIndex_ >= 0) {
            short next_ = (short) destination.getValue();
            EditedCrudPair<Integer, String> entry_ = getList().get(selectedIndex_);
            int key_ = entry_.getKey();
            short old_ = (short) key_;
            factoryMessage.rename(facadeGame,old_,next_);
            ShortMap<String> after_ = factoryMessage.retrieveMap(getFactory(), facadeGame);
            if (!after_.contains(old_)) {
                getList().remove(selectedIndex_);
                getList().add(new EditedCrudPair<Integer, String>((int) next_,entry_.getValue()));
                possibleSort();
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
    public void selectOrAdd() {
        price = new GeneComponentModelLgInt(getFactory());
        LgInt priceValue_;
        if (getSelectedIndex() > -1) {
            int key_ = getList().get(getSelectedIndex()).getKey();
            priceValue_ = getCrudGeneFormSubContent().getFacadeGame().getData().getTmPrice().getVal((short) key_);
        } else {
            priceValue_ = null;
        }
        AbsCustComponent gene_ = price.geneLgInt();
        if (priceValue_ != null) {
            price.valueLgInt(priceValue_);
        }
        getElement().add(gene_);
        gene_.setVisible(tm);
        super.selectOrAdd();
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> all_ = new IdList<SubscribedTranslation>();
        all_.addAllElts(geneComponentModelNb.all());
        return all_;
    }

    public AbsSpinner getDestination() {
        return destination;
    }

    private void update() {
        subscribedTranslations.updateRenamingId("","",new StringList());
        subscribedTranslations.update();
    }

}
