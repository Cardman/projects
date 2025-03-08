package aiki.beans.map;

import aiki.beans.*;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.facade.FacadeGame;
import aiki.map.levels.Level;
import aiki.map.places.Place;
import code.scripts.pages.aiki.*;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class MapBean extends CommonBean implements BeanRenderWithAppName {
    private CustList<PlaceIndex> places;
    public MapBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }
    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        setTitledBorder(file().getVal(MessagesDataMapLevel.M_P_32_TITLE_MAP));
        formatMessageAnc(new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_INDEX_HTML),MessagesPkBean.MAP,MessagesDataMapLevel.M_P_32_INDEX);
        int pls_ = places.size();
        for (int p = 0; p < pls_; p++) {
            initLine();
            paintMetaLabelDisk();
            formatMessageDir(places.get(p).getPlace().getName());
            if (isMultiLayer(p)) {
                CustList<Level> layers_ = layers(p);
                int len_ = layers_.size();
                for (int i = 0; i < len_; i++) {
                    formatMessageDirAnc("->"+Long.toString(i),new MapBeanClickLevelBeanAction(this,places.get(p).getIndex(),i));
                }
            } else {
                formatMessageAnc(new MapBeanClickLevelBeanAction(this,places.get(p).getIndex(),0),MessagesPkBean.MAP,MessagesDataMapLevel.M_P_32_GOLEVEL);
            }
            feedParents();
        }
    }

    @Override
    public void beforeDisplaying() {
//        getForms().removeKey(CST_INSIDE);
        places = PlaceIndex.places(getDataBase());
    }
    public StringMap<String> file() {
        return file(MessagesPkBean.MAP).getMapping();
    }
    public boolean isMultiLayer(int _index) {
        return layers(_index).size() > IndexConstants.ONE_ELEMENT;
    }
    public CustList<Level> layers(int _index) {
        Place pl_ = places.get(_index).getPlace();
        return pl_.getLevelsList();
    }
    public String clickLevel(int _indexOne, int _indexTwo) {
        feedForms(_indexOne, _indexTwo, getForms());
        return CommonBean.REN_ADD_WEB_HTML_MAP_LEVEL_HTML;
    }

    public CustList<PlaceIndex> getPlaces() {
        return places;
    }
}