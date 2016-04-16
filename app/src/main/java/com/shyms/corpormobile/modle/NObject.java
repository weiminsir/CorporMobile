package com.shyms.corpormobile.modle;

import java.io.Serializable;

/**
 * Created by Weimin on 4/7/2016.
 */
public class NObject<T> implements Serializable {
    final public static int DEFAULTE_ID_VALUE = -1;

    public final String code;
    public final T data;
    public final String message;
    public final Object meta;

    public NObject() {
        code = null;
        data = null;
        message = null;
        meta = null;


    }
}
