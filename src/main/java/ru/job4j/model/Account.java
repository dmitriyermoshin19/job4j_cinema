package ru.job4j.model;

import java.util.Objects;

public class Account {
    private  String fio;
    private final String phone;

    public Account(String fio, String phone) {
        this.fio = fio;
        this.phone = phone;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(fio, account.fio)
                && Objects.equals(phone, account.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fio, phone);
    }

    @Override
    public String toString() {
        return String.format("Account: fio=%s, phone=%s", fio, phone);
    }
}
