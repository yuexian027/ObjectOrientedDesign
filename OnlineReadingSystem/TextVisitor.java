//把各种格式转化为text格式
public class TextVisitor implements Visitor {



    @Override
    public Book visit(MobiBook book) {
        Book newBook = new TextBook(book.getBookName(),book.getAuthor(),book.getFormat());
        //do reformatting
        newBook.setFormat(Format.TEXT);
        System.out.println(book.getFormat() + " Book is reformatted in text file");
        return newBook;
    }

    @Override
    public Book visit(PdfBook book) {
        Book newBook = new TextBook(book.getBookName(),book.getAuthor(),book.getFormat());
        //do reformatting
        newBook.setFormat(Format.TEXT);
        System.out.println(book.getFormat() + " Book is reformatted in text file");
        return newBook;
    }

    @Override
    public Book visit(TextBook book) {
        Book newBook = new TextBook(book.getBookName(),book.getAuthor(),book.getFormat());
        //do reformatting
        newBook.setFormat(Format.TEXT);
        System.out.println(book.getFormat() + " Book is reformatted in text file");
        return newBook;
    }


}
