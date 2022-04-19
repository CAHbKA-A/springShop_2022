package ru.gb.springShop.core.validators;

public interface Validator<E> {
    void validate(E e);
}
