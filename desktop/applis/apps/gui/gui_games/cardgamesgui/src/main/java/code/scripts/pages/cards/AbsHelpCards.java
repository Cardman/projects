package code.scripts.pages.cards;

import cards.consts.CouleurValeur;
import code.bean.*;
import code.sml.util.*;
import code.util.core.StringUtil;

public abstract class AbsHelpCards {
    protected static final String C_CLUB_10= ""+ CouleurValeur.CLUB_10+"";
    protected static final String C_CLUB_1=""+CouleurValeur.CLUB_1+"";
    protected static final String C_CLUB_2=""+CouleurValeur.CLUB_2+"";
    protected static final String C_CLUB_3=""+CouleurValeur.CLUB_3+"";
    protected static final String C_CLUB_4=""+CouleurValeur.CLUB_4+"";
    protected static final String C_CLUB_5=""+CouleurValeur.CLUB_5+"";
    protected static final String C_CLUB_6=""+CouleurValeur.CLUB_6+"";
    protected static final String C_CLUB_7=""+CouleurValeur.CLUB_7+"";
    protected static final String C_CLUB_8=""+CouleurValeur.CLUB_8+"";
    protected static final String C_CLUB_9=""+CouleurValeur.CLUB_9+"";
    protected static final String C_CLUB_JACK=""+CouleurValeur.CLUB_JACK+"";
    protected static final String C_CLUB_KING=""+CouleurValeur.CLUB_KING+"";
    protected static final String C_CLUB_KNIGHT=""+CouleurValeur.CLUB_KNIGHT+"";
    protected static final String C_CLUB_QUEEN=""+CouleurValeur.CLUB_QUEEN+"";
    protected static final String C_DIAMOND_10=""+CouleurValeur.DIAMOND_10+"";
    protected static final String C_DIAMOND_1=""+CouleurValeur.DIAMOND_1+"";
    protected static final String C_DIAMOND_2=""+CouleurValeur.DIAMOND_2+"";
    protected static final String C_DIAMOND_3=""+CouleurValeur.DIAMOND_3+"";
    protected static final String C_DIAMOND_4=""+CouleurValeur.DIAMOND_4+"";
    protected static final String C_DIAMOND_5=""+CouleurValeur.DIAMOND_5+"";
    protected static final String C_DIAMOND_6=""+CouleurValeur.DIAMOND_6+"";
    protected static final String C_DIAMOND_7=""+CouleurValeur.DIAMOND_7+"";
    protected static final String C_DIAMOND_8=""+CouleurValeur.DIAMOND_8+"";
    protected static final String C_DIAMOND_9=""+CouleurValeur.DIAMOND_9+"";
    protected static final String C_DIAMOND_JACK=""+CouleurValeur.DIAMOND_JACK+"";
    protected static final String C_DIAMOND_KING=""+CouleurValeur.DIAMOND_KING+"";
    protected static final String C_DIAMOND_KNIGHT=""+CouleurValeur.DIAMOND_KNIGHT+"";
    protected static final String C_DIAMOND_QUEEN=""+CouleurValeur.DIAMOND_QUEEN+"";
    protected static final String C_EXCUSE=""+CouleurValeur.EXCUSE+"";
    protected static final String C_HEART_10=""+CouleurValeur.HEART_10+"";
    protected static final String C_HEART_1=""+CouleurValeur.HEART_1+"";
    protected static final String C_HEART_2=""+CouleurValeur.HEART_2+"";
    protected static final String C_HEART_3=""+CouleurValeur.HEART_3+"";
    protected static final String C_HEART_4=""+CouleurValeur.HEART_4+"";
    protected static final String C_HEART_5=""+CouleurValeur.HEART_5+"";
    protected static final String C_HEART_6=""+CouleurValeur.HEART_6+"";
    protected static final String C_HEART_7=""+CouleurValeur.HEART_7+"";
    protected static final String C_HEART_8=""+CouleurValeur.HEART_8+"";
    protected static final String C_HEART_9=""+CouleurValeur.HEART_9+"";
    protected static final String C_HEART_JACK=""+CouleurValeur.HEART_JACK+"";
    protected static final String C_HEART_KING=""+CouleurValeur.HEART_KING+"";
    protected static final String C_HEART_KNIGHT=""+CouleurValeur.HEART_KNIGHT+"";
    protected static final String C_HEART_QUEEN=""+CouleurValeur.HEART_QUEEN+"";
    protected static final String C_SPADE_10=""+CouleurValeur.SPADE_10+"";
    protected static final String C_SPADE_1=""+CouleurValeur.SPADE_1+"";
    protected static final String C_SPADE_2=""+CouleurValeur.SPADE_2+"";
    protected static final String C_SPADE_3=""+CouleurValeur.SPADE_3+"";
    protected static final String C_SPADE_4=""+CouleurValeur.SPADE_4+"";
    protected static final String C_SPADE_5=""+CouleurValeur.SPADE_5+"";
    protected static final String C_SPADE_6=""+CouleurValeur.SPADE_6+"";
    protected static final String C_SPADE_7=""+CouleurValeur.SPADE_7+"";
    protected static final String C_SPADE_8=""+CouleurValeur.SPADE_8+"";
    protected static final String C_SPADE_9=""+CouleurValeur.SPADE_9+"";
    protected static final String C_SPADE_JACK=""+CouleurValeur.SPADE_JACK+"";
    protected static final String C_SPADE_KING=""+CouleurValeur.SPADE_KING+"";
    protected static final String C_SPADE_KNIGHT=""+CouleurValeur.SPADE_KNIGHT+"";
    protected static final String C_SPADE_QUEEN=""+CouleurValeur.SPADE_QUEEN+"";
    protected static final String C_TRUMP_10=""+CouleurValeur.TRUMP_10+"";
    protected static final String C_TRUMP_11=""+CouleurValeur.TRUMP_11+"";
    protected static final String C_TRUMP_12=""+CouleurValeur.TRUMP_12+"";
    protected static final String C_TRUMP_13=""+CouleurValeur.TRUMP_13+"";
    protected static final String C_TRUMP_14=""+CouleurValeur.TRUMP_14+"";
    protected static final String C_TRUMP_15=""+CouleurValeur.TRUMP_15+"";
    protected static final String C_TRUMP_16=""+CouleurValeur.TRUMP_16+"";
    protected static final String C_TRUMP_17=""+CouleurValeur.TRUMP_17+"";
    protected static final String C_TRUMP_18=""+CouleurValeur.TRUMP_18+"";
    protected static final String C_TRUMP_19=""+CouleurValeur.TRUMP_19+"";
    protected static final String C_TRUMP_1=""+CouleurValeur.TRUMP_1+"";
    protected static final String C_TRUMP_20=""+CouleurValeur.TRUMP_20+"";
    protected static final String C_TRUMP_21=""+CouleurValeur.TRUMP_21+"";
    protected static final String C_TRUMP_2=""+CouleurValeur.TRUMP_2+"";
    protected static final String C_TRUMP_3=""+CouleurValeur.TRUMP_3+"";
    protected static final String C_TRUMP_4=""+CouleurValeur.TRUMP_4+"";
    protected static final String C_TRUMP_5=""+CouleurValeur.TRUMP_5+"";
    protected static final String C_TRUMP_6=""+CouleurValeur.TRUMP_6+"";
    protected static final String C_TRUMP_7=""+CouleurValeur.TRUMP_7+"";
    protected static final String C_TRUMP_8=""+CouleurValeur.TRUMP_8+"";
    protected static final String C_TRUMP_9=""+CouleurValeur.TRUMP_9+"";
    public abstract void format(IntBeanBuilderHelperCommon _i, TranslationsLg _lg);

