public class InvalidNumberException extends Exception {
    private String message;

    public InvalidNumberException(String message) {
        this.message = message;
    }

    public static InvalidNumberException create(String message) {
        return new InvalidNumberException(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
