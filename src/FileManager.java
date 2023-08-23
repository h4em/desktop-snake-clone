import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


//TODO: poczytac czy tutaj nie bedzie zle jak file atrybut i wogle
public class FileManager {
    private final File file;
    public FileManager() {
        file = new File(".\\highscore.bin");
        if(!file.exists()) {
            try {
                file.createNewFile();
                initFile();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    private void initFile() {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            saveIntToBinaryStream(0, fos);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public int readScore() {
        int result = 0;
        try {
            FileInputStream fis = new FileInputStream(file);
            result = readIntFromBinaryStream(fis);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    public void saveScore(int score) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            saveIntToBinaryStream(score, fos);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private int readIntFromBinaryStream(FileInputStream fis) throws IOException {
        int n = 0;

        n |= (fis.read() << 24);
        n |= (fis.read() << 16);
        n |= (fis.read() << 8);
        n |= fis.read();
        return n;
    }

    private void saveIntToBinaryStream(int n, FileOutputStream fos) throws IOException {
        fos.write(n >> 24);
        fos.write(n >> 16);
        fos.write(n >> 8);
        fos.write(n);
    }
}