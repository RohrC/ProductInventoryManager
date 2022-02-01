package Model;

/** This class is for creating Outsourced Parts for the inventory application. */
public class OutSourced extends Part {

    /**
     * The name of the company for an outsourced part.
     */
    private String companyName;

    /**
     * Constructor for creating an Outsourced Part.
     *
     * @param id The ID for the outsourced part.
     * @param name The name of the outsourced part.
     * @param price The price of the outsourced part.
     * @param stock The inventory level of the outsourced part.
     * @param min The minimum level for the outsourced part.
     * @param max The maximum level for the outsourced part.
     * @param companyName The company name for the outsourced part.
     */
    public OutSourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /** @param companyName the Outsourced part company name to set. */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /** @return the Outsourced part company name. */
    public String getCompanyName() {
        return companyName;
    }

}
