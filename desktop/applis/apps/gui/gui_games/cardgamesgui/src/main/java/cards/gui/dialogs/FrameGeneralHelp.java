package cards.gui.dialogs;




import cards.facade.MessagesCardGames;
import cards.gui.WindowCards;
import cards.gui.animations.PreparedRenderPagesCards;
import cards.gui.dialogs.events.ListenerClickTree;
import cards.gui.dialogs.help.*;
import cards.main.CardNatLgNamesNavigation;
import code.bean.nat.FixCharacterCaseConverter;
import code.bean.nat.NatNavigation;
import code.formathtml.render.MetaDocument;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.document.WindowPage;
import code.gui.events.AbsActionListenerAct;
import code.gui.events.ClosingChildFrameEvent;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.confs.HelpScriptConfPages;
import code.scripts.messages.cards.MessagesGuiCards;
import code.scripts.pages.cards.HelpCards;
import code.sml.util.*;
import code.util.*;
import code.util.core.IndexConstants;

public final class FrameGeneralHelp extends GroupFrame implements AbsChildFrame {
//    public static final String PORT_INI = "cards_port.ini";
//    public static final String COORDS="cards.coords";
//    public static final String FOLDER_MESSAGES_GUI = "resources_cards/gui/messages";
    public static final String RESOURCES_HTML_FILES_RULES_BELOTE = "0";
    public static final String RESOURCES_HTML_FILES_RULES_PRESIDENT = "1";
    public static final String RESOURCES_HTML_FILES_RULES_TAROT = "2";
    public static final String RESOURCES_HTML_FILES_RESULTS_BELOTE = "3";
    public static final String RESOURCES_HTML_FILES_RESULTS_PRESIDENT = "4";
    public static final String RESOURCES_HTML_FILES_RESULTS_TAROT = "5";
    public static final String RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE = "6";
    public static final String RESOURCES_HTML_FILES_DETAILS_RESULTS_PRESIDENT = "7";
    public static final String RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT = "8";
//    private static final String DIALOG_ACCESS = "cards.gui.dialogs.framegeneralhelp";

//    private static final String TRUMP_SUIT = "trumpSuit";
//
//    private static final String NORMAL_SUIT = "normalSuit";
//
//    private static final String TRUMPS = "trumps";
//
//    private static final String ORDER_TRUMPS = "orderTrumps";
//
//    private static final String ORDER_NO_TRUMPS = "orderNoTrumps";

//    private static final String SEARCH_LABEL = "searchLabel";
    private final AbsScrollPane scrollPaneTree;

//    private static final String TYPE = "type";

    //    private static final String TOUTE_CARTE_BELOTE = "touteCarteBelote";
//    private static final String ORDRE_CARTES_BELOTE = "ordreCartesBelote";
//    private static final String TOUTE_CARTE_TAROT = "touteCarteTarot";
//    private static final String ORDRE_CARTES_TAROT = "ordreCartesTarot";
//    private static final String EMPTY_STRING = "";
//    private StringMap<String> messages;

    private HelpIndexesTree elementsBis = new HelpIndexesTree();

    private NodeHelp racineBis;

    //private MainWindow window;

    private final RenderedPage editor;

    private final AbsTextField field;

    private final AbsSplitPane separateur;
    private final AbsButton search;
    private final EnabledMenu menuItem;
    private AbsTreeGui arbre;

