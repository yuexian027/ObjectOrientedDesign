public class readerFactory {

    public EBookReader readBook (Book book, Format format){
        if(format== Format.MOBI){
            return new MobiReader(book);
        }
        if(format == Format.PDF){
            return new PDFReader(book);
        }
        if(format == Format.TEXT){
            return new TextReader(book);
        }
        else return null;
    }
}
