package kg.angryelizar.todoapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {
    @JsonProperty("color")
    private String color;

    @JsonProperty("capacity")
    private String capacity;

    @JsonProperty("capacity GB")
    private Integer capacityGB;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("Generation")
    private String generation;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("CPU model")
    private String cpuModel;

    @JsonProperty("Hard disk size")
    private String hardDiskSize;

    @JsonProperty("Strap Colour")
    private String strapColour;

    @JsonProperty("Case Size")
    private String caseSize;

    @JsonProperty("Color")
    private String colorAlt;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("Capacity")
    private String capacityAlt;

    @JsonProperty("Screen size")
    private Double screenSize;

    @Override
    public String toString() {
        return "Data{" +
                "color='" + color + '\'' +
                ", capacity='" + capacity + '\'' +
                ", capacityGB=" + capacityGB +
                ", price=" + price +
                ", generation='" + generation + '\'' +
                ", year=" + year +
                ", cpuModel='" + cpuModel + '\'' +
                ", hardDiskSize='" + hardDiskSize + '\'' +
                ", strapColour='" + strapColour + '\'' +
                ", caseSize='" + caseSize + '\'' +
                ", colorAlt='" + colorAlt + '\'' +
                ", description='" + description + '\'' +
                ", capacityAlt='" + capacityAlt + '\'' +
                ", screenSize=" + screenSize +
                '}';
    }
}
