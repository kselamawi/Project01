package com.revature.dao;

import com.revature.models.*;
import com.revature.util.ConUtil;
import com.revature.util.LoggingUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoImpl implements  ReimbursementDao {


    @Override
    public boolean approveReimbursement(int reimb_id, int resolver_id) {
        LoggingUtil.logger.info("approveReimbursement");
        String sql = "update reimbursement set reimb_status_id = 1, reimb_resolver = ?, reimb_resolved = ? where id = ?";
        try(Connection c = ConUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, resolver_id);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setInt(3, reimb_id);

            int alteredRows = ps.executeUpdate();
            if(alteredRows == 1){
                LoggingUtil.logger.info("Reimbursement was approved successfully");

                return true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        LoggingUtil.logger.warn("Reimbursement approve wasn't successfully");
        return false;
    }

    @Override
    public boolean denyReimbursement(int reimb_id, int resolver_id) {
        LoggingUtil.logger.info("denyReimbursement");

        String sql = "update reimbursement set reimb_status_id = 2, reimb_resolver = ?, reimb_resolved = ? where id = ?";
        try(Connection c = ConUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, resolver_id);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setInt(3, reimb_id);

            int alteredRows = ps.executeUpdate();
            if(alteredRows == 1){
                LoggingUtil.logger.info("Reimbursement deny  was successful ");

                return true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        LoggingUtil.logger.warn("Reimbursement deny wasn't successful ");
        return false;
    }

    @Override
    public List<Reimbursement> getAllPendingReimbursements() {
        LoggingUtil.logger.info("get All Pending Reimbursements");
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
               if( rs.getInt(5)!=0) {
                    User u = new User();
                    u.setId(rs.getInt("reimb_resolver"));
                reimbursement.setResolver(u);
                   LoggingUtil.logger.info("Getting all pending Reimbursement was successful ");
                }

                reimbursement.setReimbursementStatus(types2[typeOrdinal2]);
                User u2 = new User();
                u2.setId(rs.getInt("reimb_author"));
                reimbursement.setAuthor(u2);
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                // reimbursement.setDescription(rs.getString("description"));
                reimbursementList.add(reimbursement);
            }
            return reimbursementList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        LoggingUtil.logger.warn("Getting all pending Reimbursement wasn't successful ");
        return null;
    }

    @Override
    public List<Reimbursement> getAllResolvedReimbursements() {
        LoggingUtil.logger.info("get All Resolved Reimbursements");
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
                if(rs.getInt("reimb_resolver") !=0) {
                    User u = new User();
                    u.setId(rs.getInt("reimb_resolver"));
                    reimbursement.setResolver(u);
                    LoggingUtil.logger.info("Getting all resolved Reimbursement  was successful ");
                }
                reimbursement.setReimbursementStatus(types2[typeOrdinal2]);
                User u2 = new User();
                u2.setId(rs.getInt("reimb_author"));
                reimbursement.setAuthor(u2);
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                reimbursement.setDescription(rs.getString("description"));

                reimbursementList.add(reimbursement);
            }
            return reimbursementList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        LoggingUtil.logger.warn("Getting all resolved Reimbursement wasn't successful ");
        return null;
    }

    @Override
    public List<Reimbursement> getAllReimbursementsByAuthor(int author_id) {
        LoggingUtil.logger.info("get All Reimbursements By ID");

        String sql = "select * from users u inner join reimbursement r on r.reimb_author = u.id inner join users u2 on r.reimb_resolver = u2.id where u.id = ?";

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

                reimbursement.setId(rs.getInt(7));
                reimbursement.setReimbursementType(types[typeOrdinal]);
                if(rs.getInt(16)!=0) {
                    User u = new User();
                    u.setId(rs.getInt(16));

                    int typeOrdinalU = rs.getInt(21);
                    UserRole[] uR = UserRole.values();
                    u.setUserRole(uR[typeOrdinalU]);
                    u.setEmail(rs.getString(20));
                    u.setF_name(rs.getString(18));
                    u.setL_name(rs.getString(19));
                    u.setPassword(rs.getString(17));
                    reimbursement.setResolver(u);
                    LoggingUtil.logger.info("Getting all Reimbursement by author was successful ");
                }
                reimbursement.setReimbursementStatus(types2[typeOrdinal2]);
                User u2 = new User();
                int typeOrdinalU2 = rs.getInt("user_role");
                UserRole[] uR2 = UserRole.values();
                u2.setUserRole(uR2[typeOrdinalU2]);
                u2.setEmail(rs.getString("email"));
                u2.setF_name(rs.getString("first_name"));
                u2.setL_name(rs.getString("last_name"));
                u2.setPassword(rs.getString("password"));
                u2.setId(rs.getInt("reimb_author"));
                reimbursement.setAuthor(u2);
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                reimbursement.setDescription(rs.getString("description"));

                reimbursementList.add(reimbursement);
            }
            return reimbursementList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        LoggingUtil.logger.warn("Getting all Reimbursement by author wasn't successful ");
        return null;
    }

    @Override
    public boolean createReimbursement(Reimbursement reimbursement) {
        LoggingUtil.logger.info("create Reimbursement");
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
                LoggingUtil.logger.info("Creating Reimbursement was successful ");
                return true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        LoggingUtil.logger.warn("Creating reimbursement  was unsuccessful");
        return false;
    }

    @Override
    public boolean deleteReimbursement(int id) {
        LoggingUtil.logger.info("delete Reimbursement");

        String sql = "delete from reimbursement where id = ?";

        try(Connection c = ConUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
        ){
            ps.setInt(1, id);
            int alteredRows = ps.executeUpdate();
            if(alteredRows == 1){
                System.out.println("Reimbursement " + id + " was deleted");
                LoggingUtil.logger.info("Deleting Reimbursement  was successful ");
                return true;
            }


        } catch (SQLException e){
            e.printStackTrace();
        }
        LoggingUtil.logger.warn(" Deleting Reimbursement  was unsuccessful ");
        return false;
    }

    @Override
    public boolean updateReimbursement(Reimbursement reimbursement) {
        LoggingUtil.logger.info("update Reimbursement");

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
                LoggingUtil.logger.info("Updating Reimbursement  was successful ");
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        LoggingUtil.logger.warn("Updating Reimbursement  was unsuccessful ");
        return false;
    }

    @Override
    public List<Reimbursement> getAllReimbursements() {
        LoggingUtil.logger.info("get All Reimbursement");

        String sql = "select * from users u inner join reimbursement r on r.reimb_author = u.id inner join users u2 on r.reimb_resolver = u2.id ";


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

                reimbursement.setId(rs.getInt(7));
                reimbursement.setReimbursementType(types[typeOrdinal]);
                if(rs.getInt(16)!=0) {
                    User u = new User();
                    u.setId(rs.getInt(16));

                    int typeOrdinalU = rs.getInt(21);
                    UserRole[] uR = UserRole.values();
                    u.setUserRole(uR[typeOrdinalU]);
                    u.setEmail(rs.getString(20));
                    u.setF_name(rs.getString(18));
                    u.setL_name(rs.getString(19));
                    u.setPassword(rs.getString(17));
                    reimbursement.setResolver(u);
                }
                reimbursement.setReimbursementStatus(types2[typeOrdinal2]);

                User u2 = new User();
                u2.setId(rs.getInt("reimb_author"));

                int typeOrdinalU2 = rs.getInt("user_role");
                UserRole[] uR2 = UserRole.values();
                u2.setUserRole(uR2[typeOrdinalU2]);
                u2.setEmail(rs.getString("email"));
                u2.setF_name(rs.getString("first_name"));
                u2.setL_name(rs.getString("last_name"));
                u2.setPassword(rs.getString("password"));

                reimbursement.setAuthor(u2);
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
               // reimbursement.setDescription(rs.getString("description"));
                reimbursementList.add(reimbursement);
            }
            LoggingUtil.logger.info("Getting all Reimbursement was successful ");
            return reimbursementList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        LoggingUtil.logger.warn(" Getting Reimbursement was unsuccessful ");
        return null;
    }

    @Override
    public Reimbursement getReimbursementById(int id) {
        LoggingUtil.logger.info("get by ID Reimbursement");

        String sql = "select * from users u inner join reimbursement r on r.reimb_author = u.id inner join users u2 on r.reimb_resolver = u2.id where r.id = ?";
        Reimbursement reim = new Reimbursement();

        try(Connection c = ConUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);
            if(rs.next()){
                int typeOrdinal = rs.getInt("reimb_type");
                int typeOrdinal2 = rs.getInt("reimb_status_id");
                ReimbursementType[] types = ReimbursementType.values();
                ReimbursementStatus[] types2 = ReimbursementStatus.values();

                reim.setId(rs.getInt(7));
                reim.setReimbursementType(types[typeOrdinal]);
                User u = new User();

                if(rs.getInt(16)==0){
                    u.setId(null);
                } else {
                    u.setId(rs.getInt(16));
                    int typeOrdinalU = rs.getInt(21);
                    UserRole[] uR = UserRole.values();
                    u.setUserRole(uR[typeOrdinalU]);
                    u.setEmail(rs.getString(20));
                    u.setF_name(rs.getString(18));
                    u.setL_name(rs.getString(19));
                    u.setPassword(rs.getString(17));
                    reim.setResolver(u);
                }

                reim.setReimbursementStatus(types2[typeOrdinal2]);
                User u2 = new User();

                u2.setId(rs.getInt("reimb_author"));

                int typeOrdinalU2 = rs.getInt("user_role");
                UserRole[] uR2 = UserRole.values();
                u2.setUserRole(uR2[typeOrdinalU2]);
                u2.setEmail(rs.getString("email"));
                u2.setF_name(rs.getString("first_name"));
                u2.setL_name(rs.getString("last_name"));
                u2.setPassword(rs.getString("password"));

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
        LoggingUtil.logger.info("get All Pending Reimbursements by Author ID");
        String sql = "select * from users u inner join reimbursement r on r.reimb_author = u.id where u.id = ? and r.reimb_status_id = 0";
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

                reimbursement.setId(rs.getInt(7));
                reimbursement.setReimbursementType(types[typeOrdinal]);
                if(rs.getInt("reimb_author")!=0) {
                    User u = new User();
                    u.setId(rs.getInt(1));
                    reimbursement.setResolver(u);
                }
                reimbursement.setReimbursementStatus(types2[typeOrdinal2]);
                User u2 = new User();
                u2.setId(rs.getInt("reimb_author"));
                reimbursement.setAuthor(u2);
                reimbursement.setAmount(rs.getDouble("reimb_amount"));
                reimbursement.setDescription(rs.getString("description"));
                reimbursementList.add(reimbursement);
            }
            System.out.println(reimbursementList);
            return reimbursementList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reimbursement> getAllResolvedReimbursementsByAuthor(int author_id) {
        LoggingUtil.logger.info("get All Pending Reimbursements by Author ID");
        String sql = "select * from users u inner join reimbursement r on r.reimb_author = u.id where u.id = ? and  r.reimb_status_id != 0";
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

                reimbursement.setId(rs.getInt(7));
                reimbursement.setReimbursementType(types[typeOrdinal]);
                User u = new User();
                u.setId(rs.getInt(1));
                reimbursement.setResolver(u);
                reimbursement.setReimbursementStatus(types2[typeOrdinal2]);
                User u2 = new User();
                u2.setId(rs.getInt("reimb_author"));
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
