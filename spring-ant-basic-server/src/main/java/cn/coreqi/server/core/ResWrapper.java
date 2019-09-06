package cn.coreqi.server.core;

/**
 * rest返回包装类
 */
public class ResWrapper<T> {
    public enum StatusCode {
        SUCCESS("成功", "0000"),
        BUSINESS_ERROR("业务错误", "9999");

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        private String code;

        StatusCode(String name, String code) {
            this.name = name;
            this.code = code;
        }
    }

    public ResWrapper() {
    }

    public ResWrapper(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResWrapper(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public static <T> ResWrapper Success(T data) {
        return new ResWrapper<>(StatusCode.SUCCESS.getCode(), "", data);
    }

    public static ResWrapper Success() {
        return new ResWrapper(StatusCode.SUCCESS.getCode(), "");
    }

    public static <T> ResWrapper BusinessError(String message, T data) {
        return new ResWrapper<>(StatusCode.BUSINESS_ERROR.getCode(), message, data);
    }

    /**
     * 编码
     */
    private String code;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    //--------------------getter setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
