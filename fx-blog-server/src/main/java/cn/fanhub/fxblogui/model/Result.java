package cn.fanhub.fxblogui.model;

public class Result<T> {

    private boolean success;

    private T       value;

    private String  errorMessage;

    private Result() {
    }

    private Result(final T value, final boolean success) {
        this.value = value;
        this.success = success;
    }

    private Result(final String errorMessage) {
        this.errorMessage = errorMessage;
        this.success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getValue() {
        if (!isSuccess())
            return null;

        return this.value;
    }

    public String getErrorMessage() {
        if (isSuccess())
            return null;
        return errorMessage;
    }

    public static <T> Result<T> of(T value) {
        if (value == null) {
            return ofNull();
        }
        return new Result<>(value, true);
    }

    public static <T> Result<T> errorMessage(String errorMessage) {
        return new Result<>(errorMessage);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> Result<T> success() {
        return new Result(true, true);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> Result<T> ofNull() {
        return new Result(null, true);
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}