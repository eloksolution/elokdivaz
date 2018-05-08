package in.eloksolutions.divaz.dtoclasses;

public class PackageItem{
        String serviceId;
        String serviceName;
        String imagePath;

    public PackageItem(String serviceId, String serviceName,String imagePath) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.imagePath = imagePath;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "PackageItem{" +
                "serviceId='" + serviceId + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageItem that = (PackageItem) o;
        return serviceId.equals(that.serviceId);
    }

    @Override
    public int hashCode() {
        return serviceId.hashCode();
    }
}