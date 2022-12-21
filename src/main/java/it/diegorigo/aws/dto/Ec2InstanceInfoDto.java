package it.diegorigo.aws.dto;

public class Ec2InstanceInfoDto {
    private String name;

    private String status;

    /**
     * The wrapper method of the {@link #name} property.
     *
     * @return the value of the property
     */
    public String getName() {
        return name;
    }

    /**
     * Stores the value of the {@link #name} property internally.
     *
     * @param name the property to be stored
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The wrapper method of the {@link #status} property.
     *
     * @return the value of the property
     */
    public String getStatus() {
        return status;
    }

    /**
     * Stores the value of the {@link #status} property internally.
     *
     * @param status the property to be stored
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
