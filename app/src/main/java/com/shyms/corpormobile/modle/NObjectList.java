package com.shyms.corpormobile.modle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Weimin on 4/7/2016.
 */
public class NObjectList<T> implements Serializable {

    public final String code;
    public final List<T> data;
    public final String message;
    public final Object meta;


    public NObjectList() {

        code = null;
        data = null;
        message = null;
        meta = null;

    }


}
