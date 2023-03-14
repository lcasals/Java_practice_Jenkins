import java.io.*;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.*;

//XSLF Cook Book URL: https://poi.apache.org/components/slideshow/xslf-cookbook.html#ReadPresentation
//Provides "recipes" for reading and writing to ppt/pptx files with Apache poi

public class PptxFile {
    File file;
    public PptxFile(String nameOfFile, String directory){
        //setting the file to a new File instance with an abstract pathname
        this.file = new File(directory+nameOfFile);

        //Setting up an existing pptx presentation to be read
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        //creating XMLSlideShow object called ppt with the path defined (inputStream)
        XMLSlideShow pptx;
        try {
            pptx = new XMLSlideShow(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //sets the value of fileName to the parameter nameOfFile
        this.fileName = nameOfFile;

        //calls the setFileSize method and returns the files length in bytes
        this.fileSize = setFileSize();

        //calls the setAuthorName method and extracts the name of the file creator
        this.author = setAuthorName(pptx);

        //calls the setCreationDate method to extract timestamp
        this.creationDate = setCreationDate(pptx);

        //calls the setNumberOfSlides methods and returns the number of slides counted
        this.numberOfSlides = setNumberOfSlides(pptx);

        //calls the setWordCount method which returns the word count
        //Issue, this method also counts words in images...
        this.wordCount = setWordCount(pptx);

    }

    //variable definitions
    String fileName;
    int numberOfSlides;
    String author;
    String creationDate;
    long fileSize;

    int wordCount;

    //get and set methods

    public String getFileName()
    {
        return this.fileName;
    }
    public long setFileSize()
    {
        return file.length();
    }
    public long getFileSize()
    {
        return this.fileSize;
    }
   public String setAuthorName(XMLSlideShow ppt)
    {
        POIXMLProperties.CoreProperties props = ppt.getProperties().getCoreProperties();
        return String.valueOf(props.getCreator());
    }
    public String getAuthorName(){
        return this.author;
    }
    public int setNumberOfSlides(XMLSlideShow ppt) {
        int slideCount = 0;
        //changed slide to ignored...not exactly sure how this works...
        for (XSLFSlide ignored: ppt.getSlides()) {
            slideCount++;
        }
        return slideCount;
    }
    public int getNumberOfSlides(){
        return this.numberOfSlides;
    }

    public String setCreationDate(XMLSlideShow ppt)
    {
        POIXMLProperties.CoreProperties props = ppt.getProperties().getCoreProperties();
        return String.valueOf(props.getCreated());
    }

    public String getCreationDate(){
        return this.creationDate;
    }
    public int setWordCount(XMLSlideShow ppt)
    {
        int count = 0;
        for (XSLFSlide slide: ppt.getSlides()) {
            XSLFShape[] shapes = slide.getShapes().toArray(new XSLFShape[0]);
            for (XSLFShape shape: shapes) {
                if (shape instanceof XSLFTextShape) {
                    XSLFTextShape textShape = (XSLFTextShape)shape;
                    String text = textShape.getText().trim();
                    if(text.length() != 0)
                    {
                        count++;
                        //System.out.println("Text: " + text);
                    }
                }
            }
        }
        return count;
    }
    public int getWordCount()
    {
        return this.wordCount;
    }
}

