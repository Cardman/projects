package cards.facade;
import cards.facade.sml.DocumentWriterCardsUnionUtil;
import cards.president.RulesPresident;
import cards.tarot.enumerations.DealingTarot;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.stream.StreamTextFile;
import code.stream.core.TechStreams;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;


public final class Nicknames {

    public static final String USER_DEF = "_";
    public static final String NICKNAME_DEF = "_{0}";
    public static final String NICKNAME = "0";
    public static final String USER = "1";
    private String pseudo;
    private StringList pseudosBelote=new StringList();
    private StringList pseudosTarot=new StringList();
    private StringList pseudosPresident=new StringList();

    public Nicknames(){
        this(USER_DEF,NICKNAME_DEF);
    }

    public Nicknames(TranslationsLg _loc){
        this(Games.getAppliTr(_loc).getMapping().getVal(Games.NICK_NAMES).getMapping());
    }
    public Nicknames(StringMap<String> _messages){
        this(_messages.getVal(USER),_messages.getVal(NICKNAME));
    }
    public Nicknames(String _user, String _nickname){
//        StringMap<String> messages_ = ExtractFromFiles.getMessagesFromLocaleClass(RESSOURCES_CLASSES, _loc, NICKNAMES);
        pseudo = StringUtil.nullToEmpty(_user);
        String player_ = StringUtil.nullToEmpty(_nickname);
        int nBots_ = 4;
        nBots_--;
        for(byte b = IndexConstants.FIRST_INDEX; b<nBots_; b++){
            pseudosBelote.add(StringUtil.simpleNumberFormat(player_, b));
        }
        int maxJoueurs_ = DealingTarot.DEAL_2_VS_4_WITHOUT_CALL.getId().getNombreJoueurs();
        nBots_ = maxJoueurs_;
        nBots_--;
        for(byte b = IndexConstants.FIRST_INDEX; b<nBots_; b++){
            pseudosTarot.add(StringUtil.simpleNumberFormat(player_, b));
        }
        maxJoueurs_=RulesPresident.getNbMaxPlayers();
        nBots_ = maxJoueurs_;
        nBots_--;
        for(byte b = IndexConstants.FIRST_INDEX; b<nBots_; b++){
            pseudosPresident.add(StringUtil.simpleNumberFormat(player_, b));
        }
    }
    public Nicknames(Nicknames _pseudos){
        pseudo = _pseudos.pseudo;
        pseudosBelote = _pseudos.pseudosBelote;
        pseudosTarot = _pseudos.pseudosTarot;
        pseudosPresident = _pseudos.pseudosPresident;
    }
    public static TranslationsFile en() {
        TranslationsFile e_ = new TranslationsFile();
        e_.add(USER,"Your_nickname");
        e_.add(NICKNAME,"Player {0}");
        return e_;
    }
    public static TranslationsFile fr() {
        TranslationsFile f_ = new TranslationsFile();
        f_.add(USER,"Votre_pseudo");
        f_.add(NICKNAME,"Joueur {0}");
        return f_;
    }
    public boolean isValidNicknames() {
        int nBots_ = 4;
        nBots_--;
        if (pseudosBelote.size() < nBots_) {
            return false;
        }
        int maxJoueurs_ = DealingTarot.DEAL_2_VS_4_WITHOUT_CALL.getId().getNombreJoueurs();
        nBots_ = maxJoueurs_;
        nBots_--;
        if (pseudosTarot.size() < nBots_) {
            return false;
        }
        maxJoueurs_=RulesPresident.getNbMaxPlayers();
        nBots_ = maxJoueurs_;
        nBots_--;
        return pseudosPresident.size() >= nBots_;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String _pseudo) {
        pseudo = _pseudo;
    }

    public StringList getPseudosBelote() {
        return pseudosBelote;
    }

    public void setPseudosBelote(StringList _pseudosBelote) {
        pseudosBelote = _pseudosBelote;
    }

    public StringList getPseudosTarot() {
        return pseudosTarot;
    }

    public void setPseudosTarot(StringList _pseudosTarot) {
        pseudosTarot = _pseudosTarot;
    }

    public StringList getPseudosPresident() {
        return pseudosPresident;
    }

    public void setPseudosPresident(StringList _pseudosPresident) {
        pseudosPresident = _pseudosPresident;
    }

    public void sauvegarder(String _fichier, TechStreams _tech){
        StreamTextFile.saveTextFile(_fichier, DocumentWriterCardsUnionUtil.setNicknames(this),_tech);
    }
}
