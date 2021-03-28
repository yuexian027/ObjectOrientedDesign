public class TextBook extends Book {

    public TextBook(String bookName, String author, Format format) {
        super(bookName, author, format);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
