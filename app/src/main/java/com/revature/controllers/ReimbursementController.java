package com.revature.controllers;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.service.ReimbursementService;
import io.javalin.http.Context;

import java.util.List;

public class ReimbursementController {
    private ReimbursementService reimbursementService = new ReimbursementService();


    public void handleGetAllReimbursements(Context ctx){
        List<Reimbursement> reimbursementList = reimbursementService.getAllReimbursements();
        System.out.println(reimbursementList);
       if(reimbursementList.isEmpty()){
           ctx.status(400);
           ctx.result("There are no reimbursements");
       } else{
           ctx.status(200);
           ctx.json(reimbursementList);
       }

    }

    public void handleGetReimbursementsById(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        Reimbursement reimbursement = reimbursementService.getReimbursementById(id);
        if(reimbursement==null){
            ctx.status(200);
            ctx.result("Reimbursement does not exist");
        }else{
            ctx.status(200);
            ctx.json(reimbursement);
        }
    }

    public void handleDenyReimbursement(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        User user = ctx.bodyAsClass(User.class);

        if(reimbursementService.changeReimbursementStatus(id,user.getId(),false)){
            ctx.status(200);
            ctx.result("You successfully denied the reimbursement");
        }else{
            ctx.status(400);
            ctx.result("Reimbursement doesn't exist");
        }
    }

    public void handleApproveReimbursement(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        User user = ctx.bodyAsClass(User.class);

        if(reimbursementService.changeReimbursementStatus(id,user.getId(),true)){
            ctx.status(200);
            ctx.result("You successfully approved the reimbursement");
        }else{
            ctx.status(400);
            ctx.result("Reimbursement doesn't exist");
        }
    }

    public void handleGetReimbursementsByAuthor(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        List<Reimbursement> reimbursement= reimbursementService.getAllReimbursementsByAuthor(id);
        if(reimbursement.isEmpty()){
            ctx.status(200);
            ctx.result("There are no reimbursements for this author");
        }else{
            ctx.status(200);
            ctx.json(reimbursement);
        }
    }

}
