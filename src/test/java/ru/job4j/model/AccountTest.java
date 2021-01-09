package ru.job4j.model;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void whenTestMethodEqualsAndHashcode() {
        Account account = new Account("Sergei", "+375295556677");
        Account account1 = new Account("Sergei", "+375295556677");
        Account account2 = new Account("Sergei", "+375296665577");
        Account account3 = null;
        Account account4 = null;

        Set<Account> store = new HashSet<>();
        store.add(account);
        store.add(account1);
        store.add(account2);
        store.add(account3);
        store.add(account4);

        assertThat(store.size(), is(3));
    }
}