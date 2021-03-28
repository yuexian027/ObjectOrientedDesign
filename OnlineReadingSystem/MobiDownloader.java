public class MobiDownloader extends EBookDownloader {

    public MobiDownloader(Book b) {
        super(b);

    }

    @Override
    public Book downLoad(Book b) {
        //copy the book that need to be download and preset its type to mobi
        Book newBook = new MobiBook(b.getBookName(),b.getAuthor(),Format.MOBI);
        //create the visitor instance to do the transformatting
        MobiVisitor visitor = new MobiVisitor();
        //the copy book accept the transformat
        newBook = newBook.accept(visitor);

        return newBook;    }


}
