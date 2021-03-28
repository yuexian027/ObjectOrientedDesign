public class PdfBook extends Book {
    public PdfBook(String bookName, String author, Format format) {
        super(bookName, author, format);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
