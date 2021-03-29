public class TextBook extends Book {

    public TextBook(String bookName, String author, Format format) {
        super(bookName, author, format);
    }

    @Override
    public Book accept(Visitor visitor) {
        Book newBook = visitor.visit(this);
        return newBook;
    }
}
