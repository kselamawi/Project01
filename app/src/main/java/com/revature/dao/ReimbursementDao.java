package com.revature.dao;

import com.revature.models.Reimbursement;

import java.util.List;

public interface ReimbursementDao {

    //Create a Reimbursement
     public boolean createReimbursement(Reimbursement reimbursement);
     //Delete a Reimbursement
     public boolean deleteReimbursement(int id);
     //Update a Reimbursement
     public boolean updateReimbursement(Reimbursement reimbursement);
     //Get all reimbursements
     public List<Reimbursement> getAllReimbursements();
    //Get a reimbursement by ID.
     public Reimbursement getReimbursementById(int id);



}
