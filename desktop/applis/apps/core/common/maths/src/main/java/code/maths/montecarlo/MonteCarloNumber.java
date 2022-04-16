package code.maths.montecarlo;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;


public final class MonteCarloNumber extends AbMonteCarlo<Rate> {

    private final CustList<EventFreq<Rate>> events;

    public MonteCarloNumber() {
        events = new CustList<EventFreq<Rate>>();
    }
    
    public MonteCarloNumber(CollCapacity _capacity) {
        events = new CustList<EventFreq<Rate>>(_capacity);
    }
    public boolean containsEvent(Rate _event) {
        for (EventFreq<Rate> e: events) {
            if (_event.eq(e.getEvent())) {
                return true;
            }
        }
        return false;
    }

    public Rate normalizedRate(Rate _event) {
        LgInt sum_ = sum();
        return new Rate(rate(_event), sum_);
    }
    public MonteCarloNumber copy() {
        MonteCarloNumber mc_ = new MonteCarloNumber(new CollCapacity(nbEvents()));
        for (EventFreq<Rate> e: events) {
            mc_.events.add(new EventFreq<Rate>(new Rate(e.getEvent()),new LgInt(e.getFreq())));
        }
        return mc_;
    }
    public LgInt rate(Rate _event) {
        LgInt sum_ = LgInt.zero();
        for (EventFreq<Rate> e: events) {
            if (_event.eq(e.getEvent())) {
                sum_.addNb(e.getFreq());
            }
        }
        return sum_;
    }
    /**Retourne l'esperance d'une loi de probabilite.*/
    public Rate getAvg(){
        Rate sum_ = Rate.zero();
        int nb_ = nbEvents();
        LgInt sumFreq_ = sum();
        for (int i = 0; i < nb_; i++) {
            Rate e_ = getEvent(i);
            sum_.addNb(Rate.multiply(e_, new Rate(getFreq(i),sumFreq_)));
        }
        /*
         LgInt sum_ = sum();
        return new Rate(rate(_event), sum_);*/
//        for(Rate c:events()){
//            sum_.addNb(Rate.multiply(c, normalizedRate(c)));
//        }
        return sum_;
    }

    /**Retourne l'esperance d'une loi de probabilite en tenant compte du biais du reste.*/
    public Rate getRealAvg(LgInt _max){
        Rate sommeNum_=Rate.zero();
        LgInt sommeDen_=LgInt.zero();
        MonteCarloNumber law_=new MonteCarloNumber();
        CustList<Rate> evenements_=events();
        for(Rate c:evenements_){
            sommeDen_.addNb(rate(c));
            law_.addQuickEvent(c,rate(c));
        }
        if (sommeDen_.isZero()) {
            return Rate.zero();
        }
        LgInt plusGdNbAlea_= MonteCarloUtil.maxNumber(_max);
        plusGdNbAlea_.increment();
        LgInt quotient_ = LgInt.divide(plusGdNbAlea_, sommeDen_);
        LgInt remain_ = LgInt.remain(plusGdNbAlea_, sommeDen_);
        LgInt sumDenTwo_=LgInt.zero();
        int nbEvenements_=evenements_.size();
        int maxIndice_ = getMaxIndice(law_, evenements_, remain_, sumDenTwo_);
        if (!remain_.isZero()) {
            MonteCarloNumber lawTwo_=new MonteCarloNumber();
            for (Rate c:evenements_) {
                lawTwo_.addQuickEvent(c, new LgInt(law_.rate(c)));
            }
            for (int i = IndexConstants.FIRST_INDEX; i<nbEvenements_; i++){
                LgInt effectif_=lawTwo_.getFreq(i);
                LgInt tmp_=new LgInt(effectif_);
                effectif_.multiplyBy(quotient_);
                if(i<maxIndice_){
                    effectif_.addNb(tmp_);
                } else if (i==maxIndice_) {
                    sumDenTwo_.affectZero();
                    addEvts(law_, evenements_, sumDenTwo_, maxIndice_);
                    effectif_.addNb(LgInt.minus(remain_,sumDenTwo_));
                }
            }
            law_=lawTwo_;
        }
        return sumRet(sommeNum_, sommeDen_, law_, evenements_, plusGdNbAlea_, remain_);
    }

    private static void addEvts(MonteCarloNumber _law, CustList<Rate> _evenements, LgInt _sumDenTwo, int _maxIndice) {
        for (int j = IndexConstants.FIRST_INDEX; j< _maxIndice; j++){
            _sumDenTwo.addNb(_law.rate(_evenements.get(j)));
        }
    }

    private static Rate sumRet(Rate _sommeNum, LgInt _sommeDen, MonteCarloNumber _law, CustList<Rate> _evenements, LgInt _plusGdNbAlea, LgInt _remain) {
        for(Rate c: _evenements){
            LgInt effectif_= _law.rate(c);
            _sommeNum.addNb(Rate.multiply(c, new Rate(effectif_)));
        }
        if(!_remain.isZero()){
            return Rate.divide(_sommeNum, new Rate(_plusGdNbAlea));
        }
        return Rate.divide(_sommeNum, new Rate(_sommeDen));
    }

