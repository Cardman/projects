package aiki.gui.components;


import aiki.facade.FacadeGame;
import aiki.facade.PaginationItem;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;
import aiki.gui.WindowAiki;
import aiki.gui.components.listeners.ChangedModeEvent;
import code.gui.*;
import code.util.IdList;
import code.util.StringList;

public abstract class PaginatorCommonItem extends Paginator {

//    private static final String CST_NAME = "name";

//    private static final String CST_DESCRIPTION = "description";

//    private static final String PRICE = "price";

    //private static final String ITEM = "item";

//    private static final String NUMBER = "number";

    private final AbsTextField name;

    private final AbsTextField description = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField minPrice = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField maxPrice = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField minNumber = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField maxNumber = getMain().getCompoFactory().newTextField(16);


    private final IdList<SearchingMode> order = new IdList<SearchingMode>();

    //private JComboBoxSearchingMode modeFirstName = new JComboBoxSearchingMode();
    private final ComboBox<SearchingMode> modeName;

    private final ComboBox<SearchingMode> modeDescription;

    private final AbsPanel results = getMain().getCompoFactory().newGrid(0,1);

    private final ComboBox<SelectedBoolean> cmpNameSorting;

    private final NumComboBox cmpNamePrio;

    private final ComboBox<SelectedBoolean> cmpDescriptionSorting;

    private final NumComboBox cmpDescriptionPrio;

    private final ComboBox<SelectedBoolean> cmpPriceSorting;

    private final NumComboBox cmpPricePrio;

    private final ComboBox<SelectedBoolean> cmpNumberSorting;

    private final NumComboBox cmpNumberPrio;

