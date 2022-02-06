package com.revature.dao;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.util.ConUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoImpl implements  ReimbursementDao {


    @Override
    public boolean createReimbursement(Reimbursement reimbursement) {
        String sql = "insert into reimbursement (reimb_amount, reimb_type, reimb_author) values (?, ?, ?)";
        try(Connection c = ConUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ReimbursementType[] types = ReimbursementType.values();
            ps.setDouble(1, reimbursement.getAmount());
            ps.setInt(2, reimbursement.getReimbursementType().ordinal());
            ps.setInt(3, reimbursement.getAuthor().getId());
            int alteredRows = ps.executeUpdate();
            if (alteredRows == 1) {
                System.out.println("Reimbursement " + reimbursement.getId() + " was created");
                return true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteReimbursement(int id) {
        String sql = "delete from reimbursement where id = ?";

        try(Connection c = ConUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
        ){
            ps.setInt(1, id);
            int alteredRows = ps.executeUpdate();
            if(alteredRows == 1){
                System.out.println("Reimbursement " + id + " was deleted");
                return true;
            }


        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateReimbursement(Reimbursement reimbursement) {
        String sql = "update reimbursement set reimb_amount = ?, reimb_resolver = ?, reimb_status_id = ? where id = ?";
        try(Connection c = ConUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
        ){
            ReimbursementType[] types = ReimbursementType.values();
            ps.setDouble(1, reimbursement.getAmount());
            ps.setInt(2, reimbursement.getResolver().getId());
            ps.setInt(3, reimbursement.getReimbursementStatus().ordinal());
            ps.setInt(4, reimbursement.getId());

            int alteredRows = ps.executeUpdate();
            if(alteredRows == 1){
                System.out.println("Reimbursement " + reimbursement.getId() + " was changed");
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Reimbursement> getAllReimbursements() {
        String sql = "select * from reimbursement";

        Connection c = null;
        try {
            c = ConUtil.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            List<Reimbursement> reimbursementList = new ArrayList<>();

            while(rs.next()){
                Reimbursement reimbursement = new Reimbursement();
                int typeOrdinal = rs.getInt("reimb_type");
                int typeOrdinal2 = rs.getInt("reimb_status_id");
                ReimbursementType[] types = ReimbursementType.values();
                ReimbursementStatus[] types2 = ReimbursementStatus.values();

                reimbursement.setId(rs.getInt("id"));
                reimbursement.setReimbursementType(types[typeOrdinal]);
                User u = new User();
                u.setId(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(u);
                reimbursement.setReimbursementStatus(types2[typeOrdinal2]);
                User u2 = new User();
                u.setId(rs.getInt("reimb_author"));
                reimbursement.setAuthor(u2);
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
               // reimbursement.setDescription(rs.getString("description"));

                reimbursementList.add(reimbursement);
            }
            return reimbursementList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reimbursement getReimbursementById(int id) {
        String sql = "select * from reimbursement where id = ?";
        Reimbursement reim = new Reimbursement();

        try(Connection c = ConUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                int typeOrdinal = rs.getInt("reimb_type");
                int typeOrdinal2 = rs.getInt("reimb_status_id");
                ReimbursementType[] types = ReimbursementType.values();
                ReimbursementStatus[] types2 = ReimbursementStatus.values();

                reim.setId(rs.getInt("id"));
                reim.setReimbursementType(types[typeOrdinal]);
                User u = new User();
                if(rs.getInt("reimb_resolver")==0){
                    u.setId(null);
                } else {
                    u.setId(rs.getInt("reimb_resolver"));
                }

                reim.setResolver(u);
                reim.setReimbursementStatus(types2[typeOrdinal2]);
                User u2 = new User();

                u.setId(rs.getInt("reimb_author"));

                reim.setAuthor(u2);
                reim.setAmount(rs.getDouble("reimb_amount"));
                return reim;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
