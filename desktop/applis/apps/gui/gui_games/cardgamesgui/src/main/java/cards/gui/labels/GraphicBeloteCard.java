package cards.gui.labels;






import cards.belote.enumerations.CardBelote;
import cards.facade.enumerations.GameEnum;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;

public final class GraphicBeloteCard extends GraphicCard<CardBelote> {

//    private CardBelote card;
//    private final boolean fullCard;
//    private boolean peindreCarte=true;
//    private AbstractImage bufferedImage;
//    private TranslationsLg lg;

    public GraphicBeloteCard(AbstractProgramInfos _fact, TranslationsLg _lg, CardBelote _pc, boolean _fullCard) {
        super(GameEnum.BELOTE,_pc,_pc.getId(),_fullCard,_fact,_lg);
//        peindreCarte = true;
//        card=_pc;
//        int[][] file_ = _lg.getMaxiCards().getVal(StringUtil.concat(FileConst.RESOURCES_IMAGES,
//                StringUtil.concatNb(card.getId().getNo(), FileConst.TXT_EXT)));
////        int[][] file_ = BaseSixtyFourUtil.getImageByString(ResourceFiles.ressourceFichier(StringUtil.concat(FileConst.RESOURCES_IMAGES,StreamTextFile.SEPARATEUR,_lg,
////                StreamTextFile.SEPARATEUR,card.getImageFileName(FileConst.TXT_EXT))));
//        bufferedImage = ConverterGraphicBufferedImage.decodeToImage(_fact.getImageFactory(),file_);
    }

    public GraphicBeloteCard(TranslationsLg _lg, boolean _fullCard, AbsCompoFactory _compoFactory) {
        super(GameEnum.BELOTE,_fullCard,_compoFactory,_lg);
//        lg = _lg;
//        setHorizontalAlignment(GuiConstants.RIGHT);
//        setVerticalAlignment(GuiConstants.TOP);
//        fullCard=_fullCard;
//        peindreCarte = false;
    }

//    public void setPreferredSize(boolean _small) {
//        setPreferredSize(Carpet.getDimension(_small));
//    }

//    void setCarte(AbstractImageFactory _fact, TranslationsLg _lg, CardBelote _pc) {
//        setCarte(_fact,_lg,_pc,_pc.getId());
////        lg = _lg;
////        card=_pc;
////        peindreCarte=true;
////        int[][] file_ = _lg.getMaxiCards().getVal(StringUtil.concat(FileConst.RESOURCES_IMAGES,
////                StringUtil.concatNb(card.getId().getNo(), FileConst.TXT_EXT)));
//////        int[][] file_ = BaseSixtyFourUtil.getImageByString(ResourceFiles.ressourceFichier(StringUtil.concat(FileConst.RESOURCES_IMAGES,StreamTextFile.SEPARATEUR,_lg,
//////                StreamTextFile.SEPARATEUR,card.getImageFileName(FileConst.TXT_EXT))));
////        bufferedImage = ConverterGraphicBufferedImage.decodeToImage(_fact,file_);
//    }
////    public CardBelote getCard() {
////        return card;
////    }
//    public void setCarteEnJeu(AbstractImageFactory _fact, TranslationsLg _lg, CardBelote _carte) {
//        setCarteEnJeu(_fact,_lg,_carte,_carte.getId());
////        peindreCarte=true;
////        lg = _lg;
////        setCarte(_fact,_lg,  _carte);
//    }
//    public void setJeu(TranslationsLg _lg) {
//        lg = _lg;
//        peindreCarte=false;
//    }
//    boolean coupeHorizontal() {
//        return fullCard;
//    }
//
//    /**Methode importante dessinant les cartes des jeux de cartes face non cachee sauf pour certaines cartes du solitaire*/
//    @Override
//    public void paintComponent(AbstractImage _g2) {
//        if(!peindreCarte) {
//            _g2.setColor(GuiConstants.RED);
//            _g2.fillRect(0,0,getWidth()-1,getHeight()-1);
//            _g2.setColor(GuiConstants.BLACK);
//            _g2.drawRect(0,0,getWidth()-1,getHeight()-1);
//            _g2.setColor(GuiConstants.BLUE);
//            _g2.setFont(DEFAULT, GuiConstants.BOLD,20);
//            _g2.drawString(GameEnum.BELOTE.toString(lg),20,80);
//            return;
//        }
//        _g2.setColor(GuiConstants.WHITE);
//        _g2.fillRect(0,0,getWidth(),getHeight());
//        if(fullCard) {
//            //whole card
//            _g2.drawImage(bufferedImage, 0, 0);
//        } else {
//            _g2.drawImage(bufferedImage, -75, 0);
//        }
//        _g2.setColor(GuiConstants.BLACK);
//        _g2.drawRect(0,0,getWidth()-1,getHeight()-1);
//    }
}
