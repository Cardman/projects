package code.maths.litteral;
import code.maths.LgInt;
import code.maths.MathList;
import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class FctMbOperation extends InvokingMbOperation {

    private static final int THREE_ARGUMENTS = 3;
    private final String methodName;

    public FctMbOperation(int _index,
                          int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName().trim();
    }

    @Override
    void analyze(StringMap<String> _conf, ErrorStatus _error, MbDelimiters _del) {
        CustList<MbOperationNode> chidren_ = getChildrenNodes();
        if (checkBinNum()) {
            checkBinary(_error, chidren_, MathType.RATE, MathType.RATE);
            return;
        }
        if (processUnaryNum()) {
            checkUnary(_error, chidren_, MathType.RATE);
            return;
        }
        if (procStatis()) {
            processStat(_error, chidren_);
            return;
        }
        if (processInterval()) {
            processInterval(_error, chidren_);
            return;
        }
        if (processBinInterval()) {
            checkBinary(_error, chidren_, MathType.RATE, MathType.RATE);
            return;
        }
        if (StringUtil.quickEq(methodName,CARD)){
            checkUnary(_error, chidren_, MathType.SET);
            return;
        }
        if (checkBinSet()) {
            checkBinary(_error, chidren_, MathType.SET, MathType.SET);
            return;
        }
        if (checkBinSet2()) {
            checkBinary(_error, chidren_, MathType.SET, MathType.RATE);
            return;
        }
        _error.setIndex(getIndexInEl());
        _error.setError(true);
    }

    private boolean checkBinSet2() {
        return StringUtil.quickEq(methodName, INCL) || StringUtil.quickEq(methodName, NON_INCL) || StringUtil.quickEq(methodName, EQ_NUM) || StringUtil.quickEq(methodName, NON_EQ_NUM);
    }

    private boolean checkBinSet() {
        return StringUtil.quickEq(methodName, INTER) || StringUtil.quickEq(methodName, UNION) || StringUtil.quickEq(methodName, COMPL);
    }

    private boolean processBinInterval() {
        return StringUtil.quickEq(methodName, CARAC_DROITE_OUVERT) || StringUtil.quickEq(methodName, CARAC_DROITE_FERME) || StringUtil.quickEq(methodName, CARAC_GAUCHE_OUVERT) || StringUtil.quickEq(methodName, CARAC_GAUCHE_FERME);
    }

    private boolean processInterval() {
        return StringUtil.quickEq(methodName, CARAC_FERME) || StringUtil.quickEq(methodName, CARAC_OUVERT) || StringUtil.quickEq(methodName, CARAC_SEMI_OUVERT_G) || StringUtil.quickEq(methodName, CARAC_SEMI_OUVERT_D);
    }

    private boolean procStatis() {
        return StringUtil.quickEq(methodName, MIN) || StringUtil.quickEq(methodName, MAX) || StringUtil.quickEq(methodName, MOY) || StringUtil.quickEq(methodName, VAR);
    }

    private boolean processUnaryNum() {
        return processUnaryNum2() || StringUtil.quickEq(methodName, NUM) || StringUtil.quickEq(methodName, DEN) || StringUtil.quickEq(methodName, SGN);
    }

    private boolean processUnaryNum2() {
        return StringUtil.quickEq(methodName, ABS) || StringUtil.quickEq(methodName, ENT) || StringUtil.quickEq(methodName, TRONC);
    }

    private boolean checkBinNum() {
        return checkBinNum2() || StringUtil.quickEq(methodName, MODTAUX) || StringUtil.quickEq(methodName, DIV_FCT);
    }

    private boolean checkBinNum2() {
        return StringUtil.quickEq(methodName, PUIS) || StringUtil.quickEq(methodName, QUOT) || StringUtil.quickEq(methodName, MOD);
    }

    private void processInterval(ErrorStatus _error, CustList<MbOperationNode> _chidren) {
        if (processSets(_chidren)) {
            _error.setIndex(getIndexInEl());
            _error.setError(true);
            return;
        }
        setResultClass(MathType.RATE);
    }

    private void processStat(ErrorStatus _error, CustList<MbOperationNode> _chidren) {
        if (_chidren.isEmpty()) {
            _error.setIndex(getIndexInEl());
            _error.setError(true);
            return;
        }
        for (MbOperationNode a : _chidren) {
            if (a.getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
        }
        setResultClass(MathType.RATE);
    }

    private void checkUnary(ErrorStatus _error, CustList<MbOperationNode> _chidren, MathType _type) {
        if (unary(_chidren, _type)) {
            _error.setIndex(getIndexInEl());
            _error.setError(true);
            return;
        }
        setResultClass(MathType.RATE);
    }

    private void checkBinary(ErrorStatus _error, CustList<MbOperationNode> _chidren, MathType _inType, MathType _outType) {
        if (binary(_chidren, _inType)) {
            _error.setIndex(getIndexInEl());
            _error.setError(true);
            return;
        }
        setResultClass(_outType);
    }

    private static boolean processSets(CustList<MbOperationNode> _chidren) {
        return _chidren.size() != THREE_ARGUMENTS || _chidren.first().getResultClass() != MathType.RATE || _chidren.get(IndexConstants.SECOND_INDEX).getResultClass() != MathType.RATE || _chidren.last().getResultClass() != MathType.RATE;
    }

    private static boolean unary(CustList<MbOperationNode> _chidren, MathType _type) {
        return _chidren.size() != 1 || _chidren.first().getResultClass() != _type;
    }

    private static boolean binary(CustList<MbOperationNode> _chidren, MathType _type) {
        return _chidren.size() != 2 || _chidren.first().getResultClass() != _type || _chidren.last().getResultClass() != _type;
    }

    @Override
    void calculate(StringMap<String> _conf, ErrorStatus _error) {
        CustList<MbOperationNode> chidren_ = getChildrenNodes();
        if (StringUtil.quickEq(methodName,PUIS)) {
            procPuiss(_error, chidren_);
            return;
        }
        if (StringUtil.quickEq(methodName,QUOT)) {
            procQuot(_error, chidren_);
            return;
        }
        if (StringUtil.quickEq(methodName,MOD)) {
            procMod(_error, chidren_);
            return;
        }
        if (StringUtil.quickEq(methodName,MODTAUX)) {
            procModTaux(_error, chidren_);
            return;
        }
        if (StringUtil.quickEq(methodName,DIV_FCT)){
            procDiv(_error, chidren_);
            return;
        }
        //valeur absolue
        if (StringUtil.quickEq(methodName,ABS)){
            Rate texteArg_= chidren_.first().getArgument().getRateVal();
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(texteArg_.absNb());
            setArgument(arg_);
            return;
        }
        //partie entiere
        if (StringUtil.quickEq(methodName,ENT)){
            Rate texteArg_= chidren_.first().getArgument().getRateVal();
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(new Rate(texteArg_.intPart()));
            setArgument(arg_);
            return;
        }
        //troncature vers l'entier en valeur absolue le plus proche
        //exemples:
        //troncature(32/10)=3,troncature(35/10)=3,troncature(37/10)=3,troncature(4)=4
        //troncature(-2)=-2,troncature(-21/10)=-2,troncature(-25/10)=-2,troncature(-26/10)=-2
        if (StringUtil.quickEq(methodName,TRONC)){
            Rate texteArg_= chidren_.first().getArgument().getRateVal();
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(new Rate(texteArg_.toLgInt()));
            setArgument(arg_);
            return;
        }
        calc2(chidren_);
    }

    private void calc2(CustList<MbOperationNode> _chidren) {
        //numerateur
        if (StringUtil.quickEq(methodName,NUM)){
            Rate texteArg_= _chidren.first().getArgument().getRateVal();
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(new Rate(texteArg_.getNumeratorCopy()));
            setArgument(arg_);
            return;
        }
        //denominateur
        if (StringUtil.quickEq(methodName,DEN)){
            Rate texteArg_= _chidren.first().getArgument().getRateVal();
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(new Rate(texteArg_.getDenominatorCopy()));
            setArgument(arg_);
            return;
        }
        if (StringUtil.quickEq(methodName,SGN)){
            Rate texteArg_= _chidren.first().getArgument().getRateVal();
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(texteArg_.signum());
            setArgument(arg_);
            return;
        }
        calc(_chidren);
    }

    private void procDiv(ErrorStatus _error, CustList<MbOperationNode> _chidren) {
        Rate rateOne_ = _chidren.first().getArgument().getRateVal();
        Rate rateTwo_ = _chidren.last().getArgument().getRateVal();
        if (rateTwo_.isZero()) {
            _error.setIndex(getIndexInEl());
            _error.setError(true);
            return;
        }
        Rate res_=Rate.divide(rateOne_, rateTwo_);
        MbArgument arg_ = new MbArgument();
        arg_.setArgClass(MathType.RATE);
        arg_.setObject(res_);
        setArgument(arg_);
    }

    private void procModTaux(ErrorStatus _error, CustList<MbOperationNode> _chidren) {
        Rate rateOne_ = _chidren.first().getArgument().getRateVal();
        Rate rateTwo_ = _chidren.last().getArgument().getRateVal();
        if (rateTwo_.isZero()) {
            _error.setIndex(getIndexInEl());
            _error.setError(true);
            return;
        }
        Rate res_=Rate.minus(rateOne_,Rate.multiply(new Rate(Rate.divide(rateOne_,rateTwo_).intPart()),rateTwo_));
        MbArgument arg_ = new MbArgument();
        arg_.setArgClass(MathType.RATE);
        arg_.setObject(res_);
        setArgument(arg_);
    }

    private void procMod(ErrorStatus _error, CustList<MbOperationNode> _chidren) {
        Rate base_= _chidren.first().getArgument().getRateVal();
        Rate exposant_= _chidren.last().getArgument().getRateVal();
        LgInt divisor_ = exposant_.intPart();
        if (divisor_.isZero()) {
            _error.setIndex(getIndexInEl());
            _error.setError(true);
            return;
        }
        MbArgument arg_ = new MbArgument();
        arg_.setArgClass(MathType.RATE);
        arg_.setObject(new Rate(LgInt.remain(base_.intPart(), divisor_)));
        setArgument(arg_);
    }

    private void procQuot(ErrorStatus _error, CustList<MbOperationNode> _chidren) {
        Rate base_= _chidren.first().getArgument().getRateVal();
        Rate exposant_= _chidren.last().getArgument().getRateVal();
        if (exposant_.isZero()) {
            _error.setIndex(getIndexInEl());
            _error.setError(true);
            return;
        }
        MbArgument arg_ = new MbArgument();
        arg_.setArgClass(MathType.RATE);
        arg_.setObject(new Rate(LgInt.divide(base_.intPart(), exposant_.intPart())));
        setArgument(arg_);
    }

    private void procPuiss(ErrorStatus _error, CustList<MbOperationNode> _chidren) {
        Rate base_= _chidren.first().getArgument().getRateVal();
        Rate exposant_= _chidren.last().getArgument().getRateVal();
        if (base_.isZero() && !exposant_.isZeroOrGt()) {
            _error.setIndex(getIndexInEl());
            _error.setError(true);
            return;
        }
        MbArgument arg_ = new MbArgument();
        arg_.setArgClass(MathType.RATE);
        arg_.setObject(Rate.powNb(base_, exposant_));
        setArgument(arg_);
    }

    private void calc(CustList<MbOperationNode> _chidren) {
        if (StringUtil.quickEq(methodName,MIN)){
            Rate min_= _chidren.first().getArgument().getRateVal();
            for(MbOperationNode a: _chidren.mid(IndexConstants.SECOND_INDEX)){
                Rate arg_= a.getArgument().getRateVal();
                if(Rate.strGreater(min_, arg_)){
                    min_=arg_;
                }
            }
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(min_);
            setArgument(arg_);
            return;
        }
        //maximum
        if (StringUtil.quickEq(methodName,MAX)){
            Rate min_= _chidren.first().getArgument().getRateVal();
            for(MbOperationNode a: _chidren.mid(IndexConstants.SECOND_INDEX)){
                Rate arg_= a.getArgument().getRateVal();
                if(Rate.strLower(min_, arg_)){
                    min_=arg_;
                }
            }
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(min_);
            setArgument(arg_);
            return;
        }
        //moyenne
        if (StringUtil.quickEq(methodName,MOY)){
            Rate moy_ = getMoy(_chidren);
            moy_.divideBy(new Rate(_chidren.size()));
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(moy_);
            setArgument(arg_);
            return;
        }
        //variance
        if (StringUtil.quickEq(methodName,VAR)){
            Rate var_ = getVar(_chidren);
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(var_);
            setArgument(arg_);
            return;
        }
        calcTwoSets2(_chidren);
    }

    private static Rate getVar(CustList<MbOperationNode> _chidren) {
        Rate var_=Rate.zero();
        Rate moy_=Rate.zero();
        for(MbOperationNode a: _chidren){
            Rate a_ = a.getArgument().getRateVal();
            moy_.addNb(a_);
            var_.addNb(Rate.multiply(a_, a_));
        }
        moy_.divideBy(new Rate(_chidren.size()));
        var_.divideBy(new Rate(_chidren.size()));
        var_.removeNb(Rate.multiply(moy_, moy_));
        return var_;
    }

    private static Rate getMoy(CustList<MbOperationNode> _chidren) {
        Rate moy_=Rate.zero();
        for(MbOperationNode a: _chidren){
            moy_.addNb(a.getArgument().getRateVal());
        }
        return moy_;
    }

    private void calcTwoSets2(CustList<MbOperationNode> _chidren) {
        //segment
        if (StringUtil.quickEq(methodName,CARAC_FERME)){
            Rate res_ = segment(_chidren);
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        //ouvert borne
        if (StringUtil.quickEq(methodName,CARAC_OUVERT)){
            Rate res_ = caracouvert(_chidren);
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        //semi ouvert gauche borne
        if (StringUtil.quickEq(methodName,CARAC_SEMI_OUVERT_G)){
            Rate res_ = caracsemiouvertg(_chidren);
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        //semi ouvert droite borne
        if (StringUtil.quickEq(methodName,CARAC_SEMI_OUVERT_D)){
            Rate res_ = caracsemiouvertd(_chidren);
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        //ouvert droite minore
        if (StringUtil.quickEq(methodName,CARAC_DROITE_OUVERT)){
            Rate res_ = caracdroiteouvert(_chidren);
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        //ferme droite minore
        if (StringUtil.quickEq(methodName,CARAC_DROITE_FERME)){
            Rate res_ = caracdroiteferme(_chidren);
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        //ouvert droite majore
        if (StringUtil.quickEq(methodName,CARAC_GAUCHE_OUVERT)){
            Rate res_ = caracgaucheouvert(_chidren);
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        //ferme gauche majore
        if (StringUtil.quickEq(methodName,CARAC_GAUCHE_FERME)){
            Rate res_ = caracgaucheferme(_chidren);
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        calcTwoSets(_chidren);
    }

    private static Rate caracgaucheferme(CustList<MbOperationNode> _chidren) {
        Rate res_=Rate.zero();
        Rate rateOne_ = _chidren.first().getArgument().getRateVal();
        Rate rateTwo_ = _chidren.last().getArgument().getRateVal();
        if(Rate.lowerEq(rateOne_,rateTwo_)){
            res_=Rate.one();
        }
        return res_;
    }

    private static Rate caracgaucheouvert(CustList<MbOperationNode> _chidren) {
        Rate res_=Rate.zero();
        Rate rateOne_ = _chidren.first().getArgument().getRateVal();
        Rate rateTwo_ = _chidren.last().getArgument().getRateVal();
        if(Rate.strLower(rateOne_,rateTwo_)){
            res_=Rate.one();
        }
        return res_;
    }

    private static Rate caracdroiteferme(CustList<MbOperationNode> _chidren) {
        Rate res_=Rate.zero();
        Rate rateOne_ = _chidren.first().getArgument().getRateVal();
        Rate rateTwo_ = _chidren.last().getArgument().getRateVal();
        if(Rate.greaterEq(rateOne_,rateTwo_)){
            res_=Rate.one();
        }
        return res_;
    }

    private static Rate caracdroiteouvert(CustList<MbOperationNode> _chidren) {
        Rate res_=Rate.zero();
        Rate rateOne_ = _chidren.first().getArgument().getRateVal();
        Rate rateTwo_ = _chidren.last().getArgument().getRateVal();
        if(Rate.strGreater(rateOne_,rateTwo_)){
            res_=Rate.one();
        }
        return res_;
    }

    private static Rate caracsemiouvertd(CustList<MbOperationNode> _chidren) {
        Rate res_=Rate.zero();
        Rate rateOne_ = _chidren.first().getArgument().getRateVal();
        Rate rateTwo_ = _chidren.get(IndexConstants.SECOND_INDEX).getArgument().getRateVal();
        Rate rateThree_ = _chidren.last().getArgument().getRateVal();
        if(Rate.greaterEq(rateOne_,rateTwo_)&&Rate.strLower(rateOne_,rateThree_)){
            res_=Rate.one();
        }
        return res_;
    }

    private static Rate caracsemiouvertg(CustList<MbOperationNode> _chidren) {
        Rate res_=Rate.zero();
        Rate rateOne_ = _chidren.first().getArgument().getRateVal();
        Rate rateTwo_ = _chidren.get(IndexConstants.SECOND_INDEX).getArgument().getRateVal();
        Rate rateThree_ = _chidren.last().getArgument().getRateVal();
        if(Rate.strGreater(rateOne_,rateTwo_)&&Rate.lowerEq(rateOne_, rateThree_)){
            res_=Rate.one();
        }
        return res_;
    }

    private static Rate caracouvert(CustList<MbOperationNode> _chidren) {
        Rate res_=Rate.zero();
        Rate rateOne_ = _chidren.first().getArgument().getRateVal();
        Rate rateTwo_ = _chidren.get(IndexConstants.SECOND_INDEX).getArgument().getRateVal();
        Rate rateThree_ = _chidren.last().getArgument().getRateVal();
        if(Rate.strGreater(rateOne_,rateTwo_)&&Rate.strLower(rateOne_,rateThree_)){
            res_=Rate.one();
        }
        return res_;
    }

    private static Rate segment(CustList<MbOperationNode> _chidren) {
        Rate res_=Rate.zero();
        Rate rateOne_ = _chidren.first().getArgument().getRateVal();
        Rate rateTwo_ = _chidren.get(IndexConstants.SECOND_INDEX).getArgument().getRateVal();
        Rate rateThree_ = _chidren.last().getArgument().getRateVal();
        if(Rate.greaterEq(rateOne_,rateTwo_)&&Rate.lowerEq(rateOne_,rateThree_)){
            res_=Rate.one();
        }
        return res_;
    }

    private void calcTwoSets(CustList<MbOperationNode> _chidren) {
        if (StringUtil.quickEq(methodName,CARD)){
            MathList texteArg_= _chidren.first().getArgument().getListVal();
            MathList set_ = new MathList(texteArg_);
            set_.removeDuplicates();
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(new Rate(set_.size()));
            setArgument(arg_);
            return;
        }
        if (StringUtil.quickEq(methodName,INTER)){
            MathList textArgOne_= _chidren.first().getArgument().getListVal();
            MathList textArgTwo_= _chidren.last().getArgument().getListVal();
            MathList inter_ = new MathList(textArgOne_.intersectStr(textArgTwo_));
            inter_.sort();
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.SET);
            arg_.setObject(inter_);
            setArgument(arg_);
            return;
        }
        if (StringUtil.quickEq(methodName,UNION)){
            MathList textArgOne_= _chidren.first().getArgument().getListVal();
            MathList textArgTwo_= _chidren.last().getArgument().getListVal();
            MathList union_ = new MathList(textArgOne_);
            union_.addAllElts(textArgTwo_);
            union_.sort();
            union_.removeDuplicates();
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.SET);
            arg_.setObject(union_);
            setArgument(arg_);
            return;
        }
        if (StringUtil.quickEq(methodName,COMPL)){
            MathList textArgOne_= _chidren.first().getArgument().getListVal();
            MathList textArgTwo_= _chidren.last().getArgument().getListVal();
            MathList res_ = new MathList(textArgTwo_);
            res_.removeAllElements(textArgOne_);
            res_.sort();
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.SET);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        calcTwoSetsRate(_chidren);
    }

    private void calcTwoSetsRate(CustList<MbOperationNode> _chidren) {
        if (StringUtil.quickEq(methodName,INCL)){
            MathList textArgOne_= _chidren.first().getArgument().getListVal();
            MathList textArgTwo_= _chidren.last().getArgument().getListVal();
            procBool(textArgTwo_.containsAllObj(textArgOne_));
            return;
        }
        if (StringUtil.quickEq(methodName,NON_INCL)){
            MathList textArgOne_= _chidren.first().getArgument().getListVal();
            MathList textArgTwo_= _chidren.last().getArgument().getListVal();
            procBool(!textArgTwo_.containsAllObj(textArgOne_));
            return;
        }
        if (StringUtil.quickEq(methodName,EQ_NUM)){
            MathList textArgOne_= _chidren.first().getArgument().getListVal();
            MathList textArgTwo_= _chidren.last().getArgument().getListVal();
            procBool(textArgOne_.eq(textArgTwo_));
            return;
        }
        MathList textArgOne_= _chidren.first().getArgument().getListVal();
        MathList textArgTwo_= _chidren.last().getArgument().getListVal();
        procBool(!textArgOne_.eq(textArgTwo_));
    }

    private void procBool(boolean _cond) {
        if (_cond) {
            MbArgument arg_ = new MbArgument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(Rate.one());
            setArgument(arg_);
            return;
        }
        MbArgument arg_ = new MbArgument();
        arg_.setArgClass(MathType.RATE);
        arg_.setObject(Rate.zero());
        setArgument(arg_);
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        vs_.remove(0);
        getChildren().addAllEntries(vs_);
    }
}
