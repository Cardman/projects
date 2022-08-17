package cards.facade;
import cards.belote.enumerations.DealingBelote;
import cards.facade.sml.DocumentWriterCardsUnionUtil;
import cards.president.RulesPresident;
import cards.tarot.enumerations.DealingTarot;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.StreamTextFile;
import code.stream.core.TechStreams;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;


public final class Nicknames {

    private static final String NICKNAMES = "cards.facade.nicknames";
    private static final String RESSOURCES_CLASSES = "resources_cards/classes";
    private static final String NICKNAME = "nickname";
    private static final String USER = "user";
    private String pseudo;
    private StringList pseudosBelote=new StringList();
    private StringList pseudosTarot=new StringList();
    private StringList pseudosPresident=new StringList();

    public Nicknames(){
    }

    public Nicknames(String _loc){
        StringMap<String> messages_ = ResourcesMessagesUtil.getMessagesFromContent(nicknames().getVal(_loc));
//        StringMap<String> messages_ = ExtractFromFiles.getMessagesFromLocaleClass(RESSOURCES_CLASSES, _loc, NICKNAMES);
        pseudo = messages_.getVal(USER);
        String player_ = messages_.getVal(NICKNAME);
        int maxJoueurs_= DealingBelote.values()[IndexConstants.FIRST_INDEX].getId().getNombreJoueurs();
        for(DealingBelote r:DealingBelote.values()){
            if(maxJoueurs_< r.getId().getNombreJoueurs()){
                maxJoueurs_= r.getId().getNombreJoueurs();
            }
        }
        int nBots_ = maxJoueurs_;
        nBots_--;
        for(byte b = IndexConstants.FIRST_INDEX; b<nBots_; b++){
            pseudosBelote.add(StringUtil.simpleNumberFormat(player_, b));
        }
        maxJoueurs_= DealingTarot.values()[IndexConstants.FIRST_INDEX].getId().getNombreJoueurs();
        for(DealingTarot r:DealingTarot.values()){
            if(maxJoueurs_< r.getId().getNombreJoueurs()){
                maxJoueurs_= r.getId().getNombreJoueurs();
            }
        }
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

    private static StringMap<String> nicknames() {
        StringMap<String> m = new StringMap<String>();
        m.addEntry("en","user=Your_nickname\nnickname=Player {0}");
        m.addEntry("fr","user=Votre_pseudo\nnickname=Joueur {0}");
        return m;
    }
    public boolean isValidNicknames() {
        int maxJoueurs_= DealingBelote.values()[IndexConstants.FIRST_INDEX].getId().getNombreJoueurs();
        for(DealingBelote r:DealingBelote.values()){
            if(maxJoueurs_< r.getId().getNombreJoueurs()){
                maxJoueurs_= r.getId().getNombreJoueurs();
            }
        }
        int nBots_ = maxJoueurs_;
        nBots_--;
        if (pseudosBelote.size() < nBots_) {
            return false;
        }
        maxJoueurs_= DealingTarot.values()[IndexConstants.FIRST_INDEX].getId().getNombreJoueurs();
        for(DealingTarot r:DealingTarot.values()){
            if(maxJoueurs_< r.getId().getNombreJoueurs()){
                maxJoueurs_= r.getId().getNombreJoueurs();
            }
        }
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
