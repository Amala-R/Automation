package framework.model.error;

import lombok.Data;

@Data
public class ValidationError {
    private String timestamp;
    private String status;
    private String error;
    private String message;
    private String path;
}
