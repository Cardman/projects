package cards.gui.dialogs;




import cards.gui.WindowCards;
import cards.gui.dialogs.events.ListenerClickTree;
import cards.gui.dialogs.help.*;
import code.bean.nat.BeanNatCommonLgNamesInt;
import code.bean.nat.FixCharacterCaseConverter;
import code.bean.nat.NatNavigation;
import code.formathtml.render.MetaDocument;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.document.WindowPage;
import code.gui.events.ClosingChildFrameEvent;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class FrameGeneralHelp extends ChildFrame {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.framegeneralhelp";

//    private static final String TRUMP_SUIT = "trumpSuit";
//
//    private static final String NORMAL_SUIT = "normalSuit";
//
//    private static final String TRUMPS = "trumps";
//
//    private static final String ORDER_TRUMPS = "orderTrumps";
//
//    private static final String ORDER_NO_TRUMPS = "orderNoTrumps";

    private static final String SEARCH_LABEL = "searchLabel";

//    private static final String TYPE = "type";

    //    private static final String TOUTE_CARTE_BELOTE = "touteCarteBelote";
//    private static final String ORDRE_CARTES_BELOTE = "ordreCartesBelote";
//    private static final String TOUTE_CARTE_TAROT = "touteCarteTarot";
//    private static final String ORDRE_CARTES_TAROT = "ordreCartesTarot";
//    private static final String EMPTY_STRING = "";
    private StringMap<String> messages;

    private HelpIndexesTree elementsBis = new HelpIndexesTree();

    private NodeHelp racineBis;

    //private MainWindow window;

    private RenderedPage editor;

    private AbsTextField field;

    private AbsSplitPane separateur;
    private AbsPlainButton search;

    public FrameGeneralHelp(String _titre, WindowCards _fenetre) {
        super(_fenetre.getLanguageKey(),_fenetre);
        setAccessFile(DIALOG_ACCESS);
        setDialogIcon(_fenetre.getCommonFrame());
        setTitle(_titre);
//        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new ClosingChildFrameEvent(this));
        //window = _fenetre;
    }

    /**It is impossible to know by advance if there is an infinite loop in a custom java code =&gt; Give up on tests about dynamic initialize html pages*/
    public static void initialize(NatNavigation _nav, MetaDocument _metaDoc, RenderedPage _cur) {
        coreInfos(_cur, _nav);
        GuiBaseUtil.invokeLater(new WindowPage(_metaDoc, _cur.getScroll(), _cur), _cur.getGene());
    }

    /**It is impossible to know by advance if there is an infinite loop in a custom java code =&gt; Give up on tests about dynamic initialize html pages*/
    public static void initialize(PreparedAnalyzedCards _stds, RenderedPage _cur) {
        NatNavigation n_ = _stds.getNavigation();
        coreInfos(_cur, n_);
        ((BeanNatCommonLgNamesInt) _stds.getBeanNatLgNames()).initializeRendSessionDoc(n_);
        _cur.setupText();
    }
    public static RenderedPage initialize(PreparedAnalyzedCards _stds, AbstractProgramInfos _pr) {
        AbsScrollPane ascenseur_=_pr.getCompoFactory().newAbsScrollPane();
        RenderedPage r_ = new RenderedPage(ascenseur_, _pr,new FixCharacterCaseConverter());
        NatNavigation n_ = _stds.getNavigation();
        coreInfos(r_, n_);
        ((BeanNatCommonLgNamesInt) _stds.getBeanNatLgNames()).initializeRendSessionDoc(n_);
        r_.setupText();
        return r_;
    }

    public static void coreInfos(RenderedPage _cur, NatNavigation _n) {
        _cur.setNavCore(_n.getBean());
        _cur.setKeys(_n.getSession().getRendKeyWords());
    }
    @Override
    public void closeWindow() {
        setVisible(false);
//        editor.clearSession();
    }

//    @Override
//    public void dispose() {
//        super.dispose();
//        window.clearHelpDialogs();
//        window = null;
//    }

    public void initialize(WindowCards _w) {
        messages = WindowCards.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _w.getLanguageKey(), getAccessFile());
        String lg_ = _w.getLanguageKey();
        elementsBis = _w.getHelpInitializerTask().getTrees().getVal(lg_);
        setFocusable(true);
        setFocusableWindowState(true);
        CustList<HelpIndexes> cles_ = new CustList<HelpIndexes>(
                elementsBis.getKeys());
        // Non null pour les valeurs
        cles_.sortElts(new ComparatorListSizeElement());
        racineBis = new NodeHelp(elementsBis.getVal(cles_.first()));
        AbstractMutableTreeNode root_ = _w.getCompoFactory().newMutableTreeNode(
                racineBis.nom());
        boolean wasNull_ = editor == null;
        AbsPanel container_;
        if (wasNull_) {
            container_ = _w.getCompoFactory().newPageBox();
        } else {
            container_ = getPane();
            container_.removeAll();
        }
        for (HelpIndexes chemin_ : cles_) {
            CustList<Integer> cheminSansNoeud_ = chemin_.left(
                    chemin_.getLastIndex());
            NodeHelp noeudLoc_;
            AbstractMutableTreeNode noeudLocGraphique_;
            noeudLoc_ = racineBis.element(cheminSansNoeud_);
            if (cheminSansNoeud_.isEmpty()) {
                noeudLocGraphique_ = root_;
            } else {
                noeudLocGraphique_ = (AbstractMutableTreeNode) root_
                        .getChildAt(cheminSansNoeud_.first());
                int lengthPath_ = cheminSansNoeud_.size();
                for (int indice_ = IndexConstants.SECOND_INDEX; indice_ < lengthPath_; indice_++) {
                    noeudLocGraphique_ = (AbstractMutableTreeNode) noeudLocGraphique_
                            .getChildAt(cheminSansNoeud_.get(indice_));
                }
            }
            ElementHelp elementLoc_ = elementsBis.getVal(chemin_);
            NodeHelp nouveauNoeud_ = new NodeHelp(elementLoc_);
            noeudLoc_.ajouterInfo(nouveauNoeud_);
            noeudLocGraphique_.add(
                    elementLoc_.nom());
        }
        if (wasNull_) {
            editor = new RenderedPage(_w.getCompoFactory().newAbsScrollPane(), _w.getFrames(),new FixCharacterCaseConverter());
        }
        AbsTreeGui arbre_ = _w.getCompoFactory().newTreeGui(root_);
        arbre_.setRootVisible(false);
        arbre_.addTreeSelectionListener(new ListenerClickTree(racineBis, editor, arbre_));
        initialize(racineBis.getElementLocal().getNavigation(),racineBis.getElementLocal().getMetaDocument(), editor);
        if (field == null) {
            field = _w.getCompoFactory().newTextField(20);
            search = _w.getCompoFactory().newPlainButton(messages.getVal(SEARCH_LABEL));
            editor.addFinder(field,search);
        }
//        search.setTextAndSize(messages.getVal(SEARCH_LABEL));
        if (wasNull_) {
            separateur = _w.getCompoFactory().newHorizontalSplitPane(
                    _w.getCompoFactory().newAbsScrollPane(arbre_), editor.getScroll());
            separateur.setPreferredSize(new MetaDimension(600, 550));
            separateur.setDividerLocation(150);
            container_.add(separateur);
            container_.add(field);
            container_.add(search);
            setContentPane(container_);
        } else {
            separateur.setLeftComponent(_w.getCompoFactory().newAbsScrollPane(arbre_));
            container_.add(separateur);
            container_.add(field);
            container_.add(search);
        }
        separateur.revalidate();
        pack();
        setVisible(true);
    }
}
