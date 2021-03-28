//把各种格式转化为pdf格式
public class PdfVisitor implements Visitor {



    @Override
    public Book visit(MobiBook book) {
        Book newBook = new PdfBook(book.getBookName(),book.getAuthor(),book.getFormat());
        //do reformatting
        newBook.setFormat(Format.PDF);
        System.out.println(book.getFormat() + "  Book is reformatted in Pdf file");
        return newBook;
    }

    @Override
    public Book visit(PdfBook book) {
        Book newBook = new PdfBook(book.getBookName(),book.getAuthor(),book.getFormat());
        //do reformatting
        newBook.setFormat(Format.PDF);
        System.out.println(book.getFormat() + "  Book is reformatted in Pdf file");
        return newBook;
    }

    @Override
    public Book visit(TextBook book) {
        Book newBook = new PdfBook(book.getBookName(),book.getAuthor(),book.getFormat());
        //do reformatting
        newBook.setFormat(Format.PDF);
        System.out.println(book.getFormat() + "  Book is reformatted in Pdf file");
        return newBook;
    }


}
