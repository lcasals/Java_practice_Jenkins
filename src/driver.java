import java.io.IOException;
import java.util.ArrayList;

public class driver {

    //change to accept an array of arrays and create a for loop to enter each to make it.
    public static void main(ArrayList<String> pdfArray, ArrayList<String> pptxArray) throws IOException {

        System.out.print("Running second program\n");
        FileObjectCreation createObject = new FileObjectCreation();
        createObject.createPdfObjects(pdfArray);
        createObject.createPptxObjects(pptxArray);

        for(PdfFile pdf: createObject.getListOfPdfObjects()){

            System.out.println("name of file: " + pdf.getFileName()+"\n");
            System.out.println("name of author: " + pdf.getAuthor()+"\n");
            System.out.println("page count: " + pdf.getPageCount()+"\n");
            System.out.println("file size: " + (pdf.getFileSize()/1024) +"KB "
                    + pdf.getFileSize()+ " bytes" + "\n");
            System.out.println("word count: " + pdf.getWordCount()+"\n");
            System.out.println("date created: " + pdf.getFileMonth()+"/"+pdf.getFileDay()+
                    "/"+pdf.getFileYear()+" " +pdf.getFileHour()+":"+pdf.getFileMinute()+":"
                    +pdf.getFileSecond()+"\n");

        }
        for(PptxFile pptx: createObject.getListOfPptxObjects()){
            System.out.println("Name of file: " + pptx.getFileName()+"\n");
            System.out.println("Name of author: " + pptx.getAuthorName()+"\n");
            System.out.println("Number of slides: " + pptx.getNumberOfSlides()+"\n");
            System.out.println("file size: " + (pptx.getFileSize()/1024) +"KB "
                    + pptx.getFileSize()+ " bytes" + "\n");
            System.out.println("word count: " + pptx.getWordCount()+"\n");
            System.out.println("Date Created: " + pptx.getCreationDate()+"\n");
        }
    }

}
