package com.blogmaster.demo.FormBean;

public interface FormConvert<S, T> {
    T convert(S s);
}
