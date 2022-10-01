package cards.consts;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;
import code.util.StringMap;


public final class ResultsGame {
    private String globalResultsPageTitle;
    private String detailResultsTitle;
    private StringMap<String> renderedPages = new StringMap<String>();
    /**Ecarts types des parties cumule&eacute;es*/
    private CustList<Rate> sigmas=new CustList<Rate>();
    /**Sommes des scores des joueurs*/
    private Longs sums=new Longs();
    private CustList<Longs> scores = new CustList<Longs>();
    private final CustList<LineDeal> history = new CustList<LineDeal>();
    private String general="";
    private String specific="";

    private StringList nicknames;

    private byte user;

    private String loc;
    public void calculateScores(CustList<Longs> _scores,Shorts _scoresDeal, GameType _type, long _number, int _nbDeals) {
        setScores(_scores);
        if(hasToCalculateScores(_type, _number, _nbDeals)) {
            calculateScores(_scoresDeal);
        }
    }
    public static boolean hasToCalculateScores(GameType _type, long _number, int _nbDeals) {
        return _type ==GameType.RANDOM&& _number ==0|| _type == GameType.EDIT && _number <= _nbDeals;
    }
    public void calculateScores(Shorts _scoresDeal) {
        long nbPl_ = _scoresDeal.size();
        long variance9_=0;
        long esperance_=0;
        CustList<LineDeal> history_ = getHistory();
        LineDeal next_ = new LineDeal();
        Longs deal_ = new Longs();
        next_.setScores(deal_);
        next_.setNumber(history_.size());
        history_.add(next_);
        getScores().add(deal_);
        Longs lastDeal_ = getScores().last();
        if(getScores().size()==1) {
            for (int i = 0; i < nbPl_; i++) {
                lastDeal_.add((long) _scoresDeal.get(i));
            }
        } else {
            for (int i = 0; i < nbPl_; i++) {
                lastDeal_.add(_scoresDeal.get(i)+getScores().get(getScores().size()-2).get(i));
            }
        }
        int nbScores_ = lastDeal_.size();
        for (int i = 0; i < nbScores_; i++) {
            esperance_+=lastDeal_.get(i);
        }
        /*Somme des_ scores*/
        variance9_+=3*esperance_;
        /*Somme des_ scores fois_ trois_*/
        variance9_*=variance9_;
        /*Carre de_ la_ somme_ des_ scores fois_ trois_ (Le carre_ comprend_ le_ fois_ trois_)*/
        variance9_=-variance9_;
        /*Oppose du_ carre_ de_ la_ somme_ des_ scores fois_ trois_*/
        for (int i = 0; i < nbScores_; i++) {
            long score_ = lastDeal_.get(i);
            variance9_+=score_*score_*9* nbPl_;
        }
        /*variance9_ vaut_ neuf_ fois_ la_ variance_ des_ scores fois_ le_ carre_ du_ nombre_ de_ joueurs_*/
        getSigmas().add(new Rate(variance9_, nbPl_ * nbPl_).rootAbs(new LgInt(2)));
        getSums().add(esperance_);
    }

    public StringList getNicknames() {
        return nicknames;
    }
    public byte getUser() {
        return user;
    }
    public void setUser(byte _user) {
        user = _user;
    }
    public String getLoc() {
        return loc;
    }

    public void setNicknames(StringList _nicknames) {
        nicknames = _nicknames;
    }

    public void setLoc(String _loc) {
        loc = _loc;
    }
    public String getGlobalResultsPageTitle() {
        return globalResultsPageTitle;
    }

    public void setGlobalResultsPageTitle(String _globalResultsPageTitle) {
        globalResultsPageTitle = _globalResultsPageTitle;
    }

    public String getDetailResultsTitle() {
        return detailResultsTitle;
    }

    public void setDetailResultsTitle(String _detailResultsTitle) {
        detailResultsTitle = _detailResultsTitle;
    }

    public StringMap<String> getRenderedPages() {
        return renderedPages;
    }

    public CustList<Rate> getSigmas() {
        return sigmas;
    }

    public Longs getSums() {
        return sums;
    }

    public CustList<Longs> getScores() {
        return scores;
    }

    public void setScores(CustList<Longs> _scores) {
        scores = new CustList<Longs>(_scores);
        CustList<LineDeal> hist_ = new CustList<LineDeal>();
        int dc_ = _scores.size();
        for (int i = 0; i < dc_; i++) {
            LineDeal ld_ = new LineDeal();
            ld_.setNumber(i);
            ld_.setScores(new Longs(_scores.get(i)));
            hist_.add(ld_);
        }
        getHistory().clear();
        getHistory().addAllElts(hist_);

    }

    public CustList<LineDeal> getHistory() {
        return history;
    }

    public void setRenderedPages(StringMap<String> _renderedPages) {
        renderedPages = _renderedPages;
    }

    public void setSigmas(CustList<Rate> _sigmas) {
        sigmas = _sigmas;
    }

    public void setSums(Longs _sums) {
        sums = _sums;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String _general) {
        this.general = _general;
    }

    public String getSpecific() {
        return specific;
    }

    public void setSpecific(String _specific) {
        this.specific = _specific;
    }
}
