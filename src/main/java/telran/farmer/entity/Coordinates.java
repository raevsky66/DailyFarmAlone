package telran.farmer.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Coordinates {
	Double latitude;
	Double longitude;
}
