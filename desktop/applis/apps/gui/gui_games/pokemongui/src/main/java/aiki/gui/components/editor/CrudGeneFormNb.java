package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.scripts.pages.aiki.*;
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
        for (EntryCust<Integer,String> e: factoryMessage.retrieveMap(getFactory(),facadeGame).entryList()) {
            nbs_.add(new EditedCrudPair<Integer, String>(e.getKey(),e.getValue()));
        }
        IntIdComparator cmp_ = new IntIdComparator();
        geneComponentModelNb = new GeneComponentModelNb(getFactory(), facadeGame, subscription());
        initForm(new DisplayKeyOnlyInt(), geneComponentModelNb, cmp_, new ValidateElementPair<Integer, String>(cmp_));
        setupValues(nbs_);
        getButtons().add(SubscribedTranslationList.line(getFactory(),MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(getFactory().currentLg())),MessagesEditorSelect.DEST_NB,destination));
        scroll();
    }

    @Override
    public void afterModif(int _index, EditedCrudPair<Integer, String> _value) {
        int k_ = _value.getKey();
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
            int next_ = destination.getValue();
            EditedCrudPair<Integer, String> entry_ = getList().get(selectedIndex_);
            int old_ = entry_.getKey();
            factoryMessage.rename(facadeGame,old_,next_);
            IntMap<String> after_ = factoryMessage.retrieveMap(getFactory(), facadeGame);
            if (!after_.contains(old_)) {
                getList().remove(selectedIndex_);
                getList().add(new EditedCrudPair<Integer, String>(next_,entry_.getValue()));
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
            priceValue_ = getCrudGeneFormSubContent().getFacadeGame().getData().getTmPrice().getVal(key_);
        } else {
            priceValue_ = null;
        }
        AbsCustComponent gene_ = SubscribedTranslationList.line(getFactory(),MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_TM_HM_PRICE,price.geneLgInt());
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
        subscribedTranslations.updateRenamingId(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING,new StringList());
        subscribedTranslations.update();
    }

}
