package br.com.foco.financeiro.dto;


import java.time.Instant;

public class ErrorResponseDTO {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    private ErrorResponseDTO() {}

    public Instant getTimestamp() { return timestamp; }
    public Integer getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public String getPath() { return path; }

    public static class Builder {
        private Instant timestamp;
        private Integer status;
        private String error;
        private String message;
        private String path;

        public Builder withTimestamp(Instant t) { this.timestamp = t; return this; }
        public Builder withStatus(Integer s) { this.status = s; return this; }
        public Builder withError(String e) { this.error = e; return this; }
        public Builder withMessage(String m) { this.message = m; return this; }
        public Builder withPath(String p) { this.path = p; return this; }

        public ErrorResponseDTO build() {
            ErrorResponseDTO dto = new ErrorResponseDTO();
            dto.timestamp = this.timestamp;
            dto.status = this.status;
            dto.error = this.error;
            dto.message = this.message;
            dto.path = this.path;
            return dto;
        }
    }
}
