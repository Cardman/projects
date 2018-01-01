package cards.gui.containers;
import java.awt.Container;
import java.awt.Rectangle;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import code.gui.Packable;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.EnumMap;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import cards.belote.DisplayingBelote;
import cards.belote.RulesBelote;
import cards.facade.Games;
import cards.facade.Nicknames;
import cards.facade.SoftParams;
import cards.facade.enumerations.GameEnum;
import cards.gui.MainWindow;
import cards.gui.dialogs.FileConst;
import cards.gui.panels.Carpet;
import cards.gui.panels.MiniCarpet;
import cards.main.LaunchingCards;
import cards.president.DisplayingPresident;
import cards.president.RulesPresident;
import cards.tarot.DisplayingTarot;
import cards.tarot.RulesTarot;
import cards.tarot.enumerations.ChoiceTarot;

public class ContainerGame implements Packable, Containable {

    public static final String INTRODUCTION_PTS = ":";

    public static final String EMPTY_STRING = "";
    protected static final String SPACE = " ";
    public static final String RETURN_LINE="\n";

    private static final char LINE_RETURN = '\n';
    private JPanel actionsHistory;
    private JPanel panneauBoutonsJeu;
    private JPanel panelHand;
    private MainWindow window;
    /**Parametres d'informations sur
    des pseudonymes*/
    private Nicknames pseudosJoueurs=new Nicknames(Constants.getLanguage());
    private RulesTarot reglesTarot=new RulesTarot();
    private RulesPresident reglesPresident=new RulesPresident();
    private RulesBelote reglesBelote=new RulesBelote();
    /**La partie actuellement jouee*/
    private Games par = new Games();
    private boolean carteEntree;
    private boolean carteSortie;
    private String raisonCourante=EMPTY_STRING;
    private boolean threadAnime;
    private boolean aJoueCarte;
    private volatile boolean pause;
    private JTextArea events;
    private MiniCarpet mini;
    /**Est vrai si et seulement si le jeu est en pause*/
    private volatile boolean passe;
    /**Parametres de lancement, de jouerie*/
    private SoftParams parametres=new SoftParams();
    private StringMap<String> messages = new StringMap<String>();
    private DisplayingBelote displayingBelote;
    private DisplayingPresident displayingPresident;
    private DisplayingTarot displayingTarot;
    private NumberMap<Byte,JLabel> handfuls = new NumberMap<Byte,JLabel>();
    private NumberMap<Byte,JPanel> declaredHandfuls = new NumberMap<Byte,JPanel>();
    private Carpet tapis = new Carpet();
    private boolean changerPileFin;
    public ContainerGame(MainWindow _window) {
        setWindow(_window);
        setParametres(_window.getParametresLogiciel());
        setReglesTarot(_window.getReglesTarot());
        setReglesPresident(_window.getReglesPresident());
        setReglesBelote(_window.getReglesBelote());
        setDisplayingBelote(_window.getDisplayingBelote());
        setDisplayingPresident(_window.getDisplayingPresident());
        setDisplayingTarot(_window.getDisplayingTarot());
        setPseudosJoueurs(_window.getPseudosJoueurs());

        setMessages(_window.getMessages());
    }

    @Override
    public boolean isSimple() {
        return true;
    }

    public void pause() {
        if(isPasse()) {
            pause=!pause;
        }
        while(pause) {
            if(!isPasse()) {
                pause=!pause;
            }
        }
    }

    protected static void changerNombreDeParties(GameEnum _game, long _nbGames) {
        String fileName_ = LaunchingCards.getTempFolderSl()+FileConst.DECK_FOLDER+StreamTextFile.SEPARATEUR+FileConst.DECK_FILE;
        String content_ = StreamTextFile.contentsOfFile(fileName_);
        Numbers<Long> vl_=new Numbers<Long>();
        boolean read_ = false;
        StringList lines_ = new StringList();
        try {
            lines_.addAllElts(StringList.splitChars(content_, LINE_RETURN));
        } catch (NullPointerException _0) {
            read_ = false;
        }
        int total_ = GameEnum.values().length;
        if (lines_.size() < total_) {
            read_ = false;
        }
        if (read_) {
            try {
                for (int indice_ = CustList.FIRST_INDEX;indice_<total_;indice_++) {
                    vl_.add(Long.parseLong(lines_.get(indice_)));
                }
            } catch (NumberFormatException _0) {
                read_ = false;
            }
        }
        if (!read_) {
            vl_=new Numbers<Long>();
            for (int indice_ = CustList.FIRST_INDEX; indice_ < total_; indice_++) {
                vl_.add((long)0);
            }
        }
        //Si l'action de battre les cartes est faite a chaque lancement
        //de logiciel alors le nombre de parties est remis a zero lors
        //d'une fermeture de logiciel
        vl_.set(_game.ordinal(), _nbGames + 1);
        StreamTextFile.saveTextFile(fileName_, vl_.join(LINE_RETURN));
    }

