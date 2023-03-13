import java.io.*;

import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.*;

public class PptxFile {
    private File file;
    public PptxFile(String fileName, String directory) throws IOException {

        this.fileName = fileName;
        this.file = new File(directory+fileName);


    }

    String Directory;
    String fileName;

    int text;
    String author;
    //Stores each links response code
    public String getFileName() {
        return this.fileName;
    }
    public void setAuthor(String name){
        this.author = name;
    }
    public String getAuthor(){
        return this.author;
    }

}