    protected PaginatorCommonItem(WindowAiki _window, AbsPanel _p, AbsCommonFrame _w, FacadeGame _d) {
        super(_window, _p);
        cmpNamePrio = new NumComboBox(_window.getFrames());
        cmpNumberPrio = new NumComboBox(_window.getFrames());
        cmpPricePrio = new NumComboBox(_window.getFrames());
        cmpDescriptionPrio = new NumComboBox(_window.getFrames());
        setWindow(_w);
        setFacade(_d);
        order.addAllElts(SearchingMode.all());
//        order.add(SearchingMode.WHOLE_STRING);
//        order.add(SearchingMode.SUBSTRING);
//        order.add(SearchingMode.META_CHARACTER);
//        order.add(SearchingMode.BEGIN);
//        order.add(SearchingMode.END);
//        order.add(SearchingMode.MATCH_SPACE);
        modeName = new ComboBox<SearchingMode>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        modeName.refresh(order, getMessagesSearchMode());
        modeDescription = new ComboBox<SearchingMode>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        modeDescription.refresh(order, getMessagesSearchMode());
        cmpNameSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpDescriptionSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpDescriptionSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpPriceSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpPriceSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpNumberSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpNumberSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        int nb_ = PaginationItem.NB_CMPARATORS;
        cmpNamePrio.setItems(nb_+1);
        cmpDescriptionPrio.setItems(nb_+1);
        cmpPricePrio.setItems(nb_+1);
        cmpNumberPrio.setItems(nb_+1);
        getFacade().setSearchModeNameItem(SearchingMode.WHOLE_STRING);
        getFacade().setSearchModeDescriptionItem(SearchingMode.WHOLE_STRING);
        StringList it_ = new StringList();
        for (String i: getFacade().getData().getItems().getKeys()) {
            String abTr_ = getFacade().translateItem(i);
            it_.add(abTr_);
        }
        name = getMain().getCompoFactory().newTextField(16);
        AutoCompleteDocument nameAuto_ = new AutoCompleteDocument(name, it_, _window.getFrames());
//        name.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfNameItem(convertStringField(name.getText()));
//            }
//        });
//        description.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfDescriptionItem(convertStringField(description.getText()));
//            }
//        });
        modeName.setListener(new ChangedModeEvent(modeName, nameAuto_));
//        modeName.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                SearchingMode s_ = modeName.getCurrent();
//                getFacade().setSearchModeNameItem(s_);
//                AutoCompleteDocument.setMode(name, s_);
//            }
//        });
//        modeDescription.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                SearchingMode s_ = modeDescription.getCurrent();
//                getFacade().setSearchModeDescriptionItem(s_);
//            }
//        });
//        minPrice.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMinPriceItem(convertNumberField(minPrice.getText()));
//            }
//        });
//        maxPrice.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMaxPriceItem(convertNumberField(maxPrice.getText()));
//            }
//        });
//        minNumber.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMinNumberItem(convertLgIntField(minNumber.getText()));
//            }
//        });
//        maxNumber.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMaxNumberItem(convertLgIntField(maxNumber.getText()));
//            }
//        });
//        cmpNameSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNameIncreasingItem(cmpNameSorting.getCurrent());
//            }
//        });
//        cmpDescriptionSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpDescriptionIncreasingItem(cmpDescriptionSorting.getCurrent());
//            }
//        });
//        cmpPriceSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpPriceIncreasingItem(cmpPriceSorting.getCurrent());
//            }
//        });
//        cmpNumberSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNumberIncreasingItem(cmpNumberSorting.getCurrent());
//            }
//        });
//        cmpNamePrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNamePriorityItem((Integer)cmpNamePrio.getSelectedItem());
//            }
//        });
//        cmpDescriptionPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpDescriptionPriorityItem((Integer)cmpDescriptionPrio.getSelectedItem());
//            }
//        });
//        cmpPricePrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpPricePriorityItem((Integer)cmpPricePrio.getSelectedItem());
//            }
//        });
//        cmpNumberPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNumberPriorityItem((Integer)cmpNumberPrio.getSelectedItem());
//            }
//        });
//        AbsPanel bottom_ = getMain().getCompoFactory().newLineBox();
//        getNbResults().addChangeListener(new ChangedNbResultsEvent(this));
//        bottom_.add(getNbResults());
//        getPages().setListener(new ChangedPageEvent(this));
//        bottom_.add(getBegin());
//        bottom_.add(getPreviousDelta());
//        bottom_.add(getPrevious());
//        bottom_.add(getPages().self());
//        bottom_.add(getNext());
//        bottom_.add(getNextDelta());
//        bottom_.add(getEnd());
//        bottom_.add(getDelta());
//        _p.add(bottom_);
//        changeNav();
    }

    public IdList<SearchingMode> getOrder() {
        return order;
    }

    protected abstract void joinGui(AbsPanel _p);

    public AbsPanel getResults() {
        return results;
    }

    public AbsTextField getName() {
        return name;
    }

    public AbsTextField getDescription() {
        return description;
    }

    public AbsTextField getMinPrice() {
        return minPrice;
    }

    public AbsTextField getMaxPrice() {
        return maxPrice;
    }

    public AbsTextField getMinNumber() {
        return minNumber;
    }

    public AbsTextField getMaxNumber() {
        return maxNumber;
    }

    public ComboBox<SearchingMode> getModeName() {
        return modeName;
    }

    public ComboBox<SearchingMode> getModeDescription() {
        return modeDescription;
    }

    public ComboBox<SelectedBoolean> getCmpNameSorting() {
        return cmpNameSorting;
    }

    public NumComboBox getCmpNamePrio() {
        return cmpNamePrio;
    }

    public ComboBox<SelectedBoolean> getCmpDescriptionSorting() {
        return cmpDescriptionSorting;
    }

    public NumComboBox getCmpDescriptionPrio() {
        return cmpDescriptionPrio;
    }

    public ComboBox<SelectedBoolean> getCmpPriceSorting() {
        return cmpPriceSorting;
    }

    public NumComboBox getCmpPricePrio() {
        return cmpPricePrio;
    }

    public ComboBox<SelectedBoolean> getCmpNumberSorting() {
        return cmpNumberSorting;
    }

    public NumComboBox getCmpNumberPrio() {
        return cmpNumberPrio;
    }
}