    public void ajouterTexteDansZone(String _texte) {
        getEvents().append(_texte);
        try {
            int endPosition_ = getEvents().getDocument().getLength();
            Rectangle bottom_ = getEvents().modelToView(endPosition_);
            getEvents().scrollRectToVisible(bottom_);
        } catch (BadLocationException _0) {
        }
    }
    public JMenuBar getJMenuBar() {
        return getWindow().getJMenuBar();
    }
    public void revalidate() {
        getWindow().revalidateFrame();
    }
    @Override
    public void pack() {
        getWindow().pack();
    }
    public MainWindow getOwner() {
        return getWindow();
    }
    protected Container getContentPane() {
        return getWindow().getContentPane();
    }
    public void setContentPane(Container _container) {
        getWindow().setContentPane(_container);
    }
    public void saveCurrentGame(String _file) {
        getPar().sauvegarderPartieEnCours(_file);
    }
    public boolean playingSingleGame() {
        return getPar().enCoursDePartie();
    }
    /**
    @param _fichier
    @throws Exception <html>
        en: if there is an error while loading or if the deal is not valid<br/>
        fr: s'il y a une erreur lors du chargement ou si la donne n'est pas valide</html>
    */
    protected void chargerPartie(String _fichier) {
    }
    public void conseil() {

    }
    protected static long chargerNombreDeParties(GameEnum _jeu) {
        try {
            String fileName_ = LaunchingCards.getTempFolderSl()+FileConst.DECK_FOLDER+StreamTextFile.SEPARATEUR+FileConst.DECK_FILE;
            String content_ = StreamTextFile.contentsOfFile(fileName_);
            StringList lines_ = StringList.splitChars(content_, LINE_RETURN);
            lines_.removeAllString(EMPTY_STRING);
            return Long.parseLong(lines_.get(_jeu.ordinal()));
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            return 0L;
        }
//        long nb_;
//        File fe_=new File(LaunchingCards.getTempFolderSl()+FileConst.DECK_FOLDER+StreamTextFile.SEPARATEUR+FileConst.DECK_FILE);
//        BufferedReader br_=new BufferedReader(new FileReader(fe_));
//        String chaine_=EMPTY_STRING;
//        int nbGames_ = GameEnum.values().length;
//        for(byte indice_=CustList.FIRST_INDEX;indice_<nbGames_;indice_++) {
//            chaine_=br_.readLine();
//            if(indice_==_jeu.ordinal()) {
//                break;
//            }
//        }
//        nb_=Long.parseLong(chaine_);
//        br_.close();
//
//        return nb_;
    }
    public void aideAuJeu() {}
    public void showTricksHands() {}
    public void showTeams() {}
    public void setNicknames(Nicknames _pseudosJoueurs) {
        setPseudosJoueurs(_pseudosJoueurs);
    }
    public void setDisplayingBelote(DisplayingBelote _displayingBelote) {
        displayingBelote = _displayingBelote;
    }
    public void setDisplayingPresident(DisplayingPresident _displayingPresident) {
        displayingPresident = _displayingPresident;
    }
    public void setDisplayingTarot(DisplayingTarot _displayingTarot) {
        displayingTarot = _displayingTarot;
    }
    public void setSettings(SoftParams _parametres) {
        setParametres(_parametres);
    }
    public void setRulesBelote(RulesBelote _reglesBelote) {
        setReglesBelote(_reglesBelote);
    }
    public void setRulesPresident(RulesPresident _reglesTarot) {
        setReglesPresident(_reglesTarot);
    }
    public void setRulesTarot(RulesTarot _reglesTarot) {
        setReglesTarot(_reglesTarot);
    }
    public void load() {

    }
    public void modify() {

    }
    public void finirParties() {
        getPar().finirParties();
    }
    public boolean isaJoueCarte() {
        return aJoueCarte;
    }
    public void setaJoueCarte(boolean _aJoueCarte) {
        aJoueCarte = _aJoueCarte;
    }
    public boolean isCarteEntree() {
        return carteEntree;
    }
    public void setCarteEntree(boolean _carteEntree) {
        carteEntree = _carteEntree;
    }
    public boolean isCarteSortie() {
        return carteSortie;
    }
    public void setCarteSortie(boolean _carteSortie) {
        carteSortie = _carteSortie;
    }
    public NumberMap<Byte,JPanel> getDeclaredHandfuls() {
        return declaredHandfuls;
    }
    public void setDeclaredHandfuls(NumberMap<Byte,JPanel> _declaredHandfuls) {
        declaredHandfuls = _declaredHandfuls;
    }
    public StringMap<String> getMessages() {
        return messages;
    }
    protected void setMessages(StringMap<String> _messages) {
        messages = _messages;
    }
    public DisplayingBelote getDisplayingBelote() {
        return displayingBelote;
    }
    public DisplayingTarot getDisplayingTarot() {
        return displayingTarot;
    }
    public DisplayingPresident getDisplayingPresident() {
        return displayingPresident;
    }
    public Carpet getTapis() {
        return tapis;
    }
    protected void setTapis(Carpet _tapis) {
        tapis = _tapis;
    }
    public NumberMap<Byte,JLabel> getHandfuls() {
        return handfuls;
    }
    public void setHandfuls(NumberMap<Byte,JLabel> _handfuls) {
        handfuls = _handfuls;
    }
    protected boolean isChangerPileFin() {
        return changerPileFin;
    }
    public void setChangerPileFin(boolean _changerPileFin) {
        changerPileFin = _changerPileFin;
    }
    public MiniCarpet getMini() {
        return mini;
    }
    public void setMini(MiniCarpet _mini) {
        mini = _mini;
    }
    public SoftParams getParametres() {
        return parametres;
    }
    protected void setParametres(SoftParams _parametres) {
        parametres = _parametres;
    }
    public JTextArea getEvents() {
        return events;
    }
    public void setEvents(JTextArea _events) {
        events = _events;
    }
    public boolean isPasse() {
        return passe;
    }
    public void setPasse(boolean _passe) {
        passe = _passe;
    }
    public String getRaisonCourante() {
        return raisonCourante;
    }
    public void setRaisonCourante(String _raisonCourante) {
        raisonCourante = _raisonCourante;
    }
    public boolean isThreadAnime() {
        return threadAnime;
    }
    public void setThreadAnime(boolean _threadAnime) {
        threadAnime = _threadAnime;
    }
    public Games getPar() {
        return par;
    }
    protected void setPar(Games _par) {
        par = _par;
    }
    protected RulesBelote getReglesBelote() {
        return reglesBelote;
    }
    protected void setReglesBelote(RulesBelote _reglesBelote) {
        reglesBelote = _reglesBelote;
    }
    protected RulesPresident getReglesPresident() {
        return reglesPresident;
    }
    protected void setReglesPresident(RulesPresident _reglesPresident) {
        reglesPresident = _reglesPresident;
    }
    protected RulesTarot getReglesTarot() {
        return reglesTarot;
    }
    protected void setReglesTarot(RulesTarot _reglesTarot) {
        reglesTarot = _reglesTarot;
    }
    public JPanel getPanelHand() {
        return panelHand;
    }
    public void setPanelHand(JPanel _panelHand) {
        panelHand = _panelHand;
    }

