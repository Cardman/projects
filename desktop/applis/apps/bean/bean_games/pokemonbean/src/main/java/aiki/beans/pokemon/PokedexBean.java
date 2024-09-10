package aiki.beans.pokemon;

import aiki.beans.WithFilterBean;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public class PokedexBean extends WithFilterBean {


    @Override
    public void beforeDisplaying() {
        bools();
        setupPokedex(getForms().getValPokemonData(CST_POKEMON_SET));
    }
    public String search() {
        return search(CST_PK, PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML, PkScriptPages.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML);
    }

    public static boolean atLeastMatchType(StringMap<String> _translationsTypes, boolean _wholeWord, String _typedType, StringList _types) {
        boolean atLeastMatchType_ = false;
        for (String t: _types) {
            String displayType_;
            displayType_ = _translationsTypes.getVal(t);
            if (_wholeWord) {
                if (StringUtil.quickEq(displayType_, _typedType)) {
                    atLeastMatchType_ = true;
                }
            } else {
                if (StringUtil.match(displayType_, _typedType)) {
                    atLeastMatchType_ = true;
                }
            }
        }
        return atLeastMatchType_;
    }

    public String clickLink(int _number) {
        getForms().put(CST_PK, getPokedex().get(_number).getName());
        return PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML;
    }
}