package com.pucminas.conectabh_service.adapter;

public interface Adapter<T, S> {
    S convert(T t);
}
