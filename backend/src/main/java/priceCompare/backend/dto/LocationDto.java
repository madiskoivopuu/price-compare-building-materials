package priceCompare.backend.dto;

import lombok.Builder;
import lombok.Getter;
import priceCompare.backend.enums.LocationName;
import java.util.Objects;

@Builder(toBuilder=true)
@Getter
public class LocationDto {
    LocationName locationName;
    String address;
    String googleMapsLink;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationDto that = (LocationDto) o;
        return getLocationName() == that.getLocationName() && Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocationName().getDisplayName(), getAddress());
    }

    @Override
    public String toString() {
        return locationName.getDisplayName() + ", " + address;
    }
}