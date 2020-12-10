public class StoreHouse {
    private static int counter = 0;
    private int id;
    private int blockNumber;

    public int getId() {
        return id;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    public StoreHouse(int blockNumber) {
        counter++;
        this.id = counter;
        this.blockNumber = blockNumber;
    }
}