    private static int getMaxIndice(MonteCarloNumber _law, CustList<Rate> _evenements, LgInt _remain, LgInt _sumDenTwo) {
        int maxIndice_;
        int i_ = IndexConstants.FIRST_INDEX;
        while (true) {
            _sumDenTwo.addNb(_law.rate(_evenements.get(i_)));
            if (LgInt.strLower(_remain, _sumDenTwo)) {
                maxIndice_=i_;
                break;
            }
            i_++;
        }
        return maxIndice_;
    }

    /**Retourne la variance d'une loi de probabilite.*/
    public Rate getVar(){
        Rate sum_ = Rate.zero();
        int nb_ = nbEvents();
        LgInt sumFreq_ = sum();
        for (int i = 0; i < nb_; i++) {
            Rate e_ = getEvent(i);
            Rate res_ = Rate.multiply(e_, e_);
            sum_.addNb(Rate.multiply(res_, new Rate(getFreq(i),sumFreq_)));
        }
//        for(Rate c:events()){
//            Rate res_ = Rate.multiply(c, c);
//            sum_.addNb(Rate.multiply(res_, normalizedRate(c)));
//        }
        Rate avg_ = getAvg();
        return Rate.minus(sum_, Rate.multiply(avg_, avg_));
    }

    /**Retourne le minimum de la loi de probabilite (soit le plus petit Xi)*/
    public Rate minimum() {
        Rate min_=Rate.zero();
        CustList<Rate> evenements_=events();
        if(evenements_.isEmpty()){
            return min_;
        }
        //Il faut bien initialiser le minimum, car 0 n'est pas forcement inferieur ou egal au minimum
        min_.affect(evenements_.first());
        for(Rate c:evenements_){
            if(Rate.strLower(c, min_)){
                min_.affect(c);
            }
        }
        return min_;
    }

    /**Retourne le maximum de la loi de probabilite (soit le plus petit Xi)*/
    public Rate maximum() {
        Rate max_=Rate.zero();
        CustList<Rate> evenements_=events();
        if(evenements_.isEmpty()){
            return max_;
        }
        //Il faut bien initialiser le maximum, car 0 n'est pas forcement superieur ou egal au maximum
        max_.affect(evenements_.first());
        for(Rate c:evenements_){
            if(Rate.strGreater(c, max_)){
                max_.affect(c);
            }
        }
        return max_;
    }

    public MonteCarloBoolean knowingLower(Rate _event) {
        MonteCarloBoolean loi_ = new MonteCarloBoolean();
        if(!containsEvent(_event)){
            if(Rate.lowerEq(_event, minimum())){
                loi_.addQuickEvent(BoolVal.FALSE,new LgInt(1));
                return loi_;
            }
            loi_.addQuickEvent(BoolVal.TRUE,new LgInt(1));
            return loi_;
        }
        LgInt somme_=LgInt.zero();
        int nb_ = nbEvents();
        for (int i = 0; i < nb_; i++) {
            if(Rate.strLower(getEvent(i), _event)){
                somme_.addNb(getFreq(i));
            }
        }
        loi_.addQuickEvent(BoolVal.FALSE,rate(_event));
        if(!somme_.isZero()){
            loi_.addQuickEvent(BoolVal.TRUE,somme_);
        }
        return loi_;
    }

    public MonteCarloBoolean knowingGreater(Rate _event) {
        MonteCarloBoolean loi_ = new MonteCarloBoolean();
        if(!containsEvent(_event)){
            if(Rate.greaterEq(_event, maximum())){
                loi_.addQuickEvent(BoolVal.FALSE,new LgInt(1));
                return loi_;
            }
            loi_.addQuickEvent(BoolVal.TRUE,new LgInt(1));
            return loi_;
        }
        LgInt somme_=LgInt.zero();
        int nb_ = nbEvents();
        for (int i = 0; i < nb_; i++) {
            if(Rate.strGreater(getEvent(i), _event)){
                somme_.addNb(getFreq(i));
            }
        }
        loi_.addQuickEvent(BoolVal.FALSE,rate(_event));
        if(!somme_.isZero()){
            loi_.addQuickEvent(BoolVal.TRUE,somme_);
        }
        return loi_;
    }

    @Override
    public Rate getEvent(int _index) {
        return events.get(_index).getEvent();
    }

    @Override
    public LgInt getFreq(int _index) {
        return events.get(_index).getFreq();
    }

    @Override
    public CustList<Rate> events() {
        CustList<Rate> evs_ = new CustList<Rate>(new CollCapacity(events.size()));
        for (EventFreq<Rate> e: events) {
            evs_.add(e.getEvent());
        }
        return evs_;
    }

    @Override
    public LgInt sum() {
        LgInt somme_= LgInt.zero();
        for (EventFreq<Rate> e: events) {
            somme_.addNb(e.getFreq());
        }
        return somme_;
    }

    public void addEvent(Rate _event, LgInt _probaRelative) {
        addQuickEvent(_event, _probaRelative);
    }

    public void addQuickEvent(Rate _event, LgInt _probaRelative) {
        events.add(new EventFreq<Rate>(_event, _probaRelative));
    }

    public CustList<EventFreq<Rate>> getEvents() {
        return events;
    }

    @Override
    public int nbEvents() {
        return getEvents().size();
    }
}
