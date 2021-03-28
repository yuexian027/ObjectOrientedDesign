public class PDFDownloader extends EBookDownloader {

    public PDFDownloader(Book b) {
        super(b);

    }

    @Override
    public Book downLoad(Book b) {
        Book newBook = new MobiBook(this.book.getBookName(),this.book.getAuthor(),this.book.getFormat());
        PdfVisitor visitor = new PdfVisitor();
        newBook.accept(visitor);

        return newBook;    }
}
