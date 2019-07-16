package ar.edu.unlam.tallerweb1.mocks;

import ar.edu.unlam.tallerweb1.Helpers.MailHelper;
import ar.edu.unlam.tallerweb1.SpringTest;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

public class MailTest extends SpringTest {
    @Test
    @Transactional
    @Rollback
    public void EnviarMailTest() throws MessagingException {
        MailHelper.enviarMail("tomas.j.pais@gmail.com", "prueba", "prueba");
    }
}
