public abstract class EBookReader {
    protected Book book;


    public EBookReader(Book b) {

        this.book = b;
    }
    public abstract void read (Book b);

}
