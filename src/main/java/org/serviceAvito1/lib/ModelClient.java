package org.serviceAvito1.lib;

import java.io.Serializable;

import org.serviceAvito1.entities.Client;

//class model  

public class ModelClient implements Serializable {
private Client c;
private int like;
private int dlike; 

public ModelClient() {
	 
	this.c = null;
	this.like = 0;
	this.dlike = 0;
	 
}

 
public Client getC() {
	return c;
}
public void setC(Client c) {
	this.c = c;
}
public int getLike() {
	return like;
}
public void setLike(int like) {
	this.like = like;
}
public int getDlike() {
	return dlike;
}
public void setDlike(int dlike) {
	this.dlike = dlike;
}

}
