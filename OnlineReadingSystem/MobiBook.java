public class MobiBook extends Book{

    public MobiBook(String bookName, String author, Format format) {

        super(bookName, author, format);
    }

    @Override
    public Book accept(Visitor visitor) {
        visitor.visit(this);
    }


}
