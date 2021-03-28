public abstract class EBookDownloader {
    protected Book book;


    public EBookDownloader(Book b) {
        this.book = b;
    }
    public abstract Book downLoad (Book b);

}
