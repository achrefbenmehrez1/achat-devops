package tn.esprit.rh.achat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class OperateurServiceImplTest {
    private OperateurServiceImpl operateurServiceImpl;

    @Mock
    private OperateurRepository operateurRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.operateurServiceImpl = new OperateurServiceImpl(operateurRepository);
    }

    @Test
    @Order(1)
    public void testAddOperateur() {
        Operateur operateur = new Operateur();
        operateur.setNom("test");
        operateur.setPrenom("test");
        operateur.setPassword("test");

        when(operateurRepository.save(any(Operateur.class))).thenReturn(operateur);

        operateurServiceImpl.addOperateur(operateur);
        assertEquals(0, operateurServiceImpl.retrieveAllOperateurs().size());
    }

    @Test
    @Order(2)
    public void testRetrieveAllOperateurs() {
        when(operateurRepository.findAll()).thenReturn(List.of(new Operateur(), new Operateur()));
        assertEquals(2, operateurServiceImpl.retrieveAllOperateurs().size());
    }

    @Test
    @Order(3)
    public void testUpdateOperateur() {
        Operateur operateur = new Operateur();
        operateur.setIdOperateur(1L); // Set the ID to match the existing operateur
        operateur.setNom("Updated Name");
        operateur.setPrenom("test");
        operateur.setPassword("test");

        when(operateurRepository.findById(1L)).thenReturn(Optional.of(operateur));
        when(operateurRepository.save(operateur)).thenReturn(operateur);

        operateurServiceImpl.updateOperateur(operateur);

        assertNotNull(operateurServiceImpl.retrieveOperateur(1L).getNom());
    }

    @Test
    @Order(4)
    public void testRetrieveOperateur() {
        when(operateurRepository.findById(1L)).thenReturn(Optional.empty());

        assertNull(operateurServiceImpl.retrieveOperateur(1L));
    }

    @Test
    @Order(5)
    public void testDeleteOperateur() {
        when(operateurRepository.findAll()).thenReturn(Collections.emptyList());

        operateurServiceImpl.deleteOperateur(1L);
        assertEquals(0, operateurServiceImpl.retrieveAllOperateurs().size());
    }
}
