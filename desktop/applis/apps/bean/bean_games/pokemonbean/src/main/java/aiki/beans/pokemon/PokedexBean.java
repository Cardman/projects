package aiki.beans.pokemon;

import aiki.beans.*;
import aiki.beans.facade.dto.PokemonLine;
import aiki.beans.game.*;
import aiki.facade.*;
import code.scripts.confs.*;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.*;

public final class PokedexBean extends WithFilterBean implements BeanRenderWithAppName {
    private IntBeanChgSubmit updateValues;
    public PokedexBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }

    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        setTitledBorder(file().getVal(MessagesDataPokemonPokedex.M_P_82_TITLE));
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML,this),MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_INDEX);
//        initPage();
        initLine();
        formatMessage(MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_CONTENT_NAME);
        setTypedName(DifficultyBeanForm.txt(getBuilder().getGenInput(),this,getTypedName().tryRet()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_CONTENT_TYPE);
        setTypedType(DifficultyBeanForm.txt(getBuilder().getGenInput(),this,getTypedType().tryRet()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_CONTENT_TYPE_WHOLE);
        setWholeWord(DifficultyBeanForm.check(getBuilder().getGenInput(), this,getWholeWord().isSelected()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_NB_EVOS);
        setTypedMinNbPossEvos(DifficultyBeanForm.txt(getBuilder().getGenInput(), this,getTypedMinNbPossEvos().tryRet()));
        setTypedMaxNbPossEvos(DifficultyBeanForm.txt(getBuilder().getGenInput(), this,getTypedMaxNbPossEvos().tryRet()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_HAS_EVO);
        setHasEvo(DifficultyBeanForm.select(getBuilder().getGenInput(), this,getBooleans(),getHasEvo().tryRet()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_IS_EVO);
        setIsEvo(DifficultyBeanForm.select(getBuilder().getGenInput(), this,getBooleans(),getIsEvo().tryRet()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_LEG);
        setIsLeg(DifficultyBeanForm.select(getBuilder().getGenInput(), this,getBooleans(),getIsLeg().tryRet()));
        feedParents();
        initLine();
        updateValues = getBuilder().button(formatMessageRend(MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_OK));
        getUpdateValues().addEvt(new PokedexBeanSearch(this));
        feedParents();
        new BeanDisplayListGrid<PokemonLine>(new BeanDisplayPokemonLine()).displayGrid(this,getPokedex(),MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_POKEDEX,MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataPokemonPokedex.M_P_82_NAME,MessagesDataPokemonPokedex.M_P_82_TYPES,MessagesDataPokemonPokedex.M_P_82_EVOS);
//        for (PokemonLine p: getPokedex()) {
//            initLine();
//            addImg(getDataBase().getMiniPk(p.getName()));
//            feedParentsCts();
//            formatMessageDirCts(p.getKey());
//            formatMessageDirCts(StringUtil.join(p.getTypes()," - "));
//            formatMessageDirCts(Long.toString(p.getEvolutions()));
//        }
//        feedParents();
//        feedParents();
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML,this),MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_INDEX);
    }
    public StringMap<String> file() {
        return file(MessagesPkBean.POKEDEX).getMapping();
    }

    public IntBeanChgSubmit getUpdateValues() {
        return updateValues;
    }

    @Override
    public void beforeDisplaying() {
        bools();
        setupPokedex(getForms().getValPokemonData(CST_POKEMON_SET));
    }
    public String search() {
        return search(CST_PK, PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML, PkScriptPages.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML);
    }

    public static boolean atLeastMatchType(StringMap<String> _translationsTypes, IntBeanChgBool _wholeWord, IntBeanChgString _typedType, StringList _types) {
        boolean atLeastMatchType_ = false;
        for (String t: _types) {
            String displayType_;
            displayType_ = _translationsTypes.getVal(t);
            if (_wholeWord.isSelected()) {
                if (StringUtil.quickEq(displayType_, _typedType.tryRet())) {
                    atLeastMatchType_ = true;
                }
            } else {
                if (StringUtil.match(displayType_, _typedType.tryRet())) {
                    atLeastMatchType_ = true;
                }
            }
        }
        return atLeastMatchType_;
    }

    public String clickLink(int _number) {
        return tryRedirect(getPokedex().get(_number).getKey());
    }
}