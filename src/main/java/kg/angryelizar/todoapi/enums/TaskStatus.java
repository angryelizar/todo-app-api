package kg.angryelizar.todoapi.enums;


import lombok.Getter;

@Getter
public enum TaskStatus {
    CREATED("CREATED"),
    COMPLETED("COMPLETED"),
    DELETED("DELETED");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }
}
