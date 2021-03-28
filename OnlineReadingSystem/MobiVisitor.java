//把各种格式转化为mobi格式
public class MobiVisitor implements Visitor {


    @Override
    public Book visit(MobiBook book) {
        //do reformatting
        book.setFormat(Format.MOBI);
        System.out.println(book.getFormat() + "  Book is reformatted in Mobi file");
        return book;
    }

    @Override
    public Book visit(PdfBook book) {
        //do reformatting
        book.setFormat(Format.MOBI);
        System.out.println(book.getFormat() + "  Book is reformatted in Mobi file");
        return book;
    }

    @Override
    public Book visit(TextBook book) {

        //do reformatting
        book.setFormat(Format.MOBI);
        System.out.println(book.getFormat() + "  Book is reformatted in Mobi file");
        return book;
    }
}



