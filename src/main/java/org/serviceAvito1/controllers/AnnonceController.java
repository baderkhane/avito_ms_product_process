package org.serviceAvito1.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.json.JSONObject;
import org.serviceAvito1.dao.IAnnonceRepository;
import org.serviceAvito1.dao.IClientRepository;
import org.serviceAvito1.entities.Annonce;
import org.serviceAvito1.entities.Client;
import org.serviceAvito1.lib.BitMatrixLib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import scala.annotation.meta.setter;

@CrossOrigin
@RestController
@RequestMapping("Annonce")
public class AnnonceController {
	@Autowired
	private IAnnonceRepository annonceRep;
	
	@Autowired
	private IClientRepository clientRep;
	
	@Autowired
	@Value("${dir.images}")
	private String imageDir;
	
 

	 
	@RequestMapping(value ="/ajouteAnnonce",method = RequestMethod.GET)
	public  Object  ajouteAnnonce(@RequestParam(name="idclt") Long clt){
			
		java.util.Calendar cal = java.util.Calendar.getInstance(); 
		java.sql.Date sqlDate = new Date(cal.getTimeInMillis());
		BitMatrixLib libM=new BitMatrixLib();
		BitMatrix bitMatrix; 
		Annonce an=new Annonce();
	 
		an.setPrix("2000");
		 an.setTitre("Annonce Produit");
		 an.setDescription("On nomme produit de nombres entiers, réels, complexes ou autres le résultat d'une multiplication, ou expression qui identifie les facteurs à multiplier.");
		 an.setClt(clientRep.findOne(clt));
		 an.setDate(sqlDate); 
		 an= annonceRep.save(an);
		 
		
		 String data =an.getDate()+"."+an.getIdAnonce()+"."+an.getTitre();
		 data="http://localhost:8086/validerAnnonce/"+an.getTitre()+data.hashCode()+""+an.getIdAnonce();
	     int size = 400;
	     an.setQrcode(data); 
		try {
			
			bitMatrix = libM.generateMatrix(data, size);
			String imageFormat = "png";
	        String outputFileName = imageDir+an.getDate()+"_"+an.getTitre()+"." + imageFormat;
	        libM.writeImage(outputFileName, imageFormat, bitMatrix);
	        an= annonceRep.save(an);
	         return an ;
	         
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			return new String("catcha problem");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return new String("pas image");
		}
 
	 
	  
		
	}
	
	 
	@RequestMapping(value ="/validerAnnonce",method = RequestMethod.GET)
	public  Object  validerAnnonce(@RequestParam(name="qrcode") String clt ,@RequestParam(name="Tel") String numTel){
		
		
		String code= clt.substring(clt.length() - 1); 
		Long id=Long.valueOf(code);
		Annonce a=annonceRep.findOne(id);
		  
	     
		 if(a != null){
			if(a.getQrcode().length()>12){ 
				 Client c= clientRep.findByTel(numTel);
				 
				  if(c!=null){
						 a.setQrcode(null);
						 a.setAchteur(c.getId().toString());
						 annonceRep.save(a);
						 return id+"";
				 }else{
					 return "client n'existe pas";
				 } 
			 }else{
				 return "deja vendu";
			 }
		 }else{
			 return "anoonce n'existe pas"  ;
		 }
	}
	
	
	
}
