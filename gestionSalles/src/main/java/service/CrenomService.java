package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Crenom;
import beans.Salle;
import connexion.Connexion;
import dao.IDao;

public class CrenomService implements IDao<Crenom> {

	@Override
	public boolean create(Crenom o) {
		 String sql = "insert into crenom values (null, ?, ?)";
	        try {
	        	System.out.println("avant prepare create");
	            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
	            System.out.println("apres prepare create");
	            ps.setTime(1, o.getHeureDebut());
	            ps.setTime(2,o.getHeureFin());
	            
	            if (ps.executeUpdate() == 1) {
	                return true;
	            }
	        } catch (SQLException e) {
	            System.out.println("create : erreur sql : " + e.getMessage());

	        }
	        return false;
	}

	@Override
	public boolean delete(Crenom o) {
		String sql = "delete from crenom where id  = ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setInt(1, o.getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("delete : erreur sql : " + e.getMessage());

        }
        return false;
	}

	@Override
	public boolean update(Crenom o) {
		 String sql = "update crenom set heureDebut = ? , heureFin = ? where id  = ?";
	        try {
	            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
	            ps.setTime(1, o.getHeureDebut());
	            ps.setTime(2,o.getHeureFin());
	            ps.setInt(3, o.getId());
	           
	            if (ps.executeUpdate() == 1) {
	                return true;
	            }
	        } catch (SQLException e) {
	            System.out.println("update : erreur sql : " + e.getMessage());

	        }
	        return false;
	}

	@Override
	public Crenom findById(int id) {
		Crenom m = null;
        String sql = "select * from crenom where id  = ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Crenom(rs.getInt("id"), rs.getTime("heureDebut"), rs.getTime("heureFin"));
            }

        } catch (SQLException e) {
            System.out.println("findById " + e.getMessage());
        }
        return null;
	}

	@Override
	public List<Crenom> findAll() {
		List<Crenom> crenoms = new ArrayList<Crenom>();

        String sql = "select * from crenom";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	crenoms.add(new Crenom(rs.getInt("id"), rs.getTime("heureDebut"), rs.getTime("heureFin")));
            }

        } catch (SQLException e) {
            System.out.println("findAll " + e.getMessage());
        }
        return crenoms;
	}

}
