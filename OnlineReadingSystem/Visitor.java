public interface Visitor {

    Book visit(MobiBook book);
    Book visit(PdfBook book);
    Book visit(TextBook book);
}
