package br.com.mercadolivre.simios.domain.http;

public class DNAResponse {

    private final String msg;

    private DNAResponse(String msg) {
	this.msg = msg;
    }

    public static DNAResponse of(String msg) {
        return new DNAResponse(msg);
    }

    public String getMsg() {
	return msg;
    }
}
