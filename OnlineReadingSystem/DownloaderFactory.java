public class DownloaderFactory {

    public EBookDownloader downLoadEBook (Book book, Format format){
        if(format== Format.MOBI){
            return new MobiDownloader(book);
        }
        if(format == Format.PDF){
            return new PDFDownloader(book);
        }
        if(format == Format.TEXT){
            return new Textdownloader(book);
        }
        else return null;
    }
}
