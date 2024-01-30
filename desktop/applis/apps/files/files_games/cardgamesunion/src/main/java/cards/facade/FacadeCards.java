package cards.facade;

import cards.belote.*;
import cards.belote.sml.*;
import cards.consts.*;
import cards.facade.enumerations.*;
import cards.facade.sml.*;
import cards.president.*;
import cards.president.sml.*;
import cards.tarot.*;
import cards.tarot.sml.*;
import code.gui.initialize.*;
import code.sml.Document;
import code.stream.*;
import code.util.*;
import code.util.core.*;

public final class FacadeCards {

    public static final String RULES_BELOTE="belote.xml";
    public static final String RULES_PRESIDENT="president.xml";
    public static final String RULES_TAROT="tarot.xml";

    public static final String DISPLAY_BELOTE="dbelote.xml";
    public static final String DISPLAY_PRESIDENT="dpresident.xml";
    public static final String DISPLAY_TAROT="dtarot.xml";
    public static final String DECK_FOLDER="Paquets";
    public static final String DECK_FILE="Paquet.txt";
    public static final String PARAMS="parametres.xml";
    public static final String LANGUAGE="langue.xml";
    public static final String PLAYERS="joueurs.xml";
    public static final String DECK_EXT=".paquet";
    public static final char JOIN_STACKS = '_';

    private static final String EMPTY_STRING = "";
    private static final char LINE_RETURN = '\n';
    /**Parametres de lancement, de jouerie*/
    private SoftParams parametres=new SoftParams();
    /**
     des pseudonymes*/
    private Nicknames pseudosJoueurs = new Nicknames();
    private RulesBelote reglesBelote=new RulesBelote();
    private DisplayingBelote displayingBelote = new DisplayingBelote();
    private RulesPresident reglesPresident=new RulesPresident();
    private DisplayingPresident displayingPresident = new DisplayingPresident();
    private RulesTarot reglesTarot=new RulesTarot();
    private DisplayingTarot displayingTarot = new DisplayingTarot();
    private final CardGamesStream nicknamesCrud;
    public FacadeCards(CardGamesStream _a) {
        nicknamesCrud = _a;
    }
    public static void install(String _tempFolder, AbstractProgramInfos _list) {
        _list.getFileCoreStream().newFile(StringUtil.concat(_tempFolder, DECK_FOLDER)).mkdirs();
        AbstractFile f = _list.getFileCoreStream().newFile(FacadeCards.beloteStack(_tempFolder));
        HandBelote mainB_=HandBelote.pileBase();
        if(!f.exists()) {
            StreamTextFile.saveTextFile(f.getAbsolutePath(), DocumentWriterBeloteUtil.setHandBelote(mainB_), _list.getStreams());
        }
        f=_list.getFileCoreStream().newFile(tarotStack(_tempFolder));
        HandTarot mainT_=HandTarot.pileBase();
        if(!f.exists()) {
            StreamTextFile.saveTextFile(f.getAbsolutePath(), DocumentWriterTarotUtil.setHandTarot(mainT_), _list.getStreams());
        }
        int maxStacks_ = RulesPresident.getNbMaxStacksPlayers();
        for (int i = IndexConstants.ONE_ELEMENT; i <= maxStacks_; i++) {
            f=_list.getFileCoreStream().newFile(presidentStack(_tempFolder,i));
            HandPresident h_ = HandPresident.stack(i);
            if(!f.exists()) {
                StreamTextFile.saveTextFile(f.getAbsolutePath(), DocumentWriterPresidentUtil.setHandPresident(h_), _list.getStreams());
            }
        }
        f=_list.getFileCoreStream().newFile(stack(_tempFolder));
        if(!f.exists()) {
            StringList dealsNumbers_ = buildDeals(maxStacks_);
            StreamTextFile.saveTextFile(f.getAbsolutePath(), StringUtil.join(dealsNumbers_, LINE_RETURN), _list.getStreams());
        }
    }

