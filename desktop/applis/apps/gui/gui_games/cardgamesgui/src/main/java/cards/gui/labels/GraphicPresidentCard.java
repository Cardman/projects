package cards.gui.labels;






import cards.facade.enumerations.GameEnum;
import cards.president.enumerations.CardPresident;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;

public final class GraphicPresidentCard extends GraphicCard<CardPresident> {

//    private CardPresident card;
//    private final boolean fullCard;
//    private boolean peindreCarte=true;
//    private AbstractImage bufferedImage;
//    private TranslationsLg lg;

    public GraphicPresidentCard(AbstractProgramInfos _fact, TranslationsLg _lg, CardPresident _pc, boolean _fullCard) {
        super(GameEnum.PRESIDENT,_pc,_pc.getId(),_fullCard,_fact,_lg);
//        peindreCarte=true;
//        card=_pc;
//        int[][] file_ = _lg.getMaxiCards().getVal(StringUtil.concat(FileConst.RESOURCES_IMAGES,
//                StringUtil.concatNb(card.getId().getNo(), FileConst.TXT_EXT)));
////        int[][] file_ = BaseSixtyFourUtil.getImageByString(ResourceFiles.ressourceFichier(StringUtil.concat(FileConst.RESOURCES_IMAGES,StreamTextFile.SEPARATEUR,_lg,
////                StreamTextFile.SEPARATEUR,card.getImageFileName(FileConst.TXT_EXT))));
//        bufferedImage = ConverterGraphicBufferedImage.decodeToImage(_fact.getImageFactory(),file_);
    }

    public GraphicPresidentCard(TranslationsLg _lg, boolean _fullCard, AbsCompoFactory _compoFactory) {
        super(GameEnum.PRESIDENT,_fullCard,_compoFactory,_lg);
//        super(_compoFactory);
//        lg = _lg;
//        peindreCarte=false;
//        setHorizontalAlignment(GuiConstants.RIGHT);
//        setVerticalAlignment(GuiConstants.TOP);
//        fullCard=_fullCard;
    }

//    public void setPreferredSize(boolean _small) {
//        setPreferredSize(Carpet.getDimension(_small));
//    }

//    void setCarte(AbstractImageFactory _fact, TranslationsLg _lg, CardPresident _pc) {
//        card=_pc;
//        lg=_lg;
//        peindreCarte=true;
//        int[][] file_ = _lg.getMaxiCards().getVal(StringUtil.concat(FileConst.RESOURCES_IMAGES,
//                StringUtil.concatNb(card.getId().getNo(), FileConst.TXT_EXT)));
////        int[][] file_ = BaseSixtyFourUtil.getImageByString(ResourceFiles.ressourceFichier(StringUtil.concat(FileConst.RESOURCES_IMAGES,StreamTextFile.SEPARATEUR,_lg,
////                StreamTextFile.SEPARATEUR,card.getImageFileName(FileConst.TXT_EXT))));
//        bufferedImage = ConverterGraphicBufferedImage.decodeToImage(_fact,file_);
//    }

//    public CardPresident getCard() {
//        return card;
//    }

//    public void setCarteEnJeu(AbstractImageFactory _fact, TranslationsLg _lg, CardPresident _carte) {
//        setCarteEnJeu(_fact,_lg,_carte,_carte.getId());
////        peindreCarte=true;
////        lg = _lg;
////        setCarte(_fact,_lg, _carte);
//    }
//    public void setJeu(TranslationsLg _lg) {
//        lg = _lg;
//        peindreCarte=false;
//    }

//    boolean coupeHorizontal() {
//        return fullCard;
//    }

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
//            _g2.drawString(GameEnum.PRESIDENT.toString(lg),20,80);
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