    public void formatMessage(IntBeanBuilderHelperCommon _i, TranslationsLg _lg,String _file, String _key, String... _values) {
        formatMessage(_i, _lg, 0, _file, _key, _values);
    }
    public void formatMessage(IntBeanBuilderHelperCommon _i, TranslationsLg _lg, int _n,String _file, String _key, String... _values) {
        if (_n == 0) {
            String txt_ = StringUtil.simpleStringsFormat(_lg.getMapping().getVal(MessagesHelpCards.APP_BEAN).getMapping().getVal(_file).getMapping().getVal(_key), _values);
            _i.formatMessageDir(txt_);
            return;
        }
        _i.initLine();
        _i.setIndent(_n);
        _i.indent();
        _i.setIndent(0);
        String txt_ = StringUtil.simpleStringsFormat(_lg.getMapping().getVal(MessagesHelpCards.APP_BEAN).getMapping().getVal(_file).getMapping().getVal(_key), _values);
        _i.formatMessageDir(txt_);
        _i.feedParents();
    }

    public void element(IntBeanBuilderHelperCommon _i, TranslationsLg _lg,String _file,String _key, String... _values) {
        element(_i,_lg,0,_file,_key,_values);
    }

    public void element(IntBeanBuilderHelperCommon _i, TranslationsLg _lg,int _n,String _file,String _key, String... _values) {
        _i.setIndent(_i.getIndent()+_n);
        _i.initLine();
        _i.indent();
        _i.paintMetaLabelDisk();
        formatMessage(_i,_lg,_file, _key, _values);
        _i.feedParents();
        _i.setIndent(_i.getIndent()-_n);
    }

    public void elementOrd(IntBeanBuilderHelperCommon _i, TranslationsLg _lg,String _file,String _key, String... _values) {
        elementOrd(_i,_lg,0,_file,_key,_values);
    }

    public void elementOrd(IntBeanBuilderHelperCommon _i, TranslationsLg _lg,int _n,String _file,String _key, String... _values) {
        _i.setIndent(_i.getIndent()+_n);
        _i.initLine();
        _i.indent();
        _i.paintNb(_i.getOrderedLists().last()+1);
        formatMessage(_i,_lg,_file, _key, _values);
        _i.getOrderedLists().set(_i.getOrderedLists().getLastIndex(),_i.getOrderedLists().last()+1);
        _i.feedParents();
        _i.setIndent(_i.getIndent()-_n);
    }
    public void feedImg(IntBeanBuilderHelperCommon _i, TranslationsLg _lg,String _key) {
        _i.initLine();
        _i.addImg(_lg.getMaxiCards().getVal(_key));
        _i.feedParentsCts();
    }

}