    private static StringList buildDeals(int _maxStacks) {
        StringList dealsNumbers_ = new StringList();
        int nbGames_ = GameEnum.allValid().size();
        for (int i = IndexConstants.FIRST_INDEX; i<nbGames_; i++) {
            if (StringUtil.quickEq(Long.toString(i),GameEnum.PRESIDENT.getNumber())) {
                dealsNumbers_.add(dealsPre(_maxStacks));
            } else {
                dealsNumbers_.add("0");
            }
        }
        return dealsNumbers_;
    }

    private static String dealsPre(int _maxStacks) {
        CustList<String> sb_ = new CustList<String>();
        for (int j = IndexConstants.ONE_ELEMENT; j <= _maxStacks; j++) {
            sb_.add("0");
        }
        return StringUtil.join(sb_, JOIN_STACKS);
    }

    public static String beloteStack(String _folder) {
        return StringUtil.concat(_folder, DECK_FOLDER, StreamTextFile.SEPARATEUR, GameEnum.BELOTE.getNumber(), DECK_EXT);
    }
    public static String presidentStack(String _folder, int _s) {
        return StringUtil.concat(_folder, DECK_FOLDER, StreamTextFile.SEPARATEUR, GameEnum.PRESIDENT.getNumber(),"_",Long.toString(_s), DECK_EXT);
    }
    public static String tarotStack(String _folder) {
        return StringUtil.concat(_folder, DECK_FOLDER, StreamTextFile.SEPARATEUR, GameEnum.TAROT.getNumber(), DECK_EXT);
    }
    public static String stack(String _folder) {
        return StringUtil.concat(_folder, DECK_FOLDER, StreamTextFile.SEPARATEUR, DECK_FILE);
    }
    public void init(String _tempFolder, AbstractProgramInfos _list, String _lg) {
        reglesBelote = DocumentReaderBeloteUtil.getRulesBelote(StreamTextFile.contentsOfFile(StringUtil.concat(_tempFolder,RULES_BELOTE),_list.getFileCoreStream(),_list.getStreams()));
        if (!reglesBelote.isValidRules()) {
            reglesBelote = new RulesBelote();
            StreamTextFile.saveTextFile(StringUtil.concat(_tempFolder,RULES_BELOTE), DocumentWriterBeloteUtil.setRulesBelote(reglesBelote),_list.getStreams());
        }
        setDisplayingBelote(DocumentReaderBeloteUtil.getDisplayingBelote(StreamTextFile.contentsOfFile(StringUtil.concat(_tempFolder,DISPLAY_BELOTE),_list.getFileCoreStream(),_list.getStreams())));
        getDisplayingBelote().validate();
        reglesPresident = DocumentReaderPresidentUtil.getRulesPresident(StreamTextFile.contentsOfFile(StringUtil.concat(_tempFolder,RULES_PRESIDENT),_list.getFileCoreStream(),_list.getStreams()));
        if (!reglesPresident.isValidRules()) {
            reglesPresident = new RulesPresident();
            StreamTextFile.saveTextFile(StringUtil.concat(_tempFolder,RULES_PRESIDENT), DocumentWriterPresidentUtil.setRulesPresident(reglesPresident),_list.getStreams());
        }
        setDisplayingPresident(DocumentReaderPresidentUtil.getDisplayingPresident(StreamTextFile.contentsOfFile(StringUtil.concat(_tempFolder,DISPLAY_PRESIDENT),_list.getFileCoreStream(),_list.getStreams())));
        getDisplayingPresident().validate();
        reglesTarot = DocumentReaderTarotUtil.getRulesTarot(StreamTextFile.contentsOfFile(StringUtil.concat(_tempFolder,RULES_TAROT),_list.getFileCoreStream(),_list.getStreams()));
        if (!reglesTarot.isValidRules()) {
            reglesTarot = new RulesTarot();
            StreamTextFile.saveTextFile(StringUtil.concat(_tempFolder,RULES_TAROT), DocumentWriterTarotUtil.setRulesTarot(reglesTarot),_list.getStreams());
        }
        setDisplayingTarot(DocumentReaderTarotUtil.getDisplayingTarot(StreamTextFile.contentsOfFile(StringUtil.concat(_tempFolder,DISPLAY_TAROT),_list.getFileCoreStream(),_list.getStreams())));
        getDisplayingTarot().validate();
        setParametres(DocumentReaderCardsUnionUtil.getSoftParams(StreamTextFile.contentsOfFile(StringUtil.concat(_tempFolder,PARAMS),_list.getFileCoreStream(),_list.getStreams())));
        getParametres().setDelays();
//        parametres.setLocale(_locale);
        pseudosJoueurs = getNicknamesCrud().getNicknamesCrud().value();
        if (!pseudosJoueurs.isValidNicknames()) {
            pseudosJoueurs = new Nicknames(_lg);
            getNicknamesCrud().getNicknamesCrud().value(pseudosJoueurs);
        }
    }
    public Games load(String _file) {
        Games g_ = new Games();
        AbsCardGamesCrud cs_ = getNicknamesCrud().getCardGamesCrud();
        String content_ = cs_.read(_file);
        Document doc_ = cs_.parse(_file,content_);
        String tagName_ = cs_.tag(_file,content_,doc_);
        if (StringUtil.quickEq(tagName_, DocumentWriterBeloteUtil.TYPE_GAME_BELOTE)) {
            GameBelote par_ = cs_.belote(_file,doc_);
            CheckerGameBeloteWithRules.check(par_);
            if (!par_.getError().isEmpty()) {
                return g_;
            }
            g_.jouerBelote(par_);
            return g_;
        }
        if (StringUtil.quickEq(tagName_, DocumentWriterPresidentUtil.TYPE_GAME_PRESIDENT)) {
            GamePresident par_ = cs_.president(_file,doc_);
            CheckerGamePresidentWithRules.check(par_);
            if (!par_.getError().isEmpty()) {
                return g_;
            }
            g_.jouerPresident(par_);
            return g_;
        }
        if (StringUtil.quickEq(tagName_, DocumentWriterTarotUtil.TYPE_GAME_TAROT)) {
            GameTarot par_ = cs_.tarot(_file,doc_);
            CheckerGameTarotWithRules.check(par_);
            if (!par_.getError().isEmpty()) {
                return g_;
            }
            g_.jouerTarot(par_);
            return g_;
        }
        return g_;
    }

