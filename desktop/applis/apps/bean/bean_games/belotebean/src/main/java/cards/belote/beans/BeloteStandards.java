package cards.belote.beans;

import cards.belote.*;
import cards.consts.beans.*;
import code.bean.nat.*;
import code.bean.nat.exec.*;
import code.bean.nat.exec.blocks.*;
import code.bean.nat.fwd.*;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.structs.*;
import code.formathtml.*;
import code.util.*;

public abstract class BeloteStandards extends BeanNatCommonLgNames {
    protected static final String TYPE_BELOTE_BEAN = "cards.belote.beans.BeloteBean";
    private static final String VALUE = "value";
    private static final String STATUT = "statut";
    private static final String NICKNAME = "nickname";
    private static final String SUM = "sum";
    private static final String DECLARING = "declaring";
    private static final String GET_SCORES = "getScores";
    private static final String GET_NICKNAMES = "getNicknames";
    private static final String PLAY_GAME = "playGame";
    private static final String TYPE_DECLARING_PLAYER_VALUE = "cards.belote.beans.DeclaringPlayerValue";
    private static final String TYPE_SUM_DECLARING_PLAYER = "cards.belote.beans.SumDeclaringPlayer";
    private ResultsBelote dataBase;
    private RulesBelote dataBaseRules;
    protected BeloteStandards(){
    }
    @Override
    public void buildOther() {
        buildBeloteBean();
        buildAddon();
    }
    protected abstract void buildAddon();
    protected void def() {
        buildLineDeal();
        buildSumDeclaringPlayer();
        buildDeclaringPlayerValue();
    }
    private void buildBeloteBean(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_BELOTE_BEAN, fields_, methods_, TYPE_BEAN);
        methods_.add( new SpecNatMethod(PLAY_GAME, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new BeloteBeanPlayGame()));
        methods_.add( new SpecNatMethod(GET_NICKNAMES, TYPE_LIST, false, MethodModifier.NORMAL,new BeloteBeanGetNicknames()));
        methods_.add( new SpecNatMethod(GET_SCORES, TYPE_LIST, false, MethodModifier.NORMAL,new BeloteBeanGetScores()));
        getStds().addEntry(TYPE_BELOTE_BEAN, std_);
    }
    private void buildLineDeal(){
        LineDealStruct.buildLineDeal(getStds());
    }
    private void buildSumDeclaringPlayer(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_SUM_DECLARING_PLAYER, fields_, methods_, OBJECT);
        fields_.add( new StandardField(DECLARING, TYPE_LIST, false, false,new BeloteSumDeclaringPlayerDeclaring(),null));
        fields_.add( new StandardField(SUM, PRIM_INTEGER, false, false,new BeloteSumDeclaringPlayerSum(),null));
        fields_.add( new StandardField(NICKNAME, STRING, false, false,new BeloteSumDeclaringPlayerNickname(),null));
        fields_.add( new StandardField(STATUT, STRING, false, false,new BeloteSumDeclaringPlayerStatut(),null));
        getStds().addEntry(TYPE_SUM_DECLARING_PLAYER, std_);
    }
    private void buildDeclaringPlayerValue(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_DECLARING_PLAYER_VALUE, fields_, methods_, OBJECT);
        fields_.add( new StandardField(DECLARING, STRING, false, false,new DeclaringPlayerValueDeclaring(),null));
        fields_.add( new StandardField(VALUE, PRIM_INTEGER, false, false,new DeclaringPlayerValueValue(),null));
        getStds().addEntry(TYPE_DECLARING_PLAYER_VALUE, std_);
    }

    @Override
    public InvokedPageOutput processAfterInvoke(Configuration _conf, String _dest, String _curUrl, Struct _bean, String _language, NatRendStackCall _rendStack) {
        NatImportingPageAbs ip_ = new NatImportingPage();
        _rendStack.addPage(ip_);
        NatDocumentBlock rendDocumentBlock_ = getRenders().getVal(_dest);
        _rendStack.clearPages();
        String res_ = getRes(rendDocumentBlock_, _conf, _rendStack,ip_);
        return new InvokedPageOutput(_dest,res_);
    }

    public static NatArrayStruct getSumDeclaringPlayerArray(CustList<BeloteSumDeclaringPlayer> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (BeloteSumDeclaringPlayer s:_ls) {
            arr_.set(j_,new BeloteSumDeclaringPlayerStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getDeclaringPlayerValueArray(CustList<DeclaringPlayerValue> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (DeclaringPlayerValue s:_ls) {
            arr_.set(j_,new DeclaringPlayerValueStruct(s));
            j_++;
        }
        return arr_;
    }

    protected BeloteBeanStruct bean(BeloteBean _bean, String _lg) {
        _bean.setDataBase(dataBase,dataBaseRules);
        _bean.setLanguage(_lg);
        return (new BeloteBeanStruct(_bean));
    }

    public void setDataBase(ResultsBelote _dataBase){
        dataBase = _dataBase;
    }

    public void setDataBaseRules(RulesBelote _dataBase){
        dataBaseRules = _dataBase;
    }

    @Override
    protected AbstractNatBlockBuilder blockBuilder() {
        return new DefNatBlockBuilder();
    }

    @Override
    protected NatRendStackCall newNatRendStackCall() {
        return new NatRendStackCall();
    }
}
