package pa.models;

/**
 *
 * @author anthonyfreda
 */
public class InhousePart extends Part{
    private int machineID;
    
    /**
     * Get the machine ID
     * @return 
     */
    public int getMachineID() {
        return this.machineID;
    }
    
    /**
     * Set the machine ID
     * @param machineID 
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
