package com.revature.dao;

import com.revature.models.Reimbursement;

import java.util.List;

public interface ReimbursementDao {


    //Approve a reimbursement
    public boolean approveReimbursement(int reimb_id, int resolver_id);
    //Deny a reimbursement
    public boolean denyReimbursement(int reimb_id, int resolver_id);
    //Get all pending reimbursements
    public List<Reimbursement> getAllPendingReimbursements();
    //Get all resolved reimbursements
    public List<Reimbursement> getAllResolvedReimbursements();
    //Get all reimbursements by Author
    public List<Reimbursement> getAllReimbursementsByAuthor(int author_id);
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
     //Get pending reimbursement requests by Author ID
    public List<Reimbursement> getAllPendingReimbursementsByAuthor(int author_id);
    //Get resolved reimbursement request by Author ID:
    public List<Reimbursement> getAllResolvedReimbursementsByAuthor(int author_id);



}