    public JPanel getActionsHistory() {
        return actionsHistory;
    }

    public void setActionsHistory(JPanel _actionsHistory) {
        actionsHistory = _actionsHistory;
    }

    public JPanel getPanneauBoutonsJeu() {
        return panneauBoutonsJeu;
    }
    public void setPanneauBoutonsJeu(JPanel _panneauBoutonsJeu) {
        panneauBoutonsJeu = _panneauBoutonsJeu;
    }
    public MainWindow getWindow() {
        return window;
    }
    protected void setWindow(MainWindow _window) {
        window = _window;
    }
    public Nicknames getPseudosJoueurs() {
        return pseudosJoueurs;
    }
    protected void setPseudosJoueurs(Nicknames _pseudosJoueurs) {
        pseudosJoueurs = _pseudosJoueurs;
    }

    public JMenu getFile() {
        return window.getFile();
    }

    public JMenuItem getLoad() {
        return window.getLoad();
    }

    public JMenuItem getSave() {
        return window.getSave();
    }

    public JMenuItem getChange() {
        return window.getChange();
    }

    public JMenuItem getExit() {
        return window.getExit();
    }

    public JMenu getDeal() {
        return window.getDeal();
    }

    public JMenuItem getConsulting() {
        return window.getConsulting();
    }

    public JCheckBoxMenuItem getPause() {
        return window.getPause();
    }

    public JMenuItem getHelpGame() {
        return window.getHelpGame();
    }

    public JMenuItem getTricksHands() {
        return window.getTricksHands();
    }

    public JMenuItem getTeams() {
        return window.getTeams();
    }

    public JMenu getEdit() {
        return window.getEdit();
    }

    public EnumMap<GameEnum,JMenuItem> getEditGames() {
        return window.getEditGames();
    }

    public JMenu getDemo() {
        return window.getDemo();
    }

    public EnumMap<GameEnum,JMenuItem> getDemoGames() {
        return window.getDemoGames();
    }

    public JMenu getTraining() {
        return window.getTraining();
    }

    public EnumMap<ChoiceTarot,JMenuItem> getTrainingTarot() {
        return window.getTrainingTarot();
    }

    public JMenuItem getMultiStop() {
        return window.getMultiStop();
    }

    public JMenu getParameters() {
        return window.getParameters();
    }

    public EnumMap<GameEnum,JMenuItem> getRulesGames() {
        return window.getRulesGames();
    }

    public JMenuItem getPlayers() {
        return window.getPlayers();
    }

    public JMenuItem getLaunching() {
        return window.getLaunching();
    }

    public JMenuItem getTiming() {
        return window.getTiming();
    }

    public JMenuItem getInteract() {
        return window.getInteract();
    }

    public JMenuItem getLanguage() {
        return window.getLanguage();
    }

    public JMenuItem getDisplaying() {
        return window.getDisplaying();
    }

    public EnumMap<GameEnum,JMenuItem> getDisplayingGames() {
        return window.getDisplayingGames();
    }

    public JMenu getHelp() {
        return window.getHelp();
    }

    public JMenuItem getGeneralHelp() {
        return window.getGeneralHelp();
    }

}

