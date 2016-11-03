package org.serviceAvito1.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

 
@Entity
public class Annonce implements Serializable {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long idAnonce;
	
	
	    private String titre;
		private String description;
		private String prix;
		private String qrcode;
		private String achteur;
		private String valueAchteur;
		private String avis; 
	
		private Date date ;
		
		@ManyToOne
		@JoinColumn(name="ID_Client")
		private Client clt;
		
		
		public Annonce() {
			super();
		}
		
		public Annonce(String titre, String description, String prix, String qrcode, String achteur, String avis) {
			super();
			this.titre = titre;
			this.description = description;
			this.prix = prix;
			this.qrcode = qrcode;
			this.achteur = achteur;
			this.avis = avis;
		}
		
		
		public String getValueAchteur() {
			return valueAchteur;
		}

		public void setValueAchteur(String valueAchteur) {
			this.valueAchteur = valueAchteur;
		}

		public Long getIdAnonce() {
			return idAnonce;
		}

		public void setIdAnonce(Long idAnonce) {
			this.idAnonce = idAnonce;
		}

		public Client getClt() {
			return clt;
		}

		public void setClt(Client clt) {
			this.clt = clt;
		}
		public Date getDate() {
					return date;
				}
		
				public void setDate(Date date2) {
					this.date = date2;
				}
		public String getTitre() {
			return titre;
		}
		

		public void setTitre(String titre) {
			this.titre = titre;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getPrix() {
			return prix;
		}
		public void setPrix(String prix) {
			this.prix = prix;
		}
		public String getQrcode() {
			return this.qrcode;
		}
		public void setQrcode(String qrcode) {
			this.qrcode = qrcode;
		}
		public String getAchteur() {
			return this.achteur;
		}
		public void setAchteur(String achteur) {
			this.achteur = achteur;
		}

		public String getAvis() {
			return avis;
		}

		public void setAvis(String avis) {
			this.avis = avis;
		}
		 

}