    public CardGamesStream getNicknamesCrud() {
        return nicknamesCrud;
    }

    public static void changerNombreDeParties(GameEnum _game, long _nbGames, String _tmpFolder, AbstractProgramInfos _tmpUserFolderSl, int _nbStacks) {
        String fileName_ = FacadeCards.stack(_tmpFolder);
        String content_ = StreamTextFile.contentsOfFile(fileName_,_tmpUserFolderSl.getFileCoreStream(),_tmpUserFolderSl.getStreams());
        StringList vl_= FacadeCards.retrieveLines(content_);
        //Si l'action de battre les cartes est faite a chaque lancement
        //de logiciel alors le nombre de parties est remis a zero lors
        //d'une fermeture de logiciel
        if (_game == GameEnum.PRESIDENT) {
            StringList line_ = StringUtil.splitChars(vl_.get(NumberUtil.parseInt(_game.getNumber())),JOIN_STACKS);
            int s_ = line_.size();
            for (int i = 0; i < s_; i++) {
                if (i + 1 == _nbStacks) {
                    line_.set(i, Long.toString(_nbGames + 1));
                }
            }
            vl_.set(NumberUtil.parseInt(_game.getNumber()), StringUtil.join(line_,JOIN_STACKS));
        } else {
            vl_.set(NumberUtil.parseInt(_game.getNumber()), Long.toString(_nbGames + 1));
        }
        StreamTextFile.saveTextFile(fileName_, StringUtil.join(vl_, LINE_RETURN),_tmpUserFolderSl.getStreams());
    }
    public void changerNombreDePartiesEnQuittant(String _tempFolder, AbstractProgramInfos _inst) {
        String fileName_ = stack(_tempFolder);
        String content_ = StreamTextFile.contentsOfFile(fileName_,_inst.getFileCoreStream(),_inst.getStreams());
        StringList vl_ = retrieveLines(content_);
        //Si l'action de battre les cartes est faite a chaque lancement
        //de logiciel alors le nombre de parties est remis a zero lors
        //d'une fermeture de logiciel

        if(reglesPresident.getCommon().getMixedCards() == MixCardsChoice.EACH_LAUNCHING) {
            vl_.set(NumberUtil.parseInt(GameEnum.PRESIDENT.getNumber()), dealsPre(RulesPresident.getNbMaxStacksPlayers()));
        }
        if(reglesBelote.getCommon().getMixedCards() ==MixCardsChoice.EACH_LAUNCHING) {
            vl_.set(NumberUtil.parseInt(GameEnum.BELOTE.getNumber()), "0");
        }
        if(reglesTarot.getCommon().getMixedCards() ==MixCardsChoice.EACH_LAUNCHING) {
            vl_.set(NumberUtil.parseInt(GameEnum.TAROT.getNumber()), "0");
        }
        StreamTextFile.saveTextFile(fileName_, StringUtil.join(vl_, LINE_RETURN),_inst.getStreams());
    }

