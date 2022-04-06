/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package processclass;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javazoom.jl.converter.Converter;
import javazoom.jl.decoder.JavaLayerException;
import org.apache.commons.io.FilenameUtils;
import static processclass.PostClient.PostData;
import responseclass.FPTResponse;

/**
 *
 * @author hnam1
 */
public class ProcessOutput {
    
    public static String[] ProcessString(String data) {
        String processed = "";
        String[] parts = data.split("\n");
        for (String part : parts) {
            String read = part.trim();
            if (read.endsWith(".")) {
                read = read + "\n";
            } else {
                read = read + " ";
            }
            processed = processed + read;
        }
        Pattern pattern1 = Pattern.compile("[\\.|\\!|\\?|\\:][\s]");
        Pattern pattern2 = Pattern.compile("[\s][\s]");
        String proc = processed.replaceAll(pattern1.pattern(), "\n").replaceAll(pattern2.pattern(), "\n");

        return proc.split("\n");
    }
    
    public static File mp3ToWav(File audioFile) {

        System.out.println("Converting mp3 to wav...");
        Converter converter = new Converter();
        String fileURI = audioFile.getAbsolutePath();
        File tempWavFile;
        try {
            tempWavFile = File.createTempFile("soniccandle", ".wav");
            tempWavFile.deleteOnExit();
            converter.convert(fileURI, tempWavFile.getAbsolutePath());
        } catch (IOException | JavaLayerException e1) {
            System.out.println("Conversion Failed");
            return null;
        }        
        System.out.println("Conversion Complete");
        return tempWavFile;
    }
    
    public static void transferFile(String src, String des){
        File file = new File(src);
        file.renameTo(new File(des));
    }
    
    public static void transferFile(File src, String des){
        File file = src;
        file.renameTo(new File(des));
    }
    
    public static void downloadFromURL(String URL, String FileName) throws MalformedURLException, IOException {
        InputStream in = new URL(URL).openStream();
        Files.copy(in, Paths.get(FileName), StandardCopyOption.REPLACE_EXISTING);
        in.close();        
    }
    
    public volatile static int complete;
    public volatile static int total;
    public static void DownloadBulk(String[] datas, String FolderName){
        total = datas.length;
        complete = 0;
        for (int i = 0; i < total; i++) {
            int curr = i;
            String data = datas[i];
            FPTResponse response = null;
            try {
                response = PostData(data);
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(ProcessOutput.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (response.getError() == null || response.getError() == 0) {
                do {
                    try {
                        downloadFromURL(response.getAsync(), FolderName + "\\" + String.valueOf(curr) + ".mp3");
                        
                    } catch (IOException ex) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException ex1) {
                            Logger.getLogger(ProcessOutput.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                        continue;
                    }
                    break;
                } while (true);

            } else {
                System.out.println("Phần thứ " + curr + " đã không thể tải do có lỗi.");
            }

            complete++;

        }
    }
    public static void mergeAudio(String SourceFolderMP3, String destinationFileName) throws UnsupportedAudioFileException, IOException{
        String FolderName = SourceFolderMP3;
        File root = new File(FolderName);
        System.out.println(root.getAbsolutePath());
        File[] mp3s = root.listFiles(
                (File pathname) -> FilenameUtils.getExtension(pathname.getName())
                        .toLowerCase()
                        .equals("mp3"));
        ArrayList<AudioInputStream> arrStream = new ArrayList<>();
        long frameLength = 0;
        AudioFormat format = null;
        for (File mp3 : mp3s) {
            File wav = mp3ToWav(mp3);
            AudioInputStream aStream = AudioSystem.getAudioInputStream(wav);
            if(format == null){
                format = aStream.getFormat();
            }
            arrStream.add(aStream);
            frameLength += aStream.getFrameLength();
        }
        AudioSystem.write(new AudioInputStream(new SequenceInputStream(Collections.enumeration(arrStream)), format, frameLength), AudioFileFormat.Type.WAVE, new File("docs\\audio\\final.wav"));

    }
    
}
