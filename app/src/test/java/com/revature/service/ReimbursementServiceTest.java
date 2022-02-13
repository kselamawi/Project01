package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class ReimbursementServiceTest {

    @Mock
    ReimbursementDao reimbursementDao;

    @InjectMocks
    ReimbursementService reimbursementService;
    Reimbursement reimbursement;
    List<Reimbursement> reimbursementList;

    @Before
    public void injectThings() {
        MockitoAnnotations.openMocks(this);

        reimbursement = new Reimbursement(240, 5, ReimbursementType.LODGING);
    }

    @Test
    public void getReimbursementById() {
        when(reimbursementService.getReimbursementById(anyInt()))
                .thenReturn(reimbursement);

        assertEquals(reimbursementService.getReimbursementById(anyInt()), reimbursement);
    }

    @Test
    public void changeReimbursementStatus(){

        when(reimbursementService.changeReimbursementStatus(anyInt(), anyInt(), true)).thenReturn(true);
        when(reimbursementService.changeReimbursementStatus(anyInt(), anyInt(), false)).thenReturn(true);

        assertEquals(reimbursementService.changeReimbursementStatus(anyInt(), anyInt(), true), true);
        assertEquals(reimbursementService.changeReimbursementStatus(anyInt(), anyInt(), false), true);
    }

    @Test
    public void getAllResolvedReimbursementsByAuthor() {
        when(reimbursementService.getAllResolvedReimbursementsByAuthor(anyInt()))
                .thenReturn(reimbursementList);

        assertEquals(reimbursementService.getAllResolvedReimbursementsByAuthor(anyInt()), reimbursementList);
    }

    @Test
    public void getAllPendingReimbursementsByAuthor(){
        when(reimbursementService.getAllPendingReimbursementsByAuthor(anyInt()))
                .thenReturn(reimbursementList);

        assertEquals(reimbursementService.getAllPendingReimbursementsByAuthor(anyInt()), reimbursementList);
    }

    @Test
    public void getAllPendingReimbursements() {
        when(reimbursementService.getAllPendingReimbursements())
                .thenReturn(reimbursementList);

        assertEquals(reimbursementService.getAllPendingReimbursements(), reimbursementList);
    }

    @Test
    public void getAllResolvedReimbursements() {
        when(reimbursementService.getAllResolvedReimbursements())
                .thenReturn(reimbursementList);

        assertEquals(reimbursementService.getAllResolvedReimbursements(), reimbursementList);
    }

    @Test
    public void getAllReimbursementsByAuthor() {
        when(reimbursementService.getAllReimbursementsByAuthor(anyInt()))
                .thenReturn(reimbursementList);

        assertEquals(reimbursementService.getAllReimbursementsByAuthor(anyInt()), reimbursementList);
    }

    @Test
    public void createReimbursement() {
        when(reimbursementService.createReimbursement(any()))
                .thenReturn(true);

        assertEquals(reimbursementService.createReimbursement(reimbursement), true);
    }

    @Test
    public void deleteReimbursement() {
        when(reimbursementService.deleteReimbursement(anyInt()))
                .thenReturn(true);

        assertEquals(reimbursementService.deleteReimbursement(anyInt()), true);
    }

    @Test
    public void updateReimbursement() {
        when(reimbursementService.updateReimbursement(any()))
                .thenReturn(true);

        assertEquals(reimbursementService.updateReimbursement(reimbursement), true);
    }

    @Test
    public void getAllReimbursements() {
        when(reimbursementService.getAllReimbursements())
                .thenReturn(reimbursementList);

        assertEquals(reimbursementService.getAllReimbursements(), reimbursementList);
    }
}