package Model;

/** This class is for creating InHouse Parts for the inventory application. */
public class InHouse extends Part{

    /**
     * The machine ID for the part
     */
    private int machineId;

    /**
     * Constructor for creating an InHouse Part.
     *
     * @param id The ID for the part
     * @param name The name of the part
     * @param price The price of the part
     * @param stock The inventory level for the part
     * @param min The minimum inventory level for the part
     * @param max The maximum inventory level for the part
     * @param machineId The machine ID for the part
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * This method is setting the machine Id.
     * @param machineId the InHouse part machineId to set.
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     * This method gets the machine Id.
     * @return the InHouse part machineId.
     */
    public int getMachineId() {
        return machineId;
    }
}
