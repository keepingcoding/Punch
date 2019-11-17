package com.example.punch;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zzs
 * @date 2019/11/14 0:00
 */
@SpringBootTest(classes = {PunchApplication.class})
public class JaysptTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void t1() {
        String s = stringEncryptor.encrypt("123456");
        System.err.println(s);
    }

    @Test
    public void t2() {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("");

        String s = textEncryptor.encrypt("123456");
        System.err.println(s);
        String s1 = textEncryptor.decrypt(s);
        System.err.println(s1);
    }
}
