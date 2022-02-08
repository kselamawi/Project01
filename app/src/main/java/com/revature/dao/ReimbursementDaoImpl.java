package com.revature.dao;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.util.ConUtil;
import com.revature.util.LoggingUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoImpl implements  ReimbursementDao {

    private LoggingUtil loggingUtil = new LoggingUtil();
    @Override
    public boolean approveReimbursement(int reimb_id, int resolver_id) {
        loggingUtil.queryLogger("approveReimbursement");

        String sql = "update reimbursement set reimb_status_id = 1, reimb_resolver = ?, reimb_resolved = ? where id = ?";
        try(Connection c = ConUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, resolver_id);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setInt(3, reimb_id);

            int alteredRows = ps.executeUpdate();
            if(alteredRows == 1){
                loggingUtil.logSuccessfulQuery("approve Reimbursement");

                return true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        loggingUtil.logger("Not successful");
        return false;
    }

    @Override
    public boolean denyReimbursement(int reimb_id, int resolver_id) {
        loggingUtil.queryLogger("denyReimbursement");

        String sql = "update reimbursement set reimb_status_id = 2, reimb_resolver = ?, reimb_resolved = ? where id = ?";
        try(Connection c = ConUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, resolver_id);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setInt(3, reimb_id);

            int alteredRows = ps.executeUpdate();
            if(alteredRows == 1){
                loggingUtil.logSuccessfulQuery("deny Reimbursement");

                return true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        loggingUtil.logger("Not successful");
        return false;
    }

    @Override
    public List<Reimbursement> getAllPendingReimbursements() {
        loggingUtil.queryLogger("get All Pending Reimbursements");
        String sql = "select * from reimbursement where reimb_status_id = 0";
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
    public List<Reimbursement> getAllResolvedReimbursements() {
        loggingUtil.queryLogger("get All Resolved Reimbursements");
        String sql = "select * from reimbursement where reimb_status_id = 1 or reimb_status_id = 2";
        try {
            Connection c = ConUtil.getConnection();
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
                reimbursement.setDescription(rs.getString("description"));

                reimbursementList.add(reimbursement);
            }
            return reimbursementList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reimbursement> getAllReimbursementsByAuthor(int author_id) {
        loggingUtil.queryLogger("get All Reimbursements By ID");

        String sql = "select * from users u right join reimbursement r on u.id = r.reimb_author where u.id = ?";

        Connection c = null;
        try {
            c = ConUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, author_id);
            ResultSet rs = ps.executeQuery();
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
                reimbursement.setDescription(rs.getString("description"));

                reimbursementList.add(reimbursement);
            }
            return reimbursementList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean createReimbursement(Reimbursement reimbursement) {
        loggingUtil.queryLogger("create Reimbursement");
        String sql = "insert into reimbursement (reimb_amount, reimb_type, reimb_author, reimb_submitted) values (?, ?, ?, ?)";
        try(Connection c = ConUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ReimbursementType[] types = ReimbursementType.values();
            ps.setDouble(1, reimbursement.getAmount());
            ps.setInt(2, reimbursement.getReimbursementType().ordinal());
            ps.setInt(3, reimbursement.getAuthor().getId());
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            int alteredRows = ps.executeUpdate();
            if (alteredRows == 1) {
                loggingUtil.logSuccessfulQuery("create Reimbursement");
                return true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        loggingUtil.logger("Create unsuccessful");
        return false;
    }

    @Override
    public boolean deleteReimbursement(int id) {
        loggingUtil.queryLogger("delete Reimbursement");

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
        loggingUtil.queryLogger("update Reimbursement");

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
        loggingUtil.queryLogger("get All Reimbursement");

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
        loggingUtil.queryLogger("get by ID Reimbursement");

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

    @Override
    //We're going to get all reimbursements by author id, then filter by pending
    public List<Reimbursement> getAllPendingReimbursementsByAuthor(int author_id) {
        loggingUtil.queryLogger("get All Pending Reimbursements by Author ID");
        String sql = "select * from users u right join reimbursement r on u.id = r.reimb_author where u.id = ? and r.reimb_status_id = 0";
        Connection c = null;
        try {
            c = ConUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, author_id);
            ResultSet rs = ps.executeQuery();
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
                reimbursement.setDescription(rs.getString("description"));

                reimbursementList.add(reimbursement);
            }
            return reimbursementList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reimbursement> getAllResolvedReimbursementsByAuthor(int author_id) {
        loggingUtil.queryLogger("get All Pending Reimbursements by Author ID");
        String sql = "select * from users u right join reimbursement r on u.id = r.reimb_author where u.id = ? and r.reimb_status_id != 0";
        Connection c = null;
        try {
            c = ConUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, author_id);
            ResultSet rs = ps.executeQuery();
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
                reimbursement.setDescription(rs.getString("description"));

                reimbursementList.add(reimbursement);
            }
            return reimbursementList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
