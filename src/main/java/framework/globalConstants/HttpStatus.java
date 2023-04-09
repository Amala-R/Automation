package framework.globalConstants;

public enum HttpStatus {

    OK(200, "OK"),
    NOT_FOUND(404, "Not Found");
    private int code;
    private String desc;

    HttpStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * Gets the HTTP status code
     * @return the status code number
     */
    public int getCode() {
        return code;
    }


}
