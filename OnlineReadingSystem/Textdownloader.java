public class Textdownloader extends EBookDownloader {

    public Textdownloader(Book b) {
        super(b);

    }

    @Override
    public Book downLoad(Book b) {
        Book newBook = new TextBook(this.book.getBookName(),this.book.getAuthor(),this.book.getFormat());
        TextVisitor visitor = new TextVisitor();
        newBook.accept(visitor);

        return newBook;    }
}
