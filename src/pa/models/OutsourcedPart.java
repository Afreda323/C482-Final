package pa.models;

/**
 *
 * @author anthonyfreda
 */
public class OutsourcedPart extends Part {
    private String companyName;
    
    /**
     * Get the company name
     * @return 
     */
    public String getCompanyName() {
        return this.companyName;
    }
    
    /**
     * Set the company name
     * @param companyName 
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
