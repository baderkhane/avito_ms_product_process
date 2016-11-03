package org.serviceAvito1.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.serviceAvito1.lib.ModelClient;

import com.fasterxml.jackson.annotation.JsonIgnore; 


@Entity
public class Client implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String login;
	private String password;
	private String tel; 
	private String prenom;
	private String nom;
	private String image;
	private String verifier;
	private String token;

	private Long totalPoint;
	@OneToMany(mappedBy="clt",fetch=FetchType.LAZY)
	@JsonIgnore
	private Collection<Annonce> annonce;
	
	public Client() {
		super();
	}
	
	public Client( String tel,String prenom, String nom, String verifier ,String login,String password) {
		super(); 
		this.tel = tel;
		this.login = login;
		this.password = password; 
		this.prenom = prenom;
		this.nom = nom;
		this.verifier = verifier;
	}
 
	public ModelClient getAvis( Client v, ModelClient model){
 
	  
				     for(Annonce a : v.getAnnonce()){ 
				    	 
				    	 
				    	  
				    	 if(  a.getAchteur().toString().equals(String.valueOf(this.getId())) ){ 
				    		 // model.setTotalPoint( a.getValueAchteur() + model.getTotalPoint());
				    		 if(a.getAvis()=="yes")
				    		  model.setDlike(model.getDlike()+1);
				    		 else if(a.getAvis()=="no")
				    		  model.setLike(model.getLike()+1);
				    	 }   }
				    		 
				     
				  
		  return  model;
	}
	
	public Long getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(Long totalPoint) {
		this.totalPoint = totalPoint;
	}

	public Collection<Annonce> getAnnonce() {
		return annonce;
	}
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setAnnonce(Collection<Annonce> annonce) {
		this.annonce = annonce;
	}

	@Override
	public String toString() {
		return "Client [login=" + login + ", password=" + password + ", Tel=" + tel + ", prenom="
				+ prenom + ", nom=" + nom + ", verifier=" + verifier + "]";
	}	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTel() {
		return tel;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	public void setTel(String tel) {
		this.tel = tel; 
	}
 
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getVerifier() {
		return verifier;
	}
	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
 	
}
