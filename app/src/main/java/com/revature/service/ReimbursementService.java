package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.models.Reimbursement;

import java.util.List;

public class ReimbursementService {


    private ReimbursementDao reimbursementDao;

    public ReimbursementService(ReimbursementDao reimbursementDao){
    this.reimbursementDao = reimbursementDao;
    }

    //Approve or Deny a reimbursement
    public boolean changeReimbursementStatus(int reimb_id, int resolver_id, boolean approve){
        //If the approve boolean is true, it will try to approve the reimbursement.
        if(approve){
            boolean success = reimbursementDao.approveReimbursement(reimb_id, resolver_id);
            //if the approval works, it will return true.
            if(success){
                return true;
            }
            //If the approval doesn't work, ti will return false.
                return false;
            //If the approve boolean is false, it will try to deny the reimbursement.
        } else if (!approve){
            //If the success is true, it will return true.
            boolean success = reimbursementDao.denyReimbursement(reimb_id, resolver_id);
            if(success) {
                return true;
            }
            //Otherwise, it will return false.
            return false;
        }

        return false;
    }
    //Get all pending reimbursements by Author
    public List<Reimbursement> getAllPendingReimbursementsByAuthor(int author_id){
        return reimbursementDao.getAllPendingReimbursementsByAuthor(author_id);
    }

    public List<Reimbursement> getAllResolvedReimbursementsByAuthor(int author_id){
        return reimbursementDao.getAllResolvedReimbursementsByAuthor(author_id);
    }

    //Get all pending reimbursements
    public List<Reimbursement> getAllPendingReimbursements(){
        return reimbursementDao.getAllPendingReimbursements();
    }

    //Get all resolved reimbursements
    public List<Reimbursement> getAllResolvedReimbursements(){
        return reimbursementDao.getAllResolvedReimbursements();
    }
    //Get reimbursements by ID.
    public List<Reimbursement> getAllReimbursementsByAuthor(int author_id){
        return reimbursementDao.getAllReimbursementsByAuthor(author_id);
    }

    //Create a Reimbursement
    public boolean createReimbursement(Reimbursement reimbursement){
        return reimbursementDao.createReimbursement(reimbursement);
    }

    //Delete a Reimbursement
    public boolean deleteReimbursement(int id){
        return reimbursementDao.deleteReimbursement(id);
    }
    //Update a Reimbursement
    public boolean updateReimbursement(Reimbursement reimbursement){
        return reimbursementDao.updateReimbursement(reimbursement);
    }
    //Get all reimbursements
    public List<Reimbursement> getAllReimbursements(){

        return reimbursementDao.getAllReimbursements();
    }
    //Get a reimbursement by ID.
    public Reimbursement getReimbursementById(int id){
        return reimbursementDao.getReimbursementById(id);
    }


}