    public FrameGeneralHelp(WindowCards _fenetre, EnabledMenu _menu) {
        super(_fenetre.getFrames());
        editor = new RenderedPage(_fenetre.getCompoFactory().newAbsScrollPane(), _fenetre.getFrames(),new FixCharacterCaseConverter(), _fenetre.getGuardRender());
        field = _fenetre.getCompoFactory().newTextField(20);
        search = _fenetre.getCompoFactory().newPlainButton();
        editor.addFinder(field,search);
        AbsPanel container_ = _fenetre.getCompoFactory().newPageBox();
        scrollPaneTree = _fenetre.getCompoFactory().newAbsScrollPane();
        separateur = _fenetre.getCompoFactory().newHorizontalSplitPane(
                scrollPaneTree, editor.getScroll());
        separateur.setPreferredSize(new MetaDimension(600, 550));
        separateur.setDividerLocation(150);
        container_.add(separateur);
        container_.add(field);
        container_.add(search);
        setContentPane(container_);
        setDialogIcon(_fenetre.getCommonFrame());
        setTitle("");
//        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        menuItem = _menu;
        addWindowListener(new ClosingChildFrameEvent(this));
        //window = _fenetre;
    }
    public void setDialogIcon(AbsCommonFrame _group) {
        setIconImage(_group.getImageIconFrame());
        setImageIconFrame(_group.getImageIconFrame());
    }
    /**It is impossible to know by advance if there is an infinite loop in a custom java code =&gt; Give up on tests about dynamic initialize html pages*/
    public static void initialize(NatNavigation _nav, MetaDocument _metaDoc, RenderedPage _cur) {
        coreInfos(_cur, _nav);
        _cur.getGene().getCompoFactory().invokeNow(new WindowPage(_metaDoc, _cur.getScroll(), _cur));
    }

    /**It is impossible to know by advance if there is an infinite loop in a custom java code =&gt; Give up on tests about dynamic initialize html pages*/
    public static void initialize(CardNatLgNamesNavigation _stds, RenderedPage _cur) {
        NatNavigation n_ = _stds.getNavigation();
        n_.setLanguage(_cur.getGene().getLanguage());
        coreInfos(_cur, n_);
        _stds.getBeanNatLgNames().initializeRendSessionDoc(n_);
        _cur.setupText();
    }
    public static RenderedPage initialize(CardNatLgNamesNavigation _stds, AbstractProgramInfos _pr, AbsActionListenerAct _guard) {
        AbsScrollPane ascenseur_=_pr.getCompoFactory().newAbsScrollPane();
        RenderedPage r_ = new RenderedPage(ascenseur_, _pr,new FixCharacterCaseConverter(), _guard);
        initialize(_stds,r_);
        return r_;
    }

    public static void coreInfos(RenderedPage _cur, NatNavigation _n) {
        _cur.setNavCore(_n.getBean());
        _cur.setKeys(_n.getSession().getRendKeyWords());
    }
    @Override
    public void closeWindow() {
        setVisible(false);
        menuItem.setEnabled(true);
//        editor.clearSession();
    }

//    @Override
//    public void dispose() {
//        super.dispose();
//        window.clearHelpDialogs();
//        window = null;
//    }

