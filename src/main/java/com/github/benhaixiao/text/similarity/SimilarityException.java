package com.github.benhaixiao.text.similarity;

/**
 * Created by BenHaiXiao
 * Date: 2017/3/13
 * Time: 14:37
 */
public class SimilarityException extends Exception{
    private static final long serialVersionUID = -2855287805931261418L;

    public SimilarityException() {
        super();
    }

    public SimilarityException(String message) {
        super(message);
    }

    public SimilarityException(String message, Throwable cause) {
        super(message, cause);
    }

    public SimilarityException(Throwable cause) {
        super(cause);
    }
}
