package aiki.beans.items;

import aiki.beans.*;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.items.Ball;
import aiki.fight.items.Item;
import code.scripts.pages.aiki.*;
import code.util.StringMap;
import code.util.core.*;

public abstract class ItemBean extends CommonBean implements BeanRenderWithAppName {
//    static final String ITEM_BEAN= PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEM_HTML;

    private DictionaryComparator<TranslatedKey, Long> happiness;
    private String name;
    private String displayName;
    private long price;
    private String description;
    private int[][] itemImage;
    protected ItemBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }

    protected void buildHeader() {
        setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesDataItemsItem.M_P_27_TITLE),displayName));
        formatMessageAnc(new ItemBeanClickItems(this),MessagesPkBean.IT_ITEM,MessagesDataItemsItem.M_P_27_ITEMS);
        formatMessageDir(displayName);
        addImg(itemImage);
        formatMessage(MessagesPkBean.IT_ITEM,MessagesDataItemsItem.M_P_27_ITEM_TYPE,description);
        formatMessage(MessagesPkBean.IT_ITEM,MessagesDataItemsItem.M_P_27_PRICE,displayName,Long.toString(price));
    }
    protected void buildHappiness(String _file, String _title, String _no, String... _cols) {
        new BeanDisplayMap<TranslatedKey,Long>(new BeanDisplayTranslatedKey(_file,_no),new BeanDisplayLong()).displayGrid(this,happiness,_file,_title,_cols);
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.IT_ITEM).getMapping();
    }
    protected void beforeDisplayingItem() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsClasses_;
        translationsClasses_ = data_.getTranslatedClassesDescriptions().getVal(getLanguage());
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        name = getForms().getValStr(CST_ITEM);
        itemImage = data_.getMiniItem(name);
        displayName = translationsItems_.getVal(name);
        Item item_ = data_.getItem(name);
        price = item_.getPrice();
        description = translationsClasses_.getVal(item_.getItemType());
    }

    public void initHappiness(StringMap<Long> _map) {
        DictionaryComparator<TranslatedKey, Long> happiness_;
        happiness_ = DictionaryComparatorUtil.buildItemsShort();
        for (String i: _map.getKeys()) {
            happiness_.put(buildIt(getFacade(), i), _map.getVal(i));
        }
        happiness = happiness_;
    }

    public boolean isBall(int _index) {
        return getDataBase().getItem(happiness.getKey(_index).getKey()) instanceof Ball;
    }
    public String getTrHappiness(int _index) {
        return happiness.getKey(_index).getTranslation();
//        return getDataBase().getTranslatedItems().getVal(getLanguage()).getVal(happiness.getKey(_index));
    }
    public String clickHappiness(int _index) {
        return tryRedirect(happiness.getKey(_index));
    }

    public DictionaryComparator<TranslatedKey,Long> getHappiness() {
        return happiness;
    }


    public String clickItems() {
        getForms().safeItems(CST_ITEMS_SET);
        return CommonBean.REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML;
    }

    protected Item getItem() {
        DataBase data_ = getDataBase();
        return data_.getItem(name);
    }

    public String getName() {
        return name;
    }

//    public void setName(String _name) {
//        name = _name;
//    }

    public String getDisplayName() {
        return displayName;
    }

    public int[][] getItemImage() {
        return itemImage;
    }

    public String getDescription() {
        return description;
    }

    public long getPrice() {
        return price;
    }
}