    public void initialize(WindowCards _w) {
        TranslationsLg lg_ = _w.getFrames().currentLg();
        elementsBis = _w.getHelpInitializerTask().attendreResultat();
        setFocusable(true);
        setFocusableWindowState(true);
        CustList<HelpIndexes> cles_ = new CustList<HelpIndexes>(
                elementsBis.getKeys());
        // Non null pour les valeurs
        cles_.sortElts(new ComparatorListSizeElement());
        racineBis = new NodeHelp(elementsBis.getVal(cles_.first()));
        AbstractMutableTreeNodeCore<String> root_ = _w.getCompoFactory().newMutableTreeNode(
                lg_.getMapping().getVal(HelpCards.APP_BEAN).getMapping().getVal(HelpScriptConfPages.TREE_FILE).getMapping().getVal(racineBis.nom()));
//        boolean wasNull_ = editor == null;
//        AbsPanel container_;
//        if (wasNull_) {
//            container_ = _w.getCompoFactory().newPageBox();
//        } else {
//            container_ = getPane();
//            container_.removeAll();
//        }
        for (HelpIndexes chemin_ : cles_) {
            CustList<Integer> cheminSansNoeud_ = chemin_.left(
                    chemin_.getLastIndex());
            NodeHelp noeudLoc_;
            AbstractMutableTreeNodeCore<String> noeudLocGraphique_;
            noeudLoc_ = racineBis.element(cheminSansNoeud_);
            if (cheminSansNoeud_.isEmpty()) {
                noeudLocGraphique_ = root_;
            } else {
                noeudLocGraphique_ = root_
                        .getChildAt(cheminSansNoeud_.first());
                int lengthPath_ = cheminSansNoeud_.size();
                for (int indice_ = IndexConstants.SECOND_INDEX; indice_ < lengthPath_; indice_++) {
                    noeudLocGraphique_ = noeudLocGraphique_
                            .getChildAt(cheminSansNoeud_.get(indice_));
                }
            }
            ElementHelp elementLoc_ = getElementsBis().getVal(chemin_);
            NodeHelp nouveauNoeud_ = new NodeHelp(elementLoc_);
            noeudLoc_.ajouterInfo(nouveauNoeud_);
            noeudLocGraphique_.add(getCompoFactory().newMutableTreeNode(
                    lg_.getMapping().getVal(HelpCards.APP_BEAN).getMapping().getVal(HelpScriptConfPages.TREE_FILE).getMapping().getVal(elementLoc_.nom())));
        }
//        if (wasNull_) {
//            editor = new RenderedPage(_w.getCompoFactory().newAbsScrollPane(), _w.getFrames(),new FixCharacterCaseConverter());
//        }
        AbsTreeGui arbre_ = _w.getCompoFactory().newTreeGui(root_);
        arbre_.setRootVisible(false);
        arbre_.addTreeSelectionListener(new ListenerClickTree(racineBis, editor, arbre_));
        arbre = arbre_;
        String concat_ = getRacineBis().getElementLocal().chemin();
//        StringMap<TranslationsAppli> builtMs_ = HelpCards.ms();
//        NavigationCore.adjustMap(builtMs_);
        PreparedRenderPagesCards prep_ = new PreparedRenderPagesCards(editor.getGene().currentLg().getMapping().getVal(HelpCards.APP_BEAN), racineBis.getElementLocal().cf().getVal(concat_), racineBis.getElementLocal().ct().getVal(concat_), editor.getGene().currentLg().getMaxiCards(), racineBis.getElementLocal().built().getVal(concat_));
        prep_.run();
        initialize(prep_.getNavigation(),prep_.getMetaDocument(), editor);
//        if (field == null) {
//            field = _w.getCompoFactory().newTextField(20);
//            search = _w.getCompoFactory().newPlainButton(messages.getVal(SEARCH_LABEL));
//            editor.addFinder(field,search);
//        }
        search.setText(MessagesCardGames.getDialogHelpTr(MessagesCardGames.getAppliTr(lg_)).getMapping().getVal(MessagesGuiCards.DIAL_HELP_SEARCH_LABEL));
        scrollPaneTree.setViewportView(arbre_);
//        if (wasNull_) {
//            separateur = _w.getCompoFactory().newHorizontalSplitPane(
//                    _w.getCompoFactory().newAbsScrollPane(arbre_), editor.getScroll());
//            separateur.setPreferredSize(new MetaDimension(600, 550));
//            separateur.setDividerLocation(150);
//            container_.add(separateur);
//            container_.add(field);
//            container_.add(search);
//            setContentPane(container_);
//        } else {
//
//            separateur.setLeftComponent(_w.getCompoFactory().newAbsScrollPane(arbre_));
//            container_.add(separateur);
//            container_.add(field);
//            container_.add(search);
//        }
        separateur.revalidate();
        pack();
        setVisible(true);
        menuItem.setEnabled(false);
    }

    public AbsButton getSearch() {
        return search;
    }

    public AbsTextField getField() {
        return field;
    }

    public NodeHelp getRacineBis() {
        return racineBis;
    }

    public HelpIndexesTree getElementsBis() {
        return elementsBis;
    }

    public AbsTreeGui getArbre() {
        return arbre;
    }
}