    public static long chargerNombreDeParties(GameEnum _jeu, String _tmpFolder, AbstractProgramInfos _tmpUserFolderSl, int _nbStacks) {
        String fileName_ = FacadeCards.stack(_tmpFolder);
        String content_ = StreamTextFile.contentsOfFile(fileName_,_tmpUserFolderSl.getFileCoreStream(),_tmpUserFolderSl.getStreams());
        if (content_ == null) {
            return 0L;
        }
        StringList lines_ = StringUtil.splitChars(content_, LINE_RETURN);
        lines_.removeAllString(EMPTY_STRING);
        int index_ = NumberUtil.parseInt(_jeu.getNumber());
        if (!lines_.isValidIndex(index_)) {
            return 0L;
        }
        if (_jeu == GameEnum.PRESIDENT) {
            StringList line_ = StringUtil.splitChars(lines_.get(index_), JOIN_STACKS);
            if (line_.isValidIndex(_nbStacks - 1)) {
                return NumberUtil.parseLongZero(line_.get(_nbStacks - 1));
            }
            return 0;
        }
        return NumberUtil.parseLongZero(lines_.get(index_));
    }
    public static StringList retrieveLines(String _content) {
        StringList vl_=new StringList();
        boolean read_ = true;
        StringList lines_ = new StringList();
        if (_content != null) {
            lines_.addAllElts(StringUtil.splitChars(_content, LINE_RETURN));
        } else {
            read_ = false;
        }
        int total_ = GameEnum.allValid().size();
        if (lines_.size() < total_) {
            read_ = false;
        }
        if (read_) {
            for (int indice_ = IndexConstants.FIRST_INDEX; indice_<total_; indice_++) {
                vl_.add(lines_.get(indice_));
            }
            return vl_;
        }
        return buildDeals(RulesPresident.getNbMaxStacksPlayers());
    }

    public SoftParams getParametres() {
        return parametres;
    }

    public void setParametres(SoftParams _p) {
        this.parametres = _p;
    }

    public DisplayingBelote getDisplayingBelote() {
        return displayingBelote;
    }

    public void setDisplayingBelote(DisplayingBelote _d) {
        this.displayingBelote = _d;
    }

    public DisplayingPresident getDisplayingPresident() {
        return displayingPresident;
    }

    public void setDisplayingPresident(DisplayingPresident _d) {
        this.displayingPresident = _d;
    }

    public DisplayingTarot getDisplayingTarot() {
        return displayingTarot;
    }

    public void setDisplayingTarot(DisplayingTarot _d) {
        this.displayingTarot = _d;
    }

    public Nicknames getPseudosJoueurs() {
        return pseudosJoueurs;
    }

    public void setPseudosJoueurs(Nicknames _p) {
        this.pseudosJoueurs = _p;
    }

    public RulesBelote getReglesBelote() {
        return reglesBelote;
    }

    public void setReglesBelote(RulesBelote _r) {
        this.reglesBelote = _r;
    }

    public RulesPresident getReglesPresident() {
        return reglesPresident;
    }

    public void setReglesPresident(RulesPresident _r) {
        this.reglesPresident = _r;
    }

    public RulesTarot getReglesTarot() {
        return reglesTarot;
    }

    public void setReglesTarot(RulesTarot _r) {
        this.reglesTarot = _r;
    }
}
