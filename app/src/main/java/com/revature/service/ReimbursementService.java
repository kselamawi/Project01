package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.models.Reimbursement;

import java.util.List;

public class ReimbursementService {

    private ReimbursementDao reimbursementDao = new ReimbursementDaoImpl();

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
