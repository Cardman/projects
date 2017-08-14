package aiki.images;
//import formathtml.images.ConverterBufferedImageIo;

public final class StreamBufferedImage {

    private static final String ZIPPED_DATA_FILE = "zippeddata.zip";

    private static final String EXTRACTED_FOLDER = "extracted";

    private StreamBufferedImage() {
    }

//    public static void importImage(DataBase _dataBase,String _fileName) {
//        _dataBase.addImage(_fileName, ConverterBufferedImageIo.toString(_fileName));
//    }
//
//    public static void importPersonImage(DataBase _dataBase,String _fileName) {
//        _dataBase.addPerson(_fileName, ConverterBufferedImageIo.toString(_fileName));
//    }
//
//    public static void importBackHero(DataBase _dataBase,String _fileName,EnvironmentType _env, Sex _sex) {
//        _dataBase.addBackHero(_env, _sex, ConverterBufferedImageIo.toString(_fileName));
//    }
//
//    public static void importFrontHero(DataBase _dataBase,String _fileName,EnvironmentType _env, Sex _sex) {
//        _dataBase.addFrontHero(_env, _sex, ConverterBufferedImageIo.toString(_fileName));
//    }
//
//    public static void importOverworldHero(DataBase _dataBase,String _fileName,EnvironmentType _env, Direction _dir, Sex _sex) {
//        _dataBase.addOverworldHero(_env, _dir, _sex, ConverterBufferedImageIo.toString(_fileName));
//    }
//
//    public static void importTrainerImage(DataBase _dataBase,String _fileName) {
//        _dataBase.addTrainerImage(_fileName, ConverterBufferedImageIo.toString(_fileName));
//    }
//
//    public static void importMaxiFrontImage(DataBase _dataBase,String _name,String _fileName) {
//        _dataBase.addFrontImagePk(_name, ConverterBufferedImageIo.toString(_fileName));
//    }
//
//    public static void importMaxiBackImage(DataBase _dataBase,String _name,String _fileName) {
//        _dataBase.addBackImagePk(_name, ConverterBufferedImageIo.toString(_fileName));
//    }
//
//    public static void importMiniPkImage(DataBase _dataBase,String _name,String _fileName) {
//        _dataBase.addMiniImagePk(_name, ConverterBufferedImageIo.toString(_fileName));
//    }
//
//    public static void importObjectImage(DataBase _dataBase,String _name,String _fileName) {
//        _dataBase.addImageObjects(_name, ConverterBufferedImageIo.toString(_fileName));
//    }
//
//    public static void saveRom(String _folderName, DataBase _dataBase) {
//        new File(_folderName).mkdirs();
//        _dataBase.save(_folderName+DataBase.SEPARATOR_FILES+ZIPPED_DATA_FILE);
//        saveRom(_folderName+DataBase.SEPARATOR_FILES+EXTRACTED_FOLDER, _dataBase, true, true, true, true);
//    }
//
//    private static void saveRom(String _folderName, DataBase _dataBase,
//            boolean _indentXml,
//            boolean _savePngImages,
//            boolean _saveTxtImages,
//            boolean _extract) {
//        boolean indenting_ = XmlParser.isIndentXmlWhileWriting();
//        XmlParser.setIndentXmlWhileWriting(_indentXml);
//        StringMap<String> files_ = _dataBase.getTextFiles(_saveTxtImages);
//        if (_extract) {
//            for (String f: files_.getKeys()) {
//                String fileName_ = _folderName+DataBase.SEPARATOR_FILES+f;
//                File folder_ = new File(fileName_.substring(0, fileName_.lastIndexOf(DataBase.SEPARATOR_FILES)));
//                if (!folder_.exists()) {
//                    folder_.mkdirs();
//                }
//                if (new File(fileName_).isDirectory()) {
//                    continue;
//                }
//                StreamTextFile.saveTextFile(fileName_, files_.getVal(f));
//            }
//            XmlParser.setIndentXmlWhileWriting(indenting_);
//            if (!_savePngImages) {
//                return;
//            }
//            try {
//                String fileName_ = _folderName+DataBase.SEPARATOR_FILES;
//                fileName_ += DataBase.IMAGE_TM_HM_FILES + DataBase.IMG_FILES_RES_EXT;
//                ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getImageTmHm()), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//            } catch (RuntimeException _0) {
//                _0.printStackTrace();
//            } catch (IOException _0) {
//                _0.printStackTrace();
//            }
//            try {
//                String fileName_ = _folderName+DataBase.SEPARATOR_FILES;
//                fileName_ += DataBase.ANIM_ABSORB + DataBase.IMG_FILES_RES_EXT;
//                ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getAnimAbsorb()), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//            } catch (RuntimeException _0) {
//                _0.printStackTrace();
//            } catch (IOException _0) {
//                _0.printStackTrace();
//            }
//            try {
//                String fileName_ = _folderName+DataBase.SEPARATOR_FILES;
//                fileName_ += DataBase.IMAGE_STORAGE_FILES + DataBase.IMG_FILES_RES_EXT;
//                ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getStorage()), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//            } catch (RuntimeException _0) {
//                _0.printStackTrace();
//            } catch (IOException _0) {
//                _0.printStackTrace();
//            }
//            if (!_dataBase.getEndGameImage().isEmpty()) {
//                try {
//                    String fileName_ = _folderName+DataBase.SEPARATOR_FILES;
//                    fileName_ += DataBase.END_GAME_IMAGE + DataBase.IMG_FILES_RES_EXT;
//                    ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getEndGameImage()), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//                } catch (RuntimeException _0) {
//                    _0.printStackTrace();
//                } catch (IOException _0) {
//                    _0.printStackTrace();
//                }
//            }
//            for (String i: _dataBase.getImages().getKeys()) {
//                try {
//                    String fileName_ = _folderName+DataBase.SEPARATOR_FILES;
//                    fileName_ += DataBase.IMAGES_FOLDER+DataBase.SEPARATOR_FILES+replaceTxtToPng(i);
//                    File folder_ = new File(fileName_.substring(0, fileName_.lastIndexOf(DataBase.SEPARATOR_FILES)));
//                    if (!folder_.exists()) {
//                        folder_.mkdirs();
//                    }
//                    ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getImages().getVal(i)), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//                } catch (RuntimeException _0) {
//                    _0.printStackTrace();
//                } catch (IOException _0) {
//                    _0.printStackTrace();
//                }
//            }
//            for (String i: _dataBase.getLinks().getKeys()) {
//                try {
//                    String fileName_ = _folderName+DataBase.SEPARATOR_FILES;
//                    fileName_ += DataBase.LINKS_FOLDER+DataBase.SEPARATOR_FILES+replaceTxtToPng(i);
//                    File folder_ = new File(fileName_.substring(0, fileName_.lastIndexOf(DataBase.SEPARATOR_FILES)));
//                    if (!folder_.exists()) {
//                        folder_.mkdirs();
//                    }
//                    ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getLinks().getVal(i)), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//                } catch (RuntimeException _0) {
//                    _0.printStackTrace();
//                } catch (IOException _0) {
//                    _0.printStackTrace();
//                }
//            }
//            for (String i: _dataBase.getPeople().getKeys()) {
//                try {
//                    String fileName_ = _folderName+DataBase.SEPARATOR_FILES;
//                    fileName_ += DataBase.PEOPLE_FOLDER +DataBase.SEPARATOR_FILES+replaceTxtToPng(i);
//                    File folder_ = new File(fileName_.substring(0, fileName_.lastIndexOf(DataBase.SEPARATOR_FILES)));
//                    if (!folder_.exists()) {
//                        folder_.mkdirs();
//                    }
//                    ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getPeople().getVal(i)), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//                } catch (RuntimeException _0) {
//                    _0.printStackTrace();
//                } catch (IOException _0) {
//                    _0.printStackTrace();
//                }
//            }
//            for (String i: _dataBase.getTrainers().getKeys()) {
//                try {
//                    String fileName_ = _folderName+DataBase.SEPARATOR_FILES;
//                    fileName_ += DataBase.TRAINERS_FOLDER+DataBase.SEPARATOR_FILES+replaceTxtToPng(i);
//                    File folder_ = new File(fileName_.substring(0, fileName_.lastIndexOf(DataBase.SEPARATOR_FILES)));
//                    if (!folder_.exists()) {
//                        folder_.mkdirs();
//                    }
//                    ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getTrainers().getVal(i)), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//                } catch (RuntimeException _0) {
//                    _0.printStackTrace();
//                } catch (IOException _0) {
//                    _0.printStackTrace();
//                }
//            }
//            for (ImageHeroKey i: _dataBase.getFrontHeros().getKeys()) {
//                try {
//                    String fileName_ = _folderName+DataBase.SEPARATOR_FILES;
//                    String folderName_;
//                    folderName_ = StringList.removeStrings(DataBase.HERO_FRONT, DataBase.IMG_FILES_RES_EXT_TXT)+DataBase.SEPARATOR_FILES;
//                    folderName_ += i.getType().name() + DataBase.SEPARATOR_FILES;
//                    File folder_ = new File(fileName_ + folderName_);
//                    if (!folder_.exists()) {
//                        folder_.mkdirs();
//                    }
//                    fileName_ += folderName_;
//                    fileName_ += i.getSex()+DataBase.IMG_FILES_RES_EXT;
//                    ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getFrontHeros().getVal(i)), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//                } catch (RuntimeException _0) {
//                    _0.printStackTrace();
//                } catch (IOException _0) {
//                    _0.printStackTrace();
//                }
//            }
//            for (ImageHeroKey i: _dataBase.getBackHeros().getKeys()) {
//                try {
//                    String fileName_ = _folderName+DataBase.SEPARATOR_FILES;
//                    String folderName_;
//                    folderName_ = StringList.removeStrings(DataBase.HERO_BACK, DataBase.IMG_FILES_RES_EXT_TXT)+DataBase.SEPARATOR_FILES;
//                    folderName_ += i.getType().name() + DataBase.SEPARATOR_FILES;
//                    File folder_ = new File(fileName_ + folderName_);
//                    if (!folder_.exists()) {
//                        folder_.mkdirs();
//                    }
//                    fileName_ += folderName_;
//                    fileName_ += i.getSex()+DataBase.IMG_FILES_RES_EXT;
//                    ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getBackHeros().getVal(i)), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//                } catch (RuntimeException _0) {
//                    _0.printStackTrace();
//                } catch (IOException _0) {
//                    _0.printStackTrace();
//                }
//            }
//            for (ImageHeroKey i: _dataBase.getOverWorldHeros().getKeys()) {
//                try {
//                    String fileName_ = _folderName+DataBase.SEPARATOR_FILES;
//                    String folderName_;
//                    folderName_ = StringList.removeStrings(DataBase.HERO_MINI, DataBase.IMG_FILES_RES_EXT_TXT)+DataBase.SEPARATOR_FILES;
//                    folderName_ += i.getType().name() + DataBase.SEPARATOR_FILES;
//                    folderName_ += i.getDirection().name() + DataBase.SEPARATOR_FILES;
//                    File folder_ = new File(fileName_ + folderName_);
//                    if (!folder_.exists()) {
//                        folder_.mkdirs();
//                    }
//                    fileName_ += folderName_;
//                    fileName_ += i.getSex()+DataBase.IMG_FILES_RES_EXT;
//                    ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getOverWorldHeros().getVal(i)), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//                } catch (RuntimeException _0) {
//                    _0.printStackTrace();
//                } catch (IOException _0) {
//                    _0.printStackTrace();
//                }
//            }
//            for (String i: _dataBase.getMiniPk().getKeys()) {
//                try {
//                    String fileName_ = _folderName+DataBase.SEPARATOR_FILES+DataBase.MINI_IMAGES_FOLDER+DataBase.SEPARATOR_FILES;
//                    fileName_ += i + DataBase.IMG_FILES_RES_EXT;
//                    File folder_ = new File(fileName_.substring(0, fileName_.lastIndexOf(DataBase.SEPARATOR_FILES)));
//                    if (!folder_.exists()) {
//                        folder_.mkdirs();
//                    }
//                    ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getMiniPk().getVal(i)), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//                } catch (RuntimeException _0) {
//                    _0.printStackTrace();
//                } catch (IOException _0) {
//                    _0.printStackTrace();
//                }
//            }
//            for (String i: _dataBase.getMaxiPkBack().getKeys()) {
//                try {
//                    String fileName_ = _folderName+DataBase.SEPARATOR_FILES+DataBase.BACK_IMAGES_FOLDER+DataBase.SEPARATOR_FILES;
//                    fileName_ += i + DataBase.IMG_FILES_RES_EXT;
//                    File folder_ = new File(fileName_.substring(0, fileName_.lastIndexOf(DataBase.SEPARATOR_FILES)));
//                    if (!folder_.exists()) {
//                        folder_.mkdirs();
//                    }
//                    ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getMaxiPkBack().getVal(i)), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//                } catch (RuntimeException _0) {
//                    _0.printStackTrace();
//                } catch (IOException _0) {
//                    _0.printStackTrace();
//                }
//            }
//            for (String i: _dataBase.getMaxiPkFront().getKeys()) {
//                try {
//                    String fileName_ = _folderName+DataBase.SEPARATOR_FILES+DataBase.FRONT_IMAGES_FOLDER+DataBase.SEPARATOR_FILES;
//                    fileName_ += i + DataBase.IMG_FILES_RES_EXT;
//                    File folder_ = new File(fileName_.substring(0, fileName_.lastIndexOf(DataBase.SEPARATOR_FILES)));
//                    if (!folder_.exists()) {
//                        folder_.mkdirs();
//                    }
//                    ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getMaxiPkFront().getVal(i)), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//                } catch (RuntimeException _0) {
//                    _0.printStackTrace();
//                } catch (IOException _0) {
//                    _0.printStackTrace();
//                }
//            }
//            for (String i: _dataBase.getMiniItems().getKeys()) {
//                try {
//                    String fileName_ = _folderName+DataBase.SEPARATOR_FILES+DataBase.OBJECTS_IMAGES_FOLDER+DataBase.SEPARATOR_FILES;
//                    fileName_ += i + DataBase.IMG_FILES_RES_EXT;
//                    File folder_ = new File(fileName_.substring(0, fileName_.lastIndexOf(DataBase.SEPARATOR_FILES)));
//                    if (!folder_.exists()) {
//                        folder_.mkdirs();
//                    }
//                    ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getMiniItems().getVal(i)), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//                } catch (RuntimeException _0) {
//                    _0.printStackTrace();
//                } catch (IOException _0) {
//                    _0.printStackTrace();
//                }
//            }
//            for (String i: _dataBase.getMiniMap().getKeys()) {
//                try {
//                    String fileName_ = _folderName+DataBase.SEPARATOR_FILES;
//                    fileName_ += DataBase.MINI_MAP_FOLDER+DataBase.SEPARATOR_FILES+replaceTxtToPng(i);
//                    File folder_ = new File(fileName_.substring(0, fileName_.lastIndexOf(DataBase.SEPARATOR_FILES)));
//                    if (!folder_.exists()) {
//                        folder_.mkdirs();
//                    }
//                    ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getMiniMap().getVal(i)), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//                } catch (RuntimeException _0) {
//                    _0.printStackTrace();
//                } catch (IOException _0) {
//                    _0.printStackTrace();
//                }
//            }
//            for (String i: _dataBase.getAnimStatis().getKeys()) {
//                try {
//                    String fileName_ = _folderName+DataBase.SEPARATOR_FILES;
//                    fileName_ += DataBase.ANIM_STATIS+DataBase.SEPARATOR_FILES+i+DataBase.IMG_FILES_RES_EXT;
//                    File folder_ = new File(fileName_.substring(0, fileName_.lastIndexOf(DataBase.SEPARATOR_FILES)));
//                    if (!folder_.exists()) {
//                        folder_.mkdirs();
//                    }
//                    ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getAnimStatis().getVal(i)), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//                } catch (RuntimeException _0) {
//                    _0.printStackTrace();
//                } catch (IOException _0) {
//                    _0.printStackTrace();
//                }
//            }
//            for (String i: _dataBase.getAnimStatus().getKeys()) {
//                try {
//                    String fileName_ = _folderName+DataBase.SEPARATOR_FILES;
//                    fileName_ += DataBase.ANIM_STATUS+DataBase.SEPARATOR_FILES+i+DataBase.IMG_FILES_RES_EXT;
//                    File folder_ = new File(fileName_.substring(0, fileName_.lastIndexOf(DataBase.SEPARATOR_FILES)));
//                    if (!folder_.exists()) {
//                        folder_.mkdirs();
//                    }
//                    ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getAnimStatus().getVal(i)), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//                } catch (RuntimeException _0) {
//                    _0.printStackTrace();
//                } catch (IOException _0) {
//                    _0.printStackTrace();
//                }
//            }
//            for (String i: _dataBase.getTypesImages().getKeys()) {
//                try {
//                    String fileName_ = _folderName+DataBase.SEPARATOR_FILES;
//                    fileName_ += DataBase.TYPES_IMAGES_FOLDER+DataBase.SEPARATOR_FILES+i+DataBase.IMG_FILES_RES_EXT;
//                    File folder_ = new File(fileName_.substring(0, fileName_.lastIndexOf(DataBase.SEPARATOR_FILES)));
//                    if (!folder_.exists()) {
//                        folder_.mkdirs();
//                    }
//                    ImageIO.write(ConverterBufferedImage.decodeToImage(_dataBase.getTypesImages().getVal(i)), DataBase.IMG_FILES_RES_EXT.substring(1), new File(fileName_));
//                } catch (RuntimeException _0) {
//                    _0.printStackTrace();
//                } catch (IOException _0) {
//                    _0.printStackTrace();
//                }
//            }
//        }
//    }
//
//    private static String replaceTxtToPng(String _i) {
//        return StringList.replace(_i, DataBase.IMG_FILES_RES_EXT_TXT,DataBase.IMG_FILES_RES_EXT);
//    }

}