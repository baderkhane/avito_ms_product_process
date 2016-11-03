package org.serviceAvito1.controllers;

 
 
import org.serviceAvito1.dao.IAnnonceRepository;
import org.serviceAvito1.dao.IClientRepository;
import org.serviceAvito1.entities.Annonce;
import org.serviceAvito1.entities.Client;
import org.serviceAvito1.lib.ModelClient; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import java.util.ArrayList;
import java.util.List; 
 

@CrossOrigin
@RestController
@RequestMapping("Client")
public class ClientControlleur {
	@Autowired
	private IClientRepository clientRep;
	@Autowired
	private IAnnonceRepository annonceRep;
	
	 
	
	@Value("${dir.images}")
	private String imageDir;
	
	 
	
	@RequestMapping(value ="/getAchteursDeVendeur",method = RequestMethod.GET, produces = "application/json")
	public List<Client> getAchteursDeVendeur(@RequestParam(name="idVendeur") String idVendeur){
		 
		  return annonceRep.chercherInfoAchteur(annonceRep.chercherIdAchteur(Long.valueOf(idVendeur))); 
	}
	
	@RequestMapping(value ="/getVendeursDeAchteur",method = RequestMethod.GET, produces = "application/json")
	public List<Client> getVendeursDeAchteur(@RequestParam(name="idVendeur") String idVendeur){
		List<Client> Vs=annonceRep.chercherIdVendeur(idVendeur);
		
		  return  Vs; 
	}	
	
	@RequestMapping(value ="/getInterVendeurEtAchteurbyAchteur",method = RequestMethod.GET, produces = "application/json")
	public List<ModelClient> getInterVendeurEtAchteurbyAchteur(@RequestParam(name="telAch") String telAch ,@RequestParam(name="idVendeur") String idVendeur){
	 
		   List<Client> Vs= annonceRep.chercherIdVendeur(clientRep.findByTel(telAch).getId().toString()); 
		   List<Client> As= annonceRep.chercherInfoAchteur(annonceRep.chercherIdAchteur(Long.valueOf(idVendeur))); 
		   As.retainAll(Vs);
		   
		   ModelClient model;
		   
		   Client v=clientRep.findOne(Long.valueOf(idVendeur));
		   v.setTotalPoint(annonceRep.moyenAnnoce(Long.valueOf(idVendeur)));
		   clientRep.save(v); 
		   
		   
		   List<ModelClient> sendListAchateur =new ArrayList<ModelClient>();
		   for (Client client : As) {
			   	model=new ModelClient();
			   
			   	model= client.getAvis(v,model);
			   	model.setC(client);
			   	
			    sendListAchateur.add(model);
		   }
		   model=new ModelClient();
		   model.setC(v);
		   sendListAchateur.add(model);
		   return    sendListAchateur  ; 
	}	
	
	 
	@RequestMapping(value ="/AchteurEvalu",method = RequestMethod.GET)
	public  Object  EvaluerAchteur(@RequestParam(name="idAnnonce") String idAnnonce,@RequestParam(name="value") String valueAch,@RequestParam(name="avis") String avis ){
		
		
	 
		Annonce a=annonceRep.findOne(Long.valueOf(idAnnonce));
		  
	     
		 if(a != null){
			 
			 if(a.getQrcode()==null){ 
				a.setValueAchteur(valueAch);
				a.setAvis(avis);
				return "merci";
			 }else{
				 return "deja evalue";
			 } 
		 }else{
			 return "anoonce n'existe pas"  ;
		 }
	 
	}
	 
}
