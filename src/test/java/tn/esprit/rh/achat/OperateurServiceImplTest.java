package tn.esprit.rh.achat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class OperateurServiceImplTest {
    @Autowired
    private OperateurServiceImpl operateurServiceImpl;

    @Test
    @Order(1)
    public void testRetrieveAllOperateurs() {
        assertEquals(1, operateurServiceImpl.retrieveAllOperateurs().size());
    }

    @Test
    @Order(1)
    public void testAddOperateur() {
        Operateur operateur = new Operateur();
        operateur.setNom("test");
        operateur.setPrenom("test");
        operateur.setPassword("test");
        operateurServiceImpl.addOperateur(operateur);
        assertEquals(1, operateurServiceImpl.retrieveAllOperateurs().size());
    }

    @Test
    @Order(3)
    public void testDeleteOperateur() {
        operateurServiceImpl.deleteOperateur(1L);
        assertEquals(0, operateurServiceImpl.retrieveAllOperateurs().size());
    }

    @Test
    @Order(4)
    public void testUpdateOperateur() {
        Operateur operateur = new Operateur();
        operateur.setNom("test");
        operateur.setPrenom("test");
        operateur.setPassword("test");
        operateurServiceImpl.updateOperateur(operateur);
        assertEquals(null, operateurServiceImpl.retrieveOperateur(2L).getNom());
    }

    @Test
    @Order(5)
    public void testRetrieveOperateur() {
        assertNull(operateurServiceImpl.retrieveOperateur(2L));
    }
}