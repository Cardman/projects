package cards.facade;

import cards.belote.DisplayingBelote;
import cards.belote.HandBelote;
import cards.belote.RulesBelote;
import cards.belote.sml.DocumentReaderBeloteUtil;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.consts.MixCardsChoice;
import cards.facade.enumerations.GameEnum;
import cards.facade.sml.DocumentReaderCardsUnionUtil;
import cards.president.DisplayingPresident;
import cards.president.HandPresident;
import cards.president.RulesPresident;
import cards.president.sml.DocumentReaderPresidentUtil;
import cards.president.sml.DocumentWriterPresidentUtil;
import cards.tarot.DisplayingTarot;
import cards.tarot.HandTarot;
import cards.tarot.RulesTarot;
import cards.tarot.sml.DocumentReaderTarotUtil;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.AbstractFile;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

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
            StringList dealsNumbers_ = new StringList();
            int nbGames_ = GameEnum.all().size();
            for (int i = IndexConstants.FIRST_INDEX; i<nbGames_; i++) {
                dealsNumbers_.add("0");
            }
            StreamTextFile.saveTextFile(f.getAbsolutePath(), StringUtil.join(dealsNumbers_, LINE_RETURN), _list.getStreams());
        }
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

        pseudosJoueurs = DocumentReaderCardsUnionUtil.getNicknames(StreamTextFile.contentsOfFile(StringUtil.concat(_tempFolder,PLAYERS),_list.getFileCoreStream(),_list.getStreams()));
        if (!pseudosJoueurs.isValidNicknames()) {
            pseudosJoueurs = new Nicknames(_lg);
            pseudosJoueurs.sauvegarder(StringUtil.concat(_tempFolder,PLAYERS),_list.getStreams());
        }
    }

    public void changerNombreDePartiesEnQuittant(String _tempFolder, AbstractProgramInfos _inst) {
        String fileName_ = stack(_tempFolder);
        String content_ = StreamTextFile.contentsOfFile(fileName_,_inst.getFileCoreStream(),_inst.getStreams());
        StringList vl_ = retrieveLines(content_);
        //Si l'action de battre les cartes est faite a chaque lancement
        //de logiciel alors le nombre de parties est remis a zero lors
        //d'une fermeture de logiciel

        if(reglesPresident.getCommon().getMixedCards() == MixCardsChoice.EACH_LAUNCHING) {
            vl_.set(NumberUtil.parseInt(GameEnum.PRESIDENT.getNumber()), "0");
        }
        if(reglesBelote.getCommon().getMixedCards() ==MixCardsChoice.EACH_LAUNCHING) {
            vl_.set(NumberUtil.parseInt(GameEnum.BELOTE.getNumber()), "0");
        }
        if(reglesTarot.getCommon().getMixedCards() ==MixCardsChoice.EACH_LAUNCHING) {
            vl_.set(NumberUtil.parseInt(GameEnum.TAROT.getNumber()), "0");
        }
        StreamTextFile.saveTextFile(fileName_, StringUtil.join(vl_, LINE_RETURN),_inst.getStreams());
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
        int total_ = GameEnum.all().size()-1;
        if (lines_.size() < total_) {
            read_ = false;
        }
        if (read_) {
            for (int indice_ = IndexConstants.FIRST_INDEX; indice_<total_; indice_++) {
                vl_.add(lines_.get(indice_));
            }
        } else {
            vl_=new StringList();
            for (int indice_ = IndexConstants.FIRST_INDEX; indice_ < total_; indice_++) {
                vl_.add("0");
            }
        }
        return vl_;
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
