package cards.facade;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;
import code.xml.util.ExtractFromFiles;
import cards.belote.enumerations.DealingBelote;
import cards.president.RulesPresident;
import cards.tarot.enumerations.DealingTarot;

@RwXml
public final class Nicknames {

    private static final String NICKNAMES = "cards.facade.Nicknames";
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
        StringMap<String> messages_ = ExtractFromFiles.getMessagesFromLocaleClass(RESSOURCES_CLASSES, _loc, NICKNAMES);
        pseudo = messages_.getVal(USER);
        String player_ = messages_.getVal(NICKNAME);
        int maxJoueurs_=DealingBelote.values()[CustList.FIRST_INDEX].getNombreJoueurs();
        for(DealingBelote r:DealingBelote.values()){
            if(maxJoueurs_<r.getNombreJoueurs()){
                maxJoueurs_=r.getNombreJoueurs();
            }
        }
        int nBots_ = maxJoueurs_;
        nBots_ --;
        for(byte b=CustList.FIRST_INDEX;b<nBots_;b++){
            pseudosBelote.add(StringList.simpleFormat(player_, b));
        }
        maxJoueurs_=DealingTarot.values()[CustList.FIRST_INDEX].getNombreJoueurs();
        for(DealingTarot r:DealingTarot.values()){
            if(maxJoueurs_<r.getNombreJoueurs()){
                maxJoueurs_=r.getNombreJoueurs();
            }
        }
        nBots_ = maxJoueurs_;
        nBots_ --;
        for(byte b=CustList.FIRST_INDEX;b<nBots_;b++){
            pseudosTarot.add(StringList.simpleFormat(player_, b));
        }
        maxJoueurs_=RulesPresident.getNbMaxPlayers();
        nBots_ = maxJoueurs_;
        nBots_ --;
        for(byte b=CustList.FIRST_INDEX;b<nBots_;b++){
            pseudosPresident.add(StringList.simpleFormat(player_, b));
        }
    }
    public Nicknames(Nicknames _pseudos){
        pseudo = _pseudos.pseudo;
        pseudosBelote = _pseudos.pseudosBelote;
        pseudosTarot = _pseudos.pseudosTarot;
        pseudosPresident = _pseudos.pseudosPresident;
    }

    public boolean isValidNicknames() {
        int maxJoueurs_=DealingBelote.values()[CustList.FIRST_INDEX].getNombreJoueurs();
        for(DealingBelote r:DealingBelote.values()){
            if(maxJoueurs_<r.getNombreJoueurs()){
                maxJoueurs_=r.getNombreJoueurs();
            }
        }
        int nBots_ = maxJoueurs_;
        nBots_ --;
        if (pseudosBelote.size() < nBots_) {
            return false;
        }
        maxJoueurs_=DealingTarot.values()[CustList.FIRST_INDEX].getNombreJoueurs();
        for(DealingTarot r:DealingTarot.values()){
            if(maxJoueurs_<r.getNombreJoueurs()){
                maxJoueurs_=r.getNombreJoueurs();
            }
        }
        nBots_ = maxJoueurs_;
        nBots_ --;
        if (pseudosTarot.size() < nBots_) {
            return false;
        }
        maxJoueurs_=RulesPresident.getNbMaxPlayers();
        nBots_ = maxJoueurs_;
        nBots_ --;
        if (pseudosPresident.size() < nBots_) {
            return false;
        }
        return true;
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

    public void sauvegarder(String _fichier){
        StreamTextFile.saveObject(_fichier, this);
    }
}
