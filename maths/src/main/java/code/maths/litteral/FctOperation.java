package code.maths.litteral;
import code.maths.LgInt;
import code.maths.MathList;
import code.maths.Rate;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class FctOperation extends InvokingOperation {

    private static final int THREE_ARGUMENTS = 3;
    private String methodName;

    public FctOperation(String _el, int _index, StringMap<String> _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    @Override
    void analyze(CustList<OperationNode> _nodes, StringMap<String> _conf, ErrorStatus _error) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (StringList.quickEq(methodName,PUIS)) {
            if (chidren_.size() != 2) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        if (StringList.quickEq(methodName,QUOT)) {
            if (chidren_.size() != 2) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        if (StringList.quickEq(methodName,MOD)) {
            if (chidren_.size() != 2) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        if (StringList.quickEq(methodName,MODTAUX)) {
            if (chidren_.size() != 2) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        if (StringList.quickEq(methodName,DIV_FCT)){
            if (chidren_.size() != 2) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        //valeur absolue
        if (StringList.quickEq(methodName,ABS)){
            if (chidren_.size() != 1) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        //partie entiere
        if (StringList.quickEq(methodName,ENT)){
            if (chidren_.size() != 1) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        //troncature vers l'entier en valeur absolue le plus proche
        //exemples:
        //troncature(32/10)=3,troncature(35/10)=3,troncature(37/10)=3,troncature(4)=4
        //troncature(-2)=-2,troncature(-21/10)=-2,troncature(-25/10)=-2,troncature(-26/10)=-2
        if (StringList.quickEq(methodName,TRONC)){
            if (chidren_.size() != 1) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        //numerateur
        if (StringList.quickEq(methodName,NUM)){
            if (chidren_.size() != 1) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        //denominateur
        if (StringList.quickEq(methodName,DEN)){
            if (chidren_.size() != 1) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        if (StringList.quickEq(methodName,SGN)){
            if (chidren_.size() != 1) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        if (StringList.quickEq(methodName,MIN)){
            if (chidren_.isEmpty()) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            for(OperationNode a:chidren_.mid(CustList.SECOND_INDEX)){
                if (a.getResultClass() != MathType.RATE) {
                    _error.setIndex(getIndexInEl());
                    _error.setError(true);
                    return;
                }
            }
            setResultClass(MathType.RATE);
            return;
        }
        //maximum
        if (StringList.quickEq(methodName,MAX)){
            if (chidren_.isEmpty()) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            for(OperationNode a:chidren_.mid(CustList.SECOND_INDEX)){
                if (a.getResultClass() != MathType.RATE) {
                    _error.setIndex(getIndexInEl());
                    _error.setError(true);
                    return;
                }
            }
            setResultClass(MathType.RATE);
            return;
        }
        //moyenne
        if (StringList.quickEq(methodName,MOY)){
            if (chidren_.isEmpty()) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            for(OperationNode a:chidren_){
                if (a.getResultClass() != MathType.RATE) {
                    _error.setIndex(getIndexInEl());
                    _error.setError(true);
                    return;
                }
            }
            setResultClass(MathType.RATE);
            return;
        }
        //variance
        if (StringList.quickEq(methodName,VAR)){
            if (chidren_.isEmpty()) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            for(OperationNode a:chidren_){
                if (a.getResultClass() != MathType.RATE) {
                    _error.setIndex(getIndexInEl());
                    _error.setError(true);
                    return;
                }
            }
            setResultClass(MathType.RATE);
            return;
        }
        //segment
        if (StringList.quickEq(methodName,CARAC_FERME)){
            if (chidren_.size() != THREE_ARGUMENTS) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.get(CustList.SECOND_INDEX).getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        //ouvert borne
        if (StringList.quickEq(methodName,CARAC_OUVERT)){
            if (chidren_.size() != THREE_ARGUMENTS) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.get(CustList.SECOND_INDEX).getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        //semi ouvert gauche borne
        if (StringList.quickEq(methodName,CARAC_SEMI_OUVERT_G)){
            if (chidren_.size() != THREE_ARGUMENTS) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.get(CustList.SECOND_INDEX).getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        //semi ouvert droite borne
        if (StringList.quickEq(methodName,CARAC_SEMI_OUVERT_D)){
            if (chidren_.size() != THREE_ARGUMENTS) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.get(CustList.SECOND_INDEX).getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        //ouvert droite minore
        if (StringList.quickEq(methodName,CARAC_DROITE_OUVERT)){
            if (chidren_.size() != 2) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        //ferme droite minore
        if (StringList.quickEq(methodName,CARAC_DROITE_FERME)){
            if (chidren_.size() != 2) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        //ouvert droite majore
        if (StringList.quickEq(methodName,CARAC_GAUCHE_OUVERT)){
            if (chidren_.size() != 2) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        //ferme gauche majore
        if (StringList.quickEq(methodName,CARAC_GAUCHE_FERME)){
            if (chidren_.size() != 2) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.RATE) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        if (StringList.quickEq(methodName,CARD)){
            if (chidren_.size() != 1) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.SET) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        if (StringList.quickEq(methodName,INTER)){
            if (chidren_.size() != 2) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.SET) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.SET) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.SET);
            return;
        }
        if (StringList.quickEq(methodName,UNION)){
            if (chidren_.size() != 2) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.SET) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.SET) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.SET);
            return;
        }
        if (StringList.quickEq(methodName,COMPL)){
            if (chidren_.size() != 2) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.SET) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.SET) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.SET);
            return;
        }
        if (StringList.quickEq(methodName,INCL)){
            if (chidren_.size() != 2) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.SET) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.SET) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        if (StringList.quickEq(methodName,NON_INCL)){
            if (chidren_.size() != 2) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.SET) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.SET) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        if (StringList.quickEq(methodName,EQ_NUM)){
            if (chidren_.size() != 2) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.SET) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.SET) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        if (StringList.quickEq(methodName,NON_EQ_NUM)){
            if (chidren_.size() != 2) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            if (chidren_.first().getResultClass() != MathType.SET) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            } else if (chidren_.last().getResultClass() != MathType.SET) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            setResultClass(MathType.RATE);
            return;
        }
        _error.setIndex(getIndexInEl());
        _error.setError(true);
    }
    @Override
    void calculate(CustList<OperationNode> _nodes, StringMap<String> _conf, ErrorStatus _error) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (StringList.quickEq(methodName,PUIS)) {
            Rate base_=(Rate) chidren_.first().getArgument().getObject();
            Rate exposant_=(Rate) chidren_.last().getArgument().getObject();
            if (base_.isZero() && !exposant_.isZeroOrGt()) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(Rate.powNb(base_, exposant_));
            setArgument(arg_);
            return;
        }
        if (StringList.quickEq(methodName,QUOT)) {
            Rate base_=(Rate) chidren_.first().getArgument().getObject();
            Rate exposant_=(Rate) chidren_.last().getArgument().getObject();
            if (exposant_.isZero()) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(new Rate(LgInt.divide(base_.intPart(), exposant_.intPart())));
            setArgument(arg_);
            return;
        }
        if (StringList.quickEq(methodName,MOD)) {
            Rate base_=(Rate) chidren_.first().getArgument().getObject();
            Rate exposant_=(Rate) chidren_.last().getArgument().getObject();
            LgInt divisor_ = exposant_.intPart();
            if (divisor_.isZero()) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(new Rate(LgInt.remain(base_.intPart(), divisor_)));
            setArgument(arg_);
            return;
        }
        if (StringList.quickEq(methodName,MODTAUX)) {
            Rate rateOne_ = (Rate) chidren_.first().getArgument().getObject();
            Rate rateTwo_ = (Rate) chidren_.last().getArgument().getObject();
            if (rateTwo_.isZero()) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            Rate res_=Rate.minus(rateOne_,Rate.multiply(new Rate(Rate.divide(rateOne_,rateTwo_).intPart()),rateTwo_));
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        if (StringList.quickEq(methodName,DIV_FCT)){
            Rate rateOne_ = (Rate) chidren_.first().getArgument().getObject();
            Rate rateTwo_ = (Rate) chidren_.last().getArgument().getObject();
            if (rateTwo_.isZero()) {
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            Rate res_=Rate.divide(rateOne_, rateTwo_);
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        //valeur absolue
        if (StringList.quickEq(methodName,ABS)){
            Rate texteArg_=(Rate) chidren_.first().getArgument().getObject();
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(texteArg_.absNb());
            setArgument(arg_);
            return;
        }
        //partie entiere
        if (StringList.quickEq(methodName,ENT)){
            Rate texteArg_=(Rate) chidren_.first().getArgument().getObject();
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(new Rate(texteArg_.intPart()));
            setArgument(arg_);
            return;
        }
        //troncature vers l'entier en valeur absolue le plus proche
        //exemples:
        //troncature(32/10)=3,troncature(35/10)=3,troncature(37/10)=3,troncature(4)=4
        //troncature(-2)=-2,troncature(-21/10)=-2,troncature(-25/10)=-2,troncature(-26/10)=-2
        if (StringList.quickEq(methodName,TRONC)){
            Rate texteArg_=(Rate) chidren_.first().getArgument().getObject();
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(new Rate(texteArg_.toLgInt()));
            setArgument(arg_);
            return;
        }
        //numerateur
        if (StringList.quickEq(methodName,NUM)){
            Rate texteArg_=(Rate) chidren_.first().getArgument().getObject();
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(new Rate(texteArg_.getNumeratorCopy()));
            setArgument(arg_);
            return;
        }
        //denominateur
        if (StringList.quickEq(methodName,DEN)){
            Rate texteArg_=(Rate) chidren_.first().getArgument().getObject();
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(new Rate(texteArg_.getDenominatorCopy()));
            setArgument(arg_);
            return;
        }
        if (StringList.quickEq(methodName,SGN)){
            Rate texteArg_=(Rate) chidren_.first().getArgument().getObject();
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(texteArg_.signum());
            setArgument(arg_);
            return;
        }
        if (StringList.quickEq(methodName,MIN)){
            Rate min_=(Rate) chidren_.first().getArgument().getObject();
            for(OperationNode a:chidren_.mid(CustList.SECOND_INDEX)){
                Rate arg_= (Rate) a.getArgument().getObject();
                if(Rate.strGreater(min_, arg_)){
                    min_=arg_;
                }
            }
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(min_);
            setArgument(arg_);
            return;
        }
        //maximum
        if (StringList.quickEq(methodName,MAX)){
            Rate min_=(Rate) chidren_.first().getArgument().getObject();
            for(OperationNode a:chidren_.mid(CustList.SECOND_INDEX)){
                Rate arg_= (Rate) a.getArgument().getObject();
                if(Rate.strLower(min_, arg_)){
                    min_=arg_;
                }
            }
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(min_);
            setArgument(arg_);
            return;
        }
        //moyenne
        if (StringList.quickEq(methodName,MOY)){
            Rate moy_=Rate.zero();
            for(OperationNode a:chidren_){
                moy_.addNb((Rate) a.getArgument().getObject());
            }
            moy_.divideBy(new Rate(chidren_.size()));
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(moy_);
            setArgument(arg_);
            return;
        }
        //variance
        if (StringList.quickEq(methodName,VAR)){
            Rate var_=Rate.zero();
            Rate moy_=Rate.zero();
            for(OperationNode a:chidren_){
                Rate a_ = (Rate) a.getArgument().getObject();
                moy_.addNb(a_);
                var_.addNb(Rate.multiply(a_, a_));
            }
            moy_.divideBy(new Rate(chidren_.size()));
            var_.divideBy(new Rate(chidren_.size()));
            var_.removeNb(Rate.multiply(moy_, moy_));
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(var_);
            setArgument(arg_);
            return;
        }
        //segment
        if (StringList.quickEq(methodName,CARAC_FERME)){
            Rate res_=Rate.zero();
            Rate rateOne_ = (Rate) chidren_.first().getArgument().getObject();
            Rate rateTwo_ = (Rate) chidren_.get(CustList.SECOND_INDEX).getArgument().getObject();
            Rate rateThree_ = (Rate) chidren_.last().getArgument().getObject();
            if(Rate.greaterEq(rateOne_,rateTwo_)&&Rate.lowerEq(rateOne_,rateThree_)){
                res_=Rate.one();
            }
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        //ouvert borne
        if (StringList.quickEq(methodName,CARAC_OUVERT)){
            Rate res_=Rate.zero();
            Rate rateOne_ = (Rate) chidren_.first().getArgument().getObject();
            Rate rateTwo_ = (Rate) chidren_.get(CustList.SECOND_INDEX).getArgument().getObject();
            Rate rateThree_ = (Rate) chidren_.last().getArgument().getObject();
            if(Rate.strGreater(rateOne_,rateTwo_)&&Rate.strLower(rateOne_,rateThree_)){
                res_=Rate.one();
            }
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        //semi ouvert gauche borne
        if (StringList.quickEq(methodName,CARAC_SEMI_OUVERT_G)){
            Rate res_=Rate.zero();
            Rate rateOne_ = (Rate) chidren_.first().getArgument().getObject();
            Rate rateTwo_ = (Rate) chidren_.get(CustList.SECOND_INDEX).getArgument().getObject();
            Rate rateThree_ = (Rate) chidren_.last().getArgument().getObject();
            if(Rate.strGreater(rateOne_,rateTwo_)&&Rate.lowerEq(rateOne_, rateThree_)){
                res_=Rate.one();
            }
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        //semi ouvert droite borne
        if (StringList.quickEq(methodName,CARAC_SEMI_OUVERT_D)){
            Rate res_=Rate.zero();
            Rate rateOne_ = (Rate) chidren_.first().getArgument().getObject();
            Rate rateTwo_ = (Rate) chidren_.get(CustList.SECOND_INDEX).getArgument().getObject();
            Rate rateThree_ = (Rate) chidren_.last().getArgument().getObject();
            if(Rate.greaterEq(rateOne_,rateTwo_)&&Rate.strLower(rateOne_,rateThree_)){
                res_=Rate.one();
            }
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        //ouvert droite minore
        if (StringList.quickEq(methodName,CARAC_DROITE_OUVERT)){
            Rate res_=Rate.zero();
            Rate rateOne_ = (Rate) chidren_.first().getArgument().getObject();
            Rate rateTwo_ = (Rate) chidren_.last().getArgument().getObject();
            if(Rate.strGreater(rateOne_,rateTwo_)){
                res_=Rate.one();
            }
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        //ferme droite minore
        if (StringList.quickEq(methodName,CARAC_DROITE_FERME)){
            Rate res_=Rate.zero();
            Rate rateOne_ = (Rate) chidren_.first().getArgument().getObject();
            Rate rateTwo_ = (Rate) chidren_.last().getArgument().getObject();
            if(Rate.greaterEq(rateOne_,rateTwo_)){
                res_=Rate.one();
            }
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        //ouvert droite majore
        if (StringList.quickEq(methodName,CARAC_GAUCHE_OUVERT)){
            Rate res_=Rate.zero();
            Rate rateOne_ = (Rate) chidren_.first().getArgument().getObject();
            Rate rateTwo_ = (Rate) chidren_.last().getArgument().getObject();
            if(Rate.strLower(rateOne_,rateTwo_)){
                res_=Rate.one();
            }
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        //ferme gauche majore
        if (StringList.quickEq(methodName,CARAC_GAUCHE_FERME)){
            Rate res_=Rate.zero();
            Rate rateOne_ = (Rate) chidren_.first().getArgument().getObject();
            Rate rateTwo_ = (Rate) chidren_.last().getArgument().getObject();
            if(Rate.lowerEq(rateOne_,rateTwo_)){
                res_=Rate.one();
            }
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        if (StringList.quickEq(methodName,CARD)){
            MathList texteArg_=(MathList) chidren_.first().getArgument().getObject();
            MathList set_ = new MathList(texteArg_);
            set_.removeDuplicates();
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(new Rate(set_.size()));
            setArgument(arg_);
            return;
        }
        if (StringList.quickEq(methodName,INTER)){
            MathList textArgOne_=(MathList) chidren_.first().getArgument().getObject();
            MathList textArgTwo_=(MathList) chidren_.last().getArgument().getObject();
            MathList inter_ = new MathList(textArgOne_.intersectStr(textArgTwo_));
            inter_.sort();
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.SET);
            arg_.setObject(inter_);
            setArgument(arg_);
            return;
        }
        if (StringList.quickEq(methodName,UNION)){
            MathList textArgOne_=(MathList) chidren_.first().getArgument().getObject();
            MathList textArgTwo_=(MathList) chidren_.last().getArgument().getObject();
            MathList union_ = new MathList(textArgOne_);
            union_.addAllElts(textArgTwo_);
            union_.sort();
            union_.removeDuplicates();
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.SET);
            arg_.setObject(union_);
            setArgument(arg_);
            return;
        }
        if (StringList.quickEq(methodName,COMPL)){
            MathList textArgOne_=(MathList) chidren_.first().getArgument().getObject();
            MathList textArgTwo_=(MathList) chidren_.last().getArgument().getObject();
            MathList res_ = new MathList(textArgTwo_);
            res_.removeAllElements(textArgOne_);
            res_.sort();
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.SET);
            arg_.setObject(res_);
            setArgument(arg_);
            return;
        }
        if (StringList.quickEq(methodName,INCL)){
            MathList textArgOne_=(MathList) chidren_.first().getArgument().getObject();
            MathList textArgTwo_=(MathList) chidren_.last().getArgument().getObject();
            if (textArgTwo_.containsAllObj(textArgOne_)) {
                Argument arg_ = new Argument();
                arg_.setArgClass(MathType.RATE);
                arg_.setObject(Rate.one());
                setArgument(arg_);
                return;
            }
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(Rate.zero());
            setArgument(arg_);
            return;
        }
        if (StringList.quickEq(methodName,NON_INCL)){
            MathList textArgOne_=(MathList) chidren_.first().getArgument().getObject();
            MathList textArgTwo_=(MathList) chidren_.last().getArgument().getObject();
            if (!textArgTwo_.containsAllObj(textArgOne_)) {
                Argument arg_ = new Argument();
                arg_.setArgClass(MathType.RATE);
                arg_.setObject(Rate.one());
                setArgument(arg_);
                return;
            }
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(Rate.zero());
            setArgument(arg_);
            return;
        }
        if (StringList.quickEq(methodName,EQ_NUM)){
            MathList textArgOne_=(MathList) chidren_.first().getArgument().getObject();
            MathList textArgTwo_=(MathList) chidren_.last().getArgument().getObject();
            if (textArgOne_.eq(textArgTwo_)) {
                Argument arg_ = new Argument();
                arg_.setArgClass(MathType.RATE);
                arg_.setObject(Rate.one());
                setArgument(arg_);
                return;
            }
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(Rate.zero());
            setArgument(arg_);
            return;
        }
        if (StringList.quickEq(methodName,NON_EQ_NUM)){
            MathList textArgOne_=(MathList) chidren_.first().getArgument().getObject();
            MathList textArgTwo_=(MathList) chidren_.last().getArgument().getObject();
            if (!textArgOne_.eq(textArgTwo_)) {
                Argument arg_ = new Argument();
                arg_.setArgClass(MathType.RATE);
                arg_.setObject(Rate.one());
                setArgument(arg_);
                return;
            }
            Argument arg_ = new Argument();
            arg_.setArgClass(MathType.RATE);
            arg_.setObject(Rate.zero());
            setArgument(arg_);
            return;
        }
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }
}
