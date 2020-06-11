/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.doranco.jaxrs.dao;



import fr.doranco.jaxrs.connexion.JaxrsDataSource;
import fr.doranco.jaxrs.jersey.entity.Employe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Boule
 */
public class EmployeDAO implements IEmployeDAO {

    //Constructeur
    public EmployeDAO() {
    }
    
    //Méthodes de l'interface à redéfinir
    @Override
    public Employe getEmployeById(int id) throws Exception{
        
        
        Employe employe = new Employe();
        Connection connexion = JaxrsDataSource.getInstance().getConnection();

        String requete = "SELECT * FROM employe WHERE id = ?";
        PreparedStatement ps = null;
        try {
            
            ps = connexion.prepareStatement(requete);
            ps.setInt(1, id);
            
            
            ResultSet rs = ps.executeQuery();
            

            if (rs.next()) {
                
                employe.setId(rs.getInt("id"));
                employe.setNom(rs.getString("nom"));
                employe.setPrenom(rs.getString("prenom"));
                employe.setPosteOccupe(rs.getString("poste_occupe"));
            }else{
                System.err.println("L'employe n'existe pas !");
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e);
            return null;
        } finally {
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ex) {
                    System.err.println("Une erreur Sql est survenue : " + ex);
                    
                }

            }

        }
        return employe;
    }

    @Override
    public Employe addEmploye(Employe employe) throws Exception{
        
        Connection connexion = JaxrsDataSource.getInstance().getConnection();

        String requete = "INSERT INTO employe(nom, prenom, poste_occupe) VALUE(?,?,?)";
        PreparedStatement ps = null;
        try {
            
            ps = connexion.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, employe.getNom());
            ps.setString(2, employe.getPrenom());
            ps.setString(3, employe.getPosteOccupe());

            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();

            if (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                employe.setId(id);
            }

        } catch (Exception e) {
            System.err.println("Une erreur de connexion est survenue." + e);
        } finally {
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ex) {
                   System.err.println("Une erreur SQL est survenue : "+ex);
                }
            }

        }
        return employe;
    }

    @Override
    public List<Employe> getListeEmploye() throws Exception{
        
        
        List<Employe> listeEmployes = new ArrayList<>();
       
        Connection connexion = JaxrsDataSource.getInstance().getConnection();
       
        String requete = "SELECT * FROM employe";
        PreparedStatement ps = null;
        try {
            ps = connexion.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            
           
            while (rs.next()) {
               
                Employe employe = new Employe();
               
                employe.setId(rs.getInt("id"));
                employe.setNom(rs.getString("nom"));
                employe.setPrenom(rs.getString("prenom"));
                employe.setPosteOccupe(rs.getString("poste_occupe"));
                listeEmployes.add(employe);
                
            }
           
        } catch (SQLException e) {
            System.err.println("Une erreur SQL est survenue : "+e);
        } finally {
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ex) {
                    System.err.println("Erreur de connexion SQL : " + ex);
                }
            }
        }
        
        
        return listeEmployes;
    }

    @Override
    public void updateEmploye(Employe employe) throws Exception {
       Connection connexion = JaxrsDataSource.getInstance().getConnection();
        String requete = "UPDATE employe SET nom = ?, prenom=?, poste_occupe=? WHERE id=?";
        PreparedStatement ps = null;
        try {
            ps = connexion.prepareStatement(requete);
            //Je spécifie à quoi correspond dans Java les points d'interrogation de ma requête.
            ps.setString(1, employe.getNom());
            ps.setString(2, employe.getPrenom());
            ps.setString(3, employe.getPosteOccupe());
            ps.setInt(4, employe.getId());
            // Je mets à jour
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Une erreur de connexion est survenue." + e);
        } finally {
            if (connexion != null) {
                try {
                    connexion.close();
                } catch (SQLException ex) {
                System.err.println("Une erreur SQL est survenue : "+ex);
                }
            }
        }
        
    }

    @Override
    public void removeEmploye(Integer id) throws Exception {
        
        Connection connexion = JaxrsDataSource.getInstance().getConnection();

        String requete = "DELETE FROM employe WHERE id = " + id;
        
            try {
                PreparedStatement ps = connexion.prepareStatement(requete);
                ps.executeUpdate();
            } catch (Exception e) {
                System.err.println("Une erreur de connexion est survenue." + e);
            } finally {
                if (connexion != null) {
                    try {
                        connexion.close();
                    } catch (SQLException ex) {
                        System.err.println("Erreur de connexion SQL : " + ex);
                    }
                }
            }
    }

    

}
