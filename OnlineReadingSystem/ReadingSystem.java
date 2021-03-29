
/**
 * Write a description of TieTacToe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ReadingSystem {
Map<Integer,Book> books = new HashMap<>();
Map<String, Member> members = new HashMap<>();
DownloaderFactory downloaderFactory;


public Book downLoad(Book book, Format format){
   EBookDownloader eBookDownloader = downloaderFactory.downLoadEBook(book,format);
   Book newBook = eBookDownloader.downLoad(book);
   return newBook;
}

public Book upload(Book book){
    Visitor visitor = new TextVisitor();
    Book newBook = book.accept(visitor);
    return newBook;
}

public void register (Member member){

}


    public static void main(String[] args) {


    }
}
        
    
    
    

