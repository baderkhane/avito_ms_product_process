package org.serviceAvito1.dao;

 
import java.util.List;

import org.serviceAvito1.entities.Annonce;
import org.serviceAvito1.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; 
 
public interface IAnnonceRepository extends JpaRepository<Annonce, Long>{
	 
	
	@Query(" select c from Client  c  where c.id  in :list")
	public List<Client> chercherInfoAchteur(@Param("list")List<Long> list);
	
	@Query(" select cast(e.achteur as long)  from Annonce e  where e.clt.id = :v and e.achteur  is not null")
	public List<Long> chercherIdAchteur(@Param("v") Long  v);
	
	@Query(" select  sum(e.valueAchteur)  from Annonce e  where e.clt.id = :v and e.achteur  is not null")
	public  Long moyenAnnoce(@Param("v") Long  v);
	
	@Query(" select e.clt  from Annonce e  where  e.achteur  is not null and e.achteur = :a  ")
	public List<Client> chercherIdVendeur(@Param("a") String  idVendeur);
	
	 
	
}