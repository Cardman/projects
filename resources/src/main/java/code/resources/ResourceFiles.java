package code.resources;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public final class ResourceFiles {
    public static final String SEPARATEUR = "/";
    private static final String LINE_RETURN = "\n";
    private static final String EMPTY_STRING = "";
    private static final char INVALID_CHARACTER = 65533;

    private ResourceFiles() {
    }

    public static ClipStream resourceSound(String _file) {
        try {
            Clip clip_ = AudioSystem.getClip();
            InputStream in_ = ClassLoader.getSystemResourceAsStream(_file);
            AudioInputStream audioIn_ = AudioSystem.getAudioInputStream(in_);
            clip_.open(audioIn_);
            in_.close();
            ClipStream c_ = new ClipStream();
            c_.setClip(clip_);
            c_.setStream(audioIn_);
            return c_;
        } catch (RuntimeException _0) {
            return null;
        } catch (LineUnavailableException _0) {
            return null;
        } catch (UnsupportedAudioFileException _0) {
            return null;
        } catch (IOException _0) {
            return null;
        }
    }

    public static String ressourceFichier(String _filePath) {
        return resourceTextFile(EMPTY_STRING, _filePath, false);
    }

    public static String ressourceFichierUrls(String _filePath, String... _resourcesFolder) {
        String file_ = resourceTextFile(EMPTY_STRING, _filePath, true);
        if (file_ != null) {
            return file_;
        }
        for (String u: _resourcesFolder) {
            file_ = resourceTextFile(u, _filePath, true);
            if (file_ != null) {
                return file_;
            }
        }
        return EMPTY_STRING;
    }

    private static String resourceTextFile(String _url, String _filePath, boolean _returnNullFail) {
        String lignes_ = EMPTY_STRING;
        try {
            lignes_ = readFile(_url+_filePath, StandardCharsets.UTF_8.getName());
            int ind_ = lignes_.indexOf(INVALID_CHARACTER);
            if (ind_ >= 0) {
                lignes_ = readFile(_url+_filePath, StandardCharsets.ISO_8859_1.getName());
            }
            return lignes_;
        } catch (RuntimeException _0) {
            if (_returnNullFail) {
                return null;
            }
            return lignes_;
        }
    }

    private static String readFile(String _file, String _encoding) {
        InputStream inputStream_ = null;
        InputStreamReader reader_ = null;
        BufferedReader br_ = null;
        try {
            inputStream_ = ClassLoader.getSystemResourceAsStream(_file);
            reader_ = new InputStreamReader(inputStream_, Charset.forName(StandardCharsets.ISO_8859_1.getName()));
            br_ = new BufferedReader(reader_);
            return readingFile(LINE_RETURN, br_);
        } finally {
            close(inputStream_, reader_, br_);
        }
    }

    public static BufferedImage resourceBufferedImage(String _nomFichier) {
        ByteArrayInputStream bis_ = null;
        try {
            byte[] data_ = ResourceFiles.resourceFileAsBytes(_nomFichier);
            bis_ = new ByteArrayInputStream(data_);
            return ImageIO.read(bis_);
        } catch (RuntimeException _0) {
            try {
                return ImageIO.read(new File(_nomFichier));
            } catch (RuntimeException _1) {
            } catch (IOException _1) {
            }
            return null;
        } catch (IOException _0) {
            try {
                return ImageIO.read(new File(_nomFichier));
            } catch (RuntimeException _1) {
            } catch (IOException _1) {
            }
            return null;
        } finally {
            try {
                if (bis_ != null) {
                    bis_.close();
                }
            } catch (RuntimeException _0) {
            } catch (IOException _0) {
            }
        }
    }

    public static ImageIcon ressourceIcon(String _nomFichier) {
        try {
            byte[] data_ = ResourceFiles.resourceFileAsBytes(_nomFichier);
            return new ImageIcon(data_);
        } catch (RuntimeException _0) {
            return new ImageIcon(_nomFichier);
        }
    }

    public static byte[] resourceFileAsBytes(String _file) {
        ClassLoader classLoader_;
        classLoader_ = ClassLoader.getSystemClassLoader();
        URL url_ = classLoader_.getResource(_file);
        URLConnection connect_ = null;
        InputStream inputStream_ = null;
        BufferedInputStream buff_ = null;
        try {
            connect_ = url_.openConnection();
            inputStream_ = connect_.getInputStream();
            buff_ = new BufferedInputStream(inputStream_);
            int len_ = connect_.getContentLength();
            int index_ = 0;
            byte[] bytes_ = new byte[len_];
            while (true) {
                int read_ = buff_.read(bytes_, index_, len_ - index_);
                if (read_ == -1) {
                    break;
                }
                if (index_ == len_) {
                    break;
                }
                index_ += read_;
            }
            return bytes_;
        } catch (RuntimeException _0) {
            return null;
        } catch (IOException _0) {
            return null;
        } finally {
            try {
                if (buff_ != null) {
                    buff_.close();
                }
                if (inputStream_ != null) {
                    inputStream_.close();
                }
            } catch (IOException _0) {
            } catch (RuntimeException _0) {
            }
        }
    }
    /**
    @param lignes_
    @param _saut
    @param in_
    @return
    @throws IOException
    */
    private static String readingFile(String _saut, BufferedReader _br) {
        try {
            StringBuilder strBuilder_ = new StringBuilder();
            while (true) {

                String ligne_ = _br.readLine();
                if (ligne_ == null) {
                    break;
                }
                strBuilder_.append(ligne_);
                strBuilder_.append(_saut);
            }
            return strBuilder_.toString();
        } catch (IOException _0) {
            return null;
        }
    }

    private static void close(InputStream _inputStream,
            InputStreamReader _reader, BufferedReader _br) {
        try {
            if (_br != null) {
                _br.close();
            }
            if (_reader != null) {
                _reader.close();
            }
            if (_inputStream != null) {
                _inputStream.close();
            }
        } catch (RuntimeException _0) {
        } catch (IOException _0) {
        }
    }
